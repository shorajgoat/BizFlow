<div align="center">

# BizFlow

### Java & Spring Boot Jewellery Business Management API

A production-minded backend for managing jewellery inventory, customers, suppliers, purchasing, sales, expenses, and business reporting.

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.4-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-Build-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-Ready-2496ED?style=for-the-badge&logo=docker&logoColor=white)](https://www.docker.com/)

</div>

---

## About the project

**BizFlow** is a Java-first REST API that gives jewellery businesses a single backend for daily operations. It is designed around clear domain modules, a layered Spring architecture, secure authentication, transactional business workflows, and consistent API responses.

The repository is primarily a **Java / Spring Boot backend**. The small HTML portion provides the bundled landing page; the core application, business rules, persistence, security, and API design are implemented in Java.

## What it can do

- **Authentication and authorization** — JWT access and refresh tokens, BCrypt password hashing, and `ADMIN` / `STAFF` role-based access control.
- **Inventory management** — products, categories, stock levels, pagination, sorting, and dynamic filtering with JPA Specifications.
- **Sales and POS workflows** — transactional billing, automatic stock deduction, historical sale pricing, and customer due tracking.
- **Purchasing** — supplier restocking workflows with purchase items and stock updates.
- **Customer and supplier management** — structured CRUD APIs for business contacts and balances.
- **Custom box orders** — order tracking with a dedicated status lifecycle.
- **Expense tracking** — record and manage operational expenses.
- **Business reporting** — daily and monthly sales and expense aggregation.
- **Consistent error handling** — centralized exception handling with predictable JSON responses.
- **Interactive API documentation** — Swagger / OpenAPI UI for exploring and testing endpoints.
- **Containerized development** — Docker Compose setup for running the API with PostgreSQL.

## Java technology stack

| Area | Technology |
| --- | --- |
| Language | **Java 17** |
| Framework | **Spring Boot 3.5.4** |
| Web layer | Spring Web / REST APIs |
| Security | Spring Security, JWT, BCrypt |
| Persistence | Spring Data JPA, Hibernate |
| Database | PostgreSQL |
| Validation | Jakarta Bean Validation |
| API documentation | Springdoc OpenAPI / Swagger UI |
| Testing | Spring Boot Test, JUnit 5 |
| Build tool | Maven |
| Deployment | Docker, Docker Compose |

## Architecture

BizFlow uses a **package-by-feature** structure. Each domain keeps its own controller, service, repository, entities, DTOs, and business rules close together.

```text
com.himal.jewellery/
├── auth/          # Login, registration, and token refresh
├── security/      # JWT filter and token utilities
├── config/        # Security, CORS, and OpenAPI configuration
├── exception/     # Global error handling and domain exceptions
├── user/          # Users, roles, and authentication DTOs
├── product/       # Product inventory and search
├── category/      # Product categories
├── customer/      # Customers and outstanding dues
├── supplier/      # Supplier management
├── sale/          # POS sales and stock deduction
├── purchase/      # Supplier purchases and restocking
├── boxorder/      # Custom box-order tracking
├── expense/       # Business expense management
└── report/        # Sales and expense reports
```

The main request flow is:

```text
HTTP Request → Controller → Service → Repository → PostgreSQL
                    ↓
              DTO validation
                    ↓
         Consistent API response / error response
```

## Important engineering decisions

- **DTO-based API contracts:** entities are not exposed directly from REST endpoints.
- **Transactional sales:** completing a sale updates sales, sale items, inventory, and optional customer data as one atomic operation.
- **Historical price accuracy:** sale item prices are captured at checkout instead of being read from the current product price later.
- **Stateless JWT security:** requests are authenticated through signed tokens without server-side sessions.
- **Feature-oriented modularity:** business domains remain isolated and easier to extend.
- **Centralized exception handling:** clients receive a predictable error structure across the API.

## Getting started

### Prerequisites

- Java 17 or newer
- Maven 3.9+ (or use the included Maven Wrapper)
- Docker and Docker Compose **or** a local PostgreSQL installation

### Run with Docker

```bash
git clone https://github.com/shorajgoat/BizFlow.git
cd BizFlow
docker compose up --build
```

The API is available at `http://localhost:8081`.

### Run locally

1. Create a PostgreSQL database named `jewellery`.
2. Configure the database credentials in `src/main/resources/application.properties`.
3. Start the application:

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
mvnw.cmd spring-boot:run
```

## API documentation

After starting the application, open:

- Swagger UI: <http://localhost:8081/swagger-ui.html>
- OpenAPI JSON: <http://localhost:8081/v3/api-docs>

Use Swagger's **Authorize** button with a JWT access token to test protected endpoints.

## Typical API flow

```text
Register / Login
      ↓
Receive access + refresh tokens
      ↓
Authorize protected requests
      ↓
Create products and manage stock
      ↓
Process purchases and sales
      ↓
Review customers, expenses, and reports
```

## Testing

Run the test suite with the Maven Wrapper:

```bash
./mvnw test
```

## Project status

BizFlow is an actively evolving Java backend project. Planned improvements include broader controller/service integration coverage, a GitHub Actions CI pipeline, Redis caching for frequently accessed inventory, and cloud deployment with managed PostgreSQL.

## Contributing

Ideas, bug reports, and pull requests are welcome. For substantial changes, please open an issue first so the proposed design can be discussed.

## License

No license has been declared yet. Contact the repository owner before redistributing this project.

---

<div align="center">

Built with Java, Spring Boot, PostgreSQL, and a focus on maintainable backend engineering.

</div>
