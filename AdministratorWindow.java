import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class AdministratorWindow extends JFrame {
    JPanel mainPanel;
    JLabel passwordLabel;
    JPasswordField passwordField;
    JButton loginButton;
    private JLabel login;
    private JPanel adminMenuPanel;
    private JLabel welcome;
    private JButton dbTrains;
    private JButton dbDisplay;
    private JButton chartTrain;
    private JButton displayUsers;
    private JButton MainMenu;

    public AdministratorWindow() {
        setTitle("Administrator Window");

        // Title
        login = new JLabel("ADMINISTRATOR ACCESS!");
        login.setFont(new Font("Arial", Font.BOLD, 40));

        // Password label and field
        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Cambria", Font.PLAIN, 20));

        passwordField = new JPasswordField(10);
        passwordField.setEchoChar('*');
        passwordField.setFont(new Font("century", Font.PLAIN, 20));
        passwordField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        passwordField.setHorizontalAlignment(JPasswordField.CENTER);

        // Login button
        loginButton = new JButton("Login") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(224, 83, 123).darker());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        loginButton.addActionListener(e -> {
            char[] password = passwordField.getPassword();
            if (checkPassword(password)) {
                adminMenu();
            } else {
                JOptionPane.showMessageDialog(AdministratorWindow.this, "Wrong password, please try again.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
            passwordField.setText("");
        });
        loginButton.setActionCommand("login");
        loginButton.setFont(passwordLabel.getFont());
        loginButton.setBackground(new Color(224, 83, 123));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusable(false);
        loginButton.setBorder(new RoundBorder(20));
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        loginButton.setContentAreaFilled(false);

        // Main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);

        mainPanel.add(login, gbc);

        gbc.gridy++;
        mainPanel.add(passwordLabel, gbc);

        gbc.gridy++;
        mainPanel.add(passwordField, gbc);

        gbc.gridy++;
        mainPanel.add(loginButton, gbc);

        mainPanel.setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        getContentPane().add(mainPanel);
        setVisible(true);
    }

    private boolean checkPassword(char[] password) {
        return Arrays.equals(password, "tan".toCharArray());
    }

    public void adminMenu() {
        remove(mainPanel);
        setTitle("Administrator Menu Window");

        // welcome admin
        welcome = new JLabel("WELCOME ADMINISTRATOR!");
        welcome.setFont(login.getFont());

        // database of trains
        dbTrains = new JButton("Create Detail Database of Trains") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(154, 132, 240).darker());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        dbTrains.addActionListener((e) -> {
            new dbTrains1();
            setVisible(false);
        });
        dbTrains.setFont(passwordLabel.getFont());
        dbTrains.setBackground(new Color(154, 132, 240));
        dbTrains.setForeground(Color.WHITE);
        dbTrains.setFocusable(false);
        dbTrains.setBorder(new RoundBorder(20));
        dbTrains.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        dbTrains.setContentAreaFilled(false);

        // display database of trains
        dbDisplay = new JButton("Display all the Database of Trains") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(72, 180, 180).darker());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        dbDisplay.addActionListener((e) -> {
            new dbDisplay();
            setVisible(false);
        });
        dbDisplay.setFont(passwordLabel.getFont());
        dbDisplay.setBackground(new Color(72, 180, 180));
        dbDisplay.setForeground(Color.WHITE);
        dbDisplay.setFocusable(false);
        dbDisplay.setBorder(new RoundBorder(20));
        dbDisplay.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        dbDisplay.setContentAreaFilled(false);

        // display chart of a train
        chartTrain = new JButton("Display Chart of a Train") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(154, 132, 240).darker());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        chartTrain.addActionListener((e) -> {
            new chartTrain();
            setVisible(false);
        });
        chartTrain.setFont(passwordLabel.getFont());
        chartTrain.setBackground(new Color(154, 132, 240));
        chartTrain.setForeground(Color.WHITE);
        chartTrain.setFocusable(false);
        chartTrain.setBorder(new RoundBorder(20));
        chartTrain.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        chartTrain.setContentAreaFilled(false);

        // display all users
        displayUsers = new JButton("Display all Users") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(72, 180, 180).darker());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        displayUsers.addActionListener((e) -> {
            new displayUsers();
            setVisible(false);
        });
        displayUsers.setFont(passwordLabel.getFont());
        displayUsers.setBackground(new Color(72, 180, 180));
        displayUsers.setForeground(Color.WHITE);
        displayUsers.setFocusable(false);
        displayUsers.setBorder(new RoundBorder(20));
        displayUsers.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        displayUsers.setContentAreaFilled(false);

        // main menu
        MainMenu = new JButton("Return to Main Menu") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(154, 132, 240).darker());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        MainMenu.addActionListener(e -> {
            dispose();
            Railway mw = new Railway();
            mw.setVisible(true);
        });
        MainMenu.setFont(passwordLabel.getFont());
        MainMenu.setBackground(new Color(154, 132, 240));
        MainMenu.setForeground(Color.WHITE);
        MainMenu.setFocusable(false);
        MainMenu.setBorder(new RoundBorder(20));
        MainMenu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        MainMenu.setContentAreaFilled(false);

        // Admin Menu panel
        adminMenuPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 10, 10);
        adminMenuPanel.add(welcome, gbc);
        gbc.gridy++;
        adminMenuPanel.add(dbTrains, gbc);
        gbc.gridy++;
        adminMenuPanel.add(dbDisplay, gbc);
        gbc.gridy++;
        adminMenuPanel.add(chartTrain, gbc);
        gbc.gridy++;
        adminMenuPanel.add(displayUsers, gbc);
        gbc.gridy++;
        adminMenuPanel.add(MainMenu, gbc);

        adminMenuPanel.setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        getContentPane().add(adminMenuPanel);
        setVisible(true);
    }

}