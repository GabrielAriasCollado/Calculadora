import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Window.Type;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public class calculadora extends JFrame {
	
	ScriptEngineManager sem = new ScriptEngineManager();
	ScriptEngine se = sem.getEngineByName("JavaScript");

	private JPanel contentPane;
	private JButton cero;
	private JPanel fondoTeclas;
	private JPanel pantalla;
	private JButton uno;
	private JButton dos;
	private JButton tres;
	private JButton cuatro;
	private JButton cinco;
	private JButton seis;
	private JButton siete;
	private JButton ocho;
	private JButton nueve;
	private JButton negativo;
	private JButton punto;
	private JButton borrarTodo;
	private JButton borrar;
	private JButton raiz;
	private JButton dividir;
	private JButton cuadrado;
	private JButton multiplicar;
	private JButton factorial;
	private JButton restar;
	private JButton unoEntreX;
	private JButton sumar;
	private JButton igual;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					calculadora frame = new calculadora();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	boolean darkmode=false;
	private JMenuItem salir;
	private JLabel respuesta;
	private JLabel operacion;
	private JPanel panel;
	
	public void imprimirRespuesta(String resultado) {
		respuesta.setText(resultado);
	}
	
	public String hallarResultado() {
		boolean tiene=false, empieza=false;
		
		try {
			char[] tieneParentesis = operacion.getText().toCharArray();
			for (int i=0;i<tieneParentesis.length;i++) {
				if (tieneParentesis[i]=='(') {
					empieza = true;
					break;
				}
			}	
			if (empieza) {
				for(int j=tieneParentesis.length-1;j>=0;j--) {
					if (tieneParentesis[j]==')') {
						tiene = true;
						break;
					}
				}
			}
			
			if (empieza && !tiene) {
				operacion.setText(operacion.getText()+")");
			}
			
			String resultado = se.eval(operacion.getText()).toString();
			return resultado;
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error";
		}
	}
	public calculadora() {
		setForeground(Color.WHITE);
		setBackground(Color.WHITE);
		setType(Type.UTILITY);
		
		setTitle("Calculadora\r\n by: Gabriel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 560);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pantalla = new JPanel();
		pantalla.setBounds(0, 32, 384, 139);
		pantalla.setBackground(new Color(230,230, 230));
		contentPane.add(pantalla);
		pantalla.setLayout(null);
		
		operacion = new JLabel("");
		operacion.setBounds(18, 35, 344, 30);
		operacion.setForeground(Color.DARK_GRAY);
		operacion.setHorizontalAlignment(SwingConstants.RIGHT);
		operacion.setFont(new Font("Calibri", Font.BOLD, 19));
		pantalla.add(operacion);
		
		respuesta = new JLabel("0");
		respuesta.setBounds(18, 76, 344, 49);
		respuesta.setForeground(new Color(51, 51, 51));
		respuesta.setHorizontalAlignment(SwingConstants.RIGHT);
		respuesta.setFont(new Font("Calibri", Font.BOLD, 48));
		pantalla.add(respuesta);
		
		panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(0, 136, 384, 3);
		pantalla.add(panel);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 384, 24);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Archivo");
		mnNewMenu.setRequestFocusEnabled(false);
		mnNewMenu.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Nuevo");
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText("");
				respuesta.setText("0");
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		salir = new JMenuItem("Salir");
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		salir.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		mnNewMenu.add(salir);
		
		JMenu mnNewMenu_1 = new JMenu("Editar");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(mnNewMenu_1);
		
		JCheckBoxMenuItem dm = new JCheckBoxMenuItem("Modo Oscuro");
		dm.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		
		
		
		dm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!darkmode){
					panel.setBackground(new Color(30,30,30));
					contentPane.setBackground(new Color(25,30,31));
					fondoTeclas.setBackground(new Color(25, 28, 35));
					pantalla.setBackground(new Color(45, 45, 45));
					operacion.setForeground(new Color(200, 106, 6));
					respuesta.setForeground(new Color(255, 98, 0));
					uno.setBackground(new Color(25, 28, 35));
					uno.setForeground(Color.WHITE);
					dos.setBackground(new Color(25, 28, 35));
					dos.setForeground(Color.WHITE);
					tres.setBackground(new Color(25, 28, 35));
					tres.setForeground(Color.WHITE);
					cuatro.setBackground(new Color(25, 28, 35));
					cuatro.setForeground(Color.WHITE);
					cinco.setBackground(new Color(25, 28, 35));
					cinco.setForeground(Color.WHITE);
					seis.setBackground(new Color(25, 28, 35));
					seis.setForeground(Color.WHITE);
					siete.setBackground(new Color(25, 28, 35));
					siete.setForeground(Color.WHITE);
					ocho.setBackground(new Color(25, 28, 35));
					ocho.setForeground(Color.WHITE);
					nueve.setBackground(new Color(25, 28, 35));
					nueve.setForeground(Color.WHITE);
					cero.setBackground(new Color(25, 28, 35));
					cero.setForeground(Color.WHITE);
					negativo.setBackground(new Color(25, 28, 35));
					negativo.setForeground(Color.WHITE);
					punto.setBackground(new Color(25, 28, 35));
					punto.setForeground(Color.WHITE);
					borrarTodo.setBackground(new Color(25, 28, 35));
					borrarTodo.setForeground(Color.WHITE);
					borrar.setBackground(new Color(25, 28, 35));
					borrar.setForeground(Color.WHITE);
					raiz.setBackground(new Color(25, 28, 35));
					raiz.setForeground(Color.WHITE);
					dividir.setBackground(new Color(25, 28, 35));
					dividir.setForeground(Color.WHITE);
					multiplicar.setBackground(new Color(25, 28, 35));
					multiplicar.setForeground(Color.WHITE);
					restar.setBackground(new Color(25, 28, 35));
					restar.setForeground(Color.WHITE);
					sumar.setBackground(new Color(25, 28, 35));
					sumar.setForeground(Color.WHITE);
					cuadrado.setBackground(new Color(25, 28, 35));
					cuadrado.setForeground(Color.WHITE);
					factorial.setBackground(new Color(25, 28, 35));
					factorial.setForeground(Color.WHITE);
					unoEntreX.setBackground(new Color(25, 28, 35));
					unoEntreX.setForeground(Color.WHITE);
					igual.setBackground(new Color(200, 106, 6));
					darkmode = true;
				}
				else {
					calculadora frame2 = new calculadora();
					frame2.dispose();
					frame2.setVisible(true);
					frame2.setLocationRelativeTo(null);
					darkmode = false;
				}
				
				
			}
		});
		mnNewMenu_1.add(dm);
		
		JMenu mnNewMenu_2 = new JMenu("Ayuda");
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		menuBar.add(mnNewMenu_2);
		
		JMenuBar menuBar_1 = new JMenuBar();
		menuBar.add(menuBar_1);
		
		fondoTeclas = new JPanel();
		fondoTeclas.setBackground(new Color(255, 255, 255));
		fondoTeclas.setBounds(0, 172, 384, 350);
		contentPane.add(fondoTeclas);
		fondoTeclas.setLayout(null);
		
		borrarTodo = new JButton("\r\nC\r\n");
		borrarTodo.setBounds(0, 0, 70, 70);
		borrarTodo.setBorderPainted(false);
		borrarTodo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		borrarTodo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		borrarTodo.setFocusPainted(false);
		borrarTodo.setForeground(new Color(51, 51, 51));
		borrarTodo.setMargin(new Insets(9, 14, 2, 14));
		borrarTodo.setHorizontalTextPosition(SwingConstants.CENTER);
		fondoTeclas.add(borrarTodo);
		borrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText("");
				respuesta.setText("0");
			}
		});
		borrarTodo.setBackground(new Color(255, 255, 255));
		borrarTodo.setFont(new Font("Calibri", Font.PLAIN, 23));
		
		borrar = new JButton("<-");
		borrar.setBounds(140, 0, 70, 70);
		borrar.setBorderPainted(false);
		borrar.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		borrar.setForeground(new Color(51, 51, 51));
		borrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		borrar.setFocusPainted(false);
		fondoTeclas.add(borrar);
		borrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText(operacion.getText().replaceFirst(".$",""));
			}
		});
		borrar.setFont(new Font("Calibri", Font.PLAIN, 21));
		borrar.setBackground(Color.WHITE);
		
		cuatro = new JButton("4");
		cuatro.setBounds(0, 140, 70, 70);
		cuatro.setBorderPainted(false);
		cuatro.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cuatro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText(operacion.getText()+4);
			}
		});
		cuatro.setForeground(new Color(51, 51, 51));
		cuatro.setMargin(new Insets(9, 14, 2, 14));
		cuatro.setFocusPainted(false);
		cuatro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fondoTeclas.add(cuatro);
		cuatro.setFont(new Font("Calibri", Font.BOLD, 24));
		cuatro.setBackground(Color.WHITE);
		
		ocho = new JButton("8");
		ocho.setBounds(70, 70, 70, 70);
		ocho.setBorderPainted(false);
		ocho.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		ocho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText(operacion.getText()+8);
			}
		});
		ocho.setForeground(new Color(51, 51, 51));
		ocho.setMargin(new Insets(9, 14, 2, 14));
		ocho.setFocusPainted(false);
		ocho.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fondoTeclas.add(ocho);
		ocho.setFont(new Font("Calibri", Font.BOLD, 24));
		ocho.setBackground(Color.WHITE);
		
		nueve = new JButton("9");
		nueve.setBounds(140, 70, 70, 70);
		nueve.setBorderPainted(false);
		nueve.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		nueve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText(operacion.getText()+9);
			}
		});
		nueve.setForeground(new Color(51, 51, 51));
		nueve.setMargin(new Insets(9, 14, 2, 14));
		nueve.setFocusPainted(false);
		nueve.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fondoTeclas.add(nueve);
		nueve.setFont(new Font("Calibri", Font.BOLD, 24));
		nueve.setBackground(Color.WHITE);
		
		cinco = new JButton("5");
		cinco.setBounds(70, 140, 70, 70);
		cinco.setBorderPainted(false);
		cinco.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cinco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText(operacion.getText()+5);
			}
		});
		cinco.setForeground(new Color(51, 51, 51));
		cinco.setMargin(new Insets(9, 14, 2, 14));
		cinco.setFocusPainted(false);
		cinco.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fondoTeclas.add(cinco);
		cinco.setFont(new Font("Calibri", Font.BOLD, 24));
		cinco.setBackground(Color.WHITE);
		
		seis = new JButton("6");
		seis.setBounds(140, 140, 70, 70);
		seis.setBorderPainted(false);
		seis.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		seis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText(operacion.getText()+6);
			}
		});
		seis.setMargin(new Insets(9, 14, 2, 14));
		seis.setForeground(new Color(51, 51, 51));
		seis.setFocusPainted(false);
		seis.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fondoTeclas.add(seis);
		seis.setFont(new Font("Calibri", Font.BOLD, 24));
		seis.setBackground(Color.WHITE);
		
		uno = new JButton("1");
		uno.setBounds(0, 210, 70, 70);
		uno.setBorderPainted(false);
		uno.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		uno.setForeground(new Color(51, 51, 51));
		uno.setMargin(new Insets(9, 14, 2, 14));
		uno.setFocusPainted(false);
		uno.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		uno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText(operacion.getText()+1);
			}
		});
		uno.setFont(new Font("Calibri", Font.BOLD, 24));
		uno.setBackground(Color.WHITE);
		fondoTeclas.add(uno);
		
		dos = new JButton("2");
		dos.setBounds(70, 210, 70, 70);
		dos.setBorderPainted(false);
		dos.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		dos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText(operacion.getText()+2);
			}
			
		});
		dos.setForeground(new Color(51, 51, 51));
		dos.setMargin(new Insets(9, 14, 2, 14));
		dos.setFocusPainted(false);
		dos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dos.setFont(new Font("Calibri", Font.BOLD, 24));
		dos.setBackground(Color.WHITE);
		fondoTeclas.add(dos);
		
		tres = new JButton("3");
		tres.setBounds(140, 210, 70, 70);
		tres.setBorderPainted(false);
		tres.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText(operacion.getText()+3);
			}
		});
		tres.setForeground(new Color(51, 51, 51));
		tres.setMargin(new Insets(9, 14, 2, 14));
		tres.setFocusPainted(false);
		tres.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		tres.setFont(new Font("Calibri", Font.BOLD, 24));
		tres.setBackground(Color.WHITE);
		fondoTeclas.add(tres);
		
		negativo = new JButton("+/-");
		negativo.setBounds(0, 280, 70, 70);
		negativo.setBorderPainted(false);
		negativo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		negativo.setForeground(new Color(51, 51, 51));
		negativo.setMargin(new Insets(9, 14, 2, 14));
		negativo.setFocusPainted(false);
		negativo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		negativo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (operacion.getText()=="") {
					operacion.setText("-(0)");
				}
				else {
					operacion.setText("-("+operacion.getText()+")");
				}
				String resultado = hallarResultado();
				imprimirRespuesta(resultado);
			}
		});
		negativo.setFont(new Font("Calibri", Font.BOLD, 20));
		negativo.setBackground(Color.WHITE);
		fondoTeclas.add(negativo);
		
		cero = new JButton("0");
		cero.setBounds(70, 280, 70, 70);
		cero.setBorderPainted(false);
		cero.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		cero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText(operacion.getText()+0);
			}
		});
		cero.setForeground(new Color(51, 51, 51));
		cero.setMargin(new Insets(9, 14, 2, 14));
		cero.setFocusPainted(false);
		cero.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cero.setFont(new Font("Calibri", Font.BOLD, 24));
		cero.setBackground(Color.WHITE);
		fondoTeclas.add(cero);
		
		punto = new JButton(".");
		punto.setMargin(new Insets(0, 14, 2, 14));
		punto.setBounds(140, 280, 70, 70);
		punto.setBorderPainted(false);
		punto.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		punto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText(operacion.getText()+".");
			}
		});
		punto.setForeground(new Color(51, 51, 51));
		punto.setFocusPainted(false);
		punto.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		punto.setFont(new Font("Calibri", Font.BOLD, 25));
		punto.setBackground(Color.WHITE);
		fondoTeclas.add(punto);
		
		igual = new JButton("=");
		igual.setBounds(244, 280, 140, 70);
		igual.setBorderPainted(false);
		igual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String resultado = hallarResultado();
				imprimirRespuesta(resultado);
				
			}
			
		});
		igual.setFocusPainted(false);
		igual.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		igual.setForeground(new Color(255, 255, 255));
		igual.setFont(new Font("Calibri", Font.BOLD, 20));
		igual.setBackground(new Color(0, 102, 153));
		fondoTeclas.add(igual);
		
		multiplicar = new JButton("x");
		multiplicar.setBounds(315, 70, 70, 70);
		multiplicar.setBorderPainted(false);
		multiplicar.setForeground(new Color(51, 51, 51));
		multiplicar.setMargin(new Insets(9, 14, 2, 14));
		multiplicar.setFocusPainted(false);
		multiplicar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		multiplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText(operacion.getText()+"*");
			}
		});
		multiplicar.setFont(new Font("Calibri", Font.PLAIN, 24));
		multiplicar.setBackground(Color.WHITE);
		fondoTeclas.add(multiplicar);
		
		dividir = new JButton("÷");
		dividir.setBounds(315, 0, 70, 70);
		dividir.setForeground(new Color(51, 51, 51));
		dividir.setBorderPainted(false);
		dividir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText(operacion.getText()+"/");
			}
		});
		dividir.setMargin(new Insets(9, 14, 2, 14));
		dividir.setFocusPainted(false);
		dividir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		dividir.setFont(new Font("Calibri", Font.PLAIN, 24));
		dividir.setBackground(new Color(255, 255, 255));
		fondoTeclas.add(dividir);
		
		restar = new JButton("-");
		restar.setBounds(315, 140, 70, 70);
		restar.setForeground(new Color(51, 51, 51));
		restar.setBorderPainted(false);
		restar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText(operacion.getText()+"-");
			}
		});
		restar.setMargin(new Insets(9, 14, 2, 14));
		restar.setFocusPainted(false);
		restar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		restar.setFont(new Font("Calibri", Font.PLAIN, 30));
		restar.setBackground(Color.WHITE);
		fondoTeclas.add(restar);
		
		sumar = new JButton("+");
		sumar.setBounds(315, 210, 70, 70);
		sumar.setForeground(new Color(51, 51, 51));
		sumar.setBorderPainted(false);
		sumar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText(operacion.getText()+"+");
			}
		});
		sumar.setMargin(new Insets(9, 14, 2, 14));
		sumar.setFocusPainted(false);
		sumar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sumar.setFont(new Font("Calibri", Font.PLAIN, 24));
		sumar.setBackground(Color.WHITE);
		fondoTeclas.add(sumar);
		
		raiz = new JButton("\u221A");
		raiz.setBounds(245, 0, 70, 70);
		raiz.setBorderPainted(false);
		raiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (operacion.getText()=="") {
					operacion.setText("Math.sqrt(0)");
				}
				else {
					operacion.setText("Math.sqrt("+operacion.getText()+")");
				}
				String resultado = hallarResultado();
				imprimirRespuesta(resultado);
			}
		});
		raiz.setForeground(new Color(51, 51, 51));
		raiz.setMargin(new Insets(9, 14, 2, 14));
		raiz.setFocusPainted(false);
		raiz.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		raiz.setFont(new Font("Calibri", Font.PLAIN, 23));
		raiz.setBackground(Color.WHITE);
		fondoTeclas.add(raiz);
		
		cuadrado = new JButton("(");
		cuadrado.setBounds(245, 70, 70, 70);
		cuadrado.setBorderPainted(false);
		cuadrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText(operacion.getText()+"(");
			}
		});
		cuadrado.setForeground(new Color(51, 51, 51));
		cuadrado.setMargin(new Insets(7, 14, 2, 14));
		cuadrado.setFocusPainted(false);
		cuadrado.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cuadrado.setFont(new Font("Calibri", Font.PLAIN, 21));
		cuadrado.setBackground(Color.WHITE);
		fondoTeclas.add(cuadrado);
		
		unoEntreX = new JButton("1/X");
		unoEntreX.setBounds(245, 210, 70, 70);
		unoEntreX.setBorderPainted(false);
		unoEntreX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (operacion.getText()=="") {
					operacion.setText("1/0");
				}
				else {
					operacion.setText("1/("+operacion.getText()+")");
				}
				String resultado = hallarResultado();
				imprimirRespuesta(resultado);
			}
		});
		unoEntreX.setForeground(new Color(51, 51, 51));
		unoEntreX.setMargin(new Insets(9, 14, 2, 14));
		unoEntreX.setFocusPainted(false);
		unoEntreX.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		unoEntreX.setFont(new Font("Calibri", Font.PLAIN, 16));
		unoEntreX.setBackground(Color.WHITE);
		fondoTeclas.add(unoEntreX);
		
		factorial = new JButton(")");
		factorial.setBounds(245, 140, 70, 70);
		factorial.setBorderPainted(false);
		factorial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText(operacion.getText()+")");
			}
		});
		factorial.setForeground(new Color(51, 51, 51));
		factorial.setMargin(new Insets(9, 14, 2, 14));
		factorial.setFocusPainted(false);
		factorial.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		factorial.setFont(new Font("Calibri", Font.PLAIN, 21));
		factorial.setBackground(Color.WHITE);
		fondoTeclas.add(factorial);
		
		siete = new JButton("7");
		siete.setBounds(0, 70, 70, 70);
		siete.setBorderPainted(false);
		siete.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		fondoTeclas.add(siete);
		siete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				operacion.setText(operacion.getText()+7);
			}
		});
		siete.setForeground(new Color(51, 51, 51));
		siete.setMargin(new Insets(9, 14, 2, 14));
		siete.setFocusPainted(false);
		siete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		siete.setFont(new Font("Calibri", Font.BOLD, 24));
		siete.setBackground(Color.WHITE);
	}
}
