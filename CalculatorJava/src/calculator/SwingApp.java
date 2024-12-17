package calculator;

/**
 *
 * @author vctor
 */
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.*;

public class SwingApp {
    
    public SwingApp(){ // Model
    JFrame a = new JFrame();
            
    // Fonts
    Font font1 = new Font("Tahoma", Font.BOLD, 25); // initialize and set the font style, font property, font size
    Font font2 = new Font("Verdana", Font.BOLD, 25); // initialize and set the font style, font property, font size
    
    // Text Fields         
    JTextField input = new JTextField();
    input.setBounds(20, 20, 200, 100); // x, y, width, height
    input.setFont(font1); // set the font for JTextField
    input.setEditable(true); // read-and-write JTextField on runtime
    input.setHorizontalAlignment(JTextField.LEFT); // align text uniformly
    
    // Buttons
    JButton one = new JButton("1");
    one.setBounds(20, 120, 50, 50);
    one.setFont(font2); // set the font for JButtons
    
    JButton two = new JButton("2");
    two.setBounds(70, 120, 50, 50);
    two.setFont(font2);
    
    JButton three = new JButton("3");
    three.setBounds(120, 120, 50, 50);
    three.setFont(font2);
    
    JButton four = new JButton("4");
    four.setBounds(20, 170, 50, 50);
    four.setFont(font2);
    
    JButton five = new JButton("5");
    five.setBounds(70, 170, 50, 50);
    five.setFont(font2);
    
    JButton six = new JButton("6");
    six.setBounds(120, 170, 50, 50);
    six.setFont(font2);
    
    JButton seven = new JButton("7");
    seven.setBounds(20, 220, 50, 50);
    seven.setFont(font2);
    
    JButton eight = new JButton("8");
    eight.setBounds(70, 220, 50, 50);
    eight.setFont(font2);
    
    JButton nine = new JButton("9");
    nine.setBounds(120, 220, 50, 50);
    nine.setFont(font2);
    
    JButton zero = new JButton("0");
    zero.setBounds(20, 270, 50, 50);
    zero.setFont(font2);
    
    JButton clear = new JButton("C");
    clear.setBounds(70, 270, 50, 50);
    clear.setFont(font2);
    
    JButton solve = new JButton("=");
    solve.setBounds(120, 270, 50, 50);
    solve.setFont(font2);
    
    JButton divide = new JButton("/");
    divide.setBounds(170, 120, 50, 50);
    divide.setFont(font2);
    
    JButton multiply = new JButton("*");
    multiply.setBounds(170, 170, 50, 50);
    multiply.setFont(font2);
    
    JButton subtract = new JButton("-");
    subtract.setBounds(170, 220, 50, 50);
    subtract.setFont(font2);
    
    JButton add = new JButton("+");
    add.setBounds(170, 270, 50, 50);
    add.setFont(font2);
    
    // Add items to the JFrame
    a.add(input);
    a.add(one);
    a.add(two);
    a.add(three);
    a.add(four);
    a.add(five);
    a.add(six);
    a.add(seven);
    a.add(eight);
    a.add(nine);
    a.add(zero);
    a.add(clear);
    a.add(solve);
    a.add(divide);
    a.add(multiply);
    a.add(subtract);
    a.add(add);
    
    // Action Listeners for Number buttons
    one.addActionListener(e -> input.setText(input.getText() + "1"));
    two.addActionListener(e -> input.setText(input.getText() + "2"));
    three.addActionListener(e -> input.setText(input.getText() + "3"));
    four.addActionListener(e -> input.setText(input.getText() + "4"));
    five.addActionListener(e -> input.setText(input.getText() + "5"));
    six.addActionListener(e -> input.setText(input.getText() + "6"));
    seven.addActionListener(e -> input.setText(input.getText() + "7"));
    eight.addActionListener(e -> input.setText(input.getText() + "8"));
    nine.addActionListener(e -> input.setText(input.getText() + "9"));
    zero.addActionListener(e -> input.setText(input.getText() + "0"));

    // Action Listeners for Operator buttons
    add.addActionListener(e -> input.setText(input.getText() + "+"));
    subtract.addActionListener(e -> input.setText(input.getText() + "-"));
    multiply.addActionListener(e -> input.setText(input.getText() + "*"));
    divide.addActionListener(e -> input.setText(input.getText() + "/"));

    // Action Listeners for Clear button
    clear.addActionListener(e -> input.setText(""));
    
    // Action Listeners for Solve button
    solve.addActionListener(e -> {
    try {
        String expression = input.getText().trim();  // Trim leading and trailing spaces
        if (expression.isEmpty()) {
            input.setText("Error"); // Handle empty input
            return;
        }

        // Debug: Print the expression to console
        System.out.println("Evaluating expression: " + expression);

        double result = evaluateExpression(expression); // Solve the expression
        input.setText(String.valueOf(result)); // Display result
    } catch (Exception ex) {
        // Catch any exception and show error
        input.setText("Error");
        ex.printStackTrace();  // Print the stack trace to the console for debugging
    }
});

    
    // Properties of the stuff
    a.setSize(240, 340); // wd, ht
    a.setLayout(null);
    a.setVisible(true);
    a.setLocationRelativeTo(null);
    a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    a.setTitle("Calculator");
    
    }
    
    // Manual method to evaluate simple arithmetic expressions
    private double evaluateExpression(String expression) {
        // First, handle multiplication and division (left to right)
        expression = evaluateOperator(expression, "*");
        expression = evaluateOperator(expression, "/");

        // Now handle addition and subtraction (left to right)
        expression = evaluateOperator(expression, "+");
        expression = evaluateOperator(expression, "-");

        return Double.parseDouble(expression); // Return the final result
    }
    
    // Helper method to evaluate a specific operator in the expression
    private String evaluateOperator(String expression, String operator) {
        while (expression.contains(operator)) {
            int operatorIndex = expression.indexOf(operator);
            int startIndex = operatorIndex - 1;
            int endIndex = operatorIndex + 1;

            // Find the full number before and after the operator
            while (startIndex >= 0 && Character.isDigit(expression.charAt(startIndex))) {
                startIndex--;
            }
            startIndex++; // Move back to the start of the number

            while (endIndex < expression.length() && Character.isDigit(expression.charAt(endIndex))) {
                endIndex++;
            }

            String leftOperand = expression.substring(startIndex, operatorIndex).trim();
            String rightOperand = expression.substring(operatorIndex + 1, endIndex).trim();

            // Perform the operation
            double left = Double.parseDouble(leftOperand);
            double right = Double.parseDouble(rightOperand);
            double result = 0;

            switch (operator) {
                case "+":
                    result = left + right;
                    break;
                case "-":
                    result = left - right;
                    break;
                case "*":
                    result = left * right;
                    break;
                case "/":
                    result = left / right;
                    break;
            }

            // Replace the evaluated operation with the result
            expression = expression.substring(0, startIndex) + result + expression.substring(endIndex);
        }
        
        return expression; // Return the expression with evaluated operator
    }
    
}
