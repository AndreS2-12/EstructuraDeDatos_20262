
package momento1y2.planificacionacademica.estructuras;

import momento1y2.planificacionacademica.modelo.Aula;
import momento1y2.planificacionacademica.excepciones.HorarioConflictivoException;

public class GestorHorarios {

    private GestorAulas gestorAulas;

    // Constructor
    public GestorHorarios(GestorAulas gestorAulas) {

        if (gestorAulas == null) {
            throw new IllegalArgumentException(
                    "El gestor de aulas no puede ser nulo."
            );
        }

        this.gestorAulas = gestorAulas;
    }

    // Reservar un horario para un aula
    public void reservarHorario(
            String codigoAula,
            int dia,
            int hora) throws HorarioConflictivoException {

        Aula aula = gestorAulas.buscarAula(codigoAula);

        if (aula == null) {
            throw new IllegalArgumentException(
                    "No se encontró el aula indicada."
            );
        }

        // Verificar que el horario esté disponible
        if (!aula.estaDisponible(dia, hora)) {
            throw new HorarioConflictivoException(
                    "El aula ya está ocupada en ese horario."
            );
        }

        // Reservar el horario
        aula.reservarHorario(dia, hora);
    }

    // Consultar disponibilidad de un aula
    public boolean consultarDisponibilidad(
            String codigoAula,
            int dia,
            int hora) {

        Aula aula = gestorAulas.buscarAula(codigoAula);

        if (aula == null) {
            throw new IllegalArgumentException(
                    "No se encontró el aula indicada."
            );
        }

        return aula.estaDisponible(dia, hora);
    }

    // Liberar un horario de un aula
    public void liberarHorario(
            String codigoAula,
            int dia,
            int hora) {

        Aula aula = gestorAulas.buscarAula(codigoAula);

        if (aula == null) {
            throw new IllegalArgumentException(
                    "No se encontró el aula indicada."
            );
        }

        aula.liberarHorario(dia, hora);
    }
}