package affichage;

import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*;

import com.jogamp.opengl.util.texture.Texture;
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
		
		
		
		if (isTurned ==true){	
			read(gl);	
		}
		
		else{
			
			if(!isLoaded){
			Texture newTexture = null;
			
			try{
				newTexture = TextureIO.newTexture(f, false);
				newTexture.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
				newTexture.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
				
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
		gl.glTranslatef(0.0f, 0.0f, -2.0f);

		
		// Draw A Quad
		gl.glBegin(GL2.GL_QUADS);       
			gl.glColor3f(0.0f, 1.0f, 1.0f);   // set the color of the quad
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
		gl.glTranslatef((float) (Math.cos(Math.PI*((double)rotateT)/180)) - 1.0f, 0.0f, -2.0f + (float) (Math.sin(Math.PI*((double)rotateT)/180)));

		
		gl.glRotatef(rotateT, 0.0f, -1.0f, 0.0f);
		
		// Draw A Quad
		gl.glBegin(GL2.GL_QUADS);       
			gl.glColor3f(0.0f, 1.0f, 1.0f);   // set the color of the quad
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
 
		gl.glTranslatef(0.0f, 0.0f, -4.0f + (float) (iteration*(2.0/91)) );
 
		gl.glBegin(GL2.GL_QUADS);    
			gl.glColor3f(0.0f, 1.0f, 1.0f);   // set the color of the quad
			gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f, 1.0f, 0.0f);   // Top Left
			gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f, 1.0f, 0.0f);   // Top Right
			gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,-1.0f, 0.0f);   // Bottom Right
			gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,-1.0f, 0.0f);   // Bottom Left
			// Done Drawing The Quad
		gl.glEnd();  
 
		gl.glFlush();

		// increasing rotation for the next iteration       
		if (rotateT<180f){
			rotateT += 2.0f;
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
		gl.glTranslatef((float) (Math.cos(Math.PI*((double)(180.0f - rotateT))/180)) - 1.0f, 0.0f, -2.0f + (float) (Math.sin(Math.PI*((double)(180.0f - rotateT))/180)));

		gl.glRotatef(180.0f - rotateT, 0.0f, -1.0f, 0.0f);
		
		
		// Draw A Quad
		gl.glBegin(GL2.GL_QUADS);       
			gl.glColor3f(0.0f, 1.0f, 1.0f);   // set the color of the quad
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
 
		gl.glTranslatef(0.0f, 0.0f, -3.0f - (float) (iteration*(2.0/91)) );
 
		gl.glBegin(GL2.GL_QUADS);    
			gl.glColor3f(0.0f, 1.0f, 1.0f);   // set the color of the quad
			gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f, 1.0f, 0.0f);   // Top Left
			gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f, 1.0f, 0.0f);   // Top Right
			gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,-1.0f, 0.0f);   // Bottom Right
			gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,-1.0f, 0.0f);   // Bottom Left
			
			// Done Drawing The Quad
		gl.glEnd();  
 
		gl.glFlush();

		// increasing rotation for the next iteration       
		if (rotateT<180f){
			rotateT += 2.0f;
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
		
		try{
			f = new File(System.getProperty("user.dir") + "\\Images\\img.png");
			currentTexture = TextureIO.newTexture(f, false);
			currentTexture.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
			currentTexture.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		   
			}
			catch (Exception e){
				System.out.println("erreur reshape  : " + e.getMessage());
			}
			finally{
				
			}
		
		
		previousTexture = currentTexture;
	}
 
	@Override
	public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) {
		GL2 gl = gLDrawable.getGL().getGL2();
		final float aspect = (float) width / (float) height;
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();
		final float fh = 0.5f;
		final float fw = fh * aspect;
		gl.glFrustumf(-fw, fw, -fh, fh, 1.0f, 1000.0f);
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		
		
		
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