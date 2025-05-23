package com.georgiana.pricecomparatormarket;

import com.georgiana.pricecomparatormarket.model.Discount;
import com.georgiana.pricecomparatormarket.model.Product;
import com.georgiana.pricecomparatormarket.repository.DiscountRepository;
import com.georgiana.pricecomparatormarket.repository.ProductRepository;
import com.georgiana.pricecomparatormarket.service.PriceComparatorService;


import java.util.*;

public class PriceComparatorMarket {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        ProductRepository productRepository = new ProductRepository();
        Map<String, List<Product>> productsByStore = productRepository.loadAllProductsFromFolder("src/main/resources/data/products");
        DiscountRepository discountRepository = new DiscountRepository();
        Map<String, List<Discount>> discountsByStore =discountRepository.loadAllDiscountsFromFolder("src/main/resources/data/discounts");

        PriceComparatorService priceComparatorService = new PriceComparatorService();

        boolean exit = false;

        while (!exit) {
            System.out.println("\n*--- Price Comparator-Market ---*");
            System.out.println("1. Daily Shopping Basket Monitoring");
            System.out.println("2. Best Discounts");
            System.out.println("3. New Discounts");
            System.out.println("4. Dynamic Price History Graphs");
            System.out.println("5. Product Substitutes & Recommendations");
            System.out.println("6. Custom Price Alert");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    priceComparatorService.optimizeShoppingBasket(productsByStore);
                    break;

                case "2":
                    priceComparatorService.showBestDiscounts(discountsByStore, productsByStore);
                    break;

                case "3":
                    priceComparatorService.showNewDiscounts(discountsByStore);
                    break;

                case "4":
                    priceComparatorService.showPriceHistory();
                    break;

                case "5":
                    priceComparatorService.showSubstitutesAndRecommendations(productsByStore);
                    break;

                case "6":
                    priceComparatorService.customPriceAlert(productsByStore);
                    break;

                case "7":
                    exit = true;
                    System.out.println("You exited the application.");
                    break;

                default:
                    System.out.println("Invalid option. Please choose a number between 1 and 7.");
            }
        }

        scanner.close();
    }

}
