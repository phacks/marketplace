package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.MyClient;
import logic.Wish;

public class WishPanel extends JPanel implements ActionListener {

	JPanel wishListPanel;
	private JButton removeWish = new JButton("Remove this wish");
	private MainPanel mainPanel;
	private Wish wish;
	private MyClient client;
	private JPanel wishList;
	


	public WishPanel(MainPanel mainPanel,Wish wish,MyClient client,JPanel wishList){
		this.mainPanel = mainPanel;
		this.wish = wish;
		this.client = client;
		this.wishList = wishList;
		BoxLayout listWishLayout = new BoxLayout(wishList, BoxLayout.Y_AXIS);
		wishList.setLayout(listWishLayout);
		JLabel nameItem = new JLabel(wish.getNameItem());
		JLabel priceItem = new JLabel(wish.getPriceItem() + " SEK");
		wishList.add(nameItem);
		wishList.add(priceItem);
		wishList.add(removeWish);
		removeWish.addActionListener(this);
		nameItem.setAlignmentX(Component.CENTER_ALIGNMENT);
		priceItem.setAlignmentX(Component.CENTER_ALIGNMENT);
		removeWish.setAlignmentX(Component.CENTER_ALIGNMENT);
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == removeWish){
			try {
				client.getServer().removeWish(wish);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
		}
		
	}

}
