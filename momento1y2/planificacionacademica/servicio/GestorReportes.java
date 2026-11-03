package momento1y2.planificacionacademica.servicio;
import java.util.Locale;
import momento1y2.planificacionacademica.estructuras.Pila;
import momento1y2.planificacionacademica.modelo.Estudiante;

/**
 * Genera los reportes académicos y administra la navegación entre
 * los reportes vistos, con dos pilas (atrás y adelante), como un navegador web.
 */
public class GestorReportes {

    private final Pila<Estudiante> pilaAtras = new Pila<>();
    private final Pila<Estudiante> pilaAdelante = new Pila<>();
    private Estudiante actual = null;

    // ---------- Generación de reportes ----------

    /** Reporte académico completo del estudiante (formato del Ejemplo 6). */
    public String generarReporte(Estudiante estudiante) {
        validar(estudiante);
        StringBuilder texto = new StringBuilder();
        texto.append("--- REPORTE ACADEMICO ---\n");
        texto.append("Estudiante: ").append(estudiante.getNombre())
                .append(" (ID: ").append(estudiante.getId()).append(")\n");

        boolean hayNotas = false;
        for (int semestre = 1; semestre <= Estudiante.MAX_SEMESTRES; semestre++) {
            String[] codigos = estudiante.getCodigosSemestre(semestre);
            if (codigos.length == 0) {
                continue;                       // semestre sin materias: se omite
            }
            hayNotas = true;
            texto.append("Semestre ").append(semestre).append(":\n");
            for (String codigo : codigos) {
                texto.append("  ").append(codigo).append(": ")
                        .append(estudiante.getNota(semestre, codigo)).append("\n");
            }
            texto.append(String.format(Locale.US, "  Promedio: %.2f\n",
                    estudiante.calcularPromedioSemestre(semestre)));
        }

        if (!hayNotas) {
            texto.append("Sin notas registradas.\n");
        }
        texto.append("=== RESUMEN ===\n");
        texto.append(String.format(Locale.US, "Promedio acumulado: %.2f\n",
                estudiante.calcularPromedioAcumulado()));
        texto.append("Materias aprobadas: ").append(estudiante.contarAprobadas()).append("\n");
        texto.append("Materias reprobadas: ").append(estudiante.contarReprobadas());
        return texto.toString();
    }

    /** Reporte de las materias con nota menor a 3.0. */
    public String generarReporteReprobadas(Estudiante estudiante) {
        validar(estudiante);
        StringBuilder texto = new StringBuilder();
        texto.append("--- MATERIAS REPROBADAS (nota < 3.0) ---\n");
        texto.append("Estudiante: ").append(estudiante.getNombre())
                .append(" (ID: ").append(estudiante.getId()).append(")\n");

        int total = 0;
        for (int semestre = 1; semestre <= Estudiante.MAX_SEMESTRES; semestre++) {
            for (String codigo : estudiante.getCodigosSemestre(semestre)) {
                Double nota = estudiante.getNota(semestre, codigo);
                if (nota != null && nota < Estudiante.NOTA_APROBATORIA) {
                    texto.append("Semestre ").append(semestre).append(" - ")
                            .append(codigo).append(": ").append(nota).append("\n");
                    total++;
                }
            }
        }

        if (total == 0) {
            texto.append("No tiene materias reprobadas.");
        } else {
            texto.append("Total reprobadas: ").append(total);
        }
        return texto.toString();
    }

    // ---------- Navegación entre reportes (pilas) ----------

    /**
     * Abre el reporte de un estudiante y lo registra en el historial de navegación.
     * Un reporte nuevo borra la pila de "adelante".
     */
    public String verReporte(Estudiante estudiante) {
        validar(estudiante);
        if (estudiante == actual) {
            return generarReporte(estudiante);  // ya se está viendo: no cambia la navegación
        }
        if (actual != null) {
            pilaAtras.apilar(actual);
        }
        actual = estudiante;
        pilaAdelante.limpiar();
        return generarReporte(actual);
    }

    /** Vuelve al reporte anterior. */
    public String irAtras() {
        if (pilaAtras.estaVacia()) {
            throw new IllegalStateException("No hay reportes anteriores.");
        }
        pilaAdelante.apilar(actual);
        actual = pilaAtras.desapilar();
        return generarReporte(actual);
    }

    /** Avanza al reporte siguiente (solo si antes se retrocedió). */
    public String irAdelante() {
        if (pilaAdelante.estaVacia()) {
            throw new IllegalStateException("No hay reportes siguientes.");
        }
        pilaAtras.apilar(actual);
        actual = pilaAdelante.desapilar();
        return generarReporte(actual);
    }

    // ---------- Utilidad interna ----------

    private void validar(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }
    }
}