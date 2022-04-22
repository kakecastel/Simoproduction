/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modeles;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Orion
 */
public class Facturation {

    public Facturation() {
        
        
    }
    
    public void fichierpdf(ArrayList<Produits> liste_de_produits,
            String nomsociete,String montanttotal,
            String prestataire,String Numerofacture,
            Date datedefait) throws FileNotFoundException{
    Document document = new Document();//FACTURE06_03:18:53.pdf
    try 
    {
      PdfWriter.getInstance(document, new FileOutputStream(cheminfichier(contruirechemin(datedefait))));
      document.open();
     //---les variables
      String datedefaisfacture="Fait à yaounde, le "+datefacture(datedefait);
      String nom_prestataire=prestataireservice(prestataire)+ "\n";
      String numerodelafacture=Numerofacture+ "\n"; 
      String Object=objectfacture(prestataire);
      
      String nomdelasocietes=" "+nomsociete;
      
      //---- pour souligner les elements
      
      Chunk underlined = new Chunk(numerodelafacture);
      underlined.setUnderline(2f, -4f);
      Chunk underlined1 = new Chunk("DOIT :");
      underlined1.setUnderline(1f, -4f);
      Chunk underlined2 = new Chunk(montanttotalenlettre(montanttotal)+" francs CFA");
      underlined2.setUnderline(1f, -4f);
      Chunk underlined3 = new Chunk("Le Client");
      underlined3.setUnderline(1f, -4f);
      Chunk underlined4 = new Chunk("LA DIRECTION");
      underlined4.setUnderline(1f, -4f);
      // 
      
      //---les paragraphes
      
      Paragraph client = new Paragraph("",FontFactory.getFont(FontFactory.TIMES_BOLD,12,Font.BOLD,BaseColor.BLACK));
      Paragraph direction = new Paragraph("",FontFactory.getFont(FontFactory.TIMES_BOLD,12,Font.BOLD,BaseColor.BLACK));
      Paragraph datedefais = new Paragraph(datedefaisfacture,FontFactory.getFont(FontFactory.TIMES_BOLD,12,Font.BOLD,BaseColor.BLACK));
      Paragraph totalenLettre = new Paragraph("Arrete la presente facture a la somme de : ",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD,BaseColor.BLACK));
      Paragraph titre =new Paragraph(nom_prestataire,FontFactory.getFont(FontFactory.TIMES_BOLD,30,Font.BOLD,BaseColor.BLACK));
      Paragraph facture = new Paragraph("",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD,BaseColor.BLACK));
      Paragraph nomdelasociete = new Paragraph("",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD,BaseColor.BLACK));
      
      
      //---souligne les elements
      
      totalenLettre.add(underlined2);
      facture.add(underlined);
      nomdelasociete.add(underlined1);
      client.add(underlined3);
      direction.add(underlined4);
      
      
      
      //----positionner les elemnents
      
      facture.setAlignment(Element.ALIGN_CENTER);
      titre.setAlignment(Element.ALIGN_CENTER);
      datedefais.setAlignment(Element.ALIGN_RIGHT);
      
      
      //-----Creation de des Tables
      
      PdfPTable table = new PdfPTable(4); 
      PdfPTable tablefin = new PdfPTable(2); 
      
      
      //contenu du tableau.
      
      //----titre du tableau
      PdfPCell Qte = new PdfPCell(new Paragraph("Qte",FontFactory.getFont(FontFactory.TIMES_BOLD,12,Font.BOLD,BaseColor.BLACK)));
      Qte.setHorizontalAlignment(Element.ALIGN_CENTER);
      PdfPCell Designation = new PdfPCell(new Paragraph("Designation",FontFactory.getFont(FontFactory.TIMES_BOLD,12,Font.BOLD,BaseColor.BLACK)));
      Designation.setHorizontalAlignment(Element.ALIGN_CENTER);
      PdfPCell PrixUnitaire = new PdfPCell(new Paragraph("Prix Unitaire",FontFactory.getFont(FontFactory.TIMES_BOLD,12,Font.BOLD,BaseColor.BLACK)));
      PrixUnitaire.setHorizontalAlignment(Element.ALIGN_CENTER);
      PdfPCell PrixTotal = new PdfPCell(new Paragraph("Prix Total",FontFactory.getFont(FontFactory.TIMES_BOLD,12,Font.BOLD,BaseColor.BLACK)));
      PrixTotal.setHorizontalAlignment(Element.ALIGN_CENTER);
      PdfPCell Total = new PdfPCell(new Paragraph("TOTAL",FontFactory.getFont(FontFactory.TIMES_BOLD,15,Font.BOLD,BaseColor.BLACK)));
      Total.setHorizontalAlignment(Element.ALIGN_CENTER);
      Total.setColspan(3);
      PdfPCell cli = new PdfPCell(client);
      cli.setHorizontalAlignment(Element.ALIGN_LEFT);
      cli.setBorder(Rectangle.NO_BORDER);
      PdfPCell direc = new PdfPCell(direction);
      direc.setHorizontalAlignment(Element.ALIGN_RIGHT);
      direc.setBorder(Rectangle.NO_BORDER);
     
      
      
      
      //------contenu du titre du tableau 1
      
      table.addCell(Qte);
      table.addCell(Designation);
      table.addCell(PrixUnitaire);
      table.addCell(PrixTotal);
      
      //------contenu du tableau 2
      
      tablefin.addCell(cli);
      tablefin.addCell(direc);
      
      //----mettre le contenue du tableau
       for (int i = 0; i < liste_de_produits.size(); i++) {
            Produits get = liste_de_produits.get(i);
            table.addCell(mettreZero(Integer.parseInt(get.getQuantite()))+" "+get.getLibelleQte());   //----quantite+libelle
            table.addCell(get.getDesignation()); //----desigantion
            table.addCell(get.getPrix_unitaire());            //-----prix unitaire
            table.addCell(String.valueOf(Integer.parseInt(get.getQuantite())*Integer.parseInt(get.getPrix_unitaire())));          //----Prix total

        }
      
      
      
      
      // total
      
      table.addCell(Total);
      table.addCell(montanttotal);
      
      
      
      
      
      
      //--ajouter le nom de la valeur dans le document 
      
      nomdelasociete.add(nomdelasocietes);
      
      
      document.add(titre);
      document.add(new Paragraph(Object));
      document.add(facture);
      document.add(new Paragraph("\n"));
      document.add(nomdelasociete);
      document.add(new Paragraph("\n"));
      document.add(table);
      document.add(new Paragraph("\n"));
      document.add(totalenLettre);
      document.add(new Paragraph("\n"+"\n"+"\n"+"\n"));
      document.add(datedefais);
      document.add(new Paragraph("\n"+"\n"+"\n"));
      document.add(tablefin);

      
    } catch (DocumentException de) {
      de.printStackTrace();
    } catch (IOException de) {
      de.printStackTrace();
    }
   
    document.close();
    
    
    
    
    }
    
    private String cheminfichier(String nomfichier){
     String sortie="";
     String chemin=System.getProperty("user.home") + "\\Desktop\\SimoDitributionDocuments";
     File document_pdf = new File(chemin);
     if(document_pdf.exists())
           sortie=chemin+"\\FACTURE_DU _"+nomfichier+".pdf";
     else{
          document_pdf.mkdir();
          sortie=chemin+"\\FACTURE_DU _"+nomfichier+".pdf";
     }
     return sortie;
    
    }
    
    private String montanttotalenlettre(String montant){
    FrenchNumberToWords translation = new FrenchNumberToWords();
    long monLong=Long.parseLong(montant);
    return translation.convert(monLong);
    }
    private String datefacture(Date dat){
        if(dat != null){
           DateFormat datefac = DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.MEDIUM);
             return datefac.format(dat);
        }
        return "";
    
    }
     private String contruirechemin(Date dat){
        if(dat != null){
           SimpleDateFormat mois = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
             return mois.format(dat);
        }
        return "";
    
    }
    
    /* SimpleDateFormat mois = new SimpleDateFormat("MM_hh:mm:ss");
       String moi= mois.format(d);*/
    private  String objectfacture(String object) {
      String sortie="";
      if(object.compareTo("SIMO")==0){
      sortie="\n"+"SIMO KOUNTCHOU MIDREL"+"\n"
             +"Gmail simomidrel@gmail.com"+"\n"
             +"Tel : 691 32 50 85 / 674 10 48 89"+"\n"+"\n";
      }
      else if(object.compareTo("MADO")==0){
      sortie="\n"+"SOCIETE MADO SARL"+"\n"
             +"Tel : 691 32 50 85 / 674 10 48 89"+"\n"
             +"B.p: 5997"+"\n"+"\n";
      }
      else{
          sortie="\n"+"VENTE DE PRODUITS AGROALIMENTAIRES ET ENTRETIEN"+"\n"
                    +"RC N° RC/YAO/2017/A/3399 du 17 Août 2017"+"\n"
                    +"N° NUI : PO789126400178"+"\n"
                    +"Tel : 691 32 50 85 / 674 10 48 89"+"\n"+"\n";
      }
      return sortie;
  }
    
   private  String prestataireservice(String object) {
      String sortie="";
      if(object.compareTo("SIMO")==0){
      sortie="SIMO DISTRIBUTION"+ "\n";
      }
      else if(object.compareTo("MADO")==0){
      sortie="MADO DISTRIBUTION"+ "\n";
      }
      else{
          sortie="NG DISTRIBUTION"+ "\n";
      }
      return sortie;
  }
    
      private String mettreZero(int val){
         String sortie;
      if(val<=9)
          sortie="0"+String.valueOf(val);
      else
          sortie=String.valueOf(val);
       return sortie;
    } 

   
}
