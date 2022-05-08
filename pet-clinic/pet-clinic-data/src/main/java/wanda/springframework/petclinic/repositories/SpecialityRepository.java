package wanda.springframework.petclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wanda.springframework.petclinic.model.Specialty;

public interface SpecialityRepository extends JpaRepository<Specialty, Long> {

}
