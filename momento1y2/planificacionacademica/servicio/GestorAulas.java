package momento1y2.planificacionacademica.servicio;
import java.util.Map;
import java.util.TreeMap;
import momento1y2.planificacionacademica.modelo.Aula;

/**
 * Administra las aulas con un TreeMap: mantiene las llaves (nombres)
 * siempre ordenadas, sin distinguir mayúsculas de minúsculas.
 */
public class GestorAulas {

    private final Map<String, Aula> aulas = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public void registrar(Aula aula) {
        if (aula == null) {
            throw new IllegalArgumentException("El aula no puede ser nula.");
        }
        if (aulas.containsKey(aula.getNombre())) {
            throw new IllegalArgumentException("Ya existe el aula " + aula.getNombre() + ".");
        }
        aulas.put(aula.getNombre(), aula);
    }

    /** Busca un aula por nombre. */
    public Aula buscar(String nombre) {
        Aula aula = (nombre == null) ? null : aulas.get(nombre.trim());
        if (aula == null) {
            throw new IllegalArgumentException("No existe el aula: " + nombre);
        }
        return aula;
    }

    public int cantidad() {
        return aulas.size();
    }

    /** Lista las aulas en orden por nombre (lo hace solo el TreeMap). */
    public void listar() {
        if (aulas.isEmpty()) {
            System.out.println("No hay aulas registradas.");
            return;
        }
        System.out.println("Aulas registradas (orden por nombre):");
        for (Aula aula : aulas.values()) {
            System.out.println(aula);
        }
    }
}