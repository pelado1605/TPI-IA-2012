/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import javax.swing.JLabel;

/**
 *
 * @author Ruben
 */
public class Recetas extends javax.swing.JDialog {

    /**
     * Creates new form Recetas
     */
    public Recetas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(getParent().getParent());
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 9; j++) {
                ((JLabel) recetaPanel.getComponent(i*9+j)).setText(i+","+j);
            }
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

        recetaPanel = new javax.swing.JPanel();
        vacioLabel = new javax.swing.JLabel();
        m1Label = new javax.swing.JLabel();
        m2Label = new javax.swing.JLabel();
        m3Label = new javax.swing.JLabel();
        m4Label = new javax.swing.JLabel();
        m5Label = new javax.swing.JLabel();
        m6Label = new javax.swing.JLabel();
        m7Label = new javax.swing.JLabel();
        m8Label = new javax.swing.JLabel();
        p1Label = new javax.swing.JLabel();
        p1m1Label = new javax.swing.JLabel();
        p1m2Label = new javax.swing.JLabel();
        p1m3Label = new javax.swing.JLabel();
        p1m4Label = new javax.swing.JLabel();
        p1m5Label = new javax.swing.JLabel();
        p1m6Label = new javax.swing.JLabel();
        p1m7Label = new javax.swing.JLabel();
        p1m8Label = new javax.swing.JLabel();
        p2Label = new javax.swing.JLabel();
        p2m1Label = new javax.swing.JLabel();
        p2m2Label = new javax.swing.JLabel();
        p2m3Label = new javax.swing.JLabel();
        p2m4Label = new javax.swing.JLabel();
        p2m5Label = new javax.swing.JLabel();
        p2m6Label = new javax.swing.JLabel();
        p2m7Label = new javax.swing.JLabel();
        p2m8Label = new javax.swing.JLabel();
        p3Label = new javax.swing.JLabel();
        p3m1Label = new javax.swing.JLabel();
        p3m2Label = new javax.swing.JLabel();
        p3m3Label = new javax.swing.JLabel();
        p3m4Label = new javax.swing.JLabel();
        p3m5Label = new javax.swing.JLabel();
        p3m6Label = new javax.swing.JLabel();
        p3m7Label = new javax.swing.JLabel();
        p3m8Label = new javax.swing.JLabel();
        p4Label = new javax.swing.JLabel();
        p4m1Label = new javax.swing.JLabel();
        p4m2Label = new javax.swing.JLabel();
        p4m3Label = new javax.swing.JLabel();
        p4m4Label = new javax.swing.JLabel();
        p4m5Label = new javax.swing.JLabel();
        p4m6Label = new javax.swing.JLabel();
        p4m7Label = new javax.swing.JLabel();
        p4m8Label = new javax.swing.JLabel();
        utilizadosPanel = new javax.swing.JPanel();
        m1LabelU = new javax.swing.JLabel();
        m2LabelU = new javax.swing.JLabel();
        m3LabelU = new javax.swing.JLabel();
        m4LabelU = new javax.swing.JLabel();
        m5LabelU = new javax.swing.JLabel();
        m6LabelU = new javax.swing.JLabel();
        m7LabelU = new javax.swing.JLabel();
        m8LabelU = new javax.swing.JLabel();
        valorm1LabelU = new javax.swing.JLabel();
        valorm2LabelU = new javax.swing.JLabel();
        valorm3LabelU = new javax.swing.JLabel();
        valorm4LabelU = new javax.swing.JLabel();
        valorm5LabelU = new javax.swing.JLabel();
        valorm6LabelU = new javax.swing.JLabel();
        valorm7LabelU = new javax.swing.JLabel();
        valorm9LabelU = new javax.swing.JLabel();
        desperdiciosPanel = new javax.swing.JPanel();
        m1LabelD = new javax.swing.JLabel();
        m2LabelD = new javax.swing.JLabel();
        m3LabelD = new javax.swing.JLabel();
        m4LabelD = new javax.swing.JLabel();
        m5LabelD = new javax.swing.JLabel();
        m6LabelD = new javax.swing.JLabel();
        m7LabelD = new javax.swing.JLabel();
        m8LabelD = new javax.swing.JLabel();
        valorm1LabelDM = new javax.swing.JLabel();
        valorm2LabelDM = new javax.swing.JLabel();
        valorm3LabelDM = new javax.swing.JLabel();
        valorm4LabelDM = new javax.swing.JLabel();
        valorm5LabelDM = new javax.swing.JLabel();
        valorm6LabelDM = new javax.swing.JLabel();
        valorm7LabelDM = new javax.swing.JLabel();
        valorm8LabelDM = new javax.swing.JLabel();
        aceptarButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        recetaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "Receta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(102, 102, 102)));
        recetaPanel.setMaximumSize(new java.awt.Dimension(534, 107));
        recetaPanel.setPreferredSize(new java.awt.Dimension(524, 200));
        recetaPanel.setLayout(new java.awt.GridLayout(5, 9));

        vacioLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        vacioLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        vacioLabel.setText("Pro\\Mat");
        recetaPanel.add(vacioLabel);

        m1Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        m1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m1Label.setText("m1");
        recetaPanel.add(m1Label);

        m2Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        m2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m2Label.setText("m2");
        recetaPanel.add(m2Label);

        m3Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        m3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m3Label.setText("m3");
        recetaPanel.add(m3Label);

        m4Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        m4Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m4Label.setText("m4");
        recetaPanel.add(m4Label);

        m5Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        m5Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m5Label.setText("m5");
        recetaPanel.add(m5Label);

        m6Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        m6Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m6Label.setText("m6");
        recetaPanel.add(m6Label);

        m7Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        m7Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m7Label.setText("m7");
        recetaPanel.add(m7Label);

        m8Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        m8Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m8Label.setText("m8");
        recetaPanel.add(m8Label);

        p1Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        p1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1Label.setText("p1");
        recetaPanel.add(p1Label);

        p1m1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1m1Label.setText("jLabel16");
        recetaPanel.add(p1m1Label);

        p1m2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1m2Label.setText("jLabel15");
        recetaPanel.add(p1m2Label);

        p1m3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1m3Label.setText("jLabel12");
        recetaPanel.add(p1m3Label);

        p1m4Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1m4Label.setText("jLabel22");
        recetaPanel.add(p1m4Label);

        p1m5Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1m5Label.setText("jLabel21");
        recetaPanel.add(p1m5Label);

        p1m6Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1m6Label.setText("jLabel8");
        recetaPanel.add(p1m6Label);

        p1m7Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1m7Label.setText("jLabel9");
        recetaPanel.add(p1m7Label);

        p1m8Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p1m8Label.setText("jLabel7");
        recetaPanel.add(p1m8Label);

        p2Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        p2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p2Label.setText("p2");
        recetaPanel.add(p2Label);

        p2m1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p2m1Label.setText("jLabel19");
        recetaPanel.add(p2m1Label);

        p2m2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p2m2Label.setText("jLabel5");
        recetaPanel.add(p2m2Label);

        p2m3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p2m3Label.setText("jLabel24");
        recetaPanel.add(p2m3Label);

        p2m4Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p2m4Label.setText("jLabel25");
        recetaPanel.add(p2m4Label);

        p2m5Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p2m5Label.setText("jLabel26");
        recetaPanel.add(p2m5Label);

        p2m6Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p2m6Label.setText("jLabel27");
        recetaPanel.add(p2m6Label);

        p2m7Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p2m7Label.setText("jLabel28");
        recetaPanel.add(p2m7Label);

        p2m8Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p2m8Label.setText("jLabel29");
        recetaPanel.add(p2m8Label);

        p3Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        p3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p3Label.setText("p3");
        recetaPanel.add(p3Label);

        p3m1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p3m1Label.setText("jLabel31");
        recetaPanel.add(p3m1Label);

        p3m2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p3m2Label.setText("jLabel32");
        recetaPanel.add(p3m2Label);

        p3m3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p3m3Label.setText("jLabel33");
        recetaPanel.add(p3m3Label);

        p3m4Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p3m4Label.setText("jLabel34");
        recetaPanel.add(p3m4Label);

        p3m5Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p3m5Label.setText("jLabel35");
        recetaPanel.add(p3m5Label);

        p3m6Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p3m6Label.setText("jLabel36");
        recetaPanel.add(p3m6Label);

        p3m7Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p3m7Label.setText("jLabel37");
        recetaPanel.add(p3m7Label);

        p3m8Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p3m8Label.setText("jLabel38");
        recetaPanel.add(p3m8Label);

        p4Label.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        p4Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p4Label.setText("p4");
        recetaPanel.add(p4Label);

        p4m1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p4m1Label.setText("jLabel40");
        recetaPanel.add(p4m1Label);

        p4m2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p4m2Label.setText("jLabel41");
        recetaPanel.add(p4m2Label);

        p4m3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p4m3Label.setText("jLabel42");
        recetaPanel.add(p4m3Label);

        p4m4Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p4m4Label.setText("jLabel43");
        recetaPanel.add(p4m4Label);

        p4m5Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p4m5Label.setText("jLabel44");
        recetaPanel.add(p4m5Label);

        p4m6Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p4m6Label.setText("jLabel45");
        recetaPanel.add(p4m6Label);

        p4m7Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p4m7Label.setText("jLabel46");
        recetaPanel.add(p4m7Label);

        p4m8Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        p4m8Label.setText("jLabel47");
        recetaPanel.add(p4m8Label);

        utilizadosPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "Utilizacion de Materiales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(102, 102, 102)));
        utilizadosPanel.setPreferredSize(new java.awt.Dimension(706, 100));
        utilizadosPanel.setLayout(new java.awt.GridLayout(2, 8));

        m1LabelU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m1LabelU.setText("m1");
        utilizadosPanel.add(m1LabelU);

        m2LabelU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m2LabelU.setText("m2");
        utilizadosPanel.add(m2LabelU);

        m3LabelU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m3LabelU.setText("m3");
        utilizadosPanel.add(m3LabelU);

        m4LabelU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m4LabelU.setText("m4");
        utilizadosPanel.add(m4LabelU);

        m5LabelU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m5LabelU.setText("m5");
        utilizadosPanel.add(m5LabelU);

        m6LabelU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m6LabelU.setText("m6");
        utilizadosPanel.add(m6LabelU);

        m7LabelU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m7LabelU.setText("m7");
        utilizadosPanel.add(m7LabelU);

        m8LabelU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m8LabelU.setText("m8");
        utilizadosPanel.add(m8LabelU);

        valorm1LabelU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        utilizadosPanel.add(valorm1LabelU);

        valorm2LabelU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        utilizadosPanel.add(valorm2LabelU);

        valorm3LabelU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        utilizadosPanel.add(valorm3LabelU);

        valorm4LabelU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        utilizadosPanel.add(valorm4LabelU);

        valorm5LabelU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        utilizadosPanel.add(valorm5LabelU);

        valorm6LabelU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        utilizadosPanel.add(valorm6LabelU);

        valorm7LabelU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        utilizadosPanel.add(valorm7LabelU);

        valorm9LabelU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        utilizadosPanel.add(valorm9LabelU);

        desperdiciosPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "Desperdicio de Materiales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(102, 102, 102)));
        desperdiciosPanel.setPreferredSize(new java.awt.Dimension(706, 100));
        desperdiciosPanel.setLayout(new java.awt.GridLayout(2, 8));

        m1LabelD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m1LabelD.setText("m1");
        desperdiciosPanel.add(m1LabelD);

        m2LabelD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m2LabelD.setText("m2");
        desperdiciosPanel.add(m2LabelD);

        m3LabelD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m3LabelD.setText("m3");
        desperdiciosPanel.add(m3LabelD);

        m4LabelD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m4LabelD.setText("m4");
        desperdiciosPanel.add(m4LabelD);

        m5LabelD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m5LabelD.setText("m5");
        desperdiciosPanel.add(m5LabelD);

        m6LabelD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m6LabelD.setText("m6");
        desperdiciosPanel.add(m6LabelD);

        m7LabelD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m7LabelD.setText("m7");
        desperdiciosPanel.add(m7LabelD);

        m8LabelD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        m8LabelD.setText("m8");
        desperdiciosPanel.add(m8LabelD);

        valorm1LabelDM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        desperdiciosPanel.add(valorm1LabelDM);

        valorm2LabelDM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        desperdiciosPanel.add(valorm2LabelDM);

        valorm3LabelDM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        desperdiciosPanel.add(valorm3LabelDM);

        valorm4LabelDM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        desperdiciosPanel.add(valorm4LabelDM);

        valorm5LabelDM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        desperdiciosPanel.add(valorm5LabelDM);

        valorm6LabelDM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        desperdiciosPanel.add(valorm6LabelDM);

        valorm7LabelDM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        desperdiciosPanel.add(valorm7LabelDM);

        valorm8LabelDM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        desperdiciosPanel.add(valorm8LabelDM);

        aceptarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/icons/icon_confirm.png"))); // NOI18N
        aceptarButton.setText("Aceptar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(recetaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(utilizadosPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
            .addComponent(desperdiciosPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(aceptarButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(recetaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(utilizadosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desperdiciosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aceptarButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Recetas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Recetas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Recetas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Recetas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Recetas dialog = new Recetas(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JPanel desperdiciosPanel;
    private javax.swing.JLabel m1Label;
    private javax.swing.JLabel m1LabelD;
    private javax.swing.JLabel m1LabelU;
    private javax.swing.JLabel m2Label;
    private javax.swing.JLabel m2LabelD;
    private javax.swing.JLabel m2LabelU;
    private javax.swing.JLabel m3Label;
    private javax.swing.JLabel m3LabelD;
    private javax.swing.JLabel m3LabelU;
    private javax.swing.JLabel m4Label;
    private javax.swing.JLabel m4LabelD;
    private javax.swing.JLabel m4LabelU;
    private javax.swing.JLabel m5Label;
    private javax.swing.JLabel m5LabelD;
    private javax.swing.JLabel m5LabelU;
    private javax.swing.JLabel m6Label;
    private javax.swing.JLabel m6LabelD;
    private javax.swing.JLabel m6LabelU;
    private javax.swing.JLabel m7Label;
    private javax.swing.JLabel m7LabelD;
    private javax.swing.JLabel m7LabelU;
    private javax.swing.JLabel m8Label;
    private javax.swing.JLabel m8LabelD;
    private javax.swing.JLabel m8LabelU;
    private javax.swing.JLabel p1Label;
    private javax.swing.JLabel p1m1Label;
    private javax.swing.JLabel p1m2Label;
    private javax.swing.JLabel p1m3Label;
    private javax.swing.JLabel p1m4Label;
    private javax.swing.JLabel p1m5Label;
    private javax.swing.JLabel p1m6Label;
    private javax.swing.JLabel p1m7Label;
    private javax.swing.JLabel p1m8Label;
    private javax.swing.JLabel p2Label;
    private javax.swing.JLabel p2m1Label;
    private javax.swing.JLabel p2m2Label;
    private javax.swing.JLabel p2m3Label;
    private javax.swing.JLabel p2m4Label;
    private javax.swing.JLabel p2m5Label;
    private javax.swing.JLabel p2m6Label;
    private javax.swing.JLabel p2m7Label;
    private javax.swing.JLabel p2m8Label;
    private javax.swing.JLabel p3Label;
    private javax.swing.JLabel p3m1Label;
    private javax.swing.JLabel p3m2Label;
    private javax.swing.JLabel p3m3Label;
    private javax.swing.JLabel p3m4Label;
    private javax.swing.JLabel p3m5Label;
    private javax.swing.JLabel p3m6Label;
    private javax.swing.JLabel p3m7Label;
    private javax.swing.JLabel p3m8Label;
    private javax.swing.JLabel p4Label;
    private javax.swing.JLabel p4m1Label;
    private javax.swing.JLabel p4m2Label;
    private javax.swing.JLabel p4m3Label;
    private javax.swing.JLabel p4m4Label;
    private javax.swing.JLabel p4m5Label;
    private javax.swing.JLabel p4m6Label;
    private javax.swing.JLabel p4m7Label;
    private javax.swing.JLabel p4m8Label;
    private javax.swing.JPanel recetaPanel;
    private javax.swing.JPanel utilizadosPanel;
    private javax.swing.JLabel vacioLabel;
    private javax.swing.JLabel valorm1LabelDM;
    private javax.swing.JLabel valorm1LabelU;
    private javax.swing.JLabel valorm2LabelDM;
    private javax.swing.JLabel valorm2LabelU;
    private javax.swing.JLabel valorm3LabelDM;
    private javax.swing.JLabel valorm3LabelU;
    private javax.swing.JLabel valorm4LabelDM;
    private javax.swing.JLabel valorm4LabelU;
    private javax.swing.JLabel valorm5LabelDM;
    private javax.swing.JLabel valorm5LabelU;
    private javax.swing.JLabel valorm6LabelDM;
    private javax.swing.JLabel valorm6LabelU;
    private javax.swing.JLabel valorm7LabelDM;
    private javax.swing.JLabel valorm7LabelU;
    private javax.swing.JLabel valorm8LabelDM;
    private javax.swing.JLabel valorm9LabelU;
    // End of variables declaration//GEN-END:variables
}
