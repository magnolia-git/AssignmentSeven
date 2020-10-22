import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
import acm.graphics.*;
import acm.program.GraphicsProgram;
public class Interactors extends GraphicsProgram {
	
	// Constants
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	
	// Instance variables
	private JLabel label;
	private JTextField textField;
	private JButton addButton;
	private JButton removeButton;
	private JButton clearButton;
	private HashMap<String, GCompound> boxHolder;
	private GObject selected;
	private GPoint lastLocation;
	private String input;
	
	public void init() {
		boxHolder = new HashMap<>();
		label = new JLabel("Name");
		textField = new JTextField(30);
		addButton = new JButton("Add");
		removeButton = new JButton("Remove");
		clearButton = new JButton("Clear");
		add(label, SOUTH);
		add(textField, SOUTH);
		add(addButton, SOUTH);
		add(removeButton, SOUTH);
		add(clearButton, SOUTH);
		addActionListeners();
		addMouseListeners();
	}
	
	public void run() {
	}
	
	public void actionPerformed(ActionEvent e) {
		
		input = new String(textField.getText());
		
		if (addButton == e.getSource() && !boxHolder.containsKey(input)) {
			GCompound box = new GCompound();
			GLabel label = new GLabel(input);
			GRect rect = new GRect(BOX_WIDTH, BOX_HEIGHT);
			box.add(rect, -BOX_WIDTH / 2, -BOX_HEIGHT / 2);
			box.add(label, -label.getWidth() / 2, label.getDescent() / 2);
			add(box,getWidth() / 2, getHeight() / 2);
			boxHolder.put(input, box);
		}
		
		if (removeButton == e.getSource() && boxHolder.containsKey(input)) {
			boxHolder.get(input).removeAll();
			boxHolder.remove(input);
		}
		
		if (clearButton == e.getSource()) {
			
			for (String key : boxHolder.keySet()) {
				boxHolder.get(key).removeAll();
			}
		boxHolder.clear();
		}
		
	}
	public void mousePressed(MouseEvent e) {		// For "selecting" one of the objects
		lastLocation = new GPoint(e.getPoint());
		selected = getElementAt(lastLocation);
	}
	
	public void mouseDragged(MouseEvent e) {		// For dragging one of the objects
		if (selected != null) {
			selected.move(e.getX() - lastLocation.getX(), e.getY() - lastLocation.getY());
			lastLocation = new GPoint(e.getPoint());
		}
	}	
}
