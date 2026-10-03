
package Semana4;
public class Main {
    public static void main(String[] args) {
        
        //Creación de un objeto trabajador
        Trabajador objT1 = new Trabajador(1,"Juan","Peréz", 20, 1000.0);
        Trabajador objT2 = new Trabajador(2,"Lina","Cuero", 21, 1500.0);
        Trabajador objT3 = new Trabajador(3,"Git","Hub", 30, 800.0);
        
        System.out.println(objT1);
        System.out.println(objT2);
        System.out.println(objT1.getNombre()); //Juan
        
        //Arreglo de objetos
        Trabajador[] t = new Trabajador[3];
        t[0] = objT1;
        t[1] = objT2;
        t[2] = objT3;
        
        double totalSalarios = objT1.calcularSalarios(t);
        System.out.println("Suma de los salarios es: " + totalSalarios);

        //Creación de los objetos operario y vendedor
        Trabajador objOperario1 = new Operario(101,"x","y",25,15000.0,40);
        Trabajador objVendedor1 = new Vendedor(256,"z","w",24,100000.0,20);
        Trabajador objVendedor2 = new Vendedor(200,"a","b",30,200000.0,30);
        
        //System.out.println("pago total: " + objOperario1.pagar());
        //System.out.println("pago total: " + objVendedor1.pagar());
        
        //Creación de un nuevo arreglo de trabajadores
        Trabajador[] e = new Trabajador[3];
        e[0] = objOperario1;
        e[1] = objVendedor1;
        e[2] = objVendedor2;
        
        for(int i = 0; i < e.length; i++){
            System.out.println("Salario mes: " + e[i].pagar());
        }
    }
}
