import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.text.DecimalFormat;

public class CalculatorPanel extends JFrame implements ActionListener {
    private JFrame frame;
    private JPanel panelButtons;
    private JPanel panelField;
    private JPanel panelLabel;
    private JPanel panelPanel;
    private JPanel panelButtonsAndField;
    private JLabel label;
    private JTextField field;
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton oneButton;
    private JButton twoButton;
    private JButton threeButton;
    private JButton fourButton;
    private JButton fiveButton;
    private JButton sixButton;
    private JButton sevenButton;
    private JButton eightButton;
    private JButton nineButton;
    private JButton zeroButton;
    private JButton clearButton;
    private JButton clearEntryButton;
    private JButton deleteButton;
    private JButton alternateSignButton;
    private JButton decimalButton;
    private JButton equalsButton;
    private Calculator calculator = new Calculator();

    // used to clear the field after displaying an answer if the user starts typing
    private boolean justShowedAnswer = false;

    // stores the value of the operation, so +, -, *, /. Also 0, which used if this is the first step or the previous step was equals.
    private char operation = '0';

    // stores the two operands, so in the equation 3 + 4 = ?, this is 3.
    private String operand1;

    // and this is 4.
    private String operand2;

    DecimalFormat format = new DecimalFormat("0.###########");

    public CalculatorPanel() {
        frame = new JFrame("Calculator");
        panelButtons = new JPanel();
        panelField = new JPanel();
        panelLabel = new JPanel();
        panelPanel = new JPanel();
        panelButtonsAndField = new JPanel();
        label = new JLabel("Hello");
        field = new JTextField(9);
        field.setFont(new Font("Cambria", Font.PLAIN, 70));
        field.setEditable(false);

        Font buttonFont = new Font("Cambria", Font.PLAIN, 40);
        clearButton = new JButton("C");
        clearEntryButton = new JButton("CE");
        deleteButton = new JButton("Delete");
        alternateSignButton = new JButton("+/-");
        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");
        decimalButton = new JButton(".");
        equalsButton = new JButton("=");

        oneButton = new JButton("1");
        twoButton = new JButton("2");
        threeButton = new JButton("3");
        fourButton = new JButton("4");
        fiveButton = new JButton("5");
        sixButton = new JButton("6");
        sevenButton = new JButton("7");
        eightButton = new JButton("8");
        nineButton = new JButton("9");
        zeroButton = new JButton("0");

        clearButton.setFont(buttonFont);
        clearEntryButton.setFont(buttonFont);
        deleteButton.setFont(buttonFont);
        alternateSignButton.setFont(buttonFont);
        addButton.setFont(buttonFont);
        subtractButton.setFont(buttonFont);
        multiplyButton.setFont(buttonFont);
        divideButton.setFont(buttonFont);
        decimalButton.setFont(buttonFont);
        equalsButton.setFont(buttonFont);
        oneButton.setFont(buttonFont);
        twoButton.setFont(buttonFont);
        threeButton.setFont(buttonFont);
        fourButton.setFont(buttonFont);
        fiveButton.setFont(buttonFont);
        sixButton.setFont(buttonFont);
        sevenButton.setFont(buttonFont);
        eightButton.setFont(buttonFont);
        nineButton.setFont(buttonFont);
        zeroButton.setFont(buttonFont);

        // BorderLayout used to position everything correctly; this was a little complicated.
        frame.add(panelPanel);
        panelPanel.setLayout(new BorderLayout());
        panelButtonsAndField.setLayout(new BorderLayout());
        panelPanel.add(panelLabel, BorderLayout.NORTH);
        panelPanel.add(panelButtonsAndField, BorderLayout.CENTER);
        panelButtonsAndField.add(panelField, BorderLayout.NORTH);
        panelButtonsAndField.add(panelButtons, BorderLayout.CENTER);

        // frame.add(panelLabel);

        // // frame.add(panelField, BorderLayout.NORTH);
        // // frame.add(panelButtons, BorderLayout.CENTER);

        panelLabel.add(label);

        panelField.add(field);

        panelButtons.setLayout(new GridLayout(5, 4));
        panelButtons.add(clearButton);
        panelButtons.add(clearEntryButton);
        panelButtons.add(deleteButton);
        panelButtons.add(alternateSignButton);
        panelButtons.add(sevenButton);
        panelButtons.add(eightButton);
        panelButtons.add(nineButton);
        panelButtons.add(divideButton);
        panelButtons.add(fourButton);
        panelButtons.add(fiveButton);
        panelButtons.add(sixButton);
        panelButtons.add(multiplyButton);
        panelButtons.add(oneButton);
        panelButtons.add(twoButton);
        panelButtons.add(threeButton);
        panelButtons.add(subtractButton);
        panelButtons.add(zeroButton);
        panelButtons.add(decimalButton);
        panelButtons.add(equalsButton);
        panelButtons.add(addButton);

        oneButton.addActionListener(this);
        twoButton.addActionListener(this);
        threeButton.addActionListener(this);
        fourButton.addActionListener(this);
        fiveButton.addActionListener(this);
        sixButton.addActionListener(this);
        sevenButton.addActionListener(this);
        eightButton.addActionListener(this);
        nineButton.addActionListener(this);
        zeroButton.addActionListener(this);
        clearButton.addActionListener(this);
        clearEntryButton.addActionListener(this);
        deleteButton.addActionListener(this);
        alternateSignButton.addActionListener(this);
        decimalButton.addActionListener(this);
        equalsButton.addActionListener(this);
        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divideButton.addActionListener(this);

        // sets the frame's properties
        frame.setVisible(true);
        frame.setSize(new Dimension(600, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent event) {
    
        if (event.getSource() == clearButton) {
            // completely clears everything.
            field.setText("");
            operand1 = "";
            operand2 = "";
            operation = '0';
        } else if (event.getSource() == clearEntryButton) {
            // clears only the field.
            field.setText("");
        } else if (event.getSource() == deleteButton) {
            // deletes the final character, unless there aren't any.
            if (field.getText().length() != 0) {
                field.setText(field.getText().substring(0, field.getText().length() - 1));
            }
        } else if (event.getSource() == alternateSignButton) {
            // changes the sign on the current display, unless there isn't any.
            if (justShowedAnswer) {
                // cancels the answer from being wiped if you change its sign because this implies you will use the answer.
                justShowedAnswer = false;
            }
            try {
                if (field.getText().substring(0, 1).equals("-")) {
                    field.setText(field.getText().substring(1));
                } else {
                    field.setText("-" + field.getText());
                }
            } catch (Exception exception) {}
        } else if (event.getSource() == decimalButton) {
            // adds a decimal point at the end of your answer unless there is already a decimal point or there is nothing in the field.
            try {
                for (int i = 0; i < field.getText().length(); i++) {
                    if (field.getText().charAt(i) == '.') {
                        return;
                    }
                }
                if (field.getText().length() == 0) {
                    field.setText("0.");
                    return;
                } 
                field.setText(field.getText() + ".");
            } catch (Exception exception) {}
        } else if (event.getSource() == addButton) {
            calculate();
            operation = '+';
        } else if (event.getSource() == subtractButton) {
            calculate();
            operation = '-';
        } else if (event.getSource() == multiplyButton) {
            calculate();
            operation = '*';
        } else if (event.getSource() == divideButton) {
            calculate();
            operation = '/';
        }
        if (justShowedAnswer) {
            // if you've just showed the answer, and you're going to start typing, then this clears the field.
            field.setText("");
            justShowedAnswer = false;
        } 
        if (event.getSource() == equalsButton) {
            // I need this after the clear because otherwise the answer never shows.
            // calculates this: operand1 (operation) operand2 = ?
            operand2 = field.getText();
            if (operation != '0') {
                field.setText((format.format(calculator.calculate(operand1, operand2, operation))));
            }
            justShowedAnswer = true;
            operation = '0';
        }
        // number button functionalities
        if (event.getSource() == oneButton) {
            field.setText(field.getText() + 1);
        } else if (event.getSource() == twoButton) {
            field.setText(field.getText() + 2);
        } else if (event.getSource() == threeButton) {
            field.setText(field.getText() + 3);
        } else if (event.getSource() == fourButton) {
            field.setText(field.getText() + 4);
        } else if (event.getSource() == fiveButton) {
            field.setText(field.getText() + 5);
        } else if (event.getSource() == sixButton) {
            field.setText(field.getText() + 6);
        } else if (event.getSource() == sevenButton) {
            field.setText(field.getText() + 7);
        } else if (event.getSource() == eightButton) {
            field.setText(field.getText() + 8);
        } else if (event.getSource() == nineButton) {
            field.setText(field.getText() + 9);
        } else if (event.getSource() == zeroButton) {
            field.setText(field.getText() + 0);
        }
    }

    public void calculate() {
        // used to calculate within the four functions, since user might do two consecutive functions rather than simply hit equals.
        if (operation != '0' && field.getText().length() != 0) {
            // runs if the previous operation wasn't an equals, and the field isn't empty.
            operand2 = field.getText();
            operand1 = Double.toString(calculator.calculate(operand1, operand2, operation));
            field.setText("");
        } else if (field.getText().length() != 0) {
            // only if the field isn't empty.
            operand1 = field.getText();
            field.setText("");
        }
    }
}