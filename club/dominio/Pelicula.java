package club.dominio;

public class Pelicula extends Contenido {
    private static final long serialVersionUID = 1L;

    private double duracion;
    private String director;

    public Pelicula(int id, String titulo, String genero, double duracion, String director) {
        super(id, titulo, genero);
        this.duracion = duracion;
        this.director = director;
    }

    @Override
    public double getDuracionTotal() {
        return this.duracion;
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo Película: " + getTitulo() + " dirigida por " + director);
    }

    @Override
    public void devolver() {
        System.out.println("Devolviendo Película: " + getTitulo());
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Película [ID: " + getId() + "] " + getTitulo()
                + " | Género: " + getGenero()
                + " | Duración: " + duracion + " min"
                + " | Estado: " + getEstado());
    }
}
