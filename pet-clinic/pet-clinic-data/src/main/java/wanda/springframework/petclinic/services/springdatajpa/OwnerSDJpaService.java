package wanda.springframework.petclinic.services.springdatajpa;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import wanda.springframework.petclinic.model.Owner;
import wanda.springframework.petclinic.repositories.OwnerRepository;
import wanda.springframework.petclinic.services.OwnerService;

@Service
@Profile("springdatajpa")
public class OwnerSDJpaService extends AbstractJpaService<Owner, OwnerRepository> implements OwnerService {

  public OwnerSDJpaService(OwnerRepository ownerRepository) {
    super(ownerRepository);
  }

  @Override
  public Owner findByLastName(String lastName) {
    return repository.findByLastName(lastName);
  }

  @Override
  public List<Owner> findAllByLastNameLike(String lastName) {
    return repository.findAllByLastNameLike(lastName);
  }
}
