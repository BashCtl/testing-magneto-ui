### Project Structure:

````
testing-magneto-ui/
├── src
│   ├── main
│   │   ├── java
│   │   │   └── org
│   │   │       └── magneto
│   │   │           ├── driver - WebDriver configs
│   │   │           ├── dto - POJO API requests
│   │   │           ├── entities - Test Data POJO
│   │   │           ├── pages - Page Object Classes
│   │   │           ├── steps - Test Steps
│   │   │           └── utils - additional utils to configure and processes data
│   │   └── resources - holds some application properties
│   └── test
│       ├── java - Tessts
│       └── resources
│           ├── collected - Data collected during test
│           ├── entities - Test data
│           └── suites - Testng suits

 

````


Install dependencies

````
mvn clean install
````

Run Test

````
mvn clean test
````