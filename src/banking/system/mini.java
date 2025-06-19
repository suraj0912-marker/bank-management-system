package banking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class mini extends JFrame implements ActionListener {

    private String pin;
    private JTextArea statementArea;
    private JLabel cardLabel, balanceLabel;
    private JButton exitBtn;

    public mini(String pin) {
        this.pin = pin;

        setTitle("Mini Statement");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(new Color(245, 255, 250));

        JLabel bankLabel = new JLabel("BANK OF SURAJ");
        bankLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        bankLabel.setBounds(120, 10, 200, 30);
        add(bankLabel);

        cardLabel = new JLabel();
        cardLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        cardLabel.setBounds(20, 50, 360, 20);
        add(cardLabel);

        statementArea = new JTextArea();
        statementArea.setFont(new Font("Courier New", Font.PLAIN, 13));
        statementArea.setEditable(false);
        statementArea.setBackground(new Color(255, 255, 240));

        JScrollPane scrollPane = new JScrollPane(statementArea);
        scrollPane.setBounds(20, 80, 340, 300);
        add(scrollPane);

        balanceLabel = new JLabel();
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        balanceLabel.setBounds(20, 390, 300, 25);
        add(balanceLabel);

        exitBtn = new JButton("Close");
        exitBtn.setBounds(280, 420, 80, 30);
        exitBtn.setBackground(new Color(0, 102, 204));
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setFocusPainted(false);
        exitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitBtn.addActionListener(this);
        add(exitBtn);

        loadCardNumber();
        loadTransactionHistory();

        setVisible(true);
    }

    private void loadCardNumber() {
        try {
            Connn c = new Connn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM login WHERE pin = '" + pin + "'");
            if (rs.next()) {
                String card = rs.getString("card_number");
                String masked = card.substring(0, 4) + "XXXXXXXX" + card.substring(12);
                cardLabel.setText("Card Number: " + masked);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTransactionHistory() {
        try {
            Connn c = new Connn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");
            int balance = 0;
            StringBuilder sb = new StringBuilder();

            sb.append("  DATE         TYPE        AMOUNT\n");
            sb.append("  --------------------------------\n");

            while (rs.next()) {
                String date = rs.getString("date");
                String type = rs.getString("type");
                String amount = rs.getString("amount");

                sb.append(String.format("  %-12s %-10s Rs.%-6s\n", date.substring(0, 10), type, amount));

                if (type.equalsIgnoreCase("Deposit")) {
                    balance += Integer.parseInt(amount);
                } else {
                    balance -= Integer.parseInt(amount);
                }
            }

            statementArea.setText(sb.toString());
            balanceLabel.setText("Available Balance: ₹" + balance);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
    }

    public static void main(String[] args) {
        new mini("");
    }
}
