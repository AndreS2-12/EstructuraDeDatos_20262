package momento1y2.planificacionacademica.modelo;

/**
 * Edificio del campus universitario.
 * Es un nodo del grafo de rutas (ver GrafoCampus).
 */
public class Edificio {

    private final String nombre;

    /**
     * Crea un edificio validando su nombre.
     *
     * @param nombre nombre del edificio, por ejemplo Ingenieria (no cambia)
     */
    public Edificio(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del edificio no puede estar vacío.");
        }
        this.nombre = nombre.trim();
    }

    public String getNombre() {
        return nombre;
    }

    /**
     * Compara el nombre del edificio con otro texto, sin importar
     * mayúsculas ni espacios al inicio o al final.
     */
    public boolean tieneNombre(String otroNombre) {
        return otroNombre != null && nombre.equalsIgnoreCase(otroNombre.trim());
    }

    @Override
    public String toString() {
        return nombre;
    }
}