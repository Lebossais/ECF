package utilitaire;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestAdresse {

	@Test
	void TestAdresse() {
		Adresse adresse = new Adresse (123, "Rue des Tests-Unitaires", 59841, "TestUnitairesLand");
		assertEquals(123, adresse.getNumero_rue());
		assertEquals("Rue des Tests-Unitaires", adresse.getNom_rue());
		assertEquals(59841, adresse.getCodePostal());
		assertEquals("TestUnitairesLand", adresse.getVille());
	}
}
