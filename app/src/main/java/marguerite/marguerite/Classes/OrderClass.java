package marguerite.marguerite.Classes;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;

import java.util.Map;

public class OrderClass {

    //Attributs
    private String m_commentaire;
    private  String m_statut;
    private String m_numero_commande;
    private int m_creneau_attente;
    private double m_prix_total;
    private Map<String,Object> m_date;
    private DocumentReference m_restaurant_id;
    private DocumentReference m_user_id;

    //Constructeur
    public OrderClass(String commentaire, String statut, int creneau_attente, double prix_total,
                      DocumentReference restaurant_id, DocumentReference user_id, Map<String,Object> date,String numero_commande)
    {
        m_commentaire=commentaire;
        m_statut=statut;
        m_numero_commande=numero_commande;
        m_creneau_attente=creneau_attente;
        m_prix_total=prix_total;
        m_restaurant_id=restaurant_id;
        m_user_id=user_id;
        m_date=date;
    }

    //Getter et Setter
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

    public Map<String, Object> getDate()
    {
        return m_date;
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