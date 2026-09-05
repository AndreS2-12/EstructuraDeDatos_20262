package Semana1;

public class Ejecutar {
    public static void main(String[] args) {
        System.out.println("Estructuras de Datos UCC!!!");

        // Delcaración del arreglo - array - vector (Forma 1)
        int[] a= {5, 16, 8, 9, 10, 25, 19, 3, 6, 22, 2}; 
        for (int i = 0; i < a.length; i++) {
            System.out.println("a[" + i + "] = " + a[i]);
        }

        // Sumar todos los elementos del arreglo a
        int suma = 0;
        for (int i = 0; i < a.length; i++) {
            suma += a[i];
        }
        System.out.println("La suma de los elementos del arreglo a es: " + suma);

        //Declaración del arreglo - array - vector (Forma 2)
        int[] b = new int[11]; // Declaración del arreglo b con tamaño 11
        int min = 1;
        int max = 30;
        for (int i = 0; i < b.length; i++) {
            b[i] = (int) (Math.random() * (max - min - 1)+ min); // Asignar valores al arreglo b
        }
        //Mostrar el arreglo b
        for (int i = 0; i < b.length; i++) {
            System.out.println("b[" + i + "] = " + b[i]);

            //Mostrar el arreglo b elevando al cuadrado cada elemento
            System.out.println("b[" + i + "] al cuadrado = " + (b[i] * b[i]));
        }   
    }
}
