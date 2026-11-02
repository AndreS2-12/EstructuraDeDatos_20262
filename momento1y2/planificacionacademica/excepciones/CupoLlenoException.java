package momento1y2.planificacionacademica.excepciones;

/** Se lanza cuando la materia no tiene cupos disponibles. */
public class CupoLlenoException extends Exception {
    public CupoLlenoException(String mensaje) {
        super(mensaje);
    }
}