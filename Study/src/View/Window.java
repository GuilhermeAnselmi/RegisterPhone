package View;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Model.Data;
import Control.Client;

public class Window extends JFrame {
	private JLabel lblTitle;
	private JLabel lblName;
	private JLabel lblPhone;
	private JLabel lblResName;
	private JLabel lblResPhone;
	
	private JLabel resName;
	private JLabel resPhone;

	private JTextField txtName;
	private JTextField txtPhone;

	private JButton btnRegister;
	private JButton btnRequest;
	
	private Container win;

	public Window() {
		init();
	}

	public void init(){
		win = getContentPane();
		win.setLayout(null);
		
		lblTitle = new JLabel("Armazem de Telefone");
		lblTitle.setBounds(100, 10, 150, 20);
		win.add(lblTitle);

		lblName = new JLabel("Nome: ");
		lblName.setBounds(10, 40, 120, 20);
		win.add(lblName);
		
		lblPhone = new JLabel("Telefone: ");
		lblPhone.setBounds(10, 70, 120, 20);
		win.add(lblPhone);
		
		lblResName = new JLabel("Nome: ");
		lblResName.setBounds(90, 120, 80, 20);
		lblResName.setVisible(false);
		win.add(lblResName);
		
		lblResPhone = new JLabel("Telefone: ");
		lblResPhone.setBounds(90, 140, 80, 20);
		lblResPhone.setVisible(false);
		win.add(lblResPhone);
		
		resName = new JLabel("Teste");
		resName.setBounds(150, 120, 120, 20);
		resName.setVisible(false);
		win.add(resName);
		
		resPhone = new JLabel("Teste");
		resPhone.setBounds(150, 140, 120, 20);
		resPhone.setVisible(false);
		win.add(resPhone);

		txtName = new JTextField();
		txtName.setBounds(120, 40, 200, 20);
		win.add(txtName);
		
		txtPhone = new JTextField();
		txtPhone.setBounds(120, 70, 200, 20);
		win.add(txtPhone);

		btnRegister = new JButton("Cadastrar-se");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((txtName.getText() == null || txtName.getText().equals("")) && (txtPhone.getText() == null || txtPhone.getText().equals(""))) {
					JOptionPane.showMessageDialog(null, "Please, information the name and phone", "Data not found", JOptionPane.INFORMATION_MESSAGE);
				}else {
					boolean NAN = true, NAD = true;
					String name = txtName.getText().trim().toLowerCase();
					String[] cap = name.split(" ");
					String phone = txtPhone.getText().replace(" ", "");
					
					//for(int i = 0; i < name.length(); i++) {
					//	if(Character.isLetter(name.charAt(i)) == false) {
					//		NAD = false;
					//		break;
					//	}
					//}
					
					name = "";
					
					for(int i = 0; i < cap.length; i++) {
						cap[i] = cap[i].substring(0, 1).toUpperCase() + cap[i].substring(1);
						name = name + " " + cap[i];
					}
					
					name = name.trim();
					
					for(int i = 0; i < phone.length(); i++) {
						if(Character.isDigit(phone.charAt(i)) == false) {
							NAN = false;
							break;
						}
					}
					
					if(NAN && NAD) {
						Send(name, phone);
					}else if(NAN == false) {
						JOptionPane.showMessageDialog(null, "The phone type is not supported", "Imcompatible type", JOptionPane.INFORMATION_MESSAGE);
					}else if(NAD == false) {
						JOptionPane.showMessageDialog(null, "The name type is not supported", "Imcompatible type", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btnRegister.setBounds(100, 220, 150, 20);
		win.add(btnRegister);
		
		btnRequest = new JButton("Pesquisa");
		btnRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtName.getText() == null || txtName.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please, informationthe name", "Data not found", JOptionPane.INFORMATION_MESSAGE);
				}else {
					Request();
				}
			}
		});
		btnRequest.setBounds(100, 250, 150, 20);
		win.add(btnRequest);

		setSize(350,350);
		setVisible(true);
		setResizable(false);
	}
	
	protected void Send(String name, String phone) {
		Client client = new Client(name, phone);
		
		System.out.println("Nome: " + client.getName());
	    System.out.println("Telefone: " + client.getPhone());
	    
	    Data data = new Data();
	    int rs = data.Write("insert into test([name], [phone]) values('" + client.getName() + "', '" + client.getPhone() + "')");
	    System.out.println(rs);
	    
	    if(rs == 1) {
	    	JOptionPane.showMessageDialog(null, "Data sent successfully!", "Success!", JOptionPane.PLAIN_MESSAGE);
	    }else {
	    	JOptionPane.showMessageDialog(null, "Server Error: Impossible establish connect in database", "Server Error", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	protected void Request() {
		Data data = new Data();
		String[] rs = new String[2];
		rs = data.Read("select * from test where [name] LIKE '%" + txtName.getText() + "%'");
		
		System.out.println(rs);
		
		if(rs[0].equals("")) {
			JOptionPane.showMessageDialog(null, "Name entered does not exist", "Data not found", JOptionPane.WARNING_MESSAGE);
		}else {
			lblResName.setVisible(true);
			lblResPhone.setVisible(true);
			
			resName.setVisible(true);
			resName.setText(rs[0]);
			resPhone.setVisible(true);
			resPhone.setText(rs[1]);
		}
	}
}