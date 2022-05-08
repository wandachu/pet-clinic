package wanda.springframework.petclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import wanda.springframework.petclinic.model.Pet;
import wanda.springframework.petclinic.repositories.PetRepository;
import wanda.springframework.petclinic.services.PetService;

@Service
@Profile("springdatajpa")
public class PetSDJpaService extends AbstractJpaService<Pet, PetRepository> implements PetService {

  public PetSDJpaService(PetRepository repository) {
    super(repository);
  }
}
