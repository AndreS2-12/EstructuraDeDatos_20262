package momento1y2.planificacionacademica.estructuras;

import java.util.HashMap;
import java.util.Collection;

import momento1y2.planificacionacademica.modelo.Estudiante;

public class GestorEstudiantes {

    // Clave: ID del estudiante. Valor: objeto Estudiante.
    private HashMap<String, Estudiante> estudiantes;

    public GestorEstudiantes() {
        estudiantes = new HashMap<>();
    }

    // Registrar un estudiante
    public void registrarEstudiante(Estudiante estudiante) {

        if (estudiantes.containsKey(estudiante.getId())) {
            System.out.println("Ya existe un estudiante con ese ID.");
            return;
        }

        estudiantes.put(estudiante.getId(), estudiante);
        System.out.println("Estudiante registrado correctamente.");
    }

    // Buscar un estudiante por su ID
    public Estudiante buscarEstudiante(String id) {
        return estudiantes.get(id);
    }

    // Eliminar un estudiante por su ID
    public void eliminarEstudiante(String id) {

        Estudiante eliminado = estudiantes.remove(id);

        if (eliminado != null) {
            System.out.println("Estudiante eliminado correctamente.");
        } else {
            System.out.println("No se encontró el estudiante.");
        }
    }

    // Obtener todos los estudiantes
    public Collection<Estudiante> listarEstudiantes() {
        return estudiantes.values();
    }

    // Consultar la cantidad de estudiantes
    public int cantidadEstudiantes() {
        return estudiantes.size();
    }
}