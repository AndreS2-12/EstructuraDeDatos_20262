package momento1y2.planificacionacademica.modelo;
import java.util.Locale;

/**
 * Profesor de la universidad. Hereda de Persona y agrega
 * el departamento al que pertenece y su salario.
 */
public class Profesor extends Persona {

    private String departamento;
    private double salario;

    /**
     * Crea un profesor con salario.
     *
     * @param departamento departamento académico, por ejemplo Ingenieria
     * @param salario      salario mensual (no puede ser negativo)
     */
    public Profesor(String nombre, String id, String email,
                    String departamento, double salario) {
        super(nombre, id, email);
        setDepartamento(departamento);
        setSalario(salario);
    }

    /**
     * Crea un profesor sin salario definido (queda en 0.0).
     * Es una sobrecarga del constructor anterior.
     */
    public Profesor(String nombre, String id, String email, String departamento) {
        this(nombre, id, email, departamento, 0.0);
    }

    // ---------- Getters y setters ----------

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        if (departamento == null || departamento.trim().isEmpty()) {
            throw new IllegalArgumentException("El departamento no puede estar vacío.");
        }
        this.departamento = departamento.trim();
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        if (salario < 0) {
            throw new IllegalArgumentException("El salario no puede ser negativo.");
        }
        this.salario = salario;
    }

    // ---------- Polimorfismo ----------

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Departamento: " + departamento);
        System.out.printf(Locale.US, "Salario: %.2f%n", salario);
    }
}