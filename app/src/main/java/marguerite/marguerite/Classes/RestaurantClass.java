package marguerite.marguerite.Classes;

import java.util.HashMap;
import java.util.Map;

public class RestaurantClass extends Object{
    private String m_adresse;
    private String m_nom;
    private Map<String,Object> m_horaires;



    public RestaurantClass(String nom, String adresse, Map<String, Object> horaires)
    {
        m_adresse=adresse;
        m_nom=nom;
        m_horaires=horaires;
    }


    public String getAdresse()
    {
        return m_adresse;
    }
    public String getNom()
    {
        return m_nom;
    }
    public Map<String, Object> getHoraires() {return m_horaires;}


    public  void setAdresse(String new_adresse)
    {
        m_adresse=new_adresse;
    }
    public  void setNom(String new_nom)
    {
        m_nom=new_nom;
    }
    public  void setHoraire(Map<String,Object> new_horaires) {m_horaires=new_horaires;}

}
