# Proiect de Testare Automată (UI & API)

Acest proiect reprezintă un framework robust de testare automată, conceput pentru a valida funcționalitățile unei aplicații web (SauceDemo) și ale unui serviciu de API (Booking), utilizând **Java**, **Selenium WebDriver** și **RestAssured**.

Proiectul implementează design pattern-ul **Page Object Model (POM)** și este optimizat pentru execuția în medii de Integrare Continuă (**GitHub Actions**).

---

## Stack Tehnologic
* **Core:** Java 11+, Maven
* **UI Testing:** Selenium WebDriver 4.44.0
* **API Testing:** RestAssured 6.0.0
* **Test Runner:** TestNG 7.12.0
* **Design Pattern:** Page Object Model (POM)
* **Raportare:** ExtentReports 5.1.1
* **Serializare JSON:** Jackson Databind 2.21.3
* **CI/CD:** GitHub Actions (configurat cu Headless Browser)

---

## Arhitectura Proiectului
Structura proiectului este organizată pentru a separa logica de business de infrastructura de testare:

* `src/main/java`:
    * `base/DriverFactory.java`: Managementul instanțelor de driver (folosind `ThreadLocal` pentru execuție paralelă).
    * `pages/`: Clasele Page Object care conțin locatorii și logica de interacțiune cu interfața (ex: `ProductsPage.java`, `LoginPage.java`).
    * `utils/`: Utilități pentru managementul rapoartelor (`ExtentManager`) și manipularea datelor/așteptărilor.
* `src/test/java`:
    * `UITests/`: Scenariile de testare UI (End-to-End flows, incluzând teste pozitive și negative).
    * `APITests/`: Testarea endpoint-urilor API, gestionând fluxul complet (Auth -> POST -> PUT -> DELETE).
    * `models/`: Clasele POJO pentru maparea datelor JSON (`BookingRequest`, `AuthRequest`, etc.).

---

## Funcționalități Cheie
* **Execuție Paralelă:** Asigurată prin `ThreadLocal` în `DriverFactory` pentru stabilitate.
* **Smart Locators:** Metodologie dinamică în `ProductsPage` pentru identificarea produselor pe baza numelui.
* **Testare Negativă:** Suită completă de teste pentru validarea scenariilor de eroare (ex: `TestLoginPage.java`).
* **Workflow API E2E:** Validarea ciclului de viață complet al unei rezervări prin `RestAssured`.
* **Raportare Avansată:** Generare de rapoarte HTML cu temă Dark prin `ExtentReports`.
* **CI/CD Ready:** Configurarea automată pentru rulare în medii fără interfață grafică prin `GitHub Actions`.

---

## Cum să rulezi proiectul

1. **Clonare:**
   git clone <link-ul-repository-ului-tau>

2. **Execuție Teste (Maven):**
   mvn clean test

---

## Rapoarte
* După execuție, raportul detaliat (HTML) este generat automat în directorul:/target/ExtentReport.html.
