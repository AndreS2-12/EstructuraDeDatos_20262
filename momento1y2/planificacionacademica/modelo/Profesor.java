package momento1y2.planificacionacademica.modelo;

public class Profesor extends Persona {

    private String departamento;
    private double salario;

    public Profesor(String nombre, String id,
                String email, String departamento,
                double salario) {

        super(nombre, id, email);

        this.departamento = departamento;
        this.salario = salario;
    }

    @Override
    public void mostrarInformacion() {

        System.out.println("Nombre: " + getNombre());
        System.out.println("ID: " + getId());
        System.out.println("Email: " + getEmail());
        System.out.println("Departamento: " + departamento);
        System.out.println("Salario: " + salario);
    }
}