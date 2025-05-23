/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui.form.gf;

import communication.Odgovor;
import communication.Operacije;
import communication.Zahtev;
import domain.Dvorana;
import domain.Igrac;
import domain.Klub;
import domain.OpstiDomenskiObjekat;
import domain.Pozicija;
import domain.PrviNastup;
import domain.Trener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import table.models.ModelTabeleIgrac;
import ui.controller.Kontroller;
import java.lang.Exception;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import table.models.ModelTabelePrviNastup;
import table.models.ModelTabeleTrener;

/**
 *
 * @author Marko
 */
public class GlavnaForma extends javax.swing.JFrame {

    private List<Igrac> igraci = new ArrayList<>();
    private List<PrviNastup> prviNastupi = new ArrayList<>();
    private List<Trener> treneri = new ArrayList<>();
    private Igrac selektovaniIgrac = null;
    private Klub izabraniKlub = null;
    private Trener selektovaniTrener = null;
    private Dvorana izabranaDvorana = null;
    private int ukupanBrojIgraca = 0;

    /**
     * Creates new form GlavnaForma
     */
    public GlavnaForma() throws Exception {
        initComponents();
        setLocationRelativeTo(null);
        jLabelIgraci.setVisible(false);
        jLabelTreneri.setVisible(false);
        popuniComboBoxeveKlubovima();
        postaviKoloneTabeleIgrac();
        postaviKoloneTabeleTrener();
        popuniComboBoxeveDvoranama();
        ukupanBrojIgraca = Kontroller.getInstance().ucitajListuIgraca().size();
    }

    public Igrac getSelektovaniIgrac() {
        return selektovaniIgrac;
    }

    public void setSelektovaniIgrac(Igrac selektovaniIgrac) {
        this.selektovaniIgrac = selektovaniIgrac;
    }

    public List<PrviNastup> getPrviNastupi() {
        return prviNastupi;
    }

    public void setPrviNastupi(List<PrviNastup> prviNastupi) {
        this.prviNastupi = prviNastupi;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableIgraci = new javax.swing.JTable();
        jComboBoxKluboviIgraci = new javax.swing.JComboBox<>();
        jButtonPronadjiIgrace = new javax.swing.JButton();
        jComboBoxDvorane = new javax.swing.JComboBox<>();
        jButtonPronadjiPrveNastupe = new javax.swing.JButton();
        jButtonPronadjiTrenere = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableTreneri = new javax.swing.JTable();
        jLabelIgraci = new javax.swing.JLabel();
        jLabelTreneri = new javax.swing.JLabel();
        jButtonKreirajIgraca = new javax.swing.JButton();
        jButtonIzmeniIgraca = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButtonKreirajKlub = new javax.swing.JButton();
        jButtonZavrsiRad = new javax.swing.JButton();
        jToggleButtonOsveziPolja = new javax.swing.JToggleButton();
        jTextFieldBrojTitula = new javax.swing.JTextField();
        jButtonUcitajTrenera = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Klubovi:");

        jTableIgraci.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableIgraci);

        jButtonPronadjiIgrace.setText("Pronadji igrace");
        jButtonPronadjiIgrace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPronadjiIgraceActionPerformed(evt);
            }
        });

        jButtonPronadjiPrveNastupe.setText("Pronadji prve nastupe");
        jButtonPronadjiPrveNastupe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPronadjiPrveNastupeActionPerformed(evt);
            }
        });

        jButtonPronadjiTrenere.setText("Pronadji trenere");
        jButtonPronadjiTrenere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPronadjiTrenereActionPerformed(evt);
            }
        });

        jTableTreneri.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane2.setViewportView(jTableTreneri);

        jLabelIgraci.setText("Igraci:");

        jLabelTreneri.setText("Treneri:");

        jButtonKreirajIgraca.setText("Kreiraj Igraca");
        jButtonKreirajIgraca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKreirajIgracaActionPerformed(evt);
            }
        });

        jButtonIzmeniIgraca.setText("Izmeni podatke o igracu");
        jButtonIzmeniIgraca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIzmeniIgracaActionPerformed(evt);
            }
        });

        jLabel4.setText("Unesite minimalan broj titula:");

        jButtonKreirajKlub.setText("Kreiraj klub");
        jButtonKreirajKlub.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonKreirajKlubActionPerformed(evt);
            }
        });

        jButtonZavrsiRad.setText("Zasvrsi rad");
        jButtonZavrsiRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonZavrsiRadActionPerformed(evt);
            }
        });

        jToggleButtonOsveziPolja.setText("Osvezi polja");
        jToggleButtonOsveziPolja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonOsveziPoljaActionPerformed(evt);
            }
        });

        jButtonUcitajTrenera.setText("Ucitaj trenera");
        jButtonUcitajTrenera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUcitajTreneraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldBrojTitula, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jButtonPronadjiTrenere))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabelTreneri))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jButtonUcitajTrenera))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(45, 45, 45)
                                .addComponent(jComboBoxKluboviIgraci, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelIgraci)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonKreirajKlub, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonPronadjiIgrace, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jButtonKreirajIgraca)))
                        .addGap(190, 190, 190)
                        .addComponent(jToggleButtonOsveziPolja))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonIzmeniIgraca)
                            .addComponent(jComboBoxDvorane, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButtonZavrsiRad)
                                    .addComponent(jButtonPronadjiPrveNastupe))))))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxKluboviIgraci, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonPronadjiIgrace))
                    .addComponent(jToggleButtonOsveziPolja))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabelIgraci))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonKreirajIgraca)
                        .addComponent(jButtonKreirajKlub)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButtonIzmeniIgraca)
                        .addGap(31, 31, 31)
                        .addComponent(jComboBoxDvorane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jButtonPronadjiPrveNastupe)))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addComponent(jTextFieldBrojTitula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPronadjiTrenere))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabelTreneri)
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jButtonUcitajTrenera)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonZavrsiRad)
                        .addGap(27, 27, 27))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonKreirajIgracaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKreirajIgracaActionPerformed
        try {
            IgracForma igf = new IgracForma();
            igf.setGf(this);
            igf.getjButtonZapamtiIzmena().setVisible(false);
            igf.getjLabelKlubZvezdica().setVisible(false);
            igf.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(GlavnaForma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonKreirajIgracaActionPerformed

    private void jButtonPronadjiIgraceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPronadjiIgraceActionPerformed
        try {
            izabraniKlub = new Klub();
            if (jComboBoxKluboviIgraci.getSelectedItem() != null) {
                if (jComboBoxKluboviIgraci.getSelectedItem().equals("--Slobodan igrac--")) {
                    izabraniKlub.setNaziv("--Slobodan igrac--");
                } else {
                    izabraniKlub = (Klub) jComboBoxKluboviIgraci.getSelectedItem();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Niste izabrali klub!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                return;
            }
            List<Object> objekti = new ArrayList<>();
            objekti.add(igraci);
            objekti.add(izabraniKlub);
            igraci = Kontroller.getInstance().nadjiIgrace(objekti);
            Collections.sort(igraci);
            popuniTabeluIgrac();
            JOptionPane.showMessageDialog(this, "Sistem je nasao igrace po zadatoj vrednosti!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
            jTableIgraci.setEnabled(true);
        } catch (Exception ex) {
            igraci = null;
            popuniTabeluIgrac();
            JOptionPane.showMessageDialog(this, "Sistem ne moze da nadje igrace po zadatoj vrednosti!", "Greska", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButtonPronadjiIgraceActionPerformed

    private void jButtonIzmeniIgracaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIzmeniIgracaActionPerformed
        try {
            int selektovaniRed = jTableIgraci.getSelectedRow();
            if (selektovaniRed == -1) {
                JOptionPane.showMessageDialog(this, "Niste izabrali igraca!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                return;
            }
            selektovaniIgrac = (Igrac) igraci.get(selektovaniRed);
            selektovaniIgrac = Kontroller.getInstance().ucitajIgraca(selektovaniIgrac);
            IgracForma igf = new IgracForma(selektovaniIgrac);
            igf.setGf(this);
            igf.getjButtonZapamtiKreiranje().setVisible(false);
            if (selektovaniIgrac.getKlub() == null) {
                igf.getjLabelKlubZvezdica().setVisible(false);
            }
            igf.setVisible(true);
            igf.obavestiKorisnika();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da ucita igraca!", "Greska!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonIzmeniIgracaActionPerformed

    private void jButtonKreirajKlubActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonKreirajKlubActionPerformed
        try {
            KlubForma kf = new KlubForma();
            kf.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(GlavnaForma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonKreirajKlubActionPerformed

    private void jButtonZavrsiRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonZavrsiRadActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonZavrsiRadActionPerformed

    private void jButtonPronadjiPrveNastupeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPronadjiPrveNastupeActionPerformed
        try {
            izabranaDvorana = (Dvorana) jComboBoxDvorane.getSelectedItem();
            if (izabranaDvorana == null) {
                JOptionPane.showMessageDialog(this, "Niste izabrali dvoranu!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                return;
            }
            List<PrviNastup> nastupi = new ArrayList<>();
            List<Object> objekti = new ArrayList<>();
            objekti.add(nastupi);
            objekti.add(izabranaDvorana);
            nastupi = Kontroller.getInstance().nadjiPrveNastupe(objekti);
            prviNastupi = nastupi;
            ModelTabelePrviNastup mtpn = new ModelTabelePrviNastup(prviNastupi);
            PrviNastupForma pnf = new PrviNastupForma();
            pnf.setGf(this);
            pnf.postaviPrviNastup();
            pnf.getjTablePrviNastupi().setModel(mtpn);
            JOptionPane.showMessageDialog(this, "Sistem je nasao prve nastupe po zadatoj vrednosti!", "Obavestenje", JOptionPane.INFORMATION_MESSAGE);
            pnf.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da nadje prve nastupe po zadatoj vrednosti!", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonPronadjiPrveNastupeActionPerformed

    private void jToggleButtonOsveziPoljaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonOsveziPoljaActionPerformed
        try {
            jComboBoxKluboviIgraci.setSelectedItem(null);
            jComboBoxDvorane.setSelectedItem(null);
            jTextFieldBrojTitula.setText(null);
            postaviKoloneTabeleIgrac();
            postaviKoloneTabeleTrener();
        } catch (Exception ex) {
            Logger.getLogger(GlavnaForma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jToggleButtonOsveziPoljaActionPerformed

    private void jButtonPronadjiTrenereActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPronadjiTrenereActionPerformed
        try {
            int minimalanBrojTitula;
            try {
                minimalanBrojTitula = Integer.parseInt(jTextFieldBrojTitula.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Minimalan broj titula nije u dobrom formatu ili ga niste uneli!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                return;
            }
            List<Object> objekti = new ArrayList<>();
            objekti.add(treneri);
            objekti.add(minimalanBrojTitula);
            treneri = Kontroller.getInstance().nadjiTrenere(objekti);
            Collections.sort(treneri);
            popuniTabeluTrener();
            jTableTreneri.setEnabled(true);
            JOptionPane.showMessageDialog(this, "Sistem je nasao trenere po zadatoj vrednosti!", "Potvrda", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            treneri = null;
            popuniTabeluTrener();
            JOptionPane.showMessageDialog(this, "Sistem ne moze da nadje trenere po zadatoj vrednosti!", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonPronadjiTrenereActionPerformed

    private void jButtonUcitajTreneraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUcitajTreneraActionPerformed
        try {
            int selektovaniRed = jTableTreneri.getSelectedRow();
            if (selektovaniRed == -1) {
                JOptionPane.showMessageDialog(this, "Niste izabrali trenera!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
                return;
            }
            selektovaniTrener = treneri.get(selektovaniRed);
            selektovaniTrener = Kontroller.getInstance().ucitajTrenera(selektovaniTrener);
            otvoriFormuTrener();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Sistem ne moze da ucita trenera!", "Greska!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonUcitajTreneraActionPerformed

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
            java.util.logging.Logger.getLogger(GlavnaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GlavnaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GlavnaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GlavnaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GlavnaForma().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(GlavnaForma.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonIzmeniIgraca;
    private javax.swing.JButton jButtonKreirajIgraca;
    private javax.swing.JButton jButtonKreirajKlub;
    private javax.swing.JButton jButtonPronadjiIgrace;
    private javax.swing.JButton jButtonPronadjiPrveNastupe;
    private javax.swing.JButton jButtonPronadjiTrenere;
    private javax.swing.JButton jButtonUcitajTrenera;
    private javax.swing.JButton jButtonZavrsiRad;
    private javax.swing.JComboBox<Object> jComboBoxDvorane;
    private javax.swing.JComboBox<Object> jComboBoxKluboviIgraci;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelIgraci;
    private javax.swing.JLabel jLabelTreneri;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableIgraci;
    private javax.swing.JTable jTableTreneri;
    private javax.swing.JTextField jTextFieldBrojTitula;
    private javax.swing.JToggleButton jToggleButtonOsveziPolja;
    // End of variables declaration//GEN-END:variables

    private void popuniComboBoxeveKlubovima() throws Exception {
        List<Klub> klubovi = Kontroller.getInstance().ucitajListuKlubova();
        Collections.sort(klubovi);
        jComboBoxKluboviIgraci.setModel(new DefaultComboBoxModel<>(klubovi.toArray()));
        jComboBoxKluboviIgraci.addItem("--Slobodan igrac--");
        jComboBoxKluboviIgraci.setSelectedItem(null);
    }

    public void postaviUkupanBrojIgraca() {
        try {
            ukupanBrojIgraca = Kontroller.getInstance().ucitajListuIgraca().size();
        } catch (Exception ex) {
            Logger.getLogger(GlavnaForma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void osveziTabelu() {
        try {
            if (jComboBoxKluboviIgraci.getSelectedItem() != null) {
                if (!jComboBoxKluboviIgraci.getSelectedItem().equals("--Slobodan igrac--")) {
                    izabraniKlub = (Klub) jComboBoxKluboviIgraci.getSelectedItem();
                } else {
                    izabraniKlub.setNaziv("--Slobodan igrac--");
                }
            }
            if (igraci.size() == ukupanBrojIgraca) {
                igraci = Kontroller.getInstance().ucitajListuIgraca();
            } else {
                List<Object> objekti = new ArrayList<>();
                objekti.add(igraci);
                objekti.add(izabraniKlub);
                igraci = Kontroller.getInstance().nadjiIgrace(objekti);
            }
            Collections.sort(igraci);
            postaviModelIgrac();

        } catch (Exception ex) {
            Logger.getLogger(GlavnaForma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void osveziTabeluDodatIgrac() {
        try {
            if (jComboBoxKluboviIgraci.getSelectedItem() != null) {
                if (!jComboBoxKluboviIgraci.getSelectedItem().equals("--Slobodan igrac--")) {
                    izabraniKlub = (Klub) jComboBoxKluboviIgraci.getSelectedItem();
                } else {
                    izabraniKlub.setNaziv("--Slobodan igrac--");
                }
            }
            igraci = Kontroller.getInstance().ucitajListuIgraca();
            Collections.sort(igraci);
            postaviModelIgrac();
        } catch (Exception ex) {
            Logger.getLogger(GlavnaForma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void popuniComboBoxeveDvoranama() throws Exception {
        List<Dvorana> dvorane = Kontroller.getInstance().ucitajListuDvorana();
        Collections.sort(dvorane);
        jComboBoxDvorane.setModel(new DefaultComboBoxModel<>(dvorane.toArray()));
        jComboBoxDvorane.setSelectedItem(null);
    }

    private void popuniTabeluIgrac() {
        postaviModelIgrac();
        jLabelIgraci.setVisible(true);
    }

    private void postaviKoloneTabeleIgrac() throws Exception {
        igraci = Kontroller.getInstance().ucitajListuIgraca();
        Collections.sort(igraci);
        postaviModelIgrac();
    }

    private void postaviModelIgrac() {
        ModelTabeleIgrac mti = new ModelTabeleIgrac(igraci);
        jTableIgraci.setModel(mti);
        jTableIgraci.getColumnModel().getColumn(4).setPreferredWidth(10);
        //jTableIgraci.setEnabled(false);
    }

    private void popuniTabeluTrener() {
        postaviModelTrener();
        jLabelTreneri.setVisible(true);
    }

    private void postaviKoloneTabeleTrener() throws Exception {
        int minimalanBrojTitula = 0;
        List<Object> objekti = new ArrayList<>();
        objekti.add(treneri);
        objekti.add(minimalanBrojTitula);
        treneri = Kontroller.getInstance().nadjiTrenere(objekti);
        Collections.sort(treneri);
        postaviModelTrener();
    }

    private void postaviModelTrener() {
        ModelTabeleTrener mtt = new ModelTabeleTrener(treneri);
        jTableTreneri.setModel(mtt);
    }

    private void otvoriFormuTrener() throws Exception {
        TrenerForma tf = new TrenerForma();
        tf.getjTextFieldIme().setText(selektovaniTrener.getIme());
        tf.getjTextFieldPrezime().setText(selektovaniTrener.getPrezime());
        tf.getjTextFieldDatumRodjenja().setText(getStringDatum(selektovaniTrener.getDatumRodjenja()));
        tf.getjTextFieldBrojTitula().setText(selektovaniTrener.getBrojTitula() + "");
        List<Klub> klubovi = Kontroller.getInstance().ucitajListuKlubova();
        tf.getjComboBoxKlubovi().setModel(new DefaultComboBoxModel<>(klubovi.toArray()));
        tf.getjComboBoxKlubovi().setSelectedItem(selektovaniTrener.getKlub());
        tf.getjTextFieldIme().setEditable(false);
        tf.getjTextFieldPrezime().setEditable(false);
        tf.getjTextFieldDatumRodjenja().setEditable(false);
        tf.getjTextFieldBrojTitula().setEditable(false);
        tf.getjComboBoxKlubovi().setEnabled(false);
        tf.setVisible(true);
        tf.obavestiKorisnika();
    }

    private String getStringDatum(Date datumRodjenja) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return dateFormat.format(datumRodjenja);
    }
}
