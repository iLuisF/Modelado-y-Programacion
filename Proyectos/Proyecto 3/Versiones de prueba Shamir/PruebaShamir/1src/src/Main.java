import java.math.BigInteger;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		SecureShamir shamir = new SecureShamir(new BigInteger[]{new BigInteger("4354563465464562")}, 
                        2, 4);
		ArrayList<BigInteger[]> shares = shamir.buildShares();
		
		for(BigInteger[] valueShares : shares) {
			for(int i = 0; i < valueShares.length; i++) {
				System.out.println(valueShares[i] + " ");
			}
			System.out.println("");
			System.out.println("Valor secreto: " + shamir.reconstruirSecreto(valueShares));
                        System.out.println("");
		}
		
		shamir.splitDomain();
	}
}