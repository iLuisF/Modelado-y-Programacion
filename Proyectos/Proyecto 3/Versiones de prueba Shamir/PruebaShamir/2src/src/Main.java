import java.math.BigInteger;

public class Main {
	public static void main(String[] args) {
		SecureShamir shamir = new SecureShamir(new BigInteger[]{new BigInteger("43545634654645624545645")}, 
                        3, 5);
		BigInteger[] shares = shamir.buildShares().get(0);
		                          
                System.out.println("");                
                System.out.println("otro");                		
					
                    for(int i = 0; i < shares.length; i++) {                        
				System.out.println(shares[i]);
			}	
                        //Solo reconstruye el secreto
			System.out.println("Valor secreto: " + shamir.reconstruirSecreto(shares));                        				
                
                System.out.println("Prueba de reconstrucción");
                BigInteger[] puntos = new BigInteger[]{
                    new BigInteger("35443606910277264763761024133272918239410124788952692886599765299592703647838"),
new BigInteger("18301141108790011652364337239932305487448617670863614986497279443314150320459"),
new BigInteger("15721369958936624483719276474980728235518644592977209265426429105010482026360"),                 
                };
            //System.out.println("Valor secreto prueba" + shamir.reconstruirSecreto(puntos));                
		//shamir.splitDomain();
	}
}