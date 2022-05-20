package wanda.springframework.petclinic.controllers;

import java.util.Collection;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wanda.springframework.petclinic.model.Owner;
import wanda.springframework.petclinic.model.Pet;
import wanda.springframework.petclinic.model.PetType;
import wanda.springframework.petclinic.services.OwnerService;
import wanda.springframework.petclinic.services.PetService;
import wanda.springframework.petclinic.services.PetTypeService;

@RequestMapping("/owners/{ownerId}")
@Controller
public class PetController {
  private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

  private final PetService petService;
  private final OwnerService ownerService;
  private final PetTypeService petTypeService;

  public PetController(PetService petService, OwnerService ownerService,
      PetTypeService petTypeService) {
    this.petService = petService;
    this.ownerService = ownerService;
    this.petTypeService = petTypeService;
  }

  @ModelAttribute("types")
  public Collection<PetType> populatePetTypes() {
    return petTypeService.findAll();
  }

  @ModelAttribute("owner")
  public Owner findOwner(@PathVariable Long ownerId) {
    return ownerService.findById(ownerId);
  }

  @InitBinder("owner")
  public void initOwnerBinder(WebDataBinder dataBinder) {
    dataBinder.setDisallowedFields("id");
  }

  @GetMapping("/pets/new")
  public String initCreationForm(Model model, Owner owner) {
    Pet pet = new Pet();
    pet.setOwner(owner); // add this so the webpage will display owner's name
    model.addAttribute("pet", pet);
    return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
  }

  @PostMapping("/pets/new")
  public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, Model model) {
    if (StringUtils.hasLength(pet.getName())
        && pet.isNew() && owner.getPet(pet.getName(), true) != null) { // search in non-new pets and found existing one
      result.rejectValue("name", "duplicate", "already exists");
    }
    if (result.hasErrors()) {
      return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    } else {
      pet.setOwner(owner); // must reset the relationship when the form is submitted
      petService.save(pet);
      return "redirect:/owners/" + owner.getId();
    }
  }

  @GetMapping("/pets/{petId}/edit")
  public String initUpdateForm(@PathVariable Long petId, Model model) {
    model.addAttribute("pet", petService.findById(petId));
    return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
  }

  @PostMapping("pets/{petId}/edit")
  public String processUpdateForm(@Valid Pet pet, BindingResult result, Model model, Owner owner) {
    if (result.hasErrors()) {
      model.addAttribute("pet", pet);
      return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    } else {
      pet.setOwner(owner); // must reset the relationship when the form is submitted
      petService.save(pet);
      return "redirect:/owners/" + owner.getId();
    }
  }
}
