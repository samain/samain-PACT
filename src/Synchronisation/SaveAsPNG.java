package Synchronisation;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.*; 
import org.w3c.dom.Document;

public class SaveAsPNG implements DocumentToFileInterface {
	
	private PNGTranscoder png;
	
//--------------------------------------------
	//constructeur de la classe SaveAsPNG
	public SaveAsPNG(){
		
		png = new PNGTranscoder();
		
	}
//--------------------------------------------------------------------------------------
	//méthode chargée de sauvegarder un document sous forme d'image PNG
    public void save(Document doc, String svgURI) {
    	
    	
    	
        try{
        	 TranscoderInput input = new TranscoderInput(doc);
        	 OutputStream ostream = new FileOutputStream(svgURI);
        	 TranscoderOutput output = new TranscoderOutput(ostream);
        	
        	 //sauvegarde du document sous forme de fichier PNG
        	 png.transcode(input, output);
        	 
        	 ostream.flush();
        	 ostream.close();
        	}
        catch (Exception e){
        	System.out.println("erreur SaveAsPNG : " + e.getMessage());
        }
          
    }
}