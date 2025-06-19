package banking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BalanceEnquiry extends JFrame implements ActionListener {

    String pin;
    JLabel balanceLbl;
    JButton backBtn;

    BalanceEnquiry(String pin) {
        this.pin = pin;
        setTitle("Balance Enquiry");
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(400, 200, 500, 300);
        panel.setBackground(new Color(240, 248, 255));
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2, true));
        add(panel);

        JLabel heading = new JLabel("Your Current Balance");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setForeground(new Color(0, 51, 102));
        heading.setBounds(130, 30, 300, 30);
        panel.add(heading);

        balanceLbl = new JLabel("Loading...");
        balanceLbl.setFont(new Font("Segoe UI", Font.BOLD, 26));
        balanceLbl.setForeground(new Color(0, 102, 51));
        balanceLbl.setBounds(160, 90, 300, 40);
        panel.add(balanceLbl);

        backBtn = new JButton("Back");
        backBtn.setBounds(200, 200, 100, 35);
        backBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backBtn.setBackground(new Color(0, 102, 204));
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(this);
        panel.add(backBtn);

        getContentPane().setBackground(new Color(230, 230, 250));
        setSize(1366, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        checkBalance();
    }

    void checkBalance() {
        int balance = 0;
        try {
            Connn c = new Connn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");

            while (rs.next()) {
                String type = rs.getString("type");
                int amt = Integer.parseInt(rs.getString("amount"));
                if (type.equalsIgnoreCase("Deposit")) {
                    balance += amt;
                } else {
                    balance -= amt;
                }
            }
            balanceLbl.setText("₹ " + balance);
        } catch (Exception ex) {
            balanceLbl.setText("Error fetching balance");
        }
    }

    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new main_Class(pin);
    }

    public static void main(String[] args) {
        new BalanceEnquiry("1234");
    }
}
