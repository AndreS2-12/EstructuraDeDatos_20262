package momento1y2.planificacionacademica.modelo;
import java.util.*;
import momento1y2.planificacionacademica.estructuras.ListaEnlazada;   // NUEVO

/**
 * Estudiante universitario. Hereda de Persona y agrega el semestre actual,
 * las notas por semestre en una matriz Double[10][20] y el historial
 * de materias cursadas en una lista enlazada.
 */
public class Estudiante extends Persona {

    public static final int MAX_SEMESTRES = 10;
    public static final int MAX_MATERIAS = 20;
    public static final double NOTA_MINIMA = 0.0;
    public static final double NOTA_MAXIMA = 5.0;
    public static final double NOTA_APROBATORIA = 3.0;

    private int semestre;

    // notas[semestre - 1][columna]; null significa "sin nota registrada"
    private final Double[][] notas = new Double[MAX_SEMESTRES][MAX_MATERIAS];

    // Matriz paralela: codigosMaterias[f][c] es la materia de notas[f][c]
    private final String[][] codigosMaterias = new String[MAX_SEMESTRES][MAX_MATERIAS];

    // NUEVO: códigos de las materias cursadas, sin repetir, en orden de registro
    private final ListaEnlazada<String> historialMaterias = new ListaEnlazada<>();

    /**
     * Crea un estudiante.
     *
     * @param semestre semestre actual (entre 1 y 10)
     */
    public Estudiante(String nombre, String id, String email, int semestre) {
        super(nombre, id, email);
        setSemestre(semestre);
    }

    // ---------- Getters y setters ----------

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        validarSemestre(semestre);
        this.semestre = semestre;
    }

    // ---------- Notas ----------

    /**
     * Registra (o actualiza) la nota de una materia en un semestre.
     * Si la materia es nueva para el estudiante, también entra al historial.
     *
     * @param semestre      semestre de 1 a 10
     * @param codigoMateria código de la materia, por ejemplo CALC101
     * @param nota          nota entre 0.0 y 5.0
     */
    public void registrarNota(int semestre, String codigoMateria, double nota) {
        validarSemestre(semestre);
        if (codigoMateria == null || codigoMateria.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de la materia no puede estar vacío.");
        }
        if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {
            throw new IllegalArgumentException(
                    "La nota debe estar entre " + NOTA_MINIMA + " y " + NOTA_MAXIMA + ".");
        }

        String codigo = codigoMateria.trim().toUpperCase();
        int fila = semestre - 1;
        int columnaLibre = -1;

        for (int col = 0; col < MAX_MATERIAS; col++) {
            if (codigosMaterias[fila][col] == null) {
                if (columnaLibre == -1) {
                    columnaLibre = col;          // primera posición vacía
                }
            } else if (codigosMaterias[fila][col].equals(codigo)) {
                notas[fila][col] = nota;         // la materia ya existía: se actualiza
                return;
            }
        }

        if (columnaLibre == -1) {
            throw new IllegalStateException(
                    "El semestre " + semestre + " ya tiene " + MAX_MATERIAS + " materias registradas.");
        }
        codigosMaterias[fila][columnaLibre] = codigo;
        notas[fila][columnaLibre] = nota;

        // NUEVO: la materia entra al historial solo la primera vez
        if (!historialMaterias.contiene(codigo)) {
            historialMaterias.agregarAlFinal(codigo);
        }
    }

    /**
     * Devuelve la nota de una materia o null si no tiene nota registrada.
     */
    public Double getNota(int semestre, String codigoMateria) {
        validarSemestre(semestre);
        if (codigoMateria == null) {
            return null;
        }
        String codigo = codigoMateria.trim().toUpperCase();
        int fila = semestre - 1;
        for (int col = 0; col < MAX_MATERIAS; col++) {
            if (codigo.equals(codigosMaterias[fila][col])) {
                return notas[fila][col];
            }
        }
        return null;
    }

    /**
     * Promedio de un semestre. Devuelve 0.0 si no hay notas.
     */
    public double calcularPromedioSemestre(int semestre) {
        validarSemestre(semestre);
        int fila = semestre - 1;
        double suma = 0.0;
        int cantidad = 0;
        for (int col = 0; col < MAX_MATERIAS; col++) {
            if (notas[fila][col] != null) {
                suma += notas[fila][col];
                cantidad++;
            }
        }
        return cantidad == 0 ? 0.0 : suma / cantidad;
    }

    /**
     * Promedio acumulado de todas las notas registradas. Devuelve 0.0 si no hay notas.
     */
    public double calcularPromedioAcumulado() {
        double suma = 0.0;
        int cantidad = 0;
        for (int fila = 0; fila < MAX_SEMESTRES; fila++) {
            for (int col = 0; col < MAX_MATERIAS; col++) {
                if (notas[fila][col] != null) {
                    suma += notas[fila][col];
                    cantidad++;
                }
            }
        }
        return cantidad == 0 ? 0.0 : suma / cantidad;
    }

    /** Cantidad de materias con nota mayor o igual a 3.0. */
    public int contarAprobadas() {
        return contarSegunAprobacion(true);
    }

    /** Cantidad de materias con nota menor a 3.0. */
    public int contarReprobadas() {
        return contarSegunAprobacion(false);
    }

    // ---------- NUEVO: historial de materias ----------

    /** Indica si el estudiante ya cursó la materia (tiene alguna nota registrada). */
    public boolean haCursado(String codigoMateria) {
        if (codigoMateria == null) {
            return false;
        }
        return historialMaterias.contiene(codigoMateria.trim().toUpperCase());
    }

    /**
     * Indica si el estudiante aprobó la materia en algún semestre
     * (nota mayor o igual a 3.0). Sirve para validar pre-requisitos.
     */
    public boolean haAprobado(String codigoMateria) {
        if (codigoMateria == null) {
            return false;
        }
        String codigo = codigoMateria.trim().toUpperCase();
        for (int fila = 0; fila < MAX_SEMESTRES; fila++) {
            for (int col = 0; col < MAX_MATERIAS; col++) {
                if (codigo.equals(codigosMaterias[fila][col])
                        && notas[fila][col] != null
                        && notas[fila][col] >= NOTA_APROBATORIA) {
                    return true;
                }
            }
        }
        return false;
    }

    /** Cantidad de materias distintas que ha cursado. */
    public int getCantidadMateriasCursadas() {
        return historialMaterias.tamano();
    }

    /** Imprime el historial de materias cursadas, numerado. */
    public void mostrarHistorial() {
        if (historialMaterias.estaVacia()) {
            System.out.println("Sin materias cursadas aun.");
            return;
        }
        int numero = 1;
        for (String codigo : historialMaterias) {
            System.out.println(numero + ". " + codigo);
            numero++;
        }
    }

    // ---------- Polimorfismo ----------

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Semestre: " + semestre);
        if (tieneNotas()) {
            System.out.printf(Locale.US, "Promedio acumulado: %.2f%n", calcularPromedioAcumulado());
        } else {
            System.out.println("Promedio acumulado: 0.0 (sin notas aun)");
        }
    }

    // ---------- Utilidades internas ----------

    private boolean tieneNotas() {
        return contarAprobadas() + contarReprobadas() > 0;
    }

    private int contarSegunAprobacion(boolean aprobadas) {
        int cantidad = 0;
        for (int fila = 0; fila < MAX_SEMESTRES; fila++) {
            for (int col = 0; col < MAX_MATERIAS; col++) {
                Double nota = notas[fila][col];
                if (nota != null && (nota >= NOTA_APROBATORIA) == aprobadas) {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }

    private void validarSemestre(int semestre) {
        if (semestre < 1 || semestre > MAX_SEMESTRES) {
            throw new IllegalArgumentException(
                    "El semestre debe estar entre 1 y " + MAX_SEMESTRES + ".");
        }
    }
}