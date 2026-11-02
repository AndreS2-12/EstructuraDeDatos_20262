package momento1y2.planificacionacademica.excepciones;

/** Se lanza cuando se necesita a alguien de la cola de espera y no hay nadie. */
public class ColaDeEsperaVaciaException extends Exception {
    public ColaDeEsperaVaciaException(String mensaje) {
        super(mensaje);
    }
}