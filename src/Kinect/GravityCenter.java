package Kinect;
import java.nio.*;

import com.googlecode.javacpp.Loader;
import com.googlecode.javacv.*;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_highgui.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.*;

import com.googlecode.javacv.OpenCVFrameGrabber;
import com.googlecode.javacv.cpp.opencv_core.CvMemStorage;
import com.googlecode.javacv.cpp.opencv_core.IplImage;

import javax.swing.JFrame;
import javax.swing.JRootPane;

import java.awt.GridLayout;

import Menu.FenetreKinect;
import Synchronisation.SynchronizerInterface;

public class GravityCenter implements Runnable {

	
	public void run(){
	      boolean record = false;
	      int codec4cc = CV_FOURCC('D','I','B',' ');
	      try{ 
	      

	        /*création de la fenêtre principale*/
	        JFrame mainframe = new JFrame();
	        mainframe.setLayout(new GridLayout(1, 1));
	        mainframe.setVisible(true);

	        /*création de la fenetre utilisée pour l'affichage de la video. L'objet CanvasFrame en JavaCV peut utiliser
	        l'accélération matérielle pour afficher les vidéos, profitons-en ! */
	        CanvasFrame depth_frame = new CanvasFrame("AVI Playback Demo");        
	        mainframe.getContentPane().add(depth_frame.getCanvas() );
	        depth_frame.setVisible(false);

	        /*création de l'objet d'acquisition de trames video à partir du fichier indiqué comme paramètre du programme*/
	        OpenKinectFrameGrabber grabber = null;
	        grabber = new OpenKinectFrameGrabber(0);
	  
	        grabber.setFormat("depth");
	        
	        grabber.start();
	        
	        IplImage depth_image = grabber.grab();
	        int width  = depth_image.width();
	        int height = depth_image.height();
	        mainframe.setSize(width, height);
	                
	        

	        /* Ligne magique de JavaCV - elle permet de faire en sorte que les trames videos non utilisées sont bien libérées de la mémoire
	        (en quelque sorte en forcant un appel au "Garbage Collector"*/
	        CvMemStorage storage = CvMemStorage.create();
	        
	        int depth_bytes_per_pixels = depth_image.widthStep() / width;
	        
	        int zone = 0 ; 
	        
	        /* Entier qui décrit la position de la main (gauche ou droite)
	         * et qui décrit si la main s'est déjà déplacée ou non.
	         */
	        
	        while (mainframe.isVisible() ) {
	        	
	        	
	           if ( (depth_image = grabber.grab()) != null) {
	        	   ByteBuffer depth_data = depth_image.getByteBuffer();
	        	   
	        	   	
	        	   	int N = 1; // nombre de pixels blancs 
	          	   	int sumI = 0; // somme des ordonnées
	          	   	int sumJ = 0; // somme des abscisses
	          	   	
	          	   	for (int i=1 ; i<height-1 ; i++) {
	        	   		for (int j=1 ; j<width-1 ; j++){
	        	   			
	        	   		// On lit les composantes de chaque pixel AINSI QUE CELLES DES PIXELS VOISINS
	        	   			int b1 = depth_data.get(depth_bytes_per_pixels*(width*i + j));
	        	   			if (b1 < 0) b1 = 255 + b1;
	        	   			
	        	   			int b2 = depth_data.get(depth_bytes_per_pixels*(width*i + j) +1);
	        	   			if (b2 < 0) b2 = 255 + b2;

	        	   			
	        	   	
	        	   		//Au delà d'une certaine profondeur (fixée à 5% à partir du détecteur), on noircit
	        	           if (b1 <(9999*255/10000) && b2<(9999*255/10000)) {
	        	        	   depth_data.put(depth_bytes_per_pixels*(width*i + j), (byte) 0);
	        	        	   depth_data.put(depth_bytes_per_pixels*(width*i + j) +1, (byte) 0);
		        	           }
	        	           
	        	           else {
	        	        	   depth_data.put(depth_bytes_per_pixels*(width*i + j), (byte) 255);
	        	        	   depth_data.put(depth_bytes_per_pixels*(width*i + j) +1, (byte) 255);
	        	        	   
	        	        	   	N++;
	          	   				sumI+=i;
	          	   				sumJ+=j;
	          	   				
	          	   			
	        	           }
	        	           
	        	          
	        	   }
	        	   }
	          	   	
	          	   	int x = sumJ/N;
	          	   	int y = sumI/N;
	          	   	
	          	  
	          	//Détection des mouvements
	          	
	          	if (x<((width/2) - 20 ) && zone !=(-2))  { //si on est à droite
	          		zone = -1 ;
	          	}

	          	if (x>((width/2) + 40 ) && zone != 2) { //si on est à gauche
	          		zone = 1 ;
	          	}
	          	
	          	if (zone == -1) { 
	          	zone = -2 ; //on change la valeur de l'entier pour que le mot ne s'affiche qu'une fois
	            FenetreKinect.synchronizer.receiveMouvement("right");
	          	}
	          	
	          	
	          	if (zone == 1) { 
		        zone = 2 ; //même remarque
		        FenetreKinect.synchronizer.receiveMouvement("left");
		        }
	          	
	        
	          	
	           }

	               /*affichage de l'image*/          
	               depth_frame.showImage(depth_image);
	               
	               

	              /*deuxiÃ¨me ligne magique JavaCV, Ã  appeler rÃ©guliÃ¨rement (aprÃ¨s chaque capture ou affichage de trame, ...)*/
	               cvClearMemStorage(storage);
	            }
	            //nettoyage des ressources        
	            grabber.stop();
	            depth_frame.dispose();
	          } catch(Exception e){
	            System.out.println(e.getMessage());
	           }       
	
	}
}

