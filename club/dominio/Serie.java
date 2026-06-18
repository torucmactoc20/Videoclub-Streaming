package club.dominio;

public class Serie extends Contenido {
    private static final long serialVersionUID = 1L;

    private int cantidadTemporadas;
    private double duracionEpisodio;

    public Serie(int id, String titulo, String genero, int cantidadTemporadas, double duracionEpisodio) {
        super(id, titulo, genero);
        this.cantidadTemporadas = cantidadTemporadas;
        this.duracionEpisodio = duracionEpisodio;
    }

    @Override
    public double getDuracionTotal() {
        return cantidadTemporadas * 10 * duracionEpisodio;
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo Serie: " + getTitulo() + " (Temporadas: " + cantidadTemporadas + ")");
    }

    @Override
    public void devolver() {
        System.out.println("Devolviendo Serie: " + getTitulo());
    }

    @Override
    public void mostrarInfo() {
        System.out.println("Serie [ID: " + getId() + "] " + getTitulo()
                + " | Temporadas: " + cantidadTemporadas
                + " | Total Estimado: " + getDuracionTotal() + " min"
                + " | Estado: " + getEstado());
    }
}
