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

import SqlDB.ConexionDB;
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
import java.sql.ResultSet;
import java.sql.Statement;

public class ListarPlanes extends JDialog {

	private final JPanel contentPanel = new JPanel();
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(6, 10, 702, 386);
		contentPanel.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		String[] headers = {"Id", "Nombre","Canales", "Internet","Minutos",  "Estado", "Costo", "Mensual"};
		model = new DefaultTableModel();
		model.setColumnIdentifiers(headers);
		table.setModel(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int aux = table.getSelectedRow();
				if(aux != -1) {
					btnModificar.setEnabled(true);
					//btnDeshabilitar.setEnabled(true);
					String id = (String) table.getValueAt(aux, 0);
					String nombres = (String) table.getValueAt(aux, 1);
					String canales = (String) table.getValueAt(aux, 2);
					String internet = (String) table.getValueAt(aux, 3);
					String minutos = (String) table.getValueAt(aux, 4);
					float costo = 0;
					float mensual = 0;
					
					try {
						costo = Float.parseFloat ((String) table.getValueAt(aux, 6));
						mensual = Float.parseFloat ((String) table.getValueAt(aux, 7));

					} catch (NumberFormatException nfe) {
						// Muestra un mensaje de error o maneja la excepción de la manera que consideres más adecuada
						System.out.println("Error al convertir los valores de la tabla a enteros");
					}
					
					if(auxPlan == null) {
						auxPlan = new Plan(id, nombres, canales, minutos, internet, costo, mensual, "");
					}
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
							loadPlanes();
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
							loadPlanes();
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
		loadPlanes();
	}
	
	private void loadPlanes() {
		model.setRowCount(0);
		rows = new Object[model.getColumnCount()];
		String sql ="select * from Planes;";
		String[] datos = new String[8];
	    Statement st;
	    
	    
	    try {
	    	st= ConexionDB.getConnection().createStatement();
	    	
	    	ResultSet rs = st.executeQuery(sql);
	    	
	    	 while(rs.next()){
	             
	                datos[0]=rs.getString(1);
	                datos[1]=rs.getString(2);
	                datos[2]=rs.getString(3);
	                datos[3]=rs.getString(4);
	                datos[4]=rs.getString(5);
	                datos[5]=rs.getString(6);
	                datos[6]=rs.getString(7);
	                datos[7]=rs.getString(8);
	                
	                model.addRow(datos);
	           }
	    }
	    catch(Exception e){
	    	JOptionPane.showMessageDialog(null,"No se pudo mostrar los registros, error: "+ e.toString());
	    }
		btnModificar.setEnabled(false);
	}
}
