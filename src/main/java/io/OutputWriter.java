package io;

import model.Metric;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputWriter {

    public static enum Format {
        TXT, XML, HTML, JSON
    }

    public void saveMetrics(List<Metric> metrics, String targetPath, Format... format) throws IOException {

        System.out.printf("saving metrics" + metrics.size());
        File target = new File(targetPath);
        if (target.exists()) {
            File metricsDir = new File(targetPath + File.separator + "processors");
            metricsDir.mkdir();

            File output = new File(targetPath + File.separator + "processors" + File.separator + "output.txt");
            output.createNewFile();

            BufferedWriter bw = new BufferedWriter(new FileWriter(output.getAbsoluteFile()));

            for (Metric metric : metrics) {
                bw.write(metricToTxt(metric));
            }
            bw.flush();
            bw.close();
        }
    }

    private String metricToTxt(Metric metric) {
        StringBuilder builder = new StringBuilder();

        builder.append("Name:").append(metric.getName()).append("\n");
        builder.append("Description:").append(metric.getDescription()).append("\n");
        for (String namedKey : metric.getElements().keySet()) {
            String namedValue = metric.getElements().get(namedKey);
            builder.append("\t").append(namedKey).append(":").append(namedValue).append("\n");
        }
        builder.append("-----------------\n");

        return builder.toString();
    }

}
