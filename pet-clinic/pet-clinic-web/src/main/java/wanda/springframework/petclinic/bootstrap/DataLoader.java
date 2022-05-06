package wanda.springframework.petclinic.bootstrap;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import wanda.springframework.petclinic.model.Owner;
import wanda.springframework.petclinic.model.Pet;
import wanda.springframework.petclinic.model.PetType;
import wanda.springframework.petclinic.model.Specialty;
import wanda.springframework.petclinic.model.Vet;
import wanda.springframework.petclinic.services.OwnerService;
import wanda.springframework.petclinic.services.PetTypeService;
import wanda.springframework.petclinic.services.SpecialtyService;
import wanda.springframework.petclinic.services.VetService;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;
  private final SpecialtyService specialtyService;

  @Autowired
  public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService,
      SpecialtyService specialtyService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
    this.specialtyService = specialtyService;
  }

  @Override
  public void run(String... args) throws Exception {
    int count = petTypeService.findAll().size();
    if (count == 0) {
      loadData();
    }
  }

  private void loadData() {
    PetType dog = new PetType();
    dog.setName("Dog");
    petTypeService.save(dog);

    PetType cat = new PetType();
    cat.setName("Cat");
    petTypeService.save(cat);

    System.out.println("Loaded PetTypes.....");

    Owner owner1 = new Owner();
    owner1.setFirstName("Michael");
    owner1.setLastName("Weston");
    owner1.setAddress("123 Main Street");
    owner1.setCity("Seattle");
    owner1.setTelephone("1234567890");

    Pet mikesPet = new Pet();
    mikesPet.setName("Rosco");
    mikesPet.setPetType(dog);
    mikesPet.setOwner(owner1);
    mikesPet.setBirthday(LocalDate.of(2019, 2, 3));
    owner1.getPets().add(mikesPet);

    ownerService.save(owner1);

    Owner owner2 = new Owner();
    owner2.setFirstName("Fiona");
    owner2.setLastName("Glenanne");
    owner2.setAddress("123 Main Street");
    owner2.setCity("Seattle");
    owner2.setTelephone("2345678901");

    Pet fionasPet = new Pet();
    fionasPet.setName("Just");
    fionasPet.setOwner(owner2);
    fionasPet.setBirthday(LocalDate.of(2020, 4, 5));
    fionasPet.setPetType(cat);
    owner2.getPets().add(fionasPet);

    ownerService.save(owner2);

    // Set specialties
    Specialty radiology = new Specialty();
    radiology.setDescription("Radiology");
    Specialty surgery = new Specialty();
    surgery.setDescription("Surgery");
    Specialty dentistry = new Specialty();
    dentistry.setDescription("Dentistry");

    specialtyService.save(radiology);
    specialtyService.save(surgery);
    specialtyService.save(dentistry);

    System.out.println("Loaded Owners.....");

    Vet vet1 = new Vet();
    vet1.setFirstName("Sam");
    vet1.setLastName("Axe");
    vet1.getSpecialities().add(radiology);

    vetService.save(vet1);

    Vet vet2 = new Vet();
    vet2.setFirstName("Jason");
    vet2.setLastName("Lee");
    vet2.getSpecialities().add(surgery);

    vetService.save(vet2);

    System.out.println("Loaded Vets.....");
  }
}
