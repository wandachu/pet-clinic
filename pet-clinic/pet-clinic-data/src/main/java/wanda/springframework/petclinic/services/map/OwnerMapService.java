package wanda.springframework.petclinic.services.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import wanda.springframework.petclinic.model.Owner;
import wanda.springframework.petclinic.services.OwnerService;
import wanda.springframework.petclinic.services.PetService;

@Service
@Profile({"default", "map"})
public class OwnerMapService extends AbstractMapService<Owner> implements
    OwnerService {

  private final PetService petService;

  public OwnerMapService(PetService petService) {
    this.petService = petService;
  }

  @Override
  public Set<Owner> findAll() {
    return super.findAll();
  }

  @Override
  public void deleteById(Long id) {
    super.deleteById(id);
  }

  @Override
  public void delete(Owner object) {
    super.delete(object);
  }

  @Override
  public Owner save(Owner object) {
    if (object == null) return null;
    object.getPets().forEach(petService::save);
    return super.save(object);
  }

  @Override
  public Owner findById(Long id) {
    return super.findById(id);
  }

  @Override
  public Owner findByLastName(String lastName) {
    return this.findAll()
               .stream()
               .filter(owner -> owner.getLastName().equalsIgnoreCase(lastName))
               .findFirst().orElse(null);
  }

  @Override
  public List<Owner> findAllByLastNameLike(String lastName) {
    List<Owner> res = new ArrayList<>();
    for (Owner owner : this.findAll()) {
      if (owner.getLastName().equalsIgnoreCase(lastName)) {
        res.add(owner);
      }
    }
    return res;
  }
}
