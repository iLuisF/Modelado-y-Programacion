
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
     * Construye un listado de puntos (X, Y)
     * 
     * @param x Arreglo BigInteger[] de puntos x.
     * @param y Arreglo BigInteger[] de puntos y.
     */
    public Puntos(BigInteger[] x, BigInteger[] y){
        this.x = x;
        this.y = y;
    }
    
    /**
     * Constructor por omisión.
     */
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
     * forma (x, y).
     * @param cadena Cadena con puntos de la forma (x, y).
     * @return Regresa una lista de Puntos.
     */
    public Puntos convertirAPunto(String cadena){        
        StringTokenizer st = new StringTokenizer(cadena, "(),\t\n\r\f");
        BigInteger[] puntosX = new BigInteger[st.countTokens()/2];
        BigInteger[] puntosY = new BigInteger[st.countTokens()/2];
        int i = 0;        
        while(st.hasMoreTokens()){
            String valorX = st.nextToken();
            String valorY = st.nextToken();            
            puntosX[i] = new BigInteger(valorX.trim());
            puntosY[i] = new BigInteger(valorY.trim());
            i++;
        }
        setPuntosX(puntosX);
        setPuntosY(puntosY);
        return new Puntos(puntosX, puntosY);
    }
    
    /**
     * Asigna los puntos x.
     * @param x Arreglo BigInteger[] con puntos x.
     */
    public void setPuntosX(BigInteger[] x){
        this.x = x;
    }
    
    /**
     * Asigna los puntos y.
     * @param y Arreglo BigInteger[] con puntos y.
     */
    public void setPuntosY(BigInteger[] y){
        this.y = y;
    }
    
    /**
     * Regresa los puntos en x.
     * @return Arreglo BigInteger[] de puntos en x.
     */
    public BigInteger[] getPuntosX(){
        return this.x;
    }
    
    /**
     * Regresa los puntos en y.
     * @return Arreglo BigInteger[] de puntos en y.
     */
    public BigInteger[] getPuntosY(){
        return this.y;
    }
}
