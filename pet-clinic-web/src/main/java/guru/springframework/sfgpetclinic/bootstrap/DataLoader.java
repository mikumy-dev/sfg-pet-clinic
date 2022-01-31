package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Micheal");
        owner1.setLastName("Weston");
        owner1.setAddress("123 St.");
        owner1.setCity("Miami");
        owner1.setTelephone("123456789");

        Pet pet1 = new Pet();
        pet1.setPetType(savedDogType);
        pet1.setBirthday(LocalDate.now());
        pet1.setName("Costo");
        pet1.setOwner(owner1);
        owner1.getPets().add(pet1);
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Peter");
        owner2.setLastName("Murphy");
        owner2.setAddress("124 St.");
        owner2.setCity("Miami");
        owner2.setTelephone("134679456");

        Pet pet2 = new Pet();
        pet2.setOwner(owner2);
        pet2.setName("Misty");
        pet2.setBirthday(LocalDate.now());
        pet2.setPetType(savedCatType);
        owner2.getPets().add(pet2);
        ownerService.save(owner2);

        System.out.println("Loaded Owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Frankie");
        vet1.setLastName("Austin");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Joel");
        vet2.setLastName("Buckley");
        vetService.save(vet2);

        System.out.println("Loaded Vets...");
    }
}
