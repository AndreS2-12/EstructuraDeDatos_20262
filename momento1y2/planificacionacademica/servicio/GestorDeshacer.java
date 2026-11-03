package momento1y2.planificacionacademica.servicio;
import momento1y2.planificacionacademica.estructuras.Pila;
import momento1y2.planificacionacademica.excepciones.PilaDeshacerVaciaException;
/**
 * Sistema de deshacer/rehacer con dos pilas de operaciones.
 */
public class GestorDeshacer {

    private final Pila<Operacion> pilaDeshacer = new Pila<>();
    private final Pila<Operacion> pilaRehacer = new Pila<>();

    /**
     * Ejecuta una operación nueva y la guarda para poder deshacerla.
     * Si la operación falla, no se guarda en ninguna pila.
     */
    public String ejecutar(Operacion operacion) throws Exception {
        if (operacion == null) {
            throw new IllegalArgumentException("La operacion no puede ser nula.");
        }
        String mensaje = operacion.ejecutar();   // si lanza una excepción, termina aquí
        pilaDeshacer.apilar(operacion);
        pilaRehacer.limpiar();                   // una operación nueva invalida lo "rehacible"
        return mensaje;
    }

    /** Deshace la última operación ejecutada. */
    public String deshacer() throws Exception {
        if (pilaDeshacer.estaVacia()) {
            throw new PilaDeshacerVaciaException("No hay operaciones para deshacer");
        }
        Operacion operacion = pilaDeshacer.verTope();   // se mira sin sacarla todavía
        String mensaje = operacion.deshacer();
        pilaDeshacer.desapilar();                       // solo se mueve si el deshacer funcionó
        pilaRehacer.apilar(operacion);
        return "Operacion deshecha: " + mensaje;
    }

    /** Rehace la última operación que se deshizo. */
    public String rehacer() throws Exception {
        if (pilaRehacer.estaVacia()) {
            throw new PilaDeshacerVaciaException("No hay operaciones para rehacer");
        }
        Operacion operacion = pilaRehacer.verTope();
        String mensaje = operacion.ejecutar();
        pilaRehacer.desapilar();
        pilaDeshacer.apilar(operacion);
        return "Operacion rehecha: " + mensaje;
    }

    public int getCantidadDeshacer() {
        return pilaDeshacer.tamano();
    }

    public int getCantidadRehacer() {
        return pilaRehacer.tamano();
    }
}