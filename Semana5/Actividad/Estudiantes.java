import java.util.*;
public class Estudiantes {

    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        // Creacion de los arreglos
        int[] codigo = new int[10];
        String[] nombre = new String[10];
        String[] programa = new String[10];
        double[] promedio = new double[10];

        int cantidad = 0;
        int opcion;

        do {
            System.out.println("\n--- GESTION DE ESTUDIANTES ---");
            System.out.println("1. Registrar estudiantes");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Buscar estudiante por codigo");
            System.out.println("4. Mostrar estudiantes con promedio >= 4.0");
            System.out.println("5. Mostrar estudiante con promedio mas alto");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {

                case 1:
                    if (cantidad < 10) {

                        System.out.print("Ingrese el codigo: ");
                        int nuevoCodigo = entrada.nextInt();
                        entrada.nextLine();

                        boolean existe = false;

                        for (int i = 0; i < cantidad; i++) {
                            if (codigo[i] == nuevoCodigo) {
                                existe = true;
                            }
                        }

                        if (existe) {
                            System.out.println("El codigo ya esta registrado.");
                        } else {

                            codigo[cantidad] = nuevoCodigo;

                            System.out.print("Ingrese el nombre: ");
                            nombre[cantidad] = entrada.nextLine();

                            System.out.print("Ingrese el programa academico: ");
                            programa[cantidad] = entrada.nextLine();

                            System.out.print("Ingrese el promedio: ");
                            promedio[cantidad] = entrada.nextDouble();
                            entrada.nextLine();

                            cantidad++;

                            System.out.println("Estudiante registrado correctamente.");
                        }

                    } else {
                        System.out.println("No se pueden registrar mas de 10 estudiantes.");
                    }
                    break;

                case 2:
                    if (cantidad == 0) {
                        System.out.println("No hay estudiantes registrados.");
                    } else {

                        System.out.println("\n--- LISTA DE ESTUDIANTES ---");

                        for (int i = 0; i < cantidad; i++) {
                            System.out.println("Codigo: " + codigo[i]);
                            System.out.println("Nombre: " + nombre[i]);
                            System.out.println("Programa: " + programa[i]);
                            System.out.println("Promedio: " + promedio[i]);
                            System.out.println("-------------------------");
                        }
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el codigo que desea buscar: ");
                    int buscarCodigo = entrada.nextInt();

                    boolean encontrado = false;

                    for (int i = 0; i < cantidad; i++) {
                        if (codigo[i] == buscarCodigo) {

                            System.out.println("Estudiante encontrado:");
                            System.out.println("Codigo: " + codigo[i]);
                            System.out.println("Nombre: " + nombre[i]);
                            System.out.println("Programa: " + programa[i]);
                            System.out.println("Promedio: " + promedio[i]);

                            encontrado = true;
                        }
                    }

                    if (!encontrado) {
                        System.out.println("No se encontro el estudiante.");
                    }
                    break;

                case 4:
                    boolean hayAprobados = false;

                    System.out.println("\n--- ESTUDIANTES CON PROMEDIO >= 4.0 ---");

                    for (int i = 0; i < cantidad; i++) {
                        if (promedio[i] >= 4.0) {

                            System.out.println("Codigo: " + codigo[i]);
                            System.out.println("Nombre: " + nombre[i]);
                            System.out.println("Promedio: " + promedio[i]);
                            System.out.println("-------------------------");

                            hayAprobados = true;
                        }
                    }

                    if (!hayAprobados) {
                        System.out.println("No hay estudiantes con promedio igual o superior a 4.0.");
                    }
                    break;

                case 5:
                    if (cantidad == 0) {
                        System.out.println("No hay estudiantes registrados.");
                    } else {

                        int mayor = 0;

                        for (int i = 1; i < cantidad; i++) {
                            if (promedio[i] > promedio[mayor]) {
                                mayor = i;
                            }
                        }

                        System.out.println("\n--- ESTUDIANTE CON PROMEDIO MAS ALTO ---");
                        System.out.println("Codigo: " + codigo[mayor]);
                        System.out.println("Nombre: " + nombre[mayor]);
                        System.out.println("Programa: " + programa[mayor]);
                        System.out.println("Promedio: " + promedio[mayor]);
                    }
                    break;

                case 6:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opcion no valida.");
                    break;
            }

        } while (opcion != 6);

        entrada.close();
    }
}