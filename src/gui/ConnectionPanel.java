package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.MyClient;

public class ConnectionPanel extends JPanel implements ActionListener {
	
	private MainPanel mainPanel;
	private JButton connectionButton = new JButton("Connection");
	JTextField IPaddress = new JTextField("IP address");
	JTextField port = new JTextField("Port");
	BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
	String inputIP;
	String inputPort;
	MyClient client;

	public ConnectionPanel(MainPanel mainPanel){
		this.mainPanel = mainPanel;
		this.setLayout(bl);
		this.add(IPaddress);
		this.add(port);
		this.add(connectionButton);
		connectionButton.addActionListener(this);
		IPaddress.setAlignmentX(Component.CENTER_ALIGNMENT);
		port.setAlignmentX(Component.CENTER_ALIGNMENT);
		connectionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == connectionButton){
			inputIP = IPaddress.getText();
			inputPort = port.getText();
			try {
				client = new MyClient();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			if (client.connectTo(inputIP, inputPort)){
				mainPanel.setRegister(client);
			} else {
				mainPanel.setConnection();
			}
		}
		
	}

}
