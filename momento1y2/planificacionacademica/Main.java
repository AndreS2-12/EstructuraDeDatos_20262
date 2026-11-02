package momento1y2.planificacionacademica;

import java.util.Locale;
import java.util.NoSuchElementException;

import momento1y2.planificacionacademica.estructuras.ListaEnlazada;
import momento1y2.planificacionacademica.estructuras.Pila;
import momento1y2.planificacionacademica.modelo.Estudiante;
import momento1y2.planificacionacademica.modelo.Facultad;
import momento1y2.planificacionacademica.modelo.Persona;
import momento1y2.planificacionacademica.modelo.Profesor;

public class Main {

    public static void main(String[] args) {

        // ---------------- ESTUDIANTE ----------------

        Estudiante ana = new Estudiante(
                "Ana Maria Gomez",
                "2024001",
                "ana.gomez@universidad.edu",
                3);

        ana.mostrarInformacion();

        ana.registrarNota(1, "CALC101", 4.5);
        ana.registrarNota(1, "FIS101", 2.8);
        ana.registrarNota(1, "PROG101", 5.0);

        ana.registrarNota(2, "CALC102", 3.5);
        ana.registrarNota(2, "FIS102", 3.0);
        ana.registrarNota(2, "MATDIS", 2.5);

        System.out.println("\n--- CON NOTAS ---");

        System.out.printf(Locale.US,
                "Promedio semestre 1: %.2f%n",
                ana.calcularPromedioSemestre(1));

        System.out.printf(Locale.US,
                "Promedio semestre 2: %.2f%n",
                ana.calcularPromedioSemestre(2));

        System.out.printf(Locale.US,
                "Promedio acumulado: %.2f%n",
                ana.calcularPromedioAcumulado());

        System.out.println("Aprobadas: " + ana.contarAprobadas());
        System.out.println("Reprobadas: " + ana.contarReprobadas());

        // ---------------- POLIMORFISMO ----------------

        System.out.println("\n--- POLIMORFISMO ---");

        Persona p = ana;
        p.mostrarInformacion();

        try {
            ana.registrarNota(11, "X", 3.0);
        } catch (IllegalArgumentException e) {
            System.out.println("Error controlado: " + e.getMessage());
        }

        try {
            ana.registrarNota(1, "X", 5.5);
        } catch (IllegalArgumentException e) {
            System.out.println("Error controlado: " + e.getMessage());
        }

        // ---------------- LISTA ENLAZADA ----------------

        System.out.println("\n--- LISTA ENLAZADA ---");

        ListaEnlazada<String> lista = new ListaEnlazada<>();

        lista.agregarAlFinal("CALC101");
        lista.agregarAlFinal("FIS101");
        lista.agregarAlFinal("PROG101");

        System.out.println("Lista inicial: " + lista);
        System.out.println("Tamaño: " + lista.tamano());

        lista.agregarAlInicio("MAT100");

        System.out.println("Después de agregar al inicio: " + lista);

        System.out.println("¿Contiene FIS101? "
                + lista.contiene("FIS101"));

        System.out.println("¿Contiene QUI101? "
                + lista.contiene("QUI101"));

        System.out.println("Eliminar FIS101: "
                + lista.eliminar("FIS101"));

        System.out.println("Lista actual: " + lista);

        System.out.println("Recorrido con for-each:");

        for (String codigo : lista) {
            System.out.println(" - " + codigo);
        }

        System.out.println("Elemento índice 1: "
                + lista.obtener(1));

        // ---------------- PILAS ----------------

        System.out.println("\n--- PILAS ---");

        Pila<String> pila = new Pila<>();

        pila.apilar("A");
        pila.apilar("B");
        pila.apilar("C");

        System.out.println("Pila (tope primero): " + pila);
        System.out.println("Tope: " + pila.verTope());
        System.out.println("Tamaño: " + pila.tamano());

        System.out.println("Desapilar: " + pila.desapilar());
        System.out.println("Desapilar: " + pila.desapilar());

        System.out.println("Pila actual: " + pila);
        System.out.println("¿Vacía? " + pila.estaVacia());

        pila.desapilar();

        System.out.println("¿Vacía? " + pila.estaVacia());

        try {
            pila.desapilar();
        } catch (NoSuchElementException e) {
            System.out.println("Error controlado: " + e.getMessage());
        }

        // ---------------- DESHACER / REHACER ----------------

        System.out.println("\n--- SIMULACIÓN DESHACER/REHACER ---");

        Pila<String> deshacer = new Pila<>();
        Pila<String> rehacer = new Pila<>();

        deshacer.apilar("Inscribir Ana en CALC101");
        deshacer.apilar("Inscribir Ana en CALC102");

        System.out.println("Deshacer: " + deshacer);

        String operacion = deshacer.desapilar();
        rehacer.apilar(operacion);

        System.out.println("Deshecha: " + operacion);
        System.out.println("Deshacer: " + deshacer);
        System.out.println("Rehacer: " + rehacer);

        operacion = rehacer.desapilar();
        deshacer.apilar(operacion);

        System.out.println("Rehecha: " + operacion);
        System.out.println("Deshacer: " + deshacer);
        System.out.println("¿Rehacer vacía? " + rehacer.estaVacia());

        // ---------------- POLIMORFISMO CON PERSONAS ----------------

        System.out.println("\n--- POLIMORFISMO CON PERSONAS ---");

        Persona[] personas = new Persona[3];

        personas[0] = new Estudiante(
                "Ana Maria Gomez",
                "2024001",
                "ana.gomez@universidad.edu",
                3);

        personas[1] = new Profesor(
                "Carlos Perez",
                "P001",
                "carlos.perez@universidad.edu",
                "Ingenieria",
                4500000);

        personas[2] = new Profesor(
                "Laura Diaz",
                "P002",
                "laura.diaz@universidad.edu",
                "Ciencias Basicas");

        for (Persona persona : personas) {
            persona.mostrarInformacion();
            System.out.println("-----");
        }

        try {
            new Profesor(
                    "Pedro Ruiz",
                    "P003",
                    "pedro@universidad.edu",
                    "  ",
                    1000);
        } catch (IllegalArgumentException e) {
            System.out.println("Error controlado: " + e.getMessage());
        }

        try {
            new Profesor(
                    "Pedro Ruiz",
                    "P003",
                    "pedro@universidad.edu",
                    "Ingenieria",
                    -5);
        } catch (IllegalArgumentException e) {
            System.out.println("Error controlado: " + e.getMessage());
        }

        // ---------------- FACULTADES ----------------

        System.out.println("\n--- ARREGLO DE FACULTADES ---");

        Facultad[] facultades = new Facultad[5];

        facultades[0] = new Facultad("ING", "Ingenieria");
        facultades[1] = new Facultad("CIE", "Ciencias Basicas");
        facultades[2] = new Facultad("ADM", "Administracion");

        for (int i = 0; i < facultades.length; i++) {

            if (facultades[i] != null) {
                System.out.println((i + 1) + ". "
                        + facultades[i]);
            } else {
                System.out.println((i + 1) + ". (libre)");
            }
        }

        try {
            new Facultad("", "Sin codigo");
        } catch (IllegalArgumentException e) {
            System.out.println("Error controlado: "
                    + e.getMessage());
        }

        // ---------------- LISTA VACIA ----------------

        System.out.println("\n--- PRUEBA DE EXCEPCION ---");

        lista.limpiar();

        System.out.println("¿Lista vacía? "
                + lista.estaVacia());

        try {
            lista.eliminarPrimero();
        } catch (NoSuchElementException e) {
            System.out.println("Error controlado: "
                    + e.getMessage());
        }
    }
}