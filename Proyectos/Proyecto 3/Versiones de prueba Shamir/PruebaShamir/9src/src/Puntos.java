
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * Representación de una lista de puntos (X, Y).
 *
 * @author Flores González Luis Brandon.
 * @version 1.0.
 */
public class Puntos {
   
    private BigInteger[] x;
    private BigInteger[] y;
    
    /**
     * Construye un punto (x, y)
     * @param x
     * @param y
     */
    public Puntos(BigInteger[] x, BigInteger[] y){
        this.x = x;
        this.y = y;
    }
    
    public Puntos(){
        
    }
    
    /**
     * Muestra una lista de puntos de la forma (x, y).
     * @return 
     */
    public String toSting(){
        String puntos = "";
        for(int i = 0; i < getPuntosX().length; i++){            
                puntos =  "("+ getPuntosX()[i] + ", " + getPuntosY()[i]
                        + ")\n" + puntos;            
        }
        return puntos;
    }
        
    /**
     * Convierte una cadena a una lista de puntos. La cadena debe estar de la 
     * forma (x, y)
     * @param cadena
     * @return 
     */
    public Puntos convertirAPunto(String cadena){        
        StringTokenizer st = new StringTokenizer(cadena, "(),\t\n\r\f");
        BigInteger[] x = new BigInteger[st.countTokens()/2];
        BigInteger[] y = new BigInteger[st.countTokens()/2];
        int i = 0;        
        while(st.hasMoreTokens()){
            String valorX = st.nextToken();
            String valorY = st.nextToken();            
            x[i] = new BigInteger(valorX.trim());
            y[i] = new BigInteger(valorY.trim());
            i++;
        }
        setPuntosX(x);
        setPuntosY(y);
        return new Puntos(x, y);
    }
    
    public void setPuntosX(BigInteger[] x){
        this.x = x;
    }
    
    public void setPuntosY(BigInteger[] y){
        this.y = y;
    }
    
    public BigInteger[] getPuntosX(){
        return this.x;
    }
    
    public BigInteger[] getPuntosY(){
        return this.y;
    }
}
