package Decoupage;

import java.io.IOException;
import java.util.*;


public class PageMaker implements PageInterface {
	private Text text;
	private int currentPage;
	
	private ArrayList<String> pageList;
	
	public PageMaker(String textURI, int policeSize)
	{
		this.text = new Text(textURI, policeSize);
		try {
			this.pageList = text.CutInPages();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.currentPage = 1;
	}
	
	public String sendNewPage(String mouvement){
		String newPage = "";
		switch(mouvement){
		case "right" : if ((currentPage<11)&&(currentPage>-1)){
							newPage = pageList.get(currentPage+1);
							currentPage++;
							System.out.println("page courante : " + currentPage);
		}
		else{
			currentPage++;
			System.out.println("page courante : " + currentPage);
		}
		break;
		case "left"  : 	if ((currentPage<12)&&(currentPage>0)){
			                newPage =  pageList.get(currentPage-1);
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
