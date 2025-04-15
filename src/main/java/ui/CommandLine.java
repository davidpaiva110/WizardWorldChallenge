package ui;

import model.Elixir;
import model.Ingredient;
import service.Matcher;
import service.WizardWorldAPIClient;

import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class CommandLine {

    private final WizardWorldAPIClient apiClient;
    private Matcher elixirMatcher;

    final Scanner scanner;

    private Set<Ingredient> ingredients;

    public CommandLine() {
        this.apiClient = new WizardWorldAPIClient();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Initializes the command line interface by fetching ingredients and elixirs from the API.
     */
    public void init() {
        try {
            this.ingredients = apiClient.fetchIngredients();
            this.elixirMatcher = new Matcher(apiClient.fetchElixirs());
        }catch (RuntimeException e) {
            System.out.println("Error fetching data from the API: " + e.getMessage());
            System.exit(1);
        }
    }


    /**
     * Starts the command line interface, allowing the user to input ingredients and find elixirs.
     */
    public void run() {

        System.out.println("Welcome to the Wizard World Elixir Matcher!");


        while (true){

            System.out.println("You can make elixirs with the following ingredients:");
            printIngredients();

            System.out.println("\n Please enter the ingredients you have (comma separated) or type 'exit' to quit:");

            String userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            final Set<String> userIngredients = getIngredientsIdBasedOnName(userInput);

            final Set<Elixir> elixirs = elixirMatcher.findMakeableElixirs(userIngredients);
            if (elixirs.isEmpty()) {
                System.out.println("No elixirs can be made with the given ingredients.");
            } else {
                System.out.println("You can make the following elixirs:");
                elixirs.forEach(System.out::println);
            }

            System.out.println("Would you like to try again? (yes/no)");
            if (continueExecution())
                break;

        }

    }

    /**
     * Prints the list of ingredients in a formatted manner.
     */
    private void printIngredients() {
        int count = 0;
        for (String ingredient : ingredients.stream().map(Ingredient::name).toList()) {
            System.out.print(ingredient + ", ");
            count++;
            if (count % 10 == 0) {
                System.out.println();
            }
        }
        System.out.println();
    }

    /**
     * Gets the ingredient IDs based on the user's input.
     *
     * @param line the user input
     * @return a set of ingredient IDs
     */
    private Set<String> getIngredientsIdBasedOnName(final String line) {
        return ingredients.stream()
                .filter(ingredient -> line.toLowerCase().contains(ingredient.name().toLowerCase()))
                .map(Ingredient::id)
                .collect(Collectors.toSet());
    }

    /**
     * Prompts the user to continue execution or exit the program.
     * @return true if the user wants to exit, false otherwise
     */
    private boolean continueExecution() {
        String userInput;
        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("no")) {
                System.out.println("Goodbye!");
                return true;
            } else if (!userInput.equalsIgnoreCase("yes")) {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

}
