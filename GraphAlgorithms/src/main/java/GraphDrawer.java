import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class GraphDrawer extends Canvas
{
	public static void main(String[] args)
	{
		int theHeight = 4;
		int theWidth = 4;
		JFrame jf = new JFrame("Demo");
		Container cp = jf.getContentPane();
		cp.setBackground(new Color(150, 151, 153));
		cp.add(new JComponent()
		{
			public void paintComponent(Graphics g)
			{
				Graphics2D g2 = (Graphics2D) g;
				// draw grid
				DrawGrid(g2, theHeight, theWidth);

				//g2.setStroke(new BasicStroke(5));
//				g2.draw(new Line2D.Float(75, 75, 175, 75));
			}
		});
		jf.setSize(1280, 720);
		jf.setVisible(true);
	}

	private static void DrawGrid(Graphics2D g2, int theHeight, int theWidth)
	{
		int xPos = 50;
		int yPos = 50;
		int width = 50;
		int height = 50;
		for (int i = 0; i < theHeight; i++)
		{
			for (int j = 0; j < theWidth; j++)
			{
				g2.fillOval(xPos,yPos,width,height);
				xPos+=100;
			}
			yPos+=100;
			xPos=50;
		}
	}
}
