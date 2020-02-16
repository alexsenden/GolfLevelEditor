package levelEditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Line extends Component {
	
	public static final int BOTH = 4;
	
	protected int x2, y2;
	private boolean r, l;
	
	public Line(int x1, int y1, int x2, int y2) {
		super(x1, y1);
		this.x2 = x2;
		this.y2 = y2;
		selected = l = r = false;
		fixLR();
	}
	
	public void move() {
		if(r) {
			
		}
	}
	
	public void move(int dir, int point) {
		int amount = 1;
		if(Keys.ctrl) {
			amount = 5;
		}
		boolean left, right;
		right = left = false;
		switch(point) {
		case RIGHT:
			right = true;
			break;
		case LEFT:
			left = true;
			break;
		default:
			right = left = true;
		}
		
		switch(dir) {
		case LEFT:
			if(left) {
				x1 -= amount;
			}
			if(right) {
				x2 -= amount;
			}
			break;
		case RIGHT:
			if(left) {
				x1 += amount;
			}
			if(right) {
				x2 += amount;
			}
			break;
		case UP:
			if(left) {
				y1 += amount;
			}
			if(right) {
				y2 += amount;
			}
			break;
		case DOWN:
			if(left) {
				y1 -= amount;
			}
			if(right) {
				y2 -= amount;
			}
			break;
		default:
		}
		fixLR();
	}
	
	public void makeHorizontal() {
		y1 = y2;
	}
	
	public void makeVertical() {
		x1 = x2;
	}
	
	public void delete() {
		x1 = x2 = y1 = y2 = -8067887;
	}
	
	public Point getPoint(int side) {
		switch(side) {
		case LEFT:
			return new Point(x1, y1);
		case RIGHT:
			return new Point(x2, y2);
		}
		return null;
	}
	
	public void setTo(Point p, int side) {
		switch(side) {
		case LEFT:
			x1 = p.x;
			y1 = p.y;
			break;
		case RIGHT:
			x2 = p.x;
			y2 = p.y;
			break;
		}
	}
	
	public void select(int point) {
		switch(point) {
		case LEFT:
			System.out.println(l);
			l = !l;
			System.out.println(l);
			break;
		case RIGHT:
			r = !r;
			break;
		default: 
			l = r = selected = !selected;
			break;
		}
	}
	
	@Override
	public void draw(Graphics g) {
		if(selected) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.BLACK);
		}
		g.drawLine(x1, Main.HEIGHT - y1, x2, Main.HEIGHT - y2);
		//g.drawPolygon(getBounds());
		
		if(l) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.BLACK);
		}
		g.drawOval(x1 - 3, Main.HEIGHT - y1 - 3, 6, 6);
		//g.drawOval(x1 - 10, Main.HEIGHT - y1 - 10, 20, 20);
		
		if(r) {
			g.setColor(Color.RED);
		} else {
			g.setColor(Color.BLACK);
		}
		g.drawOval(x2 - 3, Main.HEIGHT - y2 - 3, 6, 6);
		//g.drawOval(x2 - 10, Main.HEIGHT - y2 - 10, 20, 20);
	}
	
	public void fixLR() {
		if(x1 > x2) {
			int temp = x1;
			x1 = x2;
			x2 = temp;
			temp = y1;
			y1 = y2;
			y2 = temp;
		}
	}

	@Override
	public Polygon getBounds() {
		if(Math.abs(x2 - x1) > Math.abs(y2 - y1)) {
			return new Polygon(new int[]{x1 + 10, x1 + 10, x2 - 10, x2 - 10}, new int[]{Main.HEIGHT - y1 + 10, Main.HEIGHT - y1 - 10, Main.HEIGHT - y2 - 10, Main.HEIGHT - 
				y2 + 10}, 4);
		}
		else {
			if(y1 < y2) {
				return new Polygon(new int[]{x1 - 10, x1 + 10, x2 + 10, x2 - 10}, new int[]{Main.HEIGHT - y1 - 10, Main.HEIGHT - y1 - 10, Main.HEIGHT - y2 + 10, Main.HEIGHT - 
						y2 + 10}, 4);
			}
			else {
				return new Polygon(new int[]{x1 - 10, x1 + 10, x2 + 10, x2 - 10}, new int[]{Main.HEIGHT - y1 + 10, Main.HEIGHT - y1 + 10, Main.HEIGHT - y2 - 10, Main.HEIGHT - 
						y2 - 10}, 4);
			}
		}
	}
	
	@Override
	public void deselect() {
		l = r = selected = false;
	}
	
	@Override
	public boolean getSelected(int point) {
		switch(point) {
		case LEFT:
			return l;
		case RIGHT:
			return r;
		default:
			return selected;
		}
	}
	
	@Override
	public String toString() {
		return x1 + " " + y1 + " " + x2 + " " + y2;
	}
}
