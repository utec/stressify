package edu.utec.tools.smarth_stressor.steps;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.utec.tools.smarth_stressor.base.ExecutableStep;

public class CSVReaderStep implements ExecutableStep {

  @SuppressWarnings("resource")
  public Object execute(Object[] args) throws Exception {

    String fileName = "" + args[0];

    FileReader fileReader = null;

    CSVParser csvFileParser = null;

    CSVFormat csvFileFormat = CSVFormat.DEFAULT;

    // initialize FileReader object
    try {
      fileReader = new FileReader(fileName);
    } catch (FileNotFoundException e) {
      throw new Exception("Failed to read csv input file", e);
    }

    // initialize CSVParser object
    try {
      csvFileParser = new CSVParser(fileReader, csvFileFormat);
    } catch (IOException e) {
      throw new Exception("Failed to instantiate csv parser.", e);
    }

    // Get a list of CSV file records
    List<?> csvRecords = null;

    try {
      csvRecords = csvFileParser.getRecords();
    } catch (IOException e) {
      throw new Exception("Failed to read rows from csv", e);
    }

    if (csvRecords == null || csvRecords.size() < 2) {
      throw new Exception("csv has no rows or only has header row.");
    }

    CSVRecord header = (CSVRecord) csvRecords.get(0);
    CSVRecord row = (CSVRecord) csvRecords.get(1);

    if (header.size() != row.size()) {
      throw new Exception("csv header and rows has different columns size");
    }

    // close
    if (fileReader != null) {
      fileReader.close();
    }

    if (csvFileParser != null) {
      csvFileParser.close();
    }

    return csvRecords;

  }

}
