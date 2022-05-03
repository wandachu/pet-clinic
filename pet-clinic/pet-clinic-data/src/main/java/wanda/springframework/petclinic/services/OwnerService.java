package wanda.springframework.petclinic.services;

import java.util.Set;
import wanda.springframework.petclinic.model.Owner;

public interface OwnerService extends CrudService<OwnerService, Long> {
  Owner findByLastName(String lastName);
}
