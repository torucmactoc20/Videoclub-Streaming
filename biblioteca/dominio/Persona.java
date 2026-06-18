package biblioteca.dominio;

import java.io.Serializable;
import java.util.Objects;

public abstract class Persona implements Comparable<Persona>, Serializable {
    private static final long serialVersionUID = 1L;

    private int dni;
    private String nombre;
    private String apellido;

    public Persona(int dni, String nombre, String apellido) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public int getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }

    public abstract void mostrarInfo();

    @Override
    public int compareTo(Persona otra) {
        return this.apellido.compareToIgnoreCase(otra.getApellido());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return dni == persona.dni;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }
}
