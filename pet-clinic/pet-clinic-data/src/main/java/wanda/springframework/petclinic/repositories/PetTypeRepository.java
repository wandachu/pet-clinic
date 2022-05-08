package wanda.springframework.petclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wanda.springframework.petclinic.model.PetType;

public interface PetTypeRepository extends JpaRepository<PetType, Long> {

}
