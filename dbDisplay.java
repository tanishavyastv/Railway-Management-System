import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.*;

public class dbDisplay extends JFrame {
    public dbDisplay() {
        setTitle("Database of Trains Window");
        setPreferredSize(new Dimension(1500, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("DATABASE OF TRAINS");
        title.setFont(new Font("Cambria", Font.BOLD, 30));

        // previous button
        JButton previous = new JButton("\u2190 Previous") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(105, 65, 158).darker());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        previous.addActionListener(e -> {
            dispose();
            AdministratorWindow adminWindow = new AdministratorWindow();
            adminWindow.adminMenu();
        });
        previous.setFont(new Font("Cambria", Font.BOLD, 20));
        previous.setBackground(new Color(105, 65, 158));
        previous.setForeground(Color.WHITE);
        previous.setFocusable(false);
        previous.setBorder(new RoundBorder(20));
        previous.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        previous.setContentAreaFilled(false);

        JScrollPane scrollPane;
        try {
            String mysqlJDBCDriver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/Railway";
            String user = "root";
            String pass = "1234";
            Class.forName(mysqlJDBCDriver);
            Connection c = DriverManager.getConnection(url, user, pass);
            Statement stmt = c.createStatement();
            String sql = "select tnum,tname,seats,bp,dp,fAC,sAC,tAC,sc,doj,atime,dtime from train";
            ResultSet rs = stmt.executeQuery(sql);

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Train Number");
            model.addColumn("Train Name");
            model.addColumn("Seats");
            model.addColumn("Boarding Point");
            model.addColumn("Destination Point");
            model.addColumn("First AC");
            model.addColumn("Second AC");
            model.addColumn("Third AC");
            model.addColumn("Sleeper Class");
            model.addColumn("Date of Journey");
            model.addColumn("Arrival Time");
            model.addColumn("Departure Time");

            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getInt("tnum"),
                        rs.getString("tname"),
                        rs.getInt("seats"),
                        rs.getString("bp"),
                        rs.getString("dp"),
                        rs.getInt("fAC"),
                        rs.getInt("sAC"),
                        rs.getInt("tAC"),
                        rs.getInt("sc"),
                        rs.getDate("doj"),
                        rs.getString("atime"),
                        rs.getString("dtime")
                });
                ;
            }
            JTable table = new JTable(model);
            table.setFont(new Font("century", Font.PLAIN, 12));
            table.setRowHeight(30);
            table.setPreferredScrollableViewportSize(new Dimension(1400, 250));
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table.getTableHeader().setBackground(new Color(105, 65, 158));
            table.getTableHeader().setForeground(Color.WHITE);
            table.getTableHeader().setFont(new Font("Cambria", Font.BOLD, 14));

            scrollPane = new JScrollPane(table);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.CENTER);

            table.setDefaultRenderer(Object.class, renderer);

            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(140);
            columnModel.getColumn(1).setPreferredWidth(140);
            columnModel.getColumn(2).setPreferredWidth(70);
            columnModel.getColumn(3).setPreferredWidth(250);
            columnModel.getColumn(4).setPreferredWidth(350);
            columnModel.getColumn(5).setPreferredWidth(100);
            columnModel.getColumn(6).setPreferredWidth(100);
            columnModel.getColumn(7).setPreferredWidth(100);
            columnModel.getColumn(8).setPreferredWidth(130);
            columnModel.getColumn(9).setPreferredWidth(150);
            columnModel.getColumn(10).setPreferredWidth(130);
            columnModel.getColumn(11).setPreferredWidth(150);

            // Main panel
            JPanel mainPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(10, 10, 10, 10);

            gbc.anchor = GridBagConstraints.CENTER;
            mainPanel.add(title, gbc);

            gbc.gridy++;
            mainPanel.add(scrollPane, gbc);
            
            gbc.gridy++;
            gbc.anchor = GridBagConstraints.WEST;
            mainPanel.add(previous, gbc);

            pack();
            setLocationRelativeTo(null);
            getContentPane().add(mainPanel);
            setVisible(true);
            mainPanel.setBackground(new Color(234, 222, 250));

            rs.close();
            stmt.close();
            c.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
