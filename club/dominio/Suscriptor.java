package club.dominio;

import java.util.ArrayList;

import club.excepcion.ContenidoNoDisponibleException;

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

    public void alquilarContenido(Contenido c) throws ContenidoNoDisponibleException {
        if (c.getEstado() != EstadoContenido.DISPONIBLE) {
            throw new ContenidoNoDisponibleException("El contenido '" + c.getTitulo()
                    + "' NO está DISPONIBLE (Estado actual: " + c.getEstado() + ").");
        }
        c.setEstado(EstadoContenido.ALQUILADO);
        contenidoAlquilado.add(c);
        c.reproducir();
    }

    public void devolverContenido(Contenido c) {
        if (contenidoAlquilado.remove(c)) {
            c.setEstado(EstadoContenido.DISPONIBLE);
            c.devolver();
        } else {
            System.out.println("Este suscriptor no tiene alquilado ese contenido.");
        }
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Suscriptor [DNI: " + getDni() + "] "
                + getApellido() + ", " + getNombre()
                + " | Plan: " + planSuscripcion);
    }
}
