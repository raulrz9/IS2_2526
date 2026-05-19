import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Metricas finales:
 * WMC = 7. buscaConductor = 3; anhadeConductor = 3; conductores = 1.
 * WMCn = 2.33 (7 / 3 metodos)
 * CCog = 4. buscaConductor = 3 (for + if anidado);
 * anhadeConductor = 1; conductores = 0.
 * CCogn = 1.33 (4 / 3 metodos)
 * CBO = 4 (ArrayList, Collections, List, Conductor)
 * DIT = 1
 * NOC = 0
 */
public class GestionTransportes {

	private final List<Conductor> conductores = new ArrayList<>();

	public Conductor buscaConductor(String dni) {
		for (Conductor conductor : conductores) {
			if (conductor.dni().equals(dni)) {
				return conductor;
			}
		}
		return null;
	}

	public boolean anhadeConductor(String dni, String nombre, String apellido1,
			String apellido2, String direccion) {
		if (buscaConductor(dni) != null) {
			return false;
		}
		conductores.add(new Conductor(dni, nombre, apellido1, apellido2, direccion));
		return true;
	}

	public List<Conductor> conductores() {
		return Collections.unmodifiableList(conductores);
	}
}
