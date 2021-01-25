/**
 * @author Spencillian
 * @date 21 Jan 2020
 * A program to evaluate postfix expressions
 */

import javax.management.BadStringOperationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    // Declare a stack
    public static Stack stack;

    public static void main(String[] args) throws FileNotFoundException, BadStringOperationException {
        // Initialize scanner
        Scanner sc = new Scanner(new File("in.txt"));

        // Initialize stack
        stack = new Stack();

        // Pass over the input task info
        sc.nextLine();

        // Loop over ever expression in the input file
        while(sc.hasNext()){

            // Get the next line and split it by spaces
            String[] line = sc.nextLine().split(" ");

            // Loop over every index in the input line array
            for(int i = 0; i < line.length; i++){

                // If the current string is castable to double
                if(line[i].matches("^-?(\\d*\\.)?\\d+$")){ // Credit to taha bhutta for the regex expression
                    stack.push(Double.parseDouble(line[i])); // Push the double to the stack
                    continue;
                }

                // If the operator isn't one character in length
                if(line[i].length() != 1){
                    System.out.println("Operator is not valid"); // Tell the user their query is wrong
                    break;
                }

                try{
                    // Pop the first two doubles off the stack, evaluate them, push result back onto stack
                    stack.push(evaluate((double)stack.pop(), (double)stack.pop(), line[i].charAt(0)));
                }catch (NullPointerException e){
                    // If popping the stack results in emptying the array
                    System.out.println("Expression has too many operators or is otherwise malformed");
                    break;
                }catch (BadStringOperationException e){
                    System.out.println("Operator is not valid");
                    break;
                }
            }

            // Get the result from the query
            double result = (double)stack.pop();

            // If there is still more values in the stack, something is wrong with the query
            if(!stack.empty()){
                System.out.println("Expression is missing operators or is otherwise malformed");
                continue;
            }

            // Print result
            System.out.println(result);
        }
    }

    /**
     * Evaluate expression with 2 numbers and a specified character
     * @param a The first number coming off the stack
     * @param b The second number coming off the stack
     * @param operator The operator that decides how the expression is evaluated
     * @return The result of the evaluated expression
     * @throws BadStringOperationException If an operator is used that isn't supported
     */
    public static double evaluate(double a, double b, char operator) throws BadStringOperationException {

        // Switch to decide which operation will be done
        switch (operator){
            case '+': // Add
                return b + a;
            case '-': // Subtract
                return b - a;
            case '*': // Multiply
                return b * a;
            case '/': // Divide
                return b / a;
            case '^': // Exponential
                return Math.pow(b, a);
            case '%': // Modulo
                return b % a;
            default: // If unsupported character, throw error
                throw new BadStringOperationException("Operator is not valid");
        }
    }
}