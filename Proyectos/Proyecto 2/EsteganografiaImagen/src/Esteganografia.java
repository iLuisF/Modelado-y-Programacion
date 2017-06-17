
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Representacíón de esteganografía de tal forma para ocultar mensajes de texto
 * claro en archivos de imágenes. El método usado para almacenar el archivo de
 * texto en una imagen consiste en el uso del bit menos significativo de cada
 * byte de datos de la imagen para guardar un bit de los datos a ocultar.
 *
 * @author Flores González Luis Brandon.
 * @version 1.0
 */
public class Esteganografia {

    private final Imagen imagen;

    /**
     * Representación de esteganografía apartir de una imagen.
     *
     * @param imagen Imagen para uso tanto para cifrar o decifrar un texto.
     */
    public Esteganografia(Imagen imagen) {
        this.imagen = imagen;
    }

    /**
     * Genera el formato adecuado de un byte de un numero entero.
     *
     * @param i El entero a convertir.
     * @return Regresa un arreglo byte[4], este se genera apartir de un entero.
     */
    public byte[] convertirABit(int i) {
        byte byte0 = (byte) ((i & 0x000000FF));
        byte byte1 = (byte) ((i & 0x0000FF00) >>> 8);
        byte byte2 = (byte) ((i & 0x00FF0000) >>> 16);
        byte byte3 = (byte) ((i & 0xFF000000) >>> 24);
        return (new byte[]{byte3, byte2, byte1, byte0});        
    }

    /**
     * Crea un espacio de usuario para editar y guardar bytes, es decir, se crea
     * una nueva imagen del mismo tamaño de la imagen original y un area de
     * graficos se crea en ella. Se translada la imagen original a la nueva
     * imagen. Como un beneficio adicional de memoria, los recursos utilizados
     * por la nueva imagen se liberan. Todos los datos son creados y por lo
     * tanto se pueden modificar en Java.
     *
     * @param imagen La imagen para poner en el espacio de usuario.
     * @return El espacio de usuario de la imagen original.
     */
    public BufferedImage getEspacioUsuario(BufferedImage imagen) {
        BufferedImage nuevaImagen = new BufferedImage(imagen.getWidth(),
                imagen.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D graficas = nuevaImagen.createGraphics();
        graficas.drawRenderedImage(imagen, null);
        graficas.dispose();
        return nuevaImagen;
    }

    /**
     *
     * Agrega el texto al arreglo de bytes de la imagen, se usa la tecnica del
     * bit menos significado.
     *
     * @param imagen Arreglo de datos que representa una imagen.
     * @param datos Arreglo de datos que representa un texto.
     * @param longitud Indicador de longitud.
     * @return Conjunto de los tres parametros dados, como un solo arreglo de
     * bytes.
     */
    private byte[] codificarTexto(byte[] imagen, byte[] datos, int longitud) {
        if (datos.length + longitud > imagen.length) {
            throw new IllegalArgumentException("El archivo no es lo suficiente "
                    + "grande para contener el texto.");
        }
        for (int i = 0; i < datos.length; i++) {
            int agregar = datos[i];
            for (int bit = 7; bit >= 0; --bit, ++longitud) {
                int b = (agregar >>> bit) & 1;
                imagen[longitud] = (byte) ((imagen[longitud] & 0xFE) | b);
            }
        }
        return imagen;
    }

    /**
     * Recupera el texto oculto de una imagen.
     *
     * @param imagen Arreglo de informacion, representando una imagen.
     * @return Arreglo de informacion que contiene el texto oculto.
     */
    private byte[] decodificarTexto(byte[] imagen) {
        int longitud = 0;
        int mensaje = 32; //Indicador de posicion de longitud mensaje.
        for (int i = 0; i < 32; ++i) {
            longitud = (longitud << 1 | imagen[i] & 1);
        }
        byte[] resultado = new byte[longitud];
        for (int b = 0; b < resultado.length; ++b) {
            for (int i = 0; i < 8; ++i, ++mensaje) {
                resultado[b] = (byte) ((resultado[b] << 1) | (imagen[mensaje] & 1));
            }
        }
        return resultado;
    }

    /**
     * Encripta una imagen con el texto deseado, el archivo de salida será de
     * tipo .png
     *
     * @param mensaje El texto para guardar en la imagen.
     * @param rutaSalida Nombre de la imagen, que contendra el mensaje oculto.
     * @throws java.io.IOException
     */
    public void codificarImagen(String mensaje, String rutaSalida)
            throws IOException {
        BufferedImage imagenOriginal = this.imagen.getImagen();
        BufferedImage espacioUsuario = getEspacioUsuario(imagenOriginal);
        espacioUsuario = agregarTexto(espacioUsuario, mensaje);
        this.imagen.guardarImagen(espacioUsuario, new File(".\\" + rutaSalida),
                "png");
    }

    /**
     * Se asume que la imagen es de tipo .png y se extrae el texto oculto de una
     * imagen. En caso de que no contenga un mensaje, se manejara con una
     * excepción.
     *
     * @return Una cadena con el mensaje oculto.
     * @throws java.io.IOException Excepción en caso de que no tenga mensaje
     * oculto la imagen.
     *
     */
    public String decodificarImagen() throws IOException {
        byte[] decodificacion = null;
        BufferedImage espacioUsuario = getEspacioUsuario(this.imagen.getImagen());
        decodificacion = decodificarTexto(this.imagen.getArregloByte(espacioUsuario));
        return new String(decodificacion);
    }

    /**
     * Agrega el texto en una imagen.
     *
     * @param imagen Imagen donde se guardara el texto.
     * @param texto El texto para guardar en la imagen.
     * @return La imagen con el texto oculto en ella.
     * @throws java.io.IOException Excepción en caso de que la imagen no pueda
     * contener el texto.
     */
    private BufferedImage agregarTexto(BufferedImage imagen, String texto)
            throws IOException {
        byte img[] = this.imagen.getArregloByte(imagen);
        byte mensaje[] = texto.getBytes();
        byte longitud[] = convertirABit(mensaje.length);
        codificarTexto(img, longitud, 0); //0 primera posicion.
        codificarTexto(img, mensaje, 32); //4 bytes (32 bits) de la longitud.        
        return imagen;
    }
}
