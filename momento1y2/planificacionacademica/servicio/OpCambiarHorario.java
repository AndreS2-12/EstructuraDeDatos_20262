package momento1y2.planificacionacademica.servicio;
import momento1y2.planificacionacademica.excepciones.HorarioConflictivoException;
import momento1y2.planificacionacademica.modelo.Aula;
/**
 * Cambiar el horario de un aula: reservar o liberar horas.
 * Su inverso es la acción contraria sobre las mismas horas.
 */
public class OpCambiarHorario extends Operacion {

    private final Aula aula;
    private final int dia;
    private final int hora;
    private final int duracion;
    private final boolean esReserva;     // true = reservar, false = liberar

    public OpCambiarHorario(Aula aula, int dia, int hora, int duracion, boolean esReserva) {
        if (aula == null) {
            throw new IllegalArgumentException("El aula es obligatoria.");
        }
        this.aula = aula;
        this.dia = dia;
        this.hora = hora;
        this.duracion = duracion;
        this.esReserva = esReserva;
    }

    @Override
    public String ejecutar() throws HorarioConflictivoException {
        aplicar(esReserva);
        return descripcion();
    }

    @Override
    public String deshacer() throws HorarioConflictivoException {
        aplicar(!esReserva);             // la acción contraria
        return descripcion() + " revertida";
    }

    private void aplicar(boolean reservar) throws HorarioConflictivoException {
        if (reservar) {
            aula.reservar(dia, hora, duracion);
        } else {
            aula.liberar(dia, hora, duracion);
        }
    }

    private String descripcion() {
        return (esReserva ? "Reserva" : "Liberacion") + " en Aula " + aula.getNombre()
                + ": " + Aula.nombreDia(dia) + " " + hora + ":00 (" + duracion + " h)";
    }
}