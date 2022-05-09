package wanda.springframework.petclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import wanda.springframework.petclinic.model.Owner;
import wanda.springframework.petclinic.repositories.OwnerRepository;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

  @Mock
  private OwnerRepository ownerRepository;

  @InjectMocks
  private OwnerSDJpaService ownerSDJpaService;
  private static final String LAST_NAME = "Smith";

  private Owner returnOwner;

  @BeforeEach
  void setUp() {
    returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
  }

  @Test
  void save() {
    Owner ownerToSave = Owner.builder().id(1L).build();
    when(ownerRepository.save(any())).thenReturn(returnOwner);
    Owner savedOwner = ownerSDJpaService.save(ownerToSave);
    assertEquals(1L, savedOwner.getId());
    verify(ownerRepository).save(any());
  }

  @Test
  void findById() {
    when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
    Owner owner = ownerSDJpaService.findById(1L);
    assertNotNull(owner);
    assertEquals("Smith", owner.getLastName());
  }

  @Test
  void findByIdNotFound() {
    when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
    assertNull(ownerSDJpaService.findById(2L));
  }

  @Test
  void findAll() {
    Set<Owner> returnOwnerSet = new HashSet<>();
    returnOwnerSet.add(Owner.builder().id(1L).build());
    returnOwnerSet.add(Owner.builder().id(2L).build());
    when(ownerRepository.findAll()).thenReturn(new LinkedList<>(returnOwnerSet));
    Set<Owner> owners = ownerSDJpaService.findAll();
    assertNotNull(owners);
    assertEquals(2, owners.size());
  }

  @Test
  void deleteById() {
    ownerSDJpaService.deleteById(1L);
    verify(ownerRepository).deleteById(any());
  }

  @Test
  void delete() {
    ownerSDJpaService.delete(returnOwner);
    verify(ownerRepository).delete(any());
  }

  @Test
  void findByLastName() {
    when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);

    assertEquals(LAST_NAME, ownerSDJpaService.findByLastName(LAST_NAME).getLastName());
    verify(ownerRepository).findByLastName(any());
  }
}