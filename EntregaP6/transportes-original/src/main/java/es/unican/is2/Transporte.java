/* Clase que representa un transporte realizado por un conductor */
/*
 * Metricas iniciales:
 * WMC = 5. Se suma la complejidad ciclomatica de cada metodo:
 *   constructor = 2 (if de validacion con condicion compuesta contado como una decision)
 *   horas = 1, categoria = 1, ton = 1, getPersonas = 1
 * WMCn = 1.00 (5 / 5 metodos)
 * CCog = 1. Se suma la complejidad cognitiva: el if del constructor aporta 1.
 * CCogn = 0.20 (1 / 5 metodos)
 * CBO = 2 (CategoriaTransporte, IllegalArgumentException)
 * DIT = 1
 * NOC = 0
 */
public class Transporte {
	
	private double horas;
	private int ton;
	private int personas;
	private CategoriaTransporte cat;
	
	/**
	 * Constructor de la clase Transporte
	 * @param horas Horas que ha durado el transporte
	 * @param cat Categoria del transporte
	 * @param valor En caso de ser un transporte de tipo Personas, 
	 * representa el numero de personas, en caso de ser de tipo Mercancias 
	 * representa las toneladas
	 */ 
	public Transporte(double horas, CategoriaTransporte cat, int valor) throws IllegalArgumentException {
		if (horas <= 0 || valor <= 0 || cat == null) {
			throw new IllegalArgumentException();
		}
		this.horas = horas;
		this.cat = cat;
		if (cat.equals(CategoriaTransporte.Personas)) {
			this.personas = valor;
		} else  {
			this.ton = valor;
		}
	}
	
	public double horas() {
		return horas;
	}

	public CategoriaTransporte categoria() {
		return cat;
	}

	public int ton() {
		return ton;
	}

	public int getPersonas() {
		return personas;
	}
	
}
