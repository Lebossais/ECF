package TestPeople;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gestion.Specialiste;

class TestSpecialiste {

	@Test
	void testSpecialiste() {
		Adresse adresse = new Adresse (123, "Rue des Tests-Unitaires", 59841, "TestUnitairesLand");
		Personne testPersonne = new Personne("Test","Unitaire", adresse , "06.55.99.88.77", "Test.Unitaire@gmail.com");
		Specialiste Spe_Test = new Specialiste(testPersonne, "Chiurgie");
		assertEquals(testPersonne, Spe_Test.getPersonne());
		assertEquals("Chiurgie", Spe_Test.getSpecialite());
	}

}
