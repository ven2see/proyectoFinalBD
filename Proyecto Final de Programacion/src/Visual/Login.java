package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

import SqlDB.LlenarLista;
import logico.AlticeSystem;
import logico.Cuenta;
import logico.P_Administrador;
import logico.P_Trabajador;
import logico.Persona;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Login extends JFrame {

	private JPanel contentPane;
	private Dimension dim;
	private JTextField txtUsuario;
	private JPasswordField txtPassword;
	private JLabel labelVer;
	private JLabel labelNover;
	private JLabel labelFondo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
		
				LlenarLista listar = new LlenarLista();
				listar.listar();
				
				try {
					Login frame = new Login();
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
	public Login() {
		setFont(new Font("Tahoma", Font.BOLD, 15));
		setTitle("Altice");
		setResizable(false);
		setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		dim = getToolkit().getScreenSize();
		setSize(dim.width/2, dim.height/2);
		setLocationRelativeTo(null);
		ImageIcon logo = new ImageIcon("src/imagenes/download.jpg");
		setIconImage(logo.getImage());
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bienvenido");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(233, 74, 139, 39);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario:");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(189, 139, 56, 16);
		panel.add(lblNewLabel_1);
		
		txtUsuario = new JTextField();
		txtUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(txtUsuario.getText().equalsIgnoreCase("Ingresar Usuario")) {
					txtUsuario.setText("");
					txtUsuario.setForeground(Color.black);
				}
				if(String.valueOf(txtPassword.getPassword()).isEmpty()) {
					txtPassword.setEchoChar((char)0);
					txtPassword.setText("Ingresar Contraseña");
					txtPassword.setForeground(Color.gray);
				}
			}
		});
		txtUsuario.setForeground(Color.GRAY);
		txtUsuario.setText("Ingresar Usuario");
		txtUsuario.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtUsuario.setBounds(189, 162, 200, 23);
		panel.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(189, 189, 80, 16);
		panel.add(lblNewLabel_2);
		
		txtPassword = new JPasswordField();
		txtPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(String.valueOf(txtPassword.getPassword()).equalsIgnoreCase("Ingresar Contraseña")) {
					txtPassword.setText("");
					txtPassword.setForeground(Color.black);
					txtPassword.setEchoChar('●');
				}
				if(txtUsuario.getText().isEmpty()) {
					txtUsuario.setText("Ingresar Usuario");
					txtUsuario.setForeground(Color.gray);
				}
			}
		});
		txtPassword.setForeground(Color.GRAY);
		txtPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtPassword.setBounds(189, 210, 200, 23);
		txtPassword.setEchoChar((char)0);
		txtPassword.setText("Ingresar Contraseña");
		panel.add(txtPassword);
		
		JButton btnNewButton = new JButton("Iniciar Sesion");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String password = new String(txtPassword.getPassword());
				boolean confirmar = AlticeSystem.getInstance().confirmarLogin(txtUsuario.getText(), password);
				if(confirmar) {
					if(AlticeSystem.getLoginUser() instanceof P_Administrador) {
						Principal admin = new Principal();
						dispose();
						admin.setVisible(true);
					}
					else if(AlticeSystem.getLoginUser() instanceof P_Trabajador) {
						Principal trabajador = new Principal();
						dispose();
						trabajador.setVisible(true);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnNewButton.setBounds(222, 246, 130, 23);
		panel.add(btnNewButton);
		
		JCheckBox cbkPassword = new JCheckBox("");
		cbkPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbkPassword.isSelected()) {
					txtPassword.setEchoChar((char)0);
					labelVer.setVisible(false);
					labelNover.setVisible(true);
				}
				else {
					txtPassword.setEchoChar('●');
					labelVer.setVisible(true);
					labelNover.setVisible(false);
				}
			}
		});
		cbkPassword.setOpaque(false);
		cbkPassword.setBounds(397, 208, 80, 25);
		panel.add(cbkPassword);
		
		labelVer = new JLabel("");
		labelVer.setBounds(591, 306, 30, 23);
		ImageIcon ver = new ImageIcon("src/imagenes/passwordVer.jpg");
		Icon icon = new ImageIcon(ver.getImage().getScaledInstance(labelVer.getWidth(), labelVer.getHeight(), Image.SCALE_DEFAULT));
		labelVer.setIcon(icon);
		panel.add(labelVer);
		
		labelNover = new JLabel("");
		labelNover.setBounds(591, 306, 30, 23);
		ImageIcon noVer = new ImageIcon("src/imagenes/passwordNoVer.jpg");
		Icon icono = new ImageIcon(noVer.getImage().getScaledInstance(labelNover.getWidth(), labelNover.getHeight(), Image.SCALE_DEFAULT));
		labelNover.setIcon(icono);
		labelNover.setVisible(false);
		panel.add(labelNover);
		
		JLabel labelLogo = new JLabel("");
		labelLogo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelLogo.setBounds(358, 13, 200, 154);
		ImageIcon logoAl = new ImageIcon("src/imagenes/al.png");
		Icon ico2 = new ImageIcon(logoAl.getImage().getScaledInstance(labelLogo.getWidth(), labelLogo.getHeight(), Image.SCALE_DEFAULT));
		labelLogo.setIcon(ico2);
		panel.add(labelLogo);
		
		labelFondo = new JLabel("");
		labelFondo.setBounds(0, 0, 644, 335);
		ImageIcon fondo = new ImageIcon("src/imagenes/fondo.jpg");
		Icon ico = new ImageIcon(fondo.getImage().getScaledInstance(labelFondo.getWidth(), labelFondo.getHeight(), Image.SCALE_DEFAULT));
		labelFondo.setIcon(ico);
		panel.add(labelFondo);
		
	}
}
