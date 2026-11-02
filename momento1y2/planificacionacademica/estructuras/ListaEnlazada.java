package momento1y2.planificacionacademica.estructuras;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Lista enlazada simple genérica, implementada con nodos propios.
 * Guarda referencia al primer y al último nodo.
 *
 * @param <T> tipo de dato que almacena la lista
 */
public class ListaEnlazada<T> implements Iterable<T> {

    private NodoLista<T> primero;
    private NodoLista<T> ultimo;
    private int cantidad;

    /** Crea una lista vacía. */
    public ListaEnlazada() {
        this.primero = null;
        this.ultimo = null;
        this.cantidad = 0;
    }

    // ---------- Agregar ----------

    /** Agrega un dato al inicio de la lista. */
    public void agregarAlInicio(T dato) {
        validarDato(dato);
        NodoLista<T> nuevo = new NodoLista<>(dato);
        nuevo.setSiguiente(primero);
        primero = nuevo;
        if (ultimo == null) {      // la lista estaba vacía
            ultimo = nuevo;
        }
        cantidad++;
    }

    /** Agrega un dato al final de la lista. */
    public void agregarAlFinal(T dato) {
        validarDato(dato);
        NodoLista<T> nuevo = new NodoLista<>(dato);
        if (primero == null) {     // la lista estaba vacía
            primero = nuevo;
            ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }
        cantidad++;
    }

    // ---------- Consultar ----------

    /** Devuelve el primer dato sin eliminarlo. */
    public T obtenerPrimero() {
        if (primero == null) {
            throw new NoSuchElementException("La lista está vacía.");
        }
        return primero.getDato();
    }

    /** Devuelve el dato en la posición indicada (la primera es 0). */
    public T obtener(int indice) {
        if (indice < 0 || indice >= cantidad) {
            throw new IndexOutOfBoundsException(
                    "Índice " + indice + " fuera de rango (tamaño: " + cantidad + ").");
        }
        NodoLista<T> actual = primero;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    /** Indica si el dato está en la lista (usa equals). */
    public boolean contiene(T dato) {
        if (dato == null) {
            return false;
        }
        NodoLista<T> actual = primero;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public int tamano() {
        return cantidad;
    }

    public boolean estaVacia() {
        return cantidad == 0;
    }

    // ---------- Eliminar ----------

    /** Elimina y devuelve el primer dato. */
    public T eliminarPrimero() {
        if (primero == null) {
            throw new NoSuchElementException("La lista está vacía.");
        }
        T dato = primero.getDato();
        primero = primero.getSiguiente();
        if (primero == null) {     // la lista quedó vacía
            ultimo = null;
        }
        cantidad--;
        return dato;
    }

    /**
     * Elimina la primera aparición del dato.
     *
     * @return true si lo encontró y lo eliminó; false si no estaba
     */
    public boolean eliminar(T dato) {
        if (dato == null || primero == null) {
            return false;
        }
        if (primero.getDato().equals(dato)) {
            eliminarPrimero();
            return true;
        }
        NodoLista<T> anterior = primero;
        NodoLista<T> actual = primero.getSiguiente();
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                anterior.setSiguiente(actual.getSiguiente());  // "salta" el nodo
                if (actual == ultimo) {
                    ultimo = anterior;
                }
                cantidad--;
                return true;
            }
            anterior = actual;
            actual = actual.getSiguiente();
        }
        return false;
    }

    /** Elimina todos los datos de la lista. */
    public void limpiar() {
        primero = null;
        ultimo = null;
        cantidad = 0;
    }

    // ---------- Recorrido ----------

    /** Permite recorrer la lista con for-each. */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodoLista<T> actual = primero;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (actual == null) {
                    throw new NoSuchElementException();
                }
                T dato = actual.getDato();
                actual = actual.getSiguiente();
                return dato;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        NodoLista<T> actual = primero;
        while (actual != null) {
            sb.append(actual.getDato());
            if (actual.getSiguiente() != null) {
                sb.append(", ");
            }
            actual = actual.getSiguiente();
        }
        return sb.append("]").toString();
    }

    // ---------- Utilidad interna ----------

    private void validarDato(T dato) {
        if (dato == null) {
            throw new IllegalArgumentException("La lista no acepta datos nulos.");
        }
    }
}