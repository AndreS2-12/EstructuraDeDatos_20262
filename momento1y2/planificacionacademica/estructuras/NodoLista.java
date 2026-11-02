package momento1y2.planificacionacademica.estructuras;

/**
 * Nodo de una lista enlazada simple.
 * Guarda un dato y una referencia al nodo siguiente.
 *
 * @param <T> tipo de dato que almacena el nodo
 */
public class NodoLista<T> {

    private T dato;
    private NodoLista<T> siguiente;

    /**
     * Crea un nodo sin siguiente (queda apuntando a null).
     *
     * @param dato valor que guardará el nodo
     */
    public NodoLista(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoLista<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista<T> siguiente) {
        this.siguiente = siguiente;
    }
}