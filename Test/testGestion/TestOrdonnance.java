package testGestion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import gestion.Medecin;
import gestion.Ordonnance;
import gestion.Specialiste;

class TestOrdonnance {

	@Test
	void testOrdonnance() {
		Adresse adresse = new Adresse (123, "Rue des Tests-Unitaires", 59841, "TestUnitairesLand");
		Personne testPersonne = new Personne("Test","Unitaire", adresse , "06.55.99.88.77", "Test.Unitaire@gmail.com");
		Medecin Med_Test = new Medecin(testPersonne, 1);
		Specialiste Spe_Test = new Specialiste(testPersonne, "Chiurgie");
		Ordonnance TestOrdonnance = new Ordonnance("111", Med_Test , Spe_Test ,"30/08/2023");
		assertEquals("111", TestOrdonnance.getNum_Ordonnance());
		assertEquals(Med_Test, TestOrdonnance.getMedecin());
		assertEquals(Spe_Test, TestOrdonnance.getSpecialiste());
		assertEquals("30/08/2023", TestOrdonnance.getDate_Ordonnance());
		
		
	}

}
