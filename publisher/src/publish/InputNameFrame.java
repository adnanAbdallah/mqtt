//package publish;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class InputNameFrame extends JFrame {
//    private JPanel contentPane;
//    private JTextField nameField;
//    private JButton submitButton;
//    private String name;
//
//    public InputNameFrame() {
//        setTitle("Input Name");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setBounds(100, 100, 450, 250);
//        contentPane = new JPanel();
//        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
//        setContentPane(contentPane);
//        contentPane.setLayout(new GridLayout(2, 1, 5, 5));
//
//        JLabel nameLabel = new JLabel("Name:");
//        contentPane.add(nameLabel);
//
//        nameField = new JTextField();
//        contentPane.add(nameField);
//
//        submitButton = new JButton("Submit");
//        submitButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                name = nameField.getText();
//                dispose();
//            }
//        });
//        contentPane.add(submitButton);
//    }
//
//    public String getName() {
//        return name;
//    }
//}
