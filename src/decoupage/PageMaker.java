package Decoupage;

import java.util.*;
import java.io.*;


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
		this.currentPage = 1;
		/* this.pageList = new ArrayList<String>();
		this.currentPage = 1;
		pageList.add("");
		pageList.add("Il �tait une fois dans la for�t...");
		pageList.add("un petit lutin vivant avec sa famille.");
		pageList.add("Ce foyer �tait chaleureux et plein d'amour");
		pageList.add("Mais une nuit...");
		pageList.add("La neige, jalouse de leur bonheur");
		pageList.add("Emporta leur maion dans un grand blizzard");
		pageList.add("Isol� des siens, errant ans la nuit,");
		pageList.add("Il se dirigea avec tristesse vers la ville la plus proche.");
		pageList.add("L�-bas, on lui vanta la mer");
		pageList.add("n'ayant jamais �t� � la plage, il y cherchea du r�confort");
		pageList.add("Il y fit une grande f�te."); */
	}
	
	/* public String sendNewPage(String mouvement){
		String newPage = "";
		switch(mouvement){
		case "right" : 
			if ((currentPage<11)&&(currentPage>-1)){
			newPage = pageList.get(currentPage+2);
			currentPage++;
			}
			else{
				currentPage++;
			}
			break;
		case "left"  : 	
			if ((currentPage<12)&&(currentPage>0)){
				newPage =  pageList.get(currentPage-2);
				currentPage--;
			}
			else{
				currentPage--;
			}
			break;
		}
		return newPage;
	} */
	
	public String sendNewPage(String mouvement){
		String newPage = "";
		switch(mouvement){
		case "right" : if ((currentPage<(pageList.size()-2))&&(currentPage>-3)){
			newPage = pageList.get(currentPage+2);
			currentPage++;
		//	System.out.println("page courante : " + currentPage);
            }
            else{
            currentPage++;
        //    System.out.println("page courante : " + currentPage);
            }
            break;
         case "left"  : if ((currentPage<pageList.size()+2)&&(currentPage>1)){
            newPage =  pageList.get(currentPage-2);
            currentPage--;
            }
            else{
            currentPage--;
            }
            break;
            }
		return newPage;
	}
	
	

	public String[] firstPages() {
		String[] pages = new String[3];
		pages[0] = pageList.get(0);
		pages[1] = pageList.get(1);
		pages[2] = pageList.get(2);
		
		return	pages;
	}
	
}
