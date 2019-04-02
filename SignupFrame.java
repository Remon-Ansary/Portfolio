import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class SignupFrame extends JFrame {

	private JPanel contentPane;
	private JTextField nameTF;
	private JTextField emailTF;
	private JTextField usernameTF;
	private JTextField passField;
	private JTextField ageTF;
	private JTextField contactTF;
	private JTextField heightTF;
	private JRadioButton rdbtnMale,rdbtnFemale;
	private JComboBox addressCB;
	private JCheckBox chckbxIAcceptAll;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	String v="";
	Connection conn=null;
	PreparedStatement pst =null;
	ResultSet rs=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignupFrame frame = new SignupFrame();
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
	public SignupFrame() {
		setResizable(false);
		setForeground(new Color(255, 255, 255));
		setBackground(new Color(255, 255, 255));
		setIconImage(Toolkit.getDefaultToolkit().getImage(SignupFrame.class.getResource("/photo/korganizer-icon.png")));
		setTitle("Signup Form");
		design();
		conn =SQLConnection.ConnecrDb();
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
	
	private void register () {
		try {
			String query ="INSERT INTO employee (E_Name,E_Mail,E_Username,E_Password,E_Age,E_Contact,E_Gender,E_Height,E_Adress,E_Education,E_Books,E_Music,E_Website,E_About,E_Lessons,E_Like,E_Courses,E_Skills,E_Programming) VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pst =conn.prepareStatement(query);
			pst.setString(1, nameTF.getText());
			pst.setString(2, emailTF.getText());
			
			pst.setString(3, usernameTF.getText());
			pst.setString(4, passField.getText());
			pst.setString(5, ageTF.getText());
			pst.setString(6, contactTF.getText());
			
			if(rdbtnMale.isSelected()) {
				 v =rdbtnMale.getText().toString();
			}else if(rdbtnFemale.isSelected()) {
				 v=rdbtnFemale.getText().toString();
			}else {
				JOptionPane.showMessageDialog(null, "Select Gender");
			}
			
			pst.setString(7, String.valueOf(v) );
			pst.setString(8, heightTF.getText());
			pst.setString(9, addressCB.getSelectedItem().toString());
			pst.setString(10, "");
			pst.setString(11, "");
			pst.setString(12, "");
			pst.setString(13, "");
			pst.setString(14, "");
			pst.setString(15, "");
			pst.setString(16, "");
			pst.setString(17, "");
			pst.setString(18, "");
			pst.setString(19, "");
			pst.execute();
			pst.close();
			JOptionPane.showMessageDialog(null, "Signup Successful");
			FirstJFrame fj = new FirstJFrame();
			fj.setVisible(true);
			dispose();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
		private void design()
		{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 540);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSignupHere = new JLabel("Signup Here");
		lblSignupHere.setForeground(new Color(0, 0, 0));
		lblSignupHere.setBackground(Color.BLUE);
		lblSignupHere.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblSignupHere.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignupHere.setBounds(10, 11, 551, 36);
		contentPane.add(lblSignupHere);
		
		nameTF = new JTextField();
		nameTF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nameTF.setHorizontalAlignment(SwingConstants.CENTER);
		nameTF.setBounds(152, 58, 369, 28);
		contentPane.add(nameTF);
		nameTF.setColumns(10);
		
		emailTF = new JTextField();
		emailTF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		emailTF.setHorizontalAlignment(SwingConstants.CENTER);
		emailTF.setBounds(152, 97, 369, 28);
		contentPane.add(emailTF);
		emailTF.setColumns(10);
		
		usernameTF = new JTextField();
		usernameTF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		usernameTF.setHorizontalAlignment(SwingConstants.CENTER);
		usernameTF.setBounds(152, 136, 369, 28);
		contentPane.add(usernameTF);
		usernameTF.setColumns(10);
		
		passField = new JTextField();
		passField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passField.setHorizontalAlignment(SwingConstants.CENTER);
		passField.setBounds(152, 175, 369, 28);
		contentPane.add(passField);
		passField.setColumns(10);
		
		ageTF = new JTextField();
		ageTF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ageTF.setHorizontalAlignment(SwingConstants.CENTER);
		ageTF.setBounds(152, 219, 204, 23);
		contentPane.add(ageTF);
		ageTF.setColumns(10);
		
		contactTF = new JTextField();
		contactTF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contactTF.setHorizontalAlignment(SwingConstants.CENTER);
		contactTF.setBounds(152, 253, 369, 28);
		contentPane.add(contactTF);
		contactTF.setColumns(10);
		
		 rdbtnMale = new JRadioButton("Male");
		 rdbtnMale.setFont(new Font("Tahoma", Font.BOLD, 11));
		 rdbtnMale.setForeground(new Color(25, 25, 112));
		buttonGroup.add(rdbtnMale);
		rdbtnMale.setBounds(152, 295, 109, 23);
		contentPane.add(rdbtnMale);
		
		 rdbtnFemale = new JRadioButton("Female");
		 rdbtnFemale.setFont(new Font("Tahoma", Font.BOLD, 11));
		 rdbtnFemale.setForeground(new Color(25, 25, 112));
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setBounds(270, 295, 109, 23);
		contentPane.add(rdbtnFemale);
		
		heightTF = new JTextField();
		heightTF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		heightTF.setHorizontalAlignment(SwingConstants.CENTER);
		heightTF.setBounds(152, 329, 369, 28);
		contentPane.add(heightTF);
		heightTF.setColumns(10);
		
		 addressCB = new JComboBox();
		addressCB.setModel(new DefaultComboBoxModel(new String[] {"Dhaka", "Rajshahi", "Sylhet", "Barishal"}));
		addressCB.setBounds(152, 368, 221, 28);
		contentPane.add(addressCB);
		
		JLabel lblName = new JLabel("Name");
		lblName.setIcon(new ImageIcon(SignupFrame.class.getResource("/photo/usernnnn.png")));
		lblName.setFont(new Font("Rockwell", Font.BOLD, 17));
		lblName.setBounds(20, 55, 107, 32);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setIcon(new ImageIcon(SignupFrame.class.getResource("/photo/Mail-icon.png")));
		lblEmail.setFont(new Font("Rockwell Condensed", Font.BOLD, 17));
		lblEmail.setBounds(22, 94, 89, 32);
		contentPane.add(lblEmail);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setIcon(new ImageIcon(SignupFrame.class.getResource("/photo/Office-Customer-Male-Light-icon.png")));
		lblUsername.setFont(new Font("Rockwell Condensed", Font.BOLD, 17));
		lblUsername.setBounds(20, 133, 129, 32);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setIcon(new ImageIcon(SignupFrame.class.getResource("/photo/secrecy-icon.png")));
		lblPassword.setFont(new Font("Rockwell Condensed", Font.BOLD, 17));
		lblPassword.setBounds(20, 172, 125, 32);
		contentPane.add(lblPassword);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setIcon(new ImageIcon(SignupFrame.class.getResource("/photo/Age-Child-Male-Light-icon.png")));
		lblAge.setFont(new Font("Rockwell Condensed", Font.BOLD, 17));
		lblAge.setBounds(20, 218, 107, 23);
		contentPane.add(lblAge);
		
		JLabel lblContact = new JLabel("Contact");
		lblContact.setIcon(new ImageIcon(SignupFrame.class.getResource("/photo/phone-icon - Copy.png")));
		lblContact.setFont(new Font("Rockwell Condensed", Font.BOLD, 17));
		lblContact.setBounds(20, 252, 109, 28);
		contentPane.add(lblContact);
		
		JLabel lblHeight = new JLabel("Height");
		lblHeight.setIcon(new ImageIcon(SignupFrame.class.getResource("/photo/Height-Window-icon.png")));
		lblHeight.setFont(new Font("Rockwell Condensed", Font.BOLD, 17));
		lblHeight.setBounds(20, 329, 122, 26);
		contentPane.add(lblHeight);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setIcon(new ImageIcon(SignupFrame.class.getResource("/photo/Users-Gender-icon.png")));
		lblGender.setFont(new Font("Rockwell Condensed", Font.BOLD, 17));
		lblGender.setBounds(20, 293, 111, 23);
		contentPane.add(lblGender);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setIcon(new ImageIcon(SignupFrame.class.getResource("/photo/Actions-go-home-icon.png")));
		lblAddress.setFont(new Font("Rockwell Condensed", Font.BOLD, 17));
		lblAddress.setBounds(22, 364, 120, 32);
		contentPane.add(lblAddress);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setIcon(new ImageIcon(SignupFrame.class.getResource("/photo/korganizer-icon.png")));
		btnRegister.setForeground(new Color(0, 0, 139));
		btnRegister.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 16));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxIAcceptAll.isSelected()) {
					register ();
				}else {
					JOptionPane.showMessageDialog(null, "Read Terms and Conditions");
				}
				
			}
		});
		btnRegister.setBounds(197, 433, 182, 57);
		contentPane.add(btnRegister);
		
		chckbxIAcceptAll = new JCheckBox("I accept all the terms and conditions");
		chckbxIAcceptAll.setForeground(new Color(34, 139, 34));
		chckbxIAcceptAll.setFont(new Font("Tahoma", Font.BOLD, 11));
		chckbxIAcceptAll.setBounds(152, 403, 369, 23);
		contentPane.add(chckbxIAcceptAll);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setIcon(new ImageIcon(SignupFrame.class.getResource("/photo/arrow-back-icon.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FirstJFrame frame = new FirstJFrame();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(10, 449, 117, 33);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.setIcon(new ImageIcon(SignupFrame.class.getResource("/photo/Windows-Close-Program-icon.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton_1.setForeground(new Color(255, 0, 0));
		btnNewButton_1.setBounds(446, 441, 105, 41);
		contentPane.add(btnNewButton_1);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(SignupFrame.class.getResource("/photo/images222.jpg")));
		label.setBounds(0, 0, 561, 501);
		contentPane.add(label);
	}
}
