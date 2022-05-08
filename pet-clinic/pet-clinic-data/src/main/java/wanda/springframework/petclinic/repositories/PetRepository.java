package wanda.springframework.petclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wanda.springframework.petclinic.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {

}
