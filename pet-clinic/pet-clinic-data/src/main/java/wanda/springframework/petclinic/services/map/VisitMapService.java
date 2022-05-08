package wanda.springframework.petclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import wanda.springframework.petclinic.model.Visit;
import wanda.springframework.petclinic.services.VisitService;

@Service
@Profile({"default", "map"})
public class VisitMapService extends AbstractMapService<Visit> implements VisitService {

  @Override
  public Visit save(Visit object) {
    if (object == null || object.getPet() == null || object.getPet().getId() == null
        || object.getPet().getOwner() == null || object.getPet().getOwner().getId() == null
        || object.getPet().getPetType() == null || object.getPet().getPetType().getId() == null) {
      throw new RuntimeException("Invalid visit!");
    }
    return super.save(object);
  }
}
