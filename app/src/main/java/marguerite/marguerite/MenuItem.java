package marguerite.marguerite;

public  class MenuItem extends Object{


    private String m_nom;
    private String m_categorie;
    private String m_description;
    private String m_disponibilite;
    private Double m_prix_unitaire;
    private Integer m_quantite;

    public MenuItem(String nom, String categorie, String description, String disponibilite, Double prix_unitaire) {
        this(nom, categorie, description, disponibilite, prix_unitaire, 0);
    }

    public MenuItem(String nom, String categorie, String description, String disponibilite, Double prix_unitaire, Integer quantite){

        m_nom=nom;
        m_categorie=categorie;
        m_description= description;
        m_disponibilite=disponibilite;
        m_prix_unitaire=prix_unitaire;
        m_quantite=quantite;

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

    public Integer getQuantite(){return  m_quantite;}


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

    public  void  setQuantite(Integer new_quantite) {m_quantite=new_quantite;}

}
