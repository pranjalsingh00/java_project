# Car Rental System

A Java-based Car Rental System with a Swing GUI, MySQL database integration, and modular MVC architecture. This project enables admins to manage cars and rentals, and customers to browse and book cars.

---

## Table of Contents

- [Features](#features)
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [Setup Instructions](#setup-instructions)
  - [1. Prerequisites](#1-prerequisites)
  - [2. Database Setup](#2-database-setup)
  - [3. Project Build & Run](#3-project-build--run)
- [Usage](#usage)
- [Screenshots](#screenshots)
- [FAQ](#faq)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- **Admin Login**: Secure login for admins.
- **Customer Login**: Secure login for customers.
- **Car Management**: Add, update, delete, and view cars (admin).
- **Rental Management**: Book and return cars (customer).
- **Dashboard**: Separate dashboards for admins and customers.
- **Input Validation**: Basic validation on forms.
- **Responsive GUI**: Java Swing user interface.
- **MySQL Integration**: Persistent storage of users, cars, and rentals.

---

## Project Structure

```
project-root/
├── src/
│   └── com/domain/carrental/
│       ├── Main.java
│       ├── model/
│       ├── view/
│       ├── controller/
│       ├── dao/
│       └── utils/
├── resources/
│   ├── config.properties
│   └── messages.properties
├── lib/
│   └── mysql-connector-java.jar
└── test/
```

- `model/`: Data classes (User, Car, Rental, etc.)
- `view/`: GUI classes (LoginFrame, AdminDashboard, etc.)
- `controller/`: Business logic (LoginController, CarController, etc.)
- `dao/`: Database operations (UserDAO, CarDAO, RentalDAO)
- `utils/`: Utility/helper classes
- `resources/`: Configuration and messages
- `lib/`: MySQL JDBC driver
- `test/`: Unit tests (optional)

---

## Technologies Used

- Java 8+
- Java Swing (GUI)
- MySQL (Database)
- JDBC (Database connectivity)
- JUnit (Testing - optional)
- Maven/Gradle (optional for dependency management)

---

## Setup Instructions

### 1. Prerequisites

- Java JDK 8 or higher
- MySQL Server
- MySQL Workbench (optional)
- MySQL Connector/J [Download Here](https://dev.mysql.com/downloads/connector/j/)
- Git (optional)

---

### 2. Database Setup

1. **Create Database and Tables**

Run the following SQL script in MySQL Workbench:

```sql
CREATE DATABASE car_rental;
USE car_rental;

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role ENUM('ADMIN', 'CUSTOMER') NOT NULL
);

CREATE TABLE cars (
    car_id INT PRIMARY KEY AUTO_INCREMENT,
    brand VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    year INT NOT NULL,
    price_per_day DECIMAL(10,2) NOT NULL,
    available BOOLEAN DEFAULT TRUE
);

CREATE TABLE rentals (
    rental_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    car_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    status ENUM('BOOKED', 'COMPLETED', 'CANCELLED') DEFAULT 'BOOKED',
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (car_id) REFERENCES cars(car_id)
);

INSERT INTO users (username, password, role) VALUES
('admin', 'admin123', 'ADMIN'),
('customer1', 'customer123', 'CUSTOMER');

INSERT INTO cars (brand, model, year, price_per_day) VALUES
('Toyota', 'Camry', 2023, 50.00),
('Honda', 'Civic', 2022, 45.00),
('Ford', 'Focus', 2021, 40.00);
```

2. **Update DB Credentials in Code**

```java
private static final String DB_URL = "jdbc:mysql://localhost:3306/car_rental";
private static final String DB_USER = "root"; // Replace with your MySQL username
private static final String DB_PASSWORD = "your_password"; // Replace with your MySQL password
```

---

### 3. Project Build & Run

To **compile** the project:

```bash
javac -d bin -cp "lib/mysql-connector-java.jar" src/com/domain/carrental/**/*.java
```

To **run** the project:

```bash
java -cp "bin;lib/mysql-connector-java.jar" com.domain.carrental.Main
```

---

## Usage

- **Admin** can log in using:
  - Username: `admin`
  - Password: `admin123`
- **Customer** can log in using:
  - Username: `customer1`
  - Password: `customer123`

Navigate through the dashboard to perform respective operations such as car management or rental booking.

---

## Screenshots

-**Login Page**
![image](https://github.com/user-attachments/assets/72b1cb8d-c44f-47ea-8120-4c619b4fb4f4)

-**Admin Dashboard**
![image](https://github.com/user-attachments/assets/07882e3a-ec84-4ef0-a3b4-ed6cb04ccd2a)

-**Customer dashboard**
![image](https://github.com/user-attachments/assets/5248fa67-6e42-4814-b525-397e09308d3c)




---

## FAQ

**Q: Does this system support multiple users?**  
A: Yes, both admins and customers can be added and managed via the database.

**Q: Can I add more features like payment or notifications?**  
A: Absolutely! The project is modular and extendable.

---

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

---

## License

This project is open-source and free to use under the [MIT License](LICENSE).
