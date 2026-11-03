package momento1y2.planificacionacademica.servicio;
import momento1y2.planificacionacademica.excepciones.CupoLlenoException;
import momento1y2.planificacionacademica.excepciones.PreRequisitoNoAprobadoException;
import momento1y2.planificacionacademica.modelo.Estudiante;

/**
 * Inscribir a un estudiante en una materia.
 * Su inverso es cancelar esa inscripción.
 */
public class OpInscribir extends Operacion {

    private final GestorMaterias gestor;
    private final Estudiante estudiante;
    private final String codigoMateria;

    public OpInscribir(GestorMaterias gestor, Estudiante estudiante, String codigoMateria) {
        if (gestor == null || estudiante == null
                || codigoMateria == null || codigoMateria.trim().isEmpty()) {
            throw new IllegalArgumentException("Todos los datos son obligatorios.");
        }
        this.gestor = gestor;
        this.estudiante = estudiante;
        this.codigoMateria = codigoMateria.trim().toUpperCase();
    }

    @Override
    public String ejecutar() throws PreRequisitoNoAprobadoException, CupoLlenoException {
        gestor.inscribir(estudiante, codigoMateria);
        return "Inscripcion de " + estudiante.getNombre() + " en " + codigoMateria;
    }

    @Override
    public String deshacer() {
        // Se aplica la misma regla de siempre: al liberarse el cupo,
        // si alguien esperaba, el cupo es para el primero de la cola.
        Estudiante promovido = gestor.cancelarInscripcion(estudiante, codigoMateria);
        String mensaje = "Inscripcion de " + estudiante.getNombre()
                + " en " + codigoMateria + " revertida";
        if (promovido != null) {
            mensaje += ". Cupo asignado a " + promovido.getNombre();
        }
        return mensaje;
    }
}