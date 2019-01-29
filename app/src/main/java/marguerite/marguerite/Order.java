package marguerite.marguerite;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

import java.util.Map;

public class Order {

    private String m_commentaire;
    private  String m_statut;
    private int m_creneau_attente;
    private double m_prix_total;
    private Map<String,Timestamp> date;
    private DocumentReference m_restaurant_id;
    private DocumentReference m_user_id;


    public Order()
    {

    }

    public Order(String statut)
    {
        m_statut=statut;
    }
    public Order(String commentaire, String statut, int creneau_attente,double prix_total, DocumentReference restaurant_id, DocumentReference user_id)
    {
        m_commentaire=commentaire;
        m_statut=statut;
        m_creneau_attente=creneau_attente;
        m_prix_total=prix_total;
        m_restaurant_id=restaurant_id;
        m_user_id=user_id;
    }

    public String getCommentaire()
    {
        return m_commentaire;
    }

    public String getStatut()
    {
        return m_statut;
    }

    public int getCreneau_attente()
    {
        return m_creneau_attente;
    }

    public double getPrixTotal()
    {
        return m_prix_total;
    }

    public Map<String, Timestamp> getDate()
    {
        return date;
    }

    public void setStatut(String nouv_statut)
    {
        m_statut=nouv_statut;
    }

    public void setPrixTotal(double nouv_prix_total)
    {
        m_prix_total=nouv_prix_total;
    }
}