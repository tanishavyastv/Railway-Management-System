import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

import java.awt.*;
import java.text.NumberFormat;
import java.text.ParseException;

public class dbTrains3 extends JFrame {
    private JLabel departureTime;
    private JFormattedTextField departureTimeField;
    private JLabel arrivalTime;
    private JFormattedTextField arrivalTimeField;
    private JButton previous;
    private JButton next;
    private JPanel DBPanel;
    private Container numOfSeats;
    private JFormattedTextField numOfSeatsField;

    public static String arrivalTimeValue;
    public static String departureTimeValue;
    public static int numOfSeatsValue;

    public dbTrains3() {
        setTitle("Database of a train Window");

        // Departure time
        departureTime = new JLabel("Departure time of train's journey");
        departureTime.setFont(new Font("Cambria", Font.PLAIN, 20));

        MaskFormatter departureFormat = null;
        try {
            departureFormat = new MaskFormatter("##:##:##");
            departureFormat.setPlaceholderCharacter('0');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        departureTimeField = new JFormattedTextField(departureFormat);
        departureTimeField.setFont(new Font("century", Font.PLAIN, 20));
        departureTimeField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // arrival time
        arrivalTime = new JLabel("Arrival time of train's journey");
        arrivalTime.setFont(departureTime.getFont());

        MaskFormatter arrivalFormat = null;
        try {
            arrivalFormat = new MaskFormatter("##:##:##");
            arrivalFormat.setPlaceholderCharacter('0');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        arrivalTimeField = new JFormattedTextField(arrivalFormat);
        arrivalTimeField.setFont(departureTimeField.getFont());
        arrivalTimeField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // number of seats
        numOfSeats = new JLabel("Total Number of Seats in the Train");
        numOfSeats.setFont(departureTime.getFont());

        numOfSeatsField = new JFormattedTextField(createNumberFormatter());
        numOfSeatsField.setFont(departureTimeField.getFont());
        numOfSeatsField.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        numOfSeatsField.setInputVerifier(new InputVerifier() {
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
                    JOptionPane.showMessageDialog(dbTrains3.this, "Please enter a valid integer.", "Invalid Input",
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
            dbTrains2 db = new dbTrains2();
            db.setVisible(true);
        });
        previous.setFont(departureTime.getFont());
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
                new dbTrains4();
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Missing Information",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        next.setFont(departureTime.getFont());
        next.setBackground(new Color(0, 204, 0));
        next.setForeground(Color.WHITE);
        next.setFocusable(false);
        next.setBorder(new RoundBorder(20));
        next.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        next.setContentAreaFilled(false);

        // Main panel
        DBPanel = new JPanel();
        DBPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridy++;
        addLabelAndField(arrivalTime, arrivalTimeField, DBPanel, gbc);

        gbc.gridy++;
        addLabelAndField(departureTime, departureTimeField, DBPanel, gbc);
        
        gbc.gridy++;
        addLabelAndField(numOfSeats, numOfSeatsField, DBPanel, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        DBPanel.add(previous, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        DBPanel.add(next, gbc);

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

    private NumberFormatter createNumberFormatter() {
        NumberFormat format = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setAllowsInvalid(false);
        return formatter;
    }

    private boolean allFieldsFilled() {
        departureTimeValue = departureTimeField.getText();
        arrivalTimeValue = arrivalTimeField.getText();
        numOfSeatsValue = Integer.parseInt(numOfSeatsField.getText());
        return !departureTimeValue.isEmpty() && !arrivalTimeValue.isEmpty() && numOfSeatsValue > 0;
    }
}