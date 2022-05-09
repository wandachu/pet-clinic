package wanda.springframework.petclinic.services.map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wanda.springframework.petclinic.model.Owner;

class OwnerMapServiceTest {
  private OwnerMapService ownerMapService;
  private final Long ownerId = 1L;
  private final String lastName = "Smith";

  @BeforeEach
  void setUp() {
    ownerMapService = new OwnerMapService(new PetMapService(new PetTypeMapService()));
    Owner owner = Owner.builder().id(ownerId).lastName(lastName).build();
    ownerMapService.save(owner);
  }

  @Test
  void findAll() {
    Set<Owner> ownerSet = ownerMapService.findAll();
    assertEquals(1, ownerSet.size());
  }

  @Test
  void deleteById() {
    ownerMapService.deleteById(ownerId);
    assertEquals(0, ownerMapService.findAll().size());
  }

  @Test
  void delete() {
    ownerMapService.delete(ownerMapService.findById(ownerId));
    assertEquals(0, ownerMapService.findAll().size());
  }

  @Test
  void saveExistingId() {
    long id = 2L;
    Owner owner2 = Owner.builder().id(id).build();
    Owner savedOwner = ownerMapService.save(owner2);
    assertEquals(id, savedOwner.getId());
  }

  @Test
  void saveNoId() {
    long id = 2L;
    Owner savedOwner = ownerMapService.save(Owner.builder().build());
    assertNotNull(savedOwner);
    assertNotNull(savedOwner.getId());
    assertEquals(id, savedOwner.getId());
  }

  @Test
  void findById() {
    Owner owner = ownerMapService.findById(ownerId);
    assertEquals(ownerId, owner.getId());
  }

  @Test
  void findByLastName() {
    assertEquals(ownerId, ownerMapService.findByLastName("Smith").getId());
  }

  @Test
  void findByLastNameNotFound() {
    assertNull(ownerMapService.findByLastName("Adam"));
  }
}