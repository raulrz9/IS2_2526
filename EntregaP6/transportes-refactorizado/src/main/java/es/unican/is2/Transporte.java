/* Clase base de los transportes realizados por un conductor. */
/*
 * Metricas finales:
 * WMC = 5. Constructor = 2; horas = 1; sueldo = 1; extra = 1.
 * WMCn = 1.25 (5 / 4 metodos)
 * CCog = 1. El if de validacion del constructor aporta 1.
 * CCogn = 0.25 (1 / 4 metodos)
 * CBO = 1 (IllegalArgumentException)
 * DIT = 1
 * NOC = 2 (TransportePersonas, TransporteMercancias)
 */
public abstract class Transporte {

	private static final double EXTRA_BASE_HORA = 5.0;

	private final double horas;

	protected Transporte(double horas) {
		if (horas <= 0) {
			throw new IllegalArgumentException();
		}
		this.horas = horas;
	}

	public double horas() {
		return horas;
	}

	public double sueldo() {
		return horas * EXTRA_BASE_HORA + extra();
	}

	protected abstract double extra();
}
