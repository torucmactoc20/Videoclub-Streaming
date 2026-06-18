package club.repositorio;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivo<T> implements IRepositorio<T> {

    private File archivo;

    public RepositorioArchivo(String nombreArchivo) {
        this.archivo = new File(nombreArchivo);
    }

    @Override
    public void guardar(List<T> elementos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(archivo))) {
            oos.writeObject(elementos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<T> consultar() {
        if (!archivo.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(archivo))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
