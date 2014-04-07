package Synchronisation;

import java.io.*;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.*; 
import org.w3c.dom.Document;

public class SaveAsPNG {
	
	private PNGTranscoder png;
	
	public SaveAsPNG(){
		
		png = new PNGTranscoder();
		
	}

    public void save(Document doc, String svgURI) {

       
        
    	TranscoderInput input = new TranscoderInput(doc);

      
        try{
        	OutputStream ostream = new FileOutputStream(svgURI);
        	TranscoderOutput output = new TranscoderOutput(ostream);

        	png.transcode(input, output);

        
        	ostream.flush();
        	ostream.close();
        }
        catch (Exception e){
        	System.out.println("erreur SaveAsPNG : " + e.getMessage());
        }
        finally{
        	
        }
     
    }
}