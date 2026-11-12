
package momento1y2.planificacionacademica.modelo;

public class Aula {

    private String codigo;
    private String nombre;
    private int capacidad;

    // Matriz de disponibilidad: 7 días y 24 horas
    // false = disponible
    // true = ocupado
    private boolean[][] disponibilidad;

    // Constructor
    public Aula(String codigo, String nombre, int capacidad) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.capacidad = capacidad;

        // Inicializar la matriz de disponibilidad
        disponibilidad = new boolean[7][24];
    }

    // Obtener el código del aula
    public String getCodigo() {
        return codigo;
    }

    // Obtener el nombre del aula
    public String getNombre() {
        return nombre;
    }

    // Obtener la capacidad del aula
    public int getCapacidad() {
        return capacidad;
    }

    // Obtener la matriz de disponibilidad
    public boolean[][] getDisponibilidad() {
        return disponibilidad;
    }

    // Consultar si un horario está disponible
    public boolean estaDisponible(int dia, int hora) {

        if (dia < 0 || dia >= 7 || hora < 0 || hora >= 24) {
            throw new IllegalArgumentException(
                    "El día o la hora están fuera del rango permitido."
            );
        }

        return !disponibilidad[dia][hora];
    }

    // Reservar un horario
    public void reservarHorario(int dia, int hora) {

        if (!estaDisponible(dia, hora)) {
            System.out.println("El horario ya está ocupado.");
            return;
        }

        disponibilidad[dia][hora] = true;

        System.out.println("Horario reservado correctamente.");
    }

    // Liberar un horario
    public void liberarHorario(int dia, int hora) {

        if (estaDisponible(dia, hora)) {
            System.out.println("El horario ya está disponible.");
            return;
        }

        disponibilidad[dia][hora] = false;

        System.out.println("Horario liberado correctamente.");
    }
}