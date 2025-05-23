package com.georgiana.pricecomparatormarket.repository;

import com.georgiana.pricecomparatormarket.model.Discount;
import com.georgiana.pricecomparatormarket.model.Product;

import java.time.LocalDate;
import java.util.*;
import java.io.*;

public class DiscountRepository {
    // Method to load discounts from a given CSV file path
    public List<Discount> loadDiscountsFromCsv(String csvPath){
        List<Discount> discounts = new ArrayList<>(); // initialize a list to store Discount objects

        try (BufferedReader br = new BufferedReader(new FileReader(csvPath))){
            String line = br.readLine(); // skip the header line

            while ((line = br.readLine()) != null){
                String[] values = line.split(";");
                Discount discount = new Discount(
                        values[0],
                        values[1],
                        values[2],
                        Double.parseDouble(values[3]),
                        values[4],
                        values[5],
                        LocalDate.parse(values[6]),
                        LocalDate.parse(values[7]),
                        Double.parseDouble(values[8])
                );
                discounts.add(discount);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return discounts;
    }

    // Method to load all discount CSV files from a given folder and group them by store name
    public Map<String, List<Discount>> loadAllDiscountsFromFolder(String folderPath) {
        Map<String, List<Discount>> discountsByStore = new HashMap<>(); // Create a map to group discounts by store, where key is the store name and the value is the list of discounts

        // Create a File object for the given folder path and list all files ending with ".csv" in that folder
        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".csv"));

        /*If CSV files were found in the folder, for each file extract store name from the file name prefix, load discounts from the file
        and add the discounts to the map grouped by store name */
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                String storeName = fileName.split("_")[0];

                List<Discount> discounts = loadDiscountsFromCsv(file.getAbsolutePath());

                discountsByStore.put(storeName, discounts);
            }
        }

        return discountsByStore; // Return the map of discounts grouped by store
    }
}
