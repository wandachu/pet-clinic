package wanda.springframework.petclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import wanda.springframework.petclinic.model.Specialty;
import wanda.springframework.petclinic.repositories.SpecialityRepository;
import wanda.springframework.petclinic.services.SpecialtyService;

@Service
@Profile("springdatajpa")
public class SpecialtySDJpaService extends AbstractJpaService<Specialty, SpecialityRepository> implements
    SpecialtyService {

  public SpecialtySDJpaService(SpecialityRepository repository) {
    super(repository);
  }
}
