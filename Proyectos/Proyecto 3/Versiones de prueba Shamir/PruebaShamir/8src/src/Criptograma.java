
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Flores González Luis Brandon.
 */
public class Criptograma {

    //private final String llaveSimetrica;
    SecretKeySpec llaveSimetrica;
    
    
    public Criptograma(String llaveSimetrica){        
        this.llaveSimetrica = new SecretKeySpec(llaveSimetrica.getBytes(), "AES");       
    }
    
    public void encriptar(String mensajeSecreto) throws InvalidKeyException, 
            IllegalBlockSizeException, BadPaddingException, 
            NoSuchAlgorithmException, NoSuchPaddingException{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, this.llaveSimetrica);
        byte[] campoCifrado = cipher.doFinal(mensajeSecreto.getBytes());
        //Por el momento lo imprimo en pantalla como bytes.
        System.out.println(new String(campoCifrado));
    }
    
    public void desencriptar(byte[] campoCifrado) throws InvalidKeyException, 
            IllegalBlockSizeException, BadPaddingException, 
            NoSuchAlgorithmException, NoSuchPaddingException{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, this.llaveSimetrica);
        byte[] datosDecifrados = cipher.doFinal(campoCifrado);
        //Por el momento lo imprimo en pantalla.
        String mensajeOriginal = new String(datosDecifrados);
        System.out.println(mensajeOriginal);
    }
    
}
