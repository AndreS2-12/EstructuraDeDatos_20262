package Semana1;

public class Ejecutar {
    public static void main(String[] args) {
        System.out.println("Estructuras de Datos UCC!!!");

        // Delcaración del arreglo - array - vector (Forma 1)
        int[] a= {5, 16, 8, 9, 10, 25, 19, 3, 6, 22, 2}; 
        for (int i = 0; i < a.length; i++) {
            System.out.println("a[" + i + "] = " + a[i]);
        }

        // Sumar todos los elementos del arreglo a
        int suma = 0;
        for (int i = 0; i < a.length; i++) {
            suma += a[i];
        }
        System.out.println("La suma de los elementos del arreglo a es: " + suma);

        //Declaración del arreglo - array - vector (Forma 2)
        int[] b = new int[11]; // Declaración del arreglo b con tamaño 11
        int min = 1;
        int max = 30;
        for (int i = 0; i < b.length; i++) {
            b[i] = (int) (Math.random() * (max - min - 1)+ min); // Asignar valores al arreglo b
        }
        //Mostrar el arreglo b
        for (int i = 0; i < b.length; i++) {
            System.out.println("b[" + i + "] = " + b[i]);

            //Mostrar el arreglo b elevando al cuadrado cada elemento
            System.out.println("b[" + i + "] al cuadrado = " + (b[i] * b[i]));
        }  
        
        
        // Ejercicios Clase 1
//3. Dado un arreglo lineal de números, sumar separadamente los números pares y los números impares. 
        int sumaPares = 0;
        int sumaImpares = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {    
                sumaPares += a[i];
            } else {
                sumaImpares += a[i];
            }
        }
        System.out.println("Suma de números PARES:   " + sumaPares);
        System.out.println("Suma de números IMPARES: " + sumaImpares);


// 7. Obtener dos arreglos tal que sus elementos sean los números pares y números impares del arreglo A de 10 elementos. 
        int[] pares = new int[a.length];
        int[] impares = new int[a.length];
        int indexPares = 0;
        int indexImpares = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 0) {
                pares[indexPares] = a[i];
                indexPares++;
            } else {
                impares[indexImpares] = a[i];   
                indexImpares++;
            }
        }
        System.out.println("Arreglo de números PARES:");
        for (int i = 0; i < indexPares; i++) {
            System.out.println("pares[" + i + "] = " + pares[i]);
        }
        System.out.println("Arreglo de números IMPARES:");
        for (int i = 0; i < indexImpares; i++) {
            System.out.println("impares[" + i + "] = " + impares[i]);
        }

//8. Elaborar un programa que lea 30 números y que imprima el número mayor, menor y el número de veces que se repiten ambos. 
            int[] c = new int[30];
            for (int j = 0; j < c.length; j++) {
                c[j] = (int) (Math.random() * (max - min - 1) + min); // Asignar valores al arreglo c
            }
            int mayor = c[0];
            int menor = c[0];
            int countMayor = 0;
            int countMenor = 0;
            for (int j = 0; j < c.length; j++) {
                if (c[j] > mayor) {
                    mayor = c[j];
                    countMayor = 1; // Reiniciar el contador si encontramos un nuevo mayor
                } else if (c[j] == mayor) {
                    countMayor++;
                }
                if (c[j] < menor) {
                    menor = c[j];
                    countMenor = 1; // Reiniciar el contador si encontramos un nuevo menor
                } else if (c[j] == menor) {
                    countMenor++;
                }
            }
            System.out.println("Número mayor: " + mayor + " - Veces que se repite: " + countMayor);
            System.out.println("Número menor: " + menor + " - Veces que se repite: " + countMenor);

//9. Codifique un programa tal, que dado como entrada un arreglo unidimensional de enteros y un número entero, determine cuántas veces se encuentra este número dentro del arreglo. 
        int[] d = new int[10];
        int numero = 5; // Número a buscar
        int count = 0;
        for (int j = 0; j < d.length; j++) {
            d[j] = (int) (Math.random() * 10) + 1; // Asignar valores al arreglo d
            if (d[j] == numero) {
                count++;
            }
        }
        System.out.println("El número " + numero + " se encuentra " + count + " veces en el arreglo.");

//10. Dado un arreglo A de N elementos se desea crear otro arreglo, tal que cada uno de sus elementos sea la suma de los opuestos en el arreglo dado. 
        int[] e = new int[10];
        for (int j = 0; j < e.length; j++) {
            e[j] = (int) (Math.random() * 10) + 1; // Asignar valores al arreglo e
        }

//11. Dado un arreglo A de N elementos se desea generar tres arreglos que contengan los elementos negativos, cero y positivos de arreglo initial. 
            int[] f = new int[10];
            for (int j = 0; j < f.length; j++) {
                f[j] = (int) (Math.random() * 21) - 10; // Asignar valores al arreglo f entre -10 y 10
            }
            
//15. Se tienen los nombres y los sueldos de los trabajadores de una empresa. Se desea saber cuántos y quiénes tienen un sueldo promedio. 
            indexImpares = 0;
            indexPares = 0;
            String[] nombres = {"Juan", "María", "Pedro", "Ana", "Luis", "Carmen", "Jorge", "Lucía", "Miguel", "Sofía"};
            double[] sueldos = {1500.0, 2000.0, 1800.0, 2200.0, 2500.0, 1700.0, 1900.0, 2100.0, 2300.0, 1600.0};
            double sumaSueldos = 0;
            for (int j = 0; j < sueldos.length; j++) {
                sumaSueldos += sueldos[j];
                
            }
        }
    }