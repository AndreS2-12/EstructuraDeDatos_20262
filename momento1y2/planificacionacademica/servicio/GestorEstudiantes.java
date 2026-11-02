package momento1y2.planificacionacademica.servicio;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import momento1y2.planificacionacademica.excepciones.EstudianteNoEncontradoException;
import momento1y2.planificacionacademica.modelo.Estudiante;

/**
 * Administra los estudiantes con un HashMap que usa el ID como llave,
 * para buscar sin recorrer todos los estudiantes.
 */
public class GestorEstudiantes {

    private final Map<String, Estudiante> indice = new HashMap<>();

    /** Registra un estudiante. También sirve para restaurarlo al deshacer una baja. */
    public void registrar(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }
        if (indice.containsKey(estudiante.getId())) {
            throw new IllegalArgumentException(
                    "Ya existe un estudiante con ID: " + estudiante.getId());
        }
        indice.put(estudiante.getId(), estudiante);
    }

    /** Busca un estudiante por ID. */
    public Estudiante buscar(String id) throws EstudianteNoEncontradoException {
        Estudiante estudiante = (id == null) ? null : indice.get(id.trim());
        if (estudiante == null) {
            throw new EstudianteNoEncontradoException("No existe estudiante con ID: " + id);
        }
        return estudiante;
    }

    /** Da de baja a un estudiante y lo devuelve (para poder deshacer la baja). */
    public Estudiante eliminar(String id) throws EstudianteNoEncontradoException {
        Estudiante estudiante = buscar(id);
        indice.remove(estudiante.getId());
        return estudiante;
    }

    public int cantidad() {
        return indice.size();
    }

    /** Lista los estudiantes ordenados por ID. */
    public void listar() {
        if (indice.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        System.out.println("Estudiantes registrados (orden por ID):");
        // Un HashMap no garantiza orden: se copia a un TreeMap, que ordena por llave
        for (Estudiante e : new TreeMap<>(indice).values()) {
            System.out.println(e.getId() + " - " + e.getNombre()
                    + " (semestre " + e.getSemestre() + ")");
        }
    }
}