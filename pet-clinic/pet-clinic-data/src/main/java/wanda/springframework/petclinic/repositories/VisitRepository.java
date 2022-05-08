package wanda.springframework.petclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wanda.springframework.petclinic.model.Visit;

public interface VisitRepository extends JpaRepository<Visit,  Long> {

}
