package edu.utec.tools.fiveminutestressor.ui.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

public class HumanStressorController implements ActionListener {

	private MainView mainView;
	private JTable jTableHeaders;
	private JTextArea jTextAreaAssertResponseScript;
	private JTextArea jTextAreaStressLog;
	private JTextArea jTextAreaPostBody;
	private JTextField jTextFieldReportFolderLocation;
	private JTextField jTextFieldReportName;
	private JTextField jTextFieldUrl;
	private JTextField jTextFieldReportColumnNames;
	private JTextField jTextFieldDataCsvFilePath;
	private JTextField jTextFieldVirtualUsers;
	private JButton jButtonClearLog;
	private JButton jButtonExport;
	private JButton jButtonStress;
	private JComboBox jComboBoxMethod;
	private JComboBox jComboBoxStressMode;

	private SimpleGraphicStressor graphicStressor;

	public HumanStressorController(MainView mainView) {
		super();
		this.mainView = mainView;
		initializeUiReferences();
		initializeActionListeners();
	}

	public void initializeUiReferences() {
		this.jTableHeaders = this.mainView.getPanelHttpTestForHumans().getjTableHeaders();
		this.jTextAreaAssertResponseScript = this.mainView.getPanelHttpTestForHumans()
				.getjTextAreaAssertResponseScript();
		this.jTextAreaStressLog = this.mainView.getPanelHttpTestForHumans().getjTextAreaStressLog();
		this.jTextAreaPostBody = this.mainView.getPanelHttpTestForHumans().getjTextAreaPostBody();
		this.jTextFieldReportFolderLocation = this.mainView.getPanelHttpTestForHumans()
				.getjTextFieldReportFolderLocation();
		this.jTextFieldReportName = this.mainView.getPanelHttpTestForHumans().getjTextFieldReportName();
		this.jTextFieldUrl = this.mainView.getPanelHttpTestForHumans().getjTextFieldUrl();
		this.jButtonClearLog = this.mainView.getPanelHttpTestForHumans().getjButtonClearLog();
		this.jButtonExport = this.mainView.getPanelHttpTestForHumans().getjButtonExport();
		this.jButtonStress = this.mainView.getPanelHttpTestForHumans().getjButtonStress();
		this.jComboBoxMethod = this.mainView.getPanelHttpTestForHumans().getjComboBoxMethod();
		this.jTextFieldReportColumnNames = this.mainView.getPanelHttpTestForHumans().getjTextFieldReportColumnNames();
		this.jTextFieldDataCsvFilePath = this.mainView.getPanelHttpTestForHumans().getjTextFieldDataCsvFilePath();
		this.jComboBoxStressMode = this.mainView.getPanelHttpTestForHumans().getjComboBoxStressMode();
		this.jTextFieldVirtualUsers = this.mainView.getPanelHttpTestForHumans().getjTextFieldVirtualUsers();

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
			String reportPath = jTextFieldReportFolderLocation.getText() + File.separator
					+ jTextFieldReportName.getText();
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

			graphicStressor.perform(csvDataPath, reportPath, reportColumns, mode, threads, url, method,body, headers,
					assertScript);
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
