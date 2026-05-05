
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import es.unican.is2.*;

public class ClienteTest {

    private Cliente cliente;
    private LocalDate fecha;

    @BeforeEach
    public void setUp() {
        fecha = LocalDate.now();
    }

    @Test
    public void testCasosValidos() {
        // CP1: ([], false) -> Esperado: 0
        cliente = new Cliente("11111111A", "Juan", false);
        assertEquals(0.0, cliente.totalSeguros());

        // CP2: ([1], false) -> Un seguro de precio 1 (usamos valores que den 1000 para el ejemplo)
        cliente = new Cliente("22222222B", "Luis", false);
        Seguro s1 = new Seguro(fecha, "1111AAA", 80, Cobertura.TODO_RIESGO, false);
        cliente.getSeguros().add(s1); 
        // 1000 base - 20% antigüedad = 800.0
        assertEquals(800.0, cliente.totalSeguros());

        cliente = new Cliente("22222222C", "Pepe", true);
        Seguro s2 = new Seguro(fecha, "1111AAA", 80, Cobertura.TODO_RIESGO, false);
        cliente.getSeguros().add(s2);
        assertEquals(600.0, cliente.totalSeguros());

        // CP4: ([1, 1], true) -> Dos seguros de precio 800, con minusvalía. 
        // (800 + 800) * 0.75 = 1200.0
        cliente = new Cliente("33333333C", "Ana", true);
        Seguro s3 = new Seguro(fecha, "2222BBB", 80, Cobertura.TODO_RIESGO, false);
        Seguro s4 = new Seguro(fecha, "3333CCC", 80, Cobertura.TODO_RIESGO, false);
        cliente.getSeguros().add(s3);
        cliente.getSeguros().add(s4);
        assertEquals(1200.0, cliente.totalSeguros());
    }

    @Test
    public void testCasosNoValidos() {
        // CP: ([null], true) -> NullPointerException
        cliente = new Cliente("44444444D", "Marta", true);
        cliente.getSeguros().add(null);
        assertThrows(NullPointerException.class, () -> {
            cliente.totalSeguros();
        });

        // CP: ([1, -5], false) -> Potencia negativa lanza OperacionNoValida
        cliente = new Cliente("55555555E", "Pedro", false);
        assertThrows(OperacionNoValida.class, () -> {
            new Seguro(fecha, "4444DDD", -1, Cobertura.TERCEROS, false);
        });
    }
}
