import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JList;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.DropMode;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;

public class Management extends JFrame {

	private JPanel contentPane;
	private JTable dataTable;
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTextField nameTF;
	
	private JTextField emailTF;
	private JTextField usernameTF;
	private JTextField ageTF;
	private JTextField heightTF;
	private JComboBox addressCB;
	private JButton btnAdd;
	private JButton btnUpdate;
	private JButton btnDelete;

	private JTextField contactTF;
	private JRadioButton rdbtnFemale, rdbtnMale;
	private String v = "";
	private String combo = "";
	private int E_ID = 0;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField searchTF;
	private JButton btnReset;
	private JButton btnNewButton;
	private JComboBox dataCB, searchCB;
	private JTextField educationTF;
	private JLabel lblBooksILike;
	private JTextField booksTF;
	private JLabel lblMusicILike;
	private JTextField musicTF;
	private JLabel lblWebsiteIUse;
	private JTextField websiteTF;
	private JLabel lblLessoonPlan;
	private JTextField lessonsTF;
	private JTextField likeTF;
	private JTextField aboutTF;
	private JLabel lblSkills;
	private JTextField skillsTF;
	private JTextField programmingTF;
	private JLabel lblProgrammingLanguage;
	private JTextField coursesTF;
	private JLabel lblRecentCoursesI;
	private JEditorPane editorPane;
	private JEditorPane skillspane;
	private JEditorPane coursespane;
	private JEditorPane thingspane;
	private JEditorPane lessonspane;
	private JEditorPane aboutpane;
	private JEditorPane websitepane;
	private JEditorPane musicpane;
	private JEditorPane bookspane;
	private JEditorPane educationpane;
	private JButton btnChat;
	private JLabel label_3;
	private JLabel label_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Management frame = new Management();
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
	public Management() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Management.class.getResource("/photo/edit-file-icon.png")));
		design();
		conn = SQLConnection.ConnecrDb();
		loadTable();
		centerize();
		loadCB();
		loadList();
		
		FirstJFrame fj = new FirstJFrame();
		
		JOptionPane.showMessageDialog(null, "Welcome "+ fj.weLcomeName);
	}

	private void centerize() {

		Dimension screenSize, frameSize;
		int x, y;
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameSize = getSize();
		x = (screenSize.width - frameSize.width) / 2;
		y = (screenSize.height - frameSize.height) / 2;
		setLocation(x, y);
	}

	private void loadTable() {
		try {
			String query = "Select E_ID, E_Name,E_Mail,E_Username,E_Age,E_Contact,E_Gender,E_Height,E_Books,E_Music,E_Website,E_About,E_Lessons,E_Like,E_Courses,E_Skills,E_Programming,E_Adress,E_Education FROM Employee";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			dataTable.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadCBClick() {
		try {

			String query = "Select * FROM Employee WHERE E_Name= ?";
			pst = conn.prepareStatement(query);

			pst.setString(1, (String) dataCB.getSelectedItem());

			rs = pst.executeQuery();
			while (rs.next()) {
				E_ID = rs.getInt("E_ID");
				educationpane.setText(rs.getString("E_Education"));
				bookspane.setText(rs.getString("E_Books"));
				musicpane.setText(rs.getString("E_Music"));
				websitepane.setText(rs.getString("E_Website"));
				aboutpane.setText(rs.getString("E_About"));
				lessonspane.setText(rs.getString("E_Lessons"));
				thingspane.setText(rs.getString("E_Like"));
				coursespane.setText(rs.getString("E_Courses"));
				skillspane.setText(rs.getString("E_Skills"));
				programmingTF.setText(rs.getString("E_Programming"));
				
				

				nameTF.setText(rs.getString("E_Name"));
				emailTF.setText(rs.getString("E_Mail"));
				usernameTF.setText(rs.getString("E_Username"));
				//passField.setText(rs.getString("E_Password"));
				ageTF.setText(rs.getString("E_Age"));
				contactTF.setText(rs.getString("E_Contact"));
				v = rs.getString("E_Gender");
				if (v.equals("Male")) {
					rdbtnMale.setSelected(true);
					rdbtnFemale.setSelected(false);
				} else if (v.equals("Female")) {
					rdbtnFemale.setSelected(true);
					rdbtnMale.setSelected(false);
				} else {
					System.out.println("");
				}
				heightTF.setText(rs.getString("E_Height"));

				combo = rs.getString("E_Adress");

				if (combo.equals("Dhaka")) {
					addressCB.setSelectedItem("Dhaka");
				} else if (combo.equals("Rajshahi")) {
					addressCB.setSelectedItem("Rajshahi");
				} else if (combo.equals("Barishal")) {
					addressCB.setSelectedItem("Barishal");
				} else if (combo.equals("Sylhet")) {
					addressCB.setSelectedItem("Sylhet");
				} else {
					System.out.println("");
				}

			}
			pst.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void addData() {
		try {
			String query = "INSERT INTO Employee (E_Name,E_Mail,E_Username,E_Password,E_Age,E_Contact,E_Gender,E_Height,E_Adress,E_Education,E_Books,E_Music,E_Website,E_About,E_Lessons,E_Like,E_Courses,E_Skills,E_Programming) VALUE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			pst = conn.prepareStatement(query);
			pst.setString(11, bookspane.getText());
			pst.setString(12, musicpane.getText());
			pst.setString(13, websitepane.getText());
			pst.setString(14,aboutpane.getText());
			pst.setString(15, lessonspane.getText());
			pst.setString(16, thingspane.getText());
			pst.setString(17, coursespane.getText());
			pst.setString(18, skillspane.getText());
			pst.setString(19, editorPane.getText());
			pst.setString(10, educationpane.getText());
			pst.setString(1, nameTF.getText());
			pst.setString(2, emailTF.getText());

			pst.setString(3, usernameTF.getText());
			//pst.setString(4, passField.getText());
			pst.setString(5, ageTF.getText());
			pst.setString(6, contactTF.getText());
			if (rdbtnMale.isSelected()) {
				v = rdbtnMale.getText().toString();
			} else if (rdbtnFemale.isSelected()) {
				v = rdbtnFemale.getText().toString();
			} else {
				JOptionPane.showMessageDialog(null, "Select Gender");
			}

			pst.setString(7, String.valueOf(v));
			pst.setString(8, heightTF.getText());
			pst.setString(9, addressCB.getSelectedItem().toString());
			pst.execute();
			pst.close();
			JOptionPane.showMessageDialog(null, "New Info Added");
			loadTable();
			resetEverything();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadToField() {
		try {
			int row = dataTable.getSelectedRow();
			String ID = (dataTable.getModel().getValueAt(row, 0)).toString();
			String query = "Select * FROM Employee WHERE E_ID= '" + ID + "'";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				
				 E_ID = rs.getInt("E_ID");
				
				 bookspane.setText(rs.getString("E_Books"));
				 musicpane.setText(rs.getString("E_Music"));
				 websitepane.setText(rs.getString("E_Website"));
				 aboutpane.setText(rs.getString("E_About"));
				lessonspane.setText(rs.getString("E_Lessons"));
				 thingspane.setText(rs.getString("E_Like"));
				coursespane.setText(rs.getString("E_Courses"));
				 skillspane.setText(rs.getString("E_Skills"));
				 editorPane.setText(rs.getString("E_Programming"));
				
				 educationpane.setText(rs.getString("E_Education"));
				nameTF.setText(rs.getString("E_Name"));
				emailTF.setText(rs.getString("E_Mail"));
				usernameTF.setText(rs.getString("E_Username"));
				//passField.setText(rs.getString("E_Password"));
				ageTF.setText(rs.getString("E_Age"));
				contactTF.setText(rs.getString("E_Contact"));
				v = rs.getString("E_Gender");
				if (v.equals("Male")) {
					rdbtnMale.setSelected(true);
					rdbtnFemale.setSelected(false);
				} else if (v.equals("Female")) {
					rdbtnFemale.setSelected(true);
					rdbtnMale.setSelected(false);
				} else {
					System.out.println("");
				}
				heightTF.setText(rs.getString("E_Height"));

				combo = rs.getString("E_Adress");

				if (combo.equals("Dhaka")) {
					addressCB.setSelectedItem("Dhaka");
				} else if (combo.equals("Rajshahi")) {
					addressCB.setSelectedItem("Rajshahi");
				} else if (combo.equals("Barishal")) {
					addressCB.setSelectedItem("Barishal");
				} else if (combo.equals("Sylhet")) {
					addressCB.setSelectedItem("Sylhet");
				} else {
					System.out.println("");
				}

			}
			pst.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateData() {
		try {
			if (rdbtnMale.isSelected()) {
				v = rdbtnMale.getText().toString();

			} else if (rdbtnFemale.isSelected()) {
				v = rdbtnFemale.getText().toString();

			}

			String query = "UPDATE Employee SET E_Name = '" + nameTF.getText() + "',E_Books = '" + bookspane.getText() + "', E_Skills = '" + skillspane.getText() + "',E_Music = '" + musicpane.getText() + "',E_Website = '" + websitepane.getText() + "',E_About = '" + aboutpane.getText() + "',E_Lessons = '" + lessonspane.getText() + "',E_Like = '" + thingspane.getText() + "',E_Courses = '" + coursespane.getText() + "',E_Programming = '" + editorPane.getText() + "',E_Education = '" + educationpane.getText() + "',E_Mail='" + emailTF.getText()
					+ "',E_Username= '" + usernameTF.getText() + "',E_Age ='"
					+ ageTF.getText() + "',E_Contact ='" + contactTF.getText() + "',E_Gender ='" + v + "',E_Height ='"
					+ heightTF.getText() + "',E_Adress ='" + addressCB.getSelectedItem().toString() + "'WHERE E_ID ='"
					+ E_ID + "'";

			pst = conn.prepareStatement(query);
			pst.execute();

			JOptionPane.showMessageDialog(null, "Data updated Successfully");
			pst.close();
			resetEverything();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private void deleteData() {
		try {

			int action = JOptionPane.showConfirmDialog(null, "Are You Sure To Delete?", "Delete",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (action == 0) {
				String query = "DELETE FROM Employee WHERE E_ID ='" + E_ID + "'";
				pst = conn.prepareStatement(query);
				pst.execute();
				JOptionPane.showMessageDialog(null, "Data Deleted Successfully");
				pst.close();
				resetEverything();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	private void searchData() {

		try {

			String selection = (String) searchCB.getSelectedItem();

			String query = "SELECT E_ID, E_Name,E_Mail,E_Username,E_Age,E_Contact,E_Gender FROM Employee WHERE "
					+ selection + " LIKE '" + searchTF.getText() + "%'";

			pst = conn.prepareStatement(query);
			// pst.setString(1, searchTF.getText());
			rs = pst.executeQuery();
			dataTable.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	private void resetEverything() {

		nameTF.setText(null);
		emailTF.setText(null);
		usernameTF.setText(null);
		//passField.setText(null);
		ageTF.setText(null);
		contactTF.setText(null);
		buttonGroup.clearSelection();
		heightTF.setText(null);
		addressCB.setSelectedItem("-");
		searchCB.setSelectedItem("-");
		searchTF.setText(null);
		bookspane.setText(null);
		 musicpane.setText(null);
		 websitepane.setText(null);
		 aboutpane.setText(null);
		lessonspane.setText(null);
		 thingspane.setText(null);
		coursespane.setText(null);
		 skillspane.setText(null);
		 editorPane.setText(null);
		 educationpane.setText(null);
		

		loadTable();
	}

	private void loadCB() {
		try {

			String query = "SELECT E_Name FROM Employee";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {
				dataCB.addItem(rs.getString("E_Name"));

			}
			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void loadList() {

		try {
			DefaultListModel dlm = new DefaultListModel();

			String query = "SELECT E_Adress FROM Employee";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			while (rs.next()) {

				dlm.addElement(rs.getString("E_Adress"));

			}
			pst.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void design() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1332, 746);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 62, 1281, 59);
		contentPane.add(scrollPane);

		dataTable = new JTable();
		dataTable.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dataTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadToField();
			}

		});
		scrollPane.setViewportView(dataTable);

		nameTF = new JTextField();
		nameTF.setBackground(SystemColor.control);
		nameTF.setForeground(new Color(0, 0, 0));
		nameTF.setHorizontalAlignment(SwingConstants.CENTER);
		nameTF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		nameTF.setColumns(10);
		nameTF.setBounds(129, 181, 176, 28);
		contentPane.add(nameTF);

		emailTF = new JTextField();
		emailTF.setBackground(SystemColor.control);
		emailTF.setHorizontalAlignment(SwingConstants.CENTER);
		emailTF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		emailTF.setColumns(10);
		emailTF.setBounds(444, 180, 176, 28);
		contentPane.add(emailTF);

		usernameTF = new JTextField();
		usernameTF.setHorizontalAlignment(SwingConstants.CENTER);
		usernameTF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		usernameTF.setColumns(10);
		usernameTF.setBounds(1111, 294, 176, 28);
		contentPane.add(usernameTF);

		ageTF = new JTextField();
		ageTF.setHorizontalAlignment(SwingConstants.CENTER);
		ageTF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		ageTF.setColumns(10);
		ageTF.setBounds(1115, 345, 92, 23);
		contentPane.add(ageTF);

		heightTF = new JTextField();
		heightTF.setHorizontalAlignment(SwingConstants.CENTER);
		heightTF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		heightTF.setColumns(10);
		heightTF.setBounds(1115, 388, 167, 28);
		contentPane.add(heightTF);

		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setFont(new Font("Trajan Pro", Font.BOLD, 13));
		buttonGroup.add(rdbtnMale);
		rdbtnMale.setBounds(1001, 185, 65, 23);
		contentPane.add(rdbtnMale);

		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setFont(new Font("Trajan Pro", Font.BOLD, 13));
		buttonGroup.add(rdbtnFemale);
		rdbtnFemale.setBounds(1068, 185, 77, 23);
		contentPane.add(rdbtnFemale);

		contactTF = new JTextField();
		contactTF.setBackground(SystemColor.control);
		contactTF.setHorizontalAlignment(SwingConstants.CENTER);
		contactTF.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contactTF.setColumns(10);
		contactTF.setBounds(766, 182, 114, 28);
		contentPane.add(contactTF);

		addressCB = new JComboBox();
		addressCB.setFont(new Font("Tahoma", Font.PLAIN, 14));
		addressCB.setModel(new DefaultComboBoxModel(new String[] { "-", "Dhaka", "Rajshahi", "Sylhet", "Barishal" }));
		addressCB.setBounds(1115, 439, 139, 33);
		contentPane.add(addressCB);

		btnAdd = new JButton("Add");
		btnAdd.setIcon(new ImageIcon(Management.class.getResource("/photo/Button-Add-icon.png")));
		btnAdd.setForeground(new Color(0, 0, 255));
		btnAdd.setFont(new Font("Sitka Small", Font.BOLD, 18));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addData();
			}
		});
		btnAdd.setBounds(1163, 641, 143, 40);
		getContentPane().add(btnAdd);

		btnUpdate = new JButton("Update");
		btnUpdate.setIcon(new ImageIcon(Management.class.getResource("/photo/edit-file-icon.png")));
		btnUpdate.setForeground(new Color(50, 205, 50));
		btnUpdate.setFont(new Font("Sitka Small", Font.BOLD, 18));
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateData();
			}
		});
		btnUpdate.setBounds(986, 590, 159, 40);
		contentPane.add(btnUpdate);

		btnDelete = new JButton("Delete");
		btnDelete.setIcon(new ImageIcon(Management.class.getResource("/photo/delete-file-icon.png")));
		btnDelete.setForeground(new Color(255, 0, 0));
		btnDelete.setFont(new Font("Sitka Text", Font.BOLD, 18));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteData();
			}
		});
		btnDelete.setBounds(986, 641, 159, 40);
		contentPane.add(btnDelete);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setIcon(new ImageIcon(Management.class.getResource("/photo/usernnnn - Copy.png")));
		lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		lblNewLabel.setBounds(12, 181, 84, 33);
		contentPane.add(lblNewLabel);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setIcon(new ImageIcon(Management.class.getResource("/photo/Mail-icon.png")));
		lblEmail.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		lblEmail.setBounds(315, 177, 84, 32);
		contentPane.add(lblEmail);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setIcon(new ImageIcon(Management.class.getResource("/photo/Office-Customer-Male-Light-icon.png")));
		lblUsername.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		lblUsername.setBounds(996, 294, 116, 28);
		contentPane.add(lblUsername);

		JLabel lblAge = new JLabel("Age");
		lblAge.setIcon(new ImageIcon(Management.class.getResource("/photo/Age-Child-Male-Light-icon.png")));
		lblAge.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		lblAge.setBounds(1001, 341, 104, 28);
		contentPane.add(lblAge);

		JLabel lblContact = new JLabel("Contact");
		lblContact.setIcon(new ImageIcon(Management.class.getResource("/photo/phone-icon - Copy.png")));
		lblContact.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		lblContact.setBounds(652, 181, 104, 28);
		contentPane.add(lblContact);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setIcon(new ImageIcon(Management.class.getResource("/photo/Actions-user-group-new-icon.png")));
		lblGender.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		lblGender.setBounds(902, 181, 104, 28);
		contentPane.add(lblGender);

		JLabel lblHeight = new JLabel("Height");
		lblHeight.setIcon(new ImageIcon(Management.class.getResource("/photo/Height-Window-icon.png")));
		lblHeight.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		lblHeight.setBounds(1001, 388, 104, 28);
		contentPane.add(lblHeight);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setIcon(new ImageIcon(Management.class.getResource("/photo/Actions-go-home-icon.png")));
		lblAddress.setFont(new Font("Segoe UI Black", Font.BOLD, 14));
		lblAddress.setBounds(1001, 441, 115, 28);
		contentPane.add(lblAddress);

		searchCB = new JComboBox();
		searchCB.setBackground(SystemColor.activeCaption);
		searchCB.setForeground(new Color(255, 255, 255));
		searchCB.setModel(new DefaultComboBoxModel(new String[] {"-", "E_Name", "E_Mail", "E_Username", "E_Age", "E_Contact", "E_Gender", "E_Height", "E_Adress"}));
		searchCB.setBounds(12, 14, 167, 40);
		contentPane.add(searchCB);

		searchTF = new JTextField();
		searchTF.setBackground(new Color(211, 211, 211));
		searchTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {

				searchData();

			}
		});
		searchTF.setBounds(332, 13, 162, 42);
		contentPane.add(searchTF);
		searchTF.setColumns(10);

		btnReset = new JButton("Reset");
		btnReset.setIcon(new ImageIcon(Management.class.getResource("/photo/Actions-edit-undo-icon.png")));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				resetEverything();
			}
		});
		btnReset.setForeground(new Color(0, 0, 0));
		btnReset.setFont(new Font("Sitka Small", Font.BOLD, 18));
		btnReset.setBounds(1163, 590, 143, 40);
		contentPane.add(btnReset);

		btnNewButton = new JButton("Logout");
		btnNewButton.setBackground(new Color(211, 211, 211));
		btnNewButton.setIcon(new ImageIcon(Management.class.getResource("/photo/Apps-Dialog-Logout-icon.png")));
		btnNewButton.setForeground(new Color(255, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				FirstJFrame frame = new FirstJFrame();
				frame.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(1163, 11, 143, 42);
		contentPane.add(btnNewButton);

		dataCB = new JComboBox();
		dataCB.setFont(new Font("Tahoma", Font.BOLD, 13));
		dataCB.setModel(new DefaultComboBoxModel(new String[] { "Select Name" }));
		dataCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				loadCBClick();
			}
		});

		dataCB.setBounds(1115, 473, -35, 35);
		contentPane.add(dataCB);
		
		JLabel lblSelect = new JLabel("Select");
		lblSelect.setForeground(new Color(25, 25, 112));
		lblSelect.setFont(new Font("Stencil", Font.BOLD, 15));
		lblSelect.setIcon(new ImageIcon(Management.class.getResource("/photo/Sign-Select-icon.png")));
		lblSelect.setBounds(189, 11, 130, 48);
		contentPane.add(lblSelect);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setForeground(new Color(25, 25, 112));
		lblSearch.setIcon(new ImageIcon(Management.class.getResource("/photo/Search-icon.png")));
		lblSearch.setFont(new Font("Stencil", Font.BOLD, 15));
		lblSearch.setBounds(507, 13, 128, 44);
		contentPane.add(lblSearch);
		
		JLabel label = new JLabel("Select");
		label.setBackground(new Color(95, 158, 160));
		label.setIcon(new ImageIcon(Management.class.getResource("/photo/Sign-Select-icon.png")));
		label.setForeground(new Color(72, 209, 204));
		label.setFont(new Font("Stencil", Font.BOLD, 15));
		label.setBounds(996, 472, -13, 40);
		contentPane.add(label);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(617, 137, 46, 15);
		contentPane.add(label_2);
		
		JLabel lblWelcomeToMy = new JLabel("Welcome to my portfolio");
		lblWelcomeToMy.setForeground(Color.BLACK);
		lblWelcomeToMy.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblWelcomeToMy.setBounds(307, 132, 328, 28);
		contentPane.add(lblWelcomeToMy);
		
		JLabel label_1 = new JLabel("Education");
		label_1.setForeground(new Color(153, 51, 51));
		label_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_1.setBounds(12, 225, 89, 24);
		contentPane.add(label_1);
		
		JLabel lblCurrentlyStudying = new JLabel("Currently Studying");
		lblCurrentlyStudying.setForeground(new Color(51, 153, 0));
		lblCurrentlyStudying.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCurrentlyStudying.setBounds(12, 260, 183, 14);
		contentPane.add(lblCurrentlyStudying);
		
		/*educationTF = new JTextField();
		educationTF.setBackground(new Color(204, 255, 204));
		educationTF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		educationTF.setColumns(10);
		educationTF.setBounds(5, 281, 205, 68);
		contentPane.add(educationTF);*/
		
		JLabel label_6 = new JLabel("");
		label_6.setBounds(5, 573, 114, 23);
		contentPane.add(label_6);
		
		lblBooksILike = new JLabel("Books I Like");
		lblBooksILike.setForeground(new Color(148, 0, 211));
		lblBooksILike.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblBooksILike.setBounds(12, 365, 89, 24);
		contentPane.add(lblBooksILike);
		
		/*booksTF = new JTextField();
		booksTF.setBackground(new Color(204, 204, 255));
		booksTF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		booksTF.setColumns(10);
		booksTF.setBounds(5, 387, 205, 91);
		contentPane.add(booksTF);*/
		
		lblMusicILike = new JLabel("Music I  Like");
		lblMusicILike.setForeground(new Color(85, 107, 47));
		lblMusicILike.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMusicILike.setBounds(12, 477, 89, 24);
		contentPane.add(lblMusicILike);
		
		/*musicTF = new JTextField();
		musicTF.setBackground(new Color(204, 255, 153));
		musicTF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		musicTF.setColumns(10);
		musicTF.setBounds(5, 513, 205, 66);
		contentPane.add(musicTF);*/
		
		lblWebsiteIUse = new JLabel("Website I Use Actively");
		lblWebsiteIUse.setForeground(new Color(128, 0, 128));
		lblWebsiteIUse.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblWebsiteIUse.setBounds(7, 580, 181, 24);
		contentPane.add(lblWebsiteIUse);
		
		/*websiteTF = new JTextField();
		websiteTF.setBackground(new Color(153, 255, 204));
		websiteTF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		websiteTF.setColumns(10);
		websiteTF.setBounds(5, 615, 205, 81);
		contentPane.add(websiteTF);*/
		
		lblLessoonPlan = new JLabel("Lessoon Plans");
		lblLessoonPlan.setForeground(new Color(199, 21, 133));
		lblLessoonPlan.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLessoonPlan.setBounds(332, 325, 130, 24);
		contentPane.add(lblLessoonPlan);
		
		/*lessonsTF = new JTextField();
		lessonsTF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lessonsTF.setColumns(10);
		lessonsTF.setBackground(new Color(173, 216, 230));
		lessonsTF.setBounds(254, 360, 291, 219);
		contentPane.add(lessonsTF);*/
		
		/*likeTF = new JTextField();
		likeTF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		likeTF.setColumns(10);
		likeTF.setBackground(new Color(204, 153, 255));
		likeTF.setBounds(300, 615, 205, 81);
		contentPane.add(likeTF);*/
		
		JLabel lblThingsILike = new JLabel("Things I Like to do");
		lblThingsILike.setForeground(new Color(240, 248, 255));
		lblThingsILike.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblThingsILike.setBounds(332, 590, 160, 24);
		contentPane.add(lblThingsILike);
		
		JLabel lblALittleAbout = new JLabel("A Little about myself");
		lblALittleAbout.setForeground(Color.RED);
		lblALittleAbout.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblALittleAbout.setBounds(316, 220, 176, 24);
		contentPane.add(lblALittleAbout);
		
		/*aboutTF = new JTextField();
		aboutTF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		aboutTF.setColumns(10);
		aboutTF.setBackground(new Color(144, 238, 144));
		aboutTF.setBounds(254, 256, 290, 66);
		contentPane.add(aboutTF);*/
		
		lblSkills = new JLabel("Skills");
		lblSkills.setForeground(new Color(204, 0, 102));
		lblSkills.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSkills.setBounds(622, 348, 183, 14);
		contentPane.add(lblSkills);
		
		/*skillsTF = new JTextField();
		skillsTF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		skillsTF.setColumns(10);
		skillsTF.setBackground(new Color(204, 255, 102));
		skillsTF.setBounds(622, 373, 205, 59);
		contentPane.add(skillsTF);*/
		
		/*programmingTF = new JTextField();
		programmingTF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		programmingTF.setColumns(10);
		programmingTF.setBackground(new Color(51, 255, 153));
		programmingTF.setBounds(619, 466, 205, 81);
		contentPane.add(programmingTF);*/
		
		lblProgrammingLanguage = new JLabel("Programming Languages");
		lblProgrammingLanguage.setForeground(new Color(153, 51, 51));
		lblProgrammingLanguage.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProgrammingLanguage.setBounds(617, 459, 193, 24);
		contentPane.add(lblProgrammingLanguage);
		
		/*coursesTF = new JTextField();
		coursesTF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		coursesTF.setColumns(10);
		coursesTF.setBackground(new Color(255, 255, 204));
		coursesTF.setBounds(619, 263, 205, 59);
		contentPane.add(coursesTF);*/
		
		lblRecentCoursesI = new JLabel("Recent Courses ");
		lblRecentCoursesI.setForeground(new Color(51, 0, 255));
		lblRecentCoursesI.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRecentCoursesI.setBounds(619, 238, 183, 14);
		contentPane.add(lblRecentCoursesI);
		
		JList list = new JList();
		list.setForeground(Color.BLACK);
		list.setFont(new Font("Tahoma", Font.PLAIN, 15));
		list.setBackground(new Color(211, 211, 211));
		list.setBounds(714, 22, -6, 15);
		contentPane.add(list);
		
		JLabel label_4 = new JLabel("");
		label_4.setIcon(new ImageIcon(Management.class.getResource("/Photo/gradient-background.jpg")));
		label_4.setBounds(103, 132, 721, 38);
		contentPane.add(label_4);
		
		editorPane = new JEditorPane();
		editorPane.setBackground(SystemColor.inactiveCaptionBorder);
		editorPane.setBounds(600, 501, 227, 157);
		contentPane.add(editorPane);
		
		skillspane = new JEditorPane();
		skillspane.setBackground(SystemColor.inactiveCaptionBorder);
		skillspane.setBounds(600, 376, 227, 65);
		contentPane.add(skillspane);
		
		coursespane = new JEditorPane();
		coursespane.setBackground(SystemColor.inactiveCaptionBorder);
		coursespane.setBounds(600, 263, 227, 59);
		contentPane.add(coursespane);
		
		thingspane = new JEditorPane();
		thingspane.setBackground(SystemColor.inactiveCaptionBorder);
		thingspane.setBounds(257, 623, 288, 59);
		contentPane.add(thingspane);
		
		lessonspane = new JEditorPane();
		lessonspane.setBackground(SystemColor.inactiveCaptionBorder);
		lessonspane.setBounds(255, 373, 289, 206);
		contentPane.add(lessonspane);
		
		aboutpane = new JEditorPane();
		aboutpane.setBackground(SystemColor.inactiveCaptionBorder);
		aboutpane.setBounds(257, 262, 296, 52);
		contentPane.add(aboutpane);
		
		websitepane = new JEditorPane();
		websitepane.setBackground(SystemColor.inactiveCaptionBorder);
		websitepane.setBounds(5, 615, 205, 68);
		contentPane.add(websitepane);
		
		musicpane = new JEditorPane();
		musicpane.setBackground(SystemColor.inactiveCaptionBorder);
		musicpane.setBounds(12, 513, 198, 50);
		contentPane.add(musicpane);
		
		bookspane = new JEditorPane();
		bookspane.setBackground(SystemColor.inactiveCaptionBorder);
		bookspane.setBounds(12, 392, 198, 65);
		contentPane.add(bookspane);
		
		educationpane = new JEditorPane();
		educationpane.setBackground(SystemColor.inactiveCaptionBorder);
		educationpane.setBounds(12, 285, 198, 69);
		contentPane.add(educationpane);
		
		btnChat = new JButton("Chat");
		btnChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Chat c = new Chat();
				c.setVisible(true);
				dispose();
			}
		});
		btnChat.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnChat.setBounds(1114, 535, 139, 28);
		contentPane.add(btnChat);
		
		label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon(Management.class.getResource("/Photo/Everest-Mountain-Powerpoint-Backgroundsggg.jpg")));
		label_3.setBounds(0, 0, 1316, 706);
		contentPane.add(label_3);
		
		label_5 = new JLabel("");
		label_5.setBounds(1001, 535, 101, 28);
		contentPane.add(label_5);
	}
}
