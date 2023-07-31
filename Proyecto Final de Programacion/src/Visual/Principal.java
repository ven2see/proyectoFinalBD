package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import imagenes.FondoPanel;

import logico.AlticeSystem;
import logico.P_Administrador;
import logico.P_Trabajador;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.Canvas;
import javax.swing.JToggleButton;

public class Principal extends JFrame {

	private Dimension dim;

	private JPanel contentPane;
	FondoPanel fondo = new FondoPanel();
	private JMenuItem menuItemControlUsuario;
	private JMenuItem menuItemRegUsuario;
	private JMenu menuUsuarios;
	private JMenu menuPlan;
	private JMenuItem menuItemRegPlan;
	private JMenuItem menuItemAdPlan;
	private JMenuItem menuItemListPlan;
	private JMenu menuReporte;
	private JPanel panelTrabajadores;
	private JLabel lblTra;
	private JLabel labelTrabajador;
	private JButton btnTrabajador;
	private JLabel lblTrabajadores;
	private JLabel labelClientes;
	private JPanel panelClientes;
	private JPanel panelAdmin;
	private JLabel lblAdministradores;
	private JLabel labelAdmin;
	private JButton btnAdmin;
	private JLabel lblAdministradoresEnEl;
	private JPanel panelFacturas;
	private JLabel Facturas;
	private JLabel labelFacturas;
	private JButton btnFacturas;
	private JLabel lblFacturasEnEl;
	private JPanel panelPlanes;
	private JLabel lblPlanes;
	private JLabel labelPlanes;
	private JButton btnPlanes;
	private JLabel lblPlanesEnEl;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setTitle("Altice");
		AlticeSystem.getInstance().generarFacturaPorFecha();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		dim = getToolkit().getScreenSize();
		setSize(dim.width, dim.height-35);
		setLocationRelativeTo(null);
		ImageIcon logo = new ImageIcon("src/imagenes/download.jpg");
		setIconImage(logo.getImage());

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuUsuarios = new JMenu("Usuarios");
		menuBar.add(menuUsuarios);

		menuItemControlUsuario = new JMenuItem("Control usuario");
		menuItemControlUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlUsuario vistaU = new ControlUsuario();
				vistaU.setVisible(true);
				cantSistema();
				//	txtMostrarUsuario.setText(txtCedula.getText());

			}
		});
		menuUsuarios.add(menuItemControlUsuario);

		menuItemRegUsuario = new JMenuItem("Registrar usuario");
		menuItemRegUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarPersona regis = new RegistrarPersona(null);
				regis.setVisible(true);
				cantSistema();
			}
		});
		menuUsuarios.add(menuItemRegUsuario);

		menuPlan = new JMenu("Planes");
		menuBar.add(menuPlan);

		menuItemRegPlan = new JMenuItem("Registrar Plan");
		menuItemRegPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarPlan plan = new RegistrarPlan(null);
				plan.setVisible(true);
				cantSistema();
			}
		});
		menuPlan.add(menuItemRegPlan);

		menuItemAdPlan = new JMenuItem("Adquirir Plan");
		menuItemAdPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdquirirPlan planAd = new AdquirirPlan();
				planAd.setVisible(true);
				cantSistema();
			}
		});
		menuPlan.add(menuItemAdPlan);

		menuItemListPlan = new JMenuItem("Listar Planes");
		menuItemListPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarPlanes listPlan = new ListarPlanes();
				listPlan.setVisible(true);
				cantSistema();
			}
		});
		menuPlan.add(menuItemListPlan);

		menuReporte = new JMenu("Reportes");

		menuBar.add(menuReporte);

		JMenuItem mntmNewMenuItem = new JMenuItem("Reporte Planes");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReportePlan reporte = new ReportePlan();
				reporte.setVisible(true);
				cantSistema();
			}
		});
		menuReporte.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Reporte Ingresos");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReporteIngresos reporteIng = new ReporteIngresos();
				reporteIng.setVisible(true);
				cantSistema();
			}
		});
		menuReporte.add(mntmNewMenuItem_1);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.WHITE);
		panelPrincipal.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(null);

		JButton btnCerrar = new JButton("Cerrar Sesion");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Guardar();
				Login login = new Login();
				dispose();
				login.setVisible(true);
			}
		});
		btnCerrar.setBounds(1762, 13, 118, 33);
		panelPrincipal.add(btnCerrar);

		panelClientes = new JPanel();
		panelClientes.setBackground(Color.GRAY);
		panelClientes.setBounds(200, 100, 240, 145);
		panelPrincipal.add(panelClientes);
		panelClientes.setLayout(null);

		JLabel lblNewLabel = new JLabel("Clientes");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(0, 0, 240, 23);
		panelClientes.add(lblNewLabel);

		labelClientes = new JLabel("");
		labelClientes.setBackground(new Color(240, 240, 240));
		labelClientes.setHorizontalAlignment(SwingConstants.CENTER);
		labelClientes.setForeground(Color.WHITE);
		labelClientes.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelClientes.setBounds(0, 57, 240, 23);
		panelClientes.add(labelClientes);

		JButton btnClientes = new JButton("Revisar Clientes");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlUsuario vistaU = new ControlUsuario();
				vistaU.setVisible(true);
				cantSistema();
			}
		});
		btnClientes.setBounds(0, 98, 240, 23);
		panelClientes.add(btnClientes);

		JLabel lblNewLabel_1 = new JLabel("Clientes en el sistema");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(0, 122, 240, 23);
		panelClientes.add(lblNewLabel_1);

		panelTrabajadores = new JPanel();
		panelTrabajadores.setLayout(null);
		panelTrabajadores.setBackground(Color.DARK_GRAY);
		panelTrabajadores.setBounds(817, 313, 240, 145);
		panelPrincipal.add(panelTrabajadores);

		lblTra = new JLabel("Trabajadores");
		lblTra.setOpaque(true);
		lblTra.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTra.setBackground(Color.LIGHT_GRAY);
		lblTra.setBounds(0, 0, 240, 23);
		panelTrabajadores.add(lblTra);

		labelTrabajador = new JLabel("");
		labelTrabajador.setHorizontalAlignment(SwingConstants.CENTER);
		labelTrabajador.setForeground(Color.WHITE);
		labelTrabajador.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelTrabajador.setBackground(SystemColor.menu);
		labelTrabajador.setBounds(0, 57, 240, 23);
		panelTrabajadores.add(labelTrabajador);

		btnTrabajador = new JButton("Revisar Trabajadores");
		btnTrabajador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlUsuario vistaU = new ControlUsuario();
				vistaU.setVisible(true);
				cantSistema();
			}
		});
		btnTrabajador.setBounds(0, 98, 240, 23);
		panelTrabajadores.add(btnTrabajador);

		lblTrabajadores = new JLabel("Trabajadores en el sistema");
		lblTrabajadores.setForeground(Color.WHITE);
		lblTrabajadores.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTrabajadores.setBounds(0, 122, 240, 23);
		panelTrabajadores.add(lblTrabajadores);

		panelAdmin = new JPanel();
		panelAdmin.setLayout(null);
		panelAdmin.setBackground(Color.GRAY);
		panelAdmin.setBounds(940, 100, 240, 145);
		panelPrincipal.add(panelAdmin);

		lblAdministradores = new JLabel("Administradores");
		lblAdministradores.setOpaque(true);
		lblAdministradores.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAdministradores.setBackground(Color.LIGHT_GRAY);
		lblAdministradores.setBounds(0, 0, 240, 23);
		panelAdmin.add(lblAdministradores);

		labelAdmin = new JLabel("");
		labelAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		labelAdmin.setForeground(Color.WHITE);
		labelAdmin.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelAdmin.setBackground(SystemColor.menu);
		labelAdmin.setBounds(0, 57, 240, 23);
		panelAdmin.add(labelAdmin);

		btnAdmin = new JButton("Revisar Administradores");
		btnAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlUsuario vistaU = new ControlUsuario();
				vistaU.setVisible(true);
				cantSistema();
			}
		});
		btnAdmin.setBounds(0, 98, 240, 23);
		panelAdmin.add(btnAdmin);

		lblAdministradoresEnEl = new JLabel("Administradores en el sistema");
		lblAdministradoresEnEl.setForeground(Color.WHITE);
		lblAdministradoresEnEl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAdministradoresEnEl.setBounds(0, 122, 240, 23);
		panelAdmin.add(lblAdministradoresEnEl);

		panelFacturas = new JPanel();
		panelFacturas.setLayout(null);
		panelFacturas.setBackground(Color.DARK_GRAY);
		panelFacturas.setBounds(570, 100, 240, 145);
		panelPrincipal.add(panelFacturas);

		Facturas = new JLabel("Facturas");
		Facturas.setOpaque(true);
		Facturas.setFont(new Font("Tahoma", Font.BOLD, 18));
		Facturas.setBackground(Color.LIGHT_GRAY);
		Facturas.setBounds(0, 0, 240, 23);
		panelFacturas.add(Facturas);

		labelFacturas = new JLabel("");
		labelFacturas.setHorizontalAlignment(SwingConstants.CENTER);
		labelFacturas.setForeground(Color.WHITE);
		labelFacturas.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelFacturas.setBackground(SystemColor.menu);
		labelFacturas.setBounds(0, 57, 240, 23);
		panelFacturas.add(labelFacturas);

		btnFacturas = new JButton("Revisar Facturas");
		btnFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ControlUsuario vistaU = new ControlUsuario();
				vistaU.setVisible(true);
				cantSistema();
			}
		});
		btnFacturas.setBounds(0, 98, 240, 23);
		panelFacturas.add(btnFacturas);

		lblFacturasEnEl = new JLabel("Facturas en el sistema");
		lblFacturasEnEl.setForeground(Color.WHITE);
		lblFacturasEnEl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFacturasEnEl.setBounds(0, 122, 240, 23);
		panelFacturas.add(lblFacturasEnEl);

		panelPlanes = new JPanel();
		panelPlanes.setLayout(null);
		panelPlanes.setBackground(Color.DARK_GRAY);
		panelPlanes.setBounds(330, 313, 240, 145);
		panelPrincipal.add(panelPlanes);

		lblPlanes = new JLabel("Planes");
		lblPlanes.setOpaque(true);
		lblPlanes.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPlanes.setBackground(Color.LIGHT_GRAY);
		lblPlanes.setBounds(0, 0, 240, 23);
		panelPlanes.add(lblPlanes);

		labelPlanes = new JLabel("");
		labelPlanes.setHorizontalAlignment(SwingConstants.CENTER);
		labelPlanes.setForeground(Color.WHITE);
		labelPlanes.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelPlanes.setBackground(SystemColor.menu);
		labelPlanes.setBounds(0, 57, 240, 23);
		panelPlanes.add(labelPlanes);

		btnPlanes = new JButton("Revisar Planes");
		btnPlanes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarPlanes listPlan = new ListarPlanes();
				listPlan.setVisible(true);
				cantSistema();
			}
		});
		btnPlanes.setBounds(0, 98, 240, 23);
		panelPlanes.add(btnPlanes);

		lblPlanesEnEl = new JLabel("Planes en el sistema");
		lblPlanesEnEl.setForeground(Color.WHITE);
		lblPlanesEnEl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPlanesEnEl.setBounds(0, 122, 240, 23);
		panelPlanes.add(lblPlanesEnEl);

		cantSistema();
		permisos();
	}

	public void permisos() {

		if(AlticeSystem.loginUser instanceof P_Trabajador) {
			menuItemRegUsuario.setVisible(false);
			menuItemRegPlan.setVisible(false);
			menuItemListPlan.setVisible(false);
			menuReporte.setVisible(false);
			panelAdmin.setVisible(false);
			panelPlanes.setVisible(false);
			panelTrabajadores.setVisible(false);
		}
	}

	public void cantSistema(){

		labelClientes.setText(String.valueOf(AlticeSystem.getInstance().cantPersonasByTipo()[0]));
		labelAdmin.setText(String.valueOf(AlticeSystem.getInstance().cantPersonasByTipo()[1]));
		labelTrabajador.setText(String.valueOf(AlticeSystem.getInstance().cantPersonasByTipo()[2]));
		labelFacturas.setText(String.valueOf(AlticeSystem.getInstance().getMisFacturas().size()));
		labelPlanes.setText(String.valueOf(AlticeSystem.getInstance().getMisPlanes().size()));
	}

	public void Guardar()
	{
		
	}
}