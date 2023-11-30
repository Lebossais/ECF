package TestPeople;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gestion.Medecin;

class TestMedecin {

	@Test
	void testMedecin() {
		Adresse adresse = new Adresse (123, "Rue des Tests-Unitaires", 59841, "TestUnitairesLand");
		Personne testPersonne = new Personne("Test","Unitaire", adresse , "06.55.99.88.77", "Test.Unitaire@gmail.com");
		Medecin Med_Test = new Medecin(testPersonne, 1);
		
		assertEquals(testPersonne, Med_Test.getPersonne());
		assertEquals(1, Med_Test.getAggrement());
	}

}
