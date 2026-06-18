package biblioteca.repositorio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivo<T> implements IRepositorio<T> {
    private String nombreArchivo;

    public RepositorioArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @Override
    public void guardar(List<T> elementos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(elementos);
        } catch (IOException e) {
            System.out.println("Error al guardar en archivo: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> consultar() {
        File file = new File(nombreArchivo);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar archivo (Se creará una lista vacía): " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
