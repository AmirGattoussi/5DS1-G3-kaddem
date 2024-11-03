package tn.esprit.spring.kaddem.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.dto.UniversiteDTO;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.services.IUniversiteService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UniversiteRestControllerTest {

   @InjectMocks
   private UniversiteRestController universiteRestController;

   @Mock
   private IUniversiteService universiteService;

   @BeforeEach
   void setUp() {
      MockitoAnnotations.openMocks(this);
   }

   @Test
   void testGetUniversites() {
      // Arrange
      Universite universite1 = new Universite(1, "University A");
      Universite universite2 = new Universite(2, "University B");
      List<Universite> universitesList = new ArrayList<>(Arrays.asList(universite1, universite2));
      when(universiteService.retrieveAllUniversites()).thenReturn(universitesList);

      // Act
      List<Universite> universites = universiteRestController.getUniversites();

      // Assert
      assertEquals(2, universites.size());
      verify(universiteService, times(1)).retrieveAllUniversites();
   }

   @Test
   void testRetrieveUniversite() {
      // Arrange
      Universite universite = new Universite(1, "University A");
      when(universiteService.retrieveUniversite(1)).thenReturn(universite);

      // Act
      Universite result = universiteRestController.retrieveUniversite(1);

      // Assert
      assertEquals("University A", result.getNomUniv());
      verify(universiteService, times(1)).retrieveUniversite(1);
   }

   @Test
   void testAddUniversite() {
      // Arrange
      UniversiteDTO universiteDTO = new UniversiteDTO(null, "New University", null);
      Universite universite = new Universite();
      universite.setNomUniv("New University");

      when(universiteService.addUniversite(any(Universite.class))).thenReturn(universite);

      // Act
      Universite result = universiteRestController.addUniversite(universiteDTO);

      // Assert
      assertEquals("New University", result.getNomUniv());
      verify(universiteService, times(1)).addUniversite(any(Universite.class));
   }
}
