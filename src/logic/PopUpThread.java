package logic;

import javax.swing.JOptionPane;

class PopUpThread extends Thread {
	
    public PopUpThread() {
        super();
    }
    
    public void run() {
    	JOptionPane.showMessageDialog(null,"Item sold");
    }
    
}