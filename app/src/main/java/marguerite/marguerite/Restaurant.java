package marguerite.marguerite;

public class Restaurant {
    private String m_adresse;
    private  String m_nom;


    public String getAdresse()
    {
        return m_adresse;
    }
    public String getNom()
    {
        return m_nom;
    }

    public  void setAdresse(String new_adresse)
    {
        m_adresse=new_adresse;
    }

    public  void setNom(String new_nom)
    {
        m_nom=new_nom;
    }

}
