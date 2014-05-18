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
   
//---------------------------------------------------------------------------------------
	//méthode chargée de l'affichage, appelée en continu par l'objet de type Animator lui étant affecté
	@Override
	public void display(GLAutoDrawable gLDrawable) {
		
		/*ajout d'un sémaphore pour que la méthode turnPage appelée par le Thread du Synchroniseur
		 ne modifie pas les variables sur lesquelles cette méthode fait ses tests pendannt que celle-ci
		 s'éxécute*/
		synchronized(lock){
		GL2 gl = gLDrawable.getGL().getGL2();
		gl.glClear(GL.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL.GL_DEPTH_BUFFER_BIT);
		
		//si le lecteur ne veut pas changer de page, on affiche la page courante  
		if (isTurned ==true){	
			read(gl);	
		}
		
		else{
			
			//si la texture de la page demandée n'a pas encore été chargée 
			if(!isLoaded){
			Texture newTexture = null;
			
			/*chargement de la texture de la page demandée à partir du fichier où l'on
			  a enregistré l'image de la page demandée*/
			try{
				newTexture = TextureIO.newTexture(f, false);
				newTexture.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
				newTexture.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
				}
				catch (Exception e){
					System.out.println("erreur : " + e.getMessage());
				}
			
			   //la texture courante devient la texture nouvellement chargée
			   currentTexture = newTexture;
			   //indique que la texture a été chargée
			   isLoaded = true;
			   }
		
			//si le lecteur veut avancer d'une page
			if (direction.equals("right")){
				turnRight(gl);
			}
			
			//si le lecteur veut reculer d'une page
			if(direction.equals("left")){
				turnLeft(gl);
			}
			
		}
		
		}
		
		
	}
	//------------------------------------------------------------------------------------------------
	//méthode appelée si le lecteur lit la page actuellement affichée
	private void read(GL2 gl){
		
		//charge la texture correspondant à la page lue par l'utilisateur
		currentTexture.enable(gl);
		currentTexture.bind(gl);
  
        //place la page à une distance raisonnable du lecteur
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -1.5f);

		
		// Dessine le carré représentant la page lue par le lecteur
		gl.glBegin(GL2.GL_QUADS); 
			gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f, 1.0f, 0.0f);   // Coin Haut Gauche
			gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f, 1.0f, 0.0f);   // Coin Haut Droit
			gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,-1.0f, 0.0f);   // Coin Bas Droit
			gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,-1.0f, 0.0f);   // Coin Bas Gauche
		//Fin du carré
		gl.glEnd();  
		
	}
 //----------------------------------------------------------------------------------------------------
	//méthode appelée lorsque le lecteur avance d'une page
	private void turnRight(GL2 gl){
		
		//chargement de la texture correspondant à la page lue 
		previousTexture.enable(gl);
		previousTexture.bind(gl);
  
    
		/*rotation de la page lue autour de la "reliure" du livre (bord gauche de la page
        lorsque celle-ci n'est pas tournée)*/
		gl.glLoadIdentity();
		gl.glTranslatef((float) (Math.cos(Math.PI*((double)rotateT)/180)) - 1.0f, 0.0f, -1.5f + (float) (Math.sin(Math.PI*((double)rotateT)/180)));
		gl.glRotatef(rotateT, 0.0f, -1.0f, 0.0f);
		
		// Dessine le carré représentant la page lue par le lecteur
		gl.glBegin(GL2.GL_QUADS); 
			gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f, 1.0f, 0.0f);   // Coin Haut Gauche
			gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f, 1.0f, 0.0f);   // Coin Haut Droit
			gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,-1.0f, 0.0f);   // Coin Bas Droit
			gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,-1.0f, 0.0f);   // Coin Bas Gauche
		//Fin du carré
		gl.glEnd(); 


        //chargement de la texture correspondant à la page demandée par l'utilisateur
		previousTexture.disable(gl);	
		currentTexture.enable(gl);
		currentTexture.bind(gl);

		//translation de la page demandée vers le lecteur
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -3.5f + (float) (iteration*(2.0/901)) );
 
		// Dessine le carré représentant la page demandée par l'utilisateur
		gl.glBegin(GL2.GL_QUADS); 
			gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f, 1.0f, 0.0f);   // Coin Haut Gauche
			gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f, 1.0f, 0.0f);   // Coin Haut Droit
			gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,-1.0f, 0.0f);   // Coin Bas Droit
			gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,-1.0f, 0.0f);   // Coin Bas Gauche
		//Fin du carré
		gl.glEnd();  
 
		gl.glFlush();

		/* augmentation de l'angle de rotation pour l'itération suivante 
		si la page n'est pas complètement tournée */     
		if (rotateT<180f){
			rotateT += 0.2f;
		    iteration = iteration+1;
		}
		
		//sinon fin de la tourne de page 
		else{
			iteration = 0;
			rotateT = 0.0f;
			previousTexture = currentTexture;
		    direction = "";
			isTurned = true;
		}

		
	}
//------------------------------------------------------------------------------------------------------
	//méthode appelée lorsque le lecteur souhaite reculer d'une page
	private void turnLeft(GL2 gl){
		
		//chargement de la texture de la page demandée par l'utilisateur
		currentTexture.enable(gl);
		currentTexture.bind(gl);
  
        /*rotation de la page demandée autour de la "reliure" du livre (bord gauche de la page
         lorsque celle-ci est complètement tournée)*/
		gl.glLoadIdentity();
		gl.glTranslatef((float) (Math.cos(Math.PI*((double)(180.0f - rotateT))/180)) - 1.0f, 0.0f, -1.5f + (float) (Math.sin(Math.PI*((double)(180.0f - rotateT))/180)));
		gl.glRotatef(180.0f - rotateT, 0.0f, -1.0f, 0.0f);
		
		
		// Dessine le carré représentant la page demandée par l'utilisateur
		gl.glBegin(GL2.GL_QUADS); 
			gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f, 1.0f, 0.0f);   // Coin Haut Gauche
			gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f, 1.0f, 0.0f);   // Coin Haut Droit
			gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,-1.0f, 0.0f);   // Coin Bas Droit
			gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,-1.0f, 0.0f);   // Coin Bas Gauche
		//Fin du carré
		gl.glEnd();  
		
        //la texture affichée pour le prochain carré devient celle de la page lue par le lecteur
		currentTexture.disable(gl);	
 
		previousTexture.enable(gl);
		previousTexture.bind(gl);

		//recul de la page lue par le lecteur
		gl.glLoadIdentity();
		gl.glTranslatef(0.0f, 0.0f, -1.5f - (float) (iteration*(2.0/901)) );
 
		// Dessine le carré représentant la page demandée par l'utilisateur
		gl.glBegin(GL2.GL_QUADS); 
			gl.glTexCoord2f(0.0f, 1.0f); gl.glVertex3f(-1.0f, 1.0f, 0.0f);   // Coin Haut Gauche
			gl.glTexCoord2f(1.0f, 1.0f); gl.glVertex3f( 1.0f, 1.0f, 0.0f);   // Coin Haut Droit
			gl.glTexCoord2f(1.0f, 0.0f); gl.glVertex3f( 1.0f,-1.0f, 0.0f);   // Coin Bas Droit
			gl.glTexCoord2f(0.0f, 0.0f); gl.glVertex3f(-1.0f,-1.0f, 0.0f);   // Coin Bas Gauche
		//Fin du carré
		gl.glEnd();
 
		gl.glFlush();

		/* augmentation de l'angle de rotation pour l'itération suivante 
		si la page n'est pas complètement tournée */         
		if (rotateT<180f){
			rotateT += 0.2f;
			iteration = iteration+1;
		}
		
		//sinon fin de la tourne de page 
		else{
			iteration = 0;
			rotateT = 0.0f;
			previousTexture = currentTexture;
			direction = "";
			isTurned = true;
		}
		
		
	}
//------------------------------------------------------------------------------------------------------
	//méthode appelée à l'initalisation de la classe TourneDePage
	@Override
	public void init(GLAutoDrawable glDrawable) {
		GL2 gl = glDrawable.getGL().getGL2();
		
		//initialisations des paramètres pour le dessin
		//les couleurs sont nuancées
		gl.glShadeModel(GLLightingFunc.GL_SMOOTH);
		//la couleur courante sera le noir transparent lorsque le buffer de couleurs sera nettoyé
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		//la valeur du buffer de profondeur sera de 1.0f lorsque celui-si sera nettoyé
		gl.glClearDepth(1.0f);
		//autorise les tests de profondeurs
		gl.glEnable(GL.GL_DEPTH_TEST);
		//autorise l'utilisation de textures 2D
		gl.glEnable(GL.GL_TEXTURE_2D); 
		//paramètrage permettant de faire de la transparence
		gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);
		//autorise le mélange de couleur demandé par la fonstion précédente
		gl.glEnable(GL.GL_BLEND); 
		/*une primitive est dessinée ssi sa profondeur est <= à la valeur stockée 
		 dans le buffer de profondeur*/
		gl.glDepthFunc(GL.GL_LEQUAL);
		//la qualité des couleurs, des textures et du brouillard est la plus élevée possible
		gl.glHint(GL2ES1.GL_PERSPECTIVE_CORRECTION_HINT, GL.GL_NICEST);
		
		/*création du fichier dans lequel seront enregistré les nouvelles textures 
		et initialisation de la texture courante*/
		try{
			f = new File(System.getProperty("user.dir") + "\\Images\\img.png");;
			currentTexture = TextureIO.newTexture(f, false);
			/*les pixels sur lesquels sont peints les textures le sont avec 
			la moyenne des texures l'entourant*/
			currentTexture.setTexParameteri(gl, GL2.GL_TEXTURE_MIN_FILTER, GL.GL_LINEAR);
			currentTexture.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL.GL_LINEAR);
		   
			}
			catch (Exception e){
				System.out.println("erreur init  : " + e.getMessage());
			}
		/*la texture de la page précédemment lue sera celle de la page actuellement lue lorsque 
		 lorsque le lecteur demandera une nouvelle page*/
		previousTexture = currentTexture;
	}
 //----------------------------------------------------------------------------------------------------
	//méthode appelée pour redimensionner et créer la perspective sous laquelle les pages sont observées
	@Override
	public void reshape(GLAutoDrawable gLDrawable, int x, int y, int width, int height) {
		GL2 gl = gLDrawable.getGL().getGL2();
		final float aspect = (float) width / (float) height;
		//les opérations qui suivent affecteront la pile de matrices de projection
		gl.glMatrixMode(GLMatrixFunc.GL_PROJECTION);
		gl.glLoadIdentity();
		final float fh = 0.5f;
		final float fw = fh * aspect;
		//créé la perspective sous laquelle seront vue les pages
		gl.glFrustumf(-fw, fw, -fh, fh, 1.0f, 1000.0f);
		/*les opérations qui suivent affecteront la pile de matrices de modelview */
		gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW);
		gl.glLoadIdentity();
	}
 //-----------------------------------------------------------------------------------------------------
	//méthode appelée lorsque l'on arrête d'afficher l'image 
	@Override
	public void dispose(GLAutoDrawable gLDrawable) {
		GL2 gl = gLDrawable.getGL().getGL2();
		/*enlève les tests de profondeur, les textures 2D et la possibilité 
		de faire de la transparence*/
		gl.glDisable(GL.GL_DEPTH_TEST);
		gl.glDisable(GL.GL_TEXTURE_2D); 
		gl.glDisable(GL.GL_BLEND); 
	}
 //----------------------------------------------------------------------------------------------------- 
	//méthode appelée lorsque l'utilisateur veut changer de page
	public void turnPage(String direction){
		synchronized(lock){
			this.direction = direction;
			isLoaded = false;
			isTurned = false;
		}	
	}	
}