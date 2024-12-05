# Crop Monitoring System

**Green Shadow (Pvt) Ltd**  
**Location:** Station Road, Matale, Sri Lanka  

---

## Overview

Green Shadow (Pvt) Ltd specializes in root crops and cereals. This **Crop Monitoring System** is designed to streamline the management of fields, crops, staff, vehicles, equipment, and monitoring logs. Built with **Spring Boot RESTful APIs**, it ensures efficient operations with robust **JWT authentication** and **role-based access control**.

---

## Features

### 1. User Authentication and Authorization
- **JWT-based authentication** for secure access.
- **Role-based access control**:
  - **MANAGER**: Full CRUD access.
  - **ADMINISTRATIVE**: Restricted from modifying crop, field, and monitoring logs.
  - **SCIENTIST**: Restricted from modifying staff, vehicle, and equipment details.

### 2. Entity Management Services
- **Field Service**: Manage field locations and staff assignments.
- **Crop Service**: Track crops, growth stages, and associated fields.
- **Staff Service**: Handle staff records and roles.
- **Vehicle Service**: Manage vehicle records and allocations.
- **Equipment Service**: Oversee equipment details and availability.
- **Monitoring Log Service**: Record crop observations and notes.

### 3. Data and Security Handling
- **Database**: MySQL with tables auto-created via Hibernate.
- **Image Handling**: Images stored as Base64 strings.
- **Validation**: Input data validated via Spring Annotations.

### 4. Error Handling and Logging
- Centralized exception handling for user-friendly error messages.
- Logging using **SLF4J** and **Logback**.

### 5. Comprehensive API Documentation
- RESTful API design with CORS support.
- Detailed API documentation available at:  
  [API Documentation](https://documenter.getpostman.com/view/35385949/2sAY55Zd63).

---

## Technology Stack

### Backend
- **Language**: Java
- **Framework**: Spring Boot
- **ORM**: Hibernate
- **Security**: Spring Security with JWT
- **Validation**: Spring Validation

### Database
- **Type**: MySQL
- **ORM**: JPA with Hibernate

### Utilities
- **Model Mapping**: ModelMapper
- **Logging**: SLF4J and Logback
- **Image Handling**: Base64 Encoding

---

### Frontend implemented Github repo 
- Detailed API documentation available at:  
[Frontend github repo](https://github.com/madhushiillesinghe/Final-Project-Frontend.git).

## License

This project is licensed under the MIT License. See the LICENSE file for details.

---

## Setup Instructions

### Prerequisites
1. Install **Java JDK 17** or higher.
2. Install **MySQL** and configure the database.
3. Set up environment variables for the database username and password.
   - Default credentials:  
     **Username**: `root`  
     **Password**: `Ijse@1234`
4. Install **Maven** for dependency management.

### Backend Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/madhushiillesinghe/Final-Project-Backend.git



