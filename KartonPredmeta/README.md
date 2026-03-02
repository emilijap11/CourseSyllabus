 s# Karton predmeta

Aplikacija za vođenje kartona predmeta: pregled predmeta, nastavnika i kompletnog silabusa (ishodi, ciljevi, literature, tematske jedinice i komponente ocenjivanja).

## Struktura projekta
- `Backend/` Spring Boot aplikacija (API, baza, Liquibase, Swagger)
- `Frontend/` izdvojeni statički HTML/CSS fajlovi
- `Frontend/static/` stranice i stilovi (kanonska lokacija statike)

Napomena: backend servira statiku iz `Frontend/static` preko Spring resource konfiguracije.

## Tehnologije
- Java 17
- Spring Boot
- Spring Data JPA
- Liquibase
- MySQL
- Swagger / OpenAPI
- HTML + CSS (statički frontend)

## Pokretanje
### 1. Preduslovi
- Java 17
- MySQL server
- Kreirana baza: `karton_predmeta`

### 2. Podesi konekciju
Fajl: `Backend/src/main/resources/application.properties`
- `spring.datasource.url`
- `spring.datasource.username`
- `spring.datasource.password`

### 3. Start aplikacije
```bash
cd Backend
./mvnw spring-boot:run
```

Aplikacija radi na:
- `http://localhost:8081`

## Korisne stranice
- Početna: `http://localhost:8081/index.html`
- Svi predmeti: `http://localhost:8081/all_courses.html`
- Detalji predmeta: `http://localhost:8081/course_detail.html?id=<ID>`
- Pretraga: `http://localhost:8081/search.html`
- Nastavnici: `http://localhost:8081/teachers.html`
- Dodavanje predmeta: `http://localhost:8081/add_course.html`
- Dodavanje nastavnika: `http://localhost:8081/add_teacher.html`

## API rute
- `/api/courses`
- `/api/teachers`
- `/api/syllabus`

## Swagger
- `http://localhost:8081/swagger-ui/index.html`

## Liquibase
Migracije se pokreću automatski pri startu aplikacije.
Glavni changelog:
- `Backend/src/main/resources/db/changelog/db.changelog-master.yaml`
