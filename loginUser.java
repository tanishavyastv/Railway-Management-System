import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class loginUser extends JFrame {
    JPanel mainPanel;
    JLabel login;
    static JTextField userField;
    JLabel username;
    JLabel passwordLabel;
    JPasswordField passwordField;
    JButton loginButton;
    JButton newUser;

    public loginUser() {
        setTitle("User login Window");

        // Title
        login = new JLabel("SIGN IN!");
        login.setFont(new Font("Arial", Font.BOLD, 40));
        login.setHorizontalAlignment(JLabel.CENTER);

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

        // Login button
        loginButton = new JButton("Login") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(102, 178, 255).darker());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        loginButton.addActionListener(e -> {
            String username = userField.getText().trim();
            char[] pass = passwordField.getPassword();
            if (username.isEmpty()) {
                showErrorMessage("Username cannot be empty!");
                return;
            } else if (pass.length == 0) {
                showErrorMessage("Password cannot be empty!");
                return;
            }
            boolean isValidUser = validateUser(username, new String(pass));
            if (!isValidUser) {
                showErrorMessage("Invalid username or password.");
                return;
            }
            dispose();
        });
        loginButton.setActionCommand("login");
        loginButton.setFont(username.getFont());
        loginButton.setBackground(new Color(102, 178, 255));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusable(false);
        loginButton.setBorder(new RoundBorder(20));
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        loginButton.setContentAreaFilled(false);

        // new user button
        newUser = new JButton("New User?") {
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
        newUser.setBackground(new Color(255, 102, 178));
        newUser.setForeground(Color.WHITE);
        newUser.setFocusable(false);
        newUser.setBorder(new RoundBorder(20));
        newUser.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        newUser.setContentAreaFilled(false);
        newUser.setFont(username.getFont());
        newUser.addActionListener(e -> {
            new SignUp();
            dispose();
        });

        // Main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(login, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(username, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(userField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(passwordField, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(newUser, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(loginButton, gbc);

        mainPanel.setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        getContentPane().add(mainPanel);
        setVisible(true);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void handleSuccessfulLogin() {
        JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose();
        new userMode();
    }

    private boolean validateUser(String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/Railway";
            String user = "root";
            String pass = "1234";
            Connection c = DriverManager.getConnection(url, user, pass);
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("select uname, pass from user");
            while (r.next()) {
                if (username.equals(r.getString("uname")) && password.equals(r.getString("pass"))) {
                    handleSuccessfulLogin();
                    return true;
                }
            }
            r.close();
            s.close();
            c.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
