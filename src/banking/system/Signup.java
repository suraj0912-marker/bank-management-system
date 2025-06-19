package banking.system;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Signup extends JFrame implements ActionListener {

    private JTextField nameField, fatherField, emailField, addressField, cityField, stateField, pinField;
    private JRadioButton maleBtn, femaleBtn, marriedBtn, unmarriedBtn, otherBtn;
    private JButton nextBtn;
    private JDateChooser dobChooser;
    private String formNo;

    public Signup() {
        super("Banking System - Signup");

        formNo = String.valueOf(Math.abs(new Random().nextLong() % 9000L + 1000L));

        setSize(850, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(173, 216, 230)); // Light blue background

        JLabel heading = new JLabel("APPLICATION FORM NO: " + formNo);
        heading.setFont(new Font("Raleway", Font.BOLD, 24));
        heading.setBounds(250, 30, 400, 40);
        add(heading);

        JLabel subHeading = new JLabel("Page 1: Personal Details");
        subHeading.setFont(new Font("Raleway", Font.PLAIN, 20));
        subHeading.setBounds(320, 70, 250, 30);
        add(subHeading);

        int labelX = 100, fieldX = 300, y = 130, gap = 40;

        addLabel("Name:", labelX, y);
        nameField = createTextField(fieldX, y);
        add(nameField);

        addLabel("Father's Name:", labelX, y += gap);
        fatherField = createTextField(fieldX, y);
        add(fatherField);

        addLabel("Date of Birth:", labelX, y += gap);
        dobChooser = new JDateChooser();
        dobChooser.setBounds(fieldX, y, 300, 30);
        dobChooser.setFont(new Font("Raleway", Font.PLAIN, 16));
        add(dobChooser);

        addLabel("Gender:", labelX, y += gap);
        maleBtn = new JRadioButton("Male");
        femaleBtn = new JRadioButton("Female");
        groupAndAddButtons(maleBtn, femaleBtn, fieldX, y);
        add(maleBtn);
        add(femaleBtn);

        addLabel("Email:", labelX, y += gap);
        emailField = createTextField(fieldX, y);
        add(emailField);

        addLabel("Marital Status:", labelX, y += gap);
        marriedBtn = new JRadioButton("Married");
        unmarriedBtn = new JRadioButton("Unmarried");
        otherBtn = new JRadioButton("Other");
        groupAndAddButtons(marriedBtn, unmarriedBtn, otherBtn, fieldX, y);
        add(marriedBtn);
        add(unmarriedBtn);
        add(otherBtn);

        addLabel("Address:", labelX, y += gap);
        addressField = createTextField(fieldX, y);
        add(addressField);

        addLabel("City:", labelX, y += gap);
        cityField = createTextField(fieldX, y);
        add(cityField);

        addLabel("Pin Code:", labelX, y += gap);
        pinField = createTextField(fieldX, y);
        add(pinField);

        addLabel("State:", labelX, y += gap);
        stateField = createTextField(fieldX, y);
        add(stateField);

        nextBtn = new JButton("Next");
        nextBtn.setBounds(350, y += gap + 30, 120, 40);
        nextBtn.setFont(new Font("Raleway", Font.BOLD, 16));
        nextBtn.setBackground(new Color(0, 128, 128));
        nextBtn.setForeground(Color.WHITE);
        nextBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nextBtn.setFocusPainted(false);
        nextBtn.addActionListener(this);
        add(nextBtn);

        setVisible(true);
    }

    private void addLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Raleway", Font.BOLD, 18));
        label.setBounds(x, y, 150, 30);
        add(label);
    }

    private JTextField createTextField(int x, int y) {
        JTextField tf = new JTextField();
        tf.setBounds(x, y, 300, 30);
        tf.setFont(new Font("Raleway", Font.PLAIN, 16));
        return tf;
    }

    private void groupAndAddButtons(JRadioButton b1, JRadioButton b2, int x, int y) {
        ButtonGroup group = new ButtonGroup();
        group.add(b1);
        group.add(b2);

        b1.setBounds(x, y, 100, 30);
        b2.setBounds(x + 120, y, 100, 30);

        Color bg = new Color(173, 216, 230); // Light blue
        b1.setBackground(bg);
        b2.setBackground(bg);
    }

    private void groupAndAddButtons(JRadioButton b1, JRadioButton b2, JRadioButton b3, int x, int y) {
        ButtonGroup group = new ButtonGroup();
        group.add(b1);
        group.add(b2);
        group.add(b3);

        b1.setBounds(x, y, 100, 30);
        b2.setBounds(x + 110, y, 120, 30);
        b3.setBounds(x + 240, y, 100, 30);

        Color bg = new Color(173, 216, 230); // Light blue
        b1.setBackground(bg);
        b2.setBackground(bg);
        b3.setBackground(bg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText().trim();
        String father = fatherField.getText().trim();
        String dob = ((JTextField) dobChooser.getDateEditor().getUiComponent()).getText().trim();
        String gender = maleBtn.isSelected() ? "Male" : femaleBtn.isSelected() ? "Female" : "";
        String email = emailField.getText().trim();
        String marital = marriedBtn.isSelected() ? "Married" : unmarriedBtn.isSelected() ? "Unmarried" : otherBtn.isSelected() ? "Other" : "";
        String address = addressField.getText().trim();
        String city = cityField.getText().trim();
        String pin = pinField.getText().trim();
        String state = stateField.getText().trim();

        if (name.isEmpty() || father.isEmpty() || dob.isEmpty() || gender.isEmpty() ||
                email.isEmpty() || marital.isEmpty() || address.isEmpty() ||
                city.isEmpty() || pin.isEmpty() || state.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Please fill all the fields.");
            return;
        }

        try {
            Connn conn = new Connn();
            String query = "INSERT INTO signup VALUES('" + formNo + "', '" + name + "', '" + father + "', '" +
                    dob + "', '" + gender + "', '" + email + "', '" + marital + "', '" + address + "', '" +
                    city + "', '" + pin + "', '" + state + "')";
            conn.getStatement().executeUpdate(query);

            setVisible(false);
            new Signup2(formNo);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Oops! Something went wrong.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Signup::new);
    }
}
