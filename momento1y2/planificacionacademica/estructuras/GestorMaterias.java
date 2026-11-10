package momento1y2.planificacionacademica.estructuras;

import java.util.Collection;
import java.util.HashMap;

import momento1y2.planificacionacademica.modelo.Materia;

public class GestorMaterias {

    // Clave: código de la materia. Valor: objeto Materia.
    private HashMap<String, Materia> materias;

    public GestorMaterias() {
        materias = new HashMap<>();
    }

    // Registrar una materia
    public void registrarMateria(Materia materia) {

        if (materias.containsKey(materia.getCodigo())) {
            System.out.println("Ya existe una materia con ese código.");
            return;
        }

        materias.put(materia.getCodigo(), materia);
        System.out.println("Materia registrada correctamente.");
    }

    // Buscar una materia por su código
    public Materia buscarMateria(String codigo) {
        return materias.get(codigo);
    }

    // Eliminar una materia por su código
    public void eliminarMateria(String codigo) {

        Materia eliminada = materias.remove(codigo);

        if (eliminada != null) {
            System.out.println("Materia eliminada correctamente.");
        } else {
            System.out.println("No se encontró la materia.");
        }
    }

    // Listar todas las materias
    public Collection<Materia> listarMaterias() {
        return materias.values();
    }

    // Consultar la cantidad de materias
    public int cantidadMaterias() {
        return materias.size();
    }
}