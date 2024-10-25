public class Calculator {
    public static void main(String[] args) {
        new CalculatorPanel();
    }

    public Calculator() {
    }

    public double calculate(String input1, String input2, char operation) {
        double output = 0;
        double operand1 = Double.parseDouble(input1);
        double operand2 = Double.parseDouble(input2);
        switch(operation) {
            case '+':
                output = operand1 + operand2;
                break;
            case '-':
                output = operand1 - operand2;
                break;
            case '*':
                output = operand1 * operand2;
                break;
            case '/':
                output = operand1 / operand2;
        }
        return output;
    }
}
