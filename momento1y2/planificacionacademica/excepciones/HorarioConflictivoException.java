package momento1y2.planificacionacademica.excepciones;

/** Se lanza cuando se intenta reservar una hora de un aula que ya está ocupada. */
public class HorarioConflictivoException extends Exception {
    public HorarioConflictivoException(String mensaje) {
        super(mensaje);
    }
}