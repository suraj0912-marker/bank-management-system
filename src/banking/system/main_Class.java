package banking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class main_Class extends JFrame implements ActionListener {

    private JButton depositBtn, withdrawBtn, fastCashBtn, miniStmtBtn, pinChangeBtn, balanceEnquiryBtn, exitBtn;
    private String pin;

    public main_Class(String pin) {
        this.pin = pin;

        setTitle("Transaction Menu - Banking System");
        setLayout(null);
        getContentPane().setBackground(new Color(245, 255, 250));

        JLabel heading = new JLabel("Please Select Your Transaction");
        heading.setFont(new Font("Raleway", Font.BOLD, 24));
        heading.setBounds(70, 30, 400, 30);
        add(heading);

        depositBtn = createButton("Deposit", 50, 80);
        withdrawBtn = createButton("Cash Withdrawal", 220, 80);
        fastCashBtn = createButton("Fast Cash", 50, 130);
        miniStmtBtn = createButton("Mini Statement", 220, 130);
        pinChangeBtn = createButton("Pin Change", 50, 180);
        balanceEnquiryBtn = createButton("Balance Enquiry", 220, 180);
        exitBtn = createButton("Exit", 135, 240);

        setSize(450, 350);
        setLocationRelativeTo(null);  // Center screen
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JButton createButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 150, 35);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(new Color(0, 102, 204));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(this);
        add(btn);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == depositBtn) {
            setVisible(false);
            new Deposit(pin);
        } else if (source == withdrawBtn) {
            setVisible(false);
            new Withdrawal(pin);
        } else if (source == fastCashBtn) {
            setVisible(false);
            new FastCash(pin);
        } else if (source == miniStmtBtn) {
            new mini(pin);
        } else if (source == pinChangeBtn) {
            setVisible(false);
            new Pin(pin);
        } else if (source == balanceEnquiryBtn) {
            setVisible(false);
            new BalanceEnquiry(pin);
        } else if (source == exitBtn) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new main_Class(""); // For testing
    }
}
