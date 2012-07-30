/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tnt.scp.scripteditor.panels;

import com.google.common.collect.Lists;
import org.tnt.scp.common.generated.Script;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

/**
 * @author sith
 */
public class AddScenePanel extends javax.swing.JPanel {

    private final List<Script> scripts;
    private final DefaultComboBoxModel comboBoxModel;

    /**
     * Creates new form AddScenePanel
     *
     * @param scripts
     */
    public AddScenePanel(Collection<? extends Script> scripts) {

        this.scripts = Lists.newArrayList(scripts);

        comboBoxModel = new DefaultComboBoxModel();
        for (Script script : scripts) {
            comboBoxModel.addElement(script.getName());

        }
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sceneNameTextField = new javax.swing.JTextField();
        scriptsCombox = new javax.swing.JComboBox();
        initScriptsCombobox();
        scriptsLabel = new javax.swing.JLabel();
        sceneNameLabel = new javax.swing.JLabel();
        errorLable = new javax.swing.JLabel();

        sceneNameTextField.setText(org.openide.util.NbBundle.getMessage(AddScenePanel.class, "AddScenePanel.sceneNameTextField.text")); // NOI18N

        scriptsCombox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scriptsComboxActionPerformed(evt);
            }
        });

        scriptsLabel.setText(org.openide.util.NbBundle.getMessage(AddScenePanel.class, "AddScenePanel.scriptsLabel.text")); // NOI18N

        sceneNameLabel.setText(org.openide.util.NbBundle.getMessage(AddScenePanel.class, "AddScenePanel.sceneNameLabel.text")); // NOI18N

        errorLable.setForeground(new java.awt.Color(232, 27, 27));
        errorLable.setText(org.openide.util.NbBundle.getMessage(AddScenePanel.class, "AddScenePanel.errorLable.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(scriptsLabel)
                                                        .addComponent(sceneNameLabel))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(sceneNameTextField)
                                                        .addComponent(scriptsCombox, 0, 281, Short.MAX_VALUE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(errorLable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(scriptsCombox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scriptsLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(sceneNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sceneNameLabel))
                                .addGap(18, 18, 18)
                                .addComponent(errorLable)
                                .addContainerGap(42, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void initScriptsCombobox() {

        scriptsCombox.setModel(comboBoxModel);

    }

    private void scriptsComboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scriptsComboxActionPerformed

    }//GEN-LAST:event_scriptsComboxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errorLable;
    private javax.swing.JLabel sceneNameLabel;
    private javax.swing.JTextField sceneNameTextField;
    private javax.swing.JComboBox scriptsCombox;
    private javax.swing.JLabel scriptsLabel;
    // End of variables declaration//GEN-END:variables


    public String getSceneName() {
        return sceneNameTextField.getText();
    }

    public Script getSelectedScript() {
        return scripts.get(scriptsCombox.getSelectedIndex());
    }

    public void showError() {
        errorLable.setText("Empty scene name");
    }

}
