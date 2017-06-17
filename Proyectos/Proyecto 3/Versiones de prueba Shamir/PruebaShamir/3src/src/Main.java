
import java.math.BigInteger;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        System.out.println("Cifrando secreto...");
        BigInteger[] secreto = {new BigInteger("43545634654645624545645")};
        SecureShamir shamir = new SecureShamir(secreto,
                3, 5);          
        BigInteger[] shares = shamir.buildShares().get(0);
        System.out.print("Valores en Y: ");
        System.out.println(Arrays.toString(shares));
        System.out.println("Secreto cifrado: " + secreto[0]);

        System.out.println("\nDecifrando secreto...");
        BigInteger[] secreto1 = new BigInteger[]{
            new BigInteger("326333684986618225367763903410119723257"),
            new BigInteger("18550160462380599370700163506167643179"),
            new BigInteger("203654774162094704222167923284507762588"),};

        BigInteger[] secreto2 = new BigInteger[]{
            new BigInteger("111968639301949192579376125940657777033829572347982774259077161535726102666640"),
            new BigInteger("8832553570916711864407016545291926093157973997137599955831270432259862878922"),
            new BigInteger("58475611027583134185408047978306945120585170387016632790775763680308131215371"),};
        System.out.println("Valor secreto: " + shamir.reconstruirSecreto(secreto1, secreto2));

    }
}
