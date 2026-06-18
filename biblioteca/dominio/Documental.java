package biblioteca.dominio;

public class Documental extends Contenido {
    private static final long serialVersionUID = 1L;

    private String tematica;
    private double duracion;

    public Documental(int id, String titulo, String genero, String tematica, double duracion) {
        super(id, titulo, genero);
        this.tematica = tematica;
        this.duracion = duracion;
    }

    @Override
    public double getDuracionTotal() {
        return this.duracion;
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo Documental: " + getTitulo() + " sobre " + tematica);
    }

    @Override
    public void devolver() {
        System.out.println("Devolviendo Documental: " + getTitulo());
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Documental [ID: " + getId() + "] " + getTitulo()
                + " | Temática: " + tematica
                + " | Duración: " + duracion + " min"
                + " | Estado: " + getEstado());
    }
}
