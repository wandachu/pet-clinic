package wanda.springframework.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wanda.springframework.petclinic.services.SpecialtyService;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "specialties")
public class Specialty extends BaseEntity {

  @Column(name = "description")
  private String description;
}
