import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

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
		
		TableColumnModel cm1 = header.getColumnModel();
		TableColumnModel cm2 = table.getColumnModel();
		
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
