import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class TransporteTest {

	@Test
	public void testTransportePersonas() {
		TransportePersonas sut = new TransportePersonas(1, 1);
		assertEquals(1, sut.horas());
		assertEquals(1, sut.personas());
		assertEquals(5.5, sut.sueldo());

		sut = new TransportePersonas(10, 10);
		assertEquals(60.0, sut.sueldo());

		assertThrows(IllegalArgumentException.class, () -> new TransportePersonas(0, 1));
		assertThrows(IllegalArgumentException.class, () -> new TransportePersonas(10, 0));
	}

	@Test
	public void testTransporteMercancias() {
		TransporteMercancias sut = new TransporteMercancias(1, 1);
		assertEquals(1, sut.horas());
		assertEquals(1, sut.toneladas());
		assertEquals(7.0, sut.sueldo());

		assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(0, 1));
		assertThrows(IllegalArgumentException.class, () -> new TransporteMercancias(10, 0));
	}

	@Test
	public void testTransporteMercanciasPeligrosas() {
		TransporteMercanciasPeligrosas sut = new TransporteMercanciasPeligrosas(10, 100);
		assertEquals(10, sut.horas());
		assertEquals(100, sut.toneladas());
		assertEquals(300.0, sut.sueldo());

		assertThrows(IllegalArgumentException.class,
				() -> new TransporteMercanciasPeligrosas(0, 1));
		assertThrows(IllegalArgumentException.class,
				() -> new TransporteMercanciasPeligrosas(10, 0));
	}
}
