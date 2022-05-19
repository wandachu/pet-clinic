package wanda.springframework.petclinic.controllers;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wanda.springframework.petclinic.model.Pet;
import wanda.springframework.petclinic.model.Visit;
import wanda.springframework.petclinic.services.PetService;
import wanda.springframework.petclinic.services.VisitService;

@RequestMapping("/owners/{ownerId}/pets/{petId}/visits")
@Controller
public class VisitController {

  public static final String PETS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";

  private final VisitService visitService;
  private final PetService petService;

  public VisitController(VisitService visitService, PetService petService) {
    this.visitService = visitService;
    this.petService = petService;
  }

  @InitBinder
  public void setAllowedFields(WebDataBinder dataBinder) {
    dataBinder.setDisallowedFields("id");
  }

  @GetMapping("/new")
  public String initNewVisitForm(@PathVariable Long petId, Model model) {
    model.addAttribute("visit", new Visit());
    model.addAttribute("pet", petService.findById(petId));
    return PETS_CREATE_OR_UPDATE_VISIT_FORM;
  }

  @PostMapping("/new")
  public String processNewVisitForm(@Valid @ModelAttribute Visit visit, @PathVariable Long petId, BindingResult result) {
    if (result.hasErrors()) {
      return PETS_CREATE_OR_UPDATE_VISIT_FORM;
    } else {
      Pet petFound = petService.findById(petId);
      visit.setPet(petFound);
      visitService.save(visit);
      return "redirect:/owners/" + petFound.getOwner().getId();
    }
  }
}
