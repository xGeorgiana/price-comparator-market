package com.georgiana.pricecomparatormarket.repository;

import com.georgiana.pricecomparatormarket.model.Product;
import java.io.*;
import java.util.*;

public class ProductRepository {
    // Method to load products from a given CSV file path
    public List<Product> loadProductsFromCsv(String csvPath) {
        List<Product> products = new ArrayList<>(); // initialize a list to store Product objects

        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))) {
            String line = br.readLine(); // skip the header line

            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Product product = new Product(
                        values[0],
                        values[1],
                        values[2],
                        values[3],
                        Double.parseDouble(values[4]),
                        values[5],
                        Double.parseDouble(values[6]),
                        values[7]
                );
                products.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Method to load all product CSV files from a given folder and group them by store name
    public Map<String, List<Product>> loadAllProductsFromFolder(String folderPath) {
        Map<String, List<Product>> productsByStore = new HashMap<>(); // Create a map to group products by store, where key is the store name and the value is the list of products

        // Create a File object for the given folder path and list all files ending with ".csv" in that folder
        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".csv"));

        /*If CSV files were found in the folder, for each file extract store name from the file name prefix, load products from the file
        and add the products to the map grouped by store name */
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                String storeName = fileName.split("_")[0];

                List<Product> products = loadProductsFromCsv(file.getAbsolutePath());

                productsByStore.put(storeName, products);
            }
        }

        return productsByStore;  // Return the map of products grouped by store
    }
}
