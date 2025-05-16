import javax.swing.*;      // Swing components for GUI (JFrame, JButton, JLabel, etc.)
import java.awt.event.*;   // AWT event classes for handling user actions (like button clicks)

public class UniversityPortal extends JFrame {

    // Text fields for user input
    JTextField cmsField, nameField, ageField, addressField, semesterField;
    JButton submitButton;

    public UniversityPortal() {
        setTitle("University Portal");
        setSize(500, 500);                // Set the frame size to 500x500 pixels
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);                  // Use no layout manager (absolute positioning)
        setLocationRelativeTo(null);     // Center the window on screen

        // Create and position labels and text fields manually using setBounds (AWT method)

        JLabel cmsLabel = new JLabel("CMS:");
        cmsLabel.setBounds(50, 50, 100, 25);
        add(cmsLabel);

        cmsField = new JTextField();
        cmsField.setBounds(150, 50, 200, 25);
        add(cmsField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 90, 100, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 90, 200, 25);
        add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 130, 100, 25);
        add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(150, 130, 200, 25);
        add(ageField);

        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 170, 100, 25);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(150, 170, 200, 25);
        add(addressField);

        JLabel semesterLabel = new JLabel("Semester:");
        semesterLabel.setBounds(50, 210, 100, 25);
        add(semesterLabel);

        semesterField = new JTextField();
        semesterField.setBounds(150, 210, 200, 25);
        add(semesterField);

        // Submit button
        submitButton = new JButton("Submit");
        submitButton.setBounds(200, 260, 100, 30);
        add(submitButton);

        // ActionListener to handle button click (AWT event handling)
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Collect the data entered by the user
                String cms = cmsField.getText();
                String name = nameField.getText();
                String age = ageField.getText();
                String address = addressField.getText();
                String semester = semesterField.getText();

                // Show a dialog with all the student info
                JOptionPane.showMessageDialog(null,
                        "CMS: " + cms + "\n" +
                                "Name: " + name + "\n" +
                                "Age: " + age + "\n" +
                                "Address: " + address + "\n" +
                                "Semester: " + semester,
                        "Student Info",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        setVisible(true);  // Show the window
    }

    public static void main(String[] args) {
        new UniversityPortal();
    }
}
