package decoupage;

import java.io.IOException;
import java.util.*;

//classe r�lisant les fonctions du d�coupage en page d'un livre

public class PageMaker implements PageInterface {
	private TextInterface text;
	private int currentPage;
	private ArrayList<String> pageList;
	
//-------------------------------------------------------------------------------------
	//constructeur de la classe PageMaker
	public PageMaker(String textURI, int font)
	{
		this.text = new Text(textURI, font);
		try {
			this.pageList = text.CutInPages();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.currentPage = 0;
	}
//---------------------------------------------------------------------------------------	
	//m�thode renvoyant le texte � analyser de la page demand�e par l'utilisateur
	public String sendNewPage(String mouvement){
		String newPage = "";
		
		//disjonction de cas sur le mouvement de l'utilisateur
		switch(mouvement){
			//si le lecteur veut avancer d'une page
			case "right" : 
				if (!isLast()){
					newPage = pageList.get(currentPage+1);
					currentPage++;
				}
				break;
			//si le lecteur veut reculer d'une page	
			case "left"  : 
				if (!isFirst()){
					newPage =  pageList.get(currentPage-1);
					currentPage--;
				}
				break;
			//si l'on veut analyser le texte de la page courante	
			case "this" : 
				newPage = pageList.get(currentPage);
                break;
         	}
		
		return newPage;
	}
//------------------------------------------------------------------------------------	
	//m�thode testant si la page lue est la premi�re du livre
	public boolean isFirst(){
		return (currentPage == 0);
	}
//-------------------------------------------------------------------------------------	
	//m�thode testant si la page lue est la derni�re du livre
	public boolean isLast(){
		return (currentPage == (pageList.size() - 1));
	}

	
}
