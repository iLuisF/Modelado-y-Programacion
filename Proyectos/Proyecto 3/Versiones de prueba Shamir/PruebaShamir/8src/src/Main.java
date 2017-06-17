
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
            new BigInteger("5849642495195048585744517977496843171322236845718687639562481189218187864371"),
            new BigInteger("19167882148745343546635400021193897438670651235467715983943600557682479322070"),
            new BigInteger("97039727552263178232225970747756588275432826694037400106182446724051743165954"),};        
        BigInteger[] valoresY = new BigInteger[]{
            new BigInteger("16244603194849962841216145482414886770955668814832667340822961484378062804698750306819019848750172781306138198252402970508607860477858514159463115822258980"),
            new BigInteger("8428188060566879908817976641526702997042200166264136124477113095082408043395394801075747389481027127946055296366676890191810884027848504735205392206882101"),
            new BigInteger("20548869472438769388852393858105229525342027126505895115207027913721534329425479199871665777596398739418809329732435269101988940482685392415907686270882927"),};        
        System.out.println("Valor secreto: " + shamir.reconstruirSecreto(valoresX, valoresY, 3));
    }
}
