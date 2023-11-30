package utilitaire;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestPersonne {

	@Test
	void testPersonne() {
		Adresse adresse = new Adresse (123, "Rue des Tests-Unitaires", 59841, "TestUnitairesLand");
		Personne test = new Personne("Test","Unitaire", adresse , "06.55.99.88.77", "Test.Unitaire@gmail.com");
		assertEquals("Test", test.getPrenom());
		assertEquals("Unitaire", test.getName());
		assertEquals(adresse, test.getAdresse());
		assertEquals("06.55.99.88.77", test.getTelephone());
		assertEquals("Test.Unitaire@gmail.com", test.getEmail());
		
	}

}
