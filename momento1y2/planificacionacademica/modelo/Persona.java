package momento1y2.planificacionacademica.modelo;

public abstract class Persona {

    private String nombre;
    private String id;
    private String email;

    public Persona(String nombre, String id, String email) {
        this.nombre = nombre;
        this.id = id;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract void mostrarInformacion();
}