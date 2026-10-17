package Semana6;

//2. Pan similar (Deque como cola de doble extremo) Enunciado. Representa un sandwich como una secuencia: 
// pan– relleno– pan. Dados dos sandwiches, verificar si usan el mismo pan al inicio y al final. 
// Ejemplos de verificacion. 
// Entrada: S1 = [“pan blanco”, “lechuga”, “pan blanco”], S2 = [“pan blanco”, “tomate”, “pan blanco”] 
// Salida esperada: true Entrada: 
// S1 = [“pan integral”, “queso”, “pan integral”], S2 = [“pan blanco”, “queso”, “pan integral”] 
// Salida esperada: false

public class ColaU2 {
    public static void main(String[] args) {
        String[] sandwich1 = {"pan blanco", "lechuga", "pan blanco"};
        String[] sandwich2 = {"pan blanco", "tomate", "pan blanco"};

        // Verificar si ambos sandwiches usan el mismo pan al inicio y al final
        boolean mismoPan = verificarMismoPan(sandwich1, sandwich2);

        // Mostrar el resultado
        System.out.println("¿Los sandwiches usan el mismo pan al inicio y al final? " + mismoPan);
    }

    public static boolean verificarMismoPan(String[] s1, String[] s2) {
        if (s1.length < 2 || s2.length < 2) {
            return false; // No hay suficiente información para comparar
        }
        return s1[0].equals(s2[0]) && s1[s1.length - 1].equals(s2[s2.length - 1]);
    }
}
