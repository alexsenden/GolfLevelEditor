package levelEditor;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class Mouse extends MouseAdapter {
    
	private static int x1, x2, y1, y2;
	
    public Mouse(){}
    
    @Override
    public void mouseClicked(MouseEvent e){
    	for(Component c : Main.getComps()) {
    		if(!checkIntersects(c, e) && !Keys.ctrl) {
    			c.deselect();
    		}
    	}
    }
    
    public boolean checkIntersects(Component c, MouseEvent e) {
    	if(c.getBounds().contains(new Point(e.getXOnScreen(), e.getYOnScreen() - 32))) {
			c.select(-1);
			return true;
		}
		
		//for(int i = 0; i < Main.WHEIGHT; i++) {
			//for(int j = 0; j < Main.WWIDTH; j++) {
			
	    		if(c instanceof Line) {
	    			Line l = (Line)c;
		    		if(new Ellipse2D.Double(l.x1 - 10, l.y1 - 10, 20, 20).contains(e.getXOnScreen(), Main.HEIGHT - e.getYOnScreen() + 32)) {
		    			l.select(Line.LEFT);
		    			if(l.getSelected(Line.RIGHT)) {
		    				l.select(Line.RIGHT);
		    			}
		    			if(l.getSelected(-1)) {
		    				l.select(-1);
		    			}
		    			return true;
		    			//System.out.println("Left: X: " + j + " Y: " + (i - 32));
		    		}
		    		else if(new Ellipse2D.Double(l.x2 - 10, l.y2 - 10, 20, 20).contains(e.getXOnScreen(), Main.HEIGHT - e.getYOnScreen() + 32)) {
		    			l.select(Line.RIGHT);
		    			if(l.getSelected(Line.LEFT)) {
		    				l.select(Line.LEFT);
		    			}
		    			if(l.getSelected(-1)) {
		    				l.select(-1);
		    			}
		    			return true;
		    			//System.out.println("Right: X: " + j + " Y: " + (i - 32));
		    		}
	    		}
    		
			//}
		//}
		
		//if(c == Main.getComps().get(4));
		//for(int i = 0; i < 4; i++) {
		//	System.out.println(c.getBounds().xpoints[i] + " " + c.getBounds().ypoints[i]);
    	//System.out.println("X: " + e.getXOnScreen() + " Y: " + (e.getYOnScreen() - 16));
		//}
    	return false;
    }
   
    @Override
    public void mousePressed(MouseEvent e) {
    	x1 = e.getXOnScreen();
    	y1 = Main.HEIGHT - e.getYOnScreen() + 32;
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
    	x2 = e.getXOnScreen();
    	y2 = Main.HEIGHT - e.getYOnScreen() + 32;
    	
    	if(Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) > 30){
    		Main.getComps().add(new Line(x1, y1, x2, y2));
    	}
    }
}
