
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Кофе-машина - Константин Павленко");

        int moneyAmount = 0;

        System.out.println("Введите количество денег:");
        try (Scanner scanner = new Scanner(System.in)) {
            moneyAmount = scanner.nextInt();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }

        List<Product> products = new ArrayList<>();

        Path path = Paths.get("", "products.txt");
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                String[] tokens = line.split("\\s+");
                if (tokens.length != 2) {
                    continue;
                }
                try {
                    int price = Integer.parseInt(tokens[1]);
                    Product product = new Product(tokens[0], price);
                    products.add(product);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        checkAmount(products, moneyAmount);
    }

    public static void checkAmount (List<Product> products, int moneyAmount) {
        boolean canBuyAnything = false;

        for (Product drinkName : products) {
            int drinkPrice = drinkName.getPrice();
            if (moneyAmount >= drinkPrice) {
                System.out.println("Вы можете купить " + drinkName.getName());
                canBuyAnything = true;
            }
        }

        if (!canBuyAnything) {
            System.out.println("Недостаточно средств ((");
            System.exit(0);
        }
    }
}
