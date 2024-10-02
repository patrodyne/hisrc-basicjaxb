import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class SetColumnHeader
{
	public static void main(String[] args) throws Exception
	{
		new SetColumnHeader();
	}

	public SetColumnHeader() throws Exception
	{
//		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		JFrame frame = new JFrame("Setting the Column Header!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		String data[][] = { { "100", "Vinod", "programmer", "5000" }, { "101", "Deepak", "Content Writer", "20000" },
							{ "102", "Noor", "Techniqual Writer", "30000" },
							{ "104", "Rinku", "PHP programar", "25000" } };
		String col[] = { "Emp_Id", "Emp_name", "Emp_depart", "Emp_sal" };
		
		DefaultTableModel model = new DefaultTableModel(data, col);
		JTable table = new JTable(model);
		
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.yellow);
		
		JScrollPane pane = new JScrollPane(table);
		panel.add(pane);
		frame.add(panel);
//		frame.setUndecorated(true);
//		frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		frame.setSize(460, 200);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
