/*
 * ChartBuilder.java
 *
 * Created on 4 Dec 2006, 14:11
 */

package net.sf.jschart.util;

import com.sun.faces.util.HtmlUtils;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.io.IOException;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.PeriodAxis;
import org.jfree.chart.axis.PeriodAxisLabelInfo;
import org.jfree.chart.axis.QuarterDateFormat;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.AbstractCategoryItemLabelGenerator;
import org.jfree.chart.labels.AbstractPieItemLabelGenerator;
import org.jfree.chart.labels.AbstractXYItemLabelGenerator;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.PieToolTipGenerator;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.labels.StandardXYSeriesLabelGenerator;
import org.jfree.chart.labels.XYToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.AbstractCategoryItemRenderer;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LayeredBarRenderer;
import org.jfree.chart.renderer.xy.AbstractXYItemRenderer;
import org.jfree.chart.renderer.xy.XYDifferenceRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.general.PieDataset;
import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.OHLCDataset;
import org.jfree.data.xy.WindDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.Layer;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.jfree.util.SortOrder;
import org.jfree.util.TableOrder;
import net.sf.jschart.component.Chart;
import net.sf.jschart.component.ExternalDataset;
import net.sf.jschart.component.ExternalDataset.DatasetType;

/**
 *
 * @author als
 */
public class ChartBuilder
{
    private static Log log = LogFactory.getLog(ChartBuilder.class);
    
    private static DateFormat getChartDateFormat(String dateFormat)
    {
        if (dateFormat != null)
            return dateFormat.equals("Q") ?
                new QuarterDateFormat() :
                new SimpleDateFormat(dateFormat);
        
        DateFormat defaultDateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT,
                FacesContext.getCurrentInstance().getViewRoot().getLocale());
        
        return defaultDateFormat;
    }
    
    private static String toHtml(String orig)
    {
        char[] buffer = new char[128];
        StringWriter wrt = new StringWriter();
        
        try
        {
            HtmlUtils.writeAttribute(wrt, buffer, orig);
        }
        catch (IOException ex)
        {
            log.error("toHtml", ex);
        }
        
        return wrt.toString();
    }
    
    private static class ChartCategoryToolTipGenerator
            extends AbstractCategoryItemLabelGenerator
            implements CategoryToolTipGenerator
    {
        private static final String DEFAULT_FORMAT = "{0} = {2} ({3})";
        
        public ChartCategoryToolTipGenerator(String format)
        {
            super(format != null ? format : DEFAULT_FORMAT,
                    NumberFormat.getInstance());
        }
        
        public ChartCategoryToolTipGenerator(String format, String dateFormat)
        {
            super(format != null ? format : DEFAULT_FORMAT,
                    getChartDateFormat(dateFormat));
        }
        
        public String generateToolTip(CategoryDataset dataset,
                int row, int column)
        {
            return toHtml(generateLabelString(dataset, row, column));
        }
    }
    
    private static class ChartPieToolTipGenerator
            extends AbstractPieItemLabelGenerator
            implements PieToolTipGenerator
    {
        private static final String DEFAULT_FORMAT = "{0}: ({1}, {2})";
        
        public ChartPieToolTipGenerator(String format)
        {
            super(format != null ? format : DEFAULT_FORMAT,
                    NumberFormat.getNumberInstance(),
                    NumberFormat.getPercentInstance());
        }
        
        public String generateToolTip(PieDataset dataset, Comparable key)
        {
            return toHtml(generateSectionLabel(dataset, key));
        }
    }
    
    private static class ChartXYToolTipGenerator
            extends AbstractXYItemLabelGenerator
            implements XYToolTipGenerator
    {
        private static final String DEFAULT_FORMAT = "{0}: ({1}, {2})";
        
        public ChartXYToolTipGenerator(String format)
        {
            super(format != null ? format : DEFAULT_FORMAT,
                    NumberFormat.getInstance(), NumberFormat.getInstance());
        }
        
        public ChartXYToolTipGenerator(String format, String dateFormat)
        {
            super(format != null ? format : DEFAULT_FORMAT,
                    getChartDateFormat(dateFormat),
                    NumberFormat.getInstance());
        }
        
        public ChartXYToolTipGenerator(String format, String dateFormat, String dateFormat2)
        {
            super(format != null ? format : DEFAULT_FORMAT,
                    getChartDateFormat(dateFormat),
                    getChartDateFormat(dateFormat2));
        }
        
        public String generateToolTip(XYDataset dataset, int series, int item)
        {
            return toHtml(generateLabelString(dataset, series, item));
        }
    }
    
    private static class GradientDrawingSupplier extends DefaultDrawingSupplier
    {
        public GradientDrawingSupplier(int factor)
        {
            if (factor < 100)
            {
                this.doDarker = true;
                this.factor = ((double)factor)/100.0D;
            }
            else
            {
                this.doDarker = false;
                this.factor = ((double)(factor % 100))/100.0D;
            }
        }
        
        private Color brighter(Color orig)
        {
            int r = orig.getRed();
            int g = orig.getGreen();
            int b = orig.getBlue();
            
            int i = (int)(1.0/(1.0-factor));
            if ( r == 0 && g == 0 && b == 0)
            {
                return new Color(i, i, i);
            }
            if ( r > 0 && r < i ) r = i;
            if ( g > 0 && g < i ) g = i;
            if ( b > 0 && b < i ) b = i;
            
            return new Color(Math.min((int)(r/factor), 255),
                    Math.min((int)(g/factor), 255),
                    Math.min((int)(b/factor), 255));
        }
        
        private Color darker(Color orig)
        {
            return new Color(Math.max((int)(orig.getRed() * factor), 0),
                    Math.max((int)(orig.getGreen() * factor), 0),
                    Math.max((int)(orig.getBlue() * factor), 0));
        }
        
        public Paint getNextPaint()
        {
            Paint paint = super.getNextPaint();
            if (paint instanceof Color)
            {
                Color color = (Color)paint;
                if (doDarker)
                    paint =  new GradientPaint(0.0F, 0.0F, color,
                            0.0F, 0.0F, darker(color));
                else
                    paint = new GradientPaint(0.0F, 0.0F, brighter(color),
                            0.0F, 0.0F, color);
            }
            return paint;
        }
        
        private double factor;
        private boolean doDarker;
    }
    
    private static abstract class AbstactChartBuilder
    {
        public abstract JFreeChart build(Chart chart, Dataset dataset);
        
        public static Color getColor(String color)
        {
            if (color == null || color.length() < 1)
                return Color.white;
            
            // HTML colors (#FFFFFF format)
            if (color.startsWith("#"))
            {
                return new Color(Integer.parseInt(color.substring(1), 16));
            }
            else
            {
                // Colors by name
                if (color.equals("black"))
                    return Color.black;
                if (color.equals("gray"))
                    return Color.gray;
                if (color.equals("yellow"))
                    return Color.yellow;
                if (color.equals("green"))
                    return Color.green;
                if (color.equals("blue"))
                    return Color.blue;
                if (color.equals("red"))
                    return Color.red;
                if (color.equals("orange"))
                    return Color.orange;
                if (color.equals("cyan"))
                    return Color.cyan;
                if (color.equals("magenta"))
                    return Color.magenta;
                if (color.equals("darkgray"))
                    return Color.darkGray;
                if (color.equals("lightgray"))
                    return Color.lightGray;
                if (color.equals("pink"))
                    return Color.pink;
                // default color is white
                return Color.white;
            }
        }
        
        protected PlotOrientation getPlotOrientation(String orientation)
        {
            if (orientation.equalsIgnoreCase("horizontal"))
            {
                return PlotOrientation.HORIZONTAL;
            }
            else
            {
                return PlotOrientation.VERTICAL;
            }
        }
        
        
        protected IntervalMarker createIntervalMarker(Chart chart)
        {
            String chartIntervalMarker = chart.getIntervalMarker();
            
            if (chartIntervalMarker == null || chartIntervalMarker.length() < 1)
                return null;
            
            String[] detail = chartIntervalMarker.split(",");
            if(detail.length<3)
                return null;
            
            int x,y;
            Color col;
            IntervalMarker intervalmarker = null;
            try
            {
                x = Integer.parseInt(detail[0]);
                y = Integer.parseInt(detail[1]);
                col = getColor(detail[2]);
                
                intervalmarker = new IntervalMarker(x, y);
                intervalmarker.setPaint(col);
            }
            catch(Exception e)
            {
                return null;
            }
            return intervalmarker;
        }
    }
    
    /*
     *
     * Category charts
     *
     */
    private static abstract class AbstactCategoryChartBuilder extends AbstactChartBuilder
    {
        protected abstract JFreeChart buildCategory(String title,
                String xAxis, String yAxis, CategoryDataset ds,
                PlotOrientation po, boolean legend, boolean tooltips, boolean is3d);
        
        public JFreeChart build(Chart chart, Dataset dataset)
        {
            Dataset ds = dataset;
            if (ds == null)
                ds = ChartDemo.getCategoryDataset();
            else if (ds instanceof ExternalDataset)
            {
                ((ExternalDataset)ds).initData(DatasetType.CATEGORY);
            }
            String title = chart.getTitle();
            String xAxis = chart.getXlabel();
            String yAxis = chart.getYlabel();
            PlotOrientation plotOrientation = getPlotOrientation(chart.getOrientation());
            boolean legend = chart.isLegend();
            boolean is3d = chart.isChart3d();
            boolean tooltips = chart.isTooltips();
            
            JFreeChart jfc = buildCategory(title, xAxis, yAxis,
                    (CategoryDataset)ds, plotOrientation, legend, tooltips, is3d);
            
            setBarOutlineAndColors(jfc, chart);
            
            CategoryPlot plot = jfc.getCategoryPlot();
            
            IntervalMarker mark = createIntervalMarker(chart);
            if ( mark != null )
                plot.addRangeMarker(mark, Layer.BACKGROUND);
            
            int labelAngle = chart.getLabelAngle() % 360;
            if (labelAngle != 0)
            {
                CategoryAxis categoryAxis = plot.getDomainAxis();
                if (labelAngle > 180)
                    labelAngle -= 360;
                else if (labelAngle < -180)
                    labelAngle += 360;
                if (Math.abs(labelAngle) > 90)
                    labelAngle %= 90;
                
                if (labelAngle < 0)
                    categoryAxis.setCategoryLabelPositions(
                            CategoryLabelPositions.createUpRotationLabelPositions(
                            Math.toRadians(-labelAngle)));
                else
                    categoryAxis.setCategoryLabelPositions(
                            CategoryLabelPositions.createDownRotationLabelPositions(
                            Math.toRadians(labelAngle)));
            }
            
            CategoryItemRenderer cir = plot.getRenderer();
            if (chart.isTooltips())
            {
                if (chart.isTooltipXDate())
                    cir.setBaseToolTipGenerator(
                            new ChartCategoryToolTipGenerator(chart.getTooltipFormat(),
                            chart.getDateFormat()));
                else
                    cir.setBaseToolTipGenerator(
                            new ChartCategoryToolTipGenerator(chart.getTooltipFormat()));
                
            }
            if (chart.isLegend())
            {
                String legendLabelFormat = chart.getLegendLabelFormat();
                if (legendLabelFormat != null && cir instanceof AbstractCategoryItemRenderer)
                {
                    AbstractCategoryItemRenderer acir = (AbstractCategoryItemRenderer)cir;
                    acir.setLegendItemLabelGenerator(
                            new StandardCategorySeriesLabelGenerator(legendLabelFormat));
                }
            }
            
            return jfc;
        }
        
        private void setBarOutlineAndColors(JFreeChart jfc, Chart chart)
        {
            CategoryPlot plot = jfc.getCategoryPlot();
            CategoryItemRenderer cir = plot.getRenderer();
            
            if (cir instanceof BarRenderer)
            {
                BarRenderer barRenderer = (BarRenderer)cir;
                barRenderer.setDrawBarOutline(chart.isOutline());
                if (chart.getGradient() != 100)
                {
                    String gradientType = chart.getGradientType();
                    if (gradientType.equals("horizontal"))
                    {
                        barRenderer.setGradientPaintTransformer(
                                new StandardGradientPaintTransformer(
                                GradientPaintTransformType.HORIZONTAL));
                    }
                    else if (gradientType.equals("centerVertical"))
                    {
                        barRenderer.setGradientPaintTransformer(
                                new StandardGradientPaintTransformer(
                                GradientPaintTransformType.CENTER_VERTICAL));
                    }
                    else if (gradientType.equals("centerHorizontal"))
                    {
                        barRenderer.setGradientPaintTransformer(
                                new StandardGradientPaintTransformer(
                                GradientPaintTransformType.CENTER_HORIZONTAL));
                    }
                }
            }
            
            String chartColors = chart.getColors();
            if (chartColors != null && chartColors.length()>0)
            {
                String[] colors = chartColors.split(",");
                for (int i=0; i<colors.length; i++)
                {
                    cir.setSeriesPaint(i, getColor(colors[i].trim()));
                }
            }
        }
    }
    
    private static class BarChartBuilder extends AbstactCategoryChartBuilder
    {
        protected JFreeChart buildCategory(String title,
                String xAxis, String yAxis, CategoryDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips, boolean is3d)
        {
            JFreeChart jfc = null;
            if (is3d)
            {
                jfc = ChartFactory.createBarChart3D(title, xAxis, yAxis,
                        dataset, plotOrientation, legend, tooltips, false);
            }
            else
            {
                jfc = ChartFactory.createBarChart(title, xAxis, yAxis,
                        dataset, plotOrientation, legend, tooltips, false);
            }
            return jfc;
        }
    }
    
    private static class StackedBarChartBuilder extends AbstactCategoryChartBuilder
    {
        protected JFreeChart buildCategory(String title,
                String xAxis, String yAxis, CategoryDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips, boolean is3d)
        {
            JFreeChart jfc = null;
            if (is3d)
            {
                jfc = ChartFactory.createStackedBarChart3D(title, xAxis, yAxis,
                        dataset, plotOrientation, legend, tooltips, false);
            }
            else
            {
                jfc = ChartFactory.createStackedBarChart(title, xAxis, yAxis,
                        dataset, plotOrientation, legend, tooltips, false);
            }
            return jfc;
        }
    }
    
    private static class LineChartBuilder extends AbstactCategoryChartBuilder
    {
        protected JFreeChart buildCategory(String title,
                String xAxis, String yAxis, CategoryDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips, boolean is3d)
        {
            JFreeChart jfc = null;
            if (is3d)
            {
                jfc = ChartFactory.createLineChart3D(title, xAxis, yAxis,
                        dataset, plotOrientation, legend, tooltips, false);
            }
            else
            {
                jfc = ChartFactory.createLineChart(title, xAxis, yAxis,
                        dataset, plotOrientation, legend, tooltips, false);
            }
            return jfc;
        }
    }
    
    private static class AreaChartBuilder extends AbstactCategoryChartBuilder
    {
        protected JFreeChart buildCategory(String title,
                String xAxis, String yAxis, CategoryDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips, boolean is3d)
        {
            JFreeChart jfc = ChartFactory.createAreaChart(title, xAxis, yAxis,
                    dataset, plotOrientation, legend, tooltips, false);
            return jfc;
        }
    }
    
    private static class StackedAreaChartBuilder extends AbstactCategoryChartBuilder
    {
        protected JFreeChart buildCategory(String title,
                String xAxis, String yAxis, CategoryDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips, boolean is3d)
        {
            JFreeChart jfc = ChartFactory.createStackedAreaChart(title, xAxis, yAxis,
                    dataset, plotOrientation, legend, tooltips, false);
            return jfc;
        }
    }
    
    private static class WaterfallChartBuilder extends AbstactCategoryChartBuilder
    {
        protected JFreeChart buildCategory(String title,
                String xAxis, String yAxis, CategoryDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips, boolean is3d)
        {
            JFreeChart jfc = ChartFactory.createWaterfallChart(title, xAxis, yAxis,
                    dataset, plotOrientation, legend, tooltips, false);
            return jfc;
        }
    }
    
    private static class GanttChartBuilder extends AbstactCategoryChartBuilder
    {
        protected JFreeChart buildCategory(String title,
                String xAxis, String yAxis, CategoryDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips, boolean is3d)
        {
            JFreeChart jfc = ChartFactory.createGanttChart(title, xAxis, yAxis,
                    (IntervalCategoryDataset)dataset, legend, tooltips, false);
            return jfc;
        }
    }
    
    private static class LayeredBarChartBuilder extends AbstactCategoryChartBuilder
    {
        protected JFreeChart buildCategory(String title,
                String xAxis, String yAxis, CategoryDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips, boolean is3d)
        {
            JFreeChart jfc = ChartFactory.createBarChart(title, xAxis, yAxis,
                    dataset, plotOrientation, legend, tooltips, false);
            CategoryPlot categoryplot = jfc.getCategoryPlot();
            LayeredBarRenderer layeredbarrenderer = new LayeredBarRenderer();
            categoryplot.setRenderer(layeredbarrenderer);
            categoryplot.setRowRenderingOrder(SortOrder.DESCENDING);
            return jfc;
        }
    }
    
    /*
     *
     * Pie charts
     *
     */
    private static abstract class AbstactPieChartBuilder extends AbstactChartBuilder
    {
        protected abstract JFreeChart buildPie(Chart chart, Dataset ds,
                String title, boolean legend, boolean tooltips, boolean is3d);
        
        public JFreeChart build(Chart chart, Dataset dataset)
        {
            Dataset ds = dataset;
            boolean isMultiPie = chart.getType().equalsIgnoreCase("multipie");
            if (ds == null)
                ds = isMultiPie ?
                    ChartDemo.getCategoryDataset() :
                    ChartDemo.getPieDataset();
            else if (ds instanceof ExternalDataset)
            {
                ((ExternalDataset)ds).initData(isMultiPie ?
                    DatasetType.CATEGORY : DatasetType.PIE);
            }
            
            String title = chart.getTitle();
            boolean legend = chart.isLegend();
            boolean tooltips = chart.isTooltips();
            
            JFreeChart jfc = buildPie(chart, ds, title, legend,
                    tooltips, chart.isChart3d());
            
            int limit = chart.getLimitPercent();
            
            PiePlot plot = null;
            Plot origPlot = jfc.getPlot();
            
            if (origPlot instanceof PiePlot)
            {
                plot = (PiePlot)origPlot;
                if (limit != 0)
                {
                    PieDataset pd = DatasetUtilities.
                            createConsolidatedPieDataset((PieDataset)ds,
                            "" + chart.getLimitPercentTitle(), limit/100.0D);
                    plot.setDataset(pd);
                }
                
                PieDataset pds = (PieDataset)ds;
                if (pds.getItemCount() > 0)
                {
                    plot.setExplodePercent(pds.getKey(0), 0.2);
                    setPieColors(plot, chart, pds);
                }
            }
            else if (origPlot instanceof MultiplePiePlot)
            {
                MultiplePiePlot multiplePiePlot = (MultiplePiePlot)origPlot;
                if (limit != 0)
                {
                    multiplePiePlot.setLimit(limit/100.0D);
                    multiplePiePlot.setAggregatedItemsKey("" + chart.getLimitPercentTitle());
                }
                plot = (PiePlot)multiplePiePlot.getPieChart().getPlot();
            }
            
            if (plot == null)
                return jfc;
            
            if (chart.isLabel())
            {
                String labelFormat = chart.getLabelFormat();
                if (labelFormat == null)
                    labelFormat = "{0} = {1}";
                plot.setLabelGenerator(new StandardPieSectionLabelGenerator(labelFormat));
            }
            else
            {
                plot.setLabelGenerator(null);
            }
            
            if (legend)
            {
                String labelFormat = chart.getLegendLabelFormat();
                if (labelFormat == null)
                    labelFormat = "{0} = {1}";
                plot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator(labelFormat));
            }
            
            if (tooltips)
            {
                plot.setToolTipGenerator(new ChartPieToolTipGenerator(chart.getTooltipFormat()));
            }
            
            int sa = chart.getStartAngle();
            if (sa != 90)
                plot.setStartAngle((float)sa);
            
            return jfc;
        }
        
        private void setPieColors(PiePlot plot, Chart chart, PieDataset ds)
        {
            String chartColors = chart.getColors();
            if ( chartColors != null && chartColors.length()>0 )
            {
                String[] colors = chartColors.split(",");
                int items = ds.getItemCount();
                for ( int i=0; i<colors.length && i<items; i++ )
                {
                    plot.setSectionPaint( ds.getKey(i), getColor( colors[i].trim() ) );
                }
            }
        }
        
    }
    
    private static class PieChartBuilder extends AbstactPieChartBuilder
    {
        protected JFreeChart buildPie(Chart chart, Dataset dataset,
                String title, boolean legend, boolean tooltips, boolean is3d)
        {
            JFreeChart jfc = null;
            PieDataset pieDataset = (PieDataset)dataset;
            if (is3d)
            {
                jfc = ChartFactory.createPieChart3D(title, pieDataset, legend,
                        tooltips, false);
                int depth = chart.getDepth();
                if (depth != 20)
                {
                    PiePlot3D plot = (PiePlot3D) jfc.getPlot();
                    plot.setDepthFactor((float)depth / 100.0);
                }
            }
            else
            {
                jfc = ChartFactory.createPieChart(title, pieDataset, legend,
                        tooltips, false);
            }
            return jfc;
        }
    }
    
    private static class MultiPieChartBuilder extends AbstactPieChartBuilder
    {
        protected JFreeChart buildPie(Chart chart, Dataset dataset,
                String title, boolean legend, boolean tooltips, boolean is3d)
        {
            JFreeChart jfc = null;
            if (is3d)
            {
                jfc = ChartFactory.createMultiplePieChart3D(title, (CategoryDataset)dataset,
                        chart.isCategoryToPieByRow() ? TableOrder.BY_ROW : TableOrder.BY_COLUMN,
                        legend, tooltips, false);
                int depth = chart.getDepth();
                if (depth != 20)
                {
                    MultiplePiePlot multiplePiePlot = (MultiplePiePlot)jfc.getPlot();
                    PiePlot3D plot = (PiePlot3D)multiplePiePlot.getPieChart().getPlot();
                    plot.setDepthFactor((float)depth / 100.0);
                }
            }
            else
            {
                jfc = ChartFactory.createMultiplePieChart(title, (CategoryDataset)dataset,
                        chart.isCategoryToPieByRow() ? TableOrder.BY_ROW : TableOrder.BY_COLUMN,
                        legend, tooltips, false);
            }
            return jfc;
        }
    }
    
    private static class RingChartBuilder extends AbstactPieChartBuilder
    {
        protected JFreeChart buildPie(Chart chart, Dataset dataset,
                String title, boolean legend, boolean tooltips, boolean is3d)
        {
            JFreeChart jfc = ChartFactory.createRingChart(title, (PieDataset)dataset, legend,
                    tooltips, false);
            return jfc;
        }
    }
    
    /*
     *
     * XY charts
     *
     */
    private static abstract class AbstactXYChartBuilder extends AbstactChartBuilder
    {
        protected abstract JFreeChart buildXY(String title,
                String xAxis, String yAxis, XYDataset ds,
                PlotOrientation po, boolean legend, boolean tooltips);
        
        private Class getPeriodClass(String format)
        {
            if (format.length() < 1)
                return org.jfree.data.time.Year.class;
            char fChar = format.charAt(0);
            switch( fChar )
            {
                case 'd':
                case 'D':
                case 'E':
                case 'F': return org.jfree.data.time.Day.class;
                case 'W':
                case 'w': return org.jfree.data.time.Week.class;
                case 'M': return org.jfree.data.time.Month.class;
                case 'H': return org.jfree.data.time.Hour.class;
                case 'm': return org.jfree.data.time.Minute.class;
                case 's': return org.jfree.data.time.Second.class;
                case 'Q': return org.jfree.data.time.Quarter.class;
            }
            return org.jfree.data.time.Year.class;
        }
        
        public JFreeChart build(Chart chart, Dataset dataset)
        {
            Dataset ds = dataset;
            if (ds == null)
                ds = ChartDemo.getXYDataset();
            else if (ds instanceof ExternalDataset)
            {
                ((ExternalDataset)ds).initData(DatasetType.XY);
            }
            String title = chart.getTitle();
            String xAxis = chart.getXlabel();
            String yAxis = chart.getYlabel();
            PlotOrientation plotOrientation =
                    getPlotOrientation(chart.getOrientation());
            boolean legend = chart.isLegend();
            boolean tooltips = chart.isTooltips();
            
            JFreeChart jfc = buildXY(title, xAxis, yAxis,
                    (XYDataset)ds, plotOrientation, legend, tooltips);
            
            XYPlot plot = jfc.getXYPlot();
            
            ValueAxis rangeAxis = plot.getRangeAxis();
            if (rangeAxis instanceof NumberAxis)
            {
                NumberAxis numberAxis = (NumberAxis)rangeAxis;
                double rangeNumberTick = chart.getRangeNumberTick();
                if (rangeNumberTick != 0.0)
                    numberAxis.setTickUnit(new NumberTickUnit(rangeNumberTick));
            }
            
            ValueAxis domainAxis = plot.getDomainAxis();
            
            domainAxis.setVerticalTickLabels(chart.isVerticalDateTicks());
            
            if (domainAxis instanceof NumberAxis)
            {
                NumberAxis numberAxis = (NumberAxis)domainAxis;
                numberAxis.setAutoRangeIncludesZero(chart.isIncludeZero());
                double domainNumberTick = chart.getDomainNumberTick();
                if (domainNumberTick != 0.0)
                    numberAxis.setTickUnit(new NumberTickUnit(domainNumberTick));
            }
            
            // Override date format and ticks
            if (domainAxis instanceof DateAxis)
            {
                String sDateTick = chart.getDateTick();

                String periodAxisFormat = chart.getPeriodAxisFormat();
                if (periodAxisFormat != null && periodAxisFormat.length() > 0)
                {
                    String[] bands = periodAxisFormat.split("[|]");
                    PeriodAxisLabelInfo[] periodAxisLabelInfo =
                            new PeriodAxisLabelInfo[bands.length];
                    
                    for (int i = 0; i < bands.length; i++)
                    {
                        String fmt = bands[i];
                        periodAxisLabelInfo[i] =
                                new PeriodAxisLabelInfo(getPeriodClass(fmt),
                                fmt.equals("Q") ?
                                    new QuarterDateFormat() :
                                    new SimpleDateFormat(fmt));
                    }
                    PeriodAxis periodAxis = new PeriodAxis(chart.getXlabel());
                    Class timePeriodClass = getPeriodClass(bands[0]);
                    if (sDateTick.equals("year"))
                        timePeriodClass = org.jfree.data.time.Year.class;
                    else if (sDateTick.equals("quarter"))
                        timePeriodClass =org.jfree.data.time.Quarter.class;
                    else if (sDateTick.equals("month"))
                        timePeriodClass = org.jfree.data.time.Month.class;
                    else if (sDateTick.equals("week"))
                        timePeriodClass = org.jfree.data.time.Week.class;
                    else if (sDateTick.equals("day"))
                        timePeriodClass = org.jfree.data.time.Day.class;
                    else if (sDateTick.equals("hour"))
                        timePeriodClass = org.jfree.data.time.Hour.class;

                    log.debug("DateTick=" + sDateTick + " timePeriodClass=" + timePeriodClass.getName());
                    periodAxis.setMajorTickTimePeriodClass(timePeriodClass);
                    periodAxis.setAutoRangeTimePeriodClass(timePeriodClass);

                    periodAxis.setLabelInfo(periodAxisLabelInfo);
//                    plot.setDomainGridlinePaint(getColor(chart.getBackground()));
//                    plot.setRangeGridlinePaint(getColor(chart.getBackground()));
                    plot.setDomainCrosshairVisible(true);
                    plot.setRangeCrosshairVisible(true);
                    plot.setDomainAxis(periodAxis);
                }
                else
                {
                    DateAxis dateaxis = (DateAxis)domainAxis;
                    dateaxis.setDateFormatOverride(
                            getChartDateFormat(chart.getDateFormat()));
                    
                    if (!sDateTick.equals("auto"))
                    {
                        int dateTick = DateTickUnit.YEAR;
                        int multiplier = 1;
                        if (sDateTick.equals("month"))
                            dateTick = DateTickUnit.MONTH;
                        else if (sDateTick.equals("quarter"))
                        {
                            dateTick = DateTickUnit.MONTH;
                            multiplier = 3;
                        }
                        else if (sDateTick.equals("day"))
                            dateTick = DateTickUnit.DAY;
                        else if (sDateTick.equals("week"))
                        {
                            dateTick = DateTickUnit.DAY;
                            multiplier = 7;
                        }
                        else if (sDateTick.equals("hour"))
                            dateTick = DateTickUnit.HOUR;
                        dateaxis.setTickUnit(new DateTickUnit(dateTick,
                                chart.getDateTickCount() * multiplier));
                    }
                }
            }
            
            XYItemRenderer xyitemrenderer = plot.getRenderer();
            
            //Colors
            setXYColors(xyitemrenderer, chart);
            
            //Dots on the graph
            if(xyitemrenderer instanceof XYLineAndShapeRenderer)
            {
                XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyitemrenderer;
                xylineandshaperenderer.setBaseShapesVisible(chart.isVisibleShapes());
                xylineandshaperenderer.setBaseShapesFilled(chart.isVisibleShapes());
                xylineandshaperenderer.setDefaultEntityRadius(6);
            }
            
            //Interval Marker setter
            IntervalMarker mark = createIntervalMarker(chart);
            if ( mark != null )
                plot.addRangeMarker(mark, Layer.BACKGROUND);
            
            if (tooltips)
            {
                String toolTipFormat = chart.getTooltipFormat();
                if (chart.isTooltipXDate() && chart.isTooltipYDate())
                    xyitemrenderer.setBaseToolTipGenerator(
                            new ChartXYToolTipGenerator(toolTipFormat,
                            chart.getDateFormat(), chart.getDateFormat()));
                else if (chart.isTooltipXDate())
                    xyitemrenderer.setBaseToolTipGenerator(
                            new ChartXYToolTipGenerator(toolTipFormat,
                            chart.getDateFormat()));
                else
                    xyitemrenderer.setBaseToolTipGenerator(
                            new ChartXYToolTipGenerator(toolTipFormat));
            }
            
            if (legend)
            {
                String legendLabelFormat = chart.getLegendLabelFormat();
                if (legendLabelFormat != null && xyitemrenderer instanceof AbstractXYItemRenderer)
                {
                    AbstractXYItemRenderer aXYir = (AbstractXYItemRenderer)xyitemrenderer;
                    aXYir.setLegendItemLabelGenerator(
                            new StandardXYSeriesLabelGenerator(legendLabelFormat));
                }
            }
            
            return jfc;
        }
        
        private void setXYColors(XYItemRenderer plot, Chart chart)
        {
            String chartColors = chart.getColors();
            if ( chartColors != null && chartColors.length()>0 )
            {
                String[] colors = chartColors.split(",");
                for ( int i=0; i<colors.length; i++ )
                {
                    plot.setSeriesPaint( i, getColor( colors[i].trim() ) );
                }
            }
        }
    }
    
    private static class TimeSeriesChartBuilder extends AbstactXYChartBuilder
    {
        protected JFreeChart buildXY(String title,
                String xAxis, String yAxis, XYDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips)
        {
            JFreeChart jfc = ChartFactory.createTimeSeriesChart(title, xAxis, yAxis, dataset,
                    legend, tooltips, false);
            return jfc;
        }
    }
    
    private static class XYLineChartBuilder extends AbstactXYChartBuilder
    {
        protected JFreeChart buildXY(String title,
                String xAxis, String yAxis, XYDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips)
        {
            JFreeChart jfc = ChartFactory.createXYLineChart(title, xAxis, yAxis, dataset,
                    plotOrientation, legend, tooltips, false);
            return jfc;
        }
    }
    
    private static class XYBarChartBuilder extends AbstactXYChartBuilder
    {
        protected JFreeChart buildXY(String title,
                String xAxis, String yAxis, XYDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips)
        {
            JFreeChart jfc = ChartFactory.createXYBarChart(title, xAxis,
                    dataset instanceof TimeSeriesCollection,
                    yAxis, (IntervalXYDataset)dataset,
                    plotOrientation, legend, tooltips, false);
            return jfc;
        }
    }
    
    private static class PolarChartBuilder extends AbstactXYChartBuilder
    {
        protected JFreeChart buildXY(String title,
                String xAxis, String yAxis, XYDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips)
        {
            JFreeChart jfc = ChartFactory.createPolarChart(title, dataset,
                    legend, tooltips, false);
            return jfc;
        }
    }
    
    private static class ScatterChartBuilder extends AbstactXYChartBuilder
    {
        protected JFreeChart buildXY(String title,
                String xAxis, String yAxis, XYDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips)
        {
            JFreeChart jfc = ChartFactory.createScatterPlot(title, xAxis, yAxis, dataset,
                    plotOrientation, legend, tooltips, false);
            return jfc;
        }
    }
    
    private static class XYAreaChartBuilder extends AbstactXYChartBuilder
    {
        protected JFreeChart buildXY(String title,
                String xAxis, String yAxis, XYDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips)
        {
            JFreeChart jfc = ChartFactory.createXYAreaChart(title, xAxis, yAxis, dataset,
                    plotOrientation, legend, tooltips, false);
            return jfc;
        }
    }
    
    private static class XYStepAreaChartBuilder extends AbstactXYChartBuilder
    {
        protected JFreeChart buildXY(String title,
                String xAxis, String yAxis, XYDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips)
        {
            JFreeChart jfc = ChartFactory.createXYStepAreaChart(title, xAxis, yAxis, dataset,
                    plotOrientation, legend, tooltips, false);
            return jfc;
        }
    }
    
    private static class XYStepChartBuilder extends AbstactXYChartBuilder
    {
        protected JFreeChart buildXY(String title,
                String xAxis, String yAxis, XYDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips)
        {
            JFreeChart jfc = ChartFactory.createXYStepChart(title, xAxis, yAxis, dataset,
                    plotOrientation, legend, tooltips, false);
            return jfc;
        }
    }
    
    private static class BubbleChartBuilder extends AbstactXYChartBuilder
    {
        protected JFreeChart buildXY(String title,
                String xAxis, String yAxis, XYDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips)
        {
            JFreeChart jfc = ChartFactory.createBubbleChart(title, xAxis, yAxis,
                    (XYZDataset)dataset,
                    plotOrientation, legend, tooltips, false);
            return jfc;
        }
    }
    
    private static class HistogramChartBuilder extends AbstactXYChartBuilder
    {
        protected JFreeChart buildXY(String title,
                String xAxis, String yAxis, XYDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips)
        {
            JFreeChart jfc = ChartFactory.createHistogram(title, xAxis, yAxis,
                    (IntervalXYDataset) dataset,
                    plotOrientation, legend, tooltips, false);
            return jfc;
        }
    }
    
    private static class CandleChartBuilder extends AbstactXYChartBuilder
    {
        protected JFreeChart buildXY(String title,
                String xAxis, String yAxis, XYDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips)
        {
            JFreeChart jfc = ChartFactory.createCandlestickChart(title, xAxis, yAxis,
                    (OHLCDataset)dataset, legend);
            return jfc;
        }
    }
    
    private static class HighLowChartBuilder extends AbstactXYChartBuilder
    {
        protected JFreeChart buildXY(String title,
                String xAxis, String yAxis, XYDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips)
        {
            JFreeChart jfc = ChartFactory.createHighLowChart(title, xAxis, yAxis,
                    (OHLCDataset)dataset, legend);
            return jfc;
        }
    }
    
    private static class WindChartBuilder extends AbstactXYChartBuilder
    {
        protected JFreeChart buildXY(String title,
                String xAxis, String yAxis, XYDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips)
        {
            JFreeChart jfc = ChartFactory.createWindPlot(title, xAxis, yAxis,
                    (WindDataset) dataset, legend, tooltips, false);
            return jfc;
        }
    }
    
    private static class BoxAndWhiskerChartBuilder extends AbstactXYChartBuilder
    {
        protected JFreeChart buildXY(String title,
                String xAxis, String yAxis, XYDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips)
        {
            JFreeChart jfc = ChartFactory.createBoxAndWhiskerChart(title, xAxis, yAxis,
                    (BoxAndWhiskerXYDataset) dataset, legend);
            return jfc;
        }
    }
    
    private static class TimeSeriesDiffChartBuilder extends AbstactXYChartBuilder
    {
        protected JFreeChart buildXY(String title,
                String xAxis, String yAxis, XYDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips)
        {
            JFreeChart jfc = ChartFactory.createTimeSeriesChart(title, xAxis, yAxis, dataset,
                    legend, tooltips, false);
            jfc.getXYPlot().setRenderer(new XYDifferenceRenderer());
            return jfc;
        }
    }
    
    private static class XYLineDiffChartBuilder extends AbstactXYChartBuilder
    {
        protected JFreeChart buildXY(String title,
                String xAxis, String yAxis, XYDataset dataset,
                PlotOrientation plotOrientation, boolean legend, boolean tooltips)
        {
            JFreeChart jfc = ChartFactory.createXYLineChart(title, xAxis, yAxis, dataset,
                    plotOrientation, legend, tooltips, false);
            jfc.getXYPlot().setRenderer(new XYDifferenceRenderer());
            return jfc;
        }
    }
    
    /** Creates a new instance of ChartBuilder */
    private ChartBuilder()
    {
        charts = new HashMap<String, AbstactChartBuilder>();
    }
    
    /** Holds all chart builders by type */
    private Map<String, AbstactChartBuilder> charts;
    
    /** Registers all charts in instance */
    private void register()
    {
        charts.put("bar",
                new BarChartBuilder());
        charts.put("stackedbar",
                new StackedBarChartBuilder());
        charts.put("line",
                new LineChartBuilder());
        charts.put("area",
                new AreaChartBuilder());
        charts.put("stackedarea",
                new StackedAreaChartBuilder());
        charts.put("waterfall",
                new WaterfallChartBuilder());
        charts.put("gantt",
                new GanttChartBuilder());
        charts.put("layeredbar",
                new LayeredBarChartBuilder());
        charts.put("pie",
                new PieChartBuilder());
        charts.put("multipie",
                new MultiPieChartBuilder());
        charts.put("ring",
                new RingChartBuilder());
        charts.put("timeseries",
                new TimeSeriesChartBuilder());
        charts.put("xyline",
                new XYLineChartBuilder());
        charts.put("xybar",
                new XYBarChartBuilder());
        charts.put("polar",
                new PolarChartBuilder());
        charts.put("scatter",
                new ScatterChartBuilder());
        charts.put("xyarea",
                new XYAreaChartBuilder());
        charts.put("xysteparea",
                new XYStepAreaChartBuilder());
        charts.put("xystep",
                new XYStepChartBuilder());
        charts.put("xytimediff",
                new TimeSeriesDiffChartBuilder());
        charts.put("xylinediff",
                new XYLineDiffChartBuilder());
        charts.put("bubble",
                new BubbleChartBuilder());
        charts.put("candlestick",
                new CandleChartBuilder());
        charts.put("boxandwhisker",
                new BoxAndWhiskerChartBuilder());
        charts.put("highlow",
                new HighLowChartBuilder());
        charts.put("histogram",
                new HistogramChartBuilder());
        charts.put("wind",
                new WindChartBuilder());
    }
    
    private AbstactChartBuilder getChart(String type)
    {
        return charts.get(type);
    }
    
    /** Singleton instance */
    private static ChartBuilder instance = null;
    
    /** gets instance */
    private static ChartBuilder getInstance()
    {
        if (instance == null)
        {
            instance = new ChartBuilder();
            instance.register();
        }
        return instance;
    }
    
    /**
     * Builds chart by chart type
     * @param chart Chart descriptions and parameters
     * @param dataset Dataset for chart
     * @return JFreeChart
     */
    public static JFreeChart buildChart(Chart chart, Dataset dataset)
    {
        if (chart == null)
            return null;
        
        AbstactChartBuilder acb = getInstance().getChart(chart.getType().toLowerCase());
        
        if (acb == null)
            return null;
        
        if (log.isTraceEnabled())
            log.trace("buildChart chart: " + chart + " dataset: " + dataset);
        
        JFreeChart jfc = acb.build(chart, dataset);
        jfc.setBackgroundPaint(
                AbstactChartBuilder.getColor(chart.getBackground()));
        jfc.setAntiAlias(chart.isAntialias());
        jfc.setBorderVisible(chart.getBorder() > 0);
        
        Plot plot = jfc.getPlot();
        
        plot.setNoDataMessage(L10nUtil.getInstance().getMessage(Chart.class, "Chart.noData"));
        
        // Alpha transparency (100% means opaque)
        int alpha = chart.getAlpha();
        if (alpha < 100)
        {
            plot.setForegroundAlpha(
                    (float)( alpha / 100.0 ));
        }
        
        int gradient = chart.getGradient();
        if (gradient != 100)
        {
            plot.setDrawingSupplier(new GradientDrawingSupplier(gradient));
        }
        
        if (chart.isLegend())
        {
            String legendPos = chart.getLegendPosition();
            if (!legendPos.equals("bottom"))
            {
                LegendTitle lt = jfc.getLegend();
                if (legendPos.equals("right"))
                    lt.setPosition(RectangleEdge.RIGHT);
                else if (legendPos.equals("top"))
                    lt.setPosition(RectangleEdge.TOP);
                else if (legendPos.equals("left"))
                    lt.setPosition(RectangleEdge.LEFT);
            }
        }
        
        String subtitle = chart.getSubtitle();
        if (subtitle != null)
            jfc.addSubtitle(new TextTitle(subtitle));
        
        return jfc;
    }
}
