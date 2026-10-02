package Semana3;

public class OperacionesMatrices {
        public int[][] llenarMatrices (int f, int c, int min, int max) {
            int[][] m = new int[f][c];
            // Lógica para llenar la matriz (puede ser con valores predeterminados o ingresados por el usuario)
                for (int i = 0; i < m.length; i++) {
                    for (int j = 0; j < m[i].length; j++) {
                    m[i][j] = (int) (Math.random() * (max - min + 1)) + min; // Genera un número aleatorio entre min y max
                }
        }
        return m;
    }

    //Metodo que permite mostrar la matriz en consola
    public String mostrarMatriz(int[][] m) {
        String cad = "";
        for (int i = 0; i < m.length; i++) {
            cad += "|";
            for (int j = 0; j < m[i].length; j++) {
                cad += m[i][j] + "|";
            }
            cad += "\n";
        }
        return cad;
    }
        
    // 3. Método para sumar los elementos de cada FILA
    public int[] sumarFila(int[][] m) {
        // El arreglo de sumas tendrá el tamaño del número de filas (m.length)
        int[] suma = new int[m.length]; 
        
        for (int i = 0; i < m.length; i++) {       // Recorre las filas
            for (int j = 0; j < m[i].length; j++) { // Recorre las columnas
                suma[i] += m[i][j]; // Acumula la suma en la posición de la fila actual
            }
        }
        return suma;
    }

    // 4. Método para sumar los elementos de cada COLUMNA
    public int[] sumarColumnas(int[][] m) {
        // El arreglo de sumas tendrá el tamaño del número de columnas (m[0].length)
        int[] suma = new int[m[0].length]; 
        
        for (int j = 0; j < m[0].length; j++) {    // Recorre las columnas (¡Ojo: este va primero!)
            for (int i = 0; i < m.length; i++) {   // Recorre las filas
                suma[j] += m[i][j]; // Acumula la suma en la posición de la columna actual
            }
        }
        return suma;
    }
}



