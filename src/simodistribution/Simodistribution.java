/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simodistribution;

import Modeles.FrenchNumberToWords;
import Modeles.Produits;
import Modeles.production_fichier_excel;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.filechooser.FileSystemView;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabStop;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.border.Border;


/**
 *
 * @author Orion
 */
public class Simodistribution {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
       // Produits p = new Produits("je ","suis","kake"); 
        
        Random r = new Random();
        int n = r.nextInt(999999);
        ArrayList<Produits> liste_de_produits=new ArrayList<>();
       FileSystemView fsv = FileSystemView.getFileSystemView();
       File f = fsv.getDefaultDirectory();
       String chemin=f.getPath()+"//"+"SimoDitribution"+"//"+"Carte.pdf";
       String a=System.getProperty("user.home") + "\\Desktop\\Simo";
       Date d =new Date();
       DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(
        DateFormat.MEDIUM,
        DateFormat.MEDIUM);
       SimpleDateFormat annee = new SimpleDateFormat("yyyy");
       String anne= annee.format(d);
       SimpleDateFormat mois = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
       String moi= mois.format(d);
       //SimpleDateFormat mois = new SimpleDateFormat("MM");
       String moiss= mediumDateFormat.format(d);
       SimpleDateFormat jour = new SimpleDateFormat("dd");
       String jou= jour.format(d);
       production_fichier_excel p =new production_fichier_excel();
        int pp=p.cheminfichier();
       
       JOptionPane.showMessageDialog(null,"le moi    "+moi);
       System.out.println(moi);
      //----ici je cree un document avec  une taille de papier A4
      /*
  Document document = new Document();
    try
    {
      PdfWriter.getInstance(document, new FileOutputStream(a));
      document.open();
      //---les variables
      
      String nom_prestataire="NG DISTRIBUTION"+ "\n";
      String numerodelafacture="FACTURE N° 068"+ "\n"; 
      String Object="\n"+"VENTE DE PRODUITS AGROALIMENTAIRES ET ENTRETIEN"+"\n"
                    +"RC N° RC/YAO/2017/A/3399 du 17 Août 2017"+"\n"
                    +"N° NUI : PO789126400178"+"\n"
                    +"Tel : 691 32 50 85 / 674 10 48 89"+"\n"+"\n";
      
      String nomdelasocietes=" "+"SOCIETE OUMBE SARL";
      
      //---- pour souligner les elements
      
      Chunk underlined = new Chunk(numerodelafacture);
      underlined.setUnderline(2f, -4f);
      Chunk underlined1 = new Chunk("DOIT :");
      underlined1.setUnderline(1f, -4f);
      Chunk underlined2 = new Chunk("Deux cent soixante et un mille francs cfa");
      underlined2.setUnderline(1f, -4f);
      Chunk underlined3 = new Chunk("Le Client");
      underlined3.setUnderline(1f, -4f);
      Chunk underlined4 = new Chunk("LA DIRECTION");
      underlined4.setUnderline(1f, -4f);
      // 
      
      //---les paragraphes
      Paragraph client = new Paragraph("",FontFactory.getFont(FontFactory.TIMES_BOLD,12,Font.BOLD,BaseColor.BLACK));
      Paragraph direction = new Paragraph("",FontFactory.getFont(FontFactory.TIMES_BOLD,12,Font.BOLD,BaseColor.BLACK));
      Paragraph datedefais = new Paragraph("Fait a yaounde, le 02 juin 2020",FontFactory.getFont(FontFactory.TIMES_BOLD,12,Font.BOLD,BaseColor.BLACK));
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
      
      
      tablefin.addCell(cli);
      //tablefin.addCell("");
      tablefin.addCell(direc);
      
      //----mettre le contenue du tableau
      
      table.addCell("4");
      table.addCell("boite de conserve");
      table.addCell("25300");
      table.addCell("470000");
      table.addCell("44");
      table.addCell("boite de conserve");
      table.addCell("253000");
      table.addCell("490000");
      table.addCell("44");
      table.addCell("boite de conserve");
      table.addCell("253000");
      table.addCell("490000");
      table.addCell("44");
      table.addCell("boite de conserve");
      table.addCell("253000");
      table.addCell("490000");
      table.addCell("44");
      table.addCell("boite de conserve");
      table.addCell("253000");
      table.addCell("490000");
      table.addCell("44");
      table.addCell("boite de conserve");
      table.addCell("253000");
      table.addCell("490000");
      table.addCell("44");
      table.addCell("boite de conserve");
      table.addCell("253000");
      table.addCell("490000");
      table.addCell("44");
      table.addCell("boite de conserve");
      table.addCell("253000");
      table.addCell("490000");
      table.addCell("44");
      table.addCell("boite de conserve");
      table.addCell("253000");
      table.addCell("490000");
      table.addCell("44");
      table.addCell("boite de conserve");
      table.addCell("253000");
      table.addCell("490000");
      table.addCell("44");
      table.addCell("boite de conserve");
      table.addCell("253000");
      table.addCell("490000");
      table.addCell("44");
      table.addCell("boite de conserve");
      table.addCell("253000");
      table.addCell("490000");
      table.addCell("44");
      table.addCell("boite de conserve");
      table.addCell("253000");
      table.addCell("490000");
      table.addCell("44");
      table.addCell("boite de conserve");
      table.addCell("253000");
      table.addCell("490000");
      table.addCell("44");
      table.addCell("boite de conserve");
      table.addCell("253000");
      table.addCell("490000");
      table.addCell("44");
      table.addCell("boite de conserve");
      table.addCell("253000");
      table.addCell("490000");
      table.addCell("44");
      table.addCell("boite de conserve");
      table.addCell("253000");
      table.addCell("490000");
      
      
      
      
      
      // total
      
      table.addCell(Total);
      table.addCell("5000882200");
      
      
      
      
      
      
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
    } 
   
    document.close();
    }
    
    

    */
    
    
      // System.out.println(chemin);
   }
}

        
        //int a=Integer.parseInt("2i");
       // System.out.println(a); 
        //System.out.println("voici la designation du produit   "+p.getDesignation());
        
    
   
