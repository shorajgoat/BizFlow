# Himal Jewellery — Backend Management System

A full-stack backend API for a jewellery shop, built with **Spring Boot 3**, **PostgreSQL**, and **JWT authentication**. Manages products, categories, customers, suppliers, sales (POS/billing), purchases, box orders, and expense tracking, with role-based access control for Admin and Staff users.

## Features

- **Authentication & Authorization** — JWT-based auth with access + refresh tokens, role-based access control (ADMIN/STAFF) via Spring Security
- **Product & Category Management** — full CRUD, pagination, sorting, and dynamic search/filtering (by name, category, price range) using JPA Specifications
- **Customer Management** — CRUD with due/credit balance tracking
- **Supplier Management** — CRUD
- **Billing / POS** — transactional sale processing with automatic stock deduction, price recorded at time of sale (not live-priced), rollback on failure
- **Purchases** — supplier restocking flow, inverse of the sale flow
- **Box Orders** — custom order tracking with status lifecycle
- **Expense Tracking** — CRUD
- **Reports** — daily and monthly sales/expense aggregation via JPQL
- **Global exception handling** — consistent JSON error shape across the entire API
- **API documentation** — interactive Swagger UI
- **Dockerized** — one-command startup for the full stack (API + PostgreSQL)

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 3.5, Spring Security, Spring Data JPA |
| Database | PostgreSQL |
| Auth | JWT (jjwt), BCrypt password hashing |
| API Docs | springdoc-openapi (Swagger UI) |
| Testing | JUnit 5, Mockito |
| Build | Maven |
| Containerization | Docker, Docker Compose |

## Architecture

The project follows a **package-by-feature** structure — each business domain (`product`, `customer`, `sale`, etc.) contains its own entity, repository, service, controller, and DTOs, rather than grouping by technical layer. This keeps everything related to one feature in one place as the codebase grows.

```
com.himal.jewellery/
├── auth/          # Login, registration, token refresh
├── security/      # JwtUtil, JwtAuthFilter
├── config/        # SecurityConfig, CorsConfig, OpenApiConfig
├── exception/     # Global exception handling, custom exceptions
├── user/          # User entity, roles
├── product/       # Product CRUD, search
├── category/      # Category CRUD, Product relationship
├── customer/      # Customer CRUD, dues
├── supplier/      # Supplier CRUD
├── sale/          # Billing/POS — the core transactional feature
├── purchase/      # Supplier restocking
├── boxorder/      # Box order tracking
├── expense/       # Expense CRUD
└── report/        # Sales/expense aggregation reports
```

Every request follows the same layered flow:

```
HTTP Request → Controller → Service (business logic) → Repository → PostgreSQL
                    ↓
              DTO validation
                    ↓
         ApiResponse<T> / ErrorResponse (consistent JSON shape)
```

## Key Design Decisions

- **DTOs everywhere, never raw entities in API responses** — prevents over-exposing internal fields and gives full control over the API contract independent of the database schema.
- **Price stored at time of sale** — `SaleItem.unitPrice` is captured when a sale is made, not looked up live from `Product`, so historical receipts remain accurate even if prices change later.
- **`@Transactional` on `completeSale()`** — a sale touches multiple tables (sales, sale_items, products, and optionally customers); the whole operation succeeds or rolls back together, preventing partial stock deduction without a matching sale record.
- **JWT is stateless** — no server-side session storage; every request is authenticated independently via a signed token, with short-lived access tokens paired with longer-lived refresh tokens.
- **Role-based endpoint protection** — `@PreAuthorize` decisions are made per-endpoint based on real business rules (e.g., only ADMIN can delete a product; both ADMIN and STAFF can complete a sale).

## Getting Started

### Prerequisites
- Java 17+
- Maven
- Docker & Docker Compose (for the containerized route), or a local PostgreSQL instance

### Run with Docker (recommended)
```bash
docker-compose up --build
```
API available at `http://localhost:8081`

### Run locally
1. Create a PostgreSQL database named `jewellery`
2. Update `application.properties` with your local database credentials
3. Run:
```bash
mvn spring-boot:run
```

### API Documentation
Once running, visit:
```
http://localhost:8081/swagger-ui.html
```
Use the **Authorize** button to paste a JWT and test protected endpoints directly from the browser.

## Running Tests
```bash
mvn test
```

## Sample API Flow

1. `POST /api/auth/register` — create an account
2. `POST /api/auth/login` — receive an access token + refresh token
3. Attach `Authorization: Bearer <accessToken>` to subsequent requests
4. `POST /api/products` (ADMIN) — add inventory
5. `POST /api/sales` — process a sale; stock deducts automatically
6. `GET /api/reports/daily?date=2026-09-19` — view daily performance

## Roadmap / Future Improvements

- Additional integration tests (`@DataJpaTest`, `@WebMvcTest`) across all Services and Controllers
- CI/CD pipeline via GitHub Actions
- Redis caching for frequently-read product data
- Cloud deployment with managed PostgreSQL