# Price Comparator - Market
Price Comparator - Market Application helps users split their basket into store-specific shopping lists to optimize cost savings. It assists in finding the best deals and discounts for desired products, while also displaying newly added discounts from the last 24 hours. The application tracks and displays the price history of a product and enables users to discover best-buy alternative products based on price-per-unit value. Additionally, it alerts users when a product's price is at or below a target price set by them.


# Technologies Used
* Java
* Maven

# Features
* Daily Shopping Basket Monitoring
  - Helps users split their basket into store-specific shopping lists by automatically selecting the cheapest available product for each item, enabling cost-effective shopping and displaying the results clearly organized by store.
  - Implemented in: `optimizeShoppingBasket()`
* Best Discounts
  - Lists products with the highest current percentage discounts across all tracked stores, highlighting where the best deals are available and displaying detailed price reductions for each product. 
  - Implemented in: `showBestDiscounts()`
* New Discounts
  - Lists discounts newly added within the last 24 hours across all tracked stores.  
  - Implemented in: `showNewDiscounts()`
* Dynamic Price History Graphs
  - Lists price history for a specified product across all stores, or optionally filtered by a specific store, showing users the price trend over time.  
  - Implemented in: `showPriceHistory()`
* Product Substitutes & Recommendations
  - Lists matching alternative products across stores, sorted by value per unit (price divided by package quantity), highlighting the best buy regardless of package size and displaying detailed product information.
  - Implemented in: `showSubstitutesAndRecommendations()`
* Custom Price Alert
  - Allows users to set a target price for a product and displays alerts when matching offers across stores are at or below that price.
  - Implemented in: `customPriceAlert()`

# Project Structure
All Java source files are organized under the `src/main/java` directory, structured into packages by functionality, plus the main application class. Resource files, such as CSV data for products and discounts, are kept separate in the `src/main/resources/data` folder. This clear separation between source code and external resources helps keep the project easy to maintain and understand.

```
src
 └── main
     ├── java
     |   ├── com.georgiana.pricecomparatormarket
     |   │   ├── model
     |   |   |   ├──── Discount.java
     |   |   |   └──── Product.java 
     │   |   ├── repository
     |   |   |   ├──── DiscountRepository.java
     |   |   |   └──── ProductRepository.java 
     │   |   ├── service
     |   |   |   └──── PriceComparatorService.java
     │   └── PriceComparatorMarket.java 
     └── resources
         └── data
             ├── discounts
             |   └──── CSV files for discounts applied to products
             └── products
                 └──── CSV files for products
```
* `model` package
  - Contains the classes `Discount.java` and `Product.java` that define the main data models that are used in the application.
* `repository` package
  - Contains the `DiscountRepository.java` and `ProductRepository.java` classes responsible for reading and loading product and discount data from CSV files located in specific folders.
  - These classes parse the CSV content into Product and Discount objects and organize them by store name inferred from the file names.
* `service` package
  - Contains the `PriceComparatorService.java` class which implements the core business logic of the application.
  - This class holds the methods that I mentioned in the features section, handling operations such as optimizing shopping baskets, displaying best and new discounts, showing price history graphs, recommending substitutes, and managing custom price alerts.
* Main class `PriceComparatorMarket.java`
  - This is the entry point of the application, from where the application is run.
  - It manages user interaction through a console menu and calls methods implemented in the PriceComparatorService.

# Installation

To run this project locally, follow these steps:

1. Clone the repository
```
git clone https://github.com/xGeorgiana/price-comparator-market.git
```
2. Navigate to the project directory
```
cd price-comparator-market
```
3. Build the project using Maven
```
mvn clean install
```
4. Run the application from the terminat (OR simply RUN it from your IDE)
```
mvn exec:java -Dexec.mainClass="com.georgiana.pricecomparatormarket.PriceComparatorMarket"
```

# Assumptions and Simplifications
* The product and discount data are loaded from CSV files located in predefined folders (`src/main/resources/data/product`s and `src/main/resources/data/discounts`).
* Store names are inferred from the CSV file names by extracting the prefix before the underscore.
* The price alert feature only displays a message as an alert in the console.

# Usage
After running the application, users interact with a console menu where they can select from several options. Each menu option corresponds to a specific feature of the application, which is then executed and displays the relevant information or result on the console.
```
*--- Price Comparator-Market ---*
1. Daily Shopping Basket Monitoring
2. Best Discounts
3. New Discounts
4. Dynamic Price History Graphs
5. Product Substitutes & Recommendations
6. Custom Price Alert
7. Exit
Choose an option: 
```
Simply enter the number corresponding to the desired feature and follow the on-screen prompts.
