package wanda.springframework.petclinic.model;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "owners")
public class Owner extends Person {

  @Column(name = "address")
  private String address;

  @Column(name = "city")
  private String city;

  @Column(name = "telephone")
  private String telephone;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
  private Set<Pet> pets = new HashSet<>();

  @Builder
  public Owner(Long id, String firstName, String lastName, String address, String city,
      String telephone, Set<Pet> pets) {
    super(id, firstName, lastName);
    this.address = address;
    this.city = city;
    this.telephone = telephone;

    if (pets != null) {
      this.pets = pets;
    }
  }

  public Owner(String firstName, String lastName, String address, String city, String telephone,
      Set<Pet> pets) {
    super(firstName, lastName);
    this.address = address;
    this.city = city;
    this.telephone = telephone;
    this.pets = pets;
  }

  public void addPet(Pet pet) {
    if (pet != null) {
      this.getPets().add(pet);
      pet.setOwner(this);
    }
  }

  public Pet getPet(String name) { // not ignoring new
    return getPet(name, false);
  }

  public Pet getPet(String name, boolean ignoreNew) {
    for (Pet pet: pets) {
      if (!ignoreNew || !pet.isNew()) { // include new. no special handling for newPet
        if (name.equalsIgnoreCase(pet.getName())) {
          return pet;
        }
      }
    }
    return null;
  }
}
