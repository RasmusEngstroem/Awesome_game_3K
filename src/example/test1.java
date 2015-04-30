package example;

public class test1 {

	import javax.swing.ImageIcon;
	import javax.swing.JFrame;
	import javax.swing.JOptionPane;
	 
	public class ShowMessageDialogExample1
	{
	  public static void main(String[] args)
	  {
	    String backupDir = "/Users/al/backups";
	     //khajdakjskjhsdjkhasjkahsfkjahaæjdhaældkfhaælkhaælkahælkajdælakdjældk
	    // create a jframe
	    JFrame frame = new JFrame("JOptionPane showMessageDialog example");
	     
	    // show a joptionpane dialog using showMessageDialog
	    JOptionPane.showMessageDialog(frame,
	        "Problem writing to backup directory: '" + backupDir + "'.");
	    System.exit(0);
	  }
	}

}
