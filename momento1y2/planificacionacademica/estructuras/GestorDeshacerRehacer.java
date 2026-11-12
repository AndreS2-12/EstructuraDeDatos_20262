
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

        if (operacion == null) {
            throw new IllegalArgumentException(
                    "La operación no puede ser nula."
            );
        }

        // Primero se ejecuta la operación
        operacion.ejecutar();

        // Si no ocurre ningún error, se guarda en la pila
        pilaDeshacer.push(operacion);

        // Una nueva operación elimina la posibilidad de rehacer
        pilaRehacer.clear();
    }

    // Deshacer la última operación
    public void deshacer() throws PilaDeshacerVaciaException {

        if (pilaDeshacer.isEmpty()) {
            throw new PilaDeshacerVaciaException(
                    "No hay operaciones disponibles para deshacer."
            );
        }

        // Consultar la última operación sin eliminarla todavía
        Operacion operacion = pilaDeshacer.peek();

        // Ejecutar la acción de deshacer
        operacion.deshacer();

        // Si se completa correctamente, moverla a la otra pila
        pilaDeshacer.pop();
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

        // Consultar la última operación sin eliminarla todavía
        Operacion operacion = pilaRehacer.peek();

        // Ejecutar nuevamente la operación
        operacion.ejecutar();

        // Si se completa correctamente, moverla a la otra pila
        pilaRehacer.pop();
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