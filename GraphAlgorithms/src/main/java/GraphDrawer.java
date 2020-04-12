import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class GraphDrawer extends Canvas
{
	Graph graph;

	GraphDrawer(Graph graph)
	{
		this.graph = graph;
	}
//	public static void main(String[] args)
//	{
//		int theHeight = 4;
//		int theWidth = 4;
//		JFrame jf = new JFrame("Demo");
//		Container cp = jf.getContentPane();
//		cp.setBackground(new Color(150, 151, 153));
//		cp.add(new JComponent()
//		{
//			public void paintComponent(Graphics g)
//			{
//				Graphics2D g2 = (Graphics2D) g;
//				// draw grid
//				DrawGrid(g2, theHeight, theWidth);
//
//				//g2.setStroke(new BasicStroke(5));
////				g2.draw(new Line2D.Float(75, 75, 175, 75));
//			}
//		});
//		jf.setSize(1280, 720);
//		jf.setVisible(true);
//	}
}
