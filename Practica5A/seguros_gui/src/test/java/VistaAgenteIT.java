import es.unican.is2.ClientesDAO;
import es.unican.is2.GestionSeguros;
import es.unican.is2.IClientesDAO;
import es.unican.is2.ISegurosDAO;
import es.unican.is2.SegurosDAO;
import es.unican.is2.VistaAgente;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.timing.Pause;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class VistaAgenteIT {

    private static FrameFixture frame;

    @BeforeAll
    static void setUpAll() {
        IClientesDAO daoClientes = new ClientesDAO();
        ISegurosDAO daoSeguros = new SegurosDAO();
        GestionSeguros gestion = new GestionSeguros(daoClientes, daoSeguros);

        VistaAgente vista = GuiActionRunner.execute(() -> new VistaAgente(gestion, gestion, gestion));
        frame = new FrameFixture(vista);
        frame.show();
    }

    @AfterAll
    static void tearDownAll() {
        if (frame != null) {
            frame.cleanUp();
        }
    }

    private void buscar(String dni) {
        frame.focus();
        frame.textBox("txtDNICliente").setText(dni);

        GuiActionRunner.execute(() -> {
            frame.button("btnBuscar").target().doClick();
            return null;
        });

        Pause.pause(1200, TimeUnit.MILLISECONDS);
    }

    @Test
    public void testClienteJuan() {
        buscar("11111111A");
        assertEquals("Juan", frame.textBox("txtNombreCliente").text());
        assertEquals(3, frame.list("listSeguros").contents().length);
    }

    @Test
    public void testClienteLuis() {
        buscar("33333333A");
        assertEquals("Luis", frame.textBox("txtNombreCliente").text());
        assertEquals(0, frame.list("listSeguros").contents().length);
    }

    @Test
    public void testClienteAna() {
        buscar("22222222A");
        assertEquals("Ana", frame.textBox("txtNombreCliente").text());
        assertArrayEquals(
                new String[] { "2222AAA TERCEROS_LUNAS" },
                frame.list("listSeguros").contents());
    }

    @Test
    public void testClienteNoExistente() {
        buscar("00000000Z");
        assertEquals("Cliente no existe", frame.textBox("txtNombreCliente").text());
        assertEquals("", frame.textBox("txtTotalCliente").text());
        assertEquals(0, frame.list("listSeguros").contents().length);
    }
}