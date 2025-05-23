package com.georgiana.pricecomparatormarket.service;

import com.georgiana.pricecomparatormarket.model.Discount;
import com.georgiana.pricecomparatormarket.model.Product;
import com.georgiana.pricecomparatormarket.repository.ProductRepository;

import java.io.File;
import java.time.LocalDate;
import java.util.*;


public class PriceComparatorService {

    Scanner scanner = new Scanner(System.in);

    // DAILY SHOPPING BASKET MONITORING
    // This method splits the user's basket into shopping lists by store, for cost savings
    public void optimizeShoppingBasket(Map<String, List<Product>> productsByStore) {

        // Asks the user to enter the products they want to buy, reads the user's input, and splits it into a list of product names
        System.out.println("Please enter the products you want to buy, separated by commas:");
        String basketInput = scanner.nextLine();
        List<String> basketProductNames = Arrays.asList(basketInput.split("\\s*,\\s*"));

        // This map will store the optimized shopping lists for each store
        // The key is the store name, and the value is the list of cheapest products selected for that store
        Map<String, List<Product>> shoppingListsByStore = new HashMap<>();

        // Loop through each product name entered by the user
        for (String productName : basketProductNames) {
            Product cheapestProduct = null; // stores the cheapest product found for this name
            String cheapestStore = null;  // stores the store where this cheapest product was found

            // Loop through each store and its list of products
            for (Map.Entry<String, List<Product>> entry : productsByStore.entrySet()) {
                String store = entry.getKey();
                List<Product> products = entry.getValue();

                // Look for the product in the current store's product list
                for (Product product : products) {
                    // If a product with the same name is found
                    if (product.getProductName().equalsIgnoreCase(productName)) {
                        // Check if it's the first match found, or if it's cheaper than the current cheapest product found
                        if (cheapestProduct == null || product.getPrice() < cheapestProduct.getPrice()) {
                            cheapestProduct = product;
                            cheapestStore = store;
                        }
                    }
                }
            }

            // If we found the product in at least one store, add it to the corresponding store's shopping list
            if (cheapestProduct != null) {
                // If this is the first product for this store, create a new list for it in the map
                if (!shoppingListsByStore.containsKey(cheapestStore)) {
                    shoppingListsByStore.put(cheapestStore, new ArrayList<>());
                }
                // Add the cheapest product to the store's shopping list
                shoppingListsByStore.get(cheapestStore).add(cheapestProduct);
            } else {
                System.out.println(productName + " is not available in any store.");
            }
        }

        // Display the optimized shopping lists for each store
        System.out.println("\nOptimized shopping lists by store:");
        for (Map.Entry<String, List<Product>> entry : shoppingListsByStore.entrySet()) {
            String store = entry.getKey();
            List<Product> products = entry.getValue();

            System.out.println("\n" + store.toUpperCase() + ":");
            for (Product p : products) {
                System.out.println(" - " + p.getProductName() + " at " + p.getPrice() + " " + p.getCurrency());
            }
        }
    }


    //BEST DISCOUNT
    // This method displays products with the highest percentage discounts available across all tracked stores
    public void showBestDiscounts(Map<String, List<Discount>> discountsByStore, Map<String, List<Product>> productsByStore) {
        // Maps to track, for each product ID, the highest discount percentage found and the list of stores offering that maximum discount
        Map<String, Double> maxDiscountByProductId = new LinkedHashMap<>();
        Map<String, List<String>> storesByProductId = new LinkedHashMap<>();

        // Loop through all stores and their discount lists
        for (Map.Entry<String, List<Discount>> entry : discountsByStore.entrySet()) {
            String store = entry.getKey();
            List<Discount> discounts = entry.getValue();

            // Loop through all discounts in the current store
            for (Discount discount : discounts) {
                // Get the product ID and discount value from the current discount object
                String productId = discount.getProductId();
                double currentDiscount = discount.getPercentageOfDiscount();

                // Get the current maximum discount for this product or 0 if not found yet
                double maxDiscount = maxDiscountByProductId.getOrDefault(productId, 0.0);

                // If current discount is greater than stored maximum discount, update maximum discount and reset stores list
                if (currentDiscount > maxDiscount) {
                    maxDiscountByProductId.put(productId, currentDiscount);
                    storesByProductId.put(productId, new ArrayList<>(List.of(store)));
                } else if (currentDiscount == maxDiscount) { // If current discount equals the stored max, add the store to the list
                    storesByProductId.get(productId).add(store);
                }
            }
        }

        // Display the results
        System.out.println("\nProducts with the highest percentage discount:");
        // For each product ID with a recorded maximum discount, retrieve its discount value and the list of stores offering it
        for (String productId : maxDiscountByProductId.keySet()) {
            double discountValue = maxDiscountByProductId.get(productId);
            List<String> stores = storesByProductId.get(productId);

            // Find product details in one of the stores
            Product product = null;
            for (String store : stores) {
                List<Product> products = productsByStore.get(store);
                if (products != null) {
                    for (Product p : products) {
                        if (p.getProductId().equals(productId)) {
                            product = p;
                            break;
                        }
                    }
                }
                if (product != null) break;
            }

            // If product details found, calculate discounted price and print formatted info
            if (product != null) {
                double oldPrice = product.getPrice(); // Get the original price of the product
                double newPrice = oldPrice - (oldPrice * discountValue / 100);

                System.out.printf("%s (%s) | Store: %s | Discount %.0f%% : %.2f RON → %.2f RON\n",
                        product.getProductName(),
                        product.getBrand(),
                        String.join(", ", stores),
                        discountValue,
                        oldPrice,
                        newPrice);
            }
        }
    }


    // NEW DISCOUNTS
    // This method displays all the new discounts added in the last 24h
    public void showNewDiscounts(Map<String, List<Discount>> discountsByStore) {
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minusDays(1);

        System.out.println("New discounts added in the last 24h:");

        for (Map.Entry<String, List<Discount>> entry : discountsByStore.entrySet()) {
            // Retrieve the current store name, its list of discounts, and initialize an empty list to store new discounts added
            String store = entry.getKey();
            List<Discount> discounts = entry.getValue();
            List<Discount> newDiscounts = new ArrayList<>();

            // Loop through each discount of the current store, adding to newDiscounts only those whose start date is today or yesterday
            for (Discount discount : discounts) {
                if (!discount.getFromDate().isBefore(yesterday)) {
                    newDiscounts.add(discount);
                }
            }

            /* If no new discounts are found then an informative message is displayed,
               otherwise, the store name is printed followed by the list of the new discounts found in that store */
            if (newDiscounts.isEmpty()) {
                System.out.println("There are no new discounts added in the last 24 hours at " + store + ".");
            } else {
                System.out.println("\nStore: " + store);
                for (Discount discount : newDiscounts) {
                    System.out.println(discount.getProductName() + " - " +
                            discount.getPercentageOfDiscount() + "% discount, available from " +
                            discount.getFromDate());
                }
            }
        }
    }


    // DYNAMIC PRICE HISTORY GRAPHS
    /* This method displays the price history of a given product, across all stores,
     or optionally filtered by a specific store, based on the CSV files in products folder */
    public void showPriceHistory() {

        System.out.print("Please enter the product name: ");
        String productName = scanner.nextLine().trim();

        System.out.print("Please enter the store (press ENTER for all): ");
        String storeFilter = scanner.nextLine().trim();
        if (storeFilter.isEmpty()) storeFilter = null;

        // Create a list to store all the files from products folder, that ends with the .csv extension
        File folder = new File("src/main/resources/data/products");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".csv"));

        // Check if any product files were found, if none, a message is displayed and the method stops
        if (files == null || files.length == 0) {
            System.out.println("There were no product files found.");
            return;
        }

        // Create a list of Strings, to store the absolute paths of all product files
        List<String> productFiles = new ArrayList<>();
        for (File file : files) {
            productFiles.add(file.getAbsolutePath());
        }

        ProductRepository productRepository = new ProductRepository();
        boolean found = false;

        System.out.println("\nPrice history for the product: " + productName);
        // Loop through product files to find product history
        for (String filePath : productFiles) {
            // Get the store name by extracting the substring before the first underscore in the file name
            String fileName = new File(filePath).getName();
            String store = fileName.substring(0, fileName.indexOf('_'));

            // If a store filter is provided and the current store does not match it, it skips this file
            if (storeFilter != null && !store.equalsIgnoreCase(storeFilter)) {
                continue;
            }

            // Load the list of products from the current CSV file
            List<Product> products = productRepository.loadProductsFromCsv(filePath);
            // Loop through each product to find those matching the user-input product name
            for (Product p : products) {
                if (p.getProductName().equalsIgnoreCase(productName)) {
                    found = true;
                    // Get the date part from the file name, everything after the underscore and before the .csv
                    String datePart = fileName.substring(fileName.indexOf('_') + 1, fileName.indexOf(".csv"));
                    System.out.printf("%s | %s: %.2f %s\n", datePart, store, p.getPrice(), p.getCurrency());
                }
            }
        }

        if (!found) {
            System.out.println("The product was not found in the product files");
        }
    }


    // PRODUCT SUBSTITUTES AND RECOMMENDATIONS
    /* This method finds products matching the user's input across stores,
     sorts them by price per unit to identify the best buys regardless of pack size,
     and displays detailed info for each, marking the BEST PRICE option */
    public void showSubstitutesAndRecommendations(Map<String, List<Product>> productsByStore){

        // Ask user to enter the product name to find substitutes and recommendations
        System.out.print("Please enter the product name: ");
        String productName = scanner.nextLine();

        // List to store all products that match the user's input, from all stores
        List<Product> matchingProducts = new ArrayList<>();
        // Map to associate each matched product with its corresponding store name
        Map<Product, String> storeByProduct = new HashMap<>();

        // Loop through all stores and their product lists
        for (Map.Entry<String, List<Product>> entry : productsByStore.entrySet()) {
            String storeName = entry.getKey();
            List<Product> products = entry.getValue();

            // Loop through each product in the current store's list
            for (Product product : products) {
                // Check if the product name matches the user's input
                if (product.getProductName().equalsIgnoreCase(productName)) {
                    matchingProducts.add(product);
                    storeByProduct.put(product, storeName);
                }
            }
        }

        // Sort products by value per unit (price divided by package quantity) ascending
        matchingProducts.sort(Comparator.comparingDouble(p -> p.getPrice() / p.getPackageQuantity()));

        // Loop through the sorted list of matching products and displaying their detailed info
        for (int i = 0; i < matchingProducts.size(); i++) {
            Product product = matchingProducts.get(i);
            String storeName = storeByProduct.get(product);
            double valuePerUnit = product.getPrice() / product.getPackageQuantity();

            System.out.printf("Store: %s | Brand: %s | %.2f %s | %.2f %s | %.2f %s/%s",
                    storeName,
                    product.getBrand(),
                    product.getPackageQuantity(),
                    product.getPackageUnit(),
                    product.getPrice(),
                    product.getCurrency(),
                    valuePerUnit,
                    product.getCurrency(),
                    product.getPackageUnit());

            if (i == 0) {
                System.out.print("  --> BEST PRICE");
            }
            System.out.println();
        }
    }


    // CUSTOM PRICE ALERT
    // This method displays alerts for matching products whose prices are equal or below the target price set by the user
    public void customPriceAlert(Map<String, List<Product>> productsByStore) {

        System.out.print("Please enter the product name: ");
        String productName = scanner.nextLine();

        System.out.print("Please enter the price target: ");
        String targetPriceInput = scanner.nextLine();
        double targetPrice = Double.parseDouble(targetPriceInput);

        boolean found = false;

        // Loop through all stores and their product lists
        for (Map.Entry<String, List<Product>> entry : productsByStore.entrySet()) {
            String storeName = entry.getKey(); // stores the store's name
            List<Product> products = entry.getValue(); // stores the list of the products found in that store

            // Loop through each product in the current store
            for (Product product : products) {
                // Check if the product name matches the user's input
                if (product.getProductName().equalsIgnoreCase(productName)) {
                    // Check if the product price is less than or equal to the target price
                    if (product.getPrice() <= targetPrice) {
                        System.out.printf("ALERT! %s at %s is %.2f %s (below %.2f RON)\n",
                                product.getProductName(),
                                storeName,
                                product.getPrice(),
                                product.getCurrency(),
                                targetPrice);
                        found = true;
                    }
                }
            }
        }

        if (!found) {
            System.out.println("No product falls within the target price.");
        }
    }


}



