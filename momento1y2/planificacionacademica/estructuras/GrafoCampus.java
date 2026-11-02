package momento1y2.planificacionacademica.estructuras;
import momento1y2.planificacionacademica.modelo.Edificio;

/**
 * Mapa del campus como grafo no dirigido. Las distancias entre edificios
 * se guardan en una matriz de adyacencia int[N][N]:
 * distancias[i][j] son los metros entre el edificio i y el j (0 = sin camino directo).
 */
public class GrafoCampus {

    private static final int INFINITO = Integer.MAX_VALUE;

    private final Edificio[] edificios;
    private final int[][] distancias;
    private int cantidad;

    /**
     * Crea un campus vacío.
     *
     * @param capacidad cantidad máxima de edificios (al menos 2)
     */
    public GrafoCampus(int capacidad) {
        if (capacidad < 2) {
            throw new IllegalArgumentException("La capacidad debe ser de al menos 2 edificios.");
        }
        this.edificios = new Edificio[capacidad];
        this.distancias = new int[capacidad][capacidad];
        this.cantidad = 0;
    }

    // ---------- Edificios (arreglo de objetos) ----------

    /** Agrega un edificio y devuelve su posición en el arreglo. */
    public int agregarEdificio(Edificio edificio) {
        if (edificio == null) {
            throw new IllegalArgumentException("El edificio no puede ser nulo.");
        }
        if (buscarIndice(edificio.getNombre()) != -1) {
            throw new IllegalArgumentException("Ya existe el edificio " + edificio.getNombre() + ".");
        }
        if (cantidad == edificios.length) {
            throw new IllegalStateException(
                    "El campus ya tiene el maximo de " + edificios.length + " edificios.");
        }
        edificios[cantidad] = edificio;
        cantidad++;
        return cantidad - 1;
    }

    /** Devuelve la posición del edificio con ese nombre, o -1 si no existe. */
    public int buscarIndice(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (edificios[i].tieneNombre(nombre)) {
                return i;
            }
        }
        return -1;
    }

    public int getCantidadEdificios() {
        return cantidad;
    }

    public void mostrarEdificios() {
        if (cantidad == 0) {
            System.out.println("No hay edificios registrados.");
            return;
        }
        System.out.println("Edificios registrados:");
        for (int i = 0; i < cantidad; i++) {
            System.out.println(i + ": " + edificios[i]);
        }
    }

    // ---------- Conexiones (matriz) ----------

    /**
     * Conecta dos edificios con una distancia en metros.
     * Como el grafo no es dirigido, se guarda en [i][j] y en [j][i].
     */
    public void agregarConexion(String origen, String destino, int metros) {
        int i = indiceObligatorio(origen);
        int j = indiceObligatorio(destino);
        if (i == j) {
            throw new IllegalArgumentException("Un edificio no puede conectarse consigo mismo.");
        }
        if (metros <= 0) {
            throw new IllegalArgumentException("La distancia debe ser mayor a 0 metros.");
        }
        distancias[i][j] = metros;
        distancias[j][i] = metros;
    }

    // ---------- Dijkstra ----------

    /**
     * Calcula la ruta más corta entre dos edificios.
     *
     * @return texto con el camino, la distancia de cada tramo y el total
     * @throws IllegalStateException si no existe camino entre ambos
     */
    public String rutaMasCorta(String origen, String destino) {
        int inicio = indiceObligatorio(origen);
        int fin = indiceObligatorio(destino);

        int[] distancia = new int[cantidad];      // mejor distancia conocida desde el origen
        int[] anterior = new int[cantidad];       // de qué edificio venimos
        boolean[] visitado = new boolean[cantidad];
        for (int i = 0; i < cantidad; i++) {
            distancia[i] = INFINITO;
            anterior[i] = -1;
        }
        distancia[inicio] = 0;

        for (int paso = 0; paso < cantidad; paso++) {
            // 1. Elegir el no visitado con menor distancia
            int actual = -1;
            for (int i = 0; i < cantidad; i++) {
                if (!visitado[i] && distancia[i] != INFINITO
                        && (actual == -1 || distancia[i] < distancia[actual])) {
                    actual = i;
                }
            }
            if (actual == -1) {
                break;                            // los que faltan son inalcanzables
            }
            visitado[actual] = true;

            // 2. Revisar a los vecinos de 'actual'
            for (int vecino = 0; vecino < cantidad; vecino++) {
                int metros = distancias[actual][vecino];
                if (metros > 0 && !visitado[vecino]
                        && distancia[actual] + metros < distancia[vecino]) {
                    distancia[vecino] = distancia[actual] + metros;
                    anterior[vecino] = actual;
                }
            }
        }

        if (distancia[fin] == INFINITO) {
            throw new IllegalStateException("No hay ruta entre " + edificios[inicio].getNombre()
                    + " y " + edificios[fin].getNombre() + ".");
        }

        // 3. Reconstruir el camino desde el destino hacia atrás usando una pila,
        //    así al desapilar sale en orden: origen -> ... -> destino
        Pila<Integer> camino = new Pila<>();
        int nodo = fin;
        while (nodo != -1) {
            camino.apilar(nodo);
            nodo = anterior[nodo];
        }

        StringBuilder texto = new StringBuilder();
        int previo = camino.desapilar();
        texto.append(edificios[previo].getNombre());
        while (!camino.estaVacia()) {
            int siguiente = camino.desapilar();
            texto.append(" -> ").append(edificios[siguiente].getNombre())
                    .append(" (").append(distancias[previo][siguiente]).append("m)");
            previo = siguiente;
        }
        texto.append("\nDistancia TOTAL: ").append(distancia[fin]).append(" metros");
        return texto.toString();
    }

    // ---------- Utilidad interna ----------

    private int indiceObligatorio(String nombre) {
        int indice = buscarIndice(nombre);
        if (indice == -1) {
            throw new IllegalArgumentException("No existe el edificio: " + nombre);
        }
        return indice;
    }
}