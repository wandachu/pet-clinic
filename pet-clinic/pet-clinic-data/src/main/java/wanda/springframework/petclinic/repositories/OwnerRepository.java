package wanda.springframework.petclinic.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import wanda.springframework.petclinic.model.Owner;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
  Owner findByLastName(String lastName);
  List<Owner> findAllByLastNameLike(@Param("lastName") String lastName);
}
