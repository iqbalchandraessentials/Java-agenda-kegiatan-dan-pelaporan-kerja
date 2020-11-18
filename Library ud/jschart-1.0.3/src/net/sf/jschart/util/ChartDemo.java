/*
 * ChartDemo.java
 *
 * Created on 11 July 2006, 10:54
 */

package net.sf.jschart.util;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYZDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYZDataset;

/**
 *
 * @author als
 */
public class ChartDemo
{
    
    public static DefaultCategoryDataset getCategoryDataset()
    {
        DefaultCategoryDataset categoryDataSet;
        
        // row keys...
        String series1 = "First";
        String series2 = "Second";
        String series3 = "Third";
        // column keys...
        String category1 = "A";
        String category2 = "B";
        String category3 = "C";
        String category4 = "D";
        String category5 = "E";
        
        // create the dataset...
        categoryDataSet = new DefaultCategoryDataset();
        categoryDataSet.addValue(1.0, series1, category1);
        categoryDataSet.addValue(4.0, series1, category2);
        categoryDataSet.addValue(3.0, series1, category3);
        categoryDataSet.addValue(5.0, series1, category4);
        categoryDataSet.addValue(5.0, series1, category5);
        categoryDataSet.addValue(5.0, series2, category1);
        categoryDataSet.addValue(7.0, series2, category2);
        categoryDataSet.addValue(6.0, series2, category3);
        categoryDataSet.addValue(8.0, series2, category4);
        categoryDataSet.addValue(4.0, series2, category5);
        categoryDataSet.addValue(4.0, series3, category1);
        categoryDataSet.addValue(3.0, series3, category2);
        categoryDataSet.addValue(2.0, series3, category3);
        categoryDataSet.addValue(3.0, series3, category4);
        categoryDataSet.addValue(6.0, series3, category5);
        return categoryDataSet;
        
    }
    
    public static DefaultPieDataset getPieDataset()
    {
        DefaultPieDataset pieDataSet = new DefaultPieDataset();
        pieDataSet.setValue("A", 52);
        pieDataSet.setValue("B", 18);
        pieDataSet.setValue("C", 30);
        return pieDataSet;
    }
    
    //Returns an implementation of an xy dataset
    public static XYDataset getXYDataset()
    {
        TimeSeriesCollection timeSeriesDataSet = new TimeSeriesCollection();
        TimeSeries s1 = new TimeSeries("1", Month.class);
        s1.add(new Month(2, 2006), 181.8);
        s1.add(new Month(3, 2006), 167.3);
        s1.add(new Month(4, 2006), 153.8);
        s1.add(new Month(5, 2006), 167.6);
        s1.add(new Month(6, 2006), 158.8);
        s1.add(new Month(7, 2006), 148.3);
        s1.add(new Month(8, 2006), 153.9);
        s1.add(new Month(9, 2006), 142.7);
        s1.add(new Month(10, 2006), 123.2);
        s1.add(new Month(11, 2006), 131.8);
        s1.add(new Month(12, 2006), 139.6);
        s1.add(new Month(1, 2007), 142.9);
        s1.add(new Month(2, 2007), 138.7);
        s1.add(new Month(3, 2007), 137.3);
        s1.add(new Month(4, 2007), 143.9);
        s1.add(new Month(5, 2007), 139.8);
        s1.add(new Month(6, 2007), 137.0);
        s1.add(new Month(7, 2007), 132.8);
        
        TimeSeries s2 = new TimeSeries("2", Month.class);
        s2.add(new Month(2, 2006), 129.6);
        s2.add(new Month(3, 2006), 123.2);
        s2.add(new Month(4, 2006), 117.2);
        s2.add(new Month(5, 2006), 124.1);
        s2.add(new Month(6, 2006), 122.6);
        s2.add(new Month(7, 2006), 119.2);
        s2.add(new Month(8, 2006), 116.5);
        s2.add(new Month(9, 2006), 112.7);
        s2.add(new Month(10, 2006), 101.5);
        s2.add(new Month(11, 2006), 106.1);
        s2.add(new Month(12, 2006), 110.3);
        s2.add(new Month(1, 2007), 111.7);
        s2.add(new Month(2, 2007), 111.0);
        s2.add(new Month(3, 2007), 109.6);
        s2.add(new Month(4, 2007), 113.2);
        s2.add(new Month(5, 2007), 111.6);
        s2.add(new Month(6, 2007), 108.8);
        s2.add(new Month(7, 2007), 101.6);
        timeSeriesDataSet.addSeries(s1);
        timeSeriesDataSet.addSeries(s2);
        return timeSeriesDataSet;
    }
    
}
