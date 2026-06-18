package club.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import club.dominio.Contenido;
import club.dominio.EstadoContenido;
import club.dominio.Persona;
import club.dominio.Suscriptor;
import club.excepcion.ContenidoNoDisponibleException;
import club.repositorio.IRepositorio;
import club.repositorio.RepositorioArchivo;

public class Videoclub {
    private ArrayList<Contenido> catalogo;
    private HashMap<Integer, Persona> personas;
    private IRepositorio<Contenido> repositorio;

    public Videoclub() {
        this.repositorio = new RepositorioArchivo<>("catalogo.dat");
        this.catalogo = new ArrayList<>(repositorio.consultar());
        this.personas = new HashMap<>();
    }

    public void agregarContenido(Contenido c) {
        catalogo.add(c);
        repositorio.guardar(catalogo);
    }

    public void registrarPersona(Persona p) {
        personas.put(p.getDni(), p);
    }

    public HashMap<Integer, Persona> getPersonas() {
        return personas;
    }

    public Optional<Contenido> buscarPorTitulo(String titulo) {
        return catalogo.stream()
                .filter(c -> c.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();
    }

    public List<Contenido> obtenerContenidoDisponible() {
        return catalogo.stream()
                .filter(c -> c.getEstado() == EstadoContenido.DISPONIBLE)
                .collect(Collectors.toList());
    }

    public void mostrarCatalogoOrdenado() {
        catalogo.stream()
                .sorted((c1, c2) -> c1.getTitulo().compareToIgnoreCase(c2.getTitulo()))
                .forEach(Contenido::mostrarInfo);
    }

    public void alquilarContenido(int dni, String titulo) throws ContenidoNoDisponibleException {
        Persona p = personas.get(dni);
        if (p == null || !(p instanceof Suscriptor)) {
            throw new IllegalArgumentException("El DNI ingresado no corresponde a un Suscriptor registrado.");
        }

        Suscriptor s = (Suscriptor) p;
        Optional<Contenido> oContenido = buscarPorTitulo(titulo);

        if (!oContenido.isPresent()) {
            throw new IllegalArgumentException("El contenido solicitado no existe en el catálogo.");
        }

        Contenido c = oContenido.get();

        if (c.getEstado() != EstadoContenido.DISPONIBLE) {
            throw new ContenidoNoDisponibleException("El contenido '" + titulo
                    + "' NO se encuentra DISPONIBLE (Estado actual: " + c.getEstado() + ").");
        }

        c.setEstado(EstadoContenido.ALQUILADO);
        s.getContenidoAlquilado().add(c);
        c.reproducir();
        repositorio.guardar(catalogo);
    }

    public void devolverContenido(int dni, String titulo) {
        Persona p = personas.get(dni);
        if (p == null || !(p instanceof Suscriptor)) {
            throw new IllegalArgumentException("El DNI ingresado no corresponde a un Suscriptor.");
        }

        Suscriptor s = (Suscriptor) p;
        Contenido encontrado = null;
        for (Contenido c : s.getContenidoAlquilado()) {
            if (c.getTitulo().equalsIgnoreCase(titulo)) {
                encontrado = c;
                break;
            }
        }

        if (encontrado == null) {
            throw new IllegalArgumentException("El suscriptor no tiene asignado el alquiler de este contenido.");
        }

        encontrado.setEstado(EstadoContenido.DISPONIBLE);
        s.getContenidoAlquilado().remove(encontrado);
        encontrado.devolver();
        repositorio.guardar(catalogo);
    }
}
