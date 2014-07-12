package ui;

import internationalization.Messages;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.script.ScriptException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import engine.Loader;

public class Control extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String newLine = System.getProperty("line.separator");
	private JButton openFile;
	private JButton execute;
	private JTextFieldCustom functionName;
	private JPasswordFieldCustom password;
	private JTextArea answer;
	private Loader loader;
	
	public static void main(String[] args) {
		new Control();
		Messages.init();
	}
	
	private void initComponents() {
		openFile = new JButton("Open");
		execute = new JButton("Execute");
		answer = new JTextArea(20,20);
		execute.setEnabled(false);
		functionName = new JTextFieldCustom("Method");
		password = new JPasswordFieldCustom("Parameters");
	}
	
	public Control() {
		initComponents();
		setActions();
		setWindowBuild();
		configuration();
	}
	
	private void configuration() {
		setTitle("Javascript-Java Runner");
		setIconImage(new ImageIcon("src/img/icon.png").getImage());
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void setWindowBuild() {
		setLayout(new BorderLayout());
		JPanel topPanel = new JPanel(new FlowLayout());
		topPanel.add(openFile);
		topPanel.add(functionName);
		topPanel.add(password);
		topPanel.add(execute);
		this.add(topPanel, BorderLayout.NORTH);
		JScrollPane center = new JScrollPane(answer);
		this.add(center, BorderLayout.CENTER);
	}

	private void setActions() {
		openFile.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				loader = new Loader(loadJs());
				answer.append(loader.fileLoaded() + newLine);
				answer.append(loader.getFileName() + newLine);
				execute.setEnabled(loader != null);
			}
		});
		
		execute.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					answer.append(loader.execute(functionName.getText(), getParameters()).toString() + newLine);
				} catch (NoSuchMethodException e1) {
					answer.append(Messages.getMessage("exception.noSuchMethod") + newLine);
				} catch (IOException e1) {
					answer.append(Messages.getMessage("exception.iO") + newLine);
				} catch (ScriptException e1) {
					answer.append(Messages.getMessage("exception.script") + newLine);
				}
			}
		});
	}
	
	private Object[] getParameters() {
		Object[] parameters = new String(password.getPassword()).split(",");
		return  parameters;
	}
	
	private File loadJs() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new JSFilter());
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		}
		return null;
	}
}

