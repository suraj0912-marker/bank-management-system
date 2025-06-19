package banking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Signup3 extends JFrame implements ActionListener {

    JRadioButton savingsBtn, fixedBtn, currentBtn, recurringBtn;
    JCheckBox atmBox, netBankingBox, mobileBox, emailAlertBox, chequeBox, eStatementBox, declarationBox;
    JButton submitBtn, cancelBtn;
    String formno;

    Signup3(String formno) {
        this.formno = formno;

        setTitle("Bank Application - Page 3");
        setLayout(null);
        getContentPane().setBackground(new Color(220, 245, 255));
        setSize(600, 550);
        setLocation(400, 150);

        JLabel heading = new JLabel("Page 3: Account Details");
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        heading.setBounds(170, 20, 300, 30);
        add(heading);

        JLabel accountTypeLabel = new JLabel("Account Type:");
        accountTypeLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        accountTypeLabel.setBounds(50, 80, 150, 25);
        add(accountTypeLabel);

        savingsBtn = new JRadioButton("Saving Account");
        fixedBtn = new JRadioButton("Fixed Deposit");
        currentBtn = new JRadioButton("Current Account");
        recurringBtn = new JRadioButton("Recurring Deposit");

        groupRadio(savingsBtn, 50, 110);
        groupRadio(fixedBtn, 250, 110);
        groupRadio(currentBtn, 50, 140);
        groupRadio(recurringBtn, 250, 140);

        ButtonGroup group = new ButtonGroup();
        group.add(savingsBtn);
        group.add(fixedBtn);
        group.add(currentBtn);
        group.add(recurringBtn);

        JLabel cardLabel = new JLabel("Card Number:");
        cardLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        cardLabel.setBounds(50, 180, 150, 25);
        add(cardLabel);

        JLabel cardValue = new JLabel("XXXX-XXXX-XXXX-9845");
        cardValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        cardValue.setBounds(200, 180, 250, 25);
        add(cardValue);

        JLabel pinLabel = new JLabel("PIN:");
        pinLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        pinLabel.setBounds(50, 210, 150, 25);
        add(pinLabel);

        JLabel pinValue = new JLabel("XXXX");
        pinValue.setFont(new Font("Tahoma", Font.PLAIN, 16));
        pinValue.setBounds(200, 210, 100, 25);
        add(pinValue);

        JLabel servicesLabel = new JLabel("Services Required:");
        servicesLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        servicesLabel.setBounds(50, 250, 200, 25);
        add(servicesLabel);

        atmBox = new JCheckBox("ATM Card");
        netBankingBox = new JCheckBox("Internet Banking");
        mobileBox = new JCheckBox("Mobile Banking");
        emailAlertBox = new JCheckBox("Email Alerts");
        chequeBox = new JCheckBox("Cheque Book");
        eStatementBox = new JCheckBox("E-Statement");
        declarationBox = new JCheckBox("I declare all information is correct");

        groupCheckBox(atmBox, 50, 280);
        groupCheckBox(netBankingBox, 250, 280);
        groupCheckBox(mobileBox, 50, 310);
        groupCheckBox(emailAlertBox, 250, 310);
        groupCheckBox(chequeBox, 50, 340);
        groupCheckBox(eStatementBox, 250, 340);
        groupCheckBox(declarationBox, 50, 380, 400);

        submitBtn = new JButton("Submit");
        cancelBtn = new JButton("Cancel");

        styleButton(submitBtn, 150);
        styleButton(cancelBtn, 300);

        setVisible(true);
    }

    private void groupRadio(JRadioButton btn, int x, int y) {
        btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        btn.setBackground(new Color(220, 245, 255));
        btn.setBounds(x, y, 180, 25);
        add(btn);
    }

    private void groupCheckBox(JCheckBox box, int x, int y) {
        groupCheckBox(box, x, y, 180);
    }

    private void groupCheckBox(JCheckBox box, int x, int y, int width) {
        box.setFont(new Font("Tahoma", Font.PLAIN, 14));
        box.setBackground(new Color(220, 245, 255));
        box.setBounds(x, y, width, 25);
        add(box);
    }

    private void styleButton(JButton btn, int x) {
        btn.setFont(new Font("Tahoma", Font.BOLD, 14));
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setBounds(x, 430, 100, 30);
        btn.addActionListener(this);
        add(btn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitBtn) {
            String accountType = null;
            if (savingsBtn.isSelected()) accountType = "Saving Account";
            else if (fixedBtn.isSelected()) accountType = "Fixed Deposit";
            else if (currentBtn.isSelected()) accountType = "Current Account";
            else if (recurringBtn.isSelected()) accountType = "Recurring Deposit";

            String services = "";
            if (atmBox.isSelected()) services += "ATM Card, ";
            if (netBankingBox.isSelected()) services += "Internet Banking, ";
            if (mobileBox.isSelected()) services += "Mobile Banking, ";
            if (emailAlertBox.isSelected()) services += "Email Alerts, ";
            if (chequeBox.isSelected()) services += "Cheque Book, ";
            if (eStatementBox.isSelected()) services += "E-Statement, ";

            if (accountType == null || !declarationBox.isSelected()) {
                JOptionPane.showMessageDialog(this, "Please select account type and agree to the declaration.");
                return;
            }

            try {
                Connn conn = new Connn();
                String query = "INSERT INTO signup3 VALUES ('" + formno + "', '" + accountType + "', 'XXXX-XXXX-XXXX-9845', 'XXXX', '" + services + "')";
                conn.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(this, "Account Created Successfully!");
                setVisible(false);
                new Login().setVisible(true); // Redirect to login
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error occurred during account creation.");
            }
        } else if (e.getSource() == cancelBtn) {
            setVisible(false);
            new Login().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new Signup3("12345");
    }
}
