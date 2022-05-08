package wanda.springframework.petclinic.services.springdatajpa;

import wanda.springframework.petclinic.model.PetType;
import wanda.springframework.petclinic.repositories.PetTypeRepository;
import wanda.springframework.petclinic.services.PetTypeService;

public class PetTypeSDJpaService extends AbstractJpaService<PetType, PetTypeRepository> implements
    PetTypeService {

  public PetTypeSDJpaService(PetTypeRepository repository) {
    super(repository);
  }
}
