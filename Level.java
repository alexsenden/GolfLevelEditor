package levelEditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Level extends JPanel {

	private static final long serialVersionUID = 1L;
	private ArrayList<Component> components;
	
	public Level() {
		components = new ArrayList<Component>();
		components.add(new Spawn(100, 100));
		components.add(new Goal(900, 200));
		components.add(new Line(0, 0, 0, Main.HEIGHT));
		components.add(new Line(0, 0, Main.WIDTH, 0));
		components.add(new Line(Main.WIDTH, Main.HEIGHT, Main.WIDTH, 0));
		components.add(new Line(Main.WIDTH, Main.HEIGHT, 0, Main.HEIGHT));
		components.add(new Line(200, 400, 800, 200));
		
		
	}
	
	public void add(Component c) {
		components.add(c);
	}
	
	public void remove(Component c) {
		components.remove(c);
	}
	
	public BufferedImage bufferFrame() {
		BufferedImage bimg = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = bimg.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		for(Component c : components) {
			c.draw(g);
		}
		return bimg;
	}
	
	public void paintComponent(Graphics g) {
		BufferedImage bimg = bufferFrame();
		g.drawImage(bimg, 0, 0, this);
	}
	
	public void draw() {
		paintComponent(this.getGraphics());
	}
	
	public ArrayList<Component> getComps(){
		return components;
	}
}
