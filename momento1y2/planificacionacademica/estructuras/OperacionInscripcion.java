
package momento1y2.planificacionacademica.estructuras;

import momento1y2.planificacionacademica.servicio.InscripcionService;
import momento1y2.planificacionacademica.excepciones.EstudianteNoEncontradoException;
import momento1y2.planificacionacademica.excepciones.PreRequisitoNoAprobadoException;

public class OperacionInscripcion implements Operacion {

    private InscripcionService servicio;
    private String idEstudiante;
    private String codigoMateria;

    public OperacionInscripcion(
            InscripcionService servicio,
            String idEstudiante,
            String codigoMateria) {

        this.servicio = servicio;
        this.idEstudiante = idEstudiante;
        this.codigoMateria = codigoMateria;
    }

    @Override
    public void ejecutar() {

        try {
            servicio.inscribirEstudiante(
                    idEstudiante,
                    codigoMateria
            );

        } catch (EstudianteNoEncontradoException
                | PreRequisitoNoAprobadoException e) {

            throw new IllegalStateException(
                    "No se pudo realizar la inscripción: "
                    + e.getMessage(),
                    e
            );
        }
    }

    @Override
    public void deshacer() {

        servicio.cancelarInscripcion(
                idEstudiante,
                codigoMateria
        );
    }
}