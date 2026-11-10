package momento1y2.planificacionacademica.modelo;

import java.util.LinkedList;

public class Materia {

    private String codigo;
    private String nombre;
    private int creditos;
    private int cuposMaximos;

    private LinkedList<String> prerrequisitos;

    private LinkedList<String> estudiantesInscritos;

    private LinkedList<String> colaEspera;

    public Materia(String codigo, String nombre,
                int creditos, int cuposMaximos) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.cuposMaximos = cuposMaximos;

        this.prerrequisitos = new LinkedList<>();
        this.estudiantesInscritos = new LinkedList<>();
        this.colaEspera = new LinkedList<>();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public int getCuposMaximos() {
        return cuposMaximos;
    }

    public LinkedList<String> getPrerrequisitos() {
        return prerrequisitos;
    }

    public LinkedList<String> getEstudiantesInscritos() {
        return estudiantesInscritos;
    }

    public LinkedList<String> getColaEspera() {
        return colaEspera;
    }

    public int getCuposDisponibles() {
        return cuposMaximos - estudiantesInscritos.size();
    }
}