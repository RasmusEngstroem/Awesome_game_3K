package example;

public class test1 {
	//janik

	import javax.swing.ImageIcon;
	import javax.swing.JFrame;
	import javax.swing.JOptionPane;
	 
	public class ShowMessageDialogExample1
	{
	  public static void main(String[] args)
	  {
		  //j
		  //does my thing work again? 
	    String backupDir = "/Users/al/backups";
	     //khajdakjskjhsdjkhasjkahsfkjaha�jdha�ldkfha�lkha�lkah�lkajd�lakdj�ldk
	    // create a jframe
	    JFrame frame = new JFrame("JOptionPane showMessageDialog example");
	     
	    // show a joptionpane dialog using showMessageDialog
	    JOptionPane.showMessageDialog(frame,
	        "Problem writing to backup directory: '" + backupDir + "'.");
	    System.exit(0);
	  }
	}

}
