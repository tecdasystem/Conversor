package principal;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
//import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

import conversorMonedas.function;
import conversorTemperatura.functionTemperatura;
import javax.swing.JLabel;
import java.awt.Color;


public class principal extends JFrame {

	private JPanel contentPane;
	FondoPanel fondo = new FondoPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principal frame = new principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public principal() {
		setResizable(false);
		setTitle("CONVERSOR");
		this.setContentPane(fondo);
		setIconImage(Toolkit.getDefaultToolkit().getImage(principal.class.getResource("/image/power1.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione una opción:");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(104, 67, 188, 26);
		contentPane.add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Conversor de moneda", "Conversor de temperatura" }));
		comboBox.setBounds(104, 104, 233, 22);
		contentPane.add(comboBox);

		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				function monedas = new function();
				functionTemperatura temperatura = new functionTemperatura();
				String Item;

				Item = comboBox.getSelectedItem().toString();

				if (Item.equalsIgnoreCase("Conversor de moneda")) {
					String input = JOptionPane.showInputDialog("Ingresa la cantidad de dinero que deseas convertir: ");
					if (ValidarNumero(input) == true) {
						double Minput = Double.parseDouble(input);
						monedas.ConvertirMonedas(Minput);

						int respuesta = JOptionPane.showConfirmDialog(null, "¿Deseas realizar otra conversión?");
						if (JOptionPane.OK_OPTION == respuesta) {
							System.out.println("Selecciona opción Afirmativa");
						} else {
							JOptionPane.showMessageDialog(null, "Programa terminado");
						}

					} else {
						JOptionPane.showMessageDialog(null, "Valor inválido");
					}
					// break;

				} else {
					if (Item.equalsIgnoreCase("Conversor de temperatura")) {

						String input = JOptionPane
								.showInputDialog("Ingresa el valor de la temperatura que deseas convertir ");
						if (ValidarNumero(input) == true) {
							double Minput = Double.parseDouble(input);
							temperatura.ConvertirTemperatura(Minput);

							int respuesta = 0;
							respuesta = JOptionPane.showConfirmDialog(null, "¿Desea continuar?");
							if ((respuesta == 0) && (ValidarNumero(input) == true)) {
							} else {
								JOptionPane.showMessageDialog(null, "Programa terminado");
							}

						} else {
							JOptionPane.showMessageDialog(null, "Valor inválido");
						}
						// break;
					}

				}

			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(73, 187, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnCancel.setBounds(265, 189, 112, 23);
		contentPane.add(btnCancel);
		
		JPanel panel = new FondoPanel();
		panel.setBounds(10, 11, 414, 239);
		contentPane.add(panel);
		panel.setLayout(null);
	}

	public static boolean ValidarNumero(String input) {
		try {
			double x = Double.parseDouble(input);
			if (x >= 0 || x < 0)
				;
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	class FondoPanel extends JPanel {

		private Image imagen;

		@Override
		public void paint(Graphics g) {
			imagen = new ImageIcon(getClass().getResource("/image/fondo.png")).getImage();

			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

			setOpaque(false);

			super.paint(g);
		}

	}
}