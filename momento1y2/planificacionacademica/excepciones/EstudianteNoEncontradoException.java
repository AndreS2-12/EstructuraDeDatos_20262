package momento1y2.planificacionacademica.excepciones;  

/** Se lanza cuando no existe un estudiante con el ID buscado. */
public class EstudianteNoEncontradoException extends Exception {
    public EstudianteNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}