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

    public String getId() {
        return id;
    }

    public String getEstudianteId() {
        return estudianteId;
    }

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public LocalDateTime getFechaInscripcion() {
        return fechaInscripcion;
    }

    public int getSemestre() {
        return semestre;
    }

    public EstadoInscripcion getEstado() {
        return estado;
    }

    public Double getNotaFinal() {
        return notaFinal;
    }

    public void setEstado(EstadoInscripcion estado) {
        this.estado = estado;
    }

    public void setNotaFinal(Double notaFinal) {
        this.notaFinal = notaFinal;
    }
}