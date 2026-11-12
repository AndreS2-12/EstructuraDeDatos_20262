
package momento1y2.planificacionacademica.servicio;

import java.util.LinkedList;

import momento1y2.planificacionacademica.modelo.Estudiante;
import momento1y2.planificacionacademica.modelo.Materia;
import momento1y2.planificacionacademica.modelo.Inscripcion;
import momento1y2.planificacionacademica.modelo.EstadoInscripcion;

import momento1y2.planificacionacademica.estructuras.GestorEstudiantes;
import momento1y2.planificacionacademica.estructuras.GestorMaterias;
import momento1y2.planificacionacademica.estructuras.GestorColaEspera;

import momento1y2.planificacionacademica.excepciones.EstudianteNoEncontradoException;
import momento1y2.planificacionacademica.excepciones.PreRequisitoNoAprobadoException;
import momento1y2.planificacionacademica.excepciones.ColaDeEsperaVaciaException;

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

        // Inscribir un estudiante en una materia
        public void inscribirEstudiante(
                        String idEstudiante,
                        String codigoMateria)
                        throws EstudianteNoEncontradoException,
                        PreRequisitoNoAprobadoException {

                Estudiante estudiante = gestorEstudiantes.buscarEstudiante(idEstudiante);

                if (estudiante == null) {
                        throw new EstudianteNoEncontradoException(
                                        "El estudiante no existe.");
                }

                Materia materia = gestorMaterias.buscarMateria(codigoMateria);

                if (materia == null) {
                        System.out.println("La materia no existe.");
                        return;
                }

                // Evitar inscripciones duplicadas
                for (Inscripcion inscripcion : inscripciones) {

                        if (inscripcion.getEstudianteId().equals(idEstudiante)
                                        && inscripcion.getCodigoMateria().equals(codigoMateria)
                                        && (inscripcion.getEstado() == EstadoInscripcion.INSCRITA
                                                        || inscripcion.getEstado() == EstadoInscripcion.EN_ESPERA)) {

                                System.out.println(
                                                "El estudiante ya tiene una inscripción activa.");
                                return;
                        }
                }

                // Validar prerrequisitos
                for (String prerrequisito : materia.getPrerrequisitos()) {

                        if (!estudiante.getHistorialMaterias().contains(prerrequisito)) {

                                throw new PreRequisitoNoAprobadoException(
                                                "El estudiante no ha aprobado el prerrequisito: "
                                                                + prerrequisito);
                        }
                }

                // Crear la inscripción
                // Se envían los tres argumentos que exige el constructor de Inscripcion
                Inscripcion nuevaInscripcion = new Inscripcion(
                                idEstudiante,
                                codigoMateria,
                                estudiante.getSemestre());

                // Crear gestor conectado a la cola de esta materia
                GestorColaEspera gestorCola = new GestorColaEspera(materia.getColaEspera());

                // Verificar disponibilidad de cupos
                if (materia.getCuposDisponibles() > 0) {

                        materia.getEstudiantesInscritos().add(idEstudiante);

                        nuevaInscripcion.setEstado(EstadoInscripcion.INSCRITA);

                        System.out.println(
                                        "Inscripción realizada correctamente.");

                } else {

                        gestorCola.agregarEstudiante(idEstudiante);
                        nuevaInscripcion.setEstado(EstadoInscripcion.EN_ESPERA);

                        System.out.println(
                                        "No hay cupos disponibles. "
                                                        + "Estudiante agregado a la lista de espera.");
                }

                inscripciones.add(nuevaInscripcion);
        }

        // Cancelar una inscripción
        public void cancelarInscripcion(
                        String idEstudiante,
                        String codigoMateria) {

                for (Inscripcion inscripcion : inscripciones) {

                        if (inscripcion.getEstudianteId().equals(idEstudiante)
                                        && inscripcion.getCodigoMateria().equals(codigoMateria)
                                        && (inscripcion.getEstado() == EstadoInscripcion.INSCRITA
                                                        || inscripcion.getEstado() == EstadoInscripcion.EN_ESPERA)) {

                                Materia materia = gestorMaterias.buscarMateria(codigoMateria);

                                if (materia == null) {
                                        System.out.println("La materia no existe.");
                                        return;
                                }

                                // Gestor conectado a la cola de la materia
                                GestorColaEspera gestorCola = new GestorColaEspera(materia.getColaEspera());

                                // Si el estudiante estaba en espera
                                if (inscripcion.getEstado() == EstadoInscripcion.EN_ESPERA) {

                                        gestorCola.eliminarEstudiante(idEstudiante);
                                        inscripcion.setEstado(EstadoInscripcion.CANCELADA);

                                        System.out.println(
                                                        "Inscripción en lista de espera cancelada.");

                                } else {

                                        // Si el estudiante estaba inscrito
                                        materia.getEstudiantesInscritos().remove(idEstudiante);
                                        inscripcion.setEstado(EstadoInscripcion.CANCELADA);

                                        System.out.println(
                                                        "Inscripción cancelada correctamente.");

                                        // Asignar el cupo al siguiente estudiante
                                        while (!gestorCola.estaVacia()) {

                                                String siguienteEstudiante;

                                                try {
                                                        siguienteEstudiante = gestorCola.atenderSiguiente();

                                                } catch (ColaDeEsperaVaciaException e) {
                                                        break;
                                                }

                                                Inscripcion siguienteInscripcion = null;

                                                for (Inscripcion i : inscripciones) {

                                                        if (i.getEstudianteId().equals(siguienteEstudiante)
                                                                        && i.getCodigoMateria().equals(codigoMateria)
                                                                        && i.getEstado() == EstadoInscripcion.EN_ESPERA) {

                                                                siguienteInscripcion = i;
                                                                break;
                                                        }
                                                }

                                                // Si la inscripción sigue siendo válida
                                                if (siguienteInscripcion != null) {

                                                        siguienteInscripcion.setEstado(
                                                                        EstadoInscripcion.INSCRITA);

                                                        materia.getEstudiantesInscritos()
                                                                        .add(siguienteEstudiante);

                                                        System.out.println(
                                                                        "Se asignó el cupo al estudiante: "
                                                                                        + siguienteEstudiante);

                                                        break;
                                                }
                                        }
                                }

                                return;
                        }
                }

                System.out.println(
                                "No se encontró una inscripción activa para cancelar.");
        }

        // Consultar todas las inscripciones
        public LinkedList<Inscripcion> listarInscripciones() {

                return new LinkedList<>(inscripciones);
        }
}