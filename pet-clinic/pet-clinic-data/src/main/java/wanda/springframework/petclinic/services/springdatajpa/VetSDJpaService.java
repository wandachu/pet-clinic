package wanda.springframework.petclinic.services.springdatajpa;

import wanda.springframework.petclinic.model.Vet;
import wanda.springframework.petclinic.repositories.VetRepository;
import wanda.springframework.petclinic.services.VetService;

public class VetSDJpaService extends AbstractJpaService<Vet, VetRepository> implements VetService {

  public VetSDJpaService(VetRepository repository) {
    super(repository);
  }
}
