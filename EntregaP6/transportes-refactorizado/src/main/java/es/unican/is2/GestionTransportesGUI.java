import java.util.LinkedList;
import java.util.List;
import fundamentos.*;

/**
 * Gestion de una empresa de transportes.
 */
/*
 * Metricas finales:
 * WMC = 25. main = 18; creaTransporte = 4; mejoresConductores = 2;
 * mensaje = 1. La complejidad del pago ya no esta en la GUI ni en Conductor.
 * WMCn = 6.25 (25 / 4 metodos)
 * CCog = 27. Disminuye al extraer la creacion de transportes y el calculo
 * de mejores conductores, aunque el menu sigue siendo imperativo.
 * CCogn = 6.75 (27 / 4 metodos)
 * CBO = 12 (LinkedList, List, Menu, Lectura, Mensaje, String,
 * GestionTransportes, Conductor, Transporte, TransportePersonas,
 * TransporteMercancias, TransporteMercanciasPeligrosas)
 * DIT = 1
 * NOC = 0
 */
public class GestionTransportesGUI {

	public static void main(String[] args) {
		final int ANHADE_CONDUCTOR = 0, ANHADE_TRANSPORTE = 1,
				SUELDO_CONDUCTOR = 2, MEJOR_CONDUCTOR = 3;

		String dni;
		Lectura lect;
		Conductor conductor;

		GestionTransportes gt = new GestionTransportes();
		Menu menu = new Menu("Transportes");
		menu.insertaOpcion("Anhade conductor", ANHADE_CONDUCTOR);
		menu.insertaOpcion("Anhade transporte", ANHADE_TRANSPORTE);
		menu.insertaOpcion("Sueldo conductor", SUELDO_CONDUCTOR);
		menu.insertaOpcion("Mejor conductor", MEJOR_CONDUCTOR);

		while (true) {
			int opcion = menu.leeOpcion();

			switch (opcion) {
			case ANHADE_CONDUCTOR:
				lect = new Lectura("Datos Conductor");
				lect.creaEntrada("DNI", "");
				lect.creaEntrada("Nombre", "");
				lect.creaEntrada("Apellido1", "");
				lect.creaEntrada("Apellido2", "");
				lect.creaEntrada("Direccion", "");
				lect.esperaYCierra();
				dni = lect.leeString("DNI");
				String nombre = lect.leeString("Nombre");
				String apellido1 = lect.leeString("Apellido1");
				String apellido2 = lect.leeString("Apellido2");
				String direccion = lect.leeString("Direccion");
				if (!gt.anhadeConductor(dni, nombre, apellido1, apellido2, direccion)) {
					mensaje("ERROR", "Ya existe un conductor con DNI " + dni);
				}
				break;

			case ANHADE_TRANSPORTE:
				lect = new Lectura("Nuevo transporte");
				lect.creaEntrada("DNI", "");
				lect.creaEntrada("Tipo Transporte: P | M | MP", "");
				lect.creaEntrada("Horas", 0);
				lect.creaEntrada("Personas", 0);
				lect.creaEntrada("Toneladas", 0);
				lect.esperaYCierra();
				dni = lect.leeString("DNI");
				conductor = gt.buscaConductor(dni);
				if (conductor != null) {
					Transporte transporte = creaTransporte(
							lect.leeString("Tipo Transporte: P | M | MP"),
							lect.leeInt("Horas"),
							lect.leeInt("Personas"),
							lect.leeInt("Toneladas"));
					conductor.anhadeTransporte(transporte);
				} else {
					mensaje("ERROR", "No existe un conductor con DNI " + dni);
				}
				break;

			case SUELDO_CONDUCTOR:
				lect = new Lectura("Sueldo conductor");
				lect.creaEntrada("DNI", "");
				lect.esperaYCierra();
				dni = lect.leeString("DNI");
				conductor = gt.buscaConductor(dni);
				if (conductor != null) {
					mensaje("Sueldo", "El sueldo del conductor es: " + conductor.sueldo());
				} else {
					mensaje("ERROR", "No existe un conductor con DNI " + dni);
				}
				break;

			case MEJOR_CONDUCTOR:
				List<Conductor> resultado = mejoresConductores(gt.conductores());
				String msj = "";
				if (resultado.isEmpty()) {
					msj = "No hay conductores";
				} else {
					for (Conductor mejor : resultado) {
						msj += mejor.getNombre() + " " + mejor.getApellido1() + "\n";
					}
				}
				mensaje("MEJOR CONDUCTOR", msj);
				break;
			}
		}
	}

	private static Transporte creaTransporte(String tipo, int horas, int personas, int toneladas) {
		switch (tipo) {
		case "P":
			return new TransportePersonas(horas, personas);
		case "M":
			return new TransporteMercancias(horas, toneladas);
		case "MP":
			return new TransporteMercanciasPeligrosas(horas, toneladas);
		default:
			throw new IllegalArgumentException();
		}
	}

	private static List<Conductor> mejoresConductores(List<Conductor> conductores) {
		List<Conductor> resultado = new LinkedList<>();
		double maxSueldo = 0.0;
		for (Conductor conductor : conductores) {
			double sueldo = conductor.sueldo();
			if (sueldo > maxSueldo) {
				maxSueldo = sueldo;
				resultado.clear();
				resultado.add(conductor);
			} else if (sueldo == maxSueldo) {
				resultado.add(conductor);
			}
		}
		return resultado;
	}

	private static void mensaje(String titulo, String txt) {
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);
	}
}
