package marguerite.marguerite;

public  class Category extends Object{


    private String m_nom;
    private String m_categorie;

    public Category(String nom,String categorie){

        m_nom=nom;
        m_categorie=categorie;
    }

    public String getNom()
    {
        return m_nom;
    }

    public String getCategorie()
    {
        return m_categorie;
    }

    public void setNom(String nouv_nom)
    {
        m_nom=nouv_nom;
    }

    public void setCategorie(String nouv_categorie)
    {
        m_categorie=nouv_categorie;
    }

}
