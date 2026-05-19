import java.util.ArrayList;
import java.util.List;

/*
 * Metricas iniciales:
 * WMC = 7. buscaConductor = 3 (for + if + base);
 * anhadeConductor = 3 (if + base); conductores = 1.
 * WMCn = 2.33 (7 / 3 metodos)
 * CCog = 4. buscaConductor = 3 (for + if anidado);
 * anhadeConductor = 1; conductores = 0.
 * CCogn = 1.33 (4 / 3 metodos)
 * CBO = 4 (ArrayList, List, Conductor, String)
 * DIT = 1
 * NOC = 0
 */
public class gestionTransportes {

	private ArrayList<Conductor> cs = new ArrayList<Conductor>();
	
	public Conductor buscaConductor(String DNI) {		
		for(Conductor c: cs) 
			if (c.dni().equals(DNI))
				return c;
		
		return null;
	}
	
	public boolean anhadeConductor(String dni, String nombre, String apellido1, String apellido2, String direccion) {
		if (buscaConductor(dni) != null)
			return false;
		cs.add(new Conductor(dni, nombre, apellido1, apellido2,direccion));
		return true;
	}

	public List<Conductor> conductores() {
		return cs;
	}
	
}
