package Synchronisation;

import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.*; 
import org.w3c.dom.Document;

public class SaveAsPNG {
	
	private PNGTranscoder2 png;
	
	public SaveAsPNG(){
		
		png = new PNGTranscoder2();
		
	}

    public void save(Document doc, String svgURI) {

       
        long time1 = System.currentTimeMillis();
    	
    	TranscoderInput input = new TranscoderInput(doc);

    	
    	long time7 = System.currentTimeMillis();
    	InputStream in = input.getInputStream();
    	
    	/* BufferedImage buf = null;
    	
       	try{
    	buf = ImageIO.read(in);
    	}
    	catch(Exception e){
    	System.out.println("SaveAsPNG : ImageIO : erreur :");	
    	}
    	finally{
    		
    	}
    	long time8 = System.currentTimeMillis();
    	
    	System.out.println("SaveAsPNG : save : création BufferedImage : " + (time8-time7));
    	*/
    	
    	
        try{
        	 OutputStream ostream = new FileOutputStream(svgURI);
        	 TranscoderOutput output = new TranscoderOutput(ostream);

        	 long time3 = System.currentTimeMillis();
        	
        	 png.transcode2(doc, svgURI, output);
        	
        	 long time4 = System.currentTimeMillis();

        	 System.out.println("SaveAsPNG : save : transcode : " + (time4-time3));
        	
        	 long time5 = System.currentTimeMillis();
        	 
        	ostream.flush();
        	ostream.close();
        	
        	 long time6 = System.currentTimeMillis();
        	 
        	 System.out.println("SaveAsPNG : save : flush and close : " + (time6-time5));
        }
        catch (Exception e){
        	System.out.println("erreur SaveAsPNG : " + e.getMessage());
        }
        finally{
        	
        }
        
        
        long time2 = System.currentTimeMillis();
        
        System.out.println("SaveAsPNG : save : " + (time2-time1));
        
          
    }
}