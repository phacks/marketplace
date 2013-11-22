package gui;


import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.MyClient;
import logic.ServerInterface;


@SuppressWarnings("serial")
public class RegisterPanel extends JPanel implements ActionListener{

	private JButton registerButton = new JButton("Register");
	MainPanel mainPanel;
	String inputName;
	JTextField name = new JTextField("Name");
	BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
	MyClient client;
	//private ServerInterface server = new ServerInterface();

	public RegisterPanel(MainPanel mainPanel, MyClient client){
		this.mainPanel = mainPanel;
		this.client = client;
		this.setLayout(bl);
		this.add(name);
		this.add(registerButton);
		registerButton.addActionListener(this);
		name.setAlignmentX(Component.CENTER_ALIGNMENT);
		registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == registerButton){
			inputName = name.getText();
			try {
				
				client.getServer().registerClient(inputName);
				client.setName(inputName);
				List<String> clientTable = client.getServer().getClients();
				if (clientTable.contains(inputName)){
					mainPanel.setMarketplace(client,inputName);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		
		
	}

}
