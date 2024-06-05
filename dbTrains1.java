import javax.swing.*;
import java.awt.*;

public class dbTrains1 extends JFrame {
    public static String trainNumber;
    JPanel trainNumPanel;
    private JLabel tNumTitle;
    private JLabel trainNum;
    private JTextField trainNumField;
    private JButton trainNumValidate;
    private JButton goBack;

    public dbTrains1() {
        setTitle("Database of a train Window");

        // Title
        tNumTitle = new JLabel("DATABASE OF A TRAIN");
        tNumTitle.setFont(new Font("Arial", Font.BOLD, 40));

        // train number and field
        trainNum = new JLabel("Enter a Valid Train Number");
        trainNum.setFont(new Font("Cambria", Font.PLAIN, 20));

        trainNumField = new JTextField();
        trainNumField.setFont(new Font("century", Font.PLAIN, 20));
        trainNumField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        trainNumField.setHorizontalAlignment(JTextField.CENTER);

        // previous button
        goBack = new JButton("Go Back") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(255, 105, 97).darker());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        goBack.addActionListener(e -> {
            dispose();
            AdministratorWindow adminWindow = new AdministratorWindow();
            adminWindow.adminMenu();
        });
        goBack.setFont(trainNum.getFont());
        goBack.setBackground(new Color(255, 105, 97));
        goBack.setForeground(Color.WHITE);
        goBack.setFocusable(false);
        goBack.setBorder(new RoundBorder(20));
        goBack.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        goBack.setContentAreaFilled(false);

        // validate button
        trainNumValidate = new JButton("Validate") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(0, 204, 0).darker());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        trainNumValidate.addActionListener(e -> validateInput());
        trainNumValidate.setFont(trainNum.getFont());
        trainNumValidate.setBackground(new Color(0, 204, 0));
        trainNumValidate.setForeground(Color.WHITE);
        trainNumValidate.setFocusable(false);
        trainNumValidate.setBorder(new RoundBorder(20));
        trainNumValidate.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        trainNumValidate.setContentAreaFilled(false);

        // Main panel
        trainNumPanel = new JPanel();
        trainNumPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        trainNumPanel.add(tNumTitle, gbc);

        gbc.gridy++;
        trainNumPanel.add(trainNum, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        trainNumPanel.add(trainNumField, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        trainNumPanel.add(trainNumValidate, gbc);

        gbc.gridy++;
        trainNumPanel.add(goBack, gbc);

        trainNumPanel.setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        getContentPane().add(trainNumPanel);
        setVisible(true);
    }

    private void validateInput() {
        trainNumber = trainNumField.getText();
        if (trainNumber.isEmpty() || !trainNumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid train number.", "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            new dbTrains2();
            setVisible(false);
        }
    }
}