package wanda.springframework.petclinic.services;

import java.util.List;
import wanda.springframework.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner> {
  Owner findByLastName(String lastName);
  List<Owner> findAllByLastNameLike(String lastName);
}
