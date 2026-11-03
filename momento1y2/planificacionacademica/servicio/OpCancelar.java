package momento1y2.planificacionacademica.servicio;
import java.util.ArrayList;
import java.util.List;
import momento1y2.planificacionacademica.modelo.Estudiante;
import momento1y2.planificacionacademica.modelo.Materia;
/**
 * Cancelar la inscripción de un estudiante en una materia.
 * Guarda a quién se le asignó el cupo liberado (su estado anterior)
 * para poder devolver todo exactamente como estaba.
 */
public class OpCancelar extends Operacion {

    private final GestorMaterias gestor;
    private final Estudiante estudiante;
    private final String codigoMateria;

    private Estudiante promovido = null;   // estado anterior: quién recibió el cupo
    private boolean ejecutada = false;

    public OpCancelar(GestorMaterias gestor, Estudiante estudiante, String codigoMateria) {
        if (gestor == null || estudiante == null
                || codigoMateria == null || codigoMateria.trim().isEmpty()) {
            throw new IllegalArgumentException("Todos los datos son obligatorios.");
        }
        this.gestor = gestor;
        this.estudiante = estudiante;
        this.codigoMateria = codigoMateria.trim().toUpperCase();
    }

    @Override
    public String ejecutar() {
        promovido = gestor.cancelarInscripcion(estudiante, codigoMateria);
        ejecutada = true;
        String mensaje = "Cancelacion de " + estudiante.getNombre() + " en " + codigoMateria;
        if (promovido != null) {
            return mensaje + ". Cupo asignado a " + promovido.getNombre();
        }
        return mensaje + ". Cupo liberado";
    }

    @Override
    public String deshacer() {
        if (!ejecutada) {
            throw new IllegalStateException("La operacion aun no se ha ejecutado.");
        }
        Materia materia = gestor.buscar(codigoMateria);

        if (promovido != null) {
            // 1. A quien recibió el cupo se le quita
            materia.eliminarInscrito(promovido);

            // 2. La cola solo permite agregar al final, así que se vacía,
            //    se pone primero a 'promovido' y se devuelven los demás en su orden
            List<Estudiante> siguientes = new ArrayList<>();
            while (materia.getCantidadEnEspera() > 0) {
                siguientes.add(materia.siguienteEnEspera());
            }
            materia.agregarAEspera(promovido);
            for (Estudiante e : siguientes) {
                materia.agregarAEspera(e);
            }
        }

        // 3. Quien canceló recupera su cupo
        materia.agregarInscrito(estudiante);

        String mensaje = "Cancelacion de " + estudiante.getNombre()
                + " en " + codigoMateria + " revertida";
        if (promovido != null) {
            mensaje += ". " + promovido.getNombre() + " vuelve al frente de la cola";
        }
        return mensaje;
    }
}