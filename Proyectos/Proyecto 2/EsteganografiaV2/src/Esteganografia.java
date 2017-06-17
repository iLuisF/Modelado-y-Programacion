
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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

    /**
     * Genera el formato adecuado de un byte de un numero entero.
     *
     * @param i El entero a convertir.
     * @return Regresa un arreglo byte[4], este se genera apartir de un entero.
     */
    public byte[] convertirABit(int i) {
        return (new byte[]{0, 0, 0, (byte) (i & 0x000000FF)});
    }

    /**
     * Regresa una imagen de una ruta dada. En caso de no haber imagen, se
     * manejara con una exepción.
     *
     * @param ruta El nombre de la ruta completa de la imagen.
     * @return Un BufferedImage de la imagen de la ruta del archivo dado.
     * @throws java.io.IOException Excepción en caso de no poder leer la imagen.
     */
    public BufferedImage getImagen(String ruta) throws IOException {
        BufferedImage imagen = null;
        File archivo = new File(ruta);
        imagen = ImageIO.read(archivo);
        return imagen;
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
     * Regresa el arreglo de bytes de una imagen.
     *
     * @param imagen La imagen para obtener un arreglo de bytes.
     * @return Arreglo de bytes de la imagen dada.
     */
    public byte[] getArregloByte(BufferedImage imagen) {
        WritableRaster raster = imagen.getRaster();
        DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
        return buffer.getData();
    }

    /**
     * Método para guardar una imagen apartir de un BufferedImage, File y el
     * nombre que se le quiera dar a la nueva imagen.
     *
     * @param imagen La imagen que se requiere guardar.
     * @param archivo Archivo para guardar la imagen.
     * @param ext La extension y por lo tanto el formato del archivo para ser
     * guardado.
     * @throws java.io.IOException Excepción en caso de que no se pueda guardar
     * la imagen.
     */
    public void guardarImagen(BufferedImage imagen, File archivo, String ext)
            throws IOException {
        archivo.delete();
        ImageIO.write(imagen, ext, archivo);
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
    public byte[] codificarTexto(byte[] imagen, byte[] datos, int longitud) {
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
    public byte[] decodificarTexto(byte[] imagen) {
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
     * @param rutaEntrada La ruta (folder) que contendra la imagen para
     * modificar.
     * @param mensaje El texto para guardar en la imagen.
     * @param rutaSalida Nombre de la imagen, que contendra el mensaje oculto.
     * @throws java.io.IOException
     */
    public void codificar(String rutaEntrada, String mensaje, String rutaSalida)
            throws IOException {
        BufferedImage imagenOriginal = getImagen(rutaEntrada);
        BufferedImage imagen = getEspacioUsuario(imagenOriginal);
        imagen = agregarTexto(imagen, mensaje);
        guardarImagen(imagen, new File(".\\" + rutaSalida), "png");
    }

    /**
     * Se asume que la imagen es de tipo .png y se extrae el texto oculto de una
     * imagen. En caso de que no contenga un mensaje, se manejara con una
     * excepción.
     *
     * @param rutaImagen
     * @return Una cadena con el mensaje oculto.
     * @throws java.io.IOException Excepción en caso de que no tenga mensaje
     * oculto la imagen.
     *
     */
    public String decodificar(String rutaImagen) throws IOException {
        byte[] decodificacion = null;
        BufferedImage imagen = getEspacioUsuario(getImagen(rutaImagen));
        decodificacion = decodificarTexto(getArregloByte(imagen));
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
    public BufferedImage agregarTexto(BufferedImage imagen, String texto)
            throws IOException {
        byte img[] = getArregloByte(imagen);
        byte mensaje[] = texto.getBytes();
        byte longitud[] = convertirABit(mensaje.length);
        codificarTexto(img, longitud, 0); //0 primera posicion.
        codificarTexto(img, mensaje, 32); //4 bytes (32 bits) de la longitud.        
        return imagen;
    }
}
