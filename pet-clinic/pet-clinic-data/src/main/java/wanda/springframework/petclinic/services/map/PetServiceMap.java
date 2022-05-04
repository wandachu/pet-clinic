package wanda.springframework.petclinic.services.map;

import java.util.Set;
import org.springframework.stereotype.Service;
import wanda.springframework.petclinic.model.Pet;
import wanda.springframework.petclinic.services.PetService;

@Service
public class PetServiceMap extends AbstractMapService<Pet> implements PetService {

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
    return super.save(object);
  }

  @Override
  public Pet findById(Long id) {
    return super.findById(id);
  }
}
