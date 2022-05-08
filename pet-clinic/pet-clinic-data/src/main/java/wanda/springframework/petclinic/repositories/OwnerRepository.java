package wanda.springframework.petclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wanda.springframework.petclinic.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
  Owner findByLastName(String lastName);
}
