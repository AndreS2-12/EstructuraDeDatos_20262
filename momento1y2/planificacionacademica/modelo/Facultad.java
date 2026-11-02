package momento1y2.planificacionacademica.modelo;

/**
 * Facultad de la universidad.
 * Se almacena en un arreglo fijo de 5 posiciones (Facultad[5]).
 */
public class Facultad {

    private final String codigo;
    private String nombre;

    /**
     * Crea una facultad validando sus datos.
     *
     * @param codigo código único, por ejemplo ING (no cambia después de creada)
     * @param nombre nombre completo de la facultad
     */
    public Facultad(String codigo, String nombre) {
        this.codigo = validarTexto(codigo, "El código").toUpperCase();
        setNombre(nombre);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = validarTexto(nombre, "El nombre");
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre;
    }

    private String validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío.");
        }
        return valor.trim();
    }
}