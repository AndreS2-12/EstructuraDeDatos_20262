package momento1y2.planificacionacademica.modelo;

/**
 * Clase base abstracta de las personas del sistema universitario.
 * Agrupa los atributos comunes de estudiantes y profesores.
 */
public abstract class Persona {

    private String nombre;
    private final String id;
    private String email;

    /**
     * Crea una persona validando sus datos.
     *
     * @param nombre nombre completo
     * @param id     identificador único (no cambia después de creado)
     * @param email  correo electrónico
     */
    public Persona(String nombre, String id, String email) {
        this.id = validarTexto(id, "El ID");
        setNombre(nombre);
        setEmail(email);
    }

    // ---------- Getters y setters ----------

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = validarTexto(nombre, "El nombre");
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = validarTexto(email, "El email");
    }

    // ---------- Comportamiento ----------

    /**
     * Muestra la información básica de la persona.
     * Las clases hijas la sobrescriben para agregar sus propios datos.
     */
    public void mostrarInformacion() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);
    }

    // ---------- Utilidad interna ----------

    private String validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío.");
        }
        return valor.trim();
    }
}