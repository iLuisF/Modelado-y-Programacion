
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Prueba en conjunto del esquema de shamir, shaes y aes. 
 * 
 * @author Flores González Luis Brandon.
 * @version 1.0 - Noviembre/2015
 */
public class Main {

    /**
     * Método para probar el esquema de shamir, shaes y aes.
     *
     * @param args La entrada del programa requeria lo siguiente. 
     * <strong>args[0]:</strong> el caracter 'c' cifrar o 'd' decifrar.
     * <p><strong>En caso de escoger cifrar.</strong></p>
     * <ul>
     * <li><strong>args[1]:</strong> El nombre del archivo en el que serán 
     * guardadas las n evaluaciones del polinomio.</li>
     * <li><strong>args[2]:</strong> El número total de evaluaciones requeridas 
     * (n>2)</li>
     * <li><strong>args[3]:</strong> El número minimo de puntos necesarios para 
     * descifrar (1 < k <= n)</li>
     * <li><strong>args[4]:</strong> El nombre del archivo con el documento 
     * claro a encriptar.</li>     
     * <p><strong>En caso de escoger descifrar.</strong></p>
     * <li><strong>args[1]:</strong> El nombre del archivo con, al menos, k 
     * de las n evaluaciones del polinomio.</li>
     * <li><strong>args[2]:</strong> El nombre del archivo cifrado.</li>
     * </ul>
     * <p><strong>La salida del programa es de la siguiente forma.</strong></p>
     * <p><strong>En caso de cifrar:</strong></p>
     * <ul>
     * <li>El archivo con el documento cifrado usando AES.</li>
     * <li>El archivo con n parejas (x, p(x)) de las evaluaciones del
     * polinomio.</li>
     * <p><strong>En caso de decifrar.</strong></p>
     * <li>El archivo con documento claro y con el nombre original.</li>
     * </ul>
     */
    public static void main(String[] args) {
        try {
            Shamir shamir = new Shamir();
            switch (args[0]) {
                case "c":
                    Console consola = System.console();
                    SecureKeyGenerator llave; 
                    char[] password = consola.readPassword("%s", "Contraseña:");
                    ManejadorArchivo archivoEvaluaciones = new ManejadorArchivo(
                            args[1] + ".frg");     
                    llave = new SecureKeyGenerator(String.valueOf(password));
                    String llaveSim = llave.getLlaveSim128();
                    Puntos llaveFragmen = shamir.construirSecreto(
                            new BigInteger(llaveSim), Integer.parseInt(args[3]),
                            Integer.parseInt(args[2]));
                    archivoEvaluaciones.escribirArchivo(llaveFragmen.toSting());                                                                 
                    Criptograma encriptador = new Criptograma();
                    encriptador.encriptar(String.valueOf(llaveSim),
                            new File(args[4]),
                            new File(args[4] + ".aes"));
                    System.out.println("\nSe generarón los siguientes archivos."
                            + "\nArchivo cifrado: " + args[4] + ".aes"
                            + "\nArchivo con " + args[2] + " parejas (X, P(X)) "
                            + "de las evaluaciones del polinomio: " + args[1]
                            + ".frg"
                            + "\n\nSe deben usar al menos " + args[3] + 
                            " evaluaciones para descifrar el archivo .aes");
                    break;
                case "d":
                    Criptograma descencriptador = new Criptograma();
                    ManejadorArchivo archivoFrag = new ManejadorArchivo(args[1] 
                            + ".frg");
                    Puntos puntos = new Puntos();
                    puntos.convertirAPunto(archivoFrag.leerArchivo());
                    BigInteger llaveSimetric = (shamir.reconstruirSecreto(
                            puntos.getPuntosX(), puntos.getPuntosY(), 
                            puntos.getNumPuntos()));                    
                    descencriptador.descencriptar(String.valueOf(llaveSimetric),
                            new File(args[2] + ".aes"), new File(args[2]));
                    System.out.println("\nSe generarón los siguientes archivos."
                            + "\nArchivo descifrado: " + args[2]
                            + "\n\nSe uso el archivo " + args[1] +".frg con "
                            + puntos.getNumPuntos() 
                            +" evaluaciones del polinomio.");  
                    break;
                default:
                    System.out.println("Escoge una opción, el caracter1 'c'"
                            + "para encriptar y el caracter 'd' para "
                            + "desencriptar");
                    break;
            }
        } catch (NoSuchAlgorithmException | IllegalBlockSizeException | 
                NoSuchPaddingException | IOException | InvalidKeyException | 
                BadPaddingException ex) {
            System.out.println(ex.getMessage() + "\n" + 
                    ex.getLocalizedMessage());
        }
    }
}
