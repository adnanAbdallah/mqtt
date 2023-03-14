package newProjectMqtt;

public class Main {
    public static void main(String[] args) {
//        InputNameFrame frame = new InputNameFrame();
        mainFrame frame = new mainFrame();
        frame.setVisible(true);
        System.out.println("the name is");
        while (frame.isVisible()) {
        	System.out.print(".");
            // Wait for the user to input their name
        }
    	System.out.println(".");

//        String name = frame.getName();
        System.out.println(frame.mytopic);
    }
}
