# 🛍 Clothing Store

A full-stack e-commerce clothing store application that allows users to browse products, filter by multiple criteria, manage their shopping cart, and maintain their personal profile.

---

## 📖 Overview

The Clothing Store application provides a seamless online shopping experience where users can browse clothing products, search using different filters, and purchase items through a shopping cart.

<img width="600" height="400" alt="image" src="https://github.com/user-attachments/assets/b39663a1-2ada-4be8-90e4-4e92e9cc7ffe" />


Users can:

* Browse all available products
* Search and filter products by category
* Filter products by price range
* Filter by category ID and color
* Add items to the shopping cart
* Remove or clear items from the cart
* Create and update their personal profile

---

# ✨ Features

## User Features


* User registration
* User login and authentication
* View user profile
* Create or update profile information

  * Name
  * Email address
  * Phone number
    
* Add products to shopping cart
* View shopping cart contents
* Clear all items from the shopping cart

---

# 👕 Product Categories

The application currently supports the following categories:

* 👕 Top
* 👖 Bottom
* 👟 Shoes

Products can also be filtered using:

* Category ID
* Color
* Minimum price
* Maximum price

Price range:

```
$0 - $900
```

---

# 🚀 API Endpoints

## Categories

| Method | Endpoint                            | Description                          |
| ------ | ----------------------------------- | ------------------------------------ |
| GET    | `/categories`                       | Retrieve all categories              |
| GET    | `/categories/{id}`                  | Retrieve a category by ID            |
| GET    | `/categories/{categoryId}/products` | Retrieve all products in a category  |
| POST   | `/categories`                       | Create a new category *(Admin only)* |
| PUT    | `/categories/{id}`                  | Update a category *(Admin only)*     |
| DELETE | `/categories/{id}`                  | Delete a category *(Admin only)*     |

---

# ⚙️ Getting Started

## Prerequisites

* Java 17+
* Maven
* MySQL (or your configured database)

## Installation

### 1. Clone the repository

```bash
git clone <repository-url>
```

### 2. Navigate to the project directory

```bash
cd project-name
```

### 3. Configure the database

Update your database configuration inside:

```properties
src/main/resources/application.properties
```

Configure:

* Database URL
* Username
* Password

### 4. Run the application

```bash
mvn spring-boot:run
```

---

# 🛒 Shopping Cart

The shopping cart supports:

* Add products
* View cart contents
* Remove items
* Clear all items from the cart

---

# 👤 User Profile

Users can manage their personal information including:

* Full Name
* Email Address
* Phone Number

Profiles can be created or updated at any time.

<img width="300" height="350" alt="image" src="https://github.com/user-attachments/assets/b020ff79-7243-4d86-9a44-1fa6d13a2e49" />

---

# 🔍 Search & Filtering

Products can be searched and filtered using:

* Category
* Category ID
* Color
* Minimum price
* Maximum price

Example filters:

* Shoes under **$150**
* Black Tops
* Bottoms between **$50** and **$200**

---

# 🐞 Bug Fixes

The product search and filtering functionality was thoroughly tested to ensure accurate and reliable search results.

### Verified Areas

* ✅ Category filtering
* ✅ Price range filtering
* ✅ Sub-category filtering
* ✅ Product search behavior

### Testing

Unit tests were added to verify that:

* Valid search criteria return the correct products.
* Price filtering works correctly.
* Category filtering behaves as expected.
* Combined filters produce accurate search results.



# 📁 Project Structure
```
ecommerce-api-starter/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org/yearup/
│   │   │       ├── controllers/
│   │   │       ├── models/
│   │   │       ├── repository/
│   │   │       ├── security/
│   │   │       ├── service/
│   │   │       └── EcommerceApplication.java
│   │   └── resources/
│   └── test/
├── database/
├── target/
├── pom.xml
├── openapi.yaml
└── .gitignore


```
### Interesting Code : Updating a User Profile

 **profile update service**. 
* This method demonstrates how an existing user profile is safely updated by retrieving the current record from the database, modifying only the necessary fields, and then saving the updated entity.
* Retrieves the existing profile instead of creating a new database record.
* Updates only the fields that have changed while preserving the original entity.


```java
public Profile update(int userId, Profile profile) {
    Profile existing = profileRepository.findByUserId(userId);

    existing.setFirstName(profile.getFirstName());
    existing.setLastName(profile.getLastName());
    existing.setPhone(profile.getPhone());
    existing.setEmail(profile.getEmail());
    existing.setAddress(profile.getAddress());
    existing.setCity(profile.getCity());
    existing.setState(profile.getState());
    existing.setZip(profile.getZip());

    return profileRepository.save(existing);
}
```



# Future Enhancements

* Product sorting
* Wishlist functionality
* Order history
* Checkout and payment integration
* Product reviews and ratings
* Inventory management
* Admin dashboard

