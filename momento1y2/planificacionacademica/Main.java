
package momento1y2.planificacionacademica;

import momento1y2.planificacionacademica.modelo.Estudiante;
import momento1y2.planificacionacademica.modelo.Materia;

import momento1y2.planificacionacademica.estructuras.GestorEstudiantes;
import momento1y2.planificacionacademica.estructuras.GestorMaterias;
import momento1y2.planificacionacademica.estructuras.GestorDeshacerRehacer;
import momento1y2.planificacionacademica.estructuras.OperacionInscripcion;

import momento1y2.planificacionacademica.servicio.InscripcionService;

public class Main {

    public static void main(String[] args) {

        // 1. Crear los gestores
        GestorEstudiantes gestorEstudiantes =
                new GestorEstudiantes();

        GestorMaterias gestorMaterias =
                new GestorMaterias();

        // 2. Crear un estudiante
        Estudiante estudiante = new Estudiante(
                "Ana Gómez",
                "12345",
                "ana@gmail.com",
                2
        );

        // 3. Crear una materia
        Materia materia = new Materia(
                "PROG101",
                "Programación I",
                4,
                2
        );

        // 4. Registrar estudiante y materia
        gestorEstudiantes.registrarEstudiante(estudiante);
        gestorMaterias.registrarMateria(materia);

        // 5. Crear el servicio de inscripciones
        InscripcionService servicioInscripcion =
                new InscripcionService(
                        gestorEstudiantes,
                        gestorMaterias
                );

        // 6. Crear el gestor de deshacer y rehacer
        GestorDeshacerRehacer gestorOperaciones =
                new GestorDeshacerRehacer();

        System.out.println("===== SISTEMA ACADÉMICO =====");

        // 7. Mostrar información del estudiante
        System.out.println("\n--- ESTUDIANTE ---");
        estudiante.mostrarInformacion();

        // 8. Mostrar información de la materia
        System.out.println("\n--- MATERIA ---");
        System.out.println("Nombre: " + materia.getNombre());
        System.out.println("Código: " + materia.getCodigo());
        System.out.println(
                "Cupos disponibles: "
                + materia.getCuposDisponibles()
        );

        // 9. Crear operación de inscripción
        System.out.println("\n--- REALIZAR INSCRIPCIÓN ---");

        OperacionInscripcion operacion =
                new OperacionInscripcion(
                        servicioInscripcion,
                        "12345",
                        "PROG101"
                );

        gestorOperaciones.ejecutarOperacion(operacion);

        // 10. Consultar inscripciones
        System.out.println("\n--- LISTADO DE INSCRIPCIONES ---");

        servicioInscripcion.listarInscripciones().forEach(inscripcion -> {
            System.out.println(
                    "Estudiante: " + inscripcion.getEstudianteId()
            );
            System.out.println(
                    "Materia: " + inscripcion.getCodigoMateria()
            );
            System.out.println(
                    "Estado: " + inscripcion.getEstado()
            );
            System.out.println("---------------------------");
        });

        // 11. Deshacer inscripción
        System.out.println("\n--- DESHACER OPERACIÓN ---");

        gestorOperaciones.deshacer();

        // 12. Consultar nuevamente
        System.out.println("\n--- INSCRIPCIONES DESPUÉS DE DESHACER ---");

        servicioInscripcion.listarInscripciones().forEach(inscripcion -> {
            System.out.println(
                    "Estado: " + inscripcion.getEstado()
            );
        });

        // 13. Rehacer inscripción
        System.out.println("\n--- REHACER OPERACIÓN ---");

        gestorOperaciones.rehacer();

        // 14. Consultar resultado final
        System.out.println("\n--- ESTADO FINAL ---");

        servicioInscripcion.listarInscripciones().forEach(inscripcion -> {
            System.out.println(
                    "Estudiante: " + inscripcion.getEstudianteId()
            );
            System.out.println(
                    "Materia: " + inscripcion.getCodigoMateria()
            );
            System.out.println(
                    "Estado: " + inscripcion.getEstado()
            );
        });

        System.out.println("\n===== FIN DE LA PRUEBA =====");
    }
}
