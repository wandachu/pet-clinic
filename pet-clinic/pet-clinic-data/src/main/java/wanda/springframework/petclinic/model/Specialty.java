package wanda.springframework.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import wanda.springframework.petclinic.services.SpecialtyService;

@Entity
@Table(name = "specialties")
public class Specialty extends BaseEntity {

  @Column(name = "description")
  private String description;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void save(SpecialtyService specialtyService) {

  }
}
