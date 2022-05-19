package wanda.springframework.petclinic.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pets")
public class Pet extends BaseEntity {

  @Column(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "type_id")
  private PetType petType;

  @ManyToOne
  @JoinColumn(name = "owner_id")
  private Owner owner;

  @Column(name = "birthday")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthDate;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
  private Set<Visit> visits = new HashSet<>();

  @Builder
  public Pet(Long id, String name, PetType petType, Owner owner, LocalDate birthDate,
      Set<Visit> visits) {
    super(id);
    this.name = name;
    this.petType = petType;
    this.owner = owner;
    this.birthDate = birthDate;

    if (visits != null) {
      this.visits = visits;
    }
  }
}
