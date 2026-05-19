import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GestionTransportesTest {

	@Test
	public void anhadeYBuscaConductores() {
		GestionTransportes sut = new GestionTransportes();

		assertNull(sut.buscaConductor("123123123X"));
		assertTrue(sut.anhadeConductor("123123123X", "Pepe", "Martinez",
				"Fernandez", "Avda. de los Castros s/n"));
		assertFalse(sut.anhadeConductor("123123123X", "Ana", "Lopez",
				null, "Calle Alta"));

		Conductor conductor = sut.buscaConductor("123123123X");
		assertSame(conductor, sut.conductores().get(0));
		assertEquals(1, sut.conductores().size());
	}
}
