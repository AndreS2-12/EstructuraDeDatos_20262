
package momento1y2.planificacionacademica.modelo;

import java.time.LocalDateTime;
import java.util.UUID;

public class Inscripcion {

    private String id;
    private String estudianteId;
    private String codigoMateria;
    private LocalDateTime fechaInscripcion;
    private int semestre;
    private EstadoInscripcion estado;
    private Double notaFinal;

    // Constructor
    public Inscripcion(String estudianteId,
                       String codigoMateria,
                       int semestre) {

        this.id = UUID.randomUUID().toString();
        this.estudianteId = estudianteId;
        this.codigoMateria = codigoMateria;
        this.semestre = semestre;
        this.fechaInscripcion = LocalDateTime.now();
        this.estado = EstadoInscripcion.INSCRITA;
        this.notaFinal = null;
    }

    // Obtener identificador
    public String getId() {
        return id;
    }

    // Obtener ID del estudiante
    public String getEstudianteId() {
        return estudianteId;
    }

    // Obtener código de la materia
    public String getCodigoMateria() {
        return codigoMateria;
    }

    // Obtener fecha de inscripción
    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    // Obtener semestre
    public int getSemestre() {
        return semestre;
    }

    // Obtener estado de inscripción
    public EstadoInscripcion getEstado() {
        return estado;
    }

    // Obtener nota final
    public Double getNotaFinal() {
        return notaFinal;
    }

    // Modificar estado
    public void setEstado(EstadoInscripcion estado) {
        this.estado = estado;
    }

    // Modificar nota final
    public void setNotaFinal(Double notaFinal) {
        this.notaFinal = notaFinal;
    }
}