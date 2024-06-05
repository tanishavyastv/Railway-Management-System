import java.awt.*;
import javax.swing.*;

public class dbTrains4 extends JFrame {
    private JLabel title;
    private JLabel firstAC;
    private JFormattedTextField firstACField;
    private JLabel secondAC;
    private JFormattedTextField secondACField;
    private JLabel thirdAC;
    private JFormattedTextField thirdACField;
    private JLabel sleeperClass;
    private JFormattedTextField sleeperClassField;
    private JButton previous;
    private JButton submit;
    private JPanel DBPanel;

    public static int firstACTicketPrice;
    public static int secondACTicketPrice;
    public static int thirdACTicketPrice;
    public static int sleeperClassTicketPrice;

    public dbTrains4() {
        setTitle("Database of a train Window");

        // Title
        title = new JLabel("Price For Each Ticket Type");
        title.setFont(new Font("Arial", Font.BOLD, 30));

        // types of tickets price
        // first
        firstAC = new JLabel("First AC Ticket Price");
        firstAC.setFont(new Font("Cambria", Font.PLAIN, 20));

        firstACField = new JFormattedTextField();
        firstACField.setFont(new Font("century", Font.PLAIN, 20));
        firstACField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        firstACField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String text = ((JFormattedTextField) input).getText();
                if (text.isEmpty()) {
                    return true;
                }
                try {
                    Integer.parseInt(text);
                    return true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(dbTrains4.this, "Please enter a valid integer.", "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        });

        // second
        secondAC = new JLabel("Second AC Ticket Price");
        secondAC.setFont(firstAC.getFont());

        secondACField = new JFormattedTextField();
        secondACField.setFont(firstACField.getFont());
        secondACField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        secondACField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String text = ((JFormattedTextField) input).getText();
                if (text.isEmpty()) {
                    return true;
                }
                try {
                    Integer.parseInt(text);
                    return true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(dbTrains4.this, "Please enter a valid integer.", "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        });

        // third
        thirdAC = new JLabel("Third AC Ticket Price");
        thirdAC.setFont(firstAC.getFont());

        thirdACField = new JFormattedTextField();
        thirdACField.setFont(firstACField.getFont());
        thirdACField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        thirdACField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String text = ((JFormattedTextField) input).getText();
                if (text.isEmpty()) {
                    return true;
                }
                try {
                    Integer.parseInt(text);
                    return true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(dbTrains4.this, "Please enter a valid integer.", "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        });

        // sleeper
        sleeperClass = new JLabel("Sleeper class Ticket Price");
        sleeperClass.setFont(firstAC.getFont());

        sleeperClassField = new JFormattedTextField();
        sleeperClassField.setFont(firstACField.getFont());
        sleeperClassField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        sleeperClassField.setInputVerifier(new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                String text = ((JFormattedTextField) input).getText();
                if (text.isEmpty()) {
                    return true;
                }
                try {
                    Integer.parseInt(text);
                    return true;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(dbTrains4.this, "Please enter a valid integer.", "Invalid Input",
                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        });

        // previous button
        previous = new JButton("\u2190 Previous") {
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
        previous.addActionListener(e -> {
            dispose();
            dbTrains3 db = new dbTrains3();
            db.setVisible(true);
        });
        previous.setFont(firstAC.getFont());
        previous.setBackground(new Color(255, 105, 97));
        previous.setForeground(Color.WHITE);
        previous.setFocusable(false);
        previous.setBorder(new RoundBorder(20));
        previous.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        previous.setContentAreaFilled(false);

        // submit button
        submit = new JButton("Submit") {
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
        submit.addActionListener(e -> {
            if (allFieldsFilled()) {
                new dbTrainsSubmit();
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Incomplete Information",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        submit.setFont(firstAC.getFont());
        submit.setBackground(new Color(0, 204, 0));
        submit.setForeground(Color.WHITE);
        submit.setFocusable(false);
        submit.setBorder(new RoundBorder(20));
        submit.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        submit.setContentAreaFilled(false);

        // Main panel
        DBPanel = new JPanel();
        DBPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.anchor = GridBagConstraints.CENTER;
        DBPanel.add(title, gbc);

        gbc.gridy++;
        addLabelAndField(firstAC, firstACField, DBPanel, gbc);

        gbc.gridy++;
        addLabelAndField(secondAC, secondACField, DBPanel, gbc);

        gbc.gridy++;
        addLabelAndField(thirdAC, thirdACField, DBPanel, gbc);

        gbc.gridy++;
        addLabelAndField(sleeperClass, sleeperClassField, DBPanel, gbc);

        // Add buttons to the panel
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        DBPanel.add(previous, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.EAST;
        DBPanel.add(submit, gbc);

        DBPanel.setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        getContentPane().add(DBPanel);
        setVisible(true);
    }

    private void addLabelAndField(Component label, Component field, Container container, GridBagConstraints gbc) {
        gbc.gridx = 0; // Label on the left
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        container.add(label, gbc);

        gbc.gridx = 1; // Field on the right
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        container.add(field, gbc);
    }

    private boolean allFieldsFilled() {
        firstACTicketPrice = Integer.parseInt(firstACField.getText()); 
        secondACTicketPrice = Integer.parseInt(secondACField.getText());
        thirdACTicketPrice = Integer.parseInt(thirdACField.getText());
        sleeperClassTicketPrice = Integer.parseInt(sleeperClassField.getText());
        return firstACTicketPrice > 0 && secondACTicketPrice > 0 && thirdACTicketPrice > 0
                && sleeperClassTicketPrice > 0;
    }
}