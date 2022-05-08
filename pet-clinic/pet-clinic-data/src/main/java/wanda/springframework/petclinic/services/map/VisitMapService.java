package wanda.springframework.petclinic.services.map;

import wanda.springframework.petclinic.model.Visit;
import wanda.springframework.petclinic.services.VisitService;

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
