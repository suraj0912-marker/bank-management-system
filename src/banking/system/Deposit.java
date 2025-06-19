package banking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class Deposit extends JFrame implements ActionListener {

    private String pin;
    private JTextField amountField;
    private JButton depositBtn, backBtn;

    public Deposit(String pin) {
        this.pin = pin;

        setTitle("Deposit - Banking System");
        setSize(500, 400);
        setLocation(480, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(200, 240, 240));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(40, 50, 400, 260);
        panel.setBackground(new Color(245, 255, 255));
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1, true));
        add(panel);

        JLabel heading = new JLabel("Deposit Money", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setBounds(0, 20, 400, 30);
        panel.add(heading);

        JLabel prompt = new JLabel("Enter the amount you want to deposit:");
        prompt.setFont(new Font("Arial", Font.PLAIN, 16));
        prompt.setBounds(50, 70, 300, 20);
        panel.add(prompt);

        amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        amountField.setBounds(50, 100, 300, 35);
        amountField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panel.add(amountField);

        depositBtn = new JButton("Deposit");
        depositBtn.setFont(new Font("Arial", Font.BOLD, 14));
        depositBtn.setBounds(50, 160, 120, 35);
        depositBtn.setBackground(Color.BLACK);
        depositBtn.setForeground(Color.WHITE);
        depositBtn.addActionListener(this);
        panel.add(depositBtn);

        backBtn = new JButton("Back");
        backBtn.setFont(new Font("Arial", Font.BOLD, 14));
        backBtn.setBounds(230, 160, 120, 35);
        backBtn.setBackground(Color.GRAY);
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(this);
        panel.add(backBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String amount = amountField.getText().trim();
        Date date = new Date();

        if (e.getSource() == depositBtn) {
            if (amount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter an amount to deposit.");
                return;
            }

            try {
                Connn connn = new Connn();
                String query = "INSERT INTO bank VALUES('" + pin + "', '" + date + "', 'Deposit', '" + amount + "')";
                connn.statement.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "₹" + amount + " deposited successfully.");
                setVisible(false);
                new main_Class(pin);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error occurred during deposit.");
            }

        } else if (e.getSource() == backBtn) {
            setVisible(false);
            new main_Class(pin);
        }
    }

    public static void main(String[] args) {
        new Deposit("");
    }
}
