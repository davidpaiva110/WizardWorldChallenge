# WizardWorldChallenge

The **WizardWorldChallenge** is a Java-based application that interacts with the Wizard World API to fetch and manage data about magical elixirs and their ingredients.

The project allows users to find elixirs they can create based on the ingredients they have though Command Line Interface (CLI).


## Features
- Fetch ingredients and elixirs from the Wizard World API.
- Match elixirs based on available ingredients. 
- Unit tests to ensure functionality.

## Technologies Used
- **Java 21**: Core programming language.
- **Maven**: Build and dependency management.
- **Jackson Databind**: For JSON parsing.
- **JUnit 5**: For unit testing.

## Project Structure
```text
src
├── main
│   ├── java
│   │   ├── model         # Elixir, Ingredient, Inventor
│   │   ├── service       # API client and Matcher logic
│   │   └── ui            # Main CLI entry point
│
└── test
└── java
    └── service       # Unit tests for Matcher
```

## API Integration
The project integrates with the [Wizard World API](https://wizard-world-api.herokuapp.com/swagger/index.html) to fetch:

- Ingredients: */Ingredients*
- Elixirs: */Elixirs*

## How to Run (With Maven)
1. Build the project using Maven:
 ```bash
    mvn clean install
 ```

2. Run the application:
 ```bash
    mvn exec:java -Dexec.mainClass="Main"
 ```

## Testing
To run the unit tests, use the following command:
```bash
    mvn test
```

Only the Matcher class is tested in this project, since it's the only class with business logic and good candidate to demonstrate unit test with JUnit.

The WizardWorld API client is not tested as it is a simple wrapper around the API and does not contain any complex logic.


## Other Considerations
- The project is designed to be simple and straightforward, focusing on the core functionality of matching elixirs.
- The Elixirs and ingredients are all fetched at the beginning of the program, since the API does not support filtering by list of ingredients loading all elixirs and ingredients at once was considered the most efficient way to handle the data.
- The Matcher class is responsible for filtering the elixirs based on the ingredients provided by the user.
- The WizardWorldAPIClient class is responsible for fetching the data from the API and parsing it into Java objects.
- The model package contains the Elixir, Ingredient, and Inventor Records, which are used to represent the data fetched from the API.
- The CommandLine class is responsible for handling the user input and output. It uses the Matcher class to filter the elixirs based on the ingredients provided by the user.



    
   
