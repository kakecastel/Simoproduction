/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modeles;


import java.io.File;
//import org.apache.poi.hssf.util.Region;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFCell;


/**
 *
 * @author Orion
 */
public class production_fichier_excel {

    
   public production_fichier_excel() {}
   
   public int cheminfichier() throws FileNotFoundException, IOException{
     int sortie=15;
      String lutte="";
     
            String chemindocumentexel=System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\ORIONASSUR\\src\\Carte_rose.xls";
            File fb_Carte_rose = new File("C:\\Users\\Orion\\Desktop\\SimoDitributionDocuments\\ProductionGeneral\\production.xls");
            
            FileInputStream pren_chemn_fichierexel = new FileInputStream(fb_Carte_rose);
            HSSFWorkbook workbookexel = new HSSFWorkbook(pren_chemn_fichierexel);
            HSSFSheet feuilleexel=workbookexel.getSheetAt(0);
            //remplir la feuille par  les bonnes valeurs
            int a=feuilleexel.getLastRowNum()+1;
            int i=0;
             JOptionPane.showMessageDialog(null,"voici le nom    "+feuilleexel.getLastRowNum());
            //feuilleexel.getRow(1).getCell(0).setCellValue("1");
           // feuilleexel.getRow(feuilleexel.getLastRowNum()+1).getCell(1).setCellValue("2");
           // feuilleexel.getRow(feuilleexel.getLastRowNum()+1).getCell(2).setCellValue("3");
            //--- fermer le flux de fichier
            pren_chemn_fichierexel.close();
            
            // fermer pour ecriture 
             
            FileOutputStream out = new FileOutputStream(fb_Carte_rose);
            workbookexel.write(out);
            out.close();
            
            sortie = 1;
            
     /*if
(getLastRowNum()<1){
    res="Sheet Cannot be empty";
return
}*/
     

   return sortie;
   }
}
    
    /*int test=processus.compareTo("carte rose");
    int test1=processus.compareTo("attestation");
    copy_des_templates(chemin_complet_du_dossier_client);
    
    if(test==0){
            String chemin=chemin_complet_du_dossier_client+File.separator+"Carte_rose.xls";
            File fb_Carte_rose = new File(chemin);
            
            if(fb_Carte_rose.exists()){
            FileInputStream pren_chemn_fichier = new FileInputStream(fb_Carte_rose);
            HSSFWorkbook workbook = new HSSFWorkbook(pren_chemn_fichier);
            HSSFSheet feuille=workbook.getSheetAt(0);
   //-----PREMIERE LIGNE

      feuille.getRow(1).getCell(1).setCellValue(le_contrat.questionnaire.vehicule.proprio.getAutoinsurednom());
      feuille.getRow(1).getCell(3).setCellValue(le_contrat.questionnaire.vehicule.proprio.getAutoinsurednom());
      feuille.getRow(1).*/
 