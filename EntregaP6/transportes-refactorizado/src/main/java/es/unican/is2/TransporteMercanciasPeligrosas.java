/*
 * Metricas finales:
 * WMC = 2. Constructor = 1; extra = 1.
 * WMCn = 1.00 (2 / 2 metodos)
 * CCog = 0; CCogn = 0.00
 * CBO = 0
 * DIT = 3
 * NOC = 0
 */
public class TransporteMercanciasPeligrosas extends TransporteMercancias {

	private static final double EXTRA_PELIGROSIDAD = 50.0;

	public TransporteMercanciasPeligrosas(double horas, int toneladas) {
		super(horas, toneladas);
	}

	@Override
	protected double extra() {
		return super.extra() + EXTRA_PELIGROSIDAD;
	}
}
