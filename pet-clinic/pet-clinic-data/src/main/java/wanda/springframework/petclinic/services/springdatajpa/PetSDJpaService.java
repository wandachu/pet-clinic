package wanda.springframework.petclinic.services.springdatajpa;

import wanda.springframework.petclinic.model.Pet;
import wanda.springframework.petclinic.repositories.PetRepository;
import wanda.springframework.petclinic.services.PetService;

public class PetSDJpaService extends AbstractJpaService<Pet, PetRepository> implements PetService {

  public PetSDJpaService(PetRepository repository) {
    super(repository);
  }
}
