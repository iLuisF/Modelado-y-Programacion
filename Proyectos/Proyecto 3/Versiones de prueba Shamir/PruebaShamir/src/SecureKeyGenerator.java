/**
  * Clase para la costruccion de llaves seguras utilizando el
  * algoritmo SHA-256.
  */
import java.security.MessageDigest;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class SecureKeyGenerator{

    private final String password;

    /**
     * Construye una instancia con el password de el usuario.
     * @param password - cadena que contiene el password proporcionado.
     */
    public SecureKeyGenerator(String password) {
	this.password = password;
    }

    /**
     * Aplica el algoritmo SHA-256 para mapear una llave segur a en
     * forma de un nùmero entero(BigInteger) a partir de el password
     * que tiene como atributo este objeto.
     * @return - La llave generada en forma de entero.
     */
    public BigInteger makeKey(){
	byte[] bytes = new byte[0];
    	try{
	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    digest.update(password.getBytes());
	    bytes = digest.digest();

	}catch(NoSuchAlgorithmException e){//Nunca se arroja la excepciòn.
	}
	StringBuffer hexString = new StringBuffer();
	for (int i = 0; i < bytes.length; i++) {
	    String hex = Integer.toHexString(0xff & bytes[i]);
	    if(hex.length() == 1){
		hexString.append('0');
	    }
	    hexString.append(hex);
	}
	BigInteger key = new BigInteger(hexString.toString(), 16);
	return key;
    }
 
}