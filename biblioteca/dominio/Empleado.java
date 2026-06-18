package biblioteca.dominio;

public class Empleado extends Persona {
    private static final long serialVersionUID = 1L;

    private int legajo;

    public Empleado(int dni, String nombre, String apellido, int legajo) {
        super(dni, nombre, apellido);
        this.legajo = legajo;
    }

    public int getLegajo() { return legajo; }

    @Override
    public void mostrarInfo() {
        System.out.println("Empleado [Legajo: " + legajo + "] "
                + getApellido() + ", " + getNombre()
                + " | DNI: " + getDni());
    }
}
