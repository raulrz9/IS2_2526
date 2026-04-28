
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import es.unican.is2.Seguro;
import es.unican.is2.Cobertura;
import es.unican.is2.OperacionNoValida;

public class SeguroTest {

    private LocalDate fechaHoy;

    @BeforeEach
    public void setUp() {
        fechaHoy = LocalDate.now();
    }

    @Test
    public void testCasosValidos() {
        Seguro s;

        s = new Seguro(fechaHoy.minusDays(30), "1111AAA", 80, Cobertura.TODO_RIESGO, true);
        assertEquals(600.0, s.precio());

        s = new Seguro(fechaHoy.minusDays(364), "2222BBB", 130, Cobertura.TERCEROS, false);
        assertEquals(384.0, s.precio());

        s = new Seguro(fechaHoy.minusYears(1).minusDays(85), "3333CCC", 90, Cobertura.TERCEROS_LUNAS, false);
        assertEquals(630.0, s.precio());

        s = new Seguro(fechaHoy.minusYears(2), "8888HHH", 120, Cobertura.TERCEROS_LUNAS, false);
        assertEquals(720.0, s.precio(), 0.001);
    }

    @Test
    public void testCasosNoValidos() {
        // 1. (TERCEROS, -1 CV, fechaActual) -> Excepción OperacionNoValida
        assertThrows(OperacionNoValida.class, () -> {
            new Seguro(fechaHoy, "4444DDD", -1, Cobertura.TERCEROS, false);
        });

        // 2. (null, 100 CV, fechaActual) -> NullPointerException
        assertThrows(NullPointerException.class, () -> {
            new Seguro(fechaHoy, "5555EEE", 100, null, false);
        });

        Seguro sFuturo = new Seguro(fechaHoy.plusDays(1), "6666FFF", 100, Cobertura.TERCEROS, false);
        assertEquals(0.0, sFuturo.precio(), 0.001);

        assertThrows(NullPointerException.class, () -> {
            new Seguro(null, "7777GGG", 100, Cobertura.TODO_RIESGO, false);
        });

        Seguro s = new Seguro(fechaHoy, "0000XXX", 80, Cobertura.OTRO, false);
        assertEquals(0.0, s.precio(), 0.001);
    }
}