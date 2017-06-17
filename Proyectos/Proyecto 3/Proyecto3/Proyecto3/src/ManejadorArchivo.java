
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * En esta clase se implementa los métodos necesarios para leer los datos de un
 * archivo y escribir los datos en un archivo.
 *
 * @author Flores González Luis Brandon.
 * @version 1.1
 */
public class ManejadorArchivo {

    private final String archivo;

    /**
     * Constructor que solo indica el nombre de un archivo.
     *
     * @param archivo Nombre de un archivo.
     */
    public ManejadorArchivo(String archivo) {
        this.archivo = archivo;
    }

    /**
     * Método para escribir en un archivo una cadena dada.
     *
     * @param cadena Cadena que se escribira en el archivo.
     */
    public void escribirArchivo(String cadena) {
        FileWriter escritor = null;
        try {
            escritor = new FileWriter(this.archivo);
            escritor.write(cadena);
        } catch (IOException excepcion) {
            System.err.println(excepcion.getMessage());
        } finally {
            if (escritor != null) {
                try {
                    escritor.close();
                } catch (IOException excepcion) {
                    System.out.println(excepcion.getMessage());
                }
            }
        }
    }

    /**
     * Método para leer un texto a partir de lineas de un archivo.
     *
     * @return El texto completo de un archivo.
     */
    public String leerArchivo() {
        String linea = "";
        try {
            try (Scanner leer = new Scanner(new File(this.archivo))) {
                while (leer.hasNextLine()) {
                    linea = linea + leer.nextLine() + "\n";
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Archivo no encontrado." +
                    ex.getMessage());
        }
        return linea;
    }
}
