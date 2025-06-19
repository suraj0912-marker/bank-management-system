package banking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener {

    private String pin;
    private JButton btn100, btn500, btn1000, btn2000, btn5000, btn10000, backBtn;

    public FastCash(String pin) {
        this.pin = pin;

        setTitle("Fast Cash - Banking System");
        getContentPane().setBackground(new Color(245, 255, 250));
        setLayout(null);

        JLabel heading = new JLabel("Select Withdrawal Amount");
        heading.setFont(new Font("Raleway", Font.BOLD, 22));
        heading.setBounds(100, 30, 400, 30);
        add(heading);

        btn100 = createButton("₹100", 50, 80);
        btn500 = createButton("₹500", 220, 80);
        btn1000 = createButton("₹1000", 50, 130);
        btn2000 = createButton("₹2000", 220, 130);
        btn5000 = createButton("₹5000", 50, 180);
        btn10000 = createButton("₹10000", 220, 180);

        backBtn = new JButton("Back");
        backBtn.setBounds(135, 240, 120, 35);
        backBtn.setFont(new Font("Arial", Font.BOLD, 14));
        backBtn.setBackground(Color.GRAY);
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(this);
        add(backBtn);

        setSize(450, 350);
        setLocation(500, 250);
        setVisible(true);
    }

    private JButton createButton(String text, int x, int y) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBackground(new Color(0, 102, 204));
        btn.setForeground(Color.WHITE);
        btn.setBounds(x, y, 120, 35);
        btn.addActionListener(this);
        add(btn);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backBtn) {
            setVisible(false);
            new main_Class(pin);
            return;
        }

        String amountText = ((JButton) e.getSource()).getText().replace("₹", "").trim();
        int withdrawAmount = Integer.parseInt(amountText);

        try {
            Connn c = new Connn();
            ResultSet rs = c.statement.executeQuery("SELECT * FROM bank WHERE pin = '" + pin + "'");
            int balance = 0;

            while (rs.next()) {
                String type = rs.getString("type");
                int amt = Integer.parseInt(rs.getString("amount"));
                balance += type.equalsIgnoreCase("Deposit") ? amt : -amt;
            }

            if (withdrawAmount > balance) {
                JOptionPane.showMessageDialog(this, "Insufficient Balance");
                return;
            }

            Date date = new Date();
            String query = "INSERT INTO bank VALUES('" + pin + "', '" + date + "', 'Withdraw', '" + withdrawAmount + "')";
            c.statement.executeUpdate(query);

            JOptionPane.showMessageDialog(this, "₹" + withdrawAmount + " withdrawn successfully.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Transaction failed.");
        }

        setVisible(false);
        new main_Class(pin);
    }

    public static void main(String[] args) {
        new FastCash("");
    }
}
