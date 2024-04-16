import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // Directorio donde se encuentran los archivos generados por GenerateInfoFiles
        String directoryPath = "C:/Users/dortm/OneDrive/6 semestre/Conceptos fundamentales de programación/Entregas/cfdp/input_files/";

        // Generar archivo de reporte de ventas de los vendedores
        generateSalesReport(directoryPath);

        // Generar archivo de productos vendidos por cantidad
        generateProductSalesReport(directoryPath);
    }

    private static void generateSalesReport(String directoryPath) {
        // Mapa para almacenar la cantidad de dinero recaudada por cada vendedor
        Map<String, Double> salesData = new HashMap<>();

        try {
            // Leer archivo de ventas para obtener el dinero recaudado por cada vendedor
            BufferedReader salesReader = new BufferedReader(new FileReader(directoryPath + "sales_info_sub6.csv"));
            String line;
            while ((line = salesReader.readLine()) != null) {
                System.out.println("Línea leída del archivo de ventas: " + line); // Agregar impresión para depuración
                String[] parts = line.split(";");
                if (parts.length < 3) {
                    // La línea no tiene el formato esperado, se ignora
                    continue;
                }
                String seller = parts[0];
                double amount = 0;
                for (int i = 1; i < parts.length; i += 2) {
                    if (i + 1 >= parts.length) {
                        // La línea no tiene el formato esperado, se ignora
                        continue;
                    }
                    try {
                        amount += Double.parseDouble(parts[i]) * Double.parseDouble(parts[i + 1]);
                    } catch (NumberFormatException e) {
                        // Error al convertir a número, se ignora esta parte de la línea
                        continue;
                    }
                }
                salesData.put(seller, salesData.getOrDefault(seller, 0.0) + amount);
            }
            salesReader.close();

            // Ordenar vendedores por cantidad de dinero recaudada de mayor a menor
            List<Map.Entry<String, Double>> sortedSales = new ArrayList<>(salesData.entrySet());
            sortedSales.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));

            // Escribir el reporte de ventas de vendedores en un nuevo archivo
            FileWriter salesReportWriter = new FileWriter(directoryPath + "sales_report.csv");
            for (Map.Entry<String, Double> entry : sortedSales) {
                salesReportWriter.write(entry.getKey() + ";" + entry.getValue() + "\n");
            }
            salesReportWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateProductSalesReport(String directoryPath) {
        // Mapa para almacenar la cantidad vendida de cada producto
        Map<String, Integer> productSales = new HashMap<>();

        try {
            // Leer archivo de productos para obtener la información de los productos
            BufferedReader productsReader = new BufferedReader(new FileReader(directoryPath + "products_info_sub6.csv"));
            String line;
            while ((line = productsReader.readLine()) != null) {
                System.out.println("Línea leída del archivo de productos: " + line); // Agregar impresión para depuración
                String[] parts = line.split(";");
                if (parts.length != 3) {
                    // La línea no tiene el formato esperado, se ignora
                    continue;
                }
                String productId = parts[0];
                String productName = parts[1];
                double productPrice = Double.parseDouble(parts[2].substring(7)); // Eliminar "Precio" del inicio
                // Se cuenta solo la cantidad de veces que se vende cada producto
                productSales.put(productName, 0); // Inicializar la cantidad vendida de cada producto a 0
            }
            productsReader.close();

            // Leer archivo de ventas para obtener la cantidad vendida de cada producto
            BufferedReader salesReader = new BufferedReader(new FileReader(directoryPath + "sales_info_sub6.csv"));
            while ((line = salesReader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length < 2) {
                    // La línea no tiene el formato esperado, se ignora
                    continue;
                }
                for (int i = 1; i < parts.length; i += 2) {
                    if (i + 1 >= parts.length) {
                        // La línea no tiene el formato esperado, se ignora
                        continue;
                    }
                    String productName = parts[i];
                    int quantity = Integer.parseInt(parts[i + 1]);
                    productSales.put(productName, productSales.getOrDefault(productName, 0) + quantity);
                }
            }
            salesReader.close();

            // Ordenar productos por cantidad vendida en forma descendente
            List<Map.Entry<String, Integer>> sortedProducts = new ArrayList<>(productSales.entrySet());
            sortedProducts.sort(Map.Entry.comparingByValue(Collections.reverseOrder()));

            // Escribir el reporte de productos vendidos por cantidad en un nuevo archivo
            FileWriter productSalesReportWriter = new FileWriter(directoryPath + "product_sales_report.csv");
            for (Map.Entry<String, Integer> entry : sortedProducts) {
                productSalesReportWriter.write(entry.getKey() + ";" + entry.getValue() + "\n");
            }
            productSalesReportWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


