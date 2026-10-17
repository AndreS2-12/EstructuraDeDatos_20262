package Semana5.Actividad;

import java.util.Scanner;

//Actividad 2. Matrices de objetos: Inventario de productos
//Una empresa requiere organizar sus productos en una matriz de 3 × 4, donde cada posición representa una ubicación de almacenamiento.
//Cada producto debe tener código, nombre, precio y cantidad disponible.
//Requerimientos:
//•	Registrar productos en posiciones específicas.
//•	Mostrar los productos indicando fila y columna.
//•	Buscar un producto por código.
//•	Calcular el valor total del inventario.
//•	Identificar el producto con mayor cantidad disponible y contar las posiciones vacías.
// Esta clase encapsula todos los datos de un producto en una sola entidad.

class Producto {
    String codigo;
    String nombre;
    double precio;
    int cantidad;

    // Constructor
    public Producto(String codigo, String nombre, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }
}

public class InventarioProductos {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        
        // 2. DECLARACIÓN DE LA MATRIZ DE OBJETOS (3 filas x 4 columnas)
        // Inicialmente, todas las posiciones contienen 'null' (están vacías)
        Producto[][] inventario = new Producto[3][4];
        
        int opcion = 0;

        do {
            System.out.println("\n========================================");
            System.out.println("   SISTEMA DE INVENTARIO DE PRODUCTOS ");
            System.out.println("========================================");
            System.out.println("1. Registrar producto en posición específica");
            System.out.println("2. Mostrar todos los productos");
            System.out.println("3. Buscar producto por código");
            System.out.println("4. Calcular valor total del inventario");
            System.out.println("5. Producto con mayor cantidad y posiciones vacías");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            // Manejo seguro de la entrada
            if (!entrada.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                entrada.next();
                continue;
            }
            opcion = entrada.nextInt();
            entrada.nextLine(); // Limpiar el salto de línea pendiente

            switch (opcion) {
                case 1: // REGISTRAR PRODUCTO
                    System.out.print("Ingrese la FILA (1 a 3): ");
                    int fila = entrada.nextInt() - 1; // Restamos 1 para que el usuario piense en 1-3, pero el array sea 0-2
                    System.out.print("Ingrese la COLUMNA (1 a 4): ");
                    int col = entrada.nextInt() - 1;
                    entrada.nextLine(); // Limpiar buffer

                    // Validar que la posición exista en la matriz
                    if (fila >= 0 && fila < 3 && col >= 0 && col < 4) {
                        // Validar que la posición esté vacía
                        if (inventario[fila][col] == null) {
                            System.out.print("Ingrese el código del producto: ");
                            String cod = entrada.nextLine();
                            System.out.print("Ingrese el nombre del producto: ");
                            String nom = entrada.nextLine();
                            System.out.print("Ingrese el precio: ");
                            double pre = entrada.nextDouble();
                            System.out.print("Ingrese la cantidad disponible: ");
                            int cant = entrada.nextInt();
                            entrada.nextLine(); // Limpiar buffer

                            // Crear el objeto y guardarlo en la matriz
                            inventario[fila][col] = new Producto(cod, nom, pre, cant);
                            System.out.println(" Producto registrado exitosamente en Fila " + (fila + 1) + ", Columna " + (col + 1));
                        } else {
                            System.out.println(" Esa posición ya está ocupada por otro producto.");
                        }
                    } else {
                        System.out.println(" Error: Fila o columna fuera de los límites de la matriz (3x4).");
                    }
                    break;

                case 2: // MOSTRAR PRODUCTOS
                    System.out.println("\n--- INVENTARIO ACTUAL ---");
                    boolean hayProductos = false;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (inventario[i][j] != null) {
                                System.out.println(" Posición [" + (i + 1) + "][" + (j + 1) + "]");
                                System.out.println("   Código: " + inventario[i][j].codigo);
                                System.out.println("   Nombre: " + inventario[i][j].nombre);
                                System.out.println("   Precio: $" + inventario[i][j].precio);
                                System.out.println("   Cantidad: " + inventario[i][j].cantidad);
                                System.out.println("   -------------------------");
                                hayProductos = true;
                            }
                        }
                    }
                    if (!hayProductos) {
                        System.out.println("El inventario está completamente vacío.");
                    }
                    break;

                case 3: // BUSCAR POR CÓDIGO
                    System.out.print("Ingrese el código del producto a buscar: ");
                    String codigoBusqueda = entrada.nextLine();
                    boolean encontrado = false;

                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (inventario[i][j] != null && inventario[i][j].codigo.equals(codigoBusqueda)) {
                                System.out.println("\n Producto encontrado:");
                                System.out.println("Ubicación: Fila " + (i + 1) + ", Columna " + (j + 1));
                                System.out.println("Nombre: " + inventario[i][j].nombre);
                                System.out.println("Precio: $" + inventario[i][j].precio);
                                System.out.println("Cantidad: " + inventario[i][j].cantidad);
                                encontrado = true;
                                break; // Salimos del ciclo interno, pero podríamos seguir buscando si hay duplicados
                            }
                        }
                        if (encontrado) break; // Salimos del ciclo externo si ya lo encontramos
                    }

                    if (!encontrado) {
                        System.out.println("No se encontró ningún producto con ese código.");
                    }
                    break;

                case 4: // CALCULAR VALOR TOTAL
                    double valorTotal = 0;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (inventario[i][j] != null) {
                                valorTotal += (inventario[i][j].precio * inventario[i][j].cantidad);
                            }
                        }
                    }
                    System.out.printf("\n El valor total del inventario es: $%.2f\n", valorTotal);
                    break;

                case 5: // MAYOR CANTIDAD Y POSICIONES VACÍAS
                    int maxCantidad = -1;
                    Producto productoMayor = null;
                    int posicionesVacias = 0;

                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (inventario[i][j] == null) {
                                posicionesVacias++;
                            } else {
                                if (inventario[i][j].cantidad > maxCantidad) {
                                    maxCantidad = inventario[i][j].cantidad;
                                    productoMayor = inventario[i][j];
                                }
                            }
                        }
                    }

                    System.out.println("\n--- ESTADÍSTICAS DEL INVENTARIO ---");
                    System.out.println("Posiciones vacías en la matriz: " + posicionesVacias + " de 12");
                    
                    if (productoMayor != null) {
                        System.out.println("Producto con MAYOR cantidad disponible:");
                        System.out.println("   Nombre: " + productoMayor.nombre);
                        System.out.println("   Cantidad: " + productoMayor.cantidad + " unidades");
                    } else {
                        System.out.println("No hay productos registrados para evaluar.");
                    }
                    break;

                case 6:
                    System.out.println("Sistema finalizado. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        } while (opcion != 6);
        entrada.close();
    }
}