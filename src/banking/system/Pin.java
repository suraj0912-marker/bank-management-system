package banking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pin extends JFrame implements ActionListener {

    private JButton changeBtn, backBtn;
    private JPasswordField pinField1, pinField2;
    private String currentPin;

    public Pin(String pin) {
        this.currentPin = pin;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        JLabel background = new JLabel(new ImageIcon(i2));
        background.setBounds(0, 0, 1550, 830);
        add(background);

        JLabel titleLabel = new JLabel("CHANGE YOUR PIN");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("System", Font.BOLD, 28));
        titleLabel.setBounds(430, 180, 400, 35);
        background.add(titleLabel);

        JLabel newPinLabel = new JLabel("New PIN:");
        newPinLabel.setForeground(Color.WHITE);
        newPinLabel.setFont(new Font("System", Font.BOLD, 18));
        newPinLabel.setBounds(430, 230, 150, 35);
        background.add(newPinLabel);

        pinField1 = new JPasswordField();
        pinField1.setBackground(new Color(65, 125, 128));
        pinField1.setForeground(Color.WHITE);
        pinField1.setFont(new Font("Raleway", Font.BOLD, 22));
        pinField1.setBounds(600, 230, 180, 30);
        background.add(pinField1);

        JLabel reEnterPinLabel = new JLabel("Re-Enter New PIN:");
        reEnterPinLabel.setForeground(Color.WHITE);
        reEnterPinLabel.setFont(new Font("System", Font.BOLD, 18));
        reEnterPinLabel.setBounds(430, 280, 180, 35);
        background.add(reEnterPinLabel);

        pinField2 = new JPasswordField();
        pinField2.setBackground(new Color(65, 125, 128));
        pinField2.setForeground(Color.WHITE);
        pinField2.setFont(new Font("Raleway", Font.BOLD, 22));
        pinField2.setBounds(600, 280, 180, 30);
        background.add(pinField2);

        changeBtn = new JButton("CHANGE");
        changeBtn.setBounds(700, 350, 150, 35);
        changeBtn.setBackground(new Color(65, 125, 128));
        changeBtn.setForeground(Color.WHITE);
        changeBtn.setFocusPainted(false);
        changeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        changeBtn.addActionListener(this);
        background.add(changeBtn);

        backBtn = new JButton("BACK");
        backBtn.setBounds(700, 400, 150, 35);
        backBtn.setBackground(new Color(65, 125, 128));
        backBtn.setForeground(Color.WHITE);
        backBtn.setFocusPainted(false);
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(this);
        background.add(backBtn);

        setSize(1550, 1080);
        setLayout(null);
        setLocation(0, 0);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeBtn) {
            String newPin = new String(pinField1.getPassword());
            String reEnteredPin = new String(pinField2.getPassword());

            if (newPin.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter new PIN");
                return;
            }
            if (reEnteredPin.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please re-enter new PIN");
                return;
            }
            if (!newPin.equals(reEnteredPin)) {
                JOptionPane.showMessageDialog(this, "Entered PIN does not match");
                return;
            }

            try {
                Connn c = new Connn();

                String q1 = "UPDATE bank SET pin = '" + newPin + "' WHERE pin = '" + currentPin + "'";
                String q2 = "UPDATE login SET pin = '" + newPin + "' WHERE pin = '" + currentPin + "'";
                String q3 = "UPDATE signupthree SET pin = '" + newPin + "' WHERE pin = '" + currentPin + "'";

                c.statement.executeUpdate(q1);
                c.statement.executeUpdate(q2);
                c.statement.executeUpdate(q3);

                JOptionPane.showMessageDialog(this, "PIN changed successfully");
                setVisible(false);
                new main_Class(newPin);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error changing PIN. Please try again.");
                ex.printStackTrace();
            }

        } else if (e.getSource() == backBtn) {
            setVisible(false);
            new main_Class(currentPin);
        }
    }

    public static void main(String[] args) {
        new Pin("");
    }
}
