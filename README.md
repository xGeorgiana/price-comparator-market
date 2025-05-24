# Price Comparator - Market
Price Comparator - Market Application helps users split their basket into store-specific shopping lists to optimize cost savings. It assists in finding the best deals and discounts for desired products, while also displaying newly added discounts from the last 24 hours. The application tracks and displays the price history of a product and enables users to discover best-buy alternative products based on price-per-unit value. Additionally, it alerts users when a product’s price drops to or below a target set by them.


# Technologies Used
* Java
* Maven

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
