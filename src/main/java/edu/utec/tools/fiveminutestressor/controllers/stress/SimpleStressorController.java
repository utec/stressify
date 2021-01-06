package edu.utec.tools.fiveminutestressor.controllers.stress;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import edu.utec.tools.fiveminutestressor.common.AssertsHelper;
import edu.utec.tools.fiveminutestressor.common.StressorConstants;
import edu.utec.tools.fiveminutestressor.common.StringHelper;
import edu.utec.tools.fiveminutestressor.mode.SimpleGraphicStressor;
import edu.utec.tools.fiveminutestressor.ui.MainView;

public class SimpleStressorController implements ActionListener {

  private MainView mainView;
  private JTable jTableHeaders;
  private JTextArea jTextAreaAssertResponseScript;
  private JTextArea jTextAreaStressLog;
  private JTextArea jTextAreaPostBody;
  private JTextField jTextFieldReportFolderLocation;
  private JTextField jTextFieldReportName;
  private JTextField jTextFieldUrl;
  private JTextField jTextFieldDataCsvFilePath;
  private JTextField jTextFieldThreadsNumber;
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
    this.jTextFieldReportFolderLocation =
        this.mainView.getPanelHttpTest().getjTextFieldReportFolderLocation();
    this.jTextFieldReportName = this.mainView.getPanelHttpTest().getjTextFieldReportName();
    this.jTextFieldUrl = this.mainView.getPanelHttpTest().getjTextFieldUrl();
    this.jButtonStress = this.mainView.getPanelHttpTest().getjButtonStress();
    this.jComboBoxMethod = this.mainView.getPanelHttpTest().getjComboBoxMethod();
    this.jTextFieldDataCsvFilePath =
        this.mainView.getPanelHttpTest().getjTextFieldDataCsvFilePath();
    this.jComboBoxStressMode = this.mainView.getPanelHttpTest().getjComboBoxStressMode();
    this.jTextFieldThreadsNumber = this.mainView.getPanelHttpTest().getjTextFieldThreadsNumber();

  }

  public void initializeActionListeners() {
    jButtonStress.addActionListener(this);

    jTextFieldUrl.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        onUrlChange(jTextFieldUrl.getText());
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        onUrlChange(jTextFieldUrl.getText());
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        onUrlChange(jTextFieldUrl.getText());
      }
    });

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand() == jButtonStress.getActionCommand()) {
      try {
        stress();
      } catch (Exception ex) {
        ex.printStackTrace();
        String message = ex.getMessage();
        JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }

  public void onUrlChange(String urlString) {
    if (urlString != null && !urlString.isEmpty()) {
      try {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String date = sdf.format(new Date());
        String reportName = "report-"+StringHelper.sanitizeUrlToFileLocation(urlString)+"-"+date+".csv";
        jTextFieldReportName.setText(reportName);
      } catch (Exception e) {
        // TODO codereview
      }
    }
  }

  public void stress() throws Exception {

    if (graphicStressor == null) {
      graphicStressor = new SimpleGraphicStressor(jTextAreaStressLog);
    }

    String csvDataPath = jTextFieldDataCsvFilePath.getText();

    String reportFolderPath = jTextFieldReportFolderLocation.getText();
    AssertsHelper.ensureNotNullEmptyString(reportFolderPath, "[report folder location]");
    String reportName = jTextFieldReportName.getText();
    AssertsHelper.ensureNotNullEmptyString(reportFolderPath, "[report name]");
    String reportPath = reportFolderPath + File.separator + reportName;
    String reportColumns = StressorConstants.REPORT_COLUMN_NAMES;

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
    String threads = jTextFieldThreadsNumber.getText();

    graphicStressor.perform(csvDataPath, reportPath, reportColumns, mode, threads, url, method,
        body, headers, assertScript);
  }

  public MainView getMainView() {
    return mainView;
  }

  public void setMainView(MainView mainView) {
    this.mainView = mainView;
  }
}
