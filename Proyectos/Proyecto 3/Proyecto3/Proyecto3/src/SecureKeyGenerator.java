
/**
 * Clase para la costruccion de llaves seguras utilizando el algoritmo SHA-256.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0 - Noviembre/2015
 */
import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class SecureKeyGenerator {

    private final String password;

    /**
     * Construye una instancia con el password de el usuario.
     *
     * @param password - cadena que contiene el password proporcionado.
     */
    public SecureKeyGenerator(String password) {
        this.password = password;
    }

    /*
     * Aplica el algoritmo SHA-256 para mapear una llave segura en
     * forma de un número entero(BigInteger) a partir de el password
     * que tiene como atributo este objeto.
     * @return - La llave generada en forma de entero.
     */
    private BigInteger makeKey() {
        byte[] bytes = new byte[0];
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(this.password.getBytes());
            bytes = digest.digest();

        } catch (NoSuchAlgorithmException e) {//Nunca se arroja la excepciòn.
        }
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xff & bytes[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        BigInteger key = new BigInteger(hexString.toString(), 16);
        return key;
    }

    /**
     * Mapea una llave segura en forma de número entero(BigInteger) a partir de
     * la contraseña que tiene como atributo este objeto.
     *
     * @return La llave generada en forma de entero(BigInteger).
     */
    public String getLlaveSim128() {
        char[] llaveSim128 = makeKey().toString().toCharArray();
        String llave128 = "";
        for (int i = 0; i < 16; i++) {
            llave128 = llave128 + String.valueOf(llaveSim128[i]);
        }
        return llave128;
    }
    
}
