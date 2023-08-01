package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import logico.AlticeSystem;
import logico.Plan;


import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Font;
import javax.swing.JCheckBox;

public class RegistrarPlan extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPrecioInicial;
	private JTextField txtPrecioMensual;
	private JTextField txtVelocidad;
	private JTextField txtMinutos;
	private JTextField txtCanales;
	private JPanel panelTelefono;
	private JPanel panelInternet;
	private JPanel panelCable;
	private Plan auxPlan = null;
	private JLabel labelPrecioM;
	private JLabel labelPrecioIni;
	private JLabel labelVelocidad;
	private JLabel labelMinutos;
	private JLabel labelCanales;
	private JLabel lblNewLabel_5;
	private JTextField txtNombre;
	private JLabel labelNombre;
	private JCheckBox chxVelocidad;
	private JCheckBox chxMinutos;
	private JCheckBox chxCanales;
	private JTextField txtId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			RegistrarPlan dialog = new RegistrarPlan(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public RegistrarPlan(Plan plan) {
		auxPlan = plan;
		if(auxPlan == null){
			setTitle("Registrar Plan");
		}else {
			setTitle("Modificar Plan");
		}
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 545, 533);
		setLocationRelativeTo(null);
		ImageIcon logo = new ImageIcon("src/imagenes/download.jpg");
		setIconImage(logo.getImage());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panelInfo = new JPanel();
		panelInfo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informacion General", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelInfo.setBounds(10, 10, 510, 135);
		contentPanel.add(panelInfo);
		panelInfo.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Precio Inicial:");
			lblNewLabel.setBounds(12, 63, 87, 16);
			panelInfo.add(lblNewLabel);
		}
		
		txtPrecioInicial = new JTextField();
		txtPrecioInicial.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if((c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE) && (c != '.' || txtPrecioInicial.getText().contains("."))) {
					e.consume();
				}
			}
		});
		txtPrecioInicial.setBounds(121, 60, 200, 23);
		if(auxPlan != null) {
			txtPrecioInicial.setText(String.valueOf(auxPlan.getPrecioInicial()));;
		}
		
		panelInfo.add(txtPrecioInicial);
		txtPrecioInicial.setColumns(10);
		{
			JLabel lblNewLabel_1 = new JLabel("Precio Mensual:");
			lblNewLabel_1.setBounds(12, 92, 102, 16);
			panelInfo.add(lblNewLabel_1);
		}
		{
			txtPrecioMensual = new JTextField();
			txtPrecioMensual.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if((c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE) && (c != '.' || txtPrecioMensual.getText().contains("."))) {
						e.consume();
					}
				}
			});
			txtPrecioMensual.setBounds(121, 89, 200, 23);
			if(auxPlan != null) {
				txtPrecioMensual.setText(String.valueOf(auxPlan.getPrecioMensual()));;
			}
			panelInfo.add(txtPrecioMensual);
			txtPrecioMensual.setColumns(10);
		}
		
		labelPrecioIni = new JLabel("*");
		labelPrecioIni.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelPrecioIni.setVisible(false);
		labelPrecioIni.setForeground(Color.RED);
		labelPrecioIni.setBounds(324, 63, 56, 16);
		panelInfo.add(labelPrecioIni);
		
		labelPrecioM = new JLabel("*");
		labelPrecioM.setForeground(Color.RED);
		labelPrecioM.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelPrecioM.setVisible(false);
		labelPrecioM.setBounds(324, 92, 56, 16);
		panelInfo.add(labelPrecioM);
		
		lblNewLabel_5 = new JLabel("Nombre:");
		lblNewLabel_5.setBounds(12, 34, 56, 16);
		panelInfo.add(lblNewLabel_5);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(121, 31, 200, 23);
		if(auxPlan != null) {
			txtNombre.setText(auxPlan.getNombre());
		}
		panelInfo.add(txtNombre);
		txtNombre.setColumns(10);
		
		labelNombre = new JLabel("*");
		labelNombre.setForeground(Color.RED);
		labelNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		labelNombre.setVisible(false);
		labelNombre.setBounds(324, 34, 56, 16);
		panelInfo.add(labelNombre);
		
		JLabel lblNewLabel_6 = new JLabel("Id:");
		lblNewLabel_6.setBounds(365, 36, 45, 13);
		panelInfo.add(lblNewLabel_6);
		
		txtId = new JTextField();
		if(auxPlan != null) {
			txtId.setText(auxPlan.getId());
		}
		txtId.setBounds(385, 33, 96, 19);
		panelInfo.add(txtId);
		txtId.setColumns(10);
		{
			JPanel panelTipo = new JPanel();
			panelTipo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Servicios", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelTipo.setBounds(10, 150, 510, 295);
			contentPanel.add(panelTipo);
			panelTipo.setLayout(null);
			
			panelInternet = new JPanel();
			panelInternet.setBounds(12, 39, 469, 70);
			panelTipo.add(panelInternet);
			panelInternet.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Internet", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelInternet.setLayout(null);
			
			JLabel lblNewLabel_2 = new JLabel("Velocidad:");
			lblNewLabel_2.setBounds(10, 25, 76, 16);
			panelInternet.add(lblNewLabel_2);
			
			txtVelocidad = new JTextField();
			txtVelocidad.setEditable(false);
			txtVelocidad.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if((c < '0' || c > '9') && ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z'))) {
						e.consume();
					}
				}
			});
			txtVelocidad.setBounds(78, 25, 86, 20);
			panelInternet.add(txtVelocidad);
			txtVelocidad.setColumns(10);
			
			labelVelocidad = new JLabel("*");
			labelVelocidad.setFont(new Font("Tahoma", Font.BOLD, 13));
			labelVelocidad.setVisible(false);
			labelVelocidad.setForeground(Color.RED);
			labelVelocidad.setBounds(165, 25, 56, 16);
			panelInternet.add(labelVelocidad);
			
			chxVelocidad = new JCheckBox("A\u00F1adir");
			chxVelocidad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(chxVelocidad.isSelected()){
						txtVelocidad.setEditable(true);
					}
					else{
						txtVelocidad.setEditable(false);
					}
				}
			});
			chxVelocidad.setBounds(210, 21, 113, 25);
			panelInternet.add(chxVelocidad);
			
			panelCable = new JPanel();
			panelCable.setBounds(12, 200, 469, 70);
			panelTipo.add(panelCable);
			panelCable.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Television", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelCable.setLayout(null);
			
			JLabel lblNewLabel_4 = new JLabel("Canales:");
			lblNewLabel_4.setBounds(10, 25, 76, 16);
			panelCable.add(lblNewLabel_4);
			
			txtCanales = new JTextField();
			txtCanales.setEditable(false);
			txtCanales.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if((c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE)) {
						e.consume();
					}
				}
			});
			txtCanales.setBounds(78, 25, 86, 20);
			panelCable.add(txtCanales);
			txtCanales.setColumns(10);
			
			labelCanales = new JLabel("*");
			labelCanales.setForeground(Color.RED);
			labelCanales.setVisible(false);
			labelCanales.setFont(new Font("Tahoma", Font.BOLD, 13));
			labelCanales.setBounds(166, 25, 56, 16);
			panelCable.add(labelCanales);
			
			chxCanales = new JCheckBox("A\u00F1adir");
			chxCanales.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(chxCanales.isSelected()){
						txtCanales.setEditable(true);
					}
					else{
						txtCanales.setEditable(false);
					}
				}
			});
			chxCanales.setBounds(210, 21, 113, 25);
			panelCable.add(chxCanales);
			
			panelTelefono = new JPanel();
			panelTelefono.setBounds(12, 118, 469, 70);
			panelTipo.add(panelTelefono);
			panelTelefono.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Telefono", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelTelefono.setLayout(null);
			
			JLabel lblNewLabel_3 = new JLabel("Minutos:");
			lblNewLabel_3.setBounds(10, 25, 76, 16);
			panelTelefono.add(lblNewLabel_3);
			
			txtMinutos = new JTextField();
			txtMinutos.setEditable(false);
			txtMinutos.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char c = e.getKeyChar();
					if((c < '0' || c > '9') && (c != KeyEvent.VK_BACK_SPACE)) {
						e.consume();
					}
				}
			});
			txtMinutos.setBounds(78, 25, 86, 20);
			panelTelefono.add(txtMinutos);
			txtMinutos.setColumns(10);
			
			labelMinutos = new JLabel("*");
			labelMinutos.setFont(new Font("Tahoma", Font.BOLD, 13));
			labelMinutos.setVisible(false);
			labelMinutos.setForeground(Color.RED);
			labelMinutos.setBounds(166, 25, 56, 16);
			panelTelefono.add(labelMinutos);
			
			chxMinutos = new JCheckBox("A\u00F1adir");
			chxMinutos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(chxMinutos.isSelected()){
						txtMinutos.setEditable(true);
					}
					else{
						txtMinutos.setEditable(false);
					}

				}
			});
			chxMinutos.setBounds(210, 21, 113, 25);
			panelTelefono.add(chxMinutos);
		}
		if(auxPlan != null) {
			txtVelocidad.setText(auxPlan.getCantInternet());;
			txtMinutos.setText(auxPlan.getCantMinutos());;
			txtCanales.setText(auxPlan.getCantCanales());;
		}
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnRegistrar = new JButton("Registrar");
				if(auxPlan != null) {
					btnRegistrar.setText("Modificar");
				}
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(auxPlan == null) {
							if(verificarPlan()) {
								JOptionPane.showConfirmDialog(null, "El plan deber tener al menos dos servicios", "Error", JOptionPane.WARNING_MESSAGE);
							}
							else {
								if(validarCampos()) {
									JOptionPane.showConfirmDialog(null, "Por favor llenar los campos obligatorios", "Error", JOptionPane.WARNING_MESSAGE);
								}
								else {
									Plan p1 = null;
									String nom = txtNombre.getText();
									float precioIni = Float.valueOf(txtPrecioInicial.getText());
									float precioM = Float.valueOf(txtPrecioMensual.getText());
									p1 = new Plan(txtId.getText(),nom, txtCanales.getText(), txtMinutos.getText(), txtVelocidad.getText(), precioIni, precioM, "Habilitado");
									AlticeSystem.getInstance().insertarPlan(p1);
									JOptionPane.showMessageDialog(null, "Registro Exitoso", "Informacion", JOptionPane.INFORMATION_MESSAGE);
									clean();
								}
							}
						}
						else {
							if(verificarPlan()) {
								JOptionPane.showConfirmDialog(null, "El plan deber tener al menos dos servicios", "Error", JOptionPane.WARNING_MESSAGE);
							}
							else {
								if(validarCampos()) {
									JOptionPane.showConfirmDialog(null, "Por favor llenar los campos obligatorios", "Error", JOptionPane.WARNING_MESSAGE);
								}
								else {
								   auxPlan.setNombre(txtNombre.getText());
								   auxPlan.setPrecioInicial(Float.valueOf(txtPrecioInicial.getText()));
								   auxPlan.setPrecioMensual(Float.valueOf(txtPrecioMensual.getText()));
							       auxPlan.setCantInternet(txtVelocidad.getText());
								   auxPlan.setCantMinutos(txtMinutos.getText());
								   auxPlan.setCantCanales(txtCanales.getText());
								   AlticeSystem.getInstance().modificarPlan(auxPlan);
								   dispose();
								}
							}
					    }
					}
				});
				btnRegistrar.setActionCommand("OK");
				buttonPane.add(btnRegistrar);
				getRootPane().setDefaultButton(btnRegistrar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
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
		txtId.setText("");
	    txtPrecioInicial.setText("");
		txtPrecioMensual.setText("");
		txtVelocidad.setText("");
		txtMinutos.setText("");
		txtCanales.setText("");
		txtNombre.setText("");
		txtVelocidad.setEditable(false);
		txtMinutos.setEditable(false);
		txtCanales.setEditable(false);
		chxVelocidad.setSelected(false);
		chxMinutos.setSelected(false);
		chxCanales.setSelected(false);
	}
	
	private boolean verificarPlan() {
		
		boolean validar = false;
		
		if(txtVelocidad.getText().trim().isEmpty() && txtMinutos.getText().trim().isEmpty() && !txtCanales.getText().trim().isEmpty()) {
			validar = true;
		}
		if(txtVelocidad.getText().trim().isEmpty() && txtCanales.getText().trim().isEmpty() && !txtMinutos.getText().trim().isEmpty()) {
			validar = true;
		}
		if(txtCanales.getText().trim().isEmpty() && txtMinutos.getText().trim().isEmpty() && !txtVelocidad.getText().trim().isEmpty()) {
			validar = true;
		}
		if(txtVelocidad.getText().trim().isEmpty() && txtMinutos.getText().trim().isEmpty() && txtCanales.getText().trim().isEmpty()) {
			validar = true;
		}
		return validar;
		
	}
	private boolean validarCampos(){
		boolean validar = false;
		
		if(txtNombre.getText().trim().isEmpty()) {
			labelNombre.setVisible(true);
			validar = true;
		}
		else {
			labelNombre.setVisible(false);
		}
		
		if(txtPrecioInicial.getText().trim().isEmpty()) {
			labelPrecioIni.setVisible(true);
			validar = true;
		}
		else {
			labelPrecioIni.setVisible(false);
		}
		
		if(txtPrecioMensual.getText().trim().isEmpty()) {
			labelPrecioM.setVisible(true);
			validar = true;
		}
		else {
			labelPrecioM.setVisible(false);
		}
		
		if(txtVelocidad.getText().trim().isEmpty() && chxVelocidad.isSelected()) {
			labelVelocidad.setVisible(true);
			validar = true;
		}
		else {
			labelVelocidad.setVisible(false);
		}
		
		if(txtMinutos.getText().trim().isEmpty() && chxMinutos.isSelected()) {
			labelMinutos.setVisible(true);
			validar = true;
		}
		else {
			labelMinutos.setVisible(false);
		}
		
		if(txtCanales.getText().trim().isEmpty() && chxCanales.isSelected()) {
			labelCanales.setVisible(true);
			validar = true;
		}
		else {
			labelCanales.setVisible(false);
		}
		return validar;
	}
}
