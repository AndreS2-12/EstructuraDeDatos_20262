package momento1y2.planificacionacademica.excepciones;

/** Se lanza cuando el archivo CSV no existe o tiene un formato incorrecto. */
public class ArchivoInvalidoException extends Exception {
    public ArchivoInvalidoException(String mensaje) {
        super(mensaje);
    }
}