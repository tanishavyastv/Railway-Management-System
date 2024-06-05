import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class displayUsers extends JFrame {
    public displayUsers() {
        setTitle("All Users Window");
        setPreferredSize(new Dimension(700, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel title = new JLabel("ALL USER CREDENTIALS");
        title.setFont(new Font("Cambria", Font.BOLD, 30));

        // previous button
        JButton previous = new JButton("\u2190 Previous") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(new Color(237, 76, 151).darker());
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
        previous.setBackground(new Color(237, 76, 151));
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
            String sql = "select uname,pass,age,g from user";
            ResultSet rs = stmt.executeQuery(sql);

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Username");
            model.addColumn("Password");
            model.addColumn("Age");
            model.addColumn("Gender");

            while (rs.next()) {
                model.addRow(new Object[] {
                        rs.getString("uname"),
                        rs.getString("pass"),
                        rs.getInt("age"),
                        rs.getString("g")
                });
            }
            JTable table = new JTable(model);
            table.setFont(new Font("century", Font.PLAIN, 12));
            table.setRowHeight(30);
            table.setPreferredScrollableViewportSize(new Dimension(500, 250));
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            table.getTableHeader().setBackground(new Color(237, 76, 151));
            table.getTableHeader().setForeground(Color.WHITE);
            table.getTableHeader().setFont(new Font("Cambria", Font.BOLD, 14));

            scrollPane = new JScrollPane(table);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            renderer.setHorizontalAlignment(SwingConstants.CENTER);
            table.setDefaultRenderer(Object.class, renderer);

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
            mainPanel.setBackground(new Color(255, 194, 222));

            rs.close();
            stmt.close();
            c.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
