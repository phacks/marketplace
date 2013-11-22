package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import logic.Item;
import logic.MyClient;

public class ItemPanel extends JPanel implements ActionListener {

	public Item item;

	Font fontName = new Font("Cambria", Font.BOLD,15);
	Font fontDescription = new Font("Cambria", Font.ITALIC,12);
	JButton remove = new JButton("Remove");
	JButton sell = new JButton("Sell");
	public MainPanel mainPanel;
	public MyClient client;
	JPanel abovePanel = new JPanel();
	JPanel belowPanel = new JPanel();

	BoxLayout mainLayout = new BoxLayout(this,BoxLayout.Y_AXIS);
	BoxLayout above = new BoxLayout(abovePanel, BoxLayout.Y_AXIS);
	BoxLayout below = new BoxLayout(belowPanel, BoxLayout.X_AXIS);

	private MyItemsPanel myItemsPanel;

	public ItemPanel(MainPanel mainPanel,Item item,MyClient client,MyItemsPanel myItemsPanel){
		this.myItemsPanel = myItemsPanel;
		this.mainPanel = mainPanel;
		this.item = item;
		this.client = client;
		this.setLayout(mainLayout);
		JLabel nameItem = new JLabel(item.getName());
		JLabel descriptionItem = new JLabel(item.getDescription());
		this.add(abovePanel);
		this.add(belowPanel);
		abovePanel.setLayout(above);
		belowPanel.setLayout(below);

		abovePanel.add(nameItem);
		abovePanel.add(descriptionItem);

		belowPanel.add(sell);
		belowPanel.add(remove);

		nameItem.setFont(fontName);
		descriptionItem.setFont(fontDescription);

		sell.addActionListener(this);
		remove.addActionListener(this);
		descriptionItem.setAlignmentX(Component.CENTER_ALIGNMENT);
		nameItem.setAlignmentX(Component.CENTER_ALIGNMENT);
		sell.setAlignmentX(Component.CENTER_ALIGNMENT);
		remove.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.setBorder(BorderFactory.createLineBorder(Color.black));

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sell){
			/*Object[] possibilities = {"ham", "spam", "yam"};
			String s = (String)JOptionPane.showInputDialog(frame,"Complete the sentence:\n" + "\"Green eggs and...\"","Customized Dialog",
					JOptionPane.PLAIN_MESSAGE,icon,possibilities,"ham");
*/
		} else {
			client.removeItem(item);
			myItemsPanel.removeAll();
			myItemsPanel.update();
			myItemsPanel.repaint();
			myItemsPanel.revalidate();
		}

	}

}
