package levelEditor;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keys extends KeyAdapter {
	
	public static boolean up, down, left, right, ctrl, q, w, dlt, e, s;
	
	public Keys() {
		up = down = left = right = ctrl = q = w = dlt = e = s = false;
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		switch(ke.getKeyCode()) {
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_CONTROL:
			ctrl = true;
			break;
		case KeyEvent.VK_Q:
			q = true;
			break;
		case KeyEvent.VK_W:
			w = true;
			break;
		case KeyEvent.VK_DELETE:
			dlt = true;
			break;
		case KeyEvent.VK_E:
			e = true;
			break;
		case KeyEvent.VK_S:
			s = true;
			break;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent ke) {
		switch(ke.getKeyCode()) {
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_CONTROL:
			ctrl = false;
			break;
		case KeyEvent.VK_Q:
			q = false;
			break;
		case KeyEvent.VK_W:
			w = false;
			break;
		case KeyEvent.VK_DELETE:
			dlt = false;
			break;
		case KeyEvent.VK_E:
			e = false;
			break;
		case KeyEvent.VK_S:
			s = false;
			break;
		}
	}
}
