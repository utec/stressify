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
  // <editor-fold defaultstate="collapsed" desc="Generated Code">
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
    jTextFieldReportFilepath = new javax.swing.JTextField();
    jTextFieldReportName = new javax.swing.JTextField();
    jLabelReportName = new javax.swing.JLabel();
    jLabelReportColumnNames = new javax.swing.JLabel();
    jTextFieldReportColumnNames = new javax.swing.JTextField();
    jPanelData = new javax.swing.JPanel();
    jLabelDataCsvFilePath = new javax.swing.JLabel();
    jTextFieldDataCsvFilePath = new javax.swing.JTextField();
    jButtonClearLog = new javax.swing.JButton();
    jScrollPane4 = new javax.swing.JScrollPane();
    jTextAreaStressLog = new javax.swing.JTextArea();
    jComboBoxStressMode = new javax.swing.JComboBox();
    jLabelVirtualUsers = new javax.swing.JLabel();
    jTextFieldVirtualUsers = new javax.swing.JTextField();

    jComboBoxMethod.setModel(
        new javax.swing.DefaultComboBoxModel(new String[] {"GET", "POST", "DELETE", "PUT"}));
    jComboBoxMethod.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jComboBoxMethodActionPerformed(evt);
      }
    });

    jButtonStress.setText("Stress");

    jTableHeaders.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {{null, null, null}, {null, null, null}, {null, null, null},
            {null, null, null}, {null, null, null}, {null, null, null}, {null, null, null},
            {null, null, null}, {null, null, null}, {null, null, null}},
        new String[] {"Key", "Value", "Description"}));
    jScrollPane1.setViewportView(jTableHeaders);

    javax.swing.GroupLayout jPanelHeadersLayout = new javax.swing.GroupLayout(jPanelHeaders);
    jPanelHeaders.setLayout(jPanelHeadersLayout);
    jPanelHeadersLayout.setHorizontalGroup(jPanelHeadersLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelHeadersLayout.createSequentialGroup().addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
            .addGap(13, 13, 13)));
    jPanelHeadersLayout.setVerticalGroup(jPanelHeadersLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelHeadersLayout.createSequentialGroup().addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
            .addGap(13, 13, 13)));

    jTabbedPaneHttpOptions.addTab("Headers", jPanelHeaders);

    jTextAreaPostBody.setColumns(20);
    jTextAreaPostBody.setRows(5);
    jScrollPane2.setViewportView(jTextAreaPostBody);

    javax.swing.GroupLayout jPanelBodyLayout = new javax.swing.GroupLayout(jPanelBody);
    jPanelBody.setLayout(jPanelBodyLayout);
    jPanelBodyLayout.setHorizontalGroup(jPanelBodyLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelBodyLayout.createSequentialGroup().addContainerGap()
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
            .addContainerGap()));
    jPanelBodyLayout.setVerticalGroup(jPanelBodyLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelBodyLayout.createSequentialGroup().addContainerGap()
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
            .addContainerGap()));

    jTabbedPaneHttpOptions.addTab("Body", jPanelBody);

    jTextAreaAssertResponseScript.setColumns(20);
    jTextAreaAssertResponseScript.setRows(5);
    jScrollPane3.setViewportView(jTextAreaAssertResponseScript);

    javax.swing.GroupLayout jPanelAssertResponseScriptLayout =
        new javax.swing.GroupLayout(jPanelAssertResponseScript);
    jPanelAssertResponseScript.setLayout(jPanelAssertResponseScriptLayout);
    jPanelAssertResponseScriptLayout.setHorizontalGroup(jPanelAssertResponseScriptLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelAssertResponseScriptLayout.createSequentialGroup().addContainerGap()
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 665, Short.MAX_VALUE)
            .addContainerGap()));
    jPanelAssertResponseScriptLayout.setVerticalGroup(jPanelAssertResponseScriptLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanelAssertResponseScriptLayout.createSequentialGroup().addContainerGap()
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
            .addContainerGap()));

    jTabbedPaneHttpOptions.addTab("Assert Respose Script", jPanelAssertResponseScript);

    jLabelReportFolderLocation.setText("Enter report destination absolute file path ");

    jLabelReportName.setText("Enter the report name");
    jLabelReportName.setVisible(false);// TODO delete
    jTextFieldReportName.setVisible(false);

    jLabelReportColumnNames.setText("Modify the column names of the report");

    jTextFieldReportColumnNames
        .setText("date,start_time,end_time,http_response_code,asserts,elapsed_time");

    javax.swing.GroupLayout jPanelReportLayout = new javax.swing.GroupLayout(jPanelReport);
    jPanelReport.setLayout(jPanelReportLayout);
    jPanelReportLayout.setHorizontalGroup(
        jPanelReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReportLayout.createSequentialGroup().addContainerGap()
                .addGroup(jPanelReportLayout
                    .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldReportFilepath).addComponent(jTextFieldReportName)
                    .addComponent(jTextFieldReportColumnNames, javax.swing.GroupLayout.DEFAULT_SIZE,
                        665, Short.MAX_VALUE)
                    .addGroup(jPanelReportLayout.createSequentialGroup()
                        .addGroup(jPanelReportLayout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelReportFolderLocation,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 230,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelReportName, javax.swing.GroupLayout.PREFERRED_SIZE,
                                230, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelReportColumnNames,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 230,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap()));
    jPanelReportLayout.setVerticalGroup(
        jPanelReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReportLayout.createSequentialGroup().addContainerGap()
                .addComponent(jLabelReportFolderLocation)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldReportFilepath, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelReportName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldReportName, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelReportColumnNames)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldReportColumnNames, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE)));

    jTabbedPaneHttpOptions.addTab("Report", jPanelReport);

    jLabelDataCsvFilePath.setText("Enter csv data file absolute path");

    javax.swing.GroupLayout jPanelDataLayout = new javax.swing.GroupLayout(jPanelData);
    jPanelData.setLayout(jPanelDataLayout);
    jPanelDataLayout.setHorizontalGroup(
        jPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDataLayout.createSequentialGroup().addContainerGap()
                .addGroup(jPanelDataLayout
                    .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDataLayout.createSequentialGroup()
                        .addComponent(jLabelDataCsvFilePath, javax.swing.GroupLayout.PREFERRED_SIZE,
                            288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 377, Short.MAX_VALUE))
                    .addComponent(jTextFieldDataCsvFilePath))
                .addContainerGap()));
    jPanelDataLayout.setVerticalGroup(
        jPanelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDataLayout.createSequentialGroup().addContainerGap()
                .addComponent(jLabelDataCsvFilePath)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldDataCsvFilePath, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(138, Short.MAX_VALUE)));

    jTabbedPaneHttpOptions.addTab("Data", jPanelData);

    jButtonClearLog.setText("Clear log");

    jTextAreaStressLog.setColumns(20);
    jTextAreaStressLog.setRows(5);
    jScrollPane4.setViewportView(jTextAreaStressLog);

    jComboBoxStressMode
        .setModel(new javax.swing.DefaultComboBoxModel(new String[] {"continuous", "parallel"}));
    jComboBoxStressMode.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jComboBoxStressModeActionPerformed(evt);
      }
    });

    jLabelVirtualUsers.setText("Virtual users:");

    jTextFieldVirtualUsers.setText("5");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBoxMethod, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldUrl))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout
                        .createSequentialGroup()
                        .addComponent(jComboBoxStressMode, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelVirtualUsers, javax.swing.GroupLayout.PREFERRED_SIZE,
                            77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jTextFieldVirtualUsers,
                            javax.swing.GroupLayout.PREFERRED_SIZE, 61,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonStress)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonClearLog)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup().addComponent(jTabbedPaneHttpOptions).addGap(38,
                38, 38)))));
    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup().addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jComboBoxMethod, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jTextFieldUrl, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jTabbedPaneHttpOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 233,
                javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButtonStress).addComponent(jButtonClearLog)
                .addComponent(jComboBoxStressMode, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabelVirtualUsers).addComponent(jTextFieldVirtualUsers,
                    javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
            .addContainerGap()));
  }// </editor-fold>

  private void jComboBoxMethodActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void jComboBoxStressModeActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  // TODO implement clear method to reset view
  public void clear() {

  }

  public javax.swing.JButton getjButtonClearLog() {
    return jButtonClearLog;
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

  public javax.swing.JTextField getjTextFieldReportFilePath() {
    return jTextFieldReportFilepath;
  }

  public javax.swing.JTextField getjTextFieldUrl() {
    return jTextFieldUrl;
  }

  public javax.swing.JTextField getjTextFieldReportColumnNames() {
    return jTextFieldReportColumnNames;
  }

  public javax.swing.JTextArea getjTextAreaStressLog() {
    return jTextAreaStressLog;
  }

  public javax.swing.JTextField getjTextFieldDataCsvFilePath() {
    return jTextFieldDataCsvFilePath;
  }

  public javax.swing.JComboBox getjComboBoxStressMode() {
    return jComboBoxStressMode;
  }

  public javax.swing.JTextField getjTextFieldVirtualUsers() {
    return jTextFieldVirtualUsers;
  }



  // Variables declaration - do not modify
  private javax.swing.JButton jButtonClearLog;
  private javax.swing.JButton jButtonStress;
  private javax.swing.JComboBox jComboBoxMethod;
  private javax.swing.JComboBox jComboBoxStressMode;
  private javax.swing.JLabel jLabelDataCsvFilePath;
  private javax.swing.JLabel jLabelReportColumnNames;
  private javax.swing.JLabel jLabelReportFolderLocation;
  private javax.swing.JLabel jLabelReportName;
  private javax.swing.JLabel jLabelVirtualUsers;
  private javax.swing.JPanel jPanelAssertResponseScript;
  private javax.swing.JPanel jPanelBody;
  private javax.swing.JPanel jPanelData;
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
  private javax.swing.JTextField jTextFieldReportColumnNames;
  private javax.swing.JTextField jTextFieldReportFilepath;
  private javax.swing.JTextField jTextFieldReportName;// TODO: delete
  private javax.swing.JTextField jTextFieldUrl;
  private javax.swing.JTextField jTextFieldVirtualUsers;
  // End of variables declaration
}
