package Semana4;

class Trabajador {

    //Atributos
    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private double salarioBase;

    //Constructor
    public Trabajador(int id, String nombre, String apellido, int edad, double salarioBase) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.salarioBase = salarioBase;
    }

    public int getId(){
        return id;
    }
    
    public String getNombre(){
        return nombre;
    }

    public String getApellido(){
        return apellido;
    }
    
    public int getEdad(){
        return edad;
    }
    
    public double getSalarioBase(){
        return salarioBase;
    }
    
    //Método toString
    @Override
    public String toString() {
        return "Trabajador{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", salario=" + salarioBase + '}';
    }
    
    //Método que permite calcular el total de los salarios
    //de todos los trabajadores
    public double calcularSalarios(Trabajador[] t){
        double sumaSalario = 0.0;
        for(int i = 0; i < t.length; i++){
            sumaSalario += t[i].getSalarioBase();
        }
        return sumaSalario;
    }
    
    //Método que permite calcular el promedio de las edades
    //de todos los trabajadores
    public double promedioEdades(Trabajador[] t){
        int sumaEdades = 0;
        for(int i = 0; i < t.length; i++){
            sumaEdades += t[i].getEdad();
        }
        double promEdad = sumaEdades / t.length;
        return promEdad;
    }
    
    public double pagar(){
        return 0.0;

    }  
}
