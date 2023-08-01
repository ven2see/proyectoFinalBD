package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import logico.AlticeSystem;
import logico.Cliente;
import logico.P_Trabajador;
import logico.Persona;
import logico.Plan;
import logico.PlanAdquirido;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.TextField;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

public class AdquirirPlan extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtFecha;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTotal;
	private JTable table;
	private JFormattedTextField txtCedula;
	private static DefaultTableModel model;
	private static Object[] rows;
	private JTextField txtApellido;
	private Plan auxPlan = null;
	private Persona auxPersona = null;
	private PlanAdquirido auxPlanAd = null;
	private float precio = 0;
	private JComboBox cbxGenero;
	private JTextField txtCodigo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AdquirirPlan dialog = new AdquirirPlan(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Create the dialog.
	 */
	public AdquirirPlan(Connection conn) {
		setTitle("Adquirir Plan");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 586, 500);
		setLocationRelativeTo(null);
		ImageIcon logo = new ImageIcon("src/imagenes/download.jpg");
		setIconImage(logo.getImage());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			JLabel lblNewLabel = new JLabel("Cedula:");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel.setBounds(10, 10, 104, 23);
			panel.add(lblNewLabel);

			MaskFormatter cedula = null;
			try {
				cedula = new MaskFormatter("###-#######-#");
				cedula.setPlaceholderCharacter('_');
			} catch (ParseException e) {}
			txtCedula = new JFormattedTextField(cedula);
			txtCedula.setBounds(10, 30, 206, 23);
			panel.add(txtCedula);
			txtCedula.setColumns(10);
			{
				JLabel lblNewLabel_1 = new JLabel("Fecha:");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblNewLabel_1.setBounds(194, 430, 56, 16);
				panel.add(lblNewLabel_1);
			}
			{
				txtFecha = new JTextField();
				txtFecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
				txtFecha.setEditable(false);
				txtFecha.setBounds(238, 427, 104, 23);
				DateTimeFormatter fechaAper = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				txtFecha.setText(fechaAper.format(LocalDateTime.now()));
				panel.add(txtFecha);
				txtFecha.setColumns(10);
			}

			JButton btnNewButton = new JButton("Buscar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					auxPersona = AlticeSystem.getInstance().buscarPersonaByCedula(txtCedula.getText());
					if(auxPersona == null) {
						JOptionPane.showMessageDialog(null, "El cliente no esta registrado", "Error", JOptionPane.ERROR_MESSAGE);
						RegistrarPersona regisCliente = new RegistrarPersona(null,conn);
						regisCliente.setVisible(true);
					}
					else {
						txtNombre.setEditable(false);
						txtApellido.setEditable(false);
						txtDireccion.setEditable(false);
						cbxGenero.setEnabled(false);
						txtNombre.setText(auxPersona.getNombre());
						txtApellido.setText(auxPersona.getApellido());
						//txtNacionalidad.setText(auxPersona.getNacionalidad());
						txtDireccion.setText(auxPersona.getDireccion());
						if(auxPersona.getGenero().equalsIgnoreCase("Hombre")) {
							cbxGenero.setSelectedIndex(0);
						}
						if(auxPersona.getGenero().equalsIgnoreCase("Mujer")) {
							cbxGenero.setSelectedIndex(1);
						}
					}
				}
			});
			btnNewButton.setBounds(222, 29, 97, 22);
			panel.add(btnNewButton);

			JLabel lblNewLabel_2 = new JLabel("Nombre:");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_2.setBounds(10, 63, 104, 23);
			panel.add(lblNewLabel_2);

			txtNombre = new JTextField();
			txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtNombre.setEditable(false);
			txtNombre.setBounds(10, 83, 206, 23);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			{
				MaskFormatter telefono = null;
				try {
					telefono = new MaskFormatter("###-###-####");
					telefono.setPlaceholderCharacter('_');
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Direccion:");
				lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblNewLabel_4.setBounds(10, 130, 104, 23);
				panel.add(lblNewLabel_4);
			}
			{
				txtDireccion = new JTextField();
				txtDireccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
				txtDireccion.setEditable(false);
				txtDireccion.setBounds(10, 151, 533, 23);
				panel.add(txtDireccion);
				txtDireccion.setColumns(10);
			}

			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Planes", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_1.setBounds(10, 229, 533, 171);
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel_1.add(scrollPane, BorderLayout.CENTER);
				{
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							int aux = table.getSelectedRow();
							if(aux != -1) {
								String nombre = (String) table.getValueAt(aux, 0);
								precio = AlticeSystem.getInstance().buscarPlanByNomb(nombre).getPrecioInicial();
								DecimalFormat df = new DecimalFormat("#.0");
								txtTotal.setText("RD$"+df.format(precio));
								auxPlan = AlticeSystem.getInstance().buscarPlanByNomb(nombre);
							}
							else {
								txtTotal.setText("");
							}
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					String[] headers = {"Nombre","Internet","Minutos", "Canales", "Costo", "Mensual"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(headers);
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}

			JLabel lblNewLabel_6 = new JLabel("Total:");
			lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_6.setBounds(20, 421, 90, 35);
			panel.add(lblNewLabel_6);

			txtTotal = new JTextField();
			txtTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtTotal.setEditable(false);
			txtTotal.setBounds(56, 427, 104, 22);
			panel.add(txtTotal);
			txtTotal.setColumns(10);

			JLabel lblNewLabel_5 = new JLabel("Apellido:");
			lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_5.setBounds(242, 63, 104, 23);
			panel.add(lblNewLabel_5);

			txtApellido = new JTextField();
			txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 12));
			txtApellido.setEditable(false);
			txtApellido.setBounds(238, 83, 206, 23);
			panel.add(txtApellido);
			txtApellido.setColumns(10);
			{
				JLabel lblNewLabel_8 = new JLabel("Genero:");
				lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
				lblNewLabel_8.setBounds(466, 63, 104, 23);
				panel.add(lblNewLabel_8);
			}

			cbxGenero = new JComboBox();
			cbxGenero.setEnabled(false);
			cbxGenero.setModel(new DefaultComboBoxModel(new String[] {"Hombre", "Mujer"}));
			cbxGenero.setBounds(463, 83, 80, 23);
			panel.add(cbxGenero);

			JLabel lblNewLabel_9 = new JLabel("Codigo:");
			lblNewLabel_9.setBounds(12, 201, 56, 16);
			panel.add(lblNewLabel_9);

			txtCodigo = new JTextField();
			txtCodigo.setEditable(false);
			txtCodigo.setText("P-"+AlticeSystem.getInstance().getGeneradorCodigoPlanAd());
			txtCodigo.setBounds(63, 198, 87, 22);
			panel.add(txtCodigo);
			txtCodigo.setColumns(10);
			
			JLabel label = new JLabel("New label");
			label.setBounds(228, 33, 56, 16);
			panel.add(label);
			{
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Adquirir");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int aux = table.getSelectedRow();
						if(aux != -1) {
							if(auxPersona == null) {
								JOptionPane.showMessageDialog(null, "Se debe seleccionar un cliente");
							}
							SimpleDateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
							Date date1 = null;
							try {
								date1 = fecha.parse(txtFecha.getText());
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
							java.sql.Date date2 = new java.sql.Date(date1.getTime());
							auxPlan.cantVentasPlan(auxPlan);
							auxPlanAd = new PlanAdquirido(txtCedula.getText(), "", date2, precio, auxPlan.getPrecioMensual(), txtCodigo.getText(), auxPlan);
						
						    ((Cliente) auxPersona).insertarPlanAd(auxPlanAd);
							AlticeSystem.getInstance().insertarPlanAd(auxPlanAd);
							AlticeSystem.getInstance().addFactura((Cliente)auxPersona);
							JOptionPane.showMessageDialog(null, "Operacion Exitosa", "Informacion", JOptionPane.INFORMATION_MESSAGE);
							clean();
						}
						else {
							JOptionPane.showMessageDialog(null, "Se debe elegir un plan", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		loadPlanes();
	}
	protected void clean() {
		txtCodigo.setText("P-"+AlticeSystem.getInstance().getGeneradorCodigoPlanAd());
		txtNombre.setText("");
		txtApellido.setText("");
		txtDireccion.setText("");
		txtCedula.setValue(null);
		txtTotal.setText("");
		cbxGenero.setSelectedIndex(0);
		txtNombre.setEditable(false);
		txtApellido.setEditable(false);
		txtDireccion.setEditable(false);
		cbxGenero.setEnabled(false);
	}

	private void loadPlanes() {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];

		for(int i = 0; i < AlticeSystem.getInstance().getMisPlanes().size(); i++) {
			if(AlticeSystem.getInstance().getMisPlanes().get(i).getEstado().equalsIgnoreCase("Habilitado")) {
				rows[0] = AlticeSystem.getInstance().getMisPlanes().get(i).getNombre();
				rows[1] = AlticeSystem.getInstance().getMisPlanes().get(i).getCantInternet();
				rows[2] = AlticeSystem.getInstance().getMisPlanes().get(i).getCantMinutos();
				rows[3] = AlticeSystem.getInstance().getMisPlanes().get(i).getCantCanales();
				rows[4] = AlticeSystem.getInstance().getMisPlanes().get(i).getPrecioInicial();
				rows[5] = AlticeSystem.getInstance().getMisPlanes().get(i).getPrecioMensual();
				model.addRow(rows);
			}
		}
	}
	
}
