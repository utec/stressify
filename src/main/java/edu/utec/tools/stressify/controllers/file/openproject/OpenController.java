package edu.utec.tools.stressify.controllers.file.openproject;

import java.io.File;
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
import edu.utec.tools.stressify.model.HttpHeader;
import edu.utec.tools.stressify.model.Stress;
import edu.utec.tools.stressify.ui.MainView;

public class OpenController {

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

  public OpenController(MainView mainView) {
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
    this.jTextFieldReportFolderLocation = this.mainView.getPanelHttpTest().getjTextFieldReportFolderLocation();
    this.jTextFieldReportName = this.mainView.getPanelHttpTest().getjTextFieldReportName();
    this.jTextFieldDataCsvFilePath =
        this.mainView.getPanelHttpTest().getjTextFieldDataCsvFilePath();
    this.jComboBoxStressMode = this.mainView.getPanelHttpTest().getjComboBoxStressMode();
    this.jTextFieldThreadsNumber = this.mainView.getPanelHttpTest().getjTextFieldThreadsNumber();
  }

  public void performOpen() {

    JFileChooser fileChooser =
        new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    fileChooser.setDialogTitle("Specify a file to open");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    FileNameExtensionFilter filter = new FileNameExtensionFilter("json files", ".json");
    fileChooser.addChoosableFileFilter(filter);

    int userSelection = fileChooser.showOpenDialog(this.mainView);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File fileToOpen = fileChooser.getSelectedFile();
      ObjectMapper mapper = new ObjectMapper();
      try {
        Stress stress = mapper.readValue(fileToOpen, Stress.class);
        setValues(stress);
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

  private void setValues(Stress stress) throws Exception {

    this.jComboBoxMethod.getModel().setSelectedItem(stress.getMethod());
    this.jTextFieldUrl.setText(stress.getUrl());
    setHeaders(stress.getHttpHeaders());
    this.jTextAreaPostBody.setText(stress.getBody());
    this.jTextAreaAssertResponseScript.setText(stress.getAssertScript());
    this.jTextFieldReportFolderLocation.setText(stress.getReportFolderPath());
    this.jTextFieldReportName.setText(stress.getReportName());
    this.jTextFieldDataCsvFilePath.setText(stress.getCsvDataPath());
    this.jComboBoxStressMode.getModel().setSelectedItem(stress.getMode());
    this.jTextFieldThreadsNumber.setText("" + stress.getThreadNumber());
  }

  private void setHeaders(ArrayList<HttpHeader> headers) throws Exception {

    TableModel model = this.jTableHeaders.getModel();
    if (headers.size() > model.getRowCount()) {
      throw new Exception("More than " + model.getRowCount() + " headers are not allowed");
    }

    for (int a = 0; a < headers.size(); a++) {
      HttpHeader header = headers.get(a);
      model.setValueAt(header.getKey(), a, 0);
      model.setValueAt(header.getValue(), a, 1);
      model.setValueAt(header.getDescription(), a, 2);
    }

  }

}
