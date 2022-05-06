package wanda.springframework.petclinic.model;

import wanda.springframework.petclinic.services.PetTypeService;

public class PetType extends BaseEntity {
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
