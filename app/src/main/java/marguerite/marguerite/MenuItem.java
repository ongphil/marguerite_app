package marguerite.marguerite;

public  class MenuItem extends Object{


    private String m_nom;
    private String m_categorie;
    private String m_description;
    private String m_disponibilite;
    private Double m_prix_unitaire;

    public MenuItem(String nom,String categorie,String description,String disponibilite, Double prix_unitaire){

        m_nom=nom;
        m_categorie=categorie;
        m_description= description;
        m_disponibilite=disponibilite;
        m_prix_unitaire=prix_unitaire;

    }

    public String getNom()
    {
        return m_nom;
    }

    public String getCategorie()
    {
        return m_categorie;
    }

    public String getDescription(){return m_description;}

    public String getDisponibilite(){return m_disponibilite;}

    public Double getPrix_unitaire(){return  m_prix_unitaire;}


    public void setNom(String nouv_nom)
    {
        m_nom=nouv_nom;
    }

    public void setCategorie(String nouv_categorie)
    {
        m_categorie=nouv_categorie;
    }

    public  void setDescription(String new_description) {m_description=new_description;}

    public void setDisponibilite(String new_disponibilite) {m_disponibilite = new_disponibilite;}

    public  void setPrix_unitaire(Double new_prix_unitaire) {m_prix_unitaire=new_prix_unitaire;}

}
