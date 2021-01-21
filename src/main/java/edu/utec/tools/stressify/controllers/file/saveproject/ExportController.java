package edu.utec.tools.stressify.controllers.file.saveproject;

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
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.utec.tools.stressify.model.HttpHeader;
import edu.utec.tools.stressify.model.Stress;
import edu.utec.tools.stressify.ui.MainView;

public class ExportController {

  private MainView mainView;
  private JComboBox<?> jComboBoxMethod;
  private JTextField jTextFieldUrl;
  private JTable jTableHeaders;
  private JTextArea jTextAreaPostBody;
  private JTextArea jTextAreaAssertResponseScript;
  private JTextField jTextFieldReportFolderLocation;
  private JTextField jTextFieldReportName;
  private JTextField jTextFieldDataCsvFilePath;
  private JComboBox<?> jComboBoxStressMode;
  private JTextField jTextFieldThreadsNumber;

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
    this.jTextFieldReportFolderLocation =
        this.mainView.getPanelHttpTest().getjTextFieldReportFolderLocation();
    this.jTextFieldReportName = this.mainView.getPanelHttpTest().getjTextFieldReportName();
    this.jTextFieldDataCsvFilePath =
        this.mainView.getPanelHttpTest().getjTextFieldDataCsvFilePath();
    this.jComboBoxStressMode = this.mainView.getPanelHttpTest().getjComboBoxStressMode();
    this.jTextFieldThreadsNumber = this.mainView.getPanelHttpTest().getjTextFieldThreadsNumber();
  }

  public void performExport() {

    Stress stress = new Stress();
    stress.setMethod(this.jComboBoxMethod.getSelectedItem().toString());
    stress.setUrl(this.jTextFieldUrl.getText());
    stress.setHttpHeaders(getHeaders());
    stress.setBody(this.jTextAreaPostBody.getText());
    stress.setAssertScript(this.jTextAreaAssertResponseScript.getText());
    stress.setReportFolderPath(this.jTextFieldReportFolderLocation.getText());
    stress.setReportName(this.jTextFieldReportName.getText());
    stress.setCsvDataPath(this.jTextFieldDataCsvFilePath.getText());
    stress.setMode(this.jComboBoxStressMode.getSelectedItem().toString());
    stress.setThreadNumber(Integer.parseInt(this.jTextFieldThreadsNumber.getText()));

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
        FileOutputStream out = new FileOutputStream(addJsonExtension(fileToSave.getAbsolutePath()));
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

  private String addJsonExtension(String absoluteFileName) {
    if (!absoluteFileName.endsWith(".json")) {
      absoluteFileName += "json";
    }
    return absoluteFileName;
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
      if (header.getKey() != null && header.getValue() != null && header.getDescription() != null) {
        headers.add(header);
      }
    }
    return headers;
  }

}
