package wanda.springframework.petclinic.services.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import wanda.springframework.petclinic.model.BaseEntity;
import wanda.springframework.petclinic.services.CrudService;

public abstract class AbstractMapService<T extends BaseEntity> implements CrudService<T> {
  protected final Map<Long, T> map = new HashMap<>();

  @Override
  public Set<T> findAll() {
    return new HashSet<>(map.values());
  }

  @Override
  public T findById(Long id) {
    return map.get(id);
  }

  @Override
  public T save(T object) {
    if (object != null) {
      if (object.getId() == null) {
        object.setId(getNextId());
      }
      map.put(object.getId(), object);
    } else {
      throw new RuntimeException("Object cannot be null!");
    }
    return object;
  }

  @Override
  public void deleteById(Long id) {
    map.remove(id);
  }

  @Override
  public void delete(T object) {
    map.entrySet().removeIf(entry -> entry.getValue().equals(object));
  }

  private Long getNextId() {
    return map.isEmpty() ? 1L : Collections.max(map.keySet()) + 1;
  }
}
