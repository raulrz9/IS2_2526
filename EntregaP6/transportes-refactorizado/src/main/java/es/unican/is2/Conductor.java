import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase que representa a un conductor, con sus datos personales
 * y los transportes que ha realizado.
 */
/*
 * Metricas finales:
 * WMC = 15. Constructor = 2; getters = 6; sueldo = 3
 * (for + llamada polimorfica); anhadeTransporte = 2; transportes = 1.
 * WMCn = 1.50 (15 / 10 metodos)
 * CCog = 4. Constructor = 1; sueldo = 1; anhadeTransporte = 1;
 * resto de metodos = 0.
 * CCogn = 0.40 (4 / 10 metodos)
 * CBO = 5 (ArrayList, Collections, List, Transporte, String)
 * DIT = 1
 * NOC = 0
 */
public class Conductor {

	private static final double SUELDO_BASE = 700.0;

	private final List<Transporte> transportes = new ArrayList<>();
	private final String dni;
	private final String nombre;
	private final String apellido1;
	private final String apellido2;
	private final String direccion;

	public Conductor(String dni, String nombre, String apellido1,
			String apellido2, String direccion) {
		if (dni == null || nombre == null || apellido1 == null || direccion == null) {
			throw new IllegalArgumentException();
		}
		this.dni = dni;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.direccion = direccion;
	}

	public String dni() {
		return dni;
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public String apellido2() {
		return apellido2;
	}

	public String getDire() {
		return direccion;
	}

	public double sueldo() {
		double sueldoTransportes = 0.0;
		for (Transporte transporte : transportes) {
			sueldoTransportes += transporte.sueldo();
		}
		return SUELDO_BASE + sueldoTransportes;
	}

	public void anhadeTransporte(Transporte transporte) {
		if (transporte == null) {
			throw new IllegalArgumentException();
		}
		transportes.add(transporte);
	}

	public List<Transporte> transportes() {
		return Collections.unmodifiableList(transportes);
	}
}
