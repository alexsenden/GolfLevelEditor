package levelEditor;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Main {
	
	public static final int WWIDTH = 1080;
	public static final int WHEIGHT = 608;
	
	public static final int WIDTH = 1080 - 16;
	public static final int HEIGHT = 608 - 40;
	
	private static Level l;

	public static void main(String[] args) {
		JFrame f = new JFrame("Level Editor");
		l = new Level();
		f.setBounds(-10, 0, WWIDTH, WHEIGHT);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);
		f.setFocusable(true);
		f.requestFocus();
		f.addKeyListener(new Keys());
		f.addMouseListener(new Mouse());
		f.add(l);
		f.setVisible(true);
		
		ArrayList<Component> toDelete;
		
		long lastTime = System.nanoTime();
		long ctime;
		
		long lastptime = System.currentTimeMillis();
		
		while(true) {
			toDelete = new ArrayList<Component>();
			ctime = System.nanoTime();
			
			if(ctime - lastTime > 1000000000.0 / 60) {
				if(Keys.up) {
					for(Component c : Main.getComps()) {
						if(c instanceof Line) {
							Line l = (Line)c;
							if(l.getSelected(-1)) {
								l.move(Line.UP, -1);
							}
							else if(l.getSelected(Line.LEFT)) {
								l.move(Line.UP, Line.LEFT);
							}
							else if(l.getSelected(Line.RIGHT)) {
								l.move(Line.UP, Line.RIGHT);
							}
						}
						else if(c.getSelected(-1)) {
							c.move(Component.UP, -1);
						}
					}
				}
				else if(Keys.down) {
					for(Component c : Main.getComps()) {
						if(c instanceof Line) {
							Line l = (Line)c;
							if(l.getSelected(-1)) {
								l.move(Line.DOWN, -1);
							}
							else if(l.getSelected(Line.LEFT)) {
								l.move(Line.DOWN, Line.LEFT);
							}
							else if(l.getSelected(Line.RIGHT)) {
								l.move(Line.DOWN, Line.RIGHT);
							}
						}
						else if(c.getSelected(-1)) {
							c.move(Component.DOWN, -1);
						}
					}
				}
				else if(Keys.left) {
					for(Component c : Main.getComps()) {
						if(c instanceof Line) {
							Line l = (Line)c;
							if(l.getSelected(-1)) {
								l.move(Line.LEFT, -1);
							}
							else if(l.getSelected(Line.LEFT)) {
								l.move(Line.LEFT, Line.LEFT);
							}
							else if(l.getSelected(Line.RIGHT)) {
								l.move(Line.LEFT, Line.RIGHT);
							}
						}
						else if(c.getSelected(-1)) {
							c.move(Component.LEFT, -1);
						}
					}
				}
				else if(Keys.right) {
					for(Component c : Main.getComps()) {
						if(c instanceof Line) {
							Line l = (Line)c;
							if(l.getSelected(-1)) {
								l.move(Line.RIGHT, -1);
							}
							else if(l.getSelected(Line.LEFT)) {
								l.move(Line.RIGHT, Line.LEFT);
							}
							else if(l.getSelected(Line.RIGHT)) {
								l.move(Line.RIGHT, Line.RIGHT);
							}
						}
						else if(c.getSelected(-1)) {
							c.move(Component.RIGHT, -1);
						}
					}
				}
				else if(Keys.q) {
					for(Component c : Main.getComps()) {
						if(c instanceof Line) {
							Line l = (Line)c;
							if(l.getSelected(-1)) {
								l.makeHorizontal();
							}
						}
					}
				}
				else if(Keys.w) {
					for(Component c : Main.getComps()) {
						if(c instanceof Line) {
							Line l = (Line)c;
							if(l.getSelected(-1)) {
								l.makeVertical();
							}
						}
					}
				}
				else if(Keys.dlt) {
					for(Component c : Main.getComps()) {
						if(c instanceof Line) {
							Line l = (Line)c;
							if(l.getSelected(-1)) {
								toDelete.add(l);
							}
						}
					}
				}
				else if(Keys.e) {
					for(Component c : Main.getComps()) {
						if(c instanceof Line) {
							Line l = (Line)c;
							if(l.getSelected(-1)) {
								for(Component c2 : Main.getComps()) {
									if(c2 instanceof Goal) {
										Goal g = (Goal)c2;
										Line[] lines = g.getLines();
										for(Line l2 : lines) {
											adjustLine(l, l2);
										}
									}
									if(c2 instanceof Line && c2 != c) {
										Line l2 = (Line)c2;
										adjustLine(l, l2);
									}
								}
							}
						}
					}
				}
			}
			l.draw();
			for(Component c : toDelete) {
				Main.getComps().remove(c);
			}
			
			if(Keys.s) {
				if(System.currentTimeMillis() - lastptime > 5000) {
					export();
					lastptime = System.currentTimeMillis();
				}
			}
		}
	}
	
	public static ArrayList<Component> getComps() {
		return l.getComps();
	}
	
	public static void export() {
		int num = 0;
		while(new File("level" + num + ".golflevel").exists()) {
			num++;
		}
		PrintStream console = System.out;
		try {
			PrintStream toFile = new PrintStream(new File("level" + num + ".golflevel"));
			System.setOut(toFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for(Component c : Main.getComps()) {
				System.out.println(c.toString());
		}
	}
	
	public static void adjustLine(Line l, Line l2) {
		if(Math.sqrt(Math.pow(l2.x1 - l.x1, 2) + Math.pow(l2.y1 - l.y1, 2)) < 10) {
			l.setTo(new Point(l2.x1, l2.y1), Line.LEFT);
		}
		
		if(Math.sqrt(Math.pow(l2.x2 - l.x1, 2) + Math.pow(l2.y2 - l.y1, 2)) < 10) {
			l.setTo(new Point(l2.x2, l2.y2), Line.LEFT);
		}
		
		if(Math.sqrt(Math.pow(l2.x1 - l.x2, 2) + Math.pow(l2.y1 - l.y2, 2)) < 10) {
			l.setTo(new Point(l2.x1, l2.y1), Line.RIGHT);
		}
		
		if(Math.sqrt(Math.pow(l2.x2 - l.x2, 2) + Math.pow(l2.y2 - l.y2, 2)) < 10) {
			l.setTo(new Point(l2.x2, l2.y2), Line.RIGHT);
		}
	}
}
