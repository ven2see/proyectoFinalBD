package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import logico.AlticeSystem;
import logico.Plan;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

public class ReportePlan extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JButton btnBuscar;
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] rows;
	private Plan auxPlan = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReportePlan dialog = new ReportePlan();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReportePlan() {
		setTitle("Reporte Planes");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 730, 485);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 722, 396);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Buscar Por Nombre", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel.setBounds(6, 13, 702, 80);
			contentPanel.add(panel);
			panel.setLayout(null);
			
			txtNombre = new JTextField();
			txtNombre.setBounds(12, 34, 206, 23);
			panel.add(txtNombre);
			txtNombre.setColumns(10);
			
			btnBuscar = new JButton("Buscar");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					auxPlan = AlticeSystem.getInstance().buscarPlanByNomb(txtNombre.getText());
					if(auxPlan == null) {
						JOptionPane.showMessageDialog(null, "Plan No Esta Registrado", "Error", JOptionPane.ERROR_MESSAGE);
					}
					loadReportes(auxPlan);
				}
			});
			btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 11));
			btnBuscar.setBounds(230, 36, 66, 20);
			panel.add(btnBuscar);
		}
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(6, 97, 702, 299);
		contentPanel.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] headers = {"Nombre","Cantidad Ventas","Dinero Generado"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		scrollPane.setViewportView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			buttonPane.setBounds(6, 396, 702, 35);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton btnCerrar = new JButton("Cerrar");
				btnCerrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCerrar.setActionCommand("Cancel");
				buttonPane.add(btnCerrar);
			}
		}
		loadReportes(null);
	}
	
	private void loadReportes(Plan auxPlan) {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		AlticeSystem.getInstance().cantDineroPorPlan();
		if(auxPlan == null) {
			for(int i = 0; i < AlticeSystem.getInstance().getMisPlanes().size(); i++) {
				rows[0] = AlticeSystem.getInstance().getMisPlanes().get(i).getNombre();
				rows[1] = AlticeSystem.getInstance().getMisPlanes().get(i).getCantVentas();
				rows[2] = AlticeSystem.getInstance().getMisPlanes().get(i).getDineroGenerado();
				model.addRow(rows);
			}
		}
		else {
			rows[0] = auxPlan.getNombre();
			rows[1] = auxPlan.getCantVentas();
			rows[2] = auxPlan.getDineroGenerado();
			model.addRow(rows);
		}
	}
}