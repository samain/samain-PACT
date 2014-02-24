import java.io.*;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.apache.batik.swing.JSVGCanvas;
import org.apache.batik.dom.*;
import org.apache.batik.dom.svg.*;
import org.w3c.dom.*;



public class TestAnim {
	
	    public static int clickNumber ;
	
        public static void main(String[] args) {
                
              
        	    String svgNS = SVGDOMImplementation.SVG_NAMESPACE_URI;
        	    String xlinkNS = "http://www.w3.org/1999/xlink";
        	    
        	    DOMImplementation impl = SVGDOMImplementation.getDOMImplementation();
        	    final Document document2 = impl.createDocument(svgNS, "svg", null);
        	    
        	    Document doc = impl.createDocument(svgNS, "svg", null);
        	    final Document document3 = impl.createDocument(svgNS, "svg", null);
        	    
        	    
        	    Element svgRoot = doc.getDocumentElement();
        	    svgRoot.setAttributeNS(null, "width", "1200");
        	    svgRoot.setAttributeNS(null, "height", "700");

        	    
        	    Element svgRoot2 = document2.getDocumentElement();
        	    svgRoot2.setAttributeNS(null, "width", "1200");
        	    svgRoot2.setAttributeNS(null, "height", "700");
        	    
        	    Element svgRoot3 = document3.getDocumentElement();
        	    svgRoot3.setAttributeNS(null, "width", "1200");
        	    svgRoot3.setAttributeNS(null, "height", "700");
        	    
        	   //contour extérieur de l'horloge
        	    Element path0 = doc.createElementNS(svgNS, "path");
        	    path0.setAttributeNS(null, "id", "path0");
        	    path0.setAttributeNS(null, "d", "M50,250 C 50,0 450,0 450,250");
        	    path0.setAttributeNS(null, "fill" ,"none");
        	    path0.setAttributeNS(null, "stroke", "red"); 
        	    path0.setAttributeNS(null, "stroke-width", "7.06");
        	    svgRoot.appendChild(path0);
        	    
        	    //contour intérieur de l'horloge
        	    Element path = doc.createElementNS(svgNS, "path");
        	    path.setAttributeNS(null, "id", "path1");
        	    path.setAttributeNS(null, "d", "M100,250 C 100,50 400,50 400,250");
        	    path.setAttributeNS(null, "fill" ,"none");
        	    path.setAttributeNS(null, "stroke", "blue"); 
        	    path.setAttributeNS(null, "stroke-width", "7.06");
        	    svgRoot.appendChild(path);
        	   
        	    //midi
        	    Element text = doc.createElementNS(svgNS, "text");
        	    text.setAttributeNS(null, "x", "225");
        	    text.setAttributeNS(null, "y", "50");
        	    text.setAttributeNS(null, "font-size", "30");
        	    text.appendChild(doc.createTextNode("12"));
        	    svgRoot.appendChild(text);
        	    
        	    //neuf heure
        	    Element text2 = doc.createElementNS(svgNS, "text");
        	    text2.setAttributeNS(null, "x", "80");
        	    text2.setAttributeNS(null, "y", "250");
        	    text2.setAttributeNS(null, "font-size", "30");
        	    text2.appendChild(doc.createTextNode("9"));
        	    svgRoot.appendChild(text2);
        	    
        	    //trois heure
        	    Element text3 = doc.createElementNS(svgNS, "text");
        	    text3.setAttributeNS(null, "x", "420");
        	    text3.setAttributeNS(null, "y", "250");
        	    text3.setAttributeNS(null, "font-size", "30");
        	    text3.appendChild(doc.createTextNode("3"));
        	    svgRoot.appendChild(text3);
        	    
        	    //l'aiguille
        	    Element path2 = doc.createElementNS(svgNS, "path");
        	    path2.setAttributeNS(null, "id", "path2");
        	    path2.setAttributeNS(null, "d", "M-25,-12.5 L25,-12.5 L 0,-87.5 z");
        	    path2.setAttributeNS(null, "fill" ,"yellow");
        	    path2.setAttributeNS(null, "stroke", "red"); 
        	    path2.setAttributeNS(null, "stroke-width", "7.06");
        	    
        	    
        	    // animation la faisant tourner selon le contour intérieur        	    
        	    Element animation = doc.createElementNS(svgNS, "animateMotion");
        	    animation.setAttributeNS(null, "dur", "6s");
        	    animation.setAttributeNS(null, "repeatCount", "indefinite");
        	    animation.setAttributeNS(null, "rotate", "auto");
        	    
        	    Element mpath = doc.createElementNS(svgNS, "mpath");
        	    mpath.setAttributeNS(xlinkNS, "href", "#path1");
        	    
        	    animation.appendChild(mpath);
        	    
        	    path2.appendChild(animation);
        	    
        	    svgRoot.appendChild(path2); 
        	    
        	    
        	    //montagnes russes
        	    Element path3 = doc.createElementNS(svgNS, "path");
        	    path3.setAttributeNS(null, "id", "path3");
        	    path3.setAttributeNS(null, "d", "M500,200 C 500,100 650,100 650,200 S 800,300 800,200");
        	    path3.setAttributeNS(null, "fill" ,"none");
        	    path3.setAttributeNS(null, "stroke", "purple"); 
        	    path3.setAttributeNS(null, "stroke-width", "5.02");
        	    svgRoot.appendChild(path3);
        	    
        	    //triangle aimant les sensations fortes
        	    Element path4 = doc.createElementNS(svgNS, "path");
        	    path4.setAttributeNS(null, "id", "path2");
        	    path4.setAttributeNS(null, "d", "M-25,-12.5 L25,-12.5 L 0,-87.5 z");
        	    path4.setAttributeNS(null, "fill" ,"black");
        	    path4.setAttributeNS(null, "stroke", "green"); 
        	    path4.setAttributeNS(null, "stroke-width", "7.06");
        	    
        	    Element animation2 = doc.createElementNS(svgNS, "animateMotion");
        	    animation2.setAttributeNS(null, "dur", "8s");
        	    animation2.setAttributeNS(null, "repeatCount", "indefinite");
        	    animation2.setAttributeNS(null, "rotate", "auto");
        	    
        	    Element mpath2 = doc.createElementNS(svgNS, "mpath");
        	    mpath2.setAttributeNS(xlinkNS, "href", "#path3");
        	    
        	    animation2.appendChild(mpath2);

        	    path4.appendChild(animation2);
        	    
        	    svgRoot.appendChild(path4);

        	    // rectangle jaune
        	    Element rectangle2 = doc.createElementNS(svgNS, "rect");
        	    //rectangle2.setAttributeNS(null, "id", "rect1");
        	    rectangle2.setAttributeNS(null, "x", "100");
        	    rectangle2.setAttributeNS(null, "y", "200");
        	    rectangle2.setAttributeNS(null, "width", "100");
        	    rectangle2.setAttributeNS(null, "height", "50");
        	    rectangle2.setAttributeNS(null, "fill", "yellow");
        	    rectangle2.setAttributeNS(null, "opacity", "1"); 
        	  
        	    //rectangle rouge
        	    Element rectangle3  = doc.createElementNS(svgNS, "rect");
        	    // rectangle2.setAttributeNS(null, "id", "rect2");
        	    rectangle3.setAttributeNS(null, "x", "100");
        	    rectangle3.setAttributeNS(null, "y", "200");
        	    rectangle3.setAttributeNS(null, "width", "100");
        	    rectangle3.setAttributeNS(null, "height", "50");
        	    rectangle3.setAttributeNS(null, "fill", "red");
        	    rectangle3.setAttributeNS(null, "opacity", "0"); 
        	    
        	    // rectangle bleu
        	    Element rectangle4 = doc.createElementNS(svgNS, "rect");
        	    // rectangle4.setAttributeNS(null, "id", "rect3");
        	    rectangle4.setAttributeNS(null, "x", "200");
        	    rectangle4.setAttributeNS(null, "y", "500");
        	    rectangle4.setAttributeNS(null, "width", "100");
        	    rectangle4.setAttributeNS(null, "height", "50");
        	    rectangle4.setAttributeNS(null, "fill", "blue");        	    
        	    rectangle4.setAttributeNS(null, "opacity", "1"); 
        	   // rectangle4.setAttributeNS(null, "transform", "scale(1)");
        	    
        	    //animation rectangle jaune (disparition)
        	    Element animation1 = doc.createElementNS(svgNS, "animate");
        	    animation1.setAttributeNS(null, "attributeType", "CSS");
        	    animation1.setAttributeNS(null, "attributeName", "opacity");
        	    animation1.setAttributeNS(null, "from", "1");
        	    animation1.setAttributeNS(null, "to", "0");
        	    animation1.setAttributeNS(null, "dur", "5s");
        	    animation1.setAttributeNS(null, "repeatCount", "indefinite");
        	   
        	    
        	    //animation rectangle rouge (apparition)
        	    Element animation3 = doc.createElementNS(svgNS, "animate");
        	    animation3.setAttributeNS(null, "attributeType", "CSS");
        	    animation3.setAttributeNS(null, "attributeName", "opacity");
        	    animation3.setAttributeNS(null, "from", "0");
        	    animation3.setAttributeNS(null, "to", "1");
        	    animation3.setAttributeNS(null, "dur", "5s");
        	    animation3.setAttributeNS(null, "repeatCount", "indefinite");
        	    
        	    Element animation4 = doc.createElementNS(svgNS, "animate");
        	    animation4.setAttributeNS(null, "attributeName", "x");
        	    animation4.setAttributeNS(null, "attributeType", "XML");
        	    // animation4.setAttributeNS(null, "type", "scale");
        	    animation4.setAttributeNS(null, "from", "200");
        	    animation4.setAttributeNS(null, "to", "150");
        	   // animation4.setAttributeNS(null, "by", "1");
        	    animation4.setAttributeNS(null, "dur", "5s");
        	   // animation4.setAttributeNS(null, "additive","replace");
        	    animation4.setAttributeNS(null, "fill", "freeze"); 
        	    animation4.setAttributeNS(null, "repeatCount", "indefinite"); 
        	    
        	    Element animation5 = doc.createElementNS(svgNS, "animate");
        	    animation5.setAttributeNS(null, "attributeName", "y");
        	    animation5.setAttributeNS(null, "attributeType", "XML");
        	    // animation5.setAttributeNS(null, "type", "scale");
        	    animation5.setAttributeNS(null, "from", "500");
        	    animation5.setAttributeNS(null, "to", "475");
        	   // animation4.setAttributeNS(null, "by", "1");
        	    animation5.setAttributeNS(null, "dur", "5s");
        	   // animation4.setAttributeNS(null, "additive","replace");
        	    animation5.setAttributeNS(null, "fill", "freeze"); 
        	    animation5.setAttributeNS(null, "repeatCount", "indefinite"); 
        	    
        	    Element animation6 = doc.createElementNS(svgNS, "animate");
        	    animation6.setAttributeNS(null, "attributeName", "width");
        	    animation6.setAttributeNS(null, "attributeType", "XML");
        	   // animation6.setAttributeNS(null, "type", "scale");
        	    animation6.setAttributeNS(null, "from", "100");
        	    animation6.setAttributeNS(null, "to", "200");
        	   // animation4.setAttributeNS(null, "by", "1");
        	    animation6.setAttributeNS(null, "dur", "5s");
        	   // animation4.setAttributeNS(null, "additive","replace");
        	    animation6.setAttributeNS(null, "fill", "freeze"); 
        	    animation6.setAttributeNS(null, "repeatCount", "indefinite"); 
        	    
        	    Element animation7 = doc.createElementNS(svgNS, "animate");
        	    animation7.setAttributeNS(null, "attributeName", "height");
        	    animation7.setAttributeNS(null, "attributeType", "XML");
        	    // animation7.setAttributeNS(null, "type", "scale");
        	    animation7.setAttributeNS(null, "from", "25");
        	    animation7.setAttributeNS(null, "to", "50");
        	   // animation4.setAttributeNS(null, "by", "1");
        	    animation7.setAttributeNS(null, "dur", "5s");
        	   // animation4.setAttributeNS(null, "additive","replace");
        	    animation7.setAttributeNS(null, "fill", "freeze"); 
        	    animation7.setAttributeNS(null, "repeatCount", "indefinite"); 
        	    
        	    Element animation41 = doc.createElementNS(svgNS, "animateTransform");
        	    animation41.setAttributeNS(null, "attributeName", "transform");
        	    animation41.setAttributeNS(null, "attributeType", "XML");
        	    animation41.setAttributeNS(null, "type", "rotate");
        	    animation41.setAttributeNS(null, "from", "0 250 525");
        	    animation41.setAttributeNS(null, "to", "30 250 525");
        	    animation41.setAttributeNS(null, "dur", "5s");
        	    animation41.setAttributeNS(null, "additive","replace");
        	    animation41.setAttributeNS(null, "fill", "freeze");   
        	    animation41.setAttributeNS(null, "repeatCount", "indefinite");  
        	    
        	   /* Element animation7 = doc.createElementNS(svgNS, "animateTransform");
        	    animation7.setAttributeNS(null, "attributeName", "transform");
        	    animation7.setAttributeNS(null, "attributeType", "XML");
        	    animation7.setAttributeNS(null, "type", "scale");
        	    animation7.setAttributeNS(null, "from", "1");
        	    animation7.setAttributeNS(null, "to", "2");
        	    animation7.setAttributeNS(null, "dur", "5s");
        	    animation7.setAttributeNS(null, "additive","replace");
        	    animation7.setAttributeNS(null, "fill", "freeze"); */
        	    
        	    rectangle2.appendChild(animation1);
        	  
        	    rectangle3.appendChild(animation3);
        	    
        	    rectangle4.appendChild(animation4);
        	    rectangle4.appendChild(animation5);       
        	    rectangle4.appendChild(animation6);
        	    rectangle4.appendChild(animation7);
        	    rectangle4.appendChild(animation41);
        	    
        	    svgRoot.appendChild(rectangle2);
        	    
        	    svgRoot.appendChild(rectangle3); 
        	    
        	    svgRoot.appendChild(rectangle4); 
        	    
        	    // svgRoot.appendChild(svg);
        	    
        	    // rectangle jaune
        	    Element rectangle5 = document2.createElementNS(svgNS, "rect");
        	    //rectangle2.setAttributeNS(null, "id", "rect1");
        	    rectangle5.setAttributeNS(null, "x", "100");
        	    rectangle5.setAttributeNS(null, "y", "200");
        	    rectangle5.setAttributeNS(null, "width", "100");
        	    rectangle5.setAttributeNS(null, "height", "50");
        	    rectangle5.setAttributeNS(null, "fill", "yellow");
        	    rectangle5.setAttributeNS(null, "opacity", "1"); 
        	  
        	    //rectangle rouge
        	    Element rectangle6  = document2.createElementNS(svgNS, "rect");
        	    // rectangle2.setAttributeNS(null, "id", "rect2");
        	    rectangle6.setAttributeNS(null, "x", "100");
        	    rectangle6.setAttributeNS(null, "y", "200");
        	    rectangle6.setAttributeNS(null, "width", "100");
        	    rectangle6.setAttributeNS(null, "height", "50");
        	    rectangle6.setAttributeNS(null, "fill", "red");
        	    rectangle6.setAttributeNS(null, "opacity", "0"); 
        	    
        	    //animation rectangle jaune (disparition)
        	    Element animation8 = document2.createElementNS(svgNS, "animate");
        	    animation8.setAttributeNS(null, "attributeType", "CSS");
        	    animation8.setAttributeNS(null, "attributeName", "opacity");
        	    animation8.setAttributeNS(null, "from", "1");
        	    animation8.setAttributeNS(null, "to", "0");
        	    
        	    animation8.setAttributeNS(null, "dur", "5s");
        	    animation8.setAttributeNS(null, "repeatCount", "indefinite");
        	   
        	    
        	    //animation rectangle rouge (apparition)
        	    Element animation9 = document2.createElementNS(svgNS, "animate");
        	    animation9.setAttributeNS(null, "attributeType", "CSS");
        	    animation9.setAttributeNS(null, "attributeName", "opacity");
        	    animation9.setAttributeNS(null, "from", "0");
        	    animation9.setAttributeNS(null, "to", "1");
        	    animation9.setAttributeNS(null, "dur", "5s");
        	    animation9.setAttributeNS(null, "repeatCount", "indefinite");
        	    
        	    rectangle5.appendChild(animation8);
        	    
        	    rectangle6.appendChild(animation9);
        	    
        	    svgRoot2.appendChild(rectangle5);
        	    
        	    svgRoot2.appendChild(rectangle6);
        	    
        	    
        	  //contour extérieur de l'horloge
        	    Element path30 = document3.createElementNS(svgNS, "path");
        	    path30.setAttributeNS(null, "id", "path0");
        	    path30.setAttributeNS(null, "d", "M50,250 C 50,0 450,0 450,250");
        	    path30.setAttributeNS(null, "fill" ,"none");
        	    path30.setAttributeNS(null, "stroke", "red"); 
        	    path30.setAttributeNS(null, "stroke-width", "7.06");
        	    svgRoot3.appendChild(path30);
        	    
        	    //contour intérieur de l'horloge
        	    Element path31 = document3.createElementNS(svgNS, "path");
        	    path31.setAttributeNS(null, "id", "path1");
        	    path31.setAttributeNS(null, "d", "M100,250 C 100,50 400,50 400,250");
        	    path31.setAttributeNS(null, "fill" ,"none");
        	    path31.setAttributeNS(null, "stroke", "blue"); 
        	    path31.setAttributeNS(null, "stroke-width", "7.06");
        	    svgRoot3.appendChild(path31);
        	   
        	    //midi
        	    Element text30 = document3.createElementNS(svgNS, "text");
        	    text30.setAttributeNS(null, "x", "225");
        	    text30.setAttributeNS(null, "y", "50");
        	    text30.setAttributeNS(null, "font-size", "30");
        	    text30.appendChild(document3.createTextNode("12"));
        	    svgRoot3.appendChild(text30);
        	    
        	    //neuf heure
        	    Element text31 = document3.createElementNS(svgNS, "text");
        	    text31.setAttributeNS(null, "x", "80");
        	    text31.setAttributeNS(null, "y", "250");
        	    text31.setAttributeNS(null, "font-size", "30");
        	    text31.appendChild(document3.createTextNode("9"));
        	    svgRoot3.appendChild(text31);
        	    
        	    //trois heure
        	    Element text32 = document3.createElementNS(svgNS, "text");
        	    text32.setAttributeNS(null, "x", "420");
        	    text32.setAttributeNS(null, "y", "250");
        	    text32.setAttributeNS(null, "font-size", "30");
        	    text32.appendChild(document3.createTextNode("3"));
        	    svgRoot3.appendChild(text32);
        	    
        	    //l'aiguille
        	    Element path32 = document3.createElementNS(svgNS, "path");
        	    path32.setAttributeNS(null, "id", "path2");
        	    path32.setAttributeNS(null, "d", "M-25,-12.5 L25,-12.5 L 0,-87.5 z");
        	    path32.setAttributeNS(null, "fill" ,"yellow");
        	    path32.setAttributeNS(null, "stroke", "red"); 
        	    path32.setAttributeNS(null, "stroke-width", "7.06");
        	    
        	    
        	    // animation la faisant tourner selon le contour intérieur        	    
        	    Element animation30 = document3.createElementNS(svgNS, "animateMotion");
        	    animation30.setAttributeNS(null, "dur", "6s");
        	    animation30.setAttributeNS(null, "repeatCount", "indefinite");
        	    animation30.setAttributeNS(null, "rotate", "auto");
        	    
        	    Element mpath30 = document3.createElementNS(svgNS, "mpath");
        	    mpath30.setAttributeNS(xlinkNS, "href", "#path1");
        	    
        	    animation30.appendChild(mpath30);
        	    
        	    path32.appendChild(animation30);
        	    
        	    svgRoot3.appendChild(path32); 
        	    
        	    // l'affichage de l'image ne marche toujours pas
        	    // URI complête : "C:/Users/Sebastien/workspace3/Test Batik 3/Resources/loup4.jpg"
        	    
        	   /* Element image = doc.createElementNS(null, "image");
        	    image.setAttributeNS(null, "x", "100");
        	    image.setAttributeNS(null, "y", "100");
        	    image.setAttributeNS(null, "width", "300px");
        	    image.setAttributeNS(null, "heigth", "200px");
        	    image.setAttributeNS(xlinkNS, "xlink:href", "C:/Users/Sebastien/workspace3/Test Batik 3/Resources/loup4.jpg");
        	    image.setAttributeNS(null, "visibility", "visible");
        	    image.setAttributeNS(null, "opacity", "1");
        	    
        	    svgRoot.appendChild(image); */
        	    
        	    
                final JSVGCanvas c = new JSVGCanvas(null,true,false);
               // c.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
                c.setDocument(doc);
                
                final JSVGCanvas c2 = new JSVGCanvas(null,true,false);
                // c2.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
                c2.setDocument(document2);
                
                final JSVGCanvas c3 = new JSVGCanvas(null,true,false);
                // c3.setDocumentState(JSVGCanvas.ALWAYS_DYNAMIC);
                c3.setDocument(document3);
                
                final JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


               
                f.getContentPane().add(c);
           
                f.setResizable(false);
                f.setUndecorated(true);
                f.pack();
                
                f.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
                
                
                GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
                GraphicsDevice[] list = env.getScreenDevices();
                final GraphicsDevice dev = list[0];
           
                dev.setFullScreenWindow(f);
            
                f.setVisible(true);
                
           
                
                c.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                     f.invalidate();
           		     f.getContentPane().remove(c); 
           	         f.getContentPane().add(c2);
           	         f.validate();
           	         f.repaint();                  	           	 
            }
              });
                
                
                c2.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                    	  f.invalidate();
  		                  f.getContentPane().remove(c2); 
	                      f.getContentPane().add(c3);
	                      f.validate();
	                      f.repaint();
            }
              });
                
                
                c3.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                    	          f.dispose();
                                  System.exit(0);
            }
              });
                
        }

}   