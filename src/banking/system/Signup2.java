package banking.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Signup2 extends JFrame implements ActionListener {
    JComboBox<String> religionBox, categoryBox, incomeBox, educationBox, occupationBox;
    JTextField panField, aadharField;
    JRadioButton seniorYes, seniorNo, accountYes, accountNo;
    JButton nextBtn;
    String formno;

    Signup2(String formno) {
        this.formno = formno;
        setTitle("Bank Application - Page 2");
        setLayout(null);
        getContentPane().setBackground(new Color(173, 216, 230)); // Light blue background
        setSize(850, 700);
        setLocation(400, 100);

        JLabel heading1 = new JLabel("Page 2: Additional Details");
        heading1.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading1.setBounds(250, 20, 400, 30);
        add(heading1);

        int y = 80;
        int labelX = 100, fieldX = 300, width = 300, height = 30, gap = 50;

        addLabel("Religion:", labelX, y);
        religionBox = new JComboBox<>(new String[]{"Hindu", "Muslim", "Sikh", "Christian", "Other"});
        setCombo(religionBox, fieldX, y);

        y += gap;
        addLabel("Category:", labelX, y);
        categoryBox = new JComboBox<>(new String[]{"General", "OBC", "SC", "ST", "Other"});
        setCombo(categoryBox, fieldX, y);

        y += gap;
        addLabel("Income:", labelX, y);
        incomeBox = new JComboBox<>(new String[]{"Null", "<1,50,000", "<2,50,000", "5,00,000", "Upto 10,00,000", "Above 10,00,000"});
        setCombo(incomeBox, fieldX, y);

        y += gap;
        addLabel("Education:", labelX, y);
        educationBox = new JComboBox<>(new String[]{"Non-Graduate", "Graduate", "Post-Graduate", "Doctorate", "Others"});
        setCombo(educationBox, fieldX, y);

        y += gap;
        addLabel("Occupation:", labelX, y);
        occupationBox = new JComboBox<>(new String[]{"Salaried", "Self-Employed", "Business", "Student", "Retired", "Other"});
        setCombo(occupationBox, fieldX, y);

        y += gap;
        addLabel("PAN Number:", labelX, y);
        panField = new JTextField();
        panField.setBounds(fieldX, y, width, height);
        panField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(panField);

        y += gap;
        addLabel("Aadhar Number:", labelX, y);
        aadharField = new JTextField();
        aadharField.setBounds(fieldX, y, width, height);
        aadharField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(aadharField);

        y += gap;
        addLabel("Senior Citizen:", labelX, y);
        seniorYes = new JRadioButton("Yes");
        seniorNo = new JRadioButton("No");
        groupRadio(seniorYes, seniorNo, fieldX, y);

        y += gap;
        addLabel("Existing Account:", labelX, y);
        accountYes = new JRadioButton("Yes");
        accountNo = new JRadioButton("No");
        groupRadio(accountYes, accountNo, fieldX, y);

        JLabel formLabel = new JLabel("Form No: " + formno);
        formLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        formLabel.setBounds(650, 10, 150, 30);
        add(formLabel);

        nextBtn = new JButton("Next");
        nextBtn.setFont(new Font("Tahoma", Font.BOLD, 16));
        nextBtn.setBackground(Color.WHITE);
        nextBtn.setBounds(600, y + 50, 100, 35);
        nextBtn.addActionListener(this);
        add(nextBtn);

        setVisible(true);
    }

    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.BOLD, 16));
        label.setBounds(x, y, 150, 30);
        add(label);
    }

    private void setCombo(JComboBox<String> comboBox, int x, int y) {
        comboBox.setBounds(x, y, 300, 30);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox.setBackground(Color.WHITE);
        add(comboBox);
    }

    private void groupRadio(JRadioButton yes, JRadioButton no, int x, int y) {
        yes.setBounds(x, y, 80, 30);
        no.setBounds(x + 100, y, 80, 30);
        Color bg = new Color(173, 216, 230);
        yes.setBackground(bg);
        no.setBackground(bg);
        add(yes);
        add(no);
        ButtonGroup group = new ButtonGroup();
        group.add(yes);
        group.add(no);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String rel = (String) religionBox.getSelectedItem();
        String cate = (String) categoryBox.getSelectedItem();
        String inc = (String) incomeBox.getSelectedItem();
        String edu = (String) educationBox.getSelectedItem();
        String occ = (String) occupationBox.getSelectedItem();
        String pan = panField.getText();
        String aadhar = aadharField.getText();
        String senior = seniorYes.isSelected() ? "Yes" : (seniorNo.isSelected() ? "No" : "");
        String account = accountYes.isSelected() ? "Yes" : (accountNo.isSelected() ? "No" : "");

        if (pan.isEmpty() || aadhar.isEmpty() || senior.isEmpty() || account.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all the required fields.");
            return;
        }

        try {
            Connn conn = new Connn();
            String query = "INSERT INTO signup2 VALUES ('" + formno + "','" + rel + "','" + cate + "','" + inc + "','" + edu + "','" + occ + "','" + pan + "','" + aadhar + "','" + senior + "','" + account + "')";
            conn.statement.executeUpdate(query);

            setVisible(false);
            new Signup3(formno).setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Something went wrong. Please try again.");
        }
    }

    public static void main(String[] args) {
        new Signup2("12345");
    }
}
