package wanda.springframework.petclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import wanda.springframework.petclinic.model.PetType;
import wanda.springframework.petclinic.repositories.PetTypeRepository;
import wanda.springframework.petclinic.services.PetTypeService;

@Service
@Profile("springdatajpa")
public class PetTypeSDJpaService extends AbstractJpaService<PetType, PetTypeRepository> implements
    PetTypeService {

  public PetTypeSDJpaService(PetTypeRepository repository) {
    super(repository);
  }
}
