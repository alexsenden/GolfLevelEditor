package levelEditor;

import java.awt.Graphics;
import java.awt.Polygon;

public abstract class Component {
	
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;
	
	protected int x1, y1;
	protected boolean selected;
	
	public Component(int x1, int y1) {
		this.x1 = x1;
		this.y1 = y1;
		selected = false;
	}
	
	public void move(int dir, int point) {
		int amount = 1;
		if(Keys.ctrl) {
			amount = 5;
		}
		switch(dir) {
		case LEFT:
			x1 -= amount;
			break;
		case RIGHT:
			x1 += amount;
			break;
		case UP:
			y1 += amount;
			break;
		case DOWN:
			y1 -= amount;
			break;
		default:
		}
			
	}
	
	public boolean getSelected(int point) {
		return selected;
	}
	
	public void select(int point) {
		selected = !selected;
	}
	
	public void deselect() {
		selected = false;
	}
	
	public String toString() {
		return x1 + " " + y1;
	}
	
	public abstract void draw(Graphics g);
	public abstract Polygon getBounds();
}
