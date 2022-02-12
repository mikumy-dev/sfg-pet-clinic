package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;

    Owner owner;
    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1l).build();
    }

    @Test
    void equals() {
        Owner owner1 = new Owner();
        owner1.setId(1l);
        Owner owner2 = new Owner();
        owner2.setId(2l);
        assertEquals(false, owner1.equals(owner2));
    }

    @Test
    void findAll() {

        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1l).build());
        owners.add(Owner.builder().id(2l).build());

        when(ownerRepository.findAll()).thenReturn(owners);
        assertEquals(2, ownerSDJpaService.findAll().size());
    }

    @Test
    void findById() {
        Long id = 1l;
        Owner owner = Owner.builder().id(id).build();
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(owner));
        assertEquals(id, ownerSDJpaService.findById(id).getId());
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(1l).build();
        when(ownerRepository.save(any(Owner.class))).thenReturn(owner);
        Owner savedOwner = ownerSDJpaService.save(ownerToSave);
        assertNotNull(savedOwner);
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(owner);
        verify(ownerRepository).delete(any(Owner.class));
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(1l);
        verify(ownerRepository).deleteById(anyLong());
    }
}