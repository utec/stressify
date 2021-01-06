/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package edu.utec.tools.fiveminutestressor.ui;

/**
 *
 * @author jarvis
 */
public class JPanelHttpSimpleStress extends javax.swing.JPanel {

  /**
   * Creates new form JPanelHttpTest
   */
  public JPanelHttpSimpleStress() {
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBoxMethod = new javax.swing.JComboBox();
        jTextFieldUrl = new javax.swing.JTextField();
        jButtonStress = new javax.swing.JButton();
        jTabbedPaneHttpOptions = new javax.swing.JTabbedPane();
        jPanelHeaders = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHeaders = new javax.swing.JTable();
        jPanelBody = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaPostBody = new javax.swing.JTextArea();
        jPanelAssertResponseScript = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaAssertResponseScript = new javax.swing.JTextArea();
        jPanelReport = new javax.swing.JPanel();
        jLabelReportFolderLocation = new javax.swing.JLabel();
        jTextFieldReportFolderLocation = new javax.swing.JTextField();
        jTextFieldReportName = new javax.swing.JTextField();
        jLabelReportName = new javax.swing.JLabel();
        jButtonOpenReportFolderSelector = new javax.swing.JButton();
        jLabelDataCsvFilePath = new javax.swing.JLabel();
        jTextFieldDataCsvFilePath = new javax.swing.JTextField();
        jButtonOpenDataFileSelector = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaStressLog = new javax.swing.JTextArea();
        jLabelVirtualUsers = new javax.swing.JLabel();
        jTextFieldThreadsNumber = new javax.swing.JTextField();
        jLabelStressMode = new javax.swing.JLabel();
        jComboBoxStressMode = new javax.swing.JComboBox<>();

        jComboBoxMethod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "GET", "POST", "DELETE", "PUT" }));
        jComboBoxMethod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMethodActionPerformed(evt);
            }
        });

        jButtonStress.setText("Start Stress");

        jTableHeaders.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Key", "Value", "Description"
            }
        ));
        jScrollPane1.setViewportView(jTableHeaders);

        javax.swing.GroupLayout jPanelHeadersLayout = new javax.swing.GroupLayout(jPanelHeaders);
        jPanelHeaders.setLayout(jPanelHeadersLayout);
        jPanelHeadersLayout.setHorizontalGroup(
            jPanelHeadersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeadersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );
        jPanelHeadersLayout.setVerticalGroup(
            jPanelHeadersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelHeadersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );

        jTabbedPaneHttpOptions.addTab("Headers", jPanelHeaders);

        jTextAreaPostBody.setColumns(20);
        jTextAreaPostBody.setRows(5);
        jScrollPane2.setViewportView(jTextAreaPostBody);

        javax.swing.GroupLayout jPanelBodyLayout = new javax.swing.GroupLayout(jPanelBody);
        jPanelBody.setLayout(jPanelBodyLayout);
        jPanelBodyLayout.setHorizontalGroup(
            jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelBodyLayout.setVerticalGroup(
            jPanelBodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBodyLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneHttpOptions.addTab("Body", jPanelBody);

        jTextAreaAssertResponseScript.setColumns(20);
        jTextAreaAssertResponseScript.setRows(5);
        jScrollPane3.setViewportView(jTextAreaAssertResponseScript);

        javax.swing.GroupLayout jPanelAssertResponseScriptLayout = new javax.swing.GroupLayout(jPanelAssertResponseScript);
        jPanelAssertResponseScript.setLayout(jPanelAssertResponseScriptLayout);
        jPanelAssertResponseScriptLayout.setHorizontalGroup(
            jPanelAssertResponseScriptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAssertResponseScriptLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelAssertResponseScriptLayout.setVerticalGroup(
            jPanelAssertResponseScriptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAssertResponseScriptLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPaneHttpOptions.addTab("Assert Respose Script", jPanelAssertResponseScript);

        jLabelReportFolderLocation.setText("Select or enter the report folder location ");

        jLabelReportName.setText("Enter the report name");

        jButtonOpenReportFolderSelector.setText("...");

        jLabelDataCsvFilePath.setText("Select or enter the csv file with variables");

        jButtonOpenDataFileSelector.setText("...");

        javax.swing.GroupLayout jPanelReportLayout = new javax.swing.GroupLayout(jPanelReport);
        jPanelReport.setLayout(jPanelReportLayout);
        jPanelReportLayout.setHorizontalGroup(
            jPanelReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReportLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelReportLayout.createSequentialGroup()
                        .addGroup(jPanelReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldReportFolderLocation)
                            .addComponent(jTextFieldReportName)
                            .addGroup(jPanelReportLayout.createSequentialGroup()
                                .addGroup(jPanelReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelReportName, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelReportFolderLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 326, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonOpenReportFolderSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelReportLayout.createSequentialGroup()
                        .addGroup(jPanelReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldDataCsvFilePath)
                            .addGroup(jPanelReportLayout.createSequentialGroup()
                                .addComponent(jLabelDataCsvFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonOpenDataFileSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanelReportLayout.setVerticalGroup(
            jPanelReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelDataCsvFilePath)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDataCsvFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonOpenDataFileSelector))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelReportFolderLocation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldReportFolderLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonOpenReportFolderSelector))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelReportName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldReportName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jTabbedPaneHttpOptions.addTab("Settings", jPanelReport);

        jTextAreaStressLog.setColumns(20);
        jTextAreaStressLog.setRows(5);
        jScrollPane4.setViewportView(jTextAreaStressLog);

        jLabelVirtualUsers.setText("Enter the number of virtual users or threads");

        jTextFieldThreadsNumber.setText("1");
        jTextFieldThreadsNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldThreadsNumberActionPerformed(evt);
            }
        });

        jLabelStressMode.setText("Select stress mode");

        jComboBoxStressMode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "sequential", "parallel" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addComponent(jTabbedPaneHttpOptions, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBoxMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldUrl))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabelVirtualUsers))
                                    .addComponent(jLabelStressMode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBoxStressMode, 0, 103, Short.MAX_VALUE)
                                    .addComponent(jTextFieldThreadsNumber)))
                            .addComponent(jButtonStress))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldUrl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPaneHttpOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelVirtualUsers)
                    .addComponent(jTextFieldThreadsNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelStressMode)
                    .addComponent(jComboBoxStressMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonStress)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldThreadsNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldThreadsNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldThreadsNumberActionPerformed

  private void jComboBoxMethodActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void jComboBoxStressModeActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  // TODO implement clear method to reset view
  public void clear() {

  }

  public javax.swing.JButton getjButtonStress() {
    return jButtonStress;
  }

  public javax.swing.JComboBox getjComboBoxMethod() {
    return jComboBoxMethod;
  }

  public javax.swing.JTable getjTableHeaders() {
    return jTableHeaders;
  }

  public javax.swing.JTextArea getjTextAreaAssertResponseScript() {
    return jTextAreaAssertResponseScript;
  }

  public javax.swing.JTextArea getjTextAreaPostBody() {
    return jTextAreaPostBody;
  }

  public javax.swing.JTextField getjTextFieldUrl() {
    return jTextFieldUrl;
  }

  public javax.swing.JButton getjButtonOpenReportFolderSelector() {
    return jButtonOpenReportFolderSelector;
  }

  public javax.swing.JTextField getjTextFieldReportFolderLocation() {
    return jTextFieldReportFolderLocation;
  }

  public javax.swing.JTextField getjTextFieldReportName() {
    return jTextFieldReportName;
  }

  public javax.swing.JTextField getjTextFieldDataCsvFilePath() {
    return jTextFieldDataCsvFilePath;
  }

  public javax.swing.JComboBox<String> getjComboBoxStressMode() {
    return jComboBoxStressMode;
  }

  public javax.swing.JTextField getjTextFieldThreadsNumber() {
    return jTextFieldThreadsNumber;
  }

  public javax.swing.JTextArea getjTextAreaStressLog() {
    return jTextAreaStressLog;
  }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonOpenDataFileSelector;
    private javax.swing.JButton jButtonOpenReportFolderSelector;
    private javax.swing.JButton jButtonStress;
    private javax.swing.JComboBox jComboBoxMethod;
    private javax.swing.JComboBox<String> jComboBoxStressMode;
    private javax.swing.JLabel jLabelDataCsvFilePath;
    private javax.swing.JLabel jLabelReportFolderLocation;
    private javax.swing.JLabel jLabelReportName;
    private javax.swing.JLabel jLabelStressMode;
    private javax.swing.JLabel jLabelVirtualUsers;
    private javax.swing.JPanel jPanelAssertResponseScript;
    private javax.swing.JPanel jPanelBody;
    private javax.swing.JPanel jPanelHeaders;
    private javax.swing.JPanel jPanelReport;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPaneHttpOptions;
    private javax.swing.JTable jTableHeaders;
    private javax.swing.JTextArea jTextAreaAssertResponseScript;
    private javax.swing.JTextArea jTextAreaPostBody;
    private javax.swing.JTextArea jTextAreaStressLog;
    private javax.swing.JTextField jTextFieldDataCsvFilePath;
    private javax.swing.JTextField jTextFieldReportFolderLocation;
    private javax.swing.JTextField jTextFieldReportName;
    private javax.swing.JTextField jTextFieldThreadsNumber;
    private javax.swing.JTextField jTextFieldUrl;
    // End of variables declaration//GEN-END:variables
}