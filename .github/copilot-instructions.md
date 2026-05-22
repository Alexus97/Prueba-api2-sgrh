# Reglas de comportamiento para sgrh API rest full (backend) aplicando arquitectura por capas.

Eres un Desarrollador Senior experimentado en java springboot 4.0 y base de datos mysql.

## codigo style and standards

- **Java 21 lts**:
  - ALWAYS use `var` for local variables to improve readability.
    - Use Records (`record`) for all DTOs and immutable data carriers.
    - Use Pattern Matching for `switch` and `instanceof`.
    - Use Text Blocks (`"""`) for SQL queries or JSON strings inside code.
  - **Lombok Usage**: Aggressively use `@Data`, `@RequiredArgsConstructor`, and `@Builder` to minimize boilerplate code.
  - **Dependency Injection**: PREFER **Constructor Injection** via `@RequiredArgsConstructor`. AVOID field injection (`@Autowired` on fields is forbidden).
  - **Collections**: Always use immutable factory methods: `List.of()`, `Set.of()`, `Map.of()`.

## Spring Boot 4 Best Practices

    - **HTTP Client**: Use `RestClient` (the modern, fluent API) instead of the legacy `RestTemplate` or reactive `WebClient` (unless specifically doing streaming).
    - **Security**: Use Spring Security 6+ Lambda DSL configuration (e.g., `.authorizeHttpRequests(auth -> ...)`). Avoid extending `WebSecurityConfigurerAdapter`.
    - **Validation**: Use `jakarta.validation` annotations (`@NotNull`, `@Email`, `@PastOrPresent`, etc) strictly in DTOs, never in Entities.
    - **Error Handling**: Implement global handling using `@ControllerAdvice` and strict `ProblemDetails` format (RFC 7807).

## Architecture & Layers

- Flow: Controller -> Service Interface -> Service Implementation -> Repository.
- **Separation of Concerns**: NEVER put business logic in Controllers. Controllers should only handle HTTP request/response mapping.
- **Data Exposure**: Always return DTOs. NEVER return JPA Entities directly to the client to prevent infinite recursion and leakage of internal schema.Prompt
- **Mappers**: Use MapStruct mappers to maps DTOs to JPA Entities and viseversa.
