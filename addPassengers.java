import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class addPassengers extends JFrame {
    public static String ticketNumber;
    JLabel title;
    JLabel nameLabel;
    JTextField name;
    JLabel genderLabel;
    JComboBox<String> gender;
    JLabel ageLabel;
    JSpinner age;
    JButton addButton;
    DefaultTableModel tableModel;
    JTable passengerTable;  
    JButton showTicket;
    JScrollPane tableScrollPane;
    JPanel mainPanel;
    private JButton saveInfo;

    public addPassengers() {
        setTitle("Passengers Window");

        // title
        title = new JLabel("ADD PASSENGERS");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setHorizontalAlignment(JLabel.CENTER);

        // name
        nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Cambria", Font.PLAIN, 20));

        name = new JTextField();
        name.setFont(new Font("century", Font.PLAIN, 20));
        name.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // gender
        genderLabel = new JLabel("Gender");
        genderLabel.setFont(nameLabel.getFont());

        gender = new JComboBox<>(new String[] { "Male", "Female", "Other" });
        gender.setFont(new Font("century", Font.PLAIN, 15));
        gender.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        // age
        ageLabel = new JLabel("Age");
        ageLabel.setFont(nameLabel.getFont());

        age = new JSpinner(new SpinnerNumberModel(18, 1, 120, 1));
        age.setFont(gender.getFont());
        age.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

        // add button
        addButton = new JButton("ADD") {
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
        addButton.setFont(nameLabel.getFont());
        addButton.addActionListener(e -> addPassenger());
        addButton.setBackground(new Color(102, 178, 255));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusable(false);
        addButton.setBorder(new RoundBorder(20));
        addButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        addButton.setContentAreaFilled(false);

        // table
        tableModel = new DefaultTableModel();
        passengerTable = new JTable(tableModel);
        passengerTable.setFont(new Font("century", Font.PLAIN, 17));
        passengerTable.setRowHeight(30);
        // table column names
        tableModel.addColumn("SNo.");
        tableModel.addColumn("Name");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Age");

        // table panel
        tableScrollPane = new JScrollPane(passengerTable);
        tableScrollPane.getViewport().setBackground(Color.PINK);
        tableScrollPane.setPreferredSize(new Dimension(500, 200));
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        passengerTable.getTableHeader().setBackground(new Color(96, 96, 196));
        passengerTable.getTableHeader().setForeground(Color.WHITE);
        passengerTable.getTableHeader().setFont(new Font("Cambria", Font.BOLD, 19));

        // save info
        saveInfo = new JButton("Save Information") {
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
        saveInfo.setFont(nameLabel.getFont());
        saveInfo.setBackground(new Color(224, 83, 123));
        saveInfo.setForeground(Color.WHITE);
        saveInfo.setFocusable(false);
        saveInfo.setBorder(new RoundBorder(20));
        saveInfo.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        saveInfo.setContentAreaFilled(false);
        saveInfo.addActionListener(e -> saveInfo());

        // book button
        showTicket = new JButton("Show Ticket") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(0, 153, 153).darker());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        showTicket.setFont(nameLabel.getFont());
        showTicket.setBackground(new Color(0, 153, 153));
        showTicket.setForeground(Color.WHITE);
        showTicket.setFocusable(false);
        showTicket.setBorder(new RoundBorder(20));
        showTicket.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        showTicket.setContentAreaFilled(false);
        showTicket.addActionListener(e -> {
            new DisplayTicket();
            dispose();
        });

        // Main panel
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(name, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(genderLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(gender, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(ageLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(age, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.CENTER;
        mainPanel.add(addButton, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(tableScrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(saveInfo, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(showTicket, gbc);

        mainPanel.setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 650);
        setLocationRelativeTo(null);
        getContentPane().add(mainPanel);
        setVisible(true);
    }

    int passengerCount = 1;

    private void addPassenger() {
        String passengerName = name.getText();
        String passengerGender = (String) gender.getSelectedItem();
        int passengerAge = (int) age.getValue();

        Object[] rowData = { passengerCount++, passengerName, passengerGender, passengerAge };
        tableModel.addRow(rowData);

        name.setText("");
        gender.setSelectedIndex(0);
        age.setValue(18);
    }

    private int maxSeatNumber;

    private void saveInfo() {
        try {
            String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/Railway";
            String user = "root";
            String pass = "1234";
            Class.forName(mysqlJDBCDriver);
            Connection c = DriverManager.getConnection(url, user, pass);
            String sql = "INSERT INTO chart (ticketID, tnum, pnr, name, age, gender, seatno, coach, dot) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = c.prepareStatement(sql);

            // random ticket number generator
            UUID uuid = UUID.randomUUID();
            ticketNumber = uuid.toString().replaceAll("-", "");
            ticketNumber = ticketNumber.substring(0, 10);

            // random seat number generator
            int minSeatNumber = 1;
            PreparedStatement pst = c.prepareStatement("Select seats from train where tnum = ?");
            pst.setInt(1, userMode.tnum);
            ResultSet p = pst.executeQuery();
            if (p.next()) {
                maxSeatNumber = p.getInt("seats");
            }
            int seatNumber = generateSeatNumber(minSeatNumber, maxSeatNumber);

            for (int row = 0; row < tableModel.getRowCount(); row++) {
                String name = (String) tableModel.getValueAt(row, 1);
                String gender = (String) tableModel.getValueAt(row, 2);
                int age = (int) tableModel.getValueAt(row, 3);

                // random pnr generator
                String pnr = generatePNR();
                // seat number
                seatNumber++;

                ps.setString(1, ticketNumber);
                ps.setInt(2, userMode.tnum);
                ps.setString(3, pnr);
                ps.setString(4, name);
                ps.setInt(5, age);
                ps.setString(6, gender);
                ps.setInt(7, seatNumber);
                ps.setString(8, userMode.coach);
                ps.setDate(9, userMode.doj);

                ps.executeUpdate();
            }
            pst.close();
            ps.close();
            c.close();

            JOptionPane.showMessageDialog(this, "Data saved successfully to the database.", "Success",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error occurred while saving data to the database.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generatePNR() {
        int randomNum = ThreadLocalRandom.current().nextInt(100000, 999999 + 1);
        return "PNR" + randomNum;
    }

    private int generateSeatNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
