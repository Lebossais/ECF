package TestPeople;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gestion.Mutuelle;

class TestMutuelle {

	@Test
	void testMutuelle() {
		Adresse adresse = new Adresse (123, "Rue des Tests-Unitaires", 59841, "TestUnitairesLand");
		Personne test = new Personne("Test","Unitaire", adresse , "06.55.99.88.77", "Test.Unitaire@gmail.com");
		Mutuelle Mutuelle_Test = new Mutuelle(test, 59, 85);
		
		assertEquals(test, Mutuelle_Test.getPersonne());
		assertEquals(59, Mutuelle_Test.getDepartement());
		assertEquals(85, Mutuelle_Test.getPrise_En_Charge());
		
	}

}
