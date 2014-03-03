import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.swing.JFrame;

import org.apache.batik.swing.JSVGCanvas;


public class TestSVGGeneration {

	public static void main(String[] args) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("testSVG.svg", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		writer.println("<?xml version=\"1.0\" standalone=\"no\"?>");
		writer.println("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\"");
		writer.println("\"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">");
		writer.println("<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"800\" height=\"800\" >");
		writer.println("	<rect x=\"50\" y=\"200\" width=\"250\" height=\"40\" />");		
		writer.println("</svg>");
		writer.close();
		
		File file = new File("testSVG.svg");
		
		final JSVGCanvas c = new JSVGCanvas(null,true,false);
		
		final JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String uri = file.toURI().toString();
        c.setURI(uri);
        f.getContentPane().add(c);
        f.setResizable(false);
        f.setUndecorated(true);
        f.pack();
        
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] list = env.getScreenDevices();
        final GraphicsDevice dev = list[0];
   
        dev.setFullScreenWindow(f);
        
        c.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
                f.dispose();
                System.exit(0);
            }
        });
        
        f.setVisible(true);
	}

}
