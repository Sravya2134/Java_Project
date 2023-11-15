import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Login extends JFrame implements ActionListener {
    JButton login, Cancel;
    JTextField tfusername, tfpassword;

    Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Set the heading to "Tourist Site" with styling
        JLabel headingLabel = new JLabel("Tourist Site");
        headingLabel.setBounds(150, 10, 300, 30);
        headingLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        headingLabel.setForeground(new Color(70, 130, 180)); // Use a shade of blue color
        add(headingLabel);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 60, 100, 20);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(150, 60, 150, 20);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 100, 100, 20);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 100, 150, 20);
        add(tfpassword);

        login = new JButton("Login");
        login.setBounds(40, 160, 120, 30);
        login.setBackground(new Color(70, 130, 180)); // Use the same shade of blue color
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        login.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(login);

        Cancel = new JButton("Cancel");
        Cancel.setBounds(180, 160, 120, 30);
        Cancel.setBackground(new Color(70, 130, 180)); // Use the same shade of blue color
        Cancel.setForeground(Color.WHITE);
        Cancel.addActionListener(this);
        Cancel.setFont(new Font("Tahoma", Font.BOLD, 16));
        add(Cancel);

        setSize(400, 250); // Adjusted size for better visibility
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            // For demonstration purposes, checking against hardcoded values
            String enteredUsername = tfusername.getText();
            String enteredPassword = tfpassword.getText();

            // Replace the following condition with your actual authentication logic
            if ("Sru".equals(enteredUsername) && "123456".equals(enteredPassword)) {
                setVisible(false);
                new TouristGUI();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
            }
        } else if (ae.getSource() == Cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login());
    }
}
