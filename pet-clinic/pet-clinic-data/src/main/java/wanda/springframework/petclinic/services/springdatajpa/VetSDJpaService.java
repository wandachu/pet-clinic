package wanda.springframework.petclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import wanda.springframework.petclinic.model.Vet;
import wanda.springframework.petclinic.repositories.VetRepository;
import wanda.springframework.petclinic.services.VetService;

@Service
@Profile("springdatajpa")
public class VetSDJpaService extends AbstractJpaService<Vet, VetRepository> implements VetService {

  public VetSDJpaService(VetRepository repository) {
    super(repository);
  }
}
