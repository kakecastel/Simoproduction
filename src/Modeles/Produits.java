/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modeles;

/**
 *
 * @author Orion
 */
public class Produits {
    int numero;
    String quantite,designation,prix_unitaire,libelleQte;

    public Produits(String quantite, String designation, 
            String prix_unitaire,String libelleQte) {
        this.quantite = quantite;
        this.designation = designation;
        this.prix_unitaire = prix_unitaire;
        this.libelleQte = libelleQte;
    }

    public String getLibelleQte() {
        return libelleQte;
    }
    

    public int getNumero() {
        return numero;
    }

    public String getQuantite() {
        return quantite;
    }

    public String getDesignation() {
        return designation;
    }

    public String getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setQuantite(String quantite) {
        this.quantite = quantite;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setPrix_unitaire(String prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }
    
    
    
}
