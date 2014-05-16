package affichage;

import java.awt.image.BufferedImage;
import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;



import java.io.*;


public class TourneDePage implements GLEventListener {
 
	
	private float rotateT = 0.0f;
	private Texture currentTexture;
	private Texture previousTexture;
	private int iteration = 0;
	private String direction = "";
	private boolean isTurned = true;
	private boolean isLoaded = true;
	private Object lock = new Object();
	private File f; 
   

	@Override
	public void display(GLAutoDrawable gLDrawable) {
		
		synchronized(lock){
		
		GL2 gl = gLDrawable.getGL().getGL2();
		
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
		
		
		System.out.println("TourneDePage : display : ");
		// System.out.println(in);
		
		
		if (isTurned ==true){	
			read(gl);	
		}
		
		else{
			
			if(!isLoaded){
			Texture newTexture = null;
			
			try{
				long time1 = System.currentTimeMillis();
				
				newTexture = TextureIO.newTexture(f, false);
				newTexture.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
				newTexture.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
				long time2 = System.currentTimeMillis();
				
				System.out.println("TourneDePage : execution conversion du fichier en nouvelle texture : "+ (time2-time1) );
				}
				catch (Exception e){
					System.out.println("erreur : " + e.getMessage());
				}
				finally{
				}
			
			   currentTexture = newTexture;
			   isLoaded = true;
			   }
		
			if (direction.equals("right")){
			turnRight(gl);
			}
			
			
			if(direction.equals("left")){
				turnLeft(gl);
			}
			
		}
		
		}
		
		
	}
	
	private void read(GL2 gl){
		currentTexture.enable(gl);
		currentTexture.bind(gl);
  
    
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -1.5f);

		
		// Draw A Quad
		gl.glBegin(GL2.GL_QUADS);       
			//gl.glColor3f(0.0f, 1.0f, 1.0f);   // set the color of the quad
			gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f, 1.0f, 0.0f);   // Top Left
			gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f, 1.0f, 0.0f);   // Top Right
			gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,-1.0f, 0.0f);   // Bottom Right
			gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,-1.0f, 0.0f);   // Bottom Left
		// Done Drawing The Quad
		gl.glEnd();  
		
	}
 
	private void turnRight(GL2 gl){
		
		previousTexture.enable(gl);
		previousTexture.bind(gl);
  
    
		gl.glLoadIdentity();
		gl.glTranslatef((float) (Math.cos(Math.PI*((double)rotateT)/180)) - 1.0f, 0.0f, -1.5f + (float) (Math.sin(Math.PI*((double)rotateT)/180)));

		
		gl.glRotatef(rotateT, 0.0f, -1.0f, 0.0f);
		
		// Draw A Quad
		gl.glBegin(GL2.GL_QUADS);       
			//gl.glColor3f(0.0f, 1.0f, 1.0f);   // set the color of the quad
			gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f, 1.0f, 0.0f);   // Top Left
			gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f, 1.0f, 0.0f);   // Top Right
			gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,-1.0f, 0.0f);   // Bottom Right
			gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,-1.0f, 0.0f);   // Bottom Left
		// Done Drawing The Quad
		gl.glEnd();  



		previousTexture.disable(gl);	
 
		currentTexture.enable(gl);
		currentTexture.bind(gl);

		gl.glLoadIdentity();
 
		gl.glTranslatef(0.0f, 0.0f, -3.5f + (float) (iteration*(2.0/901)) );
 
		gl.glBegin(GL2.GL_QUADS);    
			//gl.glColor3f(0.0f, 1.0f, 1.0f);   // set the color of the quad
			gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f, 1.0f, 0.0f);   // Top Left
			gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f, 1.0f, 0.0f);   // Top Right
			gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,-1.0f, 0.0f);   // Bottom Right
			gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,-1.0f, 0.0f);   // Bottom Left
			// Done Drawing The Quad
		gl.glEnd();  
 
		gl.glFlush();

		// increasing rotation for the next iteration       
		if (rotateT<180f){
			rotateT += 0.2f;
		    iteration = iteration+1;
		}
		
		else{
			iteration = 0;
			rotateT = 0.0f;
			previousTexture = currentTexture;
		    direction = "";
			isTurned = true;
		}

		
	}
	
	private void turnLeft(GL2 gl){
		
		currentTexture.enable(gl);
		currentTexture.bind(gl);
  
    
		gl.glLoadIdentity();
		gl.glTranslatef((float) (Math.cos(Math.PI*((double)(180.0f - rotateT))/180)) - 1.0f, 0.0f, -1.5f + (float) (Math.sin(Math.PI*((double)(180.0f - rotateT))/180)));

		gl.glRotatef(180.0f - rotateT, 0.0f, -1.0f, 0.0f);
		
		
		// Draw A Quad
		gl.glBegin(GL2.GL_QUADS);       
			//gl.glColor3f(0.0f, 1.0f, 1.0f);   // set the color of the quad
			gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f, 1.0f, 0.0f);   // Top Left
			gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f, 1.0f, 0.0f);   // Top Right
			gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,-1.0f, 0.0f);   // Bottom Right
			gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,-1.0f, 0.0f);   // Bottom Left
		// Done Drawing The Quad
		gl.glEnd();  
		

		currentTexture.disable(gl);	
 
		previousTexture.enable(gl);
		previousTexture.bind(gl);

		gl.glLoadIdentity();
 
		gl.glTranslatef(0.0f, 0.0f, -1.5f - (float) (iteration*(2.0/901)) );
 
		gl.glBegin(GL2.GL_QUADS);    
			//gl.glColor3f(0.0f, 1.0f, 1.0f);   // set the color of the quad
			gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f, 1.0f, 0.0f);   // Top Left
			gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f, 1.0f, 0.0f);   // Top Right
			gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,-1.0f, 0.0f);   // Bottom Right
			gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,-1.0f, 0.0f);   // Bottom Left
			
			// Done Drawing The Quad
		gl.glEnd();  
 
		gl.glFlush();

		// increasing rotation for the next iteration       
		if (rotateT<180f){
			rotateT += 0.2f;
			iteration = iteration+1;
		}
		
		else{
			iteration = 0;
			rotateT = 0.0f;
			previousTexture = currentTexture;
			direction = "";
			isTurned = true;
		}
		
		
	}
	
	@Override
	public void init(GLAutoDrawable glDrawable) {
		long time1 = System.currentTimeMillis();
		GL2 gl = glDrawable.getGL().getGL2();
		gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL.GL_DEPTH_TEST);
		gl.glEnable(GL.GL_TEXTURE_2D); 
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
		gl.glEnable(GL.GL_BLEND); 
		gl.glDepthFunc(GL.GL_LEQUAL);
		gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		long time2 =  System.currentTimeMillis();
		
		 System.out.println("TournedePage : init : intialisation des paramètres : " + (time2-time1));
		
		try{
			long time3 =  System.currentTimeMillis();
			f = new File(System.getProperty("user.dir") + "\\Images\\img.png");
			long time4 =  System.currentTimeMillis();
			System.out.println("TournedePage : init : ouverture du fichier : " + (time4-time3));
			currentTexture = TextureIO.newTexture(f, false);
			currentTexture.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
			currentTexture.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		   
			}
			catch (Exception e){
				System.out.println("erreur init  : " + e.getMessage());
			}
			finally{
				
			} 
		
		/*
		try{
			currentTexture = TextureIO.newTexture(in, false, "png");
			currentTexture.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
			currentTexture.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		}
		catch(Exception e){
			
		}
		finally{
			
		}
		
		*/
		
		previousTexture = currentTexture;
	}
 
	@Override
	public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) {
		System.out.println(Thread.currentThread());
		long time1 = System.currentTimeMillis();
		GL2 gl = gLDrawable.getGL().getGL2();
		final float aspect = (float) width / (float) height;
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();
		final float fh = 0.5f;
		final float fw = fh * aspect;
		gl.glFrustumf(-fw, fw, -fh, fh, 1.0f, 1000.0f);
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glLoadIdentity();
		long time2 = System.currentTimeMillis();
		System.out.println("TourneDePage : reshape : " + (time2-time1));
		
		
	}
 
	
	@Override
	public void dispose(GLAutoDrawable gLDrawable) {
		GL2 gl = gLDrawable.getGL().getGL2();
		gl.glDisable(GL.GL_DEPTH_TEST);
		gl.glDisable(GL.GL_TEXTURE_2D); 
		gl.glDisable(GL.GL_BLEND); 
	}
 
	
	public void turnPage(String direction){
		
		synchronized(lock){
		this.direction = direction;
		isLoaded = false;
		isTurned = false;
		}
		
	}
	
	
	
	}