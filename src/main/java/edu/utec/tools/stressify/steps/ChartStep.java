package edu.utec.tools.stressify.steps;

import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import edu.utec.tools.stressify.core.ExecutableStep;

public class ChartStep implements ExecutableStep {

  @Override
  public Object execute(HashMap<String, Object> parameters) throws Exception {
    List<HashMap<String, Object>> dataList =
        (List<HashMap<String, Object>>) parameters.get("dataStress");
    String mode = (String) parameters.get("mode");
    String threads = (String) parameters.get("threads");
    String uuid = (String) parameters.get("uuid");
    String reportFolderPath = (String) parameters.get("reportFolderPath");
    String chartTitle = String.format("%s %s users", threads, mode);
    JFreeChart chart = createChart(dataList, chartTitle);

    String chartImageFileName = String.format("report-%s-chart.png", uuid);

    ChartUtils.saveChartAsPNG(new File(reportFolderPath + File.separator + chartImageFileName),
        chart, 1000, 500);
    return "success";
  }

  private JFreeChart createChart(List<HashMap<String, Object>> dataList, String chartTitle) {
    XYSeries realSerie = new XYSeries("Real");
    int sum = 0;
    for (int i = 0; i < dataList.size(); i++) {
      HashMap<String, Object> row = dataList.get(i);
      int y = getIntFromLong(row.get("totalExecutionMillisTime"));
      if (y >= 0) {
        realSerie.add(i + 1, y);
        sum += y;
      }
    }

    XYSeries averageSerie = new XYSeries("Average");
    int average = sum / realSerie.getItemCount();
    for (int i = 0; i < dataList.size(); i++) {
      HashMap<String, Object> row = dataList.get(i);
      int y = getIntFromLong(row.get("totalExecutionMillisTime"));
      if (y >= 0) {
        averageSerie.add(i + 1, average);
      }
    }

    XYSeriesCollection dataset = new XYSeriesCollection();
    dataset.addSeries(realSerie);
    dataset.addSeries(averageSerie);

    JFreeChart chart = ChartFactory.createXYLineChart(chartTitle, "Number of users",
        "Response Time (millis)", dataset, PlotOrientation.VERTICAL, true, true, false);

    XYPlot plot = chart.getXYPlot();

    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
    renderer.setSeriesPaint(0, Color.RED);
    renderer.setSeriesPaint(1, Color.BLUE);
    plot.setRenderer(renderer);

    return chart;
  }

  private int getIntFromLong(Object longg) {
    if (longg == null || !(longg instanceof Long)) {
      return -1;
    } else {
      Long temp = (Long) longg;
      return temp.intValue();
    }
  }

}
