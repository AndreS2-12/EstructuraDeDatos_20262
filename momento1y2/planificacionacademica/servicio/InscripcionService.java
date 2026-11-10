
package momento1y2.planificacionacademica.servicio;

import java.util.LinkedList;

import momento1y2.planificacionacademica.modelo.Estudiante;
import momento1y2.planificacionacademica.modelo.Materia;
import momento1y2.planificacionacademica.modelo.Inscripcion;
import momento1y2.planificacionacademica.modelo.EstadoInscripcion;

import momento1y2.planificacionacademica.excepciones.EstudianteNoEncontradoException;
import momento1y2.planificacionacademica.excepciones.PreRequisitoNoAprobadoException;

import momento1y2.planificacionacademica.estructuras.GestorEstudiantes;
import momento1y2.planificacionacademica.estructuras.GestorMaterias;

public class InscripcionService {

    private GestorEstudiantes gestorEstudiantes;
    private GestorMaterias gestorMaterias;

    private LinkedList<Inscripcion> inscripciones;

    public InscripcionService(
            GestorEstudiantes gestorEstudiantes,
            GestorMaterias gestorMaterias) {

        this.gestorEstudiantes = gestorEstudiantes;
        this.gestorMaterias = gestorMaterias;
        this.inscripciones = new LinkedList<>();
    }

    // Registrar una inscripción
    public void inscribirEstudiante(
            String idEstudiante,
            String codigoMateria
    ) throws EstudianteNoEncontradoException,
             PreRequisitoNoAprobadoException {

        Estudiante estudiante =
                gestorEstudiantes.buscarEstudiante(idEstudiante);

        Materia materia =
                gestorMaterias.buscarMateria(codigoMateria);

        // Validar que exista el estudiante
        if (estudiante == null) {
            throw new EstudianteNoEncontradoException(
                    "No existe un estudiante con ID: " + idEstudiante
            );
        }

        // Validar que exista la materia
        if (materia == null) {
            System.out.println("La materia no existe.");
            return;
        }

        // Validar que no esté inscrito o esperando
        for (Inscripcion inscripcion : inscripciones) {

            boolean mismoEstudiante =
                    inscripcion.getEstudianteId().equals(idEstudiante);

            boolean mismaMateria =
                    inscripcion.getCodigoMateria().equals(codigoMateria);

            boolean activa =
                    inscripcion.getEstado() == EstadoInscripcion.INSCRITA
                    || inscripcion.getEstado() == EstadoInscripcion.EN_ESPERA;

            if (mismoEstudiante && mismaMateria && activa) {
                System.out.println(
                        "El estudiante ya está inscrito o en espera."
                );
                return;
            }
        }

        // Validar prerrequisitos
        for (String prerrequisito : materia.getPrerrequisitos()) {

            if (!estudiante.getHistorialMaterias().contains(prerrequisito)) {

                throw new PreRequisitoNoAprobadoException(
                        "El estudiante no ha aprobado el prerrequisito: "
                        + prerrequisito
                );
            }
        }

        // Crear la inscripción
        Inscripcion nuevaInscripcion = new Inscripcion(
                idEstudiante,
                codigoMateria,
                estudiante.getSemestre()
        );

        // Verificar disponibilidad de cupos
        if (materia.getCuposDisponibles() > 0) {

            materia.getEstudiantesInscritos().add(idEstudiante);

            nuevaInscripcion.setEstado(EstadoInscripcion.INSCRITA);

            System.out.println(
                    "Inscripción realizada correctamente."
            );

        } else {

            materia.getColaEspera().add(idEstudiante);

            nuevaInscripcion.setEstado(EstadoInscripcion.EN_ESPERA);

            System.out.println(
                    "No hay cupos. Estudiante agregado a la lista de espera."
            );
        }

        inscripciones.add(nuevaInscripcion);
    }

    // Cancelar una inscripción
    public void cancelarInscripcion(
            String idEstudiante,
            String codigoMateria
    ) {

        Materia materia =
                gestorMaterias.buscarMateria(codigoMateria);

        if (materia == null) {
            System.out.println("La materia no existe.");
            return;
        }

        Inscripcion inscripcionEncontrada = null;

        // Buscar la inscripción activa
        for (Inscripcion inscripcion : inscripciones) {

            boolean mismoEstudiante =
                    inscripcion.getEstudianteId().equals(idEstudiante);

            boolean mismaMateria =
                    inscripcion.getCodigoMateria().equals(codigoMateria);

            boolean activa =
                    inscripcion.getEstado() == EstadoInscripcion.INSCRITA
                    || inscripcion.getEstado() == EstadoInscripcion.EN_ESPERA;

            if (mismoEstudiante && mismaMateria && activa) {
                inscripcionEncontrada = inscripcion;
                break;
            }
        }

        if (inscripcionEncontrada == null) {
            System.out.println(
                    "No se encontró una inscripción activa."
            );
            return;
        }

        // Si estaba en lista de espera
        if (inscripcionEncontrada.getEstado() == EstadoInscripcion.EN_ESPERA) {

            materia.getColaEspera().remove(idEstudiante);

            inscripcionEncontrada.setEstado(
                    EstadoInscripcion.CANCELADA
            );

            System.out.println("Inscripción en espera cancelada.");
            return;
        }

        // Si estaba inscrito, liberar el cupo
        materia.getEstudiantesInscritos().remove(idEstudiante);

        inscripcionEncontrada.setEstado(
                EstadoInscripcion.CANCELADA
        );

        System.out.println("Inscripción cancelada correctamente.");

        // Asignar el cupo al primer estudiante en espera
        while (!materia.getColaEspera().isEmpty()) {

            String siguienteEstudiante =
                    materia.getColaEspera().getFirst();

            Inscripcion siguienteInscripcion = null;

            // Buscar una inscripción válida del siguiente estudiante
            for (Inscripcion inscripcion : inscripciones) {

                boolean mismoEstudiante =
                        inscripcion.getEstudianteId().equals(siguienteEstudiante);

                boolean mismaMateria =
                        inscripcion.getCodigoMateria().equals(codigoMateria);

                boolean enEspera =
                        inscripcion.getEstado() == EstadoInscripcion.EN_ESPERA;

                if (mismoEstudiante && mismaMateria && enEspera) {
                    siguienteInscripcion = inscripcion;
                    break;
                }
            }

            // Retirar al estudiante de la cola
            materia.getColaEspera().removeFirst();

            // Asignar el cupo si su inscripción sigue vigente
            if (siguienteInscripcion != null) {

                siguienteInscripcion.setEstado(
                        EstadoInscripcion.INSCRITA
                );

                materia.getEstudiantesInscritos().add(
                        siguienteEstudiante
                );

                System.out.println(
                        "El estudiante " + siguienteEstudiante
                        + " obtuvo el cupo disponible."
                );

                break;
            }
        }
    }

    // Consultar todas las inscripciones
    public LinkedList<Inscripcion> listarInscripciones() {
        return new LinkedList<>(inscripciones);
    }
}
