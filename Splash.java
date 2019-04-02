import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Toolkit;

public class Splash extends JFrame implements ActionListener {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Splash frame = new Splash();
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
	public Splash(){
		setIconImage(Toolkit.getDefaultToolkit().getImage(Splash.class.getResource("/Photo/Cover_Page111.png")));
		design();
		tm.start();
		welcomeMusic();
		
	}
	Timer tm =new Timer(22,this);
	int time =0;
	private JLabel loadingLabel;
	private JProgressBar progressBar;
	private JLabel label_1;
	private JLabel lblStudentPortfolio_1;
	
	public void actionPerformed(ActionEvent e) {
		if(time !=100) {
			time++;
			progressBar.setValue(time);
			loadingLabel.setText(time+"%");
			
		}else {
			tm.stop();
			
			//JOptionPane.showMessageDialog(null,"Done");
			MainFrame mf=new MainFrame();
			mf.setVisible(true);
			dispose();
			
		}
	}
	
	private void welcomeMusic() {
		
		try {
			Clip c = AudioSystem.getClip();
			AudioInputStream inputStream =AudioSystem.getAudioInputStream(Splash.class.getResource("/Files/46352708.wav"));
			c.open(inputStream);
			c.start();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	private void design()
		
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 410);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	 
	loadingLabel = new JLabel("%");
	loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
	loadingLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
	loadingLabel.setBounds(232, 350, 196, 23);
	contentPane.add(loadingLabel);
		
	 progressBar = new JProgressBar();
		progressBar.setBounds(0, 346, 686, 35);
		contentPane.add(progressBar);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(Splash.class.getResource("/Files/clear12.png")));
		label_1.setBounds(0, 45, 686, 336);
		contentPane.add(label_1);
		
		lblStudentPortfolio_1 = new JLabel("Student Portfolio");
		lblStudentPortfolio_1.setForeground(new Color(0, 0, 139));
		lblStudentPortfolio_1.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblStudentPortfolio_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentPortfolio_1.setBounds(124, 0, 430, 41);
		contentPane.add(lblStudentPortfolio_1);
	}
}
