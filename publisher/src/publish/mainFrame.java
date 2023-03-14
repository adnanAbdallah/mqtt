package publish;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class mainFrame extends JFrame {

	private JPanel contentPane;
	public JTextField topicIn;
	private JTextField messageIn;
	public String mytopic;
	public String mymessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame frame = new mainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainFrame() {
		setTitle("publish");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 251);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel topic = new JLabel("topic");
		topic.setBounds(10, 11, 382, 14);
		contentPane.add(topic);
		
		JLabel message = new JLabel("message");
		message.setBounds(10, 73, 46, 14);
		contentPane.add(message);
		
		topicIn = new JTextField();
		topicIn.setBounds(20, 36, 280, 20);
		contentPane.add(topicIn);
		topicIn.setColumns(10);
		
		messageIn = new JTextField();
		messageIn.setBounds(20, 99, 280, 20);
		contentPane.add(messageIn);
		messageIn.setColumns(10);
		
		JButton btnPublish = new JButton("publish");
		btnPublish.setBounds(137, 164, 126, 23);
		btnPublish.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                mytopic = topicIn.getText();
	                mymessage 
	                = messageIn.getText();
	                dispose();
	            }
	        });
		contentPane.add(btnPublish);
		
		
	}
}
