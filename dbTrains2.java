import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class dbTrains2 extends JFrame {
    private JLabel trainName;
    private JTextField trainNameField;
    private JLabel dojLabel;
    private JFormattedTextField dojField;
    private JLabel boarding;
    private JTextField boardingField;
    private JLabel destination;
    private JTextField destinationField;
    JButton previous;
    private JButton next;
    private JPanel dbPanel;

    public static String trainNameValue;
    public static String dojValue;
    public static String boardingValue;
    public static String destinationValue;

    public dbTrains2() {
        setTitle("Database of a train Window");

        // train name and field
        trainName = new JLabel("Train name for Train Number " + dbTrains1.trainNumber);
        trainName.setFont(new Font("Cambria", Font.PLAIN, 20));

        trainNameField = new JTextField();
        trainNameField.setFont(new Font("century", Font.PLAIN, 20));
        trainNameField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // date of journey
        dojLabel = new JLabel("Date of Train's journey");
        dojLabel.setFont(trainName.getFont());

        MaskFormatter dateFormat = null;
        try {
            dateFormat = new MaskFormatter("####/##/##");
            dateFormat.setPlaceholderCharacter('0');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dojField = new JFormattedTextField(dateFormat);
        dojField.setFont(trainNameField.getFont());
        dojField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // boarding point
        boarding = new JLabel("Boarding Point");
        boarding.setFont(trainName.getFont());

        boardingField = new JTextField();
        boardingField.setFont(trainNameField.getFont());
        boardingField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // destination point
        destination = new JLabel("Destination Point");
        destination.setFont(trainName.getFont());

        destinationField = new JTextField();
        destinationField.setFont(trainNameField.getFont());
        destinationField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

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
            dbTrains1 db = new dbTrains1();
            db.setVisible(true);
        });
        previous.setFont(trainName.getFont());
        previous.setBackground(new Color(255, 105, 97));
        previous.setForeground(Color.WHITE);
        previous.setFocusable(false);
        previous.setBorder(new RoundBorder(20));
        previous.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        previous.setContentAreaFilled(false);

        // next button
        next = new JButton("Next \u2192") {
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
        next.addActionListener(e -> {
            if (allFieldsFilled()) {
                new dbTrains3();
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Missing Information",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        next.setFont(trainName.getFont());
        next.setBackground(new Color(0, 204, 0));
        next.setForeground(Color.WHITE);
        next.setFocusable(false);
        next.setBorder(new RoundBorder(20));
        next.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        next.setContentAreaFilled(false);

        // Main panel
        dbPanel = new JPanel();
        dbPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        addLabelAndField(trainName, trainNameField, dbPanel, gbc);
        gbc.gridy++;
        addLabelAndField(dojLabel, dojField, dbPanel, gbc);
        gbc.gridy++;
        addLabelAndField(boarding, boardingField, dbPanel, gbc);
        gbc.gridy++;
        addLabelAndField(destination, destinationField, dbPanel, gbc);

        // Add previous button on the left
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        dbPanel.add(previous, gbc);

        // Add next button on the right
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        dbPanel.add(next, gbc);

        dbPanel.setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        getContentPane().add(dbPanel);
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
        trainNameValue = trainNameField.getText();
        dojValue = dojField.getText();
        boardingValue = boardingField.getText();
        destinationValue = destinationField.getText();

        return !trainNameValue.isEmpty() && !dojValue.isEmpty() && !boardingValue.isEmpty() && !destinationValue.isEmpty();
    }
}
