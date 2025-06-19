package banking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;

public class Withdrawal extends JFrame implements ActionListener {

    private String pin;
    private JTextField amountField;
    private JButton withdrawBtn, cancelBtn;

    public Withdrawal(String pin) {
        this.pin = pin;

        setTitle("Banking System - Withdraw");
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

        JLabel heading = new JLabel("Withdraw Money", SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setBounds(0, 20, 400, 30);
        panel.add(heading);

        JLabel prompt = new JLabel("Enter amount to withdraw:");
        prompt.setFont(new Font("Arial", Font.PLAIN, 16));
        prompt.setBounds(50, 70, 300, 20);
        panel.add(prompt);

        amountField = new JTextField();
        amountField.setFont(new Font("Arial", Font.PLAIN, 16));
        amountField.setBounds(50, 100, 300, 35);
        amountField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panel.add(amountField);

        withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setFont(new Font("Arial", Font.BOLD, 14));
        withdrawBtn.setBounds(50, 160, 120, 35);
        withdrawBtn.setBackground(Color.BLACK);
        withdrawBtn.setForeground(Color.WHITE);
        withdrawBtn.setFocusPainted(false);
        withdrawBtn.addActionListener(this);
        panel.add(withdrawBtn);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setFont(new Font("Arial", Font.BOLD, 14));
        cancelBtn.setBounds(230, 160, 120, 35);
        cancelBtn.setBackground(Color.GRAY);
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusPainted(false);
        cancelBtn.addActionListener(this);
        panel.add(cancelBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdrawBtn) {
            String inputAmount = amountField.getText().trim();

            if (inputAmount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter the amount to withdraw.");
                return;
            }

            int amountToWithdraw;
            try {
                amountToWithdraw = Integer.parseInt(inputAmount);
                if (amountToWithdraw <= 0) {
                    JOptionPane.showMessageDialog(this, "Enter a valid positive amount.");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid numeric amount.");
                return;
            }

            try {
                Connn conn = new Connn();
                ResultSet rs = conn.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");

                int balance = 0;
                while (rs.next()) {
                    String type = rs.getString("type");
                    int amt = Integer.parseInt(rs.getString("amount"));

                    if (type.equalsIgnoreCase("Deposit")) {
                        balance += amt;
                    } else if (type.equalsIgnoreCase("Withdrawal")) {
                        balance -= amt;
                    }
                }

                if (balance < amountToWithdraw) {
                    JOptionPane.showMessageDialog(this, "Insufficient balance.");
                    return;
                }

                Date date = new Date();
                String query = "INSERT INTO bank VALUES('" + pin + "', '" + date + "', 'Withdrawal', '" + amountToWithdraw + "')";
                conn.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(this, "₹" + amountToWithdraw + " withdrawn successfully.");
                setVisible(false);
                new main_Class(pin);

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Withdrawal failed. Please try again.");
            }

        } else if (e.getSource() == cancelBtn) {
            setVisible(false);
            new main_Class(pin);
        }
    }

    public static void main(String[] args) {
        new Withdrawal("");
    }
}
