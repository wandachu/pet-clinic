package wanda.springframework.petclinic.services;

import java.util.Set;
import wanda.springframework.petclinic.model.BaseEntity;

public interface CrudService<T extends BaseEntity> {
  Set<T> findAll();
  T findById(Long id);
  T save(T object);
  void delete(T object);
  void deleteById(Long id);
}
