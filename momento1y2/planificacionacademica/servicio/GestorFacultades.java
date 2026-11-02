package momento1y2.planificacionacademica.servicio;
import momento1y2.planificacionacademica.modelo.Facultad;

/**
 * Administra la lista fija de facultades con un arreglo Facultad[5].
 */
public class GestorFacultades {

    public static final int MAX_FACULTADES = 5;

    private final Facultad[] facultades = new Facultad[MAX_FACULTADES];
    private int cantidad = 0;

    /** Agrega una facultad en la primera posición libre del arreglo. */
    public void agregar(Facultad facultad) {
        if (facultad == null) {
            throw new IllegalArgumentException("La facultad no puede ser nula.");
        }
        if (buscarPorCodigo(facultad.getCodigo()) != null) {
            throw new IllegalArgumentException("Ya existe la facultad " + facultad.getCodigo() + ".");
        }
        if (cantidad == MAX_FACULTADES) {
            throw new IllegalStateException(
                    "Ya estan registradas las " + MAX_FACULTADES + " facultades.");
        }
        facultades[cantidad] = facultad;
        cantidad++;
    }

    /** Busca por código (sin importar mayúsculas). Devuelve null si no existe. */
    public Facultad buscarPorCodigo(String codigo) {
        if (codigo == null) {
            return null;
        }
        String buscado = codigo.trim();
        for (int i = 0; i < cantidad; i++) {
            if (facultades[i].getCodigo().equalsIgnoreCase(buscado)) {
                return facultades[i];
            }
        }
        return null;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void listar() {
        if (cantidad == 0) {
            System.out.println("No hay facultades registradas.");
            return;
        }
        System.out.println("Facultades registradas (" + cantidad + " de " + MAX_FACULTADES + "):");
        for (int i = 0; i < cantidad; i++) {
            System.out.println((i + 1) + ". " + facultades[i]);
        }
    }
}