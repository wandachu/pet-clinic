package wanda.springframework.petclinic.services.map;

import java.util.Set;
import org.springframework.stereotype.Service;
import wanda.springframework.petclinic.model.Vet;
import wanda.springframework.petclinic.services.SpecialtyService;
import wanda.springframework.petclinic.services.VetService;

@Service
public class VetServiceMap extends AbstractMapService<Vet> implements VetService {
  private SpecialtyService specialtyService;

  public VetServiceMap(SpecialtyService specialtyService) {
    this.specialtyService = specialtyService;
  }

  @Override
  public Set<Vet> findAll() {
    return super.findAll();
  }

  @Override
  public void deleteById(Long id) {
    super.deleteById(id);
  }

  @Override
  public void delete(Vet object) {
    super.delete(object);
  }

  @Override
  public Vet save(Vet object) {
    if (object == null) return null;
    object.getSpecialities().forEach(specialtyService::save); // okay to now have specialties. Otherwise, we can throw exceptions
    return super.save(object);
  }

  @Override
  public Vet findById(Long id) {
    return super.findById(id);
  }
}
