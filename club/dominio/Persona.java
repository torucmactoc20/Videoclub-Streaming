package club.dominio;

import java.io.Serializable;

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
    public int hashCode() {
        return dni;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Persona other = (Persona) obj;
        return this.dni == other.dni;
    }

    @Override
    public int compareTo(Persona o) {
        return this.apellido.compareTo(o.apellido);
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " Apellido: " + apellido;
    }
}
