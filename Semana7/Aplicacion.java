package Semana7;

import java.util.*;

public class Aplicacion {
    public static void main(String[] args) {
        
        Map<Integer, String> estudiante = new HashMap<>();

        estudiante.put(856589, "Juan Perez");
        estudiante.put(547821, "Byron Cano");
        estudiante.put(698536, "Pedro Sanchez");

        System.out.println(estudiante.size()); //3
        System.out.println(estudiante.isEmpty()); //false
        System.out.println(estudiante); // {856589=Juan Perez, 547821=Byron Cano, 698536=Pedro Sanchez}
        System.out.println(estudiante.containsKey(547821)); //true
        System.out.println(estudiante.containsValue("Byron Cano")); //true
        System.out.println(estudiante.remove(547821));
        System.out.println(estudiante);
        System.out.println(estudiante.replace(856589, "Jhon Cano"));
        System.out.println(estudiante);

    }
}
