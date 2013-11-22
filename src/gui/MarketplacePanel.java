package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logic.MyClient;

public class MarketplacePanel extends JPanel implements ActionListener {
	private JTabbedPane menu = new JTabbedPane();
	private MainPanel mainPanel;
	private JButton unregisterButton = new JButton("Unregister");
	private JPanel southPanel = new JPanel();
	MyClient client;
	String name;
	MyItemsPanel itemsPanel;

	public MarketplacePanel (MainPanel mainPanel, MyClient client, String name){
		this.mainPanel = mainPanel;
		this.client = client;
		this.name = name;

		itemsPanel = new MyItemsPanel(mainPanel, client);
		this.setPreferredSize(new Dimension(mainPanel.getWidth() - 200, mainPanel.getHeight() - 100));

		this.setLayout(new BorderLayout());

		southPanel.setLayout(new FlowLayout());
		//unregisterButton.setSize(200, 100);

		this.add(southPanel, BorderLayout.SOUTH);
		southPanel.add(unregisterButton);

		setMenu();
		this.add(menu, BorderLayout.CENTER);
		unregisterButton.addActionListener(this);

		menu.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(menu.getSelectedIndex() == 1){
					itemsPanel.removeAll();
					itemsPanel.update();
					itemsPanel.repaint();
					itemsPanel.revalidate();
				}
			}
		});
	}



	private void setMenu() {
		menu.addTab("Marketplace", new AvailableItemsPanel());
		menu.addTab("My Items", itemsPanel);
		menu.addTab("Add Items", new AddItemsPanel(mainPanel,client));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == unregisterButton){
			try {
				client.getServer().unregisterClient(name);
				List<String> clientTable = client.getServer().getClients();
				if (!clientTable.contains(name)){
					mainPanel.setConnection();
				}
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
	}

}