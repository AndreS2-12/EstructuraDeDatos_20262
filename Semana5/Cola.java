package Semana5;

import java.util.*;
public class Cola { 
    public static void main(String[] args) {

        //Creación de la cola
        Queue<String> cola = new LinkedList<>();

        //Agregar elementos a la cola
        cola.add("Pedro");
        cola.add("Juan");
        cola.add("María");
        cola.offer("Miguel");
        cola.offer("Daniel");

        //Mostrar los elementos de la cola
        System.out.println(cola); //[Pedro, Juan, Mar�a, Miguel, Daniel]

        //Mostrar quién está en la cabeza de la cola
        System.out.println(cola.peek()); //Pedro
        System.out.println(cola.element()); //Pedro
        System.out.println("---------------------------");

        //Eliminar dos elementos de la cola
        System.out.println(cola.remove()); //Pedro
        System.out.println(cola.poll()); //Juan

        //Mostrar los elementos de la cola
        System.out.println(cola); //[Mar�a, Miguel, Daniel]

        //Validar si la cola está vacía
        System.out.println(cola.isEmpty()); //false

        //Validar si un elemento está dentro de la cola
        System.out.println(cola.contains("Miguel")); //true
        System.out.println(cola.contains("Pedro")); //false

    }
}