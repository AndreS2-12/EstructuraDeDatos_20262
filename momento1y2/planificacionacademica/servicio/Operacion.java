package momento1y2.planificacionacademica.servicio;
/**
 * Operación que se puede deshacer y rehacer.
 * Cada clase hija guarda lo necesario para revertirse (su estado anterior).
 */
public abstract class Operacion {

    /**
     * Ejecuta la operación. También sirve para rehacerla.
     *
     * @return mensaje que describe lo que se hizo
     */
    public abstract String ejecutar() throws Exception;

    /**
     * Revierte la operación, devolviendo el sistema al estado anterior.
     *
     * @return mensaje que describe lo que se revirtió
     */
    public abstract String deshacer() throws Exception;
}