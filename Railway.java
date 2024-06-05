import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Railway extends JFrame {
	JPanel mainPanel;
	JLabel welcomeLabel;
	JButton adminLoginButton;
	JButton userLoginButton;
	JButton exitButton;
	JFrame jFrame = new JFrame();

	public Railway() {
		setTitle("RAILWAY MANAGEMENT SYSTEM");

		// Title
		welcomeLabel = new JLabel("WELCOME!");
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 40));
		welcomeLabel.setHorizontalAlignment(JLabel.CENTER);

		// Admin button
		adminLoginButton = new JButton("Administrator Login") {
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
		adminLoginButton.setFont(new Font("Cambria", Font.PLAIN, 20));
		adminLoginButton.setBackground(new Color(102, 178, 255));
		adminLoginButton.setForeground(Color.WHITE);
		adminLoginButton.setFocusable(false);
		adminLoginButton.setBorder(new RoundBorder(20));
		adminLoginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        adminLoginButton.setContentAreaFilled(false);

		// User button
		userLoginButton = new JButton("User Login and Registration") {
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
		userLoginButton.setFont(adminLoginButton.getFont());
		userLoginButton.setBackground(new Color(255, 102, 178));
		userLoginButton.setForeground(Color.WHITE);
		userLoginButton.setFocusable(false);
		userLoginButton.setBorder(new RoundBorder(20));
		userLoginButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		userLoginButton.setContentAreaFilled(false);

		// Main panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(10, 10, 10, 10);
		mainPanel.add(welcomeLabel, gbc);
		gbc.gridy++;
		mainPanel.add(adminLoginButton, gbc);
		gbc.gridy++;
		mainPanel.add(userLoginButton, gbc);

		mainPanel.setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 500);
		setLocationRelativeTo(null);
		getContentPane().add(mainPanel);
		setVisible(true);

		adminLoginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AdministratorWindow();
				setVisible(false);
			}
		});

		userLoginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserWindow();
				setVisible(false);
			}
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Railway();
			}
		});
	}
}

class RoundBorder implements Border {
    private int radius;

    RoundBorder(int radius) {
        this.radius = radius;
    }

    public Insets getBorderInsets(Component c) {
        int borderWidth = radius ;
        return new Insets(borderWidth, borderWidth, borderWidth, borderWidth);
    }

    public boolean isBorderOpaque() {
        return true;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}