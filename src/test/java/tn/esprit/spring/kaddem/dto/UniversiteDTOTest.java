package tn.esprit.spring.kaddem.dto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UniversiteDTOTest {

   @Test
   void testNoArgsConstructor() {
      UniversiteDTO universiteDTO = new UniversiteDTO();
      assertNull(universiteDTO.getIdUniv());
      assertNull(universiteDTO.getNomUniv());
      assertNull(universiteDTO.getDepartementIds());
   }

   @Test
   void testAllArgsConstructor() {
      Integer id = 1;
      String name = "Test University";
      Set<Integer> departmentIds = new HashSet<>(Arrays.asList(1, 2));
      UniversiteDTO universiteDTO = new UniversiteDTO(id, name, departmentIds);

      assertEquals(id, universiteDTO.getIdUniv());
      assertEquals(name, universiteDTO.getNomUniv());
      assertEquals(departmentIds, universiteDTO.getDepartementIds());
   }

   @Test
   void testSettersAndGetters() {
      UniversiteDTO universiteDTO = new UniversiteDTO();
      universiteDTO.setIdUniv(1);
      universiteDTO.setNomUniv("Updated University");

      Set<Integer> departmentIds = new HashSet<>(Arrays.asList(1, 2));
      universiteDTO.setDepartementIds(departmentIds);

      assertEquals(1, universiteDTO.getIdUniv());
      assertEquals("Updated University", universiteDTO.getNomUniv());
      assertEquals(departmentIds, universiteDTO.getDepartementIds());
   }

   @Test
   void testSettersWithNullValues() {
      UniversiteDTO universiteDTO = new UniversiteDTO();
      universiteDTO.setIdUniv(null);
      universiteDTO.setNomUniv(null);
      universiteDTO.setDepartementIds(null);

      assertNull(universiteDTO.getIdUniv());
      assertNull(universiteDTO.getNomUniv());
      assertNull(universiteDTO.getDepartementIds());
   }

   @Test
   void testEqualsAndHashCode() {
      UniversiteDTO universiteDTO1 = new UniversiteDTO(1, "University A", new HashSet<>(Arrays.asList(1, 2)));
      UniversiteDTO universiteDTO2 = new UniversiteDTO(1, "University A", new HashSet<>(Arrays.asList(1, 2)));
      UniversiteDTO universiteDTO3 = new UniversiteDTO(2, "University B", new HashSet<>(Arrays.asList(3, 4)));

      // Verify that equal objects have the same hash code and are considered equal
      assertEquals(universiteDTO1, universiteDTO2);
      assertEquals(universiteDTO1.hashCode(), universiteDTO2.hashCode());

      // Verify that different objects have different hash codes and are not considered equal
      assertNotEquals(universiteDTO1, universiteDTO3);
      assertNotEquals(universiteDTO1.hashCode(), universiteDTO3.hashCode());

      // Additional cases
      assertNotEquals(null, universiteDTO1);  // Test against null
      assertNotEquals(universiteDTO1, new Object());  // Test against a different type
      assertEquals(universiteDTO1, universiteDTO1);  // Test self-equality
   }

   @Test
   void testHashCodeConsistency() {
      UniversiteDTO universiteDTO = new UniversiteDTO(1, "University A", new HashSet<>(Arrays.asList(1, 2)));
      int initialHashCode = universiteDTO.hashCode();

      // Verify hash code consistency
      assertEquals(initialHashCode, universiteDTO.hashCode());
   }

   @Test
   void testToString() {
      UniversiteDTO universiteDTO = new UniversiteDTO(1, "Test University", new HashSet<>(Arrays.asList(1, 2)));

      String toString = universiteDTO.toString();
      assertNotNull(toString);
      assertTrue(toString.contains("idUniv=1"));
      assertTrue(toString.contains("nomUniv=Test University"));
      assertTrue(toString.contains("departementIds=[1, 2]"));

      // Test with null fields to check that null handling is correct
      UniversiteDTO universiteDTOWithNulls = new UniversiteDTO();
      toString = universiteDTOWithNulls.toString();
      assertNotNull(toString);
      assertTrue(toString.contains("idUniv=null"));
      assertTrue(toString.contains("nomUniv=null"));
      assertTrue(toString.contains("departementIds=null"));
   }
}
