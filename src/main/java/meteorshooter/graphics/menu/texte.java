package meteorshooter.graphics.menu;

public class texte {

    private String Francais;
    private String Anglais;

    public texte(String francais ,String anglais){
        this.Francais = francais;
        this.Anglais = anglais;
    }
    

    public String GetTexte(String Langue){
        if (Langue.equals("Francais")){return Francais;}
        else if (Langue.equals("Anglais")){return Anglais;}
        else{return "";}
    }
}
