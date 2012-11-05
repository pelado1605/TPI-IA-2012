/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import dominio.Individuo;
import dominio.Poblacion;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import visual.validaciones.ValidacionDecimalesPositivos;

/**
 *
 * @author Ruben
 */
public class Configuracion extends javax.swing.JDialog {

    /**
     * Creates new form Configuracion
     */
    public Configuracion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        configPorDefecto();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroupSeleccion = new javax.swing.ButtonGroup();
        buttonGroupCruza = new javax.swing.ButtonGroup();
        cantIterLabel = new javax.swing.JLabel();
        cantIterTextField = new javax.swing.JTextField();
        tamPobLabel = new javax.swing.JLabel();
        tamPobTextField = new javax.swing.JTextField();
        panelOperadores = new javax.swing.JPanel();
        panelSeleccion = new javax.swing.JPanel();
        panelChecksSelec = new javax.swing.JPanel();
        elitistaCheck = new javax.swing.JCheckBox();
        ruletaCheck = new javax.swing.JCheckBox();
        rankingCheck = new javax.swing.JCheckBox();
        controlCECheck = new javax.swing.JCheckBox();
        porTorneoCheck = new javax.swing.JCheckBox();
        elitRankCheck = new javax.swing.JCheckBox();
        elitRuletCheck = new javax.swing.JCheckBox();
        elitCECChek = new javax.swing.JCheckBox();
        elitTorneoCheck = new javax.swing.JCheckBox();
        porcentSelecLabel = new javax.swing.JLabel();
        porcentSelecTextField = new javax.swing.JTextField();
        rMinLabel = new javax.swing.JLabel();
        rMinTextField = new javax.swing.JTextField();
        porTorneoTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        panelCruza = new javax.swing.JPanel();
        panelChecksCruza = new javax.swing.JPanel();
        simpleCheck = new javax.swing.JCheckBox();
        multipuntoCheck = new javax.swing.JCheckBox();
        binomialCheck = new javax.swing.JCheckBox();
        porcentCruzaLabel = new javax.swing.JLabel();
        porcentCruzaTextField = new javax.swing.JTextField();
        panelMutacion = new javax.swing.JPanel();
        mutacionCheck = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        mutacionTextField = new javax.swing.JTextField();
        aceptarButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        editarCruzaButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Configuración");
        setIconImage(null);
        setResizable(false);

        cantIterLabel.setText("Cantidad de Iteraciones:");

        cantIterTextField.setEnabled(false);
        cantIterTextField.setInputVerifier(new visual.validaciones.ValidacionEnterosPositivos());
        cantIterTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cantIterTextFieldFocusGained(evt);
            }
        });

        tamPobLabel.setText("Tamaño de la Poblacion:");

        tamPobTextField.setEnabled(false);
        tamPobTextField.setInputVerifier(new visual.validaciones.ValidacionEnterosPositivos());
        tamPobTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tamPobTextFieldFocusGained(evt);
            }
        });

        panelOperadores.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "Operadores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(102, 102, 102)));

        panelSeleccion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "Seleccion", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(102, 102, 102)));

        panelChecksSelec.setLayout(new java.awt.GridLayout(5, 2));

        buttonGroupSeleccion.add(elitistaCheck);
        elitistaCheck.setText("Elitista");
        elitistaCheck.setEnabled(false);
        panelChecksSelec.add(elitistaCheck);

        buttonGroupSeleccion.add(ruletaCheck);
        ruletaCheck.setText("Ruleta");
        ruletaCheck.setEnabled(false);
        panelChecksSelec.add(ruletaCheck);

        buttonGroupSeleccion.add(rankingCheck);
        rankingCheck.setText("Ranking");
        rankingCheck.setEnabled(false);
        panelChecksSelec.add(rankingCheck);

        buttonGroupSeleccion.add(controlCECheck);
        controlCECheck.setText("Control de copias");
        controlCECheck.setEnabled(false);
        panelChecksSelec.add(controlCECheck);

        buttonGroupSeleccion.add(porTorneoCheck);
        porTorneoCheck.setText("Torneo");
        porTorneoCheck.setEnabled(false);
        panelChecksSelec.add(porTorneoCheck);

        buttonGroupSeleccion.add(elitRankCheck);
        elitRankCheck.setText("Elitista + Ranking");
        elitRankCheck.setEnabled(false);
        panelChecksSelec.add(elitRankCheck);

        buttonGroupSeleccion.add(elitRuletCheck);
        elitRuletCheck.setText("Elitista + Ruleta");
        elitRuletCheck.setEnabled(false);
        panelChecksSelec.add(elitRuletCheck);

        buttonGroupSeleccion.add(elitCECChek);
        elitCECChek.setText("Elitista + Control de copias");
        elitCECChek.setEnabled(false);
        panelChecksSelec.add(elitCECChek);

        buttonGroupSeleccion.add(elitTorneoCheck);
        elitTorneoCheck.setText("Elitista + Torneo");
        elitTorneoCheck.setEnabled(false);
        panelChecksSelec.add(elitTorneoCheck);

        porcentSelecLabel.setText("Porcentaje de Selección:");

        porcentSelecTextField.setEnabled(false);
        porcentSelecTextField.setInputVerifier(new ValidacionDecimalesPositivos());
        porcentSelecTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                porcentSelecTextFieldFocusGained(evt);
            }
        });

        rMinLabel.setText("Rmin:");

        rMinTextField.setEnabled(false);
        rMinTextField.setInputVerifier(new ValidacionDecimalesPositivos());
        rMinTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                rMinTextFieldFocusGained(evt);
            }
        });

        porTorneoTextField.setText("1");
        porTorneoTextField.setToolTipText("Cantidad de torneos");
        porTorneoTextField.setEnabled(false);
        porTorneoTextField.setInputVerifier(new visual.validaciones.ValidacionEnterosPositivos());
        porTorneoTextField.setPreferredSize(new java.awt.Dimension(6, 20));
        porTorneoTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                porTorneoTextFieldFocusGained(evt);
            }
        });

        jLabel2.setText("Cantidad de torneos:");

        javax.swing.GroupLayout panelSeleccionLayout = new javax.swing.GroupLayout(panelSeleccion);
        panelSeleccion.setLayout(panelSeleccionLayout);
        panelSeleccionLayout.setHorizontalGroup(
            panelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeleccionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelChecksSelec, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                    .addGroup(panelSeleccionLayout.createSequentialGroup()
                        .addGroup(panelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rMinLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(porcentSelecLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(porcentSelecTextField)
                            .addComponent(rMinTextField)
                            .addComponent(porTorneoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panelSeleccionLayout.setVerticalGroup(
            panelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeleccionLayout.createSequentialGroup()
                .addComponent(panelChecksSelec, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(porTorneoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(rMinTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rMinLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelSeleccionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(porcentSelecTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(porcentSelecLabel))
                .addContainerGap())
        );

        panelCruza.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "Cruza", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(102, 102, 102)));

        panelChecksCruza.setLayout(new java.awt.GridLayout(1, 0));

        buttonGroupCruza.add(simpleCheck);
        simpleCheck.setText("Simple");
        simpleCheck.setEnabled(false);
        panelChecksCruza.add(simpleCheck);

        buttonGroupCruza.add(multipuntoCheck);
        multipuntoCheck.setText("Multipunto");
        multipuntoCheck.setEnabled(false);
        panelChecksCruza.add(multipuntoCheck);

        buttonGroupCruza.add(binomialCheck);
        binomialCheck.setText("Binomial");
        binomialCheck.setEnabled(false);
        panelChecksCruza.add(binomialCheck);

        porcentCruzaLabel.setText("Porcentaje de Cruza:");

        porcentCruzaTextField.setEnabled(false);
        porcentCruzaTextField.setInputVerifier(new ValidacionDecimalesPositivos());
        porcentCruzaTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                porcentCruzaTextFieldFocusGained(evt);
            }
        });

        javax.swing.GroupLayout panelCruzaLayout = new javax.swing.GroupLayout(panelCruza);
        panelCruza.setLayout(panelCruzaLayout);
        panelCruzaLayout.setHorizontalGroup(
            panelCruzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelChecksCruza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelCruzaLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(porcentCruzaLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(porcentCruzaTextField)
                .addContainerGap())
        );
        panelCruzaLayout.setVerticalGroup(
            panelCruzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCruzaLayout.createSequentialGroup()
                .addComponent(panelChecksCruza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCruzaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(porcentCruzaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(porcentCruzaLabel))
                .addContainerGap())
        );

        panelMutacion.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED), "Mutación (no editable)", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(102, 102, 102)));

        mutacionCheck.setText("Mutación Fija");
        mutacionCheck.setEnabled(false);

        jLabel1.setText("Porcentaje de Mutación:");

        mutacionTextField.setEnabled(false);

        javax.swing.GroupLayout panelMutacionLayout = new javax.swing.GroupLayout(panelMutacion);
        panelMutacion.setLayout(panelMutacionLayout);
        panelMutacionLayout.setHorizontalGroup(
            panelMutacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMutacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMutacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMutacionLayout.createSequentialGroup()
                        .addComponent(mutacionCheck)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelMutacionLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(mutacionTextField)))
                .addContainerGap())
        );
        panelMutacionLayout.setVerticalGroup(
            panelMutacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMutacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mutacionCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelMutacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(mutacionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelOperadoresLayout = new javax.swing.GroupLayout(panelOperadores);
        panelOperadores.setLayout(panelOperadoresLayout);
        panelOperadoresLayout.setHorizontalGroup(
            panelOperadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOperadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOperadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCruza, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSeleccion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelMutacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panelOperadoresLayout.setVerticalGroup(
            panelOperadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOperadoresLayout.createSequentialGroup()
                .addComponent(panelSeleccion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCruza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMutacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        aceptarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/icons/icon_confirm.png"))); // NOI18N
        aceptarButton.setText("Aceptar");
        aceptarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarButtonActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/icons/default24.png"))); // NOI18N
        jButton1.setText("Por defecto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        editarCruzaButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/visual/icons/edit24.png"))); // NOI18N
        editarCruzaButton.setText("Editar");
        editarCruzaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarCruzaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tamPobLabel)
                            .addComponent(cantIterLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cantIterTextField)
                            .addComponent(tamPobTextField)))
                    .addComponent(panelOperadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(editarCruzaButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(aceptarButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cantIterLabel)
                    .addComponent(cantIterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(tamPobTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tamPobLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOperadores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(aceptarButton)
                    .addComponent(jButton1)
                    .addComponent(editarCruzaButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editarCruzaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarCruzaButtonActionPerformed
        editarCampos();
        String texto = "";
        if (porcentCruzaTextField.isEnabled()) {
            texto = "Listo";
        } else {
            texto = "Editar";
        }
        editarCruzaButton.setText(texto);
    }//GEN-LAST:event_editarCruzaButtonActionPerformed

    private void aceptarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarButtonActionPerformed
        if (cantIterTextField.isEnabled()) {
            JOptionPane.showMessageDialog(this, "Confirme 'listo' en las opciones de la configuracion", "Confirmacion de cambios", JOptionPane.WARNING_MESSAGE);
        } else {
            dominio.Configuracion.setCANTIDAD_ITERACIONES(Integer.valueOf(cantIterTextField.getText()));
            dominio.Configuracion.setCANTIDAD_POBLACION(Integer.valueOf(tamPobTextField.getText()));
            dominio.Configuracion.setPORC_SELECCION(Float.valueOf(porcentSelecTextField.getText()));
            dominio.Configuracion.setRMIN(Float.valueOf(rMinTextField.getText()));
            dominio.Configuracion.setPORC_CRUZA(Float.valueOf(porcentCruzaTextField.getText()));
            setVisible(false);
        }
    }//GEN-LAST:event_aceptarButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        configPorDefecto();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cantIterTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cantIterTextFieldFocusGained
        cantIterTextField.selectAll();
    }//GEN-LAST:event_cantIterTextFieldFocusGained

    private void tamPobTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tamPobTextFieldFocusGained
        tamPobTextField.selectAll();
    }//GEN-LAST:event_tamPobTextFieldFocusGained

    private void porTorneoTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_porTorneoTextFieldFocusGained
        porTorneoTextField.selectAll();
    }//GEN-LAST:event_porTorneoTextFieldFocusGained

    private void rMinTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rMinTextFieldFocusGained
        rMinTextField.selectAll();
    }//GEN-LAST:event_rMinTextFieldFocusGained

    private void porcentSelecTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_porcentSelecTextFieldFocusGained
        porcentSelecTextField.selectAll();
    }//GEN-LAST:event_porcentSelecTextFieldFocusGained

    private void porcentCruzaTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_porcentCruzaTextFieldFocusGained
        porcentCruzaTextField.selectAll();
    }//GEN-LAST:event_porcentCruzaTextFieldFocusGained

    public JCheckBox getBinomialCheck() {
        return binomialCheck;
    }

    public JTextField getCantIterTextField() {
        return cantIterTextField;
    }

    public JCheckBox getControlCECheck() {
        return controlCECheck;
    }

    public JCheckBox getElitistaCheck() {
        return elitistaCheck;
    }

    public JCheckBox getMultipuntoCheck() {
        return multipuntoCheck;
    }

    public JCheckBox getPorTorneoCheck() {
        return porTorneoCheck;
    }

    public JTextField getPorTorneoTextField() {
        return porTorneoTextField;
    }

    public JTextField getPorcentCruzaTextField() {
        return porcentCruzaTextField;
    }

    public JTextField getPorcentSelecTextField() {
        return porcentSelecTextField;
    }

    public JTextField getTamPobTextField() {
        return tamPobTextField;
    }

    public JButton getAceptarButton() {
        return aceptarButton;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aceptarButton;
    private javax.swing.JCheckBox binomialCheck;
    private javax.swing.ButtonGroup buttonGroupCruza;
    private javax.swing.ButtonGroup buttonGroupSeleccion;
    private javax.swing.JLabel cantIterLabel;
    private javax.swing.JTextField cantIterTextField;
    private javax.swing.JCheckBox controlCECheck;
    private javax.swing.JButton editarCruzaButton;
    private javax.swing.JCheckBox elitCECChek;
    private javax.swing.JCheckBox elitRankCheck;
    private javax.swing.JCheckBox elitRuletCheck;
    private javax.swing.JCheckBox elitTorneoCheck;
    private javax.swing.JCheckBox elitistaCheck;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JCheckBox multipuntoCheck;
    private javax.swing.JCheckBox mutacionCheck;
    private javax.swing.JTextField mutacionTextField;
    private javax.swing.JPanel panelChecksCruza;
    private javax.swing.JPanel panelChecksSelec;
    private javax.swing.JPanel panelCruza;
    private javax.swing.JPanel panelMutacion;
    private javax.swing.JPanel panelOperadores;
    private javax.swing.JPanel panelSeleccion;
    private javax.swing.JCheckBox porTorneoCheck;
    private javax.swing.JTextField porTorneoTextField;
    private javax.swing.JLabel porcentCruzaLabel;
    private javax.swing.JTextField porcentCruzaTextField;
    private javax.swing.JLabel porcentSelecLabel;
    private javax.swing.JTextField porcentSelecTextField;
    private javax.swing.JLabel rMinLabel;
    private javax.swing.JTextField rMinTextField;
    private javax.swing.JCheckBox rankingCheck;
    private javax.swing.JCheckBox ruletaCheck;
    private javax.swing.JCheckBox simpleCheck;
    private javax.swing.JLabel tamPobLabel;
    private javax.swing.JTextField tamPobTextField;
    // End of variables declaration//GEN-END:variables

    private void configPorDefecto() {
        cantIterTextField.setText(String.valueOf(dominio.Configuracion.CANTIDAD_ITERACIONES_DEFAULT));
        tamPobTextField.setText(String.valueOf(dominio.Configuracion.CANTIDAD_POBLACION_DEFAULT));
        //Seleccion
        porcentSelecTextField.setText(String.valueOf(dominio.Configuracion.PORC_SELECCION_DEFAULT));
        elitRuletCheck.setSelected(true);
        rMinTextField.setText(String.valueOf(dominio.Configuracion.RMIN_DEFAULT));               
        porTorneoTextField.setText(String.valueOf(5));
        //Cruza
        porcentCruzaTextField.setText(String.valueOf(dominio.Configuracion.PORC_CRUZA_DEFAULT));
        multipuntoCheck.setSelected(true);
        //Mutacion
        float porcMutacion = 1 - dominio.Configuracion.PORC_SELECCION_DEFAULT
                - dominio.Configuracion.PORC_CRUZA_DEFAULT;
        DecimalFormat formatter = new DecimalFormat("####0.00");
        mutacionTextField.setText(String.valueOf(formatter.format(porcMutacion)));
        mutacionCheck.setSelected(true);
    }

    private void editarCampos() {
        boolean editar = !cantIterTextField.isEnabled();
        cantIterTextField.setEnabled(editar);
        tamPobTextField.setEnabled(editar);
        porcentSelecTextField.setEnabled(editar);
        porcentCruzaTextField.setEnabled(editar);
        rMinTextField.setEnabled(editar);
        ruletaCheck.setEnabled(editar);
        elitistaCheck.setEnabled(editar);
        rankingCheck.setEnabled(editar);
        porTorneoCheck.setEnabled(editar);
        porTorneoTextField.setEnabled(editar);
        elitCECChek.setEnabled(editar);
        elitRankCheck.setEnabled(editar);
        elitRuletCheck.setEnabled(editar);
        elitTorneoCheck.setEnabled(editar);
        controlCECheck.setEnabled(editar);
        multipuntoCheck.setEnabled(editar);
        simpleCheck.setEnabled(editar);
        binomialCheck.setEnabled(editar);


    }

    public int getTipoSelec() {
        int seleccion = 0;

        if (elitistaCheck.isSelected()) {
            seleccion = Poblacion.ELITISTA;
        } else {
            if (ruletaCheck.isSelected()) {
                seleccion = Poblacion.RULETA;
            } else {
                if (rankingCheck.isSelected()) {
                    seleccion = Poblacion.RANKING;
                } else {
                    if (controlCECheck.isSelected()) {
                        seleccion = Poblacion.CCE;
                    } else {
                        if (porTorneoCheck.isSelected()) {
                            seleccion = Poblacion.TORNEO;
                        } else {
                            if (elitCECChek.isSelected()) {
                                seleccion = Poblacion.CCE + Poblacion.ELITISTA;
                            } else {
                                if (elitRuletCheck.isSelected()) {
                                    seleccion = Poblacion.RULETA + Poblacion.ELITISTA;
                                } else {
                                    if (elitRankCheck.isSelected()) {
                                        seleccion = Poblacion.RANKING + Poblacion.ELITISTA;
                                    } else {
                                        if (porTorneoCheck.isSelected()) {
                                            seleccion = Poblacion.TORNEO + Poblacion.ELITISTA;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return seleccion;
    }

    public int getTipoCruza() {
        int tCruza = 0;
        if (multipuntoCheck.isSelected()) {
            tCruza = Individuo.CRUZA_MULTIPUNTO;
        } else {
            if (binomialCheck.isSelected()) {
                tCruza = Individuo.CRUZA_BINOMIAL;
            }
        }
        return tCruza;
    }
}
