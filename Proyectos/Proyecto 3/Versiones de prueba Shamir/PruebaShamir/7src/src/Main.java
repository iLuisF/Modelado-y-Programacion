
import java.math.BigInteger;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Cifrando secreto...");
        BigInteger secreto = new BigInteger("123456789");
        Shamir shamir = new Shamir();                  
        BigInteger[] secretoEncriptado = shamir.construirSecreto(secreto, 3, 5);
        System.out.print("Valores en Y: ");
        System.out.println(Arrays.toString(secretoEncriptado));
        System.out.println("Secreto cifrado: " + secreto);

        System.out.println("\nDecifrando secreto...");
        BigInteger[] valoresX = new BigInteger[]{
            new BigInteger("310710232656018587988492684788033037986"),
            new BigInteger("98623200286612444540506721511567195107"),
            new BigInteger("100013068739492621860823129420008088396"),};

        BigInteger[] valoresY = new BigInteger[]{
            new BigInteger("69413519709173349846435331234980304935264563123667846124245476982228308134933"),
            new BigInteger("32397968296940956999239763922011007912794431840982301930472677717675671107750"),
            new BigInteger("22645382338033733233407118594934339201085997109212270064240359004013736539282"),};
        
        System.out.println("Valor secreto: " + shamir.reconstruirSecreto(valoresX, valoresY, 3));

    }
}
