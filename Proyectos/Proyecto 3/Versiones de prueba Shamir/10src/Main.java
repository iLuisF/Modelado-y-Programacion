
import java.io.File;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Shamir shamir = new Shamir();
        String opcion = "";
        Scanner leer = new Scanner(System.in);
        System.out.println("Escribe c para cifrar o d para decifrar.");
        opcion = leer.nextLine();
        switch (opcion) {
            case "c":
                System.out.println("Escribe el nombre del archivo en el que serán"
                        + "guardadas las n evaluaciones del polinomio.");
                String nArchivo = leer.nextLine();
                System.out.println("Escribe el numero total de evaluaciones "
                        + "requeridas (n > 2)");
                String nEvalaluaciones = leer.nextLine();
                System.out.println("Escribe el numero minimo de puntos "
                        + "necesarios para descifrar (1 < k <= n)");
                String kEvaluaciones = leer.nextLine();
                //String archivoCifrar = leer.nextLine(); pendiente
                System.out.println("Escribe la contraseña");
                String contrasenia = leer.nextLine();
                ManejadorArchivo archivo = new ManejadorArchivo(nArchivo);
                Puntos secretoEncriptado = shamir.construirSecreto(
                        new BigInteger(contrasenia), Integer.parseInt(kEvaluaciones),
                        Integer.parseInt(nEvalaluaciones)); //3 5
                archivo.escribirArchivo(secretoEncriptado.toSting());
                break;
            case "d":
                Shamir shamir2 = new Shamir();
                ManejadorArchivo leerArchivo = new ManejadorArchivo("10.txt");
                //System.out.println(puntos.convertirAPunto(leerArchivo.leerArchivo()).toSting());                
                Puntos puntos = new Puntos();
                puntos.convertirAPunto(leerArchivo.leerArchivo());
                //System.out.println(Arrays.toString(puntos.getPuntosX()));
                System.out.println(shamir2.reconstruirSecreto(puntos.getPuntosX(), puntos.getPuntosY(), 3));
                break;
            default:
                System.out.println("Escoge una opción.");
        }

        //BigInteger secreto = new BigInteger("123456789");
        //Shamir shamir = new Shamir();
        //BigInteger[] secretoEncriptado = shamir.construirSecreto(secreto, 3, 5);
        //System.out.print("Valores en Y: ");
        //System.out.println(Arrays.toString(secretoEncriptado));
        //System.out.println("Secreto cifrado: " + secreto);
        /**
         * System.out.println("\nDecifrando secreto..."); BigInteger[] valoresX
         * = new BigInteger[]{ new
         * BigInteger("5849642495195048585744517977496843171322236845718687639562481189218187864371"),
         * new
         * BigInteger("19167882148745343546635400021193897438670651235467715983943600557682479322070"),
         * new
         * BigInteger("97039727552263178232225970747756588275432826694037400106182446724051743165954"),};
         * BigInteger[] valoresY = new BigInteger[]{ new
         * BigInteger("16244603194849962841216145482414886770955668814832667340822961484378062804698750306819019848750172781306138198252402970508607860477858514159463115822258980"),
         * new
         * BigInteger("8428188060566879908817976641526702997042200166264136124477113095082408043395394801075747389481027127946055296366676890191810884027848504735205392206882101"),
         * new
         * BigInteger("20548869472438769388852393858105229525342027126505895115207027913721534329425479199871665777596398739418809329732435269101988940482685392415907686270882927"),};
         * System.out.println("Valor secreto: " +
         * shamir.reconstruirSecreto(valoresX, valoresY, 3));
         */
        
         System.out.println("Probando la clase criptograma\n");
         //Funciona con 16 caracteres, ya que es de 128bits/16bytes
         String key = "01234567890123456789012345678901";
         File inputFile = new File("original.png");
         File encryptedFile = new File("document.encrypted");
         File decryptedFile = new File("document.decrypted");
         
         try {
         Criptograma criptograma = new Criptograma();
         criptograma.encriptar(key, inputFile, encryptedFile);
         criptograma.descencriptar(key, encryptedFile, decryptedFile);
         } catch (CryptoException ex) {
         System.out.println(ex.getMessage());
         }
         
    }
}
