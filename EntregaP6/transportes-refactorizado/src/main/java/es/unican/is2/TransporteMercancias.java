/*
 * Metricas finales:
 * WMC = 4. Constructor = 2; toneladas = 1; extra = 1.
 * WMCn = 1.33 (4 / 3 metodos)
 * CCog = 1. El if de validacion del constructor aporta 1.
 * CCogn = 0.33 (1 / 3 metodos)
 * CBO = 1 (IllegalArgumentException)
 * DIT = 2
 * NOC = 1 (TransporteMercanciasPeligrosas)
 */
public class TransporteMercancias extends Transporte {

	private static final double EXTRA_TONELADA = 2.0;

	private final int toneladas;

	public TransporteMercancias(double horas, int toneladas) {
		super(horas);
		if (toneladas <= 0) {
			throw new IllegalArgumentException();
		}
		this.toneladas = toneladas;
	}

	public int toneladas() {
		return toneladas;
	}

	@Override
	protected double extra() {
		return toneladas * EXTRA_TONELADA;
	}
}
