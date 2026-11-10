package momento1y2.planificacionacademica.estructuras;

import java.util.LinkedList;
import java.util.Queue;

import momento1y2.planificacionacademica.excepciones.ColaDeEsperaVaciaException;

public class GestorColaEspera {

    private Queue<String> cola;

    // Constructor
    public GestorColaEspera() {
        cola = new LinkedList<>();
    }

    // Agregar un estudiante a la cola
    public void agregarEstudiante(String idEstudiante) {

        if (idEstudiante == null || idEstudiante.isBlank()) {
            throw new IllegalArgumentException(
                    "El ID del estudiante no puede estar vacío."
            );
        }

        cola.offer(idEstudiante);

        System.out.println(
                "Estudiante agregado a la lista de espera."
        );
    }

    // Obtener y retirar el primer estudiante
    public String atenderSiguiente()
            throws ColaDeEsperaVaciaException {

        if (cola.isEmpty()) {
            throw new ColaDeEsperaVaciaException(
                    "No hay estudiantes en la cola de espera."
            );
        }

        return cola.poll();
    }

    // Consultar el siguiente estudiante sin retirarlo
    public String consultarSiguiente() {

        return cola.peek();
    }

    // Eliminar un estudiante específico
    public boolean eliminarEstudiante(String idEstudiante) {

        return cola.remove(idEstudiante);
    }

    // Verificar si la cola está vacía
    public boolean estaVacia() {

        return cola.isEmpty();
    }

    // Consultar cantidad de estudiantes
    public int cantidadEstudiantes() {

        return cola.size();
    }
}