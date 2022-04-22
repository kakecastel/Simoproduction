/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fenetre;

import Modeles.Facturation;
import Modeles.Produits;
import com.sun.glass.events.KeyEvent;
import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 *
 * @author Orion
 */
public class Production extends javax.swing.JFrame {
   
    /**
     * Creates new form Production
     */
    String cheminfichierexel="";
    String codefacture;
    public Production() throws IOException {
        initComponents();
        controle_facture();
        this.cheminfichierexel=creerLesDossiers();
        this.setBounds(250, 140,870, 510);
        construire_le_panel(Production);
        tabfacture.getTableHeader().setFont( new Font( "Tahoma" , Font.BOLD, 16 ));
        tab_production.getTableHeader().setFont( new Font( "Tahoma" , Font.BOLD, 16 ));
        combopresta.addItem("SIMO");
        combopresta.addItem("MADO");
        combopresta.addItem("NG");
    }
    
    
    
    ArrayList<Produits> liste_de_produits=new ArrayList<>();
    
     
    ///-----mettre les zero quand l'entier est zero
    
     private int prixtotal(ArrayList<Produits> liste_de_produits){
         int total=0;

      for (int i = 0; i < liste_de_produits.size(); i++) {
            Produits get = liste_de_produits.get(i);
            total+=Integer.parseInt(get.getQuantite())*Integer.parseInt(get.getPrix_unitaire());
        }
      return total;
    } 
    
    //------creation des dossiers de traitement des informations 
     
     private String creerLesDossiers() throws FileNotFoundException, IOException{
     
     String chemindocumentpdf=System.getProperty("user.home") + "\\Desktop\\SimoDitributionDocuments";
     String chemindocumentexel=System.getProperty("user.home") + "\\Desktop\\SimoDitributionDocuments\\ProductionGeneral";
     File document_pdf = new File(chemindocumentpdf);
     File document_exel = new File(chemindocumentexel);
     if(!document_pdf.exists())
           document_pdf.mkdir();
     if(!document_exel.exists()){
           document_exel.mkdir();
           FileOutputStream out = new FileOutputStream(chemindocumentexel+"\\production.xls");
            HSSFWorkbook  wb = new HSSFWorkbook();
            // créer une feuille
            HSSFSheet  Production = wb.createSheet("production");
            
            // créer une ligne de à l'index 2 dans la feuille Excel
            
            Row myRow = null;     
            HSSFCellStyle cellStyle = null;
            
            //----- mettre le style sur le fichier execel cree
            
            HSSFCellStyle style = wb.createCellStyle();
            HSSFFont font = wb.createFont();
            font.setFontName(HSSFFont.FONT_ARIAL);
            font.setFontHeightInPoints((short)12);
            font.setBold(true);
            style.setFont(font);
            style.setAlignment(HorizontalAlignment.CENTER);
            myRow = Production.createRow(0);
            
            // Ajouter entete du fichier execel
            
            myRow.createCell(0).setCellValue("Code de la Facture");
            myRow.createCell(1).setCellValue("Date de Fait");
            myRow.createCell(2).setCellValue("Montant Total");
            
            JOptionPane.showMessageDialog(null,"voici actuellement le nbre de ligne au debut    "+Production.getLastRowNum());
            // ajouter le style sur chaque cellule
            
            for(int j = 0; j<=2; j++){
                myRow.getCell(j).setCellStyle(style);
            }
            
            
          
            
            //JOptionPane.showMessageDialog(null,"voici actuellement le nbre de ligne a la fin    "+Production.getLastRowNum());
            wb.write(out);
            out.close(); 
     }
            return chemindocumentexel+"\\production.xls";
    
    }
    
    
    ///-----mettre les zero quand l'entier est zero
    
     private String mettreZero(int val){
         String sortie;
      if(val<=9)
          sortie="0"+String.valueOf(val);
      else
          sortie=String.valueOf(val);
       return sortie;
    } 
    /*
     Random r = new Random();
        int n = r.nextInt(999999);
    
    liste_de_produits.size()
    private int controle_facture(){
        if(liste_de_produits.size()>0)
           
        else
    
    (Integer.parseInt(textField.getText())
    jCombo_mode_payemen.getSelectedItem().toString()
         
        
    }
     
    
    
   
    
    */
    
    
    
    
    //--------- pour gerer l'affichage du botton facturation
    
    
    
    private void controle_facture(){
        
      if(liste_de_produits.size()>0)
            facturation.setVisible(true);
           
      else
            facturation.setVisible(false);
         
        
    }
    
    
    //-----enregistre dans le fichier exel
    
    private void enregistredansexel(String chemin,String codefacture,Date datefais,String montanttotat) throws FileNotFoundException, IOException{
         Row myRow = null;     
         File filleexel = new File(chemin);
         DateFormat mediumDateFormat = DateFormat.getDateTimeInstance(
         DateFormat.MEDIUM,
         DateFormat.MEDIUM);  
            if(filleexel.exists()){
            FileInputStream pren_chemn_fichierexel = new FileInputStream(filleexel);
            HSSFWorkbook workbookexel = new HSSFWorkbook(pren_chemn_fichierexel);
            HSSFSheet feuilleexel=workbookexel.getSheetAt(0);
            //remplir la feuille par  les bonnes valeurs
            // ajouter les donnees dans le fichiers exel
            
           JOptionPane.showMessageDialog(null,"voici actuellement le nbre de ligne au debut    "+feuilleexel.getLastRowNum());
                myRow = feuilleexel.createRow(feuilleexel.getLastRowNum()+1);
                myRow.createCell(0).setCellValue(codefacture);
                myRow.createCell(1).setCellValue(mediumDateFormat.format(datefais));
                myRow.createCell(2).setCellValue(montanttotat);
                 JOptionPane.showMessageDialog(null,"voici actuellement le nbre de ligne a la fin    "+feuilleexel.getLastRowNum());
            //--- fermer le flux de fichier
            pren_chemn_fichierexel.close();
            
            // fermer pour ecriture 
             
            FileOutputStream out = new FileOutputStream(filleexel);
            workbookexel.write(out);
            out.close();
            
            }
        
    }
    
    
    
    // Vider les valeurs pour ajouter des nouvelles
     private void vider_valeur_produits(){
        
      textQTE.setText("");
      texdesignation.setText("");
      texprixunitaire.setText("");
      textlibelleQte.setText("");
         
        
    }
    
    // pour gerer le numero des factures
    
    private String numeroFacure(String Chemin) throws IOException{
      int val=0;
        File fb_Carte_rose = new File(Chemin);
            
        if(fb_Carte_rose.exists()){
            FileInputStream pren_chemn_fichier = new FileInputStream(fb_Carte_rose);
            HSSFWorkbook workbook = new HSSFWorkbook(pren_chemn_fichier);
            HSSFSheet feuille=workbook.getSheetAt(0);
            val=feuille.getLastRowNum();
        }
       return mettreZero(val);
        
    }
     
        
    // pour construire les pannels
    
      private void construire_le_panel( javax.swing.JPanel panel){
        contenaire.removeAll();  
        panel.setBounds(0, 0, 843, 400);
        contenaire.add(panel);
        contenaire.repaint();
        contenaire.validate(); 
        
    }
    
    
    private void recharger_produit(ArrayList<Produits> liste_de_produits){
        DefaultTableModel model = (DefaultTableModel)tab_production.getModel();
        model.setRowCount(0);
        Object Rowdata [] = new Object[5];
        for (int i = 0; i < liste_de_produits.size(); i++) {
            Produits get = liste_de_produits.get(i);
            Rowdata[0] = i+1;
            Rowdata[1] = get.getQuantite();
            Rowdata[2] = get.getLibelleQte();
            Rowdata[3] =  get.getDesignation();
            Rowdata[4] =  get.getPrix_unitaire();
            model.addRow(Rowdata);            Rowdata[1] = get.getQuantite()+" "+get.getLibelleQte();

        }
    }
    
    
    private void recharger_facture(ArrayList<Produits> liste_de_produits){
        DefaultTableModel model = (DefaultTableModel)tabfacture.getModel();
        model.setRowCount(0);
        Object Rowdata [] = new Object[5];
        for (int i = 0; i < liste_de_produits.size(); i++) {
            Produits get = liste_de_produits.get(i);
            Rowdata[0] = i+1;
            Rowdata[1] = mettreZero(Integer.parseInt(get.getQuantite()))+" "+get.getLibelleQte();
            Rowdata[2] =  get.getDesignation();
            Rowdata[3] =  get.getPrix_unitaire();
            Rowdata[4] =  Integer.parseInt(get.getQuantite())*Integer.parseInt(get.getPrix_unitaire());
            model.addRow(Rowdata);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Facturation = new javax.swing.JPanel();
        facture = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabfacture = new javax.swing.JTable();
        total = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        numeroDeLaFacture = new javax.swing.JLabel();
        editer = new javax.swing.JLabel();
        combopresta = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        NonSociete = new javax.swing.JLabel();
        Production = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tab_production = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        text_societe = new javax.swing.JTextField();
        ajouter = new javax.swing.JLabel();
        facturation = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Produit_ajouter = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        textQTE = new javax.swing.JTextField();
        texprixunitaire = new javax.swing.JTextField();
        texdesignation = new javax.swing.JTextField();
        textlibelleQte = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        ajouter_produit = new javax.swing.JLabel();
        back = new javax.swing.JLabel();
        contenaire = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        Facturation.setBackground(new java.awt.Color(255, 255, 255));

        facture.setFont(new java.awt.Font("Tahoma", 3, 20)); // NOI18N
        facture.setForeground(new java.awt.Color(0, 51, 102));
        facture.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        facture.setText("DOIT :");
        facture.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 51, 102)));
        facture.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jScrollPane1.setViewportBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 51, 102)));

        tabfacture.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        tabfacture.setForeground(new java.awt.Color(0, 51, 102));
        tabfacture.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Numero", "Qte", "Designation", "Prix Unitaire", "Prix Total"
            }
        ));
        jScrollPane1.setViewportView(tabfacture);

        total.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 51, 102)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("TOTAL");

        jLabel2.setText("jLabel2");
        jLabel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(51, 204, 0)));

        numeroDeLaFacture.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        numeroDeLaFacture.setForeground(new java.awt.Color(0, 51, 102));
        numeroDeLaFacture.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numeroDeLaFacture.setText("facture");
        numeroDeLaFacture.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 51, 102)));
        numeroDeLaFacture.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        editer.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        editer.setForeground(new java.awt.Color(0, 51, 153));
        editer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        editer.setText("editer_la_facture");
        editer.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 153)));
        editer.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        editer.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                editerMouseMoved(evt);
            }
        });
        editer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editerMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editerMouseExited(evt);
            }
        });

        combopresta.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        combopresta.setForeground(new java.awt.Color(0, 51, 153));
        combopresta.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 153)));

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 102));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("prestataire");

        NonSociete.setFont(new java.awt.Font("Tahoma", 3, 20)); // NOI18N
        NonSociete.setForeground(new java.awt.Color(0, 51, 102));
        NonSociete.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NonSociete.setText("DOIT :");
        NonSociete.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 51, 102)));
        NonSociete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout FacturationLayout = new javax.swing.GroupLayout(Facturation);
        Facturation.setLayout(FacturationLayout);
        FacturationLayout.setHorizontalGroup(
            FacturationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FacturationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FacturationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(FacturationLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(total, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FacturationLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(editer, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FacturationLayout.createSequentialGroup()
                        .addComponent(combopresta, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(287, 287, 287)
                        .addComponent(numeroDeLaFacture, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(FacturationLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(FacturationLayout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(facture, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NonSociete, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );
        FacturationLayout.setVerticalGroup(
            FacturationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FacturationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FacturationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(facture, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(NonSociete, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FacturationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(numeroDeLaFacture, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combopresta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(FacturationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(editer, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        Production.setBackground(new java.awt.Color(255, 255, 255));

        tab_production.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        tab_production.setForeground(new java.awt.Color(0, 102, 153));
        tab_production.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Qte ", "Libele_Qte", "Designation", "Prix Unitaire"
            }
        ));
        tab_production.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab_productionMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tab_production);

        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 102, 153));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("DOIT :");
        jLabel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 102, 153)));

        text_societe.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        text_societe.setForeground(new java.awt.Color(0, 51, 153));
        text_societe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        text_societe.setText(" ");
        text_societe.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 153)));
        text_societe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_societeActionPerformed(evt);
            }
        });

        ajouter.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        ajouter.setForeground(new java.awt.Color(0, 51, 153));
        ajouter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ajouter.setText("Ajouter_un_Produit");
        ajouter.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 153)));
        ajouter.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ajouter.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                ajouterMouseMoved(evt);
            }
        });
        ajouter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ajouterMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ajouterMouseExited(evt);
            }
        });

        facturation.setFont(new java.awt.Font("Tahoma", 3, 16)); // NOI18N
        facturation.setForeground(new java.awt.Color(0, 51, 153));
        facturation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        facturation.setText("Facturation");
        facturation.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 153)));
        facturation.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        facturation.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                facturationMouseMoved(evt);
            }
        });
        facturation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                facturationMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                facturationMouseExited(evt);
            }
        });

        jLabel5.setText(" ");
        jLabel5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 204, 51)));
        jLabel5.setPreferredSize(new java.awt.Dimension(3, 3));

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 153));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("- PRODUCTION -");
        jLabel6.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 51, 153)));
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout ProductionLayout = new javax.swing.GroupLayout(Production);
        Production.setLayout(ProductionLayout);
        ProductionLayout.setHorizontalGroup(
            ProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProductionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addGroup(ProductionLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(text_societe, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 232, Short.MAX_VALUE)
                        .addComponent(ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ProductionLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(facturation, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(ProductionLayout.createSequentialGroup()
                .addGap(298, 298, 298)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ProductionLayout.setVerticalGroup(
            ProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ProductionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(ProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(text_societe, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ajouter, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(facturation)
                .addContainerGap())
        );

        Produit_ajouter.setBackground(new java.awt.Color(204, 204, 204));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 0, 3, 0, new java.awt.Color(51, 255, 0)));

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 153));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Libelle_Qte");
        jLabel7.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 153)));

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 153));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("QTE");
        jLabel8.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 153)));

        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 153));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Prix Unitaire");
        jLabel9.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 153)));

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 153));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Designation");
        jLabel10.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 153)));

        textQTE.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        textQTE.setForeground(new java.awt.Color(0, 51, 153));
        textQTE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textQTE.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 153)));
        textQTE.setPreferredSize(new java.awt.Dimension(6, 25));
        textQTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textQTEActionPerformed(evt);
            }
        });
        textQTE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textQTEKeyTyped(evt);
            }
        });

        texprixunitaire.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        texprixunitaire.setForeground(new java.awt.Color(0, 51, 153));
        texprixunitaire.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        texprixunitaire.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 153)));
        texprixunitaire.setPreferredSize(new java.awt.Dimension(6, 25));
        texprixunitaire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texprixunitaireActionPerformed(evt);
            }
        });
        texprixunitaire.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                texprixunitaireKeyTyped(evt);
            }
        });

        texdesignation.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        texdesignation.setForeground(new java.awt.Color(0, 51, 153));
        texdesignation.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        texdesignation.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 153)));
        texdesignation.setPreferredSize(new java.awt.Dimension(6, 25));
        texdesignation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                texdesignationActionPerformed(evt);
            }
        });

        textlibelleQte.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        textlibelleQte.setForeground(new java.awt.Color(0, 51, 153));
        textlibelleQte.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        textlibelleQte.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 153)));
        textlibelleQte.setPreferredSize(new java.awt.Dimension(6, 25));
        textlibelleQte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textlibelleQteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textQTE, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(textlibelleQte, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(353, 353, 353)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(texprixunitaire, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(texdesignation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textQTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texprixunitaire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(texdesignation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textlibelleQte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        jLabel11.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 153));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("- Produit -");
        jLabel11.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 51, 153)));

        ajouter_produit.setBackground(new java.awt.Color(255, 255, 255));
        ajouter_produit.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        ajouter_produit.setForeground(new java.awt.Color(0, 51, 153));
        ajouter_produit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ajouter_produit.setText("Ajouter_le_produit");
        ajouter_produit.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 51, 153)));
        ajouter_produit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ajouter_produit.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                ajouter_produitMouseMoved(evt);
            }
        });
        ajouter_produit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ajouter_produitMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ajouter_produitMouseExited(evt);
            }
        });

        back.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        back.setForeground(new java.awt.Color(0, 51, 153));
        back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back.setText("<-- Retour ");
        back.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 51, 153)));
        back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                backMouseMoved(evt);
            }
        });
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backMouseExited(evt);
            }
        });

        javax.swing.GroupLayout Produit_ajouterLayout = new javax.swing.GroupLayout(Produit_ajouter);
        Produit_ajouter.setLayout(Produit_ajouterLayout);
        Produit_ajouterLayout.setHorizontalGroup(
            Produit_ajouterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Produit_ajouterLayout.createSequentialGroup()
                .addGroup(Produit_ajouterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Produit_ajouterLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(Produit_ajouterLayout.createSequentialGroup()
                        .addGroup(Produit_ajouterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Produit_ajouterLayout.createSequentialGroup()
                                .addGap(264, 264, 264)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Produit_ajouterLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Produit_ajouterLayout.createSequentialGroup()
                                .addGap(270, 270, 270)
                                .addComponent(ajouter_produit, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 283, Short.MAX_VALUE)))
                .addContainerGap())
        );
        Produit_ajouterLayout.setVerticalGroup(
            Produit_ajouterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Produit_ajouterLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ajouter_produit, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        contenaire.setPreferredSize(new java.awt.Dimension(843, 400));

        javax.swing.GroupLayout contenaireLayout = new javax.swing.GroupLayout(contenaire);
        contenaire.setLayout(contenaireLayout);
        contenaireLayout.setHorizontalGroup(
            contenaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        contenaireLayout.setVerticalGroup(
            contenaireLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("- SIMO DISTRIBUTION -");
        jLabel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 51, 153)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(201, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(contenaire, javax.swing.GroupLayout.DEFAULT_SIZE, 838, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(contenaire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void text_societeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_societeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_societeActionPerformed

    private void textQTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textQTEActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textQTEActionPerformed

    private void texprixunitaireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texprixunitaireActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texprixunitaireActionPerformed

    private void texdesignationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_texdesignationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_texdesignationActionPerformed

    private void textlibelleQteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textlibelleQteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textlibelleQteActionPerformed

    private void ajouterMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ajouterMouseExited
        // TODO add your handling code here:
        ajouter.setForeground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_ajouterMouseExited

    private void ajouterMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ajouterMouseMoved
        // TODO add your handling code here:
        ajouter.setForeground(new java.awt.Color(0, 204, 51));
    }//GEN-LAST:event_ajouterMouseMoved

    private void facturationMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_facturationMouseExited
        // TODO add your handling code here:
        facturation.setForeground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_facturationMouseExited

    private void facturationMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_facturationMouseMoved
        // TODO add your handling code here:
        facturation.setForeground(new java.awt.Color(0, 204, 51));
    }//GEN-LAST:event_facturationMouseMoved

    private void ajouter_produitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ajouter_produitMouseExited
        // TODO add your handling code here:
        ajouter_produit.setForeground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_ajouter_produitMouseExited

    private void ajouter_produitMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ajouter_produitMouseMoved
        // TODO add your handling code here:[0,51,153]
        ajouter_produit.setForeground(new java.awt.Color(0, 204, 51));
    }//GEN-LAST:event_ajouter_produitMouseMoved

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        // TODO add your handling code here:
        construire_le_panel(Production);
        ajouter.setForeground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_backMouseClicked

    private void backMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseExited
        // TODO add your handling code here:
         back.setForeground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_backMouseExited

    private void backMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseMoved
        // TODO add your handling code here:
        back.setForeground(new java.awt.Color(0, 204, 51));
    }//GEN-LAST:event_backMouseMoved

    private void textQTEKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textQTEKeyTyped
        // TODO add your handling code here:
        
        //----ceci permet de imposer les  entiers comme unike element a entrer
        int c=evt.getKeyChar();
        int ascii = (int) c;
        if(!(ascii >=48&&ascii <=57))
            evt.consume();

    }//GEN-LAST:event_textQTEKeyTyped

    private void texprixunitaireKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_texprixunitaireKeyTyped
        // TODO add your handling code here:
        //----ceci permet de imposer les  entiers comme unike element a entrer
        int c=evt.getKeyChar();
        int ascii = (int) c;
        if(!(ascii >=48&&ascii <=57))
            evt.consume();
    }//GEN-LAST:event_texprixunitaireKeyTyped

    private void tab_productionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab_productionMousePressed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tab_production.getModel();
        int ind_tabl =  tab_production.getSelectedRow();
        int indice = Integer.parseInt(model.getValueAt(ind_tabl, 0).toString()); 
         int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer ce produit ?","Warning", JOptionPane.YES_NO_OPTION);
        //int opcion = JOptionPane.showConfirmDialog(null, "Voulez-vous effectuer sa suppression ?\n Do you Want to delete it ?","Warning", JOptionPane.YES_NO_OPTION);
        if ( reponse == 0 ){
            liste_de_produits.remove(indice-1);
            recharger_produit(liste_de_produits);
        }
        
        controle_facture();
    }//GEN-LAST:event_tab_productionMousePressed

    private void editerMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editerMouseMoved
        // TODO add your handling code here:
        editer.setForeground(new java.awt.Color(51, 204, 0));
    }//GEN-LAST:event_editerMouseMoved

    private void editerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editerMouseExited
        // TODO add your handling code here:[0,51,153]
        editer.setForeground(new java.awt.Color(0, 51, 153));
    }//GEN-LAST:event_editerMouseExited

    private void ajouterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ajouterMouseClicked
        // TODO add your handling code here:
        construire_le_panel(Produit_ajouter);
        back.setForeground(new java.awt.Color(0, 51, 153));
        ajouter_produit.setForeground(new java.awt.Color(0, 51, 153));
        vider_valeur_produits();
    }//GEN-LAST:event_ajouterMouseClicked

    private void facturationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_facturationMouseClicked
        // TODO add your handling code here:
         if(text_societe.getText().compareTo("")!=1){
            recharger_facture(liste_de_produits);
            NonSociete.setText(text_societe.getText());
             try {
                 numeroDeLaFacture.setText("FACTURE N° "+numeroFacure(cheminfichierexel));
             } catch (IOException ex) {
                 Logger.getLogger(Production.class.getName()).log(Level.SEVERE, null, ex);
             }
            construire_le_panel(Facturation);
            total.setText(String.valueOf(prixtotal(liste_de_produits))+" FCFA"); 
        
        }
        else{
            JOptionPane.showMessageDialog(null,"Entrer le nom de la societe");
        
        }
    }//GEN-LAST:event_facturationMouseClicked

    private void ajouter_produitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ajouter_produitMouseClicked
          // TODO add your handling code here:
         
        if(textQTE.getText().compareTo("")!=0 && 
           texprixunitaire.getText().compareTo("")!=0 &&
           text_societe.getText().compareTo("")!=0 && 
           texdesignation.getText().compareTo("")!=0    ){
          liste_de_produits.add(new Produits(textQTE.getText(),texdesignation.getText(),
                texprixunitaire.getText(),textlibelleQte.getText()));
        //total.setText(String.valueOf(prixtotal(liste_de_produits))+" FCFA"); 
        recharger_produit(liste_de_produits);
        controle_facture();
        construire_le_panel(Production);
        ajouter.setForeground(new java.awt.Color(0, 51, 153));
        }
        else
            JOptionPane.showMessageDialog(null,"Entrer tous les champs");
    }//GEN-LAST:event_ajouter_produitMouseClicked

    private void editerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editerMouseClicked
        // TODO add your handling code here:
        Facturation produirefacture=new Facturation();
        int facturereponse = JOptionPane.showConfirmDialog(null, "Voulez-vous editer votre facture ?","Warning", JOptionPane.YES_NO_OPTION);
        if ( facturereponse == 0 ){
            
            try {
                produirefacture.fichierpdf(liste_de_produits, NonSociete.getText(),
                        String.valueOf(prixtotal(liste_de_produits)), combopresta.getSelectedItem().toString(),
                        numeroDeLaFacture.getText(),new Date());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Production.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                //----enregistrer la facture dans le fichier exel
                //(String chemin,String codefacture,Date datefais,String montanttotat)
                enregistredansexel(cheminfichierexel,numeroDeLaFacture.getText(),new Date(),String.valueOf(prixtotal(liste_de_produits)));
            } catch (IOException ex) {
                Logger.getLogger(Production.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            //----configuration de la fenetre pour une nouvelle facturation
            
            liste_de_produits.clear();
            recharger_produit(liste_de_produits);
            controle_facture();
            construire_le_panel(Production);
            text_societe.setText("");
            ajouter.setForeground(new java.awt.Color(0, 51, 153));
            facturation.setForeground(new java.awt.Color(0, 51, 153));
            JOptionPane.showMessageDialog(null,"Operation terminer");
        }
        
        
    }//GEN-LAST:event_editerMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Production.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Production.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Production.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Production.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Production().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Production.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Facturation;
    private javax.swing.JLabel NonSociete;
    private javax.swing.JPanel Production;
    private javax.swing.JPanel Produit_ajouter;
    private javax.swing.JLabel ajouter;
    private javax.swing.JLabel ajouter_produit;
    private javax.swing.JLabel back;
    private javax.swing.JComboBox<String> combopresta;
    private javax.swing.JPanel contenaire;
    private javax.swing.JLabel editer;
    private javax.swing.JLabel facturation;
    private javax.swing.JLabel facture;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel numeroDeLaFacture;
    private javax.swing.JTable tab_production;
    private javax.swing.JTable tabfacture;
    private javax.swing.JTextField texdesignation;
    private javax.swing.JTextField texprixunitaire;
    private javax.swing.JTextField textQTE;
    private javax.swing.JTextField text_societe;
    private javax.swing.JTextField textlibelleQte;
    private javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
