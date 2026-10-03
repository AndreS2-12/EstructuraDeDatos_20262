package Semana3;
public class EjecutarOperaciones {

    public static void main(String[] args) {
        // Creación del objeto para llamar los métodos de la clase
        // OperacionesMatrices.java
        OperacionesMatrices objM = new OperacionesMatrices();

        // llenar la matriz
        int[][] matriz = objM.llenarMatrices(3, 3, 1, 9);

        // mostrar la matriz
        System.out.println(objM.mostrarMatriz(matriz));

    // Sumar los elementos de cada fila y cada columna de una matriz. 
        int[] sumaFilas = objM.sumarFila(matriz);
        int[] sumaColumnas = objM.sumarColumnas(matriz);

        // Mostrar los resultados
        System.out.println("Suma de cada fila:");
        for (int i = 0; i < sumaFilas.length; i++) {
            System.out.println("Fila " + (i + 1) + ": " + sumaFilas[i]);
        }

        System.out.println("Suma de cada columna:");
        for (int j = 0; j < sumaColumnas.length; j++) {
            System.out.println("Columna " + (j + 1) + ": " + sumaColumnas[j]);
        }   

        //18. Realice un programa que calcule la tabla de multiplicar del 1 al 10 almacenando los valores en una tabla. Imprimir dicha tabla. 
        int[][] tablaMultiplicar = objM.tablaMultiplicar(10);
        System.out.println(objM.mostrarMatriz(tablaMultiplicar));
        
    }
}