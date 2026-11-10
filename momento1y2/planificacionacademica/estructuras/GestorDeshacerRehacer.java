
package momento1y2.planificacionacademica.estructuras;

import java.util.Stack;

import momento1y2.planificacionacademica.excepciones.PilaDeshacerVaciaException;
import momento1y2.planificacionacademica.excepciones.PilaRehacerVaciaException;

public class GestorDeshacerRehacer {

    private Stack<Operacion> pilaDeshacer;
    private Stack<Operacion> pilaRehacer;

    // Constructor
    public GestorDeshacerRehacer() {
        pilaDeshacer = new Stack<>();
        pilaRehacer = new Stack<>();
    }

    // Ejecutar y registrar una operación
    public void ejecutarOperacion(Operacion operacion) {

        operacion.ejecutar();

        pilaDeshacer.push(operacion);

        // Limpiar la pila de rehacer
        pilaRehacer.clear();
    }

    // Deshacer la última operación
    public void deshacer() throws PilaDeshacerVaciaException {

        if (pilaDeshacer.isEmpty()) {
            throw new PilaDeshacerVaciaException(
                    "No hay operaciones disponibles para deshacer."
            );
        }

        Operacion operacion = pilaDeshacer.pop();

        operacion.deshacer();

        pilaRehacer.push(operacion);

        System.out.println("Operación deshecha correctamente.");
    }

    // Rehacer la última operación
    public void rehacer() throws PilaRehacerVaciaException {

        if (pilaRehacer.isEmpty()) {
            throw new PilaRehacerVaciaException(
                    "No hay operaciones disponibles para rehacer."
            );
        }

        Operacion operacion = pilaRehacer.pop();

        operacion.ejecutar();

        pilaDeshacer.push(operacion);

        System.out.println("Operación rehecha correctamente.");
    }

    // Verificar si hay operaciones para deshacer
    public boolean hayOperacionesParaDeshacer() {
        return !pilaDeshacer.isEmpty();
    }

    // Verificar si hay operaciones para rehacer
    public boolean hayOperacionesParaRehacer() {
        return !pilaRehacer.isEmpty();
    }
}