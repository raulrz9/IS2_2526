import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ConductorTest {

	private static Conductor sut;

	@Test
	public void testConstructor() {
		sut = new Conductor("123123123X", "Pepe", "Martinez", "Fernandez",
				"Avda. de los Castros s/n");
		assertEquals("123123123X", sut.dni());
		assertEquals("123123123X", sut.getDni());
		assertEquals("Pepe", sut.getNombre());
		assertEquals("Martinez", sut.getApellido1());
		assertEquals("Fernandez", sut.apellido2());
		assertEquals("Avda. de los Castros s/n", sut.getDire());

		sut = new Conductor("123123123X", "Pepe", "Martinez", null,
				"Avda. de los Castros s/n");
		assertEquals("123123123X", sut.dni());
		assertEquals("123123123X", sut.getDni());
		assertEquals("Pepe", sut.getNombre());
		assertEquals("Martinez", sut.getApellido1());
		assertNull(sut.apellido2());
		assertEquals("Avda. de los Castros s/n", sut.getDire());

		assertThrows(IllegalArgumentException.class,
				() -> new Conductor(null, "Pepe", "Martinez", "Fernandez",
						"Avda. de los Castros s/n"));
		assertThrows(IllegalArgumentException.class,
				() -> new Conductor("123123123X", null, "Martinez", "Fernandez",
						"Avda. de los Castros s/n"));
		assertThrows(IllegalArgumentException.class,
				() -> new Conductor("123123123X", "Pepe", null, "Fernandez",
						"Avda. de los Castros s/n"));
		assertThrows(IllegalArgumentException.class,
				() -> new Conductor("123123123X", "Pepe", "Martinez", "Fernandez",
						null));
	}

	@Test
	public void testSueldoYAnhadeTransporte() {
		sut = new Conductor("123123123X", "Pepe", "Martinez", "Fernandez",
				"Avda. de los Castros s/n");

		assertEquals(700.0, sut.sueldo());

		Transporte transPersonas1hora1per = new TransportePersonas(1, 1);
		sut.anhadeTransporte(transPersonas1hora1per);
		assertEquals(705.5, sut.sueldo());
		Transporte transPersonas10horas9per = new TransportePersonas(10, 9);
		sut.anhadeTransporte(transPersonas10horas9per);
		assertEquals(760.5, sut.sueldo());
		Transporte transPersonas1hora10per = new TransportePersonas(1, 10);
		sut.anhadeTransporte(transPersonas1hora10per);
		assertEquals(766.5, sut.sueldo());
		Transporte transPersonas10horas20per = new TransportePersonas(10, 20);
		sut.anhadeTransporte(transPersonas10horas20per);
		assertEquals(826.5, sut.sueldo());

		Transporte transMercancias1hora1ton = new TransporteMercancias(1, 1);
		sut.anhadeTransporte(transMercancias1hora1ton);
		assertEquals(833.5, sut.sueldo());
		Transporte transMercancias10horas100ton = new TransporteMercanciasPeligrosas(10, 100);
		sut.anhadeTransporte(transMercancias10horas100ton);
		assertEquals(1133.5, sut.sueldo());
	}

	@Test
	public void noPermiteTransporteNulo() {
		sut = new Conductor("123123123X", "Pepe", "Martinez", "Fernandez",
				"Avda. de los Castros s/n");

		assertThrows(IllegalArgumentException.class, () -> sut.anhadeTransporte(null));
		assertTrue(sut.transportes().isEmpty());
	}
}
