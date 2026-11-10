
package momento1y2.planificacionacademica.estructuras;

import java.util.Stack;

public class GestorDeshacerRehacer {

    private Stack<Operacion> pilaDeshacer;
    private Stack<Operacion> pilaRehacer;

    public GestorDeshacerRehacer() {

        pilaDeshacer = new Stack<>();
        pilaRehacer = new Stack<>();
    }

    // Registrar y ejecutar una operación
    public void ejecutarOperacion(Operacion operacion) {

        operacion.ejecutar();

        pilaDeshacer.push(operacion);

        // Una nueva operación elimina la posibilidad
        // de rehacer operaciones anteriores
        pilaRehacer.clear();
    }

    // Deshacer la última operación
    public void deshacer() {

        if (pilaDeshacer.isEmpty()) {
            System.out.println(
                    "No hay operaciones para deshacer."
            );
            return;
        }

        Operacion operacion = pilaDeshacer.pop();

        operacion.deshacer();

        pilaRehacer.push(operacion);

        System.out.println("Operación deshecha correctamente.");
    }

    // Rehacer la última operación deshecha
    public void rehacer() {

        if (pilaRehacer.isEmpty()) {
            System.out.println(
                    "No hay operaciones para rehacer."
            );
            return;
        }

        Operacion operacion = pilaRehacer.pop();

        operacion.ejecutar();

        pilaDeshacer.push(operacion);

        System.out.println("Operación rehecha correctamente.");
    }

    // Consultar si existen operaciones para deshacer
    public boolean hayOperacionesParaDeshacer() {
        return !pilaDeshacer.isEmpty();
    }

    // Consultar si existen operaciones para rehacer
    public boolean hayOperacionesParaRehacer() {
        return !pilaRehacer.isEmpty();
    }
}