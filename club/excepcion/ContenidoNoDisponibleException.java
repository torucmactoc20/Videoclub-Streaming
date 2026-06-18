package club.excepcion;

public class ContenidoNoDisponibleException extends Exception {
    private static final long serialVersionUID = 1L;

    public ContenidoNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
