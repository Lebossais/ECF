package testGestion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import enumeration.Categorie_Medicament;
import gestion.AchatMedicament;
import gestion.Medicament;

class TestAchatMedicament {

	@Test
	void testAchatMedicament() {
		Medicament medicamenttest = new Medicament("TestMedic", Categorie_Medicament.Analgesique, 5 , "27/08/2001");
		AchatMedicament AchatMedicamentTest = new AchatMedicament(medicamenttest, 2);
		assertEquals(medicamenttest, AchatMedicamentTest.getMedicament());
		assertEquals(2, AchatMedicamentTest.getQuantite());

	}

}
