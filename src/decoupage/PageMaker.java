package Decoupage;

import java.io.IOException;
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
		this.currentPage = 0;
	}
	
	public String sendNewPage(String mouvement){
		String newPage = "";
		switch(mouvement){
		case "right" : if ((currentPage<(pageList.size()-2))&&(currentPage>-3)){
			newPage = pageList.get(currentPage+2);
			currentPage++;
            }
            else{
            currentPage++;
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
	    pages[0] = "";
	    pages[1] = pageList.get(0);
	    if (pageList.size()<2){
	    	pages[2] = "";
	    }
	    else{
	    	pages[2] = pageList.get(1);
	    }
		return	pages;
	}
	
}
