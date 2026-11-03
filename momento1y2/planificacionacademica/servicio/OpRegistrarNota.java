package momento1y2.planificacionacademica.servicio;
import momento1y2.planificacionacademica.modelo.Estudiante;
/**
 * Registrar la nota de una materia. Guarda la nota anterior
 * (o su ausencia) para poder restaurarla al deshacer.
 */
public class OpRegistrarNota extends Operacion {

    private final Estudiante estudiante;
    private final int semestre;
    private final String codigoMateria;
    private final double nota;

    private Double notaAnterior = null;   // estado anterior; null = no tenía nota
    private boolean ejecutada = false;

    public OpRegistrarNota(Estudiante estudiante, int semestre, String codigoMateria, double nota) {
        if (estudiante == null || codigoMateria == null || codigoMateria.trim().isEmpty()) {
            throw new IllegalArgumentException("El estudiante y la materia son obligatorios.");
        }
        this.estudiante = estudiante;
        this.semestre = semestre;
        this.codigoMateria = codigoMateria.trim().toUpperCase();
        this.nota = nota;
    }

    @Override
    public String ejecutar() {
        Double anterior = estudiante.getNota(semestre, codigoMateria);   // se guarda antes de cambiar
        estudiante.registrarNota(semestre, codigoMateria, nota);         // si falla, no se guarda nada
        notaAnterior = anterior;
        ejecutada = true;
        return "Nota " + nota + " en " + codigoMateria + " (semestre " + semestre
                + ") de " + estudiante.getNombre();
    }

    @Override
    public String deshacer() {
        if (!ejecutada) {
            throw new IllegalStateException("La operacion aun no se ha ejecutado.");
        }
        if (notaAnterior == null) {
            estudiante.eliminarNota(semestre, codigoMateria);            // no tenía nota: se borra
        } else {
            estudiante.registrarNota(semestre, codigoMateria, notaAnterior);   // se restaura la anterior
        }
        return "Nota de " + codigoMateria + " (semestre " + semestre + ") de "
                + estudiante.getNombre() + " revertida: "
                + (notaAnterior == null ? "queda sin nota" : "vuelve a " + notaAnterior);
    }
}