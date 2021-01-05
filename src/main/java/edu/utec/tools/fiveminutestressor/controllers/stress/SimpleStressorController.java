package edu.utec.tools.fiveminutestressor.controllers.stress;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.TableModel;
import edu.utec.tools.fiveminutestressor.mode.SimpleGraphicStressor;
import edu.utec.tools.fiveminutestressor.ui.MainView;

public class SimpleStressorController implements ActionListener {

  private MainView mainView;
  private JTable jTableHeaders;
  private JTextArea jTextAreaAssertResponseScript;
  private JTextArea jTextAreaStressLog;
  private JTextArea jTextAreaPostBody;
  private JTextField jTextFieldReportFilepath;
  private JTextField jTextFieldUrl;
  private JTextField jTextFieldReportColumnNames;
  private JTextField jTextFieldDataCsvFilePath;
  private JTextField jTextFieldVirtualUsers;
  private JButton jButtonStress;
  private JComboBox<?> jComboBoxMethod;
  private JComboBox<?> jComboBoxStressMode;

  private SimpleGraphicStressor graphicStressor;

  public SimpleStressorController(MainView mainView) {
    super();
    this.mainView = mainView;
    initializeUiReferences();
    initializeActionListeners();
  }

  public void initializeUiReferences() {
    this.jTableHeaders = this.mainView.getPanelHttpTest().getjTableHeaders();
    this.jTextAreaAssertResponseScript =
        this.mainView.getPanelHttpTest().getjTextAreaAssertResponseScript();
    this.jTextAreaStressLog = this.mainView.getPanelHttpTest().getjTextAreaStressLog();
    this.jTextAreaPostBody = this.mainView.getPanelHttpTest().getjTextAreaPostBody();
    this.jTextFieldReportFilepath = this.mainView.getPanelHttpTest().getjTextFieldReportFilePath();
    this.jTextFieldUrl = this.mainView.getPanelHttpTest().getjTextFieldUrl();
    this.jButtonStress = this.mainView.getPanelHttpTest().getjButtonStress();
    this.jComboBoxMethod = this.mainView.getPanelHttpTest().getjComboBoxMethod();
    this.jTextFieldReportColumnNames =
        this.mainView.getPanelHttpTest().getjTextFieldReportColumnNames();
    this.jTextFieldDataCsvFilePath =
        this.mainView.getPanelHttpTest().getjTextFieldDataCsvFilePath();
    this.jComboBoxStressMode = this.mainView.getPanelHttpTest().getjComboBoxStressMode();
    this.jTextFieldVirtualUsers = this.mainView.getPanelHttpTest().getjTextFieldVirtualUsers();

  }

  public void initializeActionListeners() {
    jButtonStress.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand() == jButtonStress.getActionCommand()) {
      stress();
    }
  }

  public void stress() {

    if (graphicStressor == null) {
      graphicStressor = new SimpleGraphicStressor(jTextAreaStressLog);
    }

    try {

      String csvDataPath = jTextFieldDataCsvFilePath.getText();
      String reportPath = jTextFieldReportFilepath.getText();
      String reportColumns = jTextFieldReportColumnNames.getText();

      String url = jTextFieldUrl.getText();
      String method = jComboBoxMethod.getSelectedItem().toString();
      String body = jTextAreaPostBody.getText();

      TableModel model = this.jTableHeaders.getModel();
      ArrayList<HashMap<String, String>> headers = new ArrayList<>();
      for (int row = 0; row < model.getRowCount(); row++) {
        HashMap<String, String> header = new HashMap<>();
        String key = null;
        for (int col = 0; col < model.getColumnCount(); col++) {
          if (col == 0) {
            key = "" + model.getValueAt(row, col);
          } else if (col == 1) {
            header.put(key, "" + model.getValueAt(row, col));
          }
        }
        headers.add(header);
      }

      String assertScript = this.jTextAreaAssertResponseScript.getText();
      String mode = jComboBoxStressMode.getSelectedItem().toString();
      String threads = jTextFieldVirtualUsers.getText();

      graphicStressor.perform(csvDataPath, reportPath, reportColumns, mode, threads, url, method,
          body, headers, assertScript);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public MainView getMainView() {
    return mainView;
  }

  public void setMainView(MainView mainView) {
    this.mainView = mainView;
  }
}
