import java.awt.*;
import javax.swing.*;

public class UserWindow extends JFrame {
    JPanel mainPanel;
    JLabel titleLabel;
    JButton loginUser;
    JButton signUp;
    JButton mainMenu;
    JButton exit;

    public UserWindow() {
        setTitle("User Window");
        // title
        titleLabel = new JLabel("User Login & Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        // login
        loginUser = new JButton("Login as a user") {
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
        loginUser.addActionListener((e) -> {
            new loginUser();
            setVisible(false);
        });
        loginUser.setFont(new Font("Cambria", Font.PLAIN, 20));
        loginUser.setBackground(new Color(102, 178, 255));
        loginUser.setForeground(Color.WHITE);
        loginUser.setFocusable(false);
        loginUser.setBorder(new RoundBorder(20));
        loginUser.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        loginUser.setContentAreaFilled(false);

        // signup
        signUp = new JButton("Sign Up for a new account") {
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
        signUp.addActionListener((e) -> {
            new SignUp();
            setVisible(false);
        });
        signUp.setFont(loginUser.getFont());
        signUp.setBackground(new Color(255, 102, 178));
        signUp.setForeground(Color.WHITE);
        signUp.setFocusable(false);
        signUp.setBorder(new RoundBorder(20));
        signUp.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        signUp.setContentAreaFilled(false);

        // main menu
        mainMenu = new JButton("Go Back to Main Menu") {
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
        mainMenu.addActionListener(e -> {
            dispose();
            Railway mw = new Railway();
            mw.setVisible(true);
        });
        mainMenu.setFont(loginUser.getFont());
        mainMenu.setBackground(new Color(204, 153, 255));
        mainMenu.setForeground(Color.WHITE);
        mainMenu.setFocusable(false);
        mainMenu.setBorder(new RoundBorder(20));
        mainMenu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        mainMenu.setContentAreaFilled(false);

        // Main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(titleLabel, gbc);
        gbc.gridy++;
        mainPanel.add(loginUser, gbc);
        gbc.gridy++;
        mainPanel.add(signUp, gbc);
        gbc.gridy++;
        mainPanel.add(mainMenu, gbc);

        mainPanel.setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        getContentPane().add(mainPanel);
        setVisible(true);
    }
}
