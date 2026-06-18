package club.dominio;

import java.util.ArrayList;

public class Suscriptor extends Persona {
    private static final long serialVersionUID = 1L;

    private String planSuscripcion;
    private ArrayList<Contenido> contenidoAlquilado;

    public Suscriptor(int dni, String nombre, String apellido, String planSuscripcion) {
        super(dni, nombre, apellido);
        this.planSuscripcion = planSuscripcion;
        this.contenidoAlquilado = new ArrayList<>();
    }

    public String getPlanSuscripcion() { return planSuscripcion; }
    public ArrayList<Contenido> getContenidoAlquilado() { return contenidoAlquilado; }

    @Override
    public void mostrarInfo() {
        System.out.println("Suscriptor [DNI: " + getDni() + "] "
                + getApellido() + ", " + getNombre()
                + " | Plan: " + planSuscripcion);
    }
}
