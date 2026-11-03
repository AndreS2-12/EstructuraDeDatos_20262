package momento1y2.planificacionacademica.servicio;
import momento1y2.planificacionacademica.excepciones.EstudianteNoEncontradoException;
import momento1y2.planificacionacademica.modelo.Estudiante;
/**
 * Dar de baja a un estudiante. Guarda al estudiante eliminado
 * (su estado anterior) para poder restaurarlo al deshacer.
 */
public class OpEliminarEstudiante extends Operacion {

    private final GestorEstudiantes gestor;
    private final String id;
    private Estudiante eliminado;      // estado anterior: el estudiante tal como estaba

    public OpEliminarEstudiante(GestorEstudiantes gestor, String id) {
        if (gestor == null || id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El gestor y el ID son obligatorios.");
        }
        this.gestor = gestor;
        this.id = id.trim();
    }

    @Override
    public String ejecutar() throws EstudianteNoEncontradoException {
        eliminado = gestor.eliminar(id);
        return "Estudiante " + eliminado.getNombre() + " (ID: " + id + ") dado de baja";
    }

    @Override
    public String deshacer() {
        if (eliminado == null) {
            throw new IllegalStateException("La operacion aun no se ha ejecutado.");
        }
        gestor.registrar(eliminado);
        return "Estudiante " + eliminado.getNombre() + " (ID: " + id + ") restaurado";
    }
}