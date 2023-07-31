package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.AlticeSystem;
import logico.Plan;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListarPlanes extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] rows;
	private Plan auxPlan = null;
	private Plan plan = null;
	private JButton btnModificar;
	private JButton btnDeshabilitar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarPlanes dialog = new ListarPlanes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarPlanes() {
		setTitle("Listado de Planes");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 730, 485);
		setLocationRelativeTo(null);
		ImageIcon logo = new ImageIcon("src/imagenes/download.jpg");
		setIconImage(logo.getImage());
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 722, 396);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(6, 13, 702, 80);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(10, 20, 56, 16);
		panel.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(10, 40, 206, 23);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				plan = AlticeSystem.getInstance().buscarPlanByNomb(txtNombre.getText());
				if(plan == null) {
					JOptionPane.showMessageDialog(null, "Plan No Esta Registrado", "Error", JOptionPane.ERROR_MESSAGE);
				}
				loadPlanes(plan);
			}
		});
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnBuscar.setBounds(226, 40, 66, 20);
		panel.add(btnBuscar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(6, 97, 702, 299);
		contentPanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] headers = {"Nombre","Internet","Minutos", "Canales", "Costo", "Mensual", "Estado"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int aux = table.getSelectedRow();
				if(aux != -1) {
					btnModificar.setEnabled(true);
					btnDeshabilitar.setEnabled(true);
					String nombre = (String) table.getValueAt(aux, 0);
					auxPlan = AlticeSystem.getInstance().buscarPlanByNomb(nombre);
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(6, 396, 702, 35);
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(auxPlan != null) {
							RegistrarPlan regPlan = new RegistrarPlan(auxPlan);
							regPlan.setVisible(true);
							loadPlanes(null);
						}
					}
				});
				btnModificar.setEnabled(false);
				buttonPane.add(btnModificar);
			}
			{
				btnDeshabilitar = new JButton("Deshabilitar");
				btnDeshabilitar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(auxPlan != null) {
							auxPlan.setEstado("Deshabilitado");
							loadPlanes(null);
						}
					}
				});
				btnDeshabilitar.setEnabled(false);
				btnDeshabilitar.setActionCommand("OK");
				buttonPane.add(btnDeshabilitar);
				getRootPane().setDefaultButton(btnDeshabilitar);
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
		loadPlanes(null);
	}
	private void loadPlanes(Plan auxPlan) {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		if(auxPlan == null) {
			for(int i = 0; i < AlticeSystem.getInstance().getMisPlanes().size(); i++) {
				rows[0] = AlticeSystem.getInstance().getMisPlanes().get(i).getNombre();
				rows[1] = AlticeSystem.getInstance().getMisPlanes().get(i).getCantInternet();
				rows[2] = AlticeSystem.getInstance().getMisPlanes().get(i).getCantMinutos();
				rows[3] = AlticeSystem.getInstance().getMisPlanes().get(i).getCantCanales();
				rows[4] = AlticeSystem.getInstance().getMisPlanes().get(i).getPrecioInicial();
				rows[5] = AlticeSystem.getInstance().getMisPlanes().get(i).getPrecioMensual();
				rows[6] = AlticeSystem.getInstance().getMisPlanes().get(i).getEstado();
				model.addRow(rows);
			}
		}
		else {
			rows[0] = auxPlan.getNombre();
			rows[1] = auxPlan.getCantInternet();
			rows[2] = auxPlan.getCantMinutos();
			rows[3] = auxPlan.getCantCanales();
			rows[4] = auxPlan.getPrecioInicial();
			rows[5] = auxPlan.getPrecioMensual();
			rows[6] = auxPlan.getEstado();
			model.addRow(rows);
		}
		btnModificar.setEnabled(false);
		btnDeshabilitar.setEnabled(false);
	}
}
