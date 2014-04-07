package decoupage;

import java.io.IOException;
import java.util.*;

//classe r�lisant les fonctions du d�coupage en page d'un livre

public class PageMaker implements PageInterface {
	private Text text;
	private int currentPage;
	
	private ArrayList<String> pageList;
	
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
	
	public String sendNewPage(String mouvement){
		String newPage = "";
		switch(mouvement){
		case "right" : if (!isLast()){
			newPage = pageList.get(currentPage+1);
			currentPage++;
            }
            break;
         case "left"  : if (!isFirst()){
            newPage =  pageList.get(currentPage-1);
            currentPage--;
            }
            break;
         case "this" : newPage = pageList.get(currentPage);
                       break;
         }
		return newPage;
	}
	
	
	public boolean isFirst(){
		return (currentPage == 0);
	}
	
	public boolean isLast(){
		return (currentPage == (pageList.size() - 1));
	}

	
}
