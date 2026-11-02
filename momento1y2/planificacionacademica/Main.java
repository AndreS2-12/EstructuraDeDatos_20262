package momento1y2.planificacionacademica;

import java.util.Locale;
import java.util.NoSuchElementException;

import momento1y2.planificacionacademica.estructuras.ListaEnlazada;
import momento1y2.planificacionacademica.estructuras.Pila;
import momento1y2.planificacionacademica.modelo.Estudiante;
import momento1y2.planificacionacademica.modelo.Facultad;
import momento1y2.planificacionacademica.modelo.Persona;
import momento1y2.planificacionacademica.modelo.Profesor;
import momento1y2.planificacionacademica.modelo.Materia;
import momento1y2.planificacionacademica.excepciones.*;
import momento1y2.planificacionacademica.excepciones.HorarioConflictivoException;
import momento1y2.planificacionacademica.modelo.Aula;

public class Main {

    // Intenta reservar y maneja el conflicto sin que el programa se caiga
    private static void intentarReservar(Aula aula, int dia, int hora, int duracion) {
        try {
            aula.reservar(dia, hora, duracion);
            System.out.println("Reserva exitosa.");
        } catch (HorarioConflictivoException e) {
            System.out.println("Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Aula aula = new Aula("101", 30);
        System.out.println(aula);

        System.out.println("--- Reservar Lunes 8:00 por 2 horas ---");
        for (int h = 8; h < 10; h++) {
            String estado = aula.consultarDisponibilidad(1, h) ? "LIBRE" : "OCUPADO";
            System.out.println(Aula.nombreDia(1) + " " + h + ":00 -> " + estado);
        }
        intentarReservar(aula, 1, 8, 2);

        System.out.println("--- Intento conflictivo: Lunes 9:00 por 2 horas ---");
        intentarReservar(aula, 1, 9, 2);
        System.out.println("¿Lunes 10:00 sigue libre? " + aula.consultarDisponibilidad(1, 10));

        System.out.println("--- Reservar Miercoles 14:00 por 3 horas ---");
        intentarReservar(aula, 3, 14, 3);
        aula.mostrarOcupacion();

        System.out.println("--- Liberar Lunes 8:00 por 2 horas ---");
        aula.liberar(1, 8, 2);
        aula.mostrarOcupacion();

        System.out.println("--- Reservar Lunes 9:00 de nuevo ---");
        intentarReservar(aula, 1, 9, 2);

        // Validaciones
        try {
            aula.consultarDisponibilidad(7, 8);
        } catch (IllegalArgumentException e) {
            System.out.println("Error controlado: " + e.getMessage());
        }
        try {
            aula.reservar(1, 23, 2);
        } catch (IllegalArgumentException | HorarioConflictivoException e) {
            System.out.println("Error controlado: " + e.getMessage());
        }
        try {
            aula.liberar(1, 12, 1);
        } catch (IllegalStateException e) {
            System.out.println("Error controlado: " + e.getMessage());
        }
    }
}