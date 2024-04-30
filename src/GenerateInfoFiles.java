import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInfoFiles {

    /**
     * Método para generar un archivo de ventas de un vendedor con datos pseudoaleatorios.
     * @param randomSalesCount Cantidad de ventas pseudoaleatorias por vendedor.
     * @param name Nombre del vendedor.
     * @param id ID del vendedor.
     */
    public static void createSalesMenFile(int randomSalesCount, String name, long id) {
        // Ruta donde se guardará el archivo
        String directoryPath = "C:/Users/dortm/OneDrive/6 semestre/Conceptos fundamentales de programación/Entregas/cfdp/input_files/";
        String fileName = directoryPath + "sales_info_Subgrupo6_" + name.toLowerCase().replace(" ", "_") + ".csv";
        try {
            FileWriter writer = new FileWriter(fileName);
            // Escribir encabezado del archivo
            writer.write("TipoDocumentoVendedor;NúmeroDocumentoVendedor\n");
            // Generar ventas pseudoaleatorias para el vendedor
            for (int i = 0; i < randomSalesCount; i++) {
                writer.write("CC;" + id + "\n");
                for (int j = 0; j < 3; j++) {
                    int productId = j + 1;
                    int quantity = new Random().nextInt(10) + 1; // Cantidad aleatoria entre 1 y 10
                    writer.write("IDProducto" + productId + ";" + quantity + "\n");
                }
            }
            writer.close();
            System.out.println("Archivo de ventas para " + name + " creado exitosamente: " + fileName);
        } catch (IOException e) {
            System.err.println("Error al crear el archivo de ventas para " + name + ": " + e.getMessage());
        }
    }

    /**
     * Método para generar un archivo con información pseudoaleatoria de productos.
     * @param productsCount Cantidad de productos a generar.
     */
    public static void createProductsFile(int productsCount) {
        // Ruta donde se guardará el archivo
        String directoryPath = "C:/Users/dortm/OneDrive/6 semestre/Conceptos fundamentales de programación/Entregas/cfdp/input_files/";
        String fileName = directoryPath + "products_info_subgrupo6.csv";
        try {
            FileWriter writer = new FileWriter(fileName);
            // Escribir encabezado del archivo
            writer.write("IDProducto;NombreProducto;PrecioPorUnidad\n");
            // Generar información pseudoaleatoria de productos
            for (int i = 0; i < productsCount; i++) {
                int productId = i + 1;
                String productName = "Producto" + productId;
                double price = new Random().nextDouble() * 100; // Precio aleatorio entre 0 y 100
                writer.write("IDProducto" + productId + ";" + productName + ";" + String.format("%.2f", price) + "\n");
            }
            writer.close();
            System.out.println("Archivo de productos creado exitosamente: " + fileName);
        } catch (IOException e) {
            System.err.println("Error al crear el archivo de productos: " + e.getMessage());
        }
    }

    /**
     * Método para generar un archivo con información de vendedores pseudoaleatoria.
     * @param salesmanCount Cantidad de vendedores a generar.
     */
    public static void createSalesManInfoFile(int salesmanCount) {
        // Ruta donde se guardará el archivo
        String directoryPath = "C:/Users/dortm/OneDrive/6 semestre/Conceptos fundamentales de programación/Entregas/cfdp/input_files/";
        String fileName = directoryPath + "salesman_info_subgrupo6.csv";
        try {
            FileWriter writer = new FileWriter(fileName);
            // Escribir encabezado del archivo
            writer.write("TipoDocumento;NúmeroDocumento;NombresVendedor;ApellidosVendedor\n");
            // Generar información pseudoaleatoria de vendedores
            for (int i = 0; i < salesmanCount; i++) {
                int salesmanId = i + 1;
                String salesmanName = "Vendedor" + salesmanId;
                String salesmanLastName = "Apellido" + salesmanId;
                writer.write("CC;" + salesmanId + ";" + salesmanName + ";" + salesmanLastName + "\n");
            }
            writer.close();
            System.out.println("Archivo de información de vendedores creado exitosamente: " + fileName);
        } catch (IOException e) {
            System.err.println("Error al crear el archivo de información de vendedores: " + e.getMessage());
        }
    }

    /**
     * Método principal para generar archivos de ventas, productos y vendedores.
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        int randomSalesCount = 10;
        int productsCount = 5;
        int salesmanCount = 3;

        createSalesMenFile(randomSalesCount, "Vendedor1", 123456789);
        createSalesMenFile(randomSalesCount, "Vendedor2", 987654321);
        createSalesMenFile(randomSalesCount, "Vendedor3", 123123123);

        createProductsFile(productsCount);

        createSalesManInfoFile(salesmanCount);
    }
}