package momento1y2.planificacionacademica.excepciones;

/** Se lanza cuando el estudiante no ha aprobado un pre-requisito de la materia. */
public class PreRequisitoNoAprobadoException extends Exception {
    public PreRequisitoNoAprobadoException(String mensaje) {
        super(mensaje);
    }
}