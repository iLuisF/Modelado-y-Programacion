
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
 *
 * @author Flores González Luis Brandon.
 * @version 1.0 - Noviembre/2015
 */
public class Criptograma {

    /**
     * Encripta un archivo con una llave simetrica dada.
     *
     * @param llave Llave simetrica para encriptar el archivo.
     * @param archivoEntrada Nombre del archivo de entrada.
     * @param archivoSalida Nombre del archivo de salida.
     * @throws java.security.NoSuchAlgorithmException Cuando un algoritmo es
     * requerido pero no esta disponible en el ambiente.
     * @throws javax.crypto.NoSuchPaddingException Cuando un mecanismo de
     * relleno particular es requerido pero no esta disponible en el ambiente.
     * @throws java.security.InvalidKeyException Cuando hay llaves invalidad
     * (codificación invalida, longitud mal, etc).
     * @throws java.io.IOException Cuando hay una falla en operaciones I/O.
     * @throws java.io.FileNotFoundException Cuando un archivo no existe.
     * @throws javax.crypto.IllegalBlockSizeException Cuando la longitud de
     * información proveida a un bloque cipher es incorrecta.
     * @throws javax.crypto.BadPaddingException Cuando un mecanismo de relleno
     * es esperado por la información de entrada pero la información no es
     * adecuada.
     */
    public void encriptar(String llave, File archivoEntrada, File archivoSalida)
            throws NoSuchAlgorithmException, BadPaddingException, IOException,
            InvalidKeyException, FileNotFoundException, NoSuchPaddingException,
            IllegalBlockSizeException {
        doCriptograf(Cipher.ENCRYPT_MODE, llave, archivoEntrada, archivoSalida);
    }

    /**
     * Descencripta un archivo con una llave simetrica dada.
     *
     * @param llave LLave simetrica para descencriptar el archivo.
     * @param archivoEntrada Nombre del archivo de entrada.
     * @param archivoSalida Nombre del archivo de salida.
     * @throws java.security.NoSuchAlgorithmException Cuando un algoritmo es
     * requerido pero no esta disponible en el ambiente.
     * @throws javax.crypto.NoSuchPaddingException Cuando un mecanismo de
     * relleno particular es requerido pero no esta disponible en el ambiente.
     * @throws java.security.InvalidKeyException Cuando hay llaves invalidad
     * (codificación invalida, longitud mal, etc).
     * @throws java.io.IOException Cuando hay una falla en operaciones I/O.
     * @throws java.io.FileNotFoundException Cuando un archivo no existe.
     * @throws javax.crypto.IllegalBlockSizeException Cuando la longitud de
     * información proveida a un bloque cipher es incorrecta.
     * @throws javax.crypto.BadPaddingException Cuando un mecanismo de relleno
     * es esperado por la información de entrada pero la información no es
     * adecuada.
     */
    public void descencriptar(String llave, File archivoEntrada,
            File archivoSalida) throws NoSuchAlgorithmException,
            IllegalBlockSizeException, NoSuchPaddingException, IOException,
            InvalidKeyException, FileNotFoundException, BadPaddingException {
        doCriptograf(Cipher.DECRYPT_MODE, llave, archivoEntrada, archivoSalida);
    }

    /* 
     * Encripta o desencripta un archivo usando aes con una llave simetrica
     * shaes.
     * @param Modocipher Modo en que se usara chiper, ya sea para encriptar o
     * desencriptar.
     * @param llave LLave simetrica para descencriptar el archivo.
     * @param archivoEntrada Nombre del archivo de entrada.
     * @param archivoSalida Nombre del archivo de salida.     
     * @throws java.security.NoSuchAlgorithmException Cuando un algoritmo es
     * requerido pero no esta disponible en el ambiente.
     * @throws javax.crypto.NoSuchPaddingException  Cuando un mecanismo de 
     * relleno particular es requerido pero no esta disponible en el ambiente.
     * @throws java.security.InvalidKeyException Cuando hay llaves invalidad
     * (codificación invalida, longitud mal, etc).
     * @throws java.io.IOException Cuando hay una falla en operaciones I/O.
     * @throws java.io.FileNotFoundException Cuando un archivo no existe.
     * @throws javax.crypto.IllegalBlockSizeException Cuando la longitud de 
     * información proveida a un bloque cipher es incorrecta.
     * @throws javax.crypto.BadPaddingException Cuando un mecanismo de relleno
     * es esperado por la información de entrada pero la información no es 
     * adecuada.
     */
    private void doCriptograf(int Modocipher, String llave, File archivoEntrada,
            File archivoSalida) throws NoSuchAlgorithmException, IOException,
            NoSuchPaddingException, InvalidKeyException, FileNotFoundException,
            IllegalBlockSizeException, BadPaddingException {
        Key llaveSecreta = new SecretKeySpec(llave.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Modocipher, llaveSecreta);
        FileOutputStream flujoSalida;
        FileInputStream flujoEntrada = new FileInputStream(archivoEntrada);
        byte[] bytesEntrada = new byte[(int) archivoEntrada.length()];
        flujoEntrada.read(bytesEntrada);
        byte[] bytesSalida = cipher.doFinal(bytesEntrada);
        flujoSalida = new FileOutputStream(archivoSalida);
        flujoSalida.write(bytesSalida);
        flujoSalida.close();
    }
}
