/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package visual;

import dominio.Poblacion;
import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author Ruben
 */
public class Grafica1 extends ApplicationFrame {

    private XYDataset dataset;
    private XYSeries serieIndMax;
    private XYSeries serieAptProm;
    private JFreeChart chart;
    private ChartPanel chartPanel;
    private PropertyChangeListener pclModelo;

    public Grafica1(String title) {
        super(title);
        dataset = crearDataset();
        chart = crearChart(dataset);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
        pclModelo = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if ("generacion".equals(evt.getPropertyName())) {
                    System.out.println("llego");
                    Poblacion poblacion = (Poblacion) evt.getNewValue();
                    
                }
            }
        };
    }

    private JFreeChart crearChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Grafica del avance de las Generaciones",
                "Iteración",
                "Aptitud",
                dataset,
                PlotOrientation.HORIZONTAL,
                true,
                true,
                false);
        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot.getRenderer();
        renderer.setShapesVisible(true);
        renderer.setShapesFilled(true);

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        return chart;
    }

    private XYDataset crearDataset() {
        serieIndMax = new XYSeries("Individuo Maximo");
        serieAptProm = new XYSeries("Aptitud Promedio");
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(serieIndMax);
        dataset.addSeries(serieAptProm);
        return dataset;
    }


    public static void main(String[] args) throws InterruptedException {
        Grafica1 demo = new Grafica1("Line Chart Demo 2");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
