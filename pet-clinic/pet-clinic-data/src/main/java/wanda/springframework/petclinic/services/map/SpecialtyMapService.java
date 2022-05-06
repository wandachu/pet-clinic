package wanda.springframework.petclinic.services.map;

import java.util.Set;
import org.springframework.stereotype.Service;
import wanda.springframework.petclinic.model.Specialty;
import wanda.springframework.petclinic.services.SpecialtyService;

@Service
public class SpecialtyMapService extends AbstractMapService<Specialty> implements
    SpecialtyService {

  @Override
  public Set<Specialty> findAll() {
    return super.findAll();
  }

  @Override
  public void deleteById(Long id) {
    super.deleteById(id);
  }

  @Override
  public void delete(Specialty object) {
    super.delete(object);
  }

  @Override
  public Specialty save(Specialty object) {
    return super.save(object);
  }

  @Override
  public Specialty findById(Long id) {
    return super.findById(id);
  }
}
