
import java.io.IOException;

/**
 * Clase de prueba que implementa la clase esteganografia.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class Main {
 
    /**
     * <p>
     * <strong>Entrada.</strong> El programa funciona en dos modalidades, para
     * ocultar (opción h) y para develar (opción u).</p>
     * <strong>Ocultar.</strong> Se debe proporcionar, en la línea de llamada:
     * <ul>
     * <li>1. La opción h. (args[0])</li>
     * <li>2. El nombre del archivo que contiene el texto a ocultar.
     * (args[1])</li>
     * <li>3. El nombre del archivo de imagen. (args[2])</li>
     * <li>4. El nombre del archivo de imagen resultante con los datos ocultos.
     * (args[3])</li>
     * </ul>
     * <strong>Develar.</strong> Se debe proporcionar, en la línea de llamada:
     * <ul>
     * <li>1. La opción u. (args[0])</li>
     * <li>2. El nombre del archivo con la imagen que contiene los datos
     * ocultos. (args[1])</li>
     * <li>3. El nombre del archivo en el que se guardará el texto develado.
     * (args[2])</li>
     * </ul>
     * <strong>Salida.</strong> Dependiendo de la opción seleccionada el
     * programa debe entregar lo que se especifica.
     * <ul>
     * <li><strong>Ocultar.</strong> El archivo de imagen con el texto oculto.
     * </li>
     * <li><strong>Develar.</strong> El archivo con el texto claro.</li>
     * </ul>
     *
     * @param args Argumentos dados, estos ya han sido especificados en la
     * descripción de este método. Todos los <strong> archivos leidos o
     * generados</strong> deberan estar o estaran en la carpeta donde se compile
     * este mismo programa.
     */
    public static void main(String[] args) {
        try {
            Esteganografia imagen = new Esteganografia();
            ManejadorArchivo texto;
            switch (args[0]) {
                case "h":
                    texto = new ManejadorArchivo(args[1]);
                    imagen.codificar(".\\" + args[2], texto.leerArchivo(), args[3]);
                    break;
                case "u":
                    texto = new ManejadorArchivo(args[2]);
                    texto.escribirArchivo(imagen.decodificar(".\\" + args[1]));
                    break;
                default:
                    System.out.println("Escoge una opcion.");
            }
        } catch (ArrayIndexOutOfBoundsException excepcion) {
            System.err.println("Error en el argumento. "
                    + excepcion.getMessage());
        } catch (IOException excepcion) {
            System.err.println(excepcion.getMessage());
        }
    }
}
