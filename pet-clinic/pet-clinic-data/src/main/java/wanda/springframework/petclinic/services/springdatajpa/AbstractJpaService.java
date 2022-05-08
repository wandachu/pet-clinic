package wanda.springframework.petclinic.services.springdatajpa;

import java.util.HashSet;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import wanda.springframework.petclinic.model.BaseEntity;
import wanda.springframework.petclinic.services.CrudService;

public abstract class AbstractJpaService<T extends BaseEntity, R extends JpaRepository<T, Long>>
    implements CrudService<T> {

  protected final R repository;

  public AbstractJpaService(R repository) {
    this.repository = repository;
  }

  @Override
  public T save(T entity) {
    return repository.save(entity);
  }

  @Override
  public T findById(Long aLong) {
    return repository.findById(aLong).orElse(null);
  }


  @Override
  public Set<T> findAll() {
    return new HashSet<>(repository.findAll());
  }


  @Override
  public void deleteById(Long aLong) {
    repository.deleteById(aLong);
  }

  @Override
  public void delete(T entity) {
    repository.delete(entity);
  }
}
