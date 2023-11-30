package testGestion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import enumeration.Categorie_Medicament;
import gestion.Medicament;

class TestMedicament {

	@Test
	void testMedicament() {
		Medicament medicamenttest = new Medicament("TestMedic", Categorie_Medicament.Analgesique, 5 , "27/08/2001");
		assertEquals("TestMedic", medicamenttest.getNom());
		assertEquals(Categorie_Medicament.Analgesique, medicamenttest.getCategorie());
		assertEquals(5, medicamenttest.getPrix());
		assertEquals("27/08/2001", medicamenttest.getDate_Mise_en_Service());
	
	}

}
