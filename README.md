# Price Comparator - Market
Price Comparator - Market Application helps users split their basket into store-specific shopping lists to optimize cost savings. It assists in finding the best deals and discounts for desired products, while also displaying newly added discounts from the last 24 hours. The application tracks and displays the price history of a product and enables users to discover best-buy alternative products based on price-per-unit value. Additionally, it alerts users when a product's price is at or below a target price set by them.


# Technologies Used
* Java
* Maven

# Features
1. Daily Shopping Basket Monitoring
  - Helps users split their basket into store-specific shopping lists by automatically selecting the cheapest available product for each item, enabling cost-effective shopping and displaying the results clearly organized by store.
  - Implemented in: > optimizeShoppingBasket()
2. Best Discounts
  - Splits the user's basket into store-specific shopping lists by selecting the cheapest available option for each product, helping users make cost-effective purchasing decisions.
  - Implemented in: 'optimizeShoppingBasket()'
3. New Discounts
4. Dynamic Price History Graphs
5. Product Substitutes & Recommendations
6. Custom Price Alert

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
