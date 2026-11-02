package momento1y2.planificacionacademica.modelo;
import momento1y2.planificacionacademica.excepciones.HorarioConflictivoException;

/**
 * Aula de la universidad. Su horario semanal se guarda en una matriz
 * boolean[7][24]: filas = días (0 = Domingo ... 6 = Sábado),
 * columnas = horas (0 a 23). true significa "ocupada".
 */
public class Aula {

    public static final int DIAS = 7;
    public static final int HORAS = 24;

    private static final String[] NOMBRES_DIAS = {
        "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"
    };

    private final String nombre;
    private int capacidad;
    private final boolean[][] horario = new boolean[DIAS][HORAS];

    /**
     * Crea un aula con el horario completamente libre.
     *
     * @param nombre    nombre único del aula, por ejemplo 101 (no cambia)
     * @param capacidad cantidad de personas que caben (mayor a 0)
     */
    public Aula(String nombre, int capacidad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del aula no puede estar vacío.");
        }
        this.nombre = nombre.trim();
        setCapacidad(capacidad);
    }

    // ---------- Getters y setters ----------

    public String getNombre() {
        return nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0.");
        }
        this.capacidad = capacidad;
    }

    /** Devuelve el nombre del día (0 = Domingo ... 6 = Sabado). */
    public static String nombreDia(int dia) {
        validarDia(dia);
        return NOMBRES_DIAS[dia];
    }

    // ---------- Horario (matriz boolean[7][24]) ----------

    /**
     * Indica si una hora del aula está libre.
     *
     * @return true si está libre; false si está ocupada
     */
    public boolean consultarDisponibilidad(int dia, int hora) {
        validarDia(dia);
        validarHora(hora);
        return !horario[dia][hora];
    }

    /**
     * Reserva {@code duracion} horas seguidas a partir de la hora indicada.
     * Es "todo o nada": si alguna hora está ocupada, no se reserva ninguna.
     *
     * @throws HorarioConflictivoException si alguna de las horas ya está reservada
     */
    public void reservar(int dia, int hora, int duracion) throws HorarioConflictivoException {
        validarRango(dia, hora, duracion);

        // 1. Verificar: todas las horas deben estar libres
        for (int h = hora; h < hora + duracion; h++) {
            if (horario[dia][h]) {
                throw new HorarioConflictivoException(
                        NOMBRES_DIAS[dia] + " " + h + ":00 ya esta reservado");
            }
        }
        // 2. Reservar: solo si la verificación pasó completa
        for (int h = hora; h < hora + duracion; h++) {
            horario[dia][h] = true;
        }
    }

    /**
     * Libera {@code duracion} horas seguidas a partir de la hora indicada.
     * Todas deben estar reservadas; si alguna no lo está, no se libera ninguna.
     */
    public void liberar(int dia, int hora, int duracion) {
        validarRango(dia, hora, duracion);

        for (int h = hora; h < hora + duracion; h++) {
            if (!horario[dia][h]) {
                throw new IllegalStateException(
                        NOMBRES_DIAS[dia] + " " + h + ":00 no esta reservado.");
            }
        }
        for (int h = hora; h < hora + duracion; h++) {
            horario[dia][h] = false;
        }
    }

    /** Imprime las horas ocupadas del aula, agrupadas por día. */
    public void mostrarOcupacion() {
        StringBuilder detalle = new StringBuilder();
        for (int dia = 0; dia < DIAS; dia++) {
            StringBuilder horas = new StringBuilder();
            for (int hora = 0; hora < HORAS; hora++) {
                if (horario[dia][hora]) {
                    if (horas.length() > 0) {
                        horas.append(", ");
                    }
                    horas.append(hora).append(":00");
                }
            }
            if (horas.length() > 0) {
                detalle.append(NOMBRES_DIAS[dia]).append(": ").append(horas).append("\n");
            }
        }

        if (detalle.length() == 0) {
            System.out.println("Aula " + nombre + " - sin reservas.");
        } else {
            System.out.println("Aula " + nombre + " - horas ocupadas:");
            System.out.print(detalle);
        }
    }

    // ---------- Texto ----------

    @Override
    public String toString() {
        return "Aula " + nombre + " (capacidad " + capacidad + ")";
    }

    // ---------- Validaciones internas ----------

    private static void validarDia(int dia) {
        if (dia < 0 || dia >= DIAS) {
            throw new IllegalArgumentException(
                    "El dia debe estar entre 0 (Domingo) y 6 (Sabado).");
        }
    }

    private static void validarHora(int hora) {
        if (hora < 0 || hora >= HORAS) {
            throw new IllegalArgumentException("La hora debe estar entre 0 y 23.");
        }
    }

    private static void validarRango(int dia, int hora, int duracion) {
        validarDia(dia);
        validarHora(hora);
        if (duracion < 1) {
            throw new IllegalArgumentException("La duracion debe ser de al menos 1 hora.");
        }
        if (hora + duracion > HORAS) {
            throw new IllegalArgumentException("La reserva debe terminar antes de las 24:00.");
        }
    }
}