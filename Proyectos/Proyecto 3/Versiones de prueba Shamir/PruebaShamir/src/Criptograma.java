 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException; 
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
 
/**
 * Clase para encriptar y desencriptar archivos.
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class Criptograma {
       
    /**
     * Encripta un archivo con una llave simetrica dada.
     * @param llave Llave simetrica para encriptar el archivo.
     * @param archivoEntrada Nombre del archivo de entrada.
     * @param archivoSalida Nombre del archivo de salida.
     * @throws CriptogramaException 
     */
    public void encriptar(String llave, File archivoEntrada, File archivoSalida)
            throws CriptogramaException {
        doCrypto(Cipher.ENCRYPT_MODE, llave, archivoEntrada, archivoSalida);
    }
 
    /**
     * Descencripta un archivo con una llave simetrica dada.
     * @param llave LLave simetrica para descencriptar el archivo.
     * @param archivoEntrada Nombre del archivo de entrada.
     * @param archivoSalida Nombre del archivo de salida.
     * @throws CriptogramaException 
     */
    public void descencriptar(String llave, File archivoEntrada, File archivoSalida)
            throws CriptogramaException {
        doCrypto(Cipher.DECRYPT_MODE, llave, archivoEntrada, archivoSalida);
    }
 
    /*     
     * @param Modocipher Modo en que se usara chiper, ya sea para encriptar o
     * desencriptar.
     * @param llave LLave simetrica para descencriptar el archivo.
     * @param archivoEntrada Nombre del archivo de entrada.
     * @param archivoSalida Nombre del archivo de salida.
     * @throws CriptogramaException 
     */
    private void doCrypto(int Modocipher, String llave, File archivoEntrada,
            File archivoSalida) throws CriptogramaException {
        try {
            Key llaveSecreta = new SecretKeySpec(llave.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Modocipher, llaveSecreta);             
            FileOutputStream flujoSalida;
            try (FileInputStream flujoEntrada = new FileInputStream(archivoEntrada)) {
                byte[] bytesEntrada = new byte[(int) archivoEntrada.length()];
                flujoEntrada.read(bytesEntrada);
                byte[] bytesSalida = cipher.doFinal(bytesEntrada);
                flujoSalida = new FileOutputStream(archivoSalida);
                flujoSalida.write(bytesSalida);
            }
            flujoSalida.close();             
        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException ex) {
            System.out.println(ex.getMessage() + "\n" + ex.toString() + "\n"
                    + ex.getLocalizedMessage());
        }
    }
}