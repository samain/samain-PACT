package decoupage;

import java.util.*;


public class PageMaker implements PageInterface {

	private int currentPage;
	
	private ArrayList<String> pageList;
	
	public PageMaker()
	{
		this.currentPage = 0;
		this.pageList = new ArrayList<String>();
	};
	
	public String sendNewPage(String mouvement){
		return "";
	};
	
	public String[] setBook(String uri){
		return null;
	};
	
}
