package wanda.springframework.petclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import wanda.springframework.petclinic.model.Visit;
import wanda.springframework.petclinic.repositories.VisitRepository;
import wanda.springframework.petclinic.services.VisitService;

@Service
@Profile("springdatajpa")
public class VisitSDJpaService extends AbstractJpaService<Visit, VisitRepository> implements
    VisitService {

  public VisitSDJpaService(VisitRepository repository) {
    super(repository);
  }
}
