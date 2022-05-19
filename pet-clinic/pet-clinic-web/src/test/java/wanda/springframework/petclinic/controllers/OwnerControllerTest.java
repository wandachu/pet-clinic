package wanda.springframework.petclinic.controllers;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import wanda.springframework.petclinic.model.Owner;
import wanda.springframework.petclinic.services.OwnerService;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

  @Mock
  OwnerService ownerService;

  @InjectMocks
  OwnerController ownerController;

  Set<Owner> ownerSet;

  MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    ownerController = new OwnerController(ownerService);
    ownerSet = new HashSet<>();
    ownerSet.add(Owner.builder().id(1L).build());
    ownerSet.add(Owner.builder().id(2L).build());

    mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
  }

  @Test
  void findOwners() throws Exception {
    mockMvc.perform(get("/owners/find"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/findOwners"))
        .andExpect(model().attributeExists("owner"));
    verifyNoInteractions(ownerService);
  }

  @Test
  void processFindFormReturnMany() throws Exception {
    when(ownerService.findAllByLastNameLike(anyString())).thenReturn(new ArrayList<>(ownerSet));
    mockMvc.perform(get("/owners"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/ownersList"))
        .andExpect(model().attribute("selections", hasSize(2)));
  }

  @Test
  void processFindFormReturnOne() throws Exception {
    when(ownerService.findAllByLastNameLike(anyString())).thenReturn(Arrays.asList(Owner.builder().id(1L).build()));
    mockMvc.perform(get("/owners"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"));
  }

  @Test
  void displayOwner() throws Exception {
    when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());
    mockMvc.perform(get("/owners/123"))
        .andExpect(status().isOk()).andExpect(view().name("owners/ownerDetails"))
        .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
  }

  @Test
  void initCreationForm() throws Exception {
    mockMvc.perform(get("/owners/new"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/createOrUpdateOwnerForm"))
        .andExpect(model().attributeExists("owner"));
    verifyNoInteractions(ownerService); // get request should not call save
  }

  @Test
  void processCreationForm() throws Exception {
    when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).build());
    mockMvc.perform(post("/owners/new"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"))
        .andExpect(model().attributeExists("owner"));
    verify(ownerService).save(ArgumentMatchers.any()); // post request should call save
  }

  @Test
  void initUpdateOwnerForm() throws Exception {
    when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());
    mockMvc.perform(get("/owners/1/edit"))
        .andExpect(status().isOk())
        .andExpect(view().name("owners/createOrUpdateOwnerForm"))
        .andExpect(model().attributeExists("owner"));
    verifyNoMoreInteractions(ownerService); // only one call in mockito whenThenReturn but no more
  }

  @Test
  void processUpdateOwnerForm() throws Exception {
    when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1L).build());
    mockMvc.perform(post("/owners/1/edit"))
        .andExpect(status().is3xxRedirection())
        .andExpect(view().name("redirect:/owners/1"))
        .andExpect(model().attributeExists("owner"));
    verify(ownerService).save(ArgumentMatchers.any()); // post request should call save
  }
}