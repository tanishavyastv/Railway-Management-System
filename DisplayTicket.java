import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DisplayTicket extends JFrame {
    JLabel title;
    JLabel bpLabel;
    JLabel bp;
    JLabel dpLabel;
    JLabel dp;
    JLabel dateAndTime;
    JLabel trainNoTrainNameLabel;
    JLabel trainNoTrainName;
    JLabel classLabel;
    JLabel classType;
    JLabel passengerDetailsLabel;
    JLabel paymentDetailsLabel;
    JLabel ticketFareLabel;
    JLabel totalFareLabel;
    JLabel ticketFare;
    JLabel totalFare;
    JButton cancelButton;
    JPanel mainPanel;
    JTable table;
    JScrollPane scrollPane;
    JButton mainMenu;
    JButton exit;
    JLabel ticketID;

    public DisplayTicket() {
        setTitle("Ticket Window");

        // title
        title = new JLabel("TICKET");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(new Color(220, 20, 60));

        // boarding point
        bpLabel = new JLabel("Boarding");
        bpLabel.setFont(new Font("Cambria", Font.PLAIN, 18));

        bp = new JLabel(userMode.BP);
        bp.setFont(new Font("Cambria", Font.PLAIN, 15));
        bp.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        bp.setOpaque(true);
        bp.setBackground(Color.PINK);

        // departure point
        dpLabel = new JLabel("Departure");
        dpLabel.setFont(bpLabel.getFont());

        dp = new JLabel(userMode.DP);
        dp.setFont(bp.getFont());
        dp.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        dp.setOpaque(true);
        dp.setBackground(Color.PINK);

        // ticket Number
        ticketID = new JLabel("Ticket ID: #" + addPassengers.ticketNumber);
        ticketID.setFont(bpLabel.getFont());

        // date and time
        dateAndTime = new JLabel("Date: " + userMode.doj + " | Time: " + userMode.time);
        dateAndTime.setFont(bpLabel.getFont());

        // train no and train name
        trainNoTrainNameLabel = new JLabel("Train No./Train Name");
        trainNoTrainNameLabel.setFont(bpLabel.getFont());

        trainNoTrainName = new JLabel(userMode.tname + "/" + userMode.tnum);
        trainNoTrainName.setFont(bp.getFont());
        trainNoTrainName.setForeground(new Color(0, 153, 153));

        // class and seat class
        classLabel = new JLabel("Class");
        classLabel.setFont(bpLabel.getFont());

        classType = new JLabel(userMode.coach);
        classType.setFont(bp.getFont());
        classType.setForeground(new Color(0, 153, 153));

        // passenger details
        passengerDetailsLabel = new JLabel("Passenger Details");
        passengerDetailsLabel.setFont(new Font("Cambria", Font.PLAIN, 23));

        try {
            String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/Railway";
            String user = "root";
            String pass = "1234";
            Class.forName(mysqlJDBCDriver);
            Connection c = DriverManager.getConnection(url, user, pass);
            PreparedStatement ps = c.prepareStatement("select * from chart where ticketID = ?");
            ps.setString(1, addPassengers.ticketNumber);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("PNR");
            model.addColumn("Name");
            model.addColumn("Age");
            model.addColumn("Gender");
            model.addColumn("Seat No");

            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getString("pnr"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getInt("seatno")
                });
            }

            table = new JTable(model);
            table.setFont(new Font("century", Font.PLAIN, 12));
            table.setRowHeight(30);
            table.setPreferredScrollableViewportSize(new Dimension(600, 100));
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table.getTableHeader().setBackground(new Color(255, 0, 0));
            table.getTableHeader().setForeground(Color.WHITE);
            table.getTableHeader().setFont(new Font("Cambria", Font.BOLD, 15));

            scrollPane = new JScrollPane(table);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            rs.close();
            ps.close();
            c.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        // payment details
        paymentDetailsLabel = new JLabel("Payment Details");
        paymentDetailsLabel.setFont(passengerDetailsLabel.getFont());

        // fare
        ticketFareLabel = new JLabel("Ticket Fare (Rs.)");
        ticketFareLabel.setFont(bpLabel.getFont());

        ticketFare = new JLabel(String.valueOf(userMode.fare));
        ticketFare.setFont(bp.getFont());
        ticketFare.setForeground(new Color(0, 153, 153));

        // total fare
        totalFareLabel = new JLabel("Total Fare (Rs.)");
        totalFareLabel.setFont(bpLabel.getFont());

        int totalFareValue = userMode.fare * table.getModel().getRowCount();
        totalFare = new JLabel(String.valueOf(totalFareValue));
        totalFare.setFont(bp.getFont());
        totalFare.setForeground(new Color(0, 153, 153));

        // cancel ticket
        cancelButton = new JButton("Cancel Ticket") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor((new Color(255, 0, 0)).darker());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        cancelButton.setFont(bpLabel.getFont());
        cancelButton.setBackground((new Color(255, 0, 0)));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusable(false);
        cancelButton.setBorder(new RoundBorder(20));
        cancelButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        cancelButton.setContentAreaFilled(false);
        cancelButton.addActionListener(e -> cancelTicket());

        // main menu
        mainMenu = new JButton("Main Menu") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor((new Color(255, 0, 0)).darker());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        mainMenu.setFont(bpLabel.getFont());
        mainMenu.setBackground((new Color(255, 0, 0)));
        mainMenu.setForeground(Color.WHITE);
        mainMenu.setFocusable(false);
        mainMenu.setBorder(new RoundBorder(20));
        mainMenu.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        mainMenu.setContentAreaFilled(false);
        mainMenu.addActionListener(e -> {
            dispose();
            Railway mw = new Railway();
            mw.setVisible(true);
        });

        // exit
        exit = new JButton("Exit") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor((new Color(255, 0, 0)).darker());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        exit.setFont(bpLabel.getFont());
        exit.setBackground((new Color(255, 0, 0)));
        exit.setForeground(Color.WHITE);
        exit.setFocusable(false);
        exit.setBorder(new RoundBorder(20));
        exit.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        exit.setContentAreaFilled(false);
        exit.addActionListener(e -> System.exit(0));

        // Main panel
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        mainPanel.add(title, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(bpLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(bp, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(dpLabel, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(dp, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(ticketID, gbc);

        gbc.gridx++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(dateAndTime, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(trainNoTrainNameLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(trainNoTrainName, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(classLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(classType, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(passengerDetailsLabel, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        mainPanel.add(scrollPane, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(paymentDetailsLabel, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(ticketFareLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(ticketFare, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(totalFareLabel, gbc);

        gbc.gridx = 1;
        mainPanel.add(totalFare, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(cancelButton, gbc);

        gbc.gridx = 1;
        mainPanel.add(mainMenu, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(exit, gbc);

        mainPanel.setBackground(new Color(255, 255, 240));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 800);
        setLocationRelativeTo(null);
        getContentPane().add(mainPanel);
        setVisible(true);
    }

    private void cancelTicket() {
        String ticketNumberInput = JOptionPane.showInputDialog(this, "Enter the ticket number to cancel:");
        try {
            String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/Railway";
            String user = "root";
            String pass = "1234";
            Class.forName(mysqlJDBCDriver);
            Connection c = DriverManager.getConnection(url, user, pass);

            PreparedStatement ps = c.prepareStatement("SELECT ticketID FROM chart WHERE ticketID = ?");
            ps.setString(1, ticketNumberInput);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Statement s = c.createStatement();
                String query = "DELETE FROM chart WHERE ticketID ='" + ticketNumberInput + "'";
                s.executeUpdate(query);

                JOptionPane.showMessageDialog(this, "Ticket " + ticketNumberInput + " Cancelled Successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new Railway();

            } else {
                JOptionPane.showMessageDialog(this, "Ticket number " + ticketNumberInput + " not found.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            rs.close();
            ps.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ticket could not be cancelled.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
