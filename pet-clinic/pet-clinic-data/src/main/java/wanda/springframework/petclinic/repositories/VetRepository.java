package wanda.springframework.petclinic.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import wanda.springframework.petclinic.model.Vet;

public interface VetRepository extends JpaRepository<Vet, Long> {

}
