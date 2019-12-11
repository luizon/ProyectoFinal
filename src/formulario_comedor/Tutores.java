package formulario_comedor;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

//import Unidad_3.JTFClave;
//import Unidad_3.JTFRFC;

public class Tutores extends JFrame {

	JLabel titulo, lNombre, ladeudo, lcelular, ltrabajo, lteltrabajo, lhijos;
	JTextField txtNombre, txtApa, txtAma, txtadeudo, txtcel, txttrabajo, txtteltrabajo /*, txthijos*/;
	JButton btnAgregar, btnActualizar, btnQuitar, btnConsultar;
	String[] encabezado;
	JTable tabla;
	DefaultTableModel modelotabla;
	
	public Tutores(int w, int h) {
		super("Tutor");
		setSize(w, h);
		int x = w/100, y = h/100;
		setLayout(null);
		setLocationRelativeTo(null);
		titulo = new JLabel("Formulario de tutores");
		Font fBold = new Font("Arial", Font.BOLD, 15);
		titulo.setFont(fBold);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		add(titulo);
		titulo.setBounds(0, 0, w, 50);
		
		Font fPlain = new Font("Arial", Font.PLAIN, 15);
		lNombre = new JLabel("Nombre");
		lNombre.setFont(fBold);
		lNombre.setBounds(x*5, y*10, x*10, y*10);
		add(lNombre);
		txtNombre = new JTextField();
		txtNombre.setBounds(x*15, y*10, x*20, y*10);
		add(txtNombre);
		txtApa = new JTextField();
		txtApa.setBounds(x*35, y*10, x*15, y*10);
		add(txtApa);
		txtAma = new JTextField();
		txtAma.setBounds(x*50, y*10, x*15, y*10);
		add(txtAma);

		ladeudo = new JLabel("Adeudo"); 
		ladeudo.setFont(fBold);
		ladeudo.setBounds(x*70, y*10, x*10, y*10);
		add(ladeudo);
		txtadeudo = new JTextField();
		txtadeudo.setBounds(x*80, y*10, x*10, y*10);
		add(txtadeudo);
		
		lcelular = new JLabel("Tel. Celular");
		lcelular.setFont(fBold);
		lcelular.setBounds(x*5, y*25, x*10, y*10);
		add(lcelular);
		txtcel = new JTextField();
		txtcel.setBounds(x*15, y*25, x*50, y*10);
		add(txtcel);

		ltrabajo= new JLabel("Lugar de trabajo");
		ltrabajo.setFont(fBold);
		ltrabajo.setBounds(x*5, y*40, x*10, y*10);
		add(ltrabajo);
		txttrabajo = new JTextField();
		txttrabajo.setBounds(x*15, y*40, x*50, y*10);
		add(txttrabajo);

		lteltrabajo = new JLabel("Tel. de trabajo");
		lteltrabajo.setFont(fBold);
		lteltrabajo.setBounds(x*5, y*55, x*10, y*10);
		add(lteltrabajo);
		txtteltrabajo = new JTextField();
		txtteltrabajo.setBounds(x*15, y*55, x*50, y*10);
		add(txtteltrabajo);
		
		lhijos = new JLabel("Hijos");
		lhijos.setFont(fBold);
		lhijos.setBounds(x*5, y*70, x*10, y*10);
		add(lhijos);
		/*txthijos = new JTextField();
		txthijos.setBounds(x*15, y*70, x*50, y*10);
		add(txthijos);*/
		
		//tabla
		encabezado = new String[] {"Nombre", "Apellido P", "Apellido M", "Grado"};
		modelotabla = new DefaultTableModel(encabezado,0);
		tabla = new JTable()
		{
			private static final long serialVersionUID = 1L;
			boolean editable[] = new boolean[] {false,false,false,false,false};
			public boolean isCellEditable(int rowIndex, int columnIndex)
			{
				return editable[columnIndex];
			}
		};
		modelotabla.addRow(new String[] {"si", "no"});
		modelotabla.addRow(new String[] {"fuck", "24"});
		modelotabla.addRow(new String[] {"lal", "55555"});
		tabla.setModel(modelotabla);
		JScrollPane scroll = new JScrollPane(tabla);
		add(scroll);
		scroll.setBounds(x*15, y*70, x*50, y*20);
		
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(fBold);
		btnAgregar.setBounds(x*73, y*55, x*15, y*5);
		add(btnAgregar);
		btnQuitar = new JButton("Quitar");
		btnQuitar.setFont(fBold);
		btnQuitar.setBounds(x*73, y*61, x*15, y*5);
		add(btnQuitar);
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setFont(fBold);
		btnActualizar.setBounds(x*73, y*67, x*15, y*5);
		add(btnActualizar);
		btnConsultar = new JButton("Consultar");
		btnConsultar.setFont(fBold);
		btnConsultar.setBounds(x*73, y*73, x*15, y*5);
		add(btnConsultar);
		
		
		setVisible(true);
	}
}
