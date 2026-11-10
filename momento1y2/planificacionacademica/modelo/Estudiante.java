package momento1y2.planificacionacademica.modelo;

import java.util.LinkedList;

public class Estudiante extends Persona {

    private int semestre;

    // Historial de materias cursadas
    private LinkedList<String> historialMaterias;

    public Estudiante(String nombre, String id,
                    String email, int semestre) {

        super(nombre, id, email);

        this.semestre = semestre;
        this.historialMaterias = new LinkedList<>();
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public LinkedList<String> getHistorialMaterias() {
        return historialMaterias;
    }

    @Override
    public void mostrarInformacion() {

        System.out.println("Nombre: " + getNombre());
        System.out.println("ID: " + getId());
        System.out.println("Email: " + getEmail());
        System.out.println("Semestre: " + semestre);
    }
}