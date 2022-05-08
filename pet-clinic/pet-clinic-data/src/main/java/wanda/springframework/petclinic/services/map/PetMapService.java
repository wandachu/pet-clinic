package wanda.springframework.petclinic.services.map;

import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import wanda.springframework.petclinic.model.Pet;
import wanda.springframework.petclinic.services.PetService;
import wanda.springframework.petclinic.services.PetTypeService;

@Service
@Profile({"default", "map"})
public class PetMapService extends AbstractMapService<Pet> implements PetService {
  private final PetTypeService petTypeService;

  public PetMapService(PetTypeService petTypeService) {
    this.petTypeService = petTypeService;
  }

  @Override
  public Set<Pet> findAll() {
    return super.findAll();
  }

  @Override
  public void deleteById(Long id) {
    super.deleteById(id);
  }

  @Override
  public void delete(Pet object) {
    super.delete(object);
  }

  @Override
  public Pet save(Pet object) {
    if (object.getPetType() == null) { // this pet must have a PetType
      throw new RuntimeException("PetType is required");
    }
    this.petTypeService.save(object.getPetType()); // save the PetType
    return super.save(object); // save the Pet
  }

  @Override
  public Pet findById(Long id) {
    return super.findById(id);
  }
}
