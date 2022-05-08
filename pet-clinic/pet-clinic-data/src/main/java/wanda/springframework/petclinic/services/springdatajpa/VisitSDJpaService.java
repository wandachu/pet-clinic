package wanda.springframework.petclinic.services.springdatajpa;

import wanda.springframework.petclinic.model.Visit;
import wanda.springframework.petclinic.repositories.VisitRepository;
import wanda.springframework.petclinic.services.VisitService;

public class VisitSDJpaService extends AbstractJpaService<Visit, VisitRepository> implements
    VisitService {

  public VisitSDJpaService(VisitRepository repository) {
    super(repository);
  }
}
