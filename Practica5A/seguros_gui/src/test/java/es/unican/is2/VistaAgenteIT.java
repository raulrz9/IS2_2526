package es.unican.is2;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.timing.Pause;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.concurrent.TimeUnit;


public class VistaAgenteIT {

    private FrameFixture frame;

    @BeforeEach
    public void setUp() {
        IClientesDAO daoClientes = new ClientesDAO();
        ISegurosDAO daoSeguros = new SegurosDAO();
        GestionSeguros gestion = new GestionSeguros(daoClientes, daoSeguros);

        VistaAgente vista = GuiActionRunner.execute(() -> new VistaAgente(gestion, gestion, gestion));
        frame = new FrameFixture(vista);
        frame.show();
    }

    @AfterEach
    public void tearDown() {
        if (frame != null) {
            frame.cleanUp();
        }
    }

    @Test
    public void testClienteExistenteSinSeguros() {
        frame.textBox("txtDNICliente").setText("33333333A");
        frame.button("btnBuscar").click();
        Pause.pause(300, TimeUnit.MILLISECONDS);

        frame.textBox("txtNombreCliente").requireText("Luis");
        frame.list("listSeguros").requireItemCount(0);
    }

    @Test
    public void testClienteExistenteConUnSeguro() {
        frame.textBox("txtDNICliente").setText("22222222A");
        frame.button("btnBuscar").click();
        Pause.pause(300, TimeUnit.MILLISECONDS);

        frame.textBox("txtNombreCliente").requireText("Ana");
        frame.list("listSeguros").requireItemCount(1);
        assertArrayEquals(
                new String[] { "2222AAA TERCEROS_LUNAS" },
                frame.list("listSeguros").contents());
    }

    @Test
    public void testClienteExistenteConVariosSeguros() {
        frame.textBox("txtDNICliente").setText("11111111A");
        frame.button("btnBuscar").click();
        Pause.pause(300, TimeUnit.MILLISECONDS);

        frame.textBox("txtNombreCliente").requireText("Juan");
        frame.list("listSeguros").requireItemCount(3);
    }

    @Test
    public void testClienteNoExistente() {
        frame.textBox("txtDNICliente").setText("00000000Z");
        frame.button("btnBuscar").click();
        Pause.pause(300, TimeUnit.MILLISECONDS);

        frame.textBox("txtNombreCliente").requireText("Error en BBDD");
        frame.textBox("txtTotalCliente").requireText("");
        frame.list("listSeguros").requireItemCount(0);
    }
}