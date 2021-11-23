package com.cajero.co;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import javafx.scene.layout.Background;

/**
 * 
 * @author  Alexander Lozano
 *
 */
class EjecutarCajero {
	
	public static void main(String[] args) {
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(new Cliente("Sandra sanchez", "sandra12", "1234"));
		clientes.add(new Cliente("Carlos perdomo", "carlos12", "1234"));
		clientes.add(new Cliente("Cesar caceres", "cesar12", "1234"));
		
		Cliente usuarioValido = validaUsuario(clientes);
		if (usuarioValido.getNombre().equals("")) {
			JOptionPane.showMessageDialog(null, "Usuario no valido");
		} else {
			
			MarcoCajero marcoCajero = new MarcoCajero("Opciones de Cajero", usuarioValido);
			marcoCajero.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			marcoCajero.setVisible(true);
			marcoCajero.setResizable(false);
			
		}
	}
	
	public static Cliente validaUsuario(List<Cliente> clientes) {
		
		String usuario = JOptionPane.showInputDialog("Ingresar Usuario");
		while (usuario.equals("")) {
			usuario = JOptionPane.showInputDialog("Ingresar Usuario");
		}
		
		String clave = JOptionPane.showInputDialog("Ingresar ContraseÒa");
		while (clave.equals("")) {
			clave = JOptionPane.showInputDialog("Ingresar ContraseÒa");
		}
		
		if (clientes.size() > 0) {
			for (Cliente cliente : clientes) {
				if (cliente.getUsuario().equals(usuario.toLowerCase()) && cliente.getClave().equals(clave)) {
					return cliente;
				}
			}
		}
		return new Cliente("", "", "");
	}

}

class MarcoCajero extends JFrame {
	
	Cliente usuarioCliente;
	Map<String, String> historialMovimientos = new HashMap<String, String>();
	
	// JPanel
	JPanel jpanelOptionMenu;
	JPanel cardLayout;
	JPanel jpanelEncabezado;
	JPanel jpanelMenu;
	JPanel jpanelBienvenida;
	JPanel jpanelSaldo;
	JPanel jpanelDepositar;
	JPanel jpanelRetiro;
	JPanel jpanelHistorial;
	
	int countSaldo = 0;
	
	// JButton jpanelMenu
	JButton btnMenuSaldo;
	JButton btnMenuDepositar;
	JButton btnMenuRetiro;
	JButton btnMenuHistorial;
	JButton btnMenuSalir;
	
	// JLabel
	JLabel lbltitulo;
	
	// JTextField
	JTextField txtfMontoRetiro;
	JTextField txtfMontoDeposito;
	
	// JButton Guardar
	JButton btnGuardarDeposito;
	JButton btnGuardarRetiro;
	JButton btnGuardarSalir;
	
	// Constantes identificadores dee los paneles
	final String CARDJPANELBIENVENIDA = "cardjpanelBienvenida";
	final String CARDJPANELSALDO = "Saldo Cuenta";
	final String CARDJPANELDEPOSITAR = "Deposito de dinero";
	final String CARDJPANELRETIRAR = "Retiro de dinero";
	final String CARDJPANELHISTORIAL = "Historial procesos";
	
	NumberFormat formatoImporte = NumberFormat.getNumberInstance();

    public MarcoCajero(String titulo, Cliente cliente) {
    	
    	usuarioCliente = cliente;

        Toolkit miPantalla = Toolkit.getDefaultToolkit();// Consigue almacenar el sistema nativo de ventanas
        Dimension tamanoPantalla = miPantalla.getScreenSize();//Retorna el tamaÒo del monitor principal return object dimention

        int alturaPantalla = tamanoPantalla.height;
        int anchoPantalla = tamanoPantalla.width;

        setSize(anchoPantalla/2, alturaPantalla/2);// Crea el tama√±o de la panatalla
        setLocation(anchoPantalla/4, alturaPantalla/4);// ubicaci√≥n del marco
        setTitle(titulo);
        
        setLayout(new BorderLayout());
        
        encabezadoCajero();
        menuCajero();
        bienvenidoCajero();        
        saldoCajero();
        depositarCajero();
        retiroCajero();
        historialCajero();
        
       
        cardLayout = new JPanel(new CardLayout());   
        
        cardLayout.add(jpanelBienvenida, CARDJPANELBIENVENIDA);
        cardLayout.add(jpanelSaldo, CARDJPANELSALDO);
        cardLayout.add(jpanelDepositar, CARDJPANELDEPOSITAR);
        cardLayout.add(jpanelRetiro, CARDJPANELRETIRAR);
        cardLayout.add(jpanelHistorial, CARDJPANELHISTORIAL);
        
        jpanelOptionMenu = new JPanel();
        jpanelOptionMenu.setLayout(new CardLayout());        
        jpanelOptionMenu.add(cardLayout);
        
        add(jpanelEncabezado, BorderLayout.NORTH);
        add(jpanelMenu, BorderLayout.WEST);        
        add(jpanelOptionMenu, BorderLayout.CENTER);        
    }
    
    public void encabezadoCajero() {
    	
    	JLabel lblTitulo = new JLabel("Bienvenido " + usuarioCliente.getNombre().toUpperCase());    	
    	jpanelEncabezado = new JPanel();
    	jpanelEncabezado.add(lblTitulo);    
    }
    
    public void menuCajero() {
    	
    	btnMenuSaldo = new JButton("Saldo Cuenta");
    	btnMenuDepositar = new JButton("Depositar Dinero");
    	btnMenuRetiro = new JButton("Retiro Dinero");
    	btnMenuHistorial = new JButton("Historial Operaciones");
    	btnMenuSalir = new JButton("Salir");
    	
    	jpanelMenu = new JPanel();
    	jpanelMenu.setLayout(new GridLayout(0,2));
    	
    	jpanelMenu.add(btnMenuSaldo);
    	jpanelMenu.add(btnMenuDepositar);
    	jpanelMenu.add(btnMenuRetiro);
    	jpanelMenu.add(btnMenuHistorial);
    	jpanelMenu.add(btnMenuSalir);
    	
    	btnMenuSaldo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {		
				obtieneOpcionCardLayout(CARDJPANELSALDO);	
			}
		});
    	
    	btnMenuDepositar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				obtieneOpcionCardLayout(CARDJPANELDEPOSITAR);
			}
		});
    	
    	btnMenuRetiro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				obtieneOpcionCardLayout(CARDJPANELRETIRAR);					
			}
		});
    	
    	btnMenuHistorial.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				obtieneOpcionCardLayout(CARDJPANELHISTORIAL);
			}
		});
    	
    	btnMenuSalir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				JOptionPane.showMessageDialog(null, "Gracias por utilizar nuestros servicios.");
				System.exit(0);
			}
		});
    	
    }
    
    public void bienvenidoCajero() {
    	
    	JLabel lblBienvenida = new JLabel("Bienvenidos a servicios del cajero");
    	lblBienvenida.setFont(new Font(null, Font.BOLD, 35));
    	lblBienvenida.setForeground(Color.BLUE);
    	lblBienvenida.setBounds(30, 150, 570, 40);
    	
    	jpanelBienvenida = new JPanel();
    	jpanelBienvenida.setBackground(Color.WHITE);
    	jpanelBienvenida.setLayout(null);
    	
    	jpanelBienvenida.add(lblBienvenida);   	
    }
    
    public void saldoCajero() {
    	
    	lbltitulo = new JLabel(String.valueOf(usuarioCliente.getSaldo())); 
    	lbltitulo.setFont(new Font(null, Font.BOLD, 40));
    	lbltitulo.setForeground(Color.GREEN);
    	lbltitulo.setBounds(200, 150, 570, 40);
    	
    	jpanelSaldo = new JPanel();
    	jpanelSaldo.setBackground(Color.WHITE);
    	jpanelSaldo.setLayout(null);
    	    	
    	jpanelSaldo.add(lbltitulo);
    	
    	Map<String, String> historialPrueba = adicionaHistorial(usuarioCliente, "Consultar Saldo", lbltitulo.getText());
    	
    	for (Map.Entry<String, String> historia : historialPrueba.entrySet()) {
			System.out.println(historia.getKey() +  "   " + historia.getValue());
		}
    }
    
    public void depositarCajero() {
    	
    	JLabel lbltituloDeposito = new JLabel("Monto a Depositar"); 
    	lbltituloDeposito.setFont(new Font(null, Font.BOLD, 25));
    	lbltituloDeposito.setBounds(200, 90, 570, 40);
    	
    	JLabel lblPeso = new JLabel("$ "); 
    	lblPeso.setBounds(180, 150, 200, 30);
    	lblPeso.setFont(new Font("", Font.PLAIN, 20));
    	
    	txtfMontoDeposito = new JTextField();
    	txtfMontoDeposito.setBounds(210, 150, 200, 30);
    	txtfMontoDeposito.setFont(new Font("", Font.PLAIN, 20));
    	
    	btnGuardarDeposito = new JButton("Continuar");
    	btnGuardarDeposito.setBounds(210, 250, 200, 30);
    	
    	
    	jpanelDepositar = new JPanel();
    	jpanelDepositar.setBackground(Color.WHITE);
    	jpanelDepositar.setLayout(null);
    	    	
    	jpanelDepositar.add(lbltituloDeposito);
    	jpanelDepositar.add(lblPeso);
    	jpanelDepositar.add(txtfMontoDeposito);  
    	jpanelDepositar.add(btnGuardarDeposito);  
    	
    	btnGuardarDeposito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				double montoDeposito = Double.parseDouble(txtfMontoDeposito.getText());	
				double montoMinimoDeposito = 20000;
				
				if (montoDeposito > montoMinimoDeposito) {
					
					usuarioCliente.depositarDinero(montoDeposito);
					lbltitulo.setText(String.valueOf(usuarioCliente.consultaSaldo()));
					
					JOptionPane.showMessageDialog(null, "Monto Depositado correctamente");
					txtfMontoDeposito.setText("");
					obtieneOpcionCardLayout(CARDJPANELSALDO); 
					
					Map<String, String> historialPrueba = adicionaHistorial(usuarioCliente, "Depositar Dinero", String.valueOf(montoDeposito));
			    	
			    	for (Map.Entry<String, String> historia : historialPrueba.entrySet()) {
						System.out.println(historia.getKey() +  "   " + historia.getValue());
					}
				} else {
					
					JOptionPane.showMessageDialog(null, "El monto a depositar debe se mayor a " + montoMinimoDeposito);
					txtfMontoRetiro.setText("");
				}			
			}
		});
    }
    
    public void retiroCajero() {
    	
    	JLabel lbltituloRetiro = new JLabel("Monto a Retirar"); 
    	lbltituloRetiro.setFont(new Font(null, Font.BOLD, 25));
    	lbltituloRetiro.setBounds(200, 90, 570, 40);
    	
    	JLabel lblPeso = new JLabel("$ "); 
    	lblPeso.setBounds(180, 150, 200, 30);
    	lblPeso.setFont(new Font("", Font.PLAIN, 20));
    	
    	txtfMontoRetiro = new JTextField();
    	txtfMontoRetiro.setBounds(210, 150, 200, 30);
    	txtfMontoRetiro.setFont(new Font("", Font.PLAIN, 20));
    	
    	btnGuardarRetiro = new JButton("Continuar");
    	btnGuardarRetiro.setBounds(210, 250, 200, 30);
    	
    	
    	jpanelRetiro = new JPanel();
    	jpanelRetiro.setBackground(Color.WHITE);
    	jpanelRetiro.setLayout(null);
    	    	
    	jpanelRetiro.add(lbltituloRetiro);
    	jpanelRetiro.add(lblPeso);
    	jpanelRetiro.add(txtfMontoRetiro);  
    	jpanelRetiro.add(btnGuardarRetiro);  
    	
    	btnGuardarRetiro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				double montoRetiro = Double.parseDouble(txtfMontoRetiro.getText());					
				double montoMinimoRetiro = 20000;
				
				if (montoRetiro > montoMinimoRetiro) {					
					
					if (Double.parseDouble(lbltitulo.getText()) > montoRetiro) {					
					    
						usuarioCliente.retirarDinero(montoRetiro);
						lbltitulo.setText("$ " + String.valueOf(usuarioCliente.consultaSaldo()));
						
						JOptionPane.showMessageDialog(null, "Monto Retirado correctamente");
						txtfMontoRetiro.setText("");
						obtieneOpcionCardLayout(CARDJPANELSALDO); 
					} else {
						
						JOptionPane.showMessageDialog(null, "El monto a retirar es mayor a lo reservado en la cuenta");
						txtfMontoRetiro.setText("");
					}	
				} else {
					
					JOptionPane.showMessageDialog(null, "El monto a retirar debe se mayor a " + montoMinimoRetiro);
					txtfMontoRetiro.setText("");
				}		
			}
		});
    	
    }
    
    public void historialCajero() {
    	
    	JLabel lbltituloHistorial = new JLabel("Historial de procesos");
    	JTable jtableHistorial = new JTable();
    	
    	jpanelHistorial = new JPanel();
    	jpanelHistorial.setLayout(new GridLayout());
    	jpanelHistorial.add(lbltituloHistorial, BorderLayout.NORTH);
    	jpanelHistorial.add(jtableHistorial, BorderLayout.CENTER);
    }
    
    public Map<String, String> adicionaHistorial(Cliente cliente, String clave, String value) {
    	
    	historialMovimientos.put(clave, value);
    	//cliente.AdicionaHistrorialOperaciones(clave, value);
    	return historialMovimientos;
    }
    
    public void obtieneOpcionCardLayout(String opcion) {
    	CardLayout obtieneOpcionSeleccionada = (CardLayout)(cardLayout.getLayout());
		obtieneOpcionSeleccionada.show(cardLayout, opcion);
    }

}
