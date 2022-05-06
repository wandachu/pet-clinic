package wanda.springframework.petclinic.model;

import wanda.springframework.petclinic.services.SpecialtyService;

public class Specialty extends BaseEntity {
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
