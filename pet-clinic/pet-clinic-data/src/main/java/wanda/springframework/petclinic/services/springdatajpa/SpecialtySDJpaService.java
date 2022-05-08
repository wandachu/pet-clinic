package wanda.springframework.petclinic.services.springdatajpa;

import wanda.springframework.petclinic.model.Specialty;
import wanda.springframework.petclinic.repositories.SpecialityRepository;
import wanda.springframework.petclinic.services.SpecialtyService;

public class SpecialtySDJpaService extends AbstractJpaService<Specialty, SpecialityRepository> implements
    SpecialtyService {

  public SpecialtySDJpaService(SpecialityRepository repository) {
    super(repository);
  }
}
