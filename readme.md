
E-Commerce Web Application

Project Overview

This is a backend-focused e-commerce web application built using Spring Boot. The application provides users with the ability to browse products, manage their cart. Additionally, admins have the capability to manage products and view orders.

Features

User Features:
- Browse Products: Users can view a list of products available in the store.
- Add to Cart: Users can add and remove items from their shopping cart.


Admin Features:
- Product Management: Admins can add, update, and delete products.
- Order Management: Admins can view a list of all orders placed by users.

Technologies Used

- Java: Core development language.
- Spring Boot: Backend framework for building the REST APIs.
- Hibernate/JPA: ORM for database interaction.
- MySQL: Database used for storing user, product, cart, and order details.


APIs

 User APIs:
- User Registration & Login: Allows users to sign up and log in.
- View Products: Fetch the list of available products.
- Manage Cart: Add or remove items from the cart.
- Checkout: Place an order with items from the cart.

 Admin APIs:
- Manage Products: Create, update, and delete products.
- View Orders: Get a list of all placed orders.

Database Schema

The project uses a MySQL database with the following tables:
- `users`: Stores user information.
- `categories`: Stores product categories.
- `products`: Stores product details.
- `carts`: Stores cart information linked to users.
- `cart_items`: Stores items added to a cart.
- `orders`: Stores order details.
- `order_items`: Stores items for each order.


