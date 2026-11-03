package momento1y2.planificacionacademica.servicio;
import java.util.HashMap;
import java.util.Map;
import momento1y2.planificacionacademica.excepciones.ColaDeEsperaVaciaException;
import momento1y2.planificacionacademica.excepciones.CupoLlenoException;
import momento1y2.planificacionacademica.excepciones.PreRequisitoNoAprobadoException;
import momento1y2.planificacionacademica.modelo.Estudiante;
import momento1y2.planificacionacademica.modelo.Materia;

/**
 * Aplica las reglas de las materias: pre-requisitos, cupos,
 * cola de espera y asignación automática de cupos liberados.
 */
public class GestorMaterias {

    private final Map<String, Materia> materias = new HashMap<>();

    // ---------- Materias ----------

    /** Crea una materia nueva y la registra. */
    public Materia crear(String codigo, String nombre, int cuposMaximos, int creditos) {
        Materia materia = new Materia(codigo, nombre, cuposMaximos, creditos);
        if (materias.containsKey(materia.getCodigo())) {
            throw new IllegalArgumentException("Ya existe la materia " + materia.getCodigo() + ".");
        }
        materias.put(materia.getCodigo(), materia);
        return materia;
    }

    /** Busca una materia por código (sin importar mayúsculas). */
    public Materia buscar(String codigo) {
        Materia materia = (codigo == null) ? null : materias.get(codigo.trim().toUpperCase());
        if (materia == null) {
            throw new IllegalArgumentException("No existe la materia: " + codigo);
        }
        return materia;
    }

    public int cantidad() {
        return materias.size();
    }

    // ---------- Pre-requisitos ----------

    /**
     * Agrega un pre-requisito. Ambas materias deben existir.
     *
     * @return true si se agregó; false si ya estaba
     */
    public boolean agregarPrerrequisito(String codigoMateria, String codigoPrerrequisito) {
        Materia materia = buscar(codigoMateria);
        Materia prerrequisito = buscar(codigoPrerrequisito);
        return materia.agregarPrerrequisito(prerrequisito.getCodigo());
    }

    public void mostrarPrerrequisitos(String codigoMateria) {
        buscar(codigoMateria).mostrarPrerrequisitos();
    }

    // ---------- Inscripción ----------

    /**
     * Inscribe al estudiante si cumple los pre-requisitos y hay cupo.
     * Si la materia está llena, el estudiante pasa a la cola de espera
     * y se lanza CupoLlenoException para avisar que NO quedó inscrito.
     *
     * @throws PreRequisitoNoAprobadoException si falta aprobar un pre-requisito
     * @throws CupoLlenoException              si no hay cupo (queda en la cola de espera)
     */
    public void inscribir(Estudiante estudiante, String codigoMateria)
            throws PreRequisitoNoAprobadoException, CupoLlenoException {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }
        Materia materia = buscar(codigoMateria);

        if (materia.estaInscrito(estudiante)) {
            throw new IllegalStateException("El estudiante " + estudiante.getNombre()
                    + " ya esta inscrito en " + materia.getCodigo() + ".");
        }
        if (materia.estaEnEspera(estudiante)) {
            throw new IllegalStateException("El estudiante " + estudiante.getNombre()
                    + " ya esta en la cola de espera de " + materia.getCodigo() + ".");
        }

        String pendiente = materia.buscarPrerrequisitoPendiente(estudiante);
        if (pendiente != null) {
            throw new PreRequisitoNoAprobadoException(estudiante.getNombre()
                    + " no ha aprobado el pre-requisito " + pendiente
                    + " de " + materia.getCodigo());
        }

        if (materia.tieneCupo()) {
            materia.agregarInscrito(estudiante);
            return;
        }

        materia.agregarAEspera(estudiante);
        throw new CupoLlenoException("Materia llena. " + estudiante.getNombre()
                + " pasa a la COLA DE ESPERA (posicion " + materia.getCantidadEnEspera() + ")");
    }

    /**
     * Cancela la inscripción y, si hay alguien esperando, le asigna el cupo.
     *
     * @return el estudiante que recibió el cupo, o null si nadie esperaba
     */
    public Estudiante cancelarInscripcion(Estudiante estudiante, String codigoMateria) {
        Materia materia = buscar(codigoMateria);
        if (!materia.eliminarInscrito(estudiante)) {
            throw new IllegalStateException("El estudiante "
                    + (estudiante == null ? "null" : estudiante.getNombre())
                    + " no esta inscrito en " + materia.getCodigo() + ".");
        }
        try {
            return asignarSiguienteDeCola(materia);
        } catch (ColaDeEsperaVaciaException e) {
            return null;    // nadie esperaba: el cupo simplemente queda libre
        }
    }

    // ---------- Cola de espera ----------

    public void mostrarColaEspera(String codigoMateria) {
        buscar(codigoMateria).mostrarColaEspera();
    }

    /** Saca al primero de la cola y lo inscribe. */
    private Estudiante asignarSiguienteDeCola(Materia materia) throws ColaDeEsperaVaciaException {
        Estudiante siguiente = materia.siguienteEnEspera();
        if (siguiente == null) {
            throw new ColaDeEsperaVaciaException(
                    "No hay estudiantes en espera para " + materia.getCodigo());
        }
        materia.agregarInscrito(siguiente);
        return siguiente;
    }
}