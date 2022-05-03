package wanda.springframework.petclinic.model;

import java.io.Serializable;

public class BaseEntity implements Serializable {
  private Long id; // could be null

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
