import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/Photo/Free-Images-Book-Wallpapers-HD-full-hd-download-high-definiton-wallpapers-desktop-images-windows-10-backgrounds-colourful-hi-res-cool-artwork-2560x1600.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnwelcome = new JButton("Welcome To Portfolio Management");
		btnwelcome.setBackground(UIManager.getColor("Button.light"));
		btnwelcome.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnwelcome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FirstJFrame g= new FirstJFrame();
				g.setVisible(true);
				
				dispose();
			}
		});
		btnwelcome.setBounds(207, 248, 346, 61);
		contentPane.add(btnwelcome);
		
		JLabel lblStudentPortfolioHelps = new JLabel("Student Portfolio helps you  to reflect on your academic goals and progress.\r\n");
		lblStudentPortfolioHelps.setForeground(new Color(0, 0, 0));
		lblStudentPortfolioHelps.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStudentPortfolioHelps.setBounds(34, 98, 767, 55);
		contentPane.add(lblStudentPortfolioHelps);
		
		JLabel lblItCreatesAn = new JLabel("It creates an archive that compiles your most important projects and academic work.");
		lblItCreatesAn.setForeground(SystemColor.desktop);
		lblItCreatesAn.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblItCreatesAn.setBounds(20, 164, 791, 55);
		contentPane.add(lblItCreatesAn);
		
		JLabel lblStudentPortfolio = new JLabel("Student Portfolio");
		lblStudentPortfolio.setForeground(new Color(102, 0, 0));
		lblStudentPortfolio.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblStudentPortfolio.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentPortfolio.setBounds(255, 36, 298, 51);
		contentPane.add(lblStudentPortfolio);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(MainFrame.class.getResource("/Photo/Free-Images-Book-Wallpapers-HD-full-hd-download-high-definiton-wallpapers-desktop-images-windows-10-backgrounds-colourful-hi-res-cool-artwork-2560x1600.jpg")));
		label.setBounds(0, 0, 811, 474);
		contentPane.add(label);
	}
}
