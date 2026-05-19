/*
 * Metricas finales:
 * WMC = 6. Constructor = 2; personas = 1; extra = 3 (ternario/decision).
 * WMCn = 2.00 (6 / 3 metodos)
 * CCog = 2. Constructor = 1; ternario de extra = 1.
 * CCogn = 0.67 (2 / 3 metodos)
 * CBO = 1 (IllegalArgumentException)
 * DIT = 2
 * NOC = 0
 */
public class TransportePersonas extends Transporte {

	private static final int MIN_PERSONAS_COLECTIVO = 10;
	private static final double EXTRA_HORA_NO_COLECTIVO = 0.5;
	private static final double EXTRA_HORA_COLECTIVO = 1.0;

	private final int personas;

	public TransportePersonas(double horas, int personas) {
		super(horas);
		if (personas <= 0) {
			throw new IllegalArgumentException();
		}
		this.personas = personas;
	}

	public int personas() {
		return personas;
	}

	@Override
	protected double extra() {
		return horas() * (personas < MIN_PERSONAS_COLECTIVO
				? EXTRA_HORA_NO_COLECTIVO
				: EXTRA_HORA_COLECTIVO);
	}
}
