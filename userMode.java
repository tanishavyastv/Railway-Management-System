import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class userMode extends JFrame {
    public static int tnum;
    public static String coach;
    public static Date doj;
    public static String BP;
    public static String DP;
    public static String time;
    public static String tname;
    public static int fare;
    JPanel mainPanel;
    JLabel login;
    JLabel reserve;
    JLabel bpLabel;
    JLabel dpLabel;
    JComboBox<String> boardingPointComboBox;
    JComboBox<String> destinationPointComboBox;
    JButton mainMenu;
    JButton submit;
    JTextArea trainInfoTextArea;
    JLabel classLabel;
    JComboBox<String> classComboBox;

    public userMode() {
        setTitle("User Mode Window");

        // title
        login = new JLabel("Welcome, " + loginUser.userField.getText() + "!");
        login.setFont(new Font("Arial", Font.BOLD, 40));
        login.setHorizontalAlignment(JLabel.CENTER);

        // reserve title
        reserve = new JLabel("RESERVE");
        reserve.setFont(new Font("Arial", Font.BOLD, 30));
        reserve.setHorizontalAlignment(JLabel.CENTER);

        // boarding point
        bpLabel = new JLabel("Boarding Point");
        bpLabel.setFont(new Font("Cambria", Font.PLAIN, 20));

        boardingPointComboBox = new JComboBox<>();
        boardingPointComboBox.setFont(new Font("century", Font.PLAIN, 15));
        boardingPointComboBox.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        boardingPointComboBox.addActionListener(this::loadTrainInfo);

        // destination point
        dpLabel = new JLabel("Destination Point");
        dpLabel.setFont(bpLabel.getFont());

        destinationPointComboBox = new JComboBox<>();
        destinationPointComboBox.setFont(bpLabel.getFont());
        destinationPointComboBox.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        destinationPointComboBox.addActionListener(this::loadTrainInfo);

        // Class selection
        classLabel = new JLabel("Select Class:");
        classLabel.setFont(bpLabel.getFont());

        classComboBox = new JComboBox<>(new String[] { "First AC", "Second AC", "Third AC", "Sleeper AC" });
        classComboBox.setFont(bpLabel.getFont());
        classComboBox.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        classComboBox.addActionListener(this::loadTrainInfo);

        // Train info text area
        trainInfoTextArea = new JTextArea();
        trainInfoTextArea.setEditable(false);
        trainInfoTextArea.setFont(new Font("Consolas", Font.PLAIN, 18));
        JScrollPane scrollPane = new JScrollPane(trainInfoTextArea);
        trainInfoTextArea.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // jdbc connectivity
        try {
            String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/Railway";
            String user = "root";
            String pass = "1234";
            Class.forName(mysqlJDBCDriver);
            Connection c = DriverManager.getConnection(url, user, pass);
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT DISTINCT bp FROM train");

            while (r.next()) {
                boardingPointComboBox.addItem(r.getString("bp"));
            }
            r.close();

            r = s.executeQuery("SELECT DISTINCT dp FROM train");
            while (r.next()) {
                destinationPointComboBox.addItem(r.getString("dp"));
            }
            r.close();
            s.close();
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        submit = new JButton("Submit") {
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
        submit.setFont(bpLabel.getFont());
        submit.setActionCommand("SUBMIT");
        submit.addActionListener((e) -> {
            new addPassengers();
            setVisible(false);
        });
        submit.setBackground(new Color(102, 178, 255));
        submit.setForeground(Color.WHITE);
        submit.setFocusable(false);
        submit.setBorder(new RoundBorder(20));
        submit.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        submit.setContentAreaFilled(false);

        mainMenu = new JButton("Main Menu") {
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
        mainMenu.setBackground(new Color(255, 102, 178));
        mainMenu.setForeground(Color.WHITE);
        mainMenu.setFocusable(false);
        mainMenu.setBorder(new RoundBorder(20));
        mainMenu.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        mainMenu.setContentAreaFilled(false);
        mainMenu.setFont(bpLabel.getFont());
        mainMenu.addActionListener(e -> {
            dispose();
            Railway mw = new Railway();
            mw.setVisible(true);
        });

        // Main panel
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        mainPanel.add(login, gbc);
        gbc.gridy++;
        mainPanel.add(reserve, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(bpLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(boardingPointComboBox, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(dpLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(destinationPointComboBox, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        mainPanel.add(classLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        mainPanel.add(classComboBox, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        mainPanel.add(scrollPane, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(mainMenu, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(submit, gbc);

        mainPanel.setBackground(Color.LIGHT_GRAY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 650);
        setLocationRelativeTo(null);
        getContentPane().add(mainPanel);
        setVisible(true);
    }

    private void loadTrainInfo(ActionEvent e) {
        String boardingPoint = (String) boardingPointComboBox.getSelectedItem();
        String destinationPoint = (String) destinationPointComboBox.getSelectedItem();
        String selectedClass = (String) classComboBox.getSelectedItem();

        if (boardingPoint != null && destinationPoint != null && selectedClass != null) {
            try {
                String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver";
                String url = "jdbc:mysql://localhost:3306/Railway";
                String user = "root";
                String pass = "1234";
                Class.forName(mysqlJDBCDriver);
                Connection c = DriverManager.getConnection(url, user, pass);
                PreparedStatement ps = c.prepareStatement("SELECT * FROM train WHERE bp = ? AND dp = ?");
                ps.setString(1, boardingPoint);
                ps.setString(2, destinationPoint);
                ResultSet rs = ps.executeQuery();

                StringBuilder sb = new StringBuilder();
                while (rs.next()) {
                    sb.append("Train Name: ").append(rs.getString("tname")).append("\n");
                    sb.append("Train Number: ").append(rs.getInt("tnum")).append("\n");
                    sb.append("Journey Date: ").append(rs.getDate("doj")).append("\n");
                    sb.append("Arrival Time: ").append(rs.getString("atime")).append("\n");
                    sb.append("Departure Time: ").append(rs.getString("dtime")).append("\n");

                    if (selectedClass == "First AC") {
                        fare = rs.getInt("fAC");
                        sb.append("Fare: ").append(fare);
                    } else if (selectedClass == "Second AC") {
                        fare = rs.getInt("sAC");
                        sb.append("Fare: ").append(fare);
                    } else if (selectedClass == "Third AC") {
                        fare = rs.getInt("tAC");
                        sb.append("Fare: ").append(fare);
                    } else if (selectedClass == "Sleeper AC") {
                        fare = rs.getInt("sc");
                        sb.append("Fare: ").append(fare);
                    }

                    // call values
                    BP = boardingPoint;
                    DP = destinationPoint;
                    time = rs.getString("atime");
                    doj = rs.getDate("doj");
                    coach = selectedClass;
                    tnum = rs.getInt("tnum");
                    tname = rs.getString("tname");
                }

                trainInfoTextArea.setText(sb.toString());

                rs.close();
                ps.close();
                c.close();
            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
