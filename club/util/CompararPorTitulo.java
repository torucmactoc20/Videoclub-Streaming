package club.util;

import java.util.Comparator;
import club.dominio.Contenido;

public class CompararPorTitulo implements Comparator<Contenido> {
    @Override
    public int compare(Contenido c1, Contenido c2) {
        return c1.getTitulo().compareToIgnoreCase(c2.getTitulo());
    }
}
