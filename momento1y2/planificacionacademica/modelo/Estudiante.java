package momento1y2.planificacionacademica.modelo;

import java.util.Locale;
import momento1y2.planificacionacademica.estructuras.ListaEnlazada;

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

    private final Double[][] notas = new Double[MAX_SEMESTRES][MAX_MATERIAS];

    private final String[][] codigosMaterias =
            new String[MAX_SEMESTRES][MAX_MATERIAS];

    private final ListaEnlazada<String> historialMaterias =
            new ListaEnlazada<>();

    public Estudiante(String nombre, String id,
                      String email, int semestre) {

        super(nombre, id, email);
        setSemestre(semestre);
    }

    // ---------------- GETTERS Y SETTERS ----------------

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        validarSemestre(semestre);
        this.semestre = semestre;
    }

    // ---------------- NOTAS ----------------

    public void registrarNota(int semestre,
                              String codigoMateria,
                              double nota) {

        validarSemestre(semestre);

        if (codigoMateria == null
                || codigoMateria.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "El código de la materia no puede estar vacío.");
        }

        if (nota < NOTA_MINIMA || nota > NOTA_MAXIMA) {

            throw new IllegalArgumentException(
                    "La nota debe estar entre "
                    + NOTA_MINIMA
                    + " y "
                    + NOTA_MAXIMA
                    + ".");
        }

        String codigo = codigoMateria.trim().toUpperCase();

        int fila = semestre - 1;
        int columnaLibre = -1;

        for (int col = 0; col < MAX_MATERIAS; col++) {

            if (codigosMaterias[fila][col] == null) {

                if (columnaLibre == -1) {
                    columnaLibre = col;
                }

            } else if (codigosMaterias[fila][col].equals(codigo)) {

                notas[fila][col] = nota;
                return;
            }
        }

        if (columnaLibre == -1) {

            throw new IllegalStateException(
                    "El semestre "
                    + semestre
                    + " ya tiene "
                    + MAX_MATERIAS
                    + " materias registradas.");
        }

        codigosMaterias[fila][columnaLibre] = codigo;
        notas[fila][columnaLibre] = nota;

        if (!historialMaterias.contiene(codigo)) {
            historialMaterias.agregarAlFinal(codigo);
        }
    }

    public Double getNota(int semestre,
                          String codigoMateria) {

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

    public String[] getCodigosSemestre(int semestre) {

        validarSemestre(semestre);

        int fila = semestre - 1;

        int cantidad = 0;

        for (int col = 0; col < MAX_MATERIAS; col++) {

            if (codigosMaterias[fila][col] != null) {
                cantidad++;
            }
        }

        String[] codigos = new String[cantidad];

        int indice = 0;

        for (int col = 0; col < MAX_MATERIAS; col++) {

            if (codigosMaterias[fila][col] != null) {
                codigos[indice++] = codigosMaterias[fila][col];
            }
        }

        return codigos;
    }

    /**
     * Elimina la nota de una materia en un semestre.
     * Si la materia ya no tiene notas en ningún semestre,
     * también se elimina del historial.
     */
    public boolean eliminarNota(int semestre,
                                String codigoMateria) {

        validarSemestre(semestre);

        if (codigoMateria == null) {
            return false;
        }

        String codigo = codigoMateria.trim().toUpperCase();

        int fila = semestre - 1;

        for (int col = 0; col < MAX_MATERIAS; col++) {

            if (codigo.equals(codigosMaterias[fila][col])) {

                codigosMaterias[fila][col] = null;
                notas[fila][col] = null;

                if (!tieneNotaDe(codigo)) {
                    historialMaterias.eliminar(codigo);
                }

                return true;
            }
        }

        return false;
    }

    /**
     * Verifica si una materia tiene notas registradas
     * en cualquier semestre.
     */
    private boolean tieneNotaDe(String codigo) {

        for (int fila = 0; fila < MAX_SEMESTRES; fila++) {

            for (int col = 0; col < MAX_MATERIAS; col++) {

                if (codigo.equals(codigosMaterias[fila][col])) {
                    return true;
                }
            }
        }

        return false;
    }

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

        return cantidad == 0
                ? 0.0
                : suma / cantidad;
    }

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

        return cantidad == 0
                ? 0.0
                : suma / cantidad;
    }

    public int contarAprobadas() {
        return contarSegunAprobacion(true);
    }

    public int contarReprobadas() {
        return contarSegunAprobacion(false);
    }

    // ---------------- HISTORIAL ----------------

    public boolean haCursado(String codigoMateria) {

        if (codigoMateria == null) {
            return false;
        }

        return historialMaterias.contiene(
                codigoMateria.trim().toUpperCase());
    }

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

    public int getCantidadMateriasCursadas() {
        return historialMaterias.tamano();
    }

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

    // ---------------- POLIMORFISMO ----------------

    @Override
    public void mostrarInformacion() {

        super.mostrarInformacion();

        System.out.println("Semestre: " + semestre);

        if (tieneNotas()) {

            System.out.printf(
                    Locale.US,
                    "Promedio acumulado: %.2f%n",
                    calcularPromedioAcumulado());

        } else {

            System.out.println(
                    "Promedio acumulado: 0.0 (sin notas aun)");
        }
    }

    // ---------------- MÉTODOS AUXILIARES ----------------

    private boolean tieneNotas() {

        return contarAprobadas()
                + contarReprobadas() > 0;
    }

    private int contarSegunAprobacion(
            boolean aprobadas) {

        int cantidad = 0;

        for (int fila = 0; fila < MAX_SEMESTRES; fila++) {

            for (int col = 0; col < MAX_MATERIAS; col++) {

                Double nota = notas[fila][col];

                if (nota != null
                        && (nota >= NOTA_APROBATORIA)
                        == aprobadas) {

                    cantidad++;
                }
            }
        }

        return cantidad;
    }

    private void validarSemestre(int semestre) {

        if (semestre < 1
                || semestre > MAX_SEMESTRES) {

            throw new IllegalArgumentException(
                    "El semestre debe estar entre 1 y "
                    + MAX_SEMESTRES
                    + ".");
        }
    }
}