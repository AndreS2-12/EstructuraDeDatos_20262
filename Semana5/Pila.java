package Semana5;
import java.util.*;

public class Pila {

    public static void main(String[] args) {
        
        //Creación de la Pila de Enteros
        Stack<Integer> pila = new Stack<>();
        
        //Validar si la pila está vacía
        System.out.println(pila.empty()); //true
        
        //Agregar elementos a la pila
        pila.push(5);
        pila.push(8);
        pila.push(10);
        pila.push(2);
        pila.push(20);
        pila.push(15);
        pila.push(1);
        
        //Imprimir la pila
        System.out.println(pila); //[5, 8, 10, 2, 20, 15, 1]
        
        //Validar si la pila está vacía
        System.out.println(pila.empty()); //false
        
        //Mostrar el tope de la pila sin remover el elemento
        System.out.println("Tope de la pila: " + pila.peek());
        
        //Buscar un elemento dentro de la pila
        System.out.println("pos: " + pila.search(10));
        
        //Eliminar dos elementos de la pila
        pila.pop(); //1
        pila.pop(); //15
        
        //Imprimir la pila
        System.out.println(pila); //[5, 8, 10, 2, 20]

        //Obtener el tamaño de la pila
        System.out.println("Tamaño de la pila: " + pila.size());

        
    }   
}
