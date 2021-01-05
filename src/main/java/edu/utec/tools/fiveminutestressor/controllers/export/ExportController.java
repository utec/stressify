package edu.utec.tools.fiveminutestressor.controllers.export;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableModel;
import org.codehaus.jackson.map.ObjectMapper;
import edu.utec.tools.fiveminutestressor.model.HttpHeader;
import edu.utec.tools.fiveminutestressor.model.Stress;
import edu.utec.tools.fiveminutestressor.ui.MainView;

public class ExportController {

  private MainView mainView;
  private JComboBox<?> jComboBoxMethod;
  private JTextField jTextFieldUrl;
  private JTable jTableHeaders;
  private JTextArea jTextAreaPostBody;
  private JTextArea jTextAreaAssertResponseScript;
  private JTextField jTextFieldReportFilepath;
  private JTextField jTextFieldReportColumnNames;
  private JTextField jTextFieldDataCsvFilePath;
  private JComboBox<?> jComboBoxStressMode;
  private JTextField jTextFieldVirtualUsers;

  public ExportController(MainView mainView) {
    super();
    this.mainView = mainView;
    initializeUiReferences();
  }

  public void initializeUiReferences() {
    this.jComboBoxMethod = this.mainView.getPanelHttpTest().getjComboBoxMethod();
    this.jTextFieldUrl = this.mainView.getPanelHttpTest().getjTextFieldUrl();
    this.jTableHeaders = this.mainView.getPanelHttpTest().getjTableHeaders();
    this.jTextAreaPostBody = this.mainView.getPanelHttpTest().getjTextAreaPostBody();
    this.jTextAreaAssertResponseScript =
        this.mainView.getPanelHttpTest().getjTextAreaAssertResponseScript();
    this.jTextFieldReportFilepath = this.mainView.getPanelHttpTest().getjTextFieldReportFilePath();
    this.jTextFieldReportColumnNames =
        this.mainView.getPanelHttpTest().getjTextFieldReportColumnNames();
    this.jTextFieldDataCsvFilePath =
        this.mainView.getPanelHttpTest().getjTextFieldDataCsvFilePath();
    this.jComboBoxStressMode = this.mainView.getPanelHttpTest().getjComboBoxStressMode();
    this.jTextFieldVirtualUsers = this.mainView.getPanelHttpTest().getjTextFieldVirtualUsers();
  }

  public void performExport() {

    Stress stress = new Stress();
    stress.setMethod(this.jComboBoxMethod.getSelectedItem().toString());
    stress.setUrl(this.jTextFieldUrl.getText());
    stress.setHttpHeaders(getHeaders());
    stress.setBody(this.jTextAreaPostBody.getText());
    stress.setAssertScript(this.jTextAreaAssertResponseScript.getText());
    stress.setReportPath(this.jTextFieldReportFilepath.getText());
    stress.setReportColumns(this.jTextFieldReportColumnNames.getText());
    stress.setCsvDataPath(this.jTextFieldDataCsvFilePath.getText());
    stress.setMode(this.jComboBoxStressMode.getSelectedItem().toString());
    stress.setVirtualUsers(Integer.parseInt(this.jTextFieldVirtualUsers.getText()));

    ObjectMapper mapper = new ObjectMapper();
    String jsonInString = null;
    try {
      jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(stress);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error",
          JOptionPane.ERROR_MESSAGE);
      return;
    }

    JFileChooser fileChooser =
        new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    fileChooser.setDialogTitle("Specify a file to save");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("json files", ".json");
    fileChooser.addChoosableFileFilter(filter);

    int userSelection = fileChooser.showSaveDialog(this.mainView);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File fileToSave = fileChooser.getSelectedFile();
      try {
        FileOutputStream out = new FileOutputStream(fileToSave.getAbsolutePath());
        out.write(jsonInString.getBytes());
        out.close();
        JOptionPane.showMessageDialog(new JFrame(),
            fileToSave.getAbsolutePath() + " was successfully created.", "Success",
            JOptionPane.INFORMATION_MESSAGE);
        return;
      } catch (Exception e) {
        JOptionPane.showMessageDialog(new JFrame(), e.getMessage(), "Error",
            JOptionPane.ERROR_MESSAGE);
        return;
      }
    } else {
      JOptionPane.showMessageDialog(new JFrame(), "Selection file was canceled", "Error",
          JOptionPane.ERROR_MESSAGE);
      return;
    }
  }

  private ArrayList<HttpHeader> getHeaders() {
    TableModel model = this.jTableHeaders.getModel();
    ArrayList<HttpHeader> headers = new ArrayList<>();
    for (int row = 0; row < model.getRowCount(); row++) {
      HttpHeader header = new HttpHeader();
      for (int col = 0; col < model.getColumnCount(); col++) {        
        if (col == 0) {
          header.setKey((String) model.getValueAt(row, col));
        } else if (col == 1) {
          header.setValue((String) model.getValueAt(row, col));
        } else if (col == 2) {
          header.setDescription((String) model.getValueAt(row, col));
        }
      }
      if(header.getKey()!=null && header.getValue()!=null && header.getDescription()!=null) {
        headers.add(header);
      }
    }
    return headers;
  }

}
