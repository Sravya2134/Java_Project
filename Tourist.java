import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class NoBudgetException extends Exception {
    public NoBudgetException() {
        super("Budget not sufficient! Not available");
    }
}

public class TouristGUI {
    private JFrame frame;
    private JPanel panel;
    private JComboBox<String> choiceComboBox;
    private JTextField daysField;
    private JTextField budgetField;
    private JTextField locationField;
    private JTextField membersField;
    private JButton calculateButton;
    private JTextArea resultArea;
    private ImageIcon bhogathaImage;
    private ImageIcon pakalImage;
    private ImageIcon BhimneniImage;
    private ImageIcon vizagImage;
    private ImageIcon NagarjunaSagardamImage;
    private ImageIcon ShirdiImage;

    private JLabel imageLabel;

    public TouristGUI() {
        frame = new JFrame("Tourist Planner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.pink);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);

        imageLabel = new JLabel();
        bhogathaImage = new ImageIcon("C:\\Users\\sravy\\IdeaProjects\\Touristsite\\images\\bhogatha.jpg");
        pakalImage = new ImageIcon("C:\\Users\\sravy\\IdeaProjects\\Touristsite\\images\\Pakal.jpg");
        BhimneniImage = new ImageIcon("C:\\Users\\sravy\\IdeaProjects\\Touristsite\\images\\Bhimneni.jpg");
        vizagImage = new ImageIcon("C:\\Users\\sravy\\IdeaProjects\\Touristsite\\images\\vizag.jpg");
        NagarjunaSagardamImage = new ImageIcon(
                "C:\\Users\\sravy\\IdeaProjects\\Touristsite\\images\\NagarjunaSagardam.jpg");
        ShirdiImage = new ImageIcon("C:\\Users\\sravy\\IdeaProjects\\Touristsite\\images\\Shirdi.jpg");

        // Add a heading label
        // Add a heading label
        JLabel headingLabel = new JLabel(
                "<html><div style='text-align: center;'>Hello!! Jobers, Here you get the weekend plan</div></html>");
        Font headingFont = new Font("Arial", Font.BOLD, 50);
        headingLabel.setFont(headingFont);

        String[] choices = { "1 Day", "2 Days", "3 Days" };
        // Font comboBoxFont = new Font("Arial", Font.PLAIN, 50);
        // choiceComboBox.setFont(comboBoxFont);
        choiceComboBox = new JComboBox<>(choices);

        daysField = new JTextField(10);
        budgetField = new JTextField(10);
        locationField = new JTextField(10);
        membersField = new JTextField(10);
        calculateButton = new JButton("Calculate");
        resultArea = new JTextArea(5, 20);
        resultArea.setEditable(false);

        // Font inputFieldFont = new Font("Arial", Font.PLAIN, 40);
        // daysField.setFont(inputFieldFont);
        // budgetField.setFont(inputFieldFont);
        // membersField.setFont(inputFieldFont);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2; // Span two columns for the heading
        c.anchor = GridBagConstraints.CENTER;
        panel.add(headingLabel, c);

        c.gridy = 1;
        panel.add(Box.createVerticalStrut(10), c); // Add 20 pixels of vertical spacing

        // Add the components below the heading
        c.gridy = 2;
        c.gridwidth = 1;

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1; // Reset gridwidth
        panel.add(new JLabel("Choose Duration:"), c);
        c.gridx = 1;
        panel.add(choiceComboBox, c);

        // Rest of your code...
        // ... (existing code)

        Font inputFieldFont = new Font("times roman", Font.PLAIN, 40); // Choose your desired font size and style

        c.gridx = 0;
        c.gridy = 2;
        panel.add(new JLabel("Enter Days (Sunday Monday Saturday):"), c);
        c.gridx = 1;
        panel.add(daysField, c);
        daysField.setFont(inputFieldFont);

        c.gridx = 0;
        c.gridy = 3;
        panel.add(new JLabel("Enter Budget:"), c);
        c.gridx = 1;
        panel.add(budgetField, c);
        budgetField.setFont(inputFieldFont);

        // ... (rest of your code)

        // c.gridx = 0;
        // c.gridy = 3;
        // panel.add(new JLabel("Enter Location (kerala, Tamil Nadu, Varanasi, Jammu,
        // Delhi):"), c);
        // c.gridx = 1;
        // panel.add(locationField, c);

        // Choose your desired font size and style

        c.gridx = 0;
        c.gridy = 4;
        panel.add(new JLabel("Enter Number of Members:"), c);
        c.gridx = 1;
        c.gridwidth = 1; // Set gridwidth to 1
        panel.add(membersField, c);
        membersField.setFont(inputFieldFont);
        membersField.setToolTipText("Enter the number of members");

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(calculateButton, c);

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(new JLabel("Results:"), c);

        // ... (existing code)

        Font resultFont = new Font("Arial", Font.PLAIN, 35); // Choose your desired font size and style for the result

        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.CENTER;
        panel.add(new JScrollPane(resultArea), c);
        resultArea.setFont(resultFont);
        resultArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultArea.setAlignmentY(Component.CENTER_ALIGNMENT);

        // ... (rest of your code)

        // Add imageLabel to the panel
        c.gridx = 0;
        c.gridy = 8; // Adjust the position to your preference
        c.gridwidth = 2;
        panel.add(imageLabel, c);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root",
                            "Sravya@123")) {
                        Statement stmt = con.createStatement();
                        String no_of_days = (String) choiceComboBox.getSelectedItem();
                        String days = daysField.getText();
                        String Budget = budgetField.getText();
                        String members = membersField.getText();

                        // Use PreparedStatement to prevent SQL injection
                        String sql = "INSERT INTO mytable VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement preparedStatement = con.prepareStatement(sql);
                        preparedStatement.setString(1, no_of_days);
                        preparedStatement.setString(2, days);
                        preparedStatement.setString(3, Budget);
                        preparedStatement.setString(4, members);
                        preparedStatement.setString(5, calculate());
                        preparedStatement.executeUpdate();
                        JOptionPane.showMessageDialog(frame, "Enjoy your Trip :)");
                    } catch (SQLException ex) {
                        resultArea.setText(ex.getMessage());
                    }
                } catch (NoBudgetException ex) {
                    resultArea.setText(ex.getMessage());
                }
            }
        });

        frame.add(panel);
        // frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        // frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Open in full screen
        // frame.setUndecorated(true); // Remove window decorations
        frame.setVisible(true);
    }

    public String calculate() throws NoBudgetException {
        String choiceString = (String) choiceComboBox.getSelectedItem();
        int choice = Integer.parseInt(choiceString.split(" ")[0]);
        String days = daysField.getText().toLowerCase();
        int budget = Integer.parseInt(budgetField.getText());
        String location = locationField.getText().toLowerCase();
        int numberOfMembers = Integer.parseInt(membersField.getText());

        if (choice < 1 || choice > 3) {
            throw new IllegalArgumentException("Invalid choice");
        }

        String placeToVisit = "";
        int totalAmount = 0;
        switch (choice) {
            case 1:
                if (budget < 1000 || budget > 2000) {
                    throw new NoBudgetException();
                }

                if (days.equals("sunday")) {
                    placeToVisit = "Bhogatha Waterfalls";
                    totalAmount = budget * numberOfMembers;
                } else if (days.equals("saturday")) {
                    placeToVisit = "Pakal";
                    totalAmount = budget * numberOfMembers;
                } else if (days.equals("monday")) {
                    placeToVisit = "Bhimneni Waterfalls";
                    totalAmount = budget * numberOfMembers;
                } else {
                    throw new IllegalArgumentException("Invalid day input");
                }
                break;

            case 2:
                String[] dayArray = days.split(" ");
                if (dayArray.length != 2) {
                    throw new IllegalArgumentException("Invalid days input");
                }
                if ((dayArray[0].equals("saturday") && dayArray[1].equals("sunday")) ||
                        (dayArray[1].equals("saturday") && dayArray[0].equals("sunday"))) {
                    if (budget >= 3000 && budget <= 5000) {
                        placeToVisit = "vizag";
                        totalAmount = budget * numberOfMembers;
                    } else {
                        throw new NoBudgetException();
                    }
                } else if ((dayArray[0].equals("sunday") && dayArray[1].equals("monday")) ||
                        (dayArray[1].equals("sunday") && dayArray[0].equals("monday"))) {
                    if (budget >= 3000 && budget <= 5000) { // Use && for logical AND
                        placeToVisit = "NagarjunaSagardam";
                        totalAmount = budget * numberOfMembers;
                    } else {
                        throw new NoBudgetException();
                    }
                } else {
                    throw new IllegalArgumentException("Invalid days input");
                }
                break;

            case 3:
                String[] dayArray3 = days.split(" ");
                if (dayArray3.length != 3) {
                    throw new IllegalArgumentException("Invalid days input");
                }
                if ((dayArray3[0].equals("saturday") || dayArray3[0].equals("sunday") || dayArray3[0].equals("monday"))
                        &&
                        (dayArray3[1].equals("saturday") || dayArray3[1].equals("sunday")
                                || dayArray3[1].equals("monday"))
                        &&
                        (dayArray3[2].equals("saturday") || dayArray3[2].equals("sunday")
                                || dayArray3[2].equals("monday"))) {
                    if (budget >= 4000 && budget <= 7000) {
                        placeToVisit = "Ooty, Shirdi, or Tirupati";
                        totalAmount = budget * numberOfMembers;
                    } else {
                        throw new NoBudgetException();
                    }
                } else {
                    throw new IllegalArgumentException("Invalid days input");
                }
                break;

            default:
                throw new IllegalArgumentException("Invalid choice");
        }

        resultArea.setText("Places to visit: " + placeToVisit + "\nTotal amount: " + totalAmount
                + "\nEnjoy your Trip :) Visit our Page Again");

        // Update the displayed image based on the destination
        if (placeToVisit.equals("Bhogatha Waterfalls")) {
            imageLabel.setIcon(bhogathaImage);
        } else if (placeToVisit.equals("Pakal")) {
            imageLabel.setIcon(pakalImage);
        } else if (placeToVisit.equals("Bhimneni Waterfalls")) {
            imageLabel.setIcon(BhimneniImage);
        } else if (placeToVisit.equals("vizag")) {
            imageLabel.setIcon(vizagImage);
        } else if (placeToVisit.equals("NagarjunaSagardam")) {
            imageLabel.setIcon(NagarjunaSagardamImage);
        } else if (placeToVisit.equals("Shirdi")) {
            imageLabel.setIcon(ShirdiImage);
        }

        return placeToVisit;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TouristGUI());
    }
}