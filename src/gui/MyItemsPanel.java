package gui;

import java.awt.Component;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import logic.Item;
import logic.MyClient;

public class MyItemsPanel extends JPanel{
	
	BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
	public MainPanel mainPanel;
	MyClient client;
	Item item;
	
	public MyItemsPanel(MainPanel mainPanel, MyClient client){
		this.mainPanel = mainPanel;
		this.client = client;
		this.setLayout(bl);
	}

	public void update() {
		Iterator<Item> it = client.getMyItemTable().iterator();
		while (it.hasNext()) {
			item = it.next();
			this.add(new ItemPanel(mainPanel, item,client,this));
		}
	}

}
