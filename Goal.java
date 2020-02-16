package levelEditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Goal extends Component {

	private Line[] lines;
	
	public Goal(int x1, int y1) {
		super(x1, y1);
		lines = new Line[]{new Line(x1, y1, x1, y1 - 32), new Line(x1, y1 - 32, x1 + 32, y1 - 32), new Line(x1 + 32, y1 - 32, x1 + 32, y1)};
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		for(Line l : lines) {
			l.draw(g);
		}
		//g.drawPolygon(getBounds());
	}

	@Override
	public Polygon getBounds() {
		return new Polygon(new int[] {x1, x1 + 32, x1 + 32, x1}, new int[] {Main.HEIGHT - y1, Main.HEIGHT - y1, Main.HEIGHT - y1 + 32, Main.HEIGHT - y1 + 32}, 4);
	}
	
	@Override
	public void move(int dir, int point) {
		super.move(dir, -1);
		for(Line l : lines) {
			l.move(dir, -1);
		}
	}
	
	@Override
	public void select(int num) {
		super.select(-1);
		for(Line l : lines) {
			l.select(-1);
		}
	}
	
	@Override
	public void deselect() {
		super.deselect();
		for(Line l : lines) {
			l.deselect();
		}
	}
	
	public Line[] getLines() {
		return lines;
	}
}
