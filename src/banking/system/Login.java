package banking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {

    private JTextField cardField;
    private JPasswordField pinField;
    private JButton signInBtn, clearBtn, signUpBtn;

    public Login() {
        super("Banking System Login");

        setLayout(null);
        setSize(720, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(20, 20, 50));

        JLabel labelTitle = new JLabel("Welcome to Banking System");
        labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
        labelTitle.setForeground(Color.WHITE);
        labelTitle.setBounds(180, 40, 500, 40);
        add(labelTitle);

        JLabel labelCardNo = new JLabel("Card Number:");
        labelCardNo.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        labelCardNo.setForeground(Color.WHITE);
        labelCardNo.setBounds(150, 120, 150, 30);
        add(labelCardNo);

        cardField = new JTextField();
        cardField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        cardField.setBounds(300, 120, 250, 30);
        add(cardField);

        JLabel labelPIN = new JLabel("PIN:");
        labelPIN.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        labelPIN.setForeground(Color.WHITE);
        labelPIN.setBounds(150, 180, 150, 30);
        add(labelPIN);

        pinField = new JPasswordField();
        pinField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        pinField.setBounds(300, 180, 250, 30);
        add(pinField);

        signInBtn = createButton("Sign In", 180, 260);
        clearBtn = createButton("Clear", 300, 260);
        signUpBtn = createButton("Sign Up", 420, 260);

        add(signInBtn);
        add(clearBtn);
        add(signUpBtn);

        setVisible(true);
    }

    private JButton createButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 100, 35);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBackground(new Color(70, 130, 180));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(this);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == signInBtn) {
                String cardNo = cardField.getText().trim();
                String pin = new String(pinField.getPassword()).trim();

                if (cardNo.isEmpty() || pin.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter both Card Number and PIN.", "Missing Info", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Connn conn = new Connn();
                String query = "SELECT * FROM login WHERE card_number = '" + cardNo + "' AND pin = '" + pin + "'";
                ResultSet rs = conn.getStatement().executeQuery(query);

                if (rs.next()) {
                    setVisible(false);
                    new main_Class(pin);
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect Card Number or PIN.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }

            } else if (e.getSource() == clearBtn) {
                cardField.setText("");
                pinField.setText("");

            } else if (e.getSource() == signUpBtn) {
                setVisible(false);
                new Signup();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Something went wrong. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
