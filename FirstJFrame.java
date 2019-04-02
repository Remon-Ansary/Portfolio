import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class FirstJFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Connection conn=null;
	PreparedStatement pst =null;
	ResultSet rs=null;
	public static String weLcomeName = "";
	
	private JTextField usernameField;
	private JPasswordField passField;
	private JLabel clockLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstJFrame frame = new FirstJFrame();
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
	public FirstJFrame()
	{
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FirstJFrame.class.getResource("/photo/Apps-Computer-Login-icon.png")));
		setTitle("Login and Signup");
		design();
		conn= SQLConnection.ConnecrDb();
		clock();
		centerize();	
	}
	
	public void centerize() {
		
		Dimension screenSize,frameSize;
		int x,y;
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameSize = getSize();
		x = (screenSize.width - frameSize.width)/2;
		y = (screenSize.height - frameSize.height)/2;
		setLocation(x,y);
	}
	
	private void login() {
		try {
			String query="SELECT * FROM EMPLOYEE WHERE E_Username = ? AND E_Password =?";
		
			pst = conn.prepareStatement(query);
			pst.setString(1, usernameField.getText());
			pst.setString(2, passField.getText());
			rs =pst.executeQuery();
			//int count =0;
			if(rs.next()) {
				
			weLcomeName = rs.getString("E_Name");
			
			JOptionPane.showMessageDialog(null, "Login Successfull");
			Management m= new Management();
			m.setVisible(true);
			
			dispose();
	}
	else {
		JOptionPane.showMessageDialog(null, "Login Failed");
		}
		pst.close();
		rs.close();
			
		}
		catch(Exception e) {
			e.printStackTrace();
			
		}
}
	
	private void clock() {
		
		Thread clock = new Thread(){
			
			public void run() {
				
				try {
					for(;;) {
						
						Calendar cal = new GregorianCalendar();
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy  hh:mm:ss a");
						Date date = cal.getTime();
						String timeString = formatter.format(date);
						clockLabel.setText(timeString);
						sleep(1000);
						 
					}
					
				}catch(Exception e) {
					e.getStackTrace();
				}
			}
			
		};
		clock.start();
	}
			
	private void design()
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon(FirstJFrame.class.getResource("/photo/Login-icon.png")));
		btnLogin.setForeground(new Color(0, 0, 128));
		btnLogin.setFont(new Font("Eras Bold ITC", Font.BOLD, 18));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(usernameField.getText().equals("")) {
					
					JOptionPane.showMessageDialog(null, "Enter username...!");
				}else if(passField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Password to continue...!");
				}else {
					login();
				}
				
			}
		});
		btnLogin.setBounds(163, 231, 159, 57);
		contentPane.add(btnLogin);
		
		JButton btnSignupHere = new JButton("Signup");
		btnSignupHere.setIcon(new ImageIcon(FirstJFrame.class.getResource("/photo/korganizer-icon.png")));
		btnSignupHere.setForeground(new Color(34, 139, 34));
		btnSignupHere.setFont(new Font("Stencil", Font.BOLD, 18));
		btnSignupHere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignupFrame frame = new SignupFrame();
				frame.setVisible(true);
			}
		});
		btnSignupHere.setBounds(151, 313, 185, 44);
		contentPane.add(btnSignupHere);
		
		usernameField = new JTextField();
		usernameField.setBackground(SystemColor.menu);
		usernameField.setFont(new Font("Tahoma", Font.BOLD, 13));
		usernameField.setHorizontalAlignment(SwingConstants.CENTER);
		usernameField.setBounds(151, 87, 185, 44);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passField = new JPasswordField();
		passField.setBackground(SystemColor.menu);
		passField.setFont(new Font("Tahoma", Font.BOLD, 13));
		passField.setHorizontalAlignment(SwingConstants.CENTER);
		passField.setBounds(151, 165, 185, 44);
		contentPane.add(passField);
		
		clockLabel = new JLabel("Clock");
		clockLabel.setForeground(new Color(0, 0, 128));
		clockLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		clockLabel.setHorizontalAlignment(SwingConstants.CENTER);
		clockLabel.setBounds(10, 11, 414, 65);
		contentPane.add(clockLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(FirstJFrame.class.getResource("/photo/backg1.jpg")));
		label.setBounds(10, 11, 0, 0);
		contentPane.add(label);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 15));
		lblUsername.setIcon(new ImageIcon(FirstJFrame.class.getResource("/photo/username1.png")));
		lblUsername.setBounds(10, 86, 144, 44);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblPassword.setIcon(new ImageIcon(FirstJFrame.class.getResource("/photo/pass.png")));
		lblPassword.setBounds(10, 161, 144, 48);
		contentPane.add(lblPassword);
		
		JLabel label_1 = new JLabel("");
		label_1.setBackground(SystemColor.menu);
		label_1.setIcon(new ImageIcon(FirstJFrame.class.getResource("/photo/alessandro-sacchi-195296-unsplash11111.jpg")));
		label_1.setBounds(0, -23, 444, 419);
		contentPane.add(label_1);
	}
}
