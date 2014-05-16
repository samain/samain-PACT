package Menu;

public class ZDialogInfo {
	  private String nom, police;
	  public ZDialogInfo(){}
	  public ZDialogInfo(String nom, String police){
	    this.nom = nom;
	    this.police = police;
	   
	  }
	  public String toString(){
	    String str;
	    if(this.nom != null && this.police != null){
	      str = "Lecteur :";
	      str += "" + this.nom + "\n";
	      str += "Police : " + this.police + "\n";
	     
	    }
	    else{
	      str = "Aucune information !";
	    }
	    return str;
	  }
	}
