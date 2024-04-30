import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    /**
     * Método para leer la información de los vendedores desde el archivo de vendedores.
     * @param filePath Ruta del archivo de información de vendedores.
     * @return Mapa con el nombre de los vendedores y su cantidad de dinero recaudada (inicialmente 0).
     */
    private static Map<String, Double> readSalesMenInfo(String filePath) {
        Map<String, Double> salesData = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            // Leer cada línea del archivo
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 2) {
                    // Agregar vendedor al mapa con cantidad de dinero inicializada en 0
                    salesData.put(parts[2] + " " + parts[3], 0.0);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de información de vendedores: " + e.getMessage());
        }
        return salesData;
    }

    /**
     * Método para ordenar y escribir el reporte de ventas de los vendedores en un archivo.
     * @param salesData Mapa con el nombre de los vendedores y su cantidad de dinero recaudada.
     * @param outputFilePath Ruta del archivo de salida para el reporte de ventas.
     */
    private static void writeSalesReport(Map<String, Double> salesData, String outputFilePath) {
        try {
            // Ordenar vendedores por cantidad de dinero recaudada de mayor a menor
            List<Map.Entry<String, Double>> sortedSales = new ArrayList<>(salesData.entrySet());
            sortedSales.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));

            // Escribir el reporte de ventas de vendedores en un nuevo archivo
            FileWriter writer = new FileWriter(outputFilePath);
            for (Map.Entry<String, Double> entry : sortedSales) {
                writer.write(entry.getKey() + ";" + entry.getValue() + "\n");
            }
            writer.close();
            System.out.println("Reporte de ventas de vendedores creado exitosamente: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error al escribir el reporte de ventas de vendedores: " + e.getMessage());
        }
    }

    /**
     * Método para leer la información de los productos desde el archivo de productos.
     * @param filePath Ruta del archivo de información de productos.
     * @return Lista de cadenas con la información de los productos.
     */
    private static List<String> readProductsInfo(String filePath) {
        List<String> productData = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            // Leer cada línea del archivo
            while ((line = reader.readLine()) != null) {
                productData.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de información de productos: " + e.getMessage());
        }
        return productData;
    }

    /**
     * Método para ordenar y escribir el reporte de productos vendidos por cantidad en un archivo.
     * @param productData Lista de cadenas con la información de los productos.
     * @param outputFilePath Ruta del archivo de salida para el reporte de productos vendidos.
     */
    private static void writeProductSalesReport(List<String> productData, String outputFilePath) {
        try {
            // Ordenar productos por cantidad vendida en forma descendente
            List<String> sortedProducts = new ArrayList<>(productData);
            sortedProducts.sort(Collections.reverseOrder());

            // Escribir el reporte de productos vendidos por cantidad en un nuevo archivo
            FileWriter writer = new FileWriter(outputFilePath);
            for (String product : sortedProducts) {
                writer.write(product + "\n");
            }
            writer.close();
            System.out.println("Reporte de productos vendidos creado exitosamente: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error al escribir el reporte de productos vendidos: " + e.getMessage());
        }
    }

    /**
     * Método principal para ejecutar las tareas de generación de reportes.
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        String directoryPath = "C:/Users/dortm/OneDrive/6 semestre/Conceptos fundamentales de programación/Entregas/cfdp/input_files/";
        String salesmanInfoFilePath = directoryPath + "salesman_info_subgrupo6.csv";
        String productsInfoFilePath = directoryPath + "products_info_subgrupo6.csv";
        String outputSalesReportFilePath = directoryPath + "sales_report_subgrupo6.csv";
        String outputProductSalesReportFilePath = directoryPath + "product_sales_report_Subgrupo6.csv";

        // Leer la información de los vendedores
        Map<String, Double> salesData = readSalesMenInfo(salesmanInfoFilePath);

        // Escribir el reporte de ventas de vendedores
        writeSalesReport(salesData, outputSalesReportFilePath);

        // Leer la información de productos
        List<String> productData = readProductsInfo(productsInfoFilePath);

        // Escribir el reporte de productos vendidos por cantidad
        writeProductSalesReport(productData, outputProductSalesReportFilePath);
    }
}
