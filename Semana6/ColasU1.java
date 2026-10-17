package Semana6;

//1. Votos a favor vs. en contra (Cola) 
// Enunciado. Dados dos contadores: upvotes y downvotes, modela cada voto en una cola como +1 (a favor) o-1 
// (en contra). Procesa la cola sumando todos los valores hasta vaciarla. Retorna la diferencia: (a favor) − 
// (en contra). 
// Ejemplos de verificacion. 
// Entrada: upvotes = 13, downvotes = 0 Salida esperada: 13 
// Entrada: upvotes = 2, downvotes = 33 Salida esperada:-31 
// Entrada: upvotes = 132, downvotes = 132 Salida esperada: 0
public class ColasU1 {
    public static void main(String[] args) {
        int upvotes = 13; 
        int downvotes = 0; 

        // Crear una cola para almacenar los votos
        java.util.Queue<Integer> votos = new java.util.LinkedList<>();

        // Agregar los votos a la cola
        for (int i = 0; i < upvotes; i++) {
            votos.add(1); // +1 para votos a favor
        }
        for (int i = 0; i < downvotes; i++) {
            votos.add(-1); // -1 para votos en contra
        }

        // Procesar la cola y calcular la diferencia
        int resultado = 0;
        while (!votos.isEmpty()) {
            resultado += votos.poll(); // Sumar y eliminar el voto de la cola
        }

        // Mostrar el resultado final
        System.out.println("Resultado final (a favor - en contra): " + resultado);
    }
}
