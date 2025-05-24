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
