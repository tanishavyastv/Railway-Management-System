import java.awt.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;

public class dbTrainsSubmit extends JFrame {
    public dbTrainsSubmit() {
        // calling values
        String trainNumber = dbTrains1.trainNumber;
        String trainName = dbTrains2.trainNameValue;
        String doj = dbTrains2.dojValue;
        String bp = dbTrains2.boardingValue;
        String dp = dbTrains2.destinationValue;
        String at = dbTrains3.arrivalTimeValue;
        String dt = dbTrains3.departureTimeValue;
        int seats = dbTrains3.numOfSeatsValue;
        int fAC = dbTrains4.firstACTicketPrice;
        int sAC = dbTrains4.secondACTicketPrice;
        int tAC = dbTrains4.thirdACTicketPrice;
        int sC = dbTrains4.sleeperClassTicketPrice;

        String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/Railway";
        String user = "root";
        String pass = "1234";

        try {
            Class.forName(mysqlJDBCDriver);
            Connection c = DriverManager.getConnection(url, user, pass);

            PreparedStatement st = c.prepareStatement(
                    "insert into train (tnum,tname,seats,bp,dp,fAC,sAC,tAC,sc,doj,atime,dtime) values(?,?,?,?,?,?,?,?,?,?,?,?)");

            st.setString(1, trainNumber);
            st.setString(2, trainName);
            st.setInt(3, seats);
            st.setString(4, bp);
            st.setString(5, dp);
            st.setInt(6, fAC);
            st.setInt(7, sAC);
            st.setInt(8, tAC);
            st.setInt(9, sC);

            // Convert the date string to a java.sql.Date object
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            java.util.Date date = formatter.parse(doj);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            st.setDate(10, sqlDate);

            // Convert the time strings to java.sql.Time objects
            SimpleDateFormat timeFormatter = new SimpleDateFormat("hh:mm:ss");
            java.util.Date departureTime = timeFormatter.parse(dt);
            java.util.Date arrivalTime = timeFormatter.parse(at);
            java.sql.Time sqlDepartureTime = new java.sql.Time(departureTime.getTime());
            java.sql.Time sqlArrivalTime = new java.sql.Time(arrivalTime.getTime());
            st.setTime(11, sqlArrivalTime);
            st.setTime(12, sqlDepartureTime);

            st.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data inserted successfully!", "Success",
                    JOptionPane.INFORMATION_MESSAGE);

            st.close();
            c.close();

            // Display the records inserted
            setTitle("Database of a train Window");

            // Title
            JLabel title = new JLabel("Record Inserted Successfully!");
            title.setFont(new Font("Arial", Font.ITALIC, 30));

            // Train Number
            JLabel trainNum = new JLabel("Train Number:");
            trainNum.setFont(new Font("Consolas", Font.PLAIN, 20));

            JLabel trainNumField = new JLabel(trainNumber);
            trainNumField.setFont(trainNum.getFont());

            // Train Name
            JLabel tName = new JLabel("Train Name:");
            tName.setFont(trainNum.getFont());

            JLabel tNameField = new JLabel(trainName);
            tNameField.setFont(trainNum.getFont());

            // Date of Journey
            JLabel dOj = new JLabel("Date of Journey:");
            dOj.setFont(trainNum.getFont());

            JLabel dOjField = new JLabel("" + sqlDate);
            dOjField.setFont(trainNum.getFont());

            // Boarding Point
            JLabel bP = new JLabel("Boarding Point:");
            bP.setFont(trainNum.getFont());

            JLabel bPField = new JLabel(bp);
            bPField.setFont(trainNum.getFont());

            // Destination Point
            JLabel dP = new JLabel("Destination Point:");
            dP.setFont(trainNum.getFont());

            JLabel dPField = new JLabel(dp);
            dPField.setFont(trainNum.getFont());

            // Arrival Time
            JLabel aT = new JLabel("Arrival Time:");
            aT.setFont(trainNum.getFont());

            JLabel aTField = new JLabel("" + sqlArrivalTime);
            aTField.setFont(trainNum.getFont());

            // Departure Time
            JLabel dT = new JLabel("Departure Time:");
            dT.setFont(trainNum.getFont());

            JLabel dTField = new JLabel("" + sqlDepartureTime);
            dTField.setFont(trainNum.getFont());
            
            // Number of Seats
            JLabel seat = new JLabel("Number of Seats:");
            seat.setFont(trainNum.getFont());

            JLabel seatField = new JLabel("" + seats);
            seatField.setFont(trainNum.getFont());

            // First AC
            JLabel fac = new JLabel("First AC ticket Price:");
            fac.setFont(trainNum.getFont());

            JLabel facField = new JLabel("" + fAC);
            facField.setFont(trainNum.getFont());

            // Second AC
            JLabel sac = new JLabel("Second AC ticket Price:");
            sac.setFont(trainNum.getFont());

            JLabel sacField = new JLabel("" + sAC);
            sacField.setFont(trainNum.getFont());

            // Third AC
            JLabel tac = new JLabel("Third AC ticket Price:");
            tac.setFont(trainNum.getFont());

            JLabel tacField = new JLabel("" + tAC);
            tacField.setFont(trainNum.getFont());

            // Sleeper Class
            JLabel sc = new JLabel("Sleeper Class ticket Price:");
            sc.setFont(trainNum.getFont());

            JLabel scField = new JLabel("" + sC);
            scField.setFont(trainNum.getFont());

            // menu button
            JButton mainMenu = new JButton("Admin Menu") {
                @Override
                protected void paintComponent(Graphics g) {
                    if (getModel().isArmed()) {
                        g.setColor(new Color(232, 180, 37).darker());
                    } else {
                        g.setColor(getBackground());
                    }
                    g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                    super.paintComponent(g);
                }
            };
            mainMenu.addActionListener(e -> {
                dispose();
                AdministratorWindow adminWindow = new AdministratorWindow();
                adminWindow.adminMenu();
            });
            mainMenu.setFont(new Font("Cambria", Font.PLAIN, 20));
            mainMenu.setBackground(new Color(232, 180, 37));
            mainMenu.setForeground(Color.WHITE);
            mainMenu.setFocusable(false);
            mainMenu.setBorder(new RoundBorder(20));
            mainMenu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            mainMenu.setContentAreaFilled(false);

            // exit
            JButton exit = new JButton("Exit") {
                @Override
                protected void paintComponent(Graphics g) {
                    if (getModel().isArmed()) {
                        g.setColor((new Color(255, 105, 97)).darker());
                    } else {
                        g.setColor(getBackground());
                    }
                    g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                    super.paintComponent(g);
                }
            };
            exit.setFont(mainMenu.getFont());
            exit.setBackground((new Color(255, 105, 97)));
            exit.setForeground(Color.WHITE);
            exit.setFocusable(false);
            exit.setBorder(new RoundBorder(20));
            exit.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
            exit.setContentAreaFilled(false);
            exit.addActionListener(e -> System.exit(0));

            // Main panel
            JPanel DBPanel = new JPanel();
            DBPanel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(10, 10, 10, 10);

            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            DBPanel.add(title, gbc);

            gbc.gridwidth = 1;
            gbc.gridy++;
            addLabel(trainNum, trainNumField, DBPanel, gbc);
            gbc.gridy++;
            addLabel(tName, tNameField, DBPanel, gbc);
            gbc.gridy++;
            addLabel(dOj, dOjField, DBPanel, gbc);
            gbc.gridy++;
            addLabel(bP, bPField, DBPanel, gbc);
            gbc.gridy++;
            addLabel(dP, dPField, DBPanel, gbc);
            gbc.gridy++;
            addLabel(aT, aTField, DBPanel, gbc);
            gbc.gridy++;
            addLabel(dT, dTField, DBPanel, gbc);
            gbc.gridy++;
            addLabel(seat, seatField, DBPanel, gbc);
            gbc.gridy++;
            addLabel(fac, facField, DBPanel, gbc);
            gbc.gridy++;
            addLabel(sac, sacField, DBPanel, gbc);
            gbc.gridy++;
            addLabel(tac, tacField, DBPanel, gbc);
            gbc.gridy++;
            addLabel(sc, scField, DBPanel, gbc);

            // Add buttons to the panel
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.WEST;
            DBPanel.add(mainMenu, gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.EAST;
            DBPanel.add(exit, gbc);

            DBPanel.setBackground(new Color(234, 222, 250));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(700, 750);
            setLocationRelativeTo(null);
            getContentPane().add(DBPanel);
            setVisible(true);

        } catch (ClassNotFoundException | SQLException | ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addLabel(Component label1, Component label2, Container container, GridBagConstraints gbc) {
        gbc.gridx = 0; // Label on the left
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        container.add(label1, gbc);

        gbc.gridx = 1; // Label on the right
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        container.add(label2, gbc);
    }
}
