import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Chat extends JFrame {

	private JPanel contentPane;
	private JTextField searchTF;
	private JComboBox searchCB;
	private JTable table;
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTable dataTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chat frame = new Chat();
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
	public Chat() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Management.class.getResource("/photo/edit-file-icon.png")));
		setTitle("Chat");
		design();
		conn = SQLConnection.ConnecrDb();
		loadTable();
		FirstJFrame fj = new FirstJFrame();
		
		//JOptionPane.showMessageDialog(null, "Welcome "+ fj.weLcomeName);
		
		searchData();
		
	}
	
	private void loadTable() {
		try {
			String query = "Select E_ID, E_Name FROM Employee";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void searchData() {

		try {

			String selection = (String) searchCB.getSelectedItem();

			String query = "SELECT E_ID, E_Name FROM Employee WHERE "
					+ selection + " LIKE '" + searchTF.getText() + "%'";

			pst = conn.prepareStatement(query);
			// pst.setString(1, searchTF.getText());
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();

		} catch (Exception e) {
			e.getStackTrace();
		}
	}
	
	
	private void design(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 924, 518);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JEditorPane displaypane = new JEditorPane();
		displaypane.setBounds(10, 23, 457, 273);
		contentPane.add(displaypane);
		
		JEditorPane textpane = new JEditorPane();
		textpane.setBounds(10, 350, 457, 101);
		contentPane.add(textpane);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(484, 402, 100, 49);
		contentPane.add(btnNewButton);
		
		searchTF = new JTextField();
		searchTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				searchData();
			}
		});
		searchTF.setColumns(10);
		searchTF.setBackground(new Color(211, 211, 211));
		searchTF.setBounds(671, 50, 128, 42);
		contentPane.add(searchTF);
		
		searchCB = new JComboBox();
		searchCB.setModel(new DefaultComboBoxModel(new String[] {"-", "E_Name"}));
		searchCB.setForeground(Color.WHITE);
		searchCB.setBackground(SystemColor.activeCaption);
		searchCB.setBounds(494, 51, 140, 40);
		contentPane.add(searchCB);
		
		JScrollPane tablepane = new JScrollPane();
		tablepane.setBounds(494, 113, 404, 143);
		contentPane.add(tablepane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tablepane.setViewportView(table);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(new Color(152, 251, 152));
		btnExit.setIcon(new ImageIcon(Chat.class.getResource("/Photo/Windows-Close-Program-icon.png")));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExit.setBounds(771, 408, 115, 38);
		contentPane.add(btnExit);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(new Color(144, 238, 144));
		btnBack.setIcon(new ImageIcon(Chat.class.getResource("/Photo/arrow-back-icon.png")));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Management p = new Management();
				p.setVisible(true);
				dispose();
			}
		});
		btnBack.setBounds(634, 407, 108, 40);
		contentPane.add(btnBack);
	}
}
