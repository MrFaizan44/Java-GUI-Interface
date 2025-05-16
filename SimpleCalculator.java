import javax.swing.*;
import java.awt.event.*;

public class SimpleCalculator extends JFrame {

    JTextField num1Field, num2Field;
    JLabel resultLabel;
    JButton addBtn, subBtn, mulBtn, divBtn;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setSize(300, 300);
        setLayout(null);             // No layout manager, absolute positioning
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center window

        // Create components
        num1Field = new JTextField();
        num1Field.setBounds(50, 20, 200, 30);
        add(num1Field);

        num2Field = new JTextField();
        num2Field.setBounds(50, 60, 200, 30);
        add(num2Field);

        resultLabel = new JLabel("Result: ");
        resultLabel.setBounds(50, 100, 200, 30);
        add(resultLabel);

        addBtn = new JButton("+");
        addBtn.setBounds(50, 140, 60, 40);
        add(addBtn);

        subBtn = new JButton("-");
        subBtn.setBounds(120, 140, 60, 40);
        add(subBtn);

        mulBtn = new JButton("*");
        mulBtn.setBounds(50, 190, 60, 40);
        add(mulBtn);

        divBtn = new JButton("/");
        divBtn.setBounds(120, 190, 60, 40);
        add(divBtn);

        // Add listeners to buttons using basic anonymous inner classes

        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculate('+');
            }
        });

        subBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculate('-');
            }
        });

        mulBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculate('*');
            }
        });

        divBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculate('/');
            }
        });

        setVisible(true);
    }

    // Simple method to do calculation and show result
    public void calculate(char op) {
        try {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = Double.parseDouble(num2Field.getText());
            double res = 0;

            if (op == '+') {
                res = num1 + num2;
            } else if (op == '-') {
                res = num1 - num2;
            } else if (op == '*') {
                res = num1 * num2;
            } else if (op == '/') {
                if (num2 == 0) {
                    JOptionPane.showMessageDialog(this, "Cannot divide by zero");
                    return;
                }
                res = num1 / num2;
            }

            resultLabel.setText("Result: " + res);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers");
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}
