package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import javax.swing.text.MaskFormatter;

import SqlDB.ConexionDB;
import SqlDB.SQLInsert;
import logico.P_Administrador;
import logico.AlticeSystem;
import logico.Cliente;
import logico.Cuenta;
import logico.P_Trabajador;
import logico.Persona;

import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

public class RegistrarPersona extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField txtCedula;
	private JFormattedTextField txtTelefono;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtNacionalidad;
	private JComboBox<String> cbxCargo;
	private JTextField txtUser;
	private JLabel labelCedula;
	private JLabel labelTelefono;
	private JLabel labelApellido;
	private JLabel labelNacionalidad;
	private JLabel labelDireccion;
	private JLabel labelPersona;
	private JLabel labelPassword;
	private JPasswordField txtPassword = new JPasswordField();
	private Persona auxPersona = null;
	private JLabel lblNewLabel_1;
	private JTextField txtCodigo;
	private JComboBox<String> cbxGenero;
    private Cuenta cuenta = null;
    private JTextField txtApellido;
    private JLabel labelNombre;
    private JLabel labelGenero;
    private JLabel labelCargo;
    private JLabel lblTipo;

	Connection conn = ConexionDB.getConnection();
	private JTextField textSnombre;
	private JTextField textSapellido;
	private JLabel lblNombre_1;
	private JTextField textCalle;
	private JLabel lblNombre_2;
	private JTextField textCasa;
	private JLabel lblNombre_3;
	private JPanel panel_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarPersona dialog = new RegistrarPersona(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarPersona(Persona Persona) {
		auxPersona = Persona;
		setTitle("Registrar Usuario");
		if(auxPersona != null) {
			setTitle("Modificar Usuario");
		}
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 643,532);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.menu);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		ImageIcon logo = new ImageIcon("src/imagenes/download.jpg");
		setIconImage(logo.getImage());

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informaci\u00F3n Personal", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBackground(SystemColor.menu);
		panel.setBounds(12, 12, 565, 337);
		contentPanel.add(panel);
		MaskFormatter cedula = null;
		try {
			cedula = new MaskFormatter("###-#######-#");
			cedula.setPlaceholderCharacter('_');
		} catch (ParseException e) {}
		txtCedula = new JFormattedTextField(cedula);
		txtCedula.setColumns(10);
		txtCedula.setBounds(12, 147, 171, 22);
		if(auxPersona != null) {
			txtCedula.setEditable(false);
			txtCedula.setText(auxPersona.getCedula());
		}
		panel.add(txtCedula);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(12, 39, 171, 22);
		if(auxPersona != null) {
			txtNombre.setText(auxPersona.getNombre());
		}
		panel.add(txtNombre);
		
		MaskFormatter telefono = null;
		try {
			telefono = new MaskFormatter("###-###-####");
			telefono.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			e.printStackTrace();
		}
		txtTelefono = new JFormattedTextField(telefono);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(12, 198, 361, 22);
		if(auxPersona != null) {
			txtTelefono.setText(auxPersona.getTelefono());
		}
		panel.add(txtTelefono);

		JLabel lblTelfono = new JLabel("Tel\u00E9fono:");
		lblTelfono.setFont(new Font("Sitka Small", Font.BOLD, 11));
		lblTelfono.setBounds(12, 182, 109, 14);
		panel.add(lblTelfono);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Sitka Small", Font.BOLD, 11));
		lblNombre.setBounds(12, 23, 72, 14);
		panel.add(lblNombre);

		JLabel lblCdula = new JLabel("C\u00E9dula:");
		lblCdula.setFont(new Font("Sitka Small", Font.BOLD, 11));
		lblCdula.setBounds(12, 130, 46, 14);
		panel.add(lblCdula);

		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(12, 251, 361, 22);
		if(auxPersona != null) {
			txtDireccion.setText(auxPersona.getDireccion());
		}
		panel.add(txtDireccion);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setFont(new Font("Sitka Small", Font.BOLD, 11));
		lblDireccin.setBounds(12, 233, 83, 14);
		panel.add(lblDireccin);

		JLabel lblGnero = new JLabel("G\u00E9nero:");
		lblGnero.setFont(new Font("Sitka Small", Font.BOLD, 11));
		lblGnero.setBounds(407, 23, 46, 14);
		panel.add(lblGnero);

		cbxGenero = new JComboBox<String>();
		cbxGenero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbxGenero.setModel(new DefaultComboBoxModel(new String[] {"<Selecciona>", "Hombre", "Mujer"}));
		cbxGenero.setBounds(407, 39, 119, 22);
		if(auxPersona != null && auxPersona.getGenero().equalsIgnoreCase("Hombre")) {
			cbxGenero.setSelectedIndex(1);
			cbxGenero.setEnabled(false);
		}
		if(auxPersona != null && auxPersona.getGenero().equalsIgnoreCase("Mujer")) {
			cbxGenero.setSelectedIndex(2);
			cbxGenero.setEnabled(false);
		}
		panel.add(cbxGenero);

		txtNacionalidad = new JTextField();
		txtNacionalidad.setColumns(10);
		txtNacionalidad.setBounds(202, 147, 171, 22);
//		if(auxPersona != null) {
//			txtNacionalidad.setText(auxPersona.getNacionalidad());
//		}
		panel.add(txtNacionalidad);

		JLabel lblNacionalidad = new JLabel("Nacionalidad:");
		lblNacionalidad.setFont(new Font("Sitka Small", Font.BOLD, 11));
		lblNacionalidad.setBounds(202, 130, 119, 14);
		panel.add(lblNacionalidad);



		cbxCargo = new JComboBox<String>();
		cbxCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxCargo.getSelectedIndex()==3) {
					panel_1.setVisible(false);
				}
			}
		});
		cbxCargo.setForeground(SystemColor.textText);
		cbxCargo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbxCargo.setModel(new DefaultComboBoxModel(new String[] {"<Selecciona>", "Administrador", "Trabajador","Cliente"}));
		cbxCargo.setSelectedIndex(0);
		cbxCargo.setBounds(407, 91, 119, 22);
		
		if(auxPersona != null && auxPersona instanceof Cliente){
			cbxCargo.setSelectedIndex(3);
		}
		if(auxPersona != null && auxPersona instanceof P_Administrador) {
			cbxCargo.setSelectedIndex(1);
		}
		if(auxPersona != null && auxPersona instanceof P_Trabajador) {
			cbxCargo.setSelectedIndex(2);
		}
		
		if(cbxCargo.getSelectedIndex()==3) {
			panel_1.setEnabled(false);
		}
		panel.add(cbxCargo);

		lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Sitka Small", Font.BOLD, 11));
		lblTipo.setBounds(407, 74, 46, 14);
		if(auxPersona != null && auxPersona instanceof Cliente){
			lblTipo.setVisible(false);
		}
		panel.add(lblTipo);
		
		labelCedula = new JLabel("*");
		labelCedula.setForeground(Color.RED);
		labelCedula.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelCedula.setBounds(184, 150, 20, 16);
		labelCedula.setVisible(false);
		panel.add(labelCedula);
		
		labelApellido = new JLabel("*");
		labelApellido.setForeground(Color.RED);
		labelApellido.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelApellido.setBounds(373, 42, 20, 16);
		labelApellido.setVisible(false);
		panel.add(labelApellido);
		
		labelTelefono = new JLabel("*");
		labelTelefono.setForeground(Color.RED);
		labelTelefono.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelTelefono.setBounds(373, 201, 20, 16);
		labelTelefono.setVisible(false);
		panel.add(labelTelefono);
		
		labelNacionalidad = new JLabel("*");
		labelNacionalidad.setForeground(Color.RED);
		labelNacionalidad.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelNacionalidad.setBounds(373, 150, 20, 16);
		labelNacionalidad.setVisible(false);
		panel.add(labelNacionalidad);
		
		labelDireccion = new JLabel("*");
		labelDireccion.setForeground(Color.RED);
		labelDireccion.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelDireccion.setBounds(373, 254, 20, 16);
		labelDireccion.setVisible(false);
		panel.add(labelDireccion);
	
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(202, 39, 171, 22);
		if(auxPersona != null) {
			txtApellido.setText(auxPersona.getApellido());
		}
		panel.add(txtApellido);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setFont(new Font("Dialog", Font.BOLD, 11));
		lblApellido.setBounds(202, 23, 72, 14);
		panel.add(lblApellido);
		
		labelNombre = new JLabel("*");
		labelNombre.setForeground(Color.RED);
		labelNombre.setVisible(false);
		labelNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelNombre.setBounds(184, 42, 56, 16);
		panel.add(labelNombre);
		
		labelGenero = new JLabel("*");
		labelGenero.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelGenero.setForeground(Color.RED);
		labelGenero.setVisible(false);
		labelGenero.setBounds(532, 42, 56, 16);
		panel.add(labelGenero);
		
		labelCargo = new JLabel("*");
		labelCargo.setForeground(Color.RED);
		labelCargo.setVisible(false);
		labelCargo.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelCargo.setBounds(532, 94, 56, 16);
		panel.add(labelCargo);
		
		textSnombre = new JTextField();
		textSnombre.setColumns(10);
		textSnombre.setBounds(12, 88, 171, 22);
		panel.add(textSnombre);
		
		JLabel lblSnombre = new JLabel("2do Nombre:");
		lblSnombre.setFont(new Font("Sitka Small", Font.BOLD, 11));
		lblSnombre.setBounds(12, 72, 119, 14);
		panel.add(lblSnombre);
		
		textSapellido = new JTextField();
		textSapellido.setColumns(10);
		textSapellido.setBounds(202, 90, 171, 22);
		panel.add(textSapellido);
		
		lblNombre_1 = new JLabel("2do Apellido:");
		lblNombre_1.setFont(new Font("Sitka Small", Font.BOLD, 11));
		lblNombre_1.setBounds(202, 74, 72, 14);
		panel.add(lblNombre_1);
		
		textCalle = new JTextField();
		textCalle.setColumns(10);
		textCalle.setBounds(12, 300, 171, 22);
		panel.add(textCalle);
		
		lblNombre_2 = new JLabel("Calle:");
		lblNombre_2.setFont(new Font("Sitka Small", Font.BOLD, 11));
		lblNombre_2.setBounds(12, 284, 72, 14);
		panel.add(lblNombre_2);
		
		textCasa = new JTextField();
		textCasa.setColumns(10);
		textCasa.setBounds(202, 300, 171, 22);
		panel.add(textCasa);
		
		lblNombre_3 = new JLabel("Casa:");
		lblNombre_3.setFont(new Font("Sitka Small", Font.BOLD, 11));
		lblNombre_3.setBounds(202, 284, 72, 14);
		panel.add(lblNombre_3);
		
		JComboBox<String> cbxCiudad = new JComboBox<String>();
		//cbxCiudad.setSelectedIndex(0);
		cbxCiudad.setForeground(SystemColor.textText);
		cbxCiudad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbxCiudad.setBounds(407, 147, 119, 22);
		panel.add(cbxCiudad);
		
		JLabel lblTipo_1 = new JLabel("Ciudad Nacimiento:");
		lblTipo_1.setFont(new Font("Sitka Small", Font.BOLD, 11));
		lblTipo_1.setBounds(407, 130, 119, 14);
		panel.add(lblTipo_1);
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Crear Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.menu);
		panel_1.setBounds(12, 360, 565, 85);
		if(auxPersona != null && auxPersona instanceof Cliente){
			panel_1.setVisible(false);
		}
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setBounds(12, 28, 111, 16);
		panel_1.add(lblNewLabel);
		
		txtUser = new JTextField();
		txtUser.setBounds(12, 50, 187, 22);
		
		if(auxPersona != null && auxPersona instanceof P_Administrador) {
			txtUser.setText(((P_Administrador) auxPersona).getCuenta().getUsuario());
		}
		if(auxPersona != null && auxPersona instanceof P_Trabajador) {
			txtUser.setText(((P_Trabajador) auxPersona).getCuenta().getUsuario());
		}
		
		panel_1.add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblNewLabel2 = new JLabel("Contrase\u00F1a:");
		lblNewLabel2.setBounds(294, 28, 121, 16);
		panel_1.add(lblNewLabel2);
		
		labelPersona = new JLabel("*");
		labelPersona.setForeground(Color.RED);
		labelPersona.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelPersona.setBounds(204, 53, 20, 16);
		labelPersona.setVisible(false);
		panel_1.add(labelPersona);
		
		labelPassword = new JLabel("*");
		labelPassword.setForeground(Color.RED);
		labelPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelPassword.setBounds(485, 53, 20, 16);
		labelPassword.setVisible(false);
		panel_1.add(labelPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(294, 50, 187, 22);
		
 		if(auxPersona != null && auxPersona instanceof P_Administrador) {
			txtPassword.setText(((P_Administrador) auxPersona).getCuenta().getPassword());
		}
 		if(auxPersona != null && auxPersona instanceof P_Trabajador) {
			txtPassword.setText(((P_Trabajador) auxPersona).getCuenta().getPassword());
		}
		
		panel_1.add(txtPassword);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(UIManager.getColor("Button.background"));
			buttonPane.setForeground(Color.WHITE);
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnRegistrar = new JButton("Registrar");
			btnRegistrar.setForeground(Color.GRAY);
			if(auxPersona != null) {
				btnRegistrar.setText("Modificar");
			}
			btnRegistrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					boolean Persona = AlticeSystem.getInstance().PersonaExiste(txtUser.getText(), auxPersona);
					String password = new String(txtPassword.getPassword());
					if(!Persona) {
						if(auxPersona == null) {
							if(validarCampos()) {
								JOptionPane.showConfirmDialog(null, "Por favor llenar los campos obligatorios", "Error", JOptionPane.WARNING_MESSAGE);
							}
							else {
								Persona aux = null;
								cuenta = new Cuenta(password, txtUser.getText());
								
								if(cbxCargo.getSelectedIndex()==1) {
									aux = new P_Administrador(txtCedula.getText(), txtNombre.getText(),txtApellido.getText(),textSnombre.getText(),textSapellido.getText(),cbxGenero.getSelectedItem().toString(), txtTelefono.getText(),cbxCiudad.getSelectedItem().toString(),textCalle.getText(), textCasa.getText(),txtDireccion.getText());

									
								}
								if(cbxCargo.getSelectedIndex()== 2) {
									aux = new P_Trabajador(txtCedula.getText(), txtNombre.getText(),txtApellido.getText(),textSnombre.getText(),textSapellido.getText(),cbxGenero.getSelectedItem().toString(), txtTelefono.getText(),cbxCiudad.getSelectedItem().toString(),textCalle.getText(), textCasa.getText(),txtDireccion.getText());
								}
								
								if(cbxCargo.getSelectedIndex()== 3) {
									aux = new Cliente(txtCedula.getText(), txtNombre.getText(),txtApellido.getText(),textSnombre.getText(),textSapellido.getText(),cbxGenero.getSelectedItem().toString(), txtTelefono.getText(),cbxCiudad.getSelectedItem().toString(),textCalle.getText(), textCasa.getText(),txtDireccion.getText());
								}
								AlticeSystem.getInstance().insertarPersona(aux);
								AlticeSystem.getInstance().addUser(txtCedula.getText(), cuenta);
								

								JOptionPane.showMessageDialog(null, "Registro Exitoso", "Informacion", JOptionPane.INFORMATION_MESSAGE);
								clean();
							}
						}
						else {
							if(validarCampos()) {
								JOptionPane.showConfirmDialog(null, "Por favor llenar los campos obligatorios", "Error", JOptionPane.WARNING_MESSAGE);
							}
							else {
								auxPersona.setNombre(txtNombre.getText());
								auxPersona.setDireccion(txtDireccion.getText());
								//auxPersona.setNacionalidad(txtNacionalidad.getText());
								auxPersona.setTelefono(txtTelefono.getText());
								auxPersona.setApellido(txtApellido.getText());
								if(auxPersona instanceof P_Administrador) {
									((P_Administrador) auxPersona).addCuenta(cuenta);
								}
								if(auxPersona instanceof P_Trabajador) {
									((P_Trabajador) auxPersona).addCuenta(cuenta);
								}
//								if(cbxCargo.getSelectedIndex() == 0) {
//									auxPersona.setTipo(cbxCargo.getSelectedItem().toString());
//								}
//								if(cbxCargo.getSelectedIndex() == 1) {
//									auxPersona.setTipo(cbxCargo.getSelectedItem().toString());
//								}
								AlticeSystem.getInstance().modificarPersona(auxPersona, cuenta);
								dispose();
							}
						}
					}
					else if(Persona) {
						JOptionPane.showMessageDialog(null, "Este Usuario ya Existe", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnRegistrar.setActionCommand("OK");
			buttonPane.add(btnRegistrar);
			{
				
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setForeground(Color.GRAY);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
	}
	private void clean() {
	    txtCedula.setValue(null);
		txtNombre.setText("");
		txtNacionalidad.setText("");
		txtTelefono.setValue(null);
		txtDireccion.setText("");
		txtUser.setText("");
		txtPassword.setText("");
		txtApellido.setText("");
		cbxGenero.setSelectedIndex(0);
		cbxCargo.setSelectedIndex(0);
	}
	
	private boolean validarCampos(){
		boolean validar = false;
		
		if(auxPersona == null) {
			if(!txtCedula.isEditValid()) {
				labelCedula.setVisible(true);
				validar = true;
			}
			else {
				labelCedula.setVisible(false);
			}
			
			if(!txtTelefono.isEditValid()) {
				labelTelefono.setVisible(true);
				validar = true;
			}
			else {
				labelTelefono.setVisible(false);
			}
			
			if(txtNombre.getText().trim().isEmpty()) {
				labelNombre.setVisible(true);
				validar = true;
			}
			else {
				labelNombre.setVisible(false);
			}
			
			if(txtApellido.getText().trim().isEmpty()) {
				labelApellido.setVisible(true);
				validar = true;
			}
			else {
				labelApellido.setVisible(false);
			}
			
			if(txtNacionalidad.getText().trim().isEmpty()) {
				labelNacionalidad.setVisible(true);
				validar = true;
			}
			else {
				labelNacionalidad.setVisible(false);
			}
			
			if(txtDireccion.getText().trim().isEmpty()) {
				labelDireccion.setVisible(true);
				validar = true;
			}
			else {
				labelDireccion.setVisible(false);
			}
			
			if(txtUser.getText().trim().isEmpty()) {
				labelPersona.setVisible(true);
				validar = true;
			}
			else {
				labelPersona.setVisible(false);
			}
			
			if(txtPassword.getPassword().length == 0) {
				labelPassword.setVisible(true);
				validar = true;
			}
			else {
				labelPassword.setVisible(false);
			}
			
			if(cbxGenero.getSelectedIndex() == 0) {
				labelGenero.setVisible(true);
				validar = true;
			}
			else {
				labelGenero.setVisible(false);
			}
			
			if(cbxCargo.getSelectedIndex() == 0) {
				labelCargo.setVisible(true);
				validar = true;
			}
			else {
				labelCargo.setVisible(false);
			}
		}
		
		if(auxPersona != null && auxPersona instanceof P_Administrador || auxPersona instanceof P_Trabajador) {
			if(!txtCedula.isEditValid()) {
				labelCedula.setVisible(true);
				validar = true;
			}
			else {
				labelCedula.setVisible(false);
			}
			
			if(!txtTelefono.isEditValid()) {
				labelTelefono.setVisible(true);
				validar = true;
			}
			else {
				labelTelefono.setVisible(false);
			}
			
			if(txtNombre.getText().trim().isEmpty()) {
				labelNombre.setVisible(true);
				validar = true;
			}
			else {
				labelNombre.setVisible(false);
			}
			
			if(txtApellido.getText().trim().isEmpty()) {
				labelApellido.setVisible(true);
				validar = true;
			}
			else {
				labelApellido.setVisible(false);
			}
			
			if(txtNacionalidad.getText().trim().isEmpty()) {
				labelNacionalidad.setVisible(true);
				validar = true;
			}
			else {
				labelNacionalidad.setVisible(false);
			}
			
			if(txtDireccion.getText().trim().isEmpty()) {
				labelDireccion.setVisible(true);
				validar = true;
			}
			else {
				labelDireccion.setVisible(false);
			}
			
			if(txtUser.getText().trim().isEmpty()) {
				labelPersona.setVisible(true);
				validar = true;
			}
			else {
				labelPersona.setVisible(false);
			}
			
			if(txtPassword.getPassword().length == 0) {
				labelPassword.setVisible(true);
				validar = true;
			}
			else {
				labelPassword.setVisible(false);
			}
			
			if(cbxGenero.getSelectedIndex() == 0) {
				labelGenero.setVisible(true);
				validar = true;
			}
			else {
				labelGenero.setVisible(false);
			}
			
			if(cbxCargo.getSelectedIndex() == 0) {
				labelCargo.setVisible(true);
				validar = true;
			}
			else {
				labelCargo.setVisible(false);
			}
		}
		
		if(auxPersona != null && auxPersona instanceof Cliente) {
			if(!txtCedula.isEditValid()) {
				labelCedula.setVisible(true);
				validar = true;
			}
			else {
				labelCedula.setVisible(false);
			}
			
			if(!txtTelefono.isEditValid()) {
				labelTelefono.setVisible(true);
				validar = true;
			}
			else {
				labelTelefono.setVisible(false);
			}
			
			if(txtNombre.getText().trim().isEmpty()) {
				labelNombre.setVisible(true);
				validar = true;
			}
			else {
				labelNombre.setVisible(false);
			}
			
			if(txtApellido.getText().trim().isEmpty()) {
				labelApellido.setVisible(true);
				validar = true;
			}
			else {
				labelApellido.setVisible(false);
			}
			
			if(txtNacionalidad.getText().trim().isEmpty()) {
				labelNacionalidad.setVisible(true);
				validar = true;
			}
			else {
				labelNacionalidad.setVisible(false);
			}
			
			if(txtDireccion.getText().trim().isEmpty()) {
				labelDireccion.setVisible(true);
				validar = true;
			}
			else {
				labelDireccion.setVisible(false);
			}
		
			if(cbxGenero.getSelectedIndex() == 0) {
				labelGenero.setVisible(true);
				validar = true;
			}
			else {
				labelGenero.setVisible(false);
			}
		}
		return validar;
	}
}