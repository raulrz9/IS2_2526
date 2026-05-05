package es.unican.is2;

import java.time.LocalDate;

/**
 * Clase que representa un seguro de coche.
 */
public class Seguro {
	
	private long id;

    private String matricula;

	private int potencia;

    private Cobertura cobertura;
    
    private LocalDate fechaInicio;

	private String conductorAdicional;

	private boolean minusvalido;

	public Seguro(LocalDate fechaInicio, String matricula, int potencia, Cobertura cobertura, boolean minusvalido) {

		if (potencia < 0) {
        	throw new OperacionNoValida("La potencia no puede ser negativa");
    	}

		if (cobertura == null || fechaInicio == null) {
        	throw new NullPointerException("Datos obligatorios nulos");
    	}
		
       	this.fechaInicio = fechaInicio;    // Correcto: Guardamos el dato externo en la clase
    	this.matricula = matricula;
    	this.potencia = potencia;
    	this.cobertura = cobertura;
		this.minusvalido = minusvalido;
    }

    /**
	 * Retorna el identificador del seguro
	 */
	public long getId() {
		return id;
	}

	/**
	 *  Asigna el valor del identificador del seguro
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Retorna la matricula del coche 
	 * asociado al seguro
	 */
	public String getMatricula() {
		return matricula;
	}

	/**
	 *  Asigna el valor de la matrícula del seguro
	 */
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	/**
	 * Retorna la fecha de contratacion del seguro
	 */
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Asigna la fecha de inicio del seguro
	 * @param fechaInicio La fecha de inicio del seguro
	 */
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Retorna el tipo de cobertura del seguro
	 */
	public Cobertura getCobertura() {
		return cobertura;
	}

	/**
	 * Asigna el tipo de cobertura del seguro
	 * @param cobertura El tipo de cobertura del seguro
	 */	
	public void setCobertura(Cobertura cobertura) {
		this.cobertura = cobertura;		
}

	/**
     * Retorna la potencia del coche asociado 
     * al seguro. 
     */
    public int getPotencia() {
        return potencia;
    }

	/**
	 *  Asigna el valor del identificador del seguro
	 */
	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	/**
	 * Retorna el conductor adicional del seguro, si lo hay
	 * @return El conductor adicional si lo hay
	 * 		null en caso contrario
	 */
	public String getConductorAdicional() {
		return conductorAdicional;
	}

	/**
	 * Asigna el conductor adicional del seguro
	 * @param conductorAdicional
	 */
	public void setConductorAdicional(String conductorAdicional) {
		this.conductorAdicional = conductorAdicional;
	}
    
    /**
     * Retorna el precio del seguro. 
	 * El precio se calcula a partir de la cobertura, la potencia del coche y el tiempo que lleva contratado el seguro
	 * @return El precio del seguro
	 *         0 si el seguro todavía no está en vigor (no se ha alcanzado su fecha de inicio)
     */
	public double precio() {
		if (LocalDate.now().isBefore(fechaInicio)) {
			return 0;
		}

		double base = 0;
		if (cobertura == Cobertura.TODO_RIESGO) {
			base = 1000;
		} else if (cobertura == Cobertura.TERCEROS_LUNAS) {
			base = 600;
		} else if (cobertura == Cobertura.TERCEROS) {
			base = 400;
		}

		double precio = base;


		if (potencia >= 90 && potencia <= 110) {
			precio = precio + base * 0.05;   // +5%
		} else if (potencia > 110) {
			precio = precio + base * 0.20;   // +20%
		}

		if (fechaInicio.isAfter(LocalDate.now().minusYears(1))) {
        	precio = precio * 0.80; // -20%
   		}

		if (minusvalido == true) {
            precio = precio * 0.75; // Aplica el -25%
        }

		return precio;
	}
		
	
}
