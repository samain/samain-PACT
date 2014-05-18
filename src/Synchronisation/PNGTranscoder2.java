package Synchronisation;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.*; 
import org.w3c.dom.Document;

public class PNGTranscoder2 extends PNGTranscoder {

	public PNGTranscoder2(){
		super();
	}
	
	//méthode pour enregistrer directement le Document sous forme de fichier PNG
	public void transcode2 (Document doc, String uri, TranscoderOutput out){
		
		try{
			transcode(doc, uri, out);
		}
		catch(Exception e){
			System.out.println("PNGTranscoder2 : transcoder2 : " + e.getMessage());
			
		}
	}
	
}
