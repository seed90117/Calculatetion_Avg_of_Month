package UserInterface;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import IO.LoadFile;
import IO.SaveFile;
import Program.Compute;

public class MainView extends JFrame {
	
	/**
	 * M10456012
	 * Kevin Yen
	 * kelly10056040@gmail.com
	 */
	private static final long serialVersionUID = 1L;
	private boolean isLoad = false;
	private String dataSetName = "";
	
	//*****宣告介面*****//
	Container cp = this.getContentPane();
	
	//*****宣告元件*****//
	JLabel nullDataLabel = new JLabel("空值符號: ");
	JLabel fileNameLabel = new JLabel("Excel: ");
	JLabel statusLabel = new JLabel("Status: ");
	JTextField nullDataTextField = new JTextField();
	JButton loadFileButton = new JButton("Load File");
	JButton exportFileButton = new JButton("Export Excel");
	
	JFileChooser open = new JFileChooser();
	
	MainView()
	{
		//*****設定介面*****//
		this.setSize(300, 300);
		this.setLayout(null);
		this.setTitle("Computing Avg of Month");
		
		//*****設定元件位置*****//
		nullDataLabel.setBounds(30, 30, 100, 30);
		nullDataTextField.setBounds(100, 30, 50, 30);
		loadFileButton.setBounds(30, 80, 200, 30);
		exportFileButton.setBounds(30, 130, 200, 30);
		fileNameLabel.setBounds(30, 180, 250, 30);
		statusLabel.setBounds(30, 230, 200, 30);
		
		cp.add(nullDataLabel);
		cp.add(nullDataTextField);
		cp.add(exportFileButton);
		cp.add(loadFileButton);
		cp.add(fileNameLabel);
		cp.add(statusLabel);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
		loadFileButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				LoadFile loadFile = new LoadFile();
				String tmpStr = loadFile.loadfile(open);
				if (!tmpStr.equals("")) {
					dataSetName = tmpStr;
				}
				fileNameLabel.setText("Excel: " + dataSetName);
				if (!fileNameLabel.getText().equals("Excel: "))
					isLoad = true;
			}});
		
		exportFileButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (isLoad) {
					try {
						Compute compute = new Compute();
						if (!nullDataTextField.getText().equals("")) {
							compute.setNoData(nullDataTextField.getText());
						}
						compute.getComputeResults();
						SaveFile saveFile = new SaveFile();
						saveFile.saveFile(dataSetName);
						statusLabel.setText("Status: Success");
					} catch (Exception ex) {
						statusLabel.setText("Status: Fail");
					}
					
				}
			}});
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainView();
	}
}
