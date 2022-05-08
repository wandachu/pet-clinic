package wanda.springframework.petclinic.services;

import wanda.springframework.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner> {
  Owner findByLastName(String lastName);
}
