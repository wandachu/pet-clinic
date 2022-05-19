package wanda.springframework.petclinic.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import wanda.springframework.petclinic.model.Owner;
import wanda.springframework.petclinic.services.OwnerService;

@RequestMapping("/owners")
@Controller
public class OwnerController {
  private final OwnerService ownerService;

  @Autowired
  public OwnerController(OwnerService ownerService) {
    this.ownerService = ownerService;
  }

  @InitBinder
  public void setAllowedFields(WebDataBinder dataBinder) {
    dataBinder.setDisallowedFields("id");
  }

  @GetMapping("/find")
  public String findOwner(Model model) {
    model.addAttribute("owner", Owner.builder().build());
    return "owners/findOwners";
  }

  @GetMapping
  public String processFindForm(Owner owner, Model model, BindingResult result) {
    if (owner.getLastName() == null) {
      owner.setLastName(""); // empty string signifies the broadest possible search
    }

    List<Owner> res = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");

    if (res.isEmpty()) {
      result.rejectValue("lastName", "notFound", "not found");
      return "owners/findOwners";
    } else if (res.size() == 1) {
      owner = res.get(0);
      return "redirect:/owners/" + owner.getId();
    } else {
      model.addAttribute("selections", res);
      return "owners/ownersList";
    }
  }

  @GetMapping("/{ownerId}")
  public ModelAndView showOwner(@PathVariable Long ownerId) {
    ModelAndView mav = new ModelAndView("owners/ownerDetails");
    mav.addObject(this.ownerService.findById(ownerId));
    return mav;
  }
}
