import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class SignUp extends JFrame {
    private JLabel title;
    private JLabel username;
    private JTextField userField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel confirmPasswordLabel;
    private JPasswordField confirmPasswordField;
    private JLabel age;
    private JSpinner ageField;
    private JLabel gender;
    private JComboBox<String> genderField;
    private JButton loginUser;
    private JButton SignUp;
    private JPanel mainPanel;

    public SignUp() {
        setTitle("Sign Up Window");

        // Title
        title = new JLabel("CREATE A USER ACCOUNT!");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setHorizontalAlignment(JLabel.CENTER);

        // Username label and field
        username = new JLabel("Username");
        username.setFont(new Font("Cambria", Font.PLAIN, 20));

        userField = new JTextField(24);
        userField.setFont(new Font("century", Font.PLAIN, 20));
        userField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Password label and field
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(username.getFont());

        passwordField = new JPasswordField(10);
        passwordField.setEchoChar('*');
        passwordField.setFont(userField.getFont());
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Password label and field
        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setFont(username.getFont());

        confirmPasswordField = new JPasswordField(10);
        confirmPasswordField.setEchoChar('*');
        confirmPasswordField.setFont(userField.getFont());
        confirmPasswordField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // age
        age = new JLabel("Age");
        age.setFont(username.getFont());

        ageField = new JSpinner(new SpinnerNumberModel(18, 1, 100, 1));
        ageField.setFont(userField.getFont());
        ageField.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 10));

        // gender
        gender = new JLabel("Gender");
        gender.setFont(username.getFont());

        genderField = new JComboBox<>(new String[] { "Male", "Female", "Other" });
        genderField.setFont(userField.getFont());

        // existing user
        loginUser = new JButton("Existing user?") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(255, 102, 178).darker());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        loginUser.addActionListener((e) -> {
            new loginUser();
            setVisible(false);
        });
        loginUser.setFont(username.getFont());
        loginUser.setBackground(new Color(255, 102, 178));
        loginUser.setForeground(Color.WHITE);
        loginUser.setFocusable(false);
        loginUser.setBorder(new RoundBorder(20));
        loginUser.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        loginUser.setContentAreaFilled(false);

        // signup
        SignUp = new JButton("Register") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(204, 153, 255).darker());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        SignUp.addActionListener(e -> storeUser());
        SignUp.setFont(username.getFont());
        SignUp.setBackground(new Color(204, 153, 255));
        SignUp.setForeground(Color.WHITE);
        SignUp.setFocusable(false);
        SignUp.setBorder(new RoundBorder(20));
        SignUp.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        SignUp.setContentAreaFilled(false);

        // Main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);

        mainPanel.add(title, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(username, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(userField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(passwordLabel, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(passwordField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(confirmPasswordLabel, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(confirmPasswordField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(age, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(ageField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(gender, gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(genderField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(loginUser, gbc);
        gbc.gridx++;
        mainPanel.add(SignUp, gbc);

        mainPanel.setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        getContentPane().add(mainPanel);
        setVisible(true);
    }

    @SuppressWarnings("deprecation")
    private void storeUser() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Railway";
            String user = "root";
            String pass = "1234";
            Connection c = DriverManager.getConnection(url, user, pass);
            PreparedStatement ps = c.prepareStatement("INSERT INTO user (uname, pass, age, g) VALUES (?, ?, ?, ?)");

            if (!userField.getText().isEmpty() &&
                    !passwordField.getText().isEmpty() &&
                    !confirmPasswordField.getText().isEmpty() &&
                    passwordField.getText().equals(confirmPasswordField.getText())) {

                ps.setString(1, userField.getText());
                ps.setString(2, passwordField.getText());
                ps.setInt(3, (int) ageField.getValue());
                ps.setString(4, (String) genderField.getSelectedItem());
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "User Added Successfully.", "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                new loginUser();
                dispose();

            } else {
                JOptionPane.showMessageDialog(this, "Password does not match.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while adding user.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}