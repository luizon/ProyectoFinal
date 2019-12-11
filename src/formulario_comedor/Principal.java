package formulario_comedor;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Principal extends JFrame implements KeyListener, ActionListener{
	JLabel titulo;
	JButton btnNiño, btnTutor, btnAlimento, btnListaAlimentos;
	
	public Principal() {
		super("Proyecto 2 - Comedor escolar");
		setSize(1000, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		addKeyListener(this);

		int x = getWidth()/10, y = getHeight()/10;
		setLayout(null);
		titulo = new JLabel("Formularios del comedor escolar");
		Font fTitulo = new Font("Arial", Font.BOLD, 30);
		titulo.setFont(fTitulo);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		add(titulo);
		titulo.setBounds(0, 0, getWidth(), 100);
		
		Font fBoton = new Font("Arial", Font.PLAIN, 15);
		btnNiño = new JButton("Niño");
		btnNiño.setFont(fBoton);
		btnNiño.addActionListener(this);
		add(btnNiño);
		btnTutor = new JButton("Tutor");
		btnTutor.setFont(fBoton);
		btnTutor.addActionListener(this);
		add(btnTutor);
		btnAlimento= new JButton("Alimento");
		btnAlimento.setFont(fBoton);
		btnAlimento.addActionListener(this);
		add(btnAlimento);
		btnListaAlimentos= new JButton("Lista de alimentos");
		btnListaAlimentos.setFont(fBoton);
		btnListaAlimentos.addActionListener(this);
		add(btnListaAlimentos);
		
		btnNiño.setBounds(1*x, 4*y, 3*x, y);
		btnTutor.setBounds(6*x, 4*y, 3*x, y);
		btnAlimento.setBounds(1*x, 7*y, 3*x, y);
		btnListaAlimentos.setBounds(6*x, 7*y, 3*x, y);

		Connection conexion = DBConexion.GetConnection();

	}
	public static void main(String[] args) {
		new Principal();
	}
	@Override
	public void keyPressed(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNiño) {
			new Niño(getWidth(), getHeight());
			return;
		}
		if(e.getSource() == btnTutor) {
			new Tutores(getWidth(), getHeight());
			return;
		}
		if(e.getSource() == btnAlimento) {
			new Alimento(getWidth(), getHeight());
			return;
		}
		if(e.getSource() == btnListaAlimentos) {
			new Alimentos(getWidth(), getHeight());
			return;
		}
	}
}

/*
 * tutores y sus adeudos
 * niños y sus alergias
 * menus y sus alimentos
 * alimentos y sus ingredientes
 * almacen de ingredientes
 */
