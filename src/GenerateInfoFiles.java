import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
// Se importan la librerias necesarias para la generación/edición de los archivos csv y generar datos aleatorios
public class GenerateInfoFiles {
//Se declara la clase GenerateInfoFiles que contiene el método main y los métodos para generar los archivos con información aleatoria
    public static void main(String[] args) {
        // Se Generan archivos de información
        boolean success = generateFiles();
        if (success) {
            System.out.println("Archivos de información generados exitosamente.");
        } else {
            System.err.println("Error al generar archivos de información.");
        }
    }
    /*Este método "main" es el punto de entrada del programa, dónde se llama el método "generateFiles()" que genera los archivos de información.
    Dependiendo el resultado de la generación de archivo imprime un mensaje de exito o de error*/

    private static boolean generateFiles() {
        // Directorio donde se generarán los archivos
        String directoryPath = "C:/Users/dortm/OneDrive/6 semestre/Conceptos fundamentales de programación/Entregas/cfdp/input_files/";

        // Generar archivos de vendedores, productos y ventas
        boolean success = createSalesmenFile(directoryPath);
        success &= createProductsFile(directoryPath);
        success &= createSalesFile(directoryPath);

        return success;
       /*Este método coordina la generación de los archivos, primero se define la ruta o carpeta dónde se guardarán los archivos generados.
         Luego se llaman 3 métodos ("createSalesmenFile", "createProductsFile", "createSalesFile") que son los encargados de generar la información de vendedores,
         productos y ventas respectivamente; al final por cada método regresa "True" o "False" dependiendo el estado de la generación de los archivos. */
    }

    private static boolean createSalesmenFile(String directoryPath) {
        try {
            FileWriter writer = new FileWriter(directoryPath + "salesmen_info_sub6.csv");
            Random random = new Random();

            for (int i = 1; i <= 30; i++) { // Generar información para 30 vendedores (ejemplo)
                String line = "TipoDocumento" + i + ";Documento" + i + ";NombresVendedor" + i + ";ApellidosVendedor" + i + "\n";
                writer.write(line);
            }

            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        /*Este método crea un archivo plano para vendedores, se crea un objeto FileWriter para escribir en el archivo. Luego desde el bucle for se genera información
          en este caso para 30 vendedores y escribe en cada línea del archivo. Paso siguiente se cierra el FileWriter y por último devuelve true si la operación fue correcta
          o false si se presentó algún error*/
    }

    private static boolean createProductsFile(String directoryPath) {
        try {
            FileWriter writer = new FileWriter(directoryPath + "products_info_sub6.csv");
            Random random = new Random();

            for (int i = 1; i <= 30; i++) { // Generar información para 10 productos (ejemplo)
                String line = "IDProducto" + i + ";NombreProducto" + i + ";Precio" + (random.nextDouble() * 100) + "\n";
                writer.write(line);
            }

            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        /*Este método crea un archivo plano para productos, se crea un objeto FileWriter para escribir en el archivo. Luego desde el bucle for se genera información
          en este caso para 30 productos y escribe en cada línea del archivo. Paso siguiente se cierra el FileWriter y por último devuelve true si la operación fue correcta
          o false si se presentó algún error*/
    }

    private static boolean createSalesFile(String directoryPath) {
        try {
            FileWriter writer = new FileWriter(directoryPath + "sales_info_sub6.csv");
            Random random = new Random();

            for (int i = 1; i <= 30; i++) { // Generar información para 10 ventas (ejemplo)
                String line = "TipoDocumento" + i + ";Documento" + i + "\n";
                for (int j = 1; j <= 3; j++) { // Cada venta con 3 productos (ejemplo)
                    line += "IDProducto" + j + ";" + random.nextInt(10) + "\n";
                }
                writer.write(line);
            }

            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        /*Este método crea un archivo plano para ventas, se crea un objeto FileWriter para escribir en el archivo. Luego desde el bucle for se genera información
          en este caso para 30 ventas y escribe en cada línea del archivo. Paso siguiente se cierra el FileWriter y por último devuelve true si la operación fue correcta
          o false si se presentó algún error*/
    }
}
