package formulario_comedor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import formulario_bichos.JIDfield;

public class Dialog_ingredientes extends JDialog
{
	private JTextField txt_nombre, txt_caducidad, txt_existencias;
	private JButton btn_agregar;

	public Dialog_ingredientes()
	{
		setLayout(new GridBagLayout());
		setTitle("Agregar ingredientes");
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(250, 150);
		setLocationRelativeTo(this.getParent());
		init();
	}

	void init()
	{
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		add(new JLabel("Ingrediente: "), gbc);

		gbc.gridy = 1;
		add(new JLabel("Caducidad"), gbc);

		gbc.gridy = 2;
		add(new JLabel("Existencias"), gbc);

		gbc.gridy = 3;
		btn_agregar = new JButton("Agregar ingredientes");
		btn_agregar.addActionListener(e -> agregar());
		add(btn_agregar, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		txt_nombre = new JTextField();
		add(txt_nombre, gbc);
		
		gbc.gridy = 1;
		txt_caducidad = new JTextField();
		add(txt_caducidad, gbc);
		
		gbc.gridy = 2;
		txt_existencias = new JTextField();
		add(txt_existencias, gbc);
	}

	private void agregar()
	{
		
	}
}
