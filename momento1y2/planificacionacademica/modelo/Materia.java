package momento1y2.planificacionacademica.modelo;
import java.util.LinkedList;
import java.util.Queue;
import momento1y2.planificacionacademica.estructuras.ListaEnlazada;

/**
 * Materia de la universidad. Guarda sus pre-requisitos (lista enlazada),
 * los estudiantes inscritos (lista enlazada) y la cola de espera (Queue).
 */
public class Materia {

    private final String codigo;
    private String nombre;
    private final int cuposMaximos;
    private final int creditos;

    // Códigos de las materias que hay que aprobar antes de cursar esta
    private final ListaEnlazada<String> prerrequisitos = new ListaEnlazada<>();

    // Estudiantes con cupo asignado
    private final ListaEnlazada<Estudiante> inscritos = new ListaEnlazada<>();

    // Estudiantes esperando un cupo (FIFO: el primero en llegar sale primero)
    private final Queue<Estudiante> colaEspera = new LinkedList<>();

    /**
     * Crea una materia validando sus datos.
     *
     * @param codigo       código único, por ejemplo CALC101 (no cambia)
     * @param nombre       nombre de la materia
     * @param cuposMaximos cantidad máxima de estudiantes (mayor a 0)
     * @param creditos     créditos de la materia (mayor a 0)
     */
    public Materia(String codigo, String nombre, int cuposMaximos, int creditos) {
        this.codigo = validarTexto(codigo, "El código").toUpperCase();
        setNombre(nombre);
        if (cuposMaximos <= 0) {
            throw new IllegalArgumentException("Los cupos máximos deben ser mayores a 0.");
        }
        if (creditos <= 0) {
            throw new IllegalArgumentException("Los créditos deben ser mayores a 0.");
        }
        this.cuposMaximos = cuposMaximos;
        this.creditos = creditos;
    }

    // ---------- Getters y setters ----------

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = validarTexto(nombre, "El nombre");
    }

    public int getCuposMaximos() {
        return cuposMaximos;
    }

    public int getCreditos() {
        return creditos;
    }

    // ---------- Pre-requisitos (lista enlazada) ----------

    /**
     * Agrega un pre-requisito por su código.
     *
     * @return true si se agregó; false si ya estaba en la lista
     */
    public boolean agregarPrerrequisito(String codigoPrerrequisito) {
        String codigoNormalizado = validarTexto(codigoPrerrequisito, "El código del pre-requisito")
                .toUpperCase();
        if (codigoNormalizado.equals(codigo)) {
            throw new IllegalArgumentException(
                    "Una materia no puede ser pre-requisito de sí misma.");
        }
        if (prerrequisitos.contiene(codigoNormalizado)) {
            return false;
        }
        prerrequisitos.agregarAlFinal(codigoNormalizado);
        return true;
    }

    /**
     * Busca el primer pre-requisito que el estudiante NO ha aprobado.
     *
     * @return el código del pre-requisito pendiente, o null si cumple todos
     */
    public String buscarPrerrequisitoPendiente(Estudiante estudiante) {
        validarEstudiante(estudiante);
        for (String codigoPrerrequisito : prerrequisitos) {
            if (!estudiante.haAprobado(codigoPrerrequisito)) {
                return codigoPrerrequisito;
            }
        }
        return null;
    }

    /** Imprime los pre-requisitos de la materia, numerados. */
    public void mostrarPrerrequisitos() {
        if (prerrequisitos.estaVacia()) {
            System.out.println(codigo + " no tiene pre-requisitos.");
            return;
        }
        System.out.println("Pre-requisitos de " + codigo + ":");
        int numero = 1;
        for (String codigoPrerrequisito : prerrequisitos) {
            System.out.println(numero + ". " + codigoPrerrequisito);
            numero++;
        }
    }

    // ---------- Inscritos (lista enlazada) ----------

    public int getCantidadInscritos() {
        return inscritos.tamano();
    }

    public int getCuposDisponibles() {
        return cuposMaximos - inscritos.tamano();
    }

    public boolean tieneCupo() {
        return getCuposDisponibles() > 0;
    }

    public boolean estaInscrito(Estudiante estudiante) {
        return inscritos.contiene(estudiante);
    }

    /**
     * Agrega un estudiante a los inscritos.
     * Quien llama debe haber comprobado antes que hay cupo y que no está inscrito.
     */
    public void agregarInscrito(Estudiante estudiante) {
        validarEstudiante(estudiante);
        if (estaInscrito(estudiante)) {
            throw new IllegalStateException("El estudiante ya está inscrito en " + codigo + ".");
        }
        if (!tieneCupo()) {
            throw new IllegalStateException("La materia " + codigo + " no tiene cupos disponibles.");
        }
        inscritos.agregarAlFinal(estudiante);
    }

    /**
     * Quita a un estudiante de los inscritos.
     *
     * @return true si estaba inscrito y se quitó; false si no estaba
     */
    public boolean eliminarInscrito(Estudiante estudiante) {
        return inscritos.eliminar(estudiante);
    }

    // ---------- Cola de espera (Queue) ----------

    public boolean estaEnEspera(Estudiante estudiante) {
        return colaEspera.contains(estudiante);
    }

    /** Pone al estudiante al final de la cola de espera. */
    public void agregarAEspera(Estudiante estudiante) {
        validarEstudiante(estudiante);
        if (estaInscrito(estudiante)) {
            throw new IllegalStateException("El estudiante ya está inscrito en " + codigo + ".");
        }
        if (estaEnEspera(estudiante)) {
            throw new IllegalStateException("El estudiante ya está en la cola de espera de " + codigo + ".");
        }
        colaEspera.offer(estudiante);
    }

    /**
     * Saca de la cola al primer estudiante en espera.
     *
     * @return el estudiante, o null si la cola está vacía
     */
    public Estudiante siguienteEnEspera() {
        return colaEspera.poll();
    }

    public int getCantidadEnEspera() {
        return colaEspera.size();
    }

    /** Imprime la cola de espera por posición. */
    public void mostrarColaEspera() {
        if (colaEspera.isEmpty()) {
            System.out.println("No hay estudiantes en cola de espera.");
            return;
        }
        int posicion = 1;
        for (Estudiante estudiante : colaEspera) {
            System.out.println("Posicion " + posicion + ": " + estudiante.getNombre());
            posicion++;
        }
        System.out.println("Total en espera: " + colaEspera.size());
    }

    // ---------- Texto ----------

    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }

    // ---------- Utilidades internas ----------

    private String validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío.");
        }
        return valor.trim();
    }

    private void validarEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }
    }
}