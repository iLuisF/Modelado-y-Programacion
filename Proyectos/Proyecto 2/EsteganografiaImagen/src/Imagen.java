
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Representación de una imagen solo con los servicios necesarios para la
 * implementación de esteganografia.
 *
 * @author Flores Gonzalez Luis Brandon.
 * @version 1.0
 */
public class Imagen {

    private final String ruta;

    public Imagen(String ruta) {
        this.ruta = ruta;
    }

    /**
     * Regresa una imagen de una ruta dada. En caso de no haber imagen, se
     * manejara con una exepción.
     *
     * @return Un BufferedImage de la imagen de la ruta del archivo dado.
     * @throws java.io.IOException Excepción en caso de no poder leer la imagen.
     */
    public BufferedImage getImagen() throws IOException {
        BufferedImage imagen = null;
        File archivo = new File(this.ruta);
        imagen = ImageIO.read(archivo);
        return imagen;
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
}
