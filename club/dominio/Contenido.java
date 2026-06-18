package club.dominio;

import java.io.Serializable;

public abstract class Contenido implements IReproducible, Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String titulo;
    private String genero;
    private EstadoContenido estado;

    public Contenido(int id, String titulo, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.estado = EstadoContenido.DISPONIBLE;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getGenero() { return genero; }
    public EstadoContenido getEstado() { return estado; }
    public void setEstado(EstadoContenido estado) { this.estado = estado; }

    public abstract double getDuracionTotal();
    public abstract void mostrarInfo();
}
