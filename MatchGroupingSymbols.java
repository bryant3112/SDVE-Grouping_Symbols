import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class MatchGroupingSymbols {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MatchGroupingSymbols <source-file>");
            return;
        }

        File file = new File(args[0]);
        Stack<Character> stack = new Stack<>();

        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                for (char ch : line.toCharArray()) {
                    if (ch == '(' || ch == '{' || ch == '[') {
                        stack.push(ch);
                    } 
                    else if (ch == ')' || ch == '}' || ch == ']') {
                        if (stack.isEmpty() || !isMatchingPair(stack.pop(), ch)) {
                            System.out.println("Incorrect grouping symbols.");
                            return;
                        }
                    }
                }
            }

            if (stack.isEmpty()) {
                System.out.println("Grouping symbols are correctly matched.");
            } else {
                System.out.println("Incorrect grouping symbols.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }
}
