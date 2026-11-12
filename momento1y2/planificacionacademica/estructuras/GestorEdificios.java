
package momento1y2.planificacionacademica.estructuras;

import java.util.LinkedList;

import momento1y2.planificacionacademica.modelo.Edificio;

public class GestorEdificios {

    private LinkedList<Edificio> edificios;

    public GestorEdificios() {
        edificios = new LinkedList<>();
    }

    public void registrarEdificio(Edificio edificio) {

        if (edificio == null) {
            throw new IllegalArgumentException(
                "El edificio no puede ser nulo."
            );
        }

        if (buscarEdificio(edificio.getCodigo()) != null) {
            System.out.println("El edificio ya está registrado.");
            return;
        }

        edificios.add(edificio);

        System.out.println("Edificio registrado correctamente.");
    }

    public Edificio buscarEdificio(String codigo) {

        for (Edificio edificio : edificios) {

            if (edificio.getCodigo().equalsIgnoreCase(codigo)) {
                return edificio;
            }
        }

        return null;
    }

    public int obtenerIndice(String codigo) {

        for (int i = 0; i < edificios.size(); i++) {

            if (edificios.get(i).getCodigo().equalsIgnoreCase(codigo)) {
                return i;
            }
        }

        return -1;
    }

    public LinkedList<Edificio> listarEdificios() {
        return new LinkedList<>(edificios);
    }

    public int cantidadEdificios() {
        return edificios.size();
    }
}