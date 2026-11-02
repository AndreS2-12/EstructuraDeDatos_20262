package momento1y2.planificacionacademica.estructuras;
import java.util.NoSuchElementException;

/**
 * Pila genérica (LIFO: el último en entrar es el primero en salir).
 * Se implementa sobre una lista enlazada propia; el tope es el primer nodo.
 *
 * @param <T> tipo de dato que almacena la pila
 */
public class Pila<T> {

    private final ListaEnlazada<T> elementos;

    /** Crea una pila vacía. */
    public Pila() {
        this.elementos = new ListaEnlazada<>();
    }

    /** Coloca un dato en el tope de la pila. */
    public void apilar(T dato) {
        if (dato == null) {
            throw new IllegalArgumentException("La pila no acepta datos nulos.");
        }
        elementos.agregarAlInicio(dato);
    }

    /** Quita y devuelve el dato del tope. */
    public T desapilar() {
        if (estaVacia()) {
            throw new NoSuchElementException("La pila está vacía.");
        }
        return elementos.eliminarPrimero();
    }

    /** Devuelve el dato del tope sin quitarlo. */
    public T verTope() {
        if (estaVacia()) {
            throw new NoSuchElementException("La pila está vacía.");
        }
        return elementos.obtenerPrimero();
    }

    public boolean estaVacia() {
        return elementos.estaVacia();
    }

    public int tamano() {
        return elementos.tamano();
    }

    /** Vacía la pila por completo. */
    public void limpiar() {
        elementos.limpiar();
    }

    /** Muestra la pila con el tope primero, por ejemplo [C, B, A]. */
    @Override
    public String toString() {
        return elementos.toString();
    }
}