
package momento1y2.planificacionacademica.estructuras;

import java.util.TreeMap;
import java.util.LinkedList;

import momento1y2.planificacionacademica.modelo.Aula;

public class GestorAulas {

    // TreeMap para almacenar las aulas ordenadas
    private TreeMap<String, Aula> aulas;

    // Constructor
    public GestorAulas() {
        aulas = new TreeMap<>();
    }

    // Registrar un aula
    public void registrarAula(Aula aula) {

        if (aula == null) {
            throw new IllegalArgumentException(
                    "El aula no puede ser nula."
            );
        }

        String clave = crearClave(aula);

        if (aulas.containsKey(clave)) {
            System.out.println("El aula ya está registrada.");
            return;
        }

        aulas.put(clave, aula);

        System.out.println("Aula registrada correctamente.");
    }

    // Buscar un aula por su código
    public Aula buscarAula(String codigo) {

        for (Aula aula : aulas.values()) {

            if (aula.getCodigo().equalsIgnoreCase(codigo)) {
                return aula;
            }
        }

        return null;
    }

    // Eliminar un aula por su código
    public void eliminarAula(String codigo) {

        String claveEncontrada = null;

        for (String clave : aulas.keySet()) {

            if (aulas.get(clave).getCodigo().equalsIgnoreCase(codigo)) {
                claveEncontrada = clave;
                break;
            }
        }

        if (claveEncontrada != null) {
            aulas.remove(claveEncontrada);
            System.out.println("Aula eliminada correctamente.");
        } else {
            System.out.println("No se encontró el aula.");
        }
    }

    // Listar todas las aulas
    public LinkedList<Aula> listarAulas() {

        return new LinkedList<>(aulas.values());
    }

    // Consultar cantidad de aulas registradas
    public int cantidadAulas() {
        return aulas.size();
    }

    // Crear una clave compuesta para ordenar por nombre,
    // capacidad y código
    private String crearClave(Aula aula) {

        return aula.getNombre().toLowerCase()
                + "|"
                + String.format("%010d", aula.getCapacidad())
                + "|"
                + aula.getCodigo().toLowerCase();
    }
}
