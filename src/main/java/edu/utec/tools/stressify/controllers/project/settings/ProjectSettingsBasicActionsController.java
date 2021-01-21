package edu.utec.tools.stressify.controllers.project.settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import edu.utec.tools.stressify.ui.MainView;

public class ProjectSettingsBasicActionsController implements ActionListener {

  private MainView mainView;
  private JButton jButtonOpenDataFileSelector;
  private JButton jButtonOpenReportFolderSelector;
  private JTextField jTextFieldDataCsvFilePath;
  private JTextField jTextFieldReportFolderLocation;

  public ProjectSettingsBasicActionsController(MainView mainView) {
    super();
    this.mainView = mainView;
    initializeUiReferences();
    initializeActionListeners();
  }

  public void initializeUiReferences() {
    this.jButtonOpenDataFileSelector =
        this.mainView.getPanelHttpTest().getjButtonOpenDataFileSelector();
    this.jButtonOpenReportFolderSelector =
        this.mainView.getPanelHttpTest().getjButtonOpenReportFolderSelector();
    this.jTextFieldDataCsvFilePath =
        this.mainView.getPanelHttpTest().getjTextFieldDataCsvFilePath();
    this.jTextFieldReportFolderLocation =
        this.mainView.getPanelHttpTest().getjTextFieldReportFolderLocation();
  }

  public void initializeActionListeners() {
    jButtonOpenDataFileSelector.addActionListener(this);
    jButtonOpenReportFolderSelector.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource().hashCode() == jButtonOpenDataFileSelector.hashCode()) {
      openDataFileChooser();
    } else if (e.getSource().hashCode() == jButtonOpenReportFolderSelector.hashCode()) {
      openReportFolderChooser();
    }
  }

  public void openDataFileChooser() {

    JFileChooser fileChooser =
        new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    fileChooser.setDialogTitle("Select the csv file");
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

    fileChooser.setFileFilter(new FileNameExtensionFilter("csv files", "csv"));

    int userSelection = fileChooser.showOpenDialog(this.mainView);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File choosenFile = fileChooser.getSelectedFile();
      jTextFieldDataCsvFilePath.setText(choosenFile.getAbsolutePath());
    } else {
      JOptionPane.showMessageDialog(new JFrame(), "Selection file was canceled", "Error",
          JOptionPane.ERROR_MESSAGE);
      return;
    }
  }

  public void openReportFolderChooser() {

    JFileChooser fileChooser =
        new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
    fileChooser.setDialogTitle("Select the report folder");
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    int userSelection = fileChooser.showOpenDialog(this.mainView);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File choosenFile = fileChooser.getSelectedFile();
      jTextFieldReportFolderLocation.setText(choosenFile.getAbsolutePath());
    } else {
      JOptionPane.showMessageDialog(new JFrame(), "Selection folder was canceled", "Error",
          JOptionPane.ERROR_MESSAGE);
      return;
    }
  }

}
