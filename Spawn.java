package levelEditor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Spawn extends Component {

	public Spawn(int x1, int y1) {
		super(x1, y1);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawOval(x1 - 8, Main.HEIGHT - y1 - 8, 16, 16);
		//g.drawPolygon(getBounds());
	}

	@Override
	public Polygon getBounds() {
		return new Polygon(new int[] {x1 - 8, x1 + 8, x1 + 8, x1 - 8}, new int[] {Main.HEIGHT - y1 - 8, Main.HEIGHT - y1 - 8, Main.HEIGHT - y1 + 8, Main.HEIGHT - y1 + 8}, 4);
	}

}
