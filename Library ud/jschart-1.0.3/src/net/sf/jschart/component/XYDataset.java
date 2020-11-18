/*
 * XYDataset.java
 *
 * Created on 9 Oct 2006, 14:20
 */

package net.sf.jschart.component;
import com.sun.data.provider.FieldKey;
import com.sun.data.provider.RowKey;
import com.sun.data.provider.TableDataProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.data.DomainInfo;
import org.jfree.data.Range;
import org.jfree.data.RangeInfo;
import org.jfree.data.xy.DefaultXYDataset;
import net.sf.jschart.component.ExternalDataset.DatasetType;
import net.sf.jschart.util.L10nUtil;
import net.sf.jschart.util.NumberUtils;

/**
 *
 * @author als
 */
public class XYDataset extends DefaultXYDataset
        implements DomainInfo, RangeInfo, ExternalDataset
{
    private static Log log = LogFactory.getLog(XYDataset.class);
    
    /** The maximum x value of the returned result set */
    private double maxDomainValue = 0.0;
    
    /** The minimum x value of the returned result set */
    private double minDomainValue = 0.0;
    
    /** The maximum y value of the returned result set */
    private double maxRangeValue = 0.0;
    
    /** The minimum y value of the returned result set */
    private double minRangeValue = 0.0;
    
    /** Creates a new instance of PieDataset */
    public XYDataset()
    {
    }
    
    // byColumn
    private boolean byColumn = false;
    
    /**
     * <p>Sets a flag that controls whether or not the
     *    data gets by columns or by rows. If by columns then dataset
     *    should have as many columns as you want to have plots +1.
     *    First column is xValue other corresponding yValue.
     *    If by rows then dataset should have three column: 1 xValue,
     *    2 series, 3 value.</p>
     */
    public boolean isByColumn()
    {
        return this.byColumn;
    }
    
    /**
     * <p>Sets a flag that controls whether or not the
     *    data gets by columns or by rows. If by columns then dataset
     *    should have as many columns as you want to have plots +1.
     *    First column is xValue other corresponding yValue.
     *    If by rows then dataset should have three column: 1 xValue,
     *    2 series, 3 value.</p>
     * @see #isByColumn()
     */
    public void setByColumn(boolean byColumn)
    {
        this.byColumn = byColumn;
    }
    
    // dataProvider
    private com.sun.data.provider.DataProvider dataProvider = null;
    
    /**
     * <p>The wrapped data provider.</p>
     */
    public com.sun.data.provider.DataProvider getDataProvider()
    {
        return this.dataProvider;
    }
    
    /**
     * <p>The wrapped data provider.</p>
     * @see #getDataProvider()
     */
    public void setDataProvider(com.sun.data.provider.DataProvider dataProvider)
    {
        this.dataProvider = dataProvider;
    }
    
    /**
     * Returns the minimum x-value in the dataset.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         x-interval is taken into account.
     *
     * @return The minimum value.
     */
    public double getDomainLowerBound(boolean includeInterval)
    {
        return minDomainValue;
    }
    
    /**
     * Returns the maximum x-value in the dataset.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         x-interval is taken into account.
     *
     * @return The maximum value.
     */
    public double getDomainUpperBound(boolean includeInterval)
    {
        return maxDomainValue;
    }
    
    /**
     * Returns the range of the values in this dataset's domain.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         x-interval is taken into account.
     *
     * @return The range.
     */
    public Range getDomainBounds(boolean includeInterval)
    {
        return new Range(minDomainValue, maxDomainValue);
    }
    
    /**
     * Returns the minimum y-value in the dataset.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     *
     * @return The minimum value.
     */
    public double getRangeLowerBound(boolean includeInterval)
    {
        return minRangeValue;
    }
    
    /**
     * Returns the maximum y-value in the dataset.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     *
     * @return The maximum value.
     */
    public double getRangeUpperBound(boolean includeInterval)
    {
        return maxRangeValue;
    }
    
    /**
     * Returns the range of the values in this dataset's range.
     *
     * @param includeInterval  a flag that determines whether or not the
     *                         y-interval is taken into account.
     *
     * @return The range.
     */
    public Range getRangeBounds(boolean includeInterval)
    {
        return new Range(minRangeValue, maxRangeValue);
    }
    
    /**
     * Method called when dataprovider has changed
     */
    public void initData(DatasetType datasetType)
    {
        log.trace("XYDataset.initData: Begin");
        
        if (datasetType != DatasetType.XY)
            throw new IllegalArgumentException("Invalid dataset type");
        
        try
        {
            if (dataProvider != null)
            {
                if (dataProvider instanceof TableDataProvider)
                {
                    TableDataProvider tdp = (TableDataProvider)dataProvider;
                    int rowCount = tdp.getRowCount();
                    
                    if (log.isTraceEnabled())
                    {
                        FieldKey[] cols = tdp.getFieldKeys();
                        StringBuffer sb = new StringBuffer();
                        sb.append("XYDataset.initData: TableDataProvider ");
                        sb.append(rowCount);
                        sb.append(" rows ");
                        sb.append(cols.length);
                        sb.append(" cols");
                        log.trace(sb.toString());
                    }
                    
                    if (isByColumn())
                    {
                        fillByColumn(tdp);
                    }
                    else
                    {
                        fillByRow(tdp);
                    }
                }
                else
                {
                    log.error("XYDataset.initData: Only TableDataProvider supported");
                }
            }
            else
            {
                log.trace("XYDataset.initData: NULL Dataprovider");
            }
        }
        catch (Exception e)
        {
            log.error("initData: ", e);
            throw new RuntimeException("XYDataset unexpected", e);
        }
        
        log.trace("XYDataset.initData: End");
    }
    
    private void fillByRow(TableDataProvider tdp) throws Exception
    {
        log.trace("fillByRow: Begin");
        
        Locale locale = L10nUtil.getLocale();
        
        // Get all columns
        FieldKey[] columns = tdp.getFieldKeys();
        if (columns.length < 3)
            throw new IllegalArgumentException("Invalid columns number");
        
        // Get all rows.
        RowKey allRows[] = tdp.getRowKeys(Integer.MAX_VALUE, null);
        
        maxDomainValue = Double.NEGATIVE_INFINITY;
        minDomainValue = Double.POSITIVE_INFINITY;
        maxRangeValue = Double.NEGATIVE_INFINITY;
        minRangeValue = Double.POSITIVE_INFINITY;
        
        List<Comparable> seriesList = new ArrayList<Comparable>();
        
        Map<Comparable,List<double[]>> seriesMap =
                new TreeMap<Comparable,List<double[]>>();
        
        for (RowKey rk : allRows)
        {
            Comparable series = (Comparable)tdp.getValue(columns[0], rk);
            Number xValue = NumberUtils.createNumber(locale, tdp.getValue(columns[1], rk));
            Number yValue = NumberUtils.createNumber(locale, tdp.getValue(columns[2], rk));
            
            double x = xValue.doubleValue();
            double y = yValue.doubleValue();
            
            List<double[]> data = seriesMap.get(series);
            
            if (data == null)
            {
                data = new ArrayList<double[]>();
                seriesMap.put(series, data);
                seriesList.add(series);
            }
            
            data.add(new double[]{ x, y });
            
            // min & max
            if (x < minDomainValue)
            {
                minDomainValue = x;
            }
            if (x > maxDomainValue)
            {
                maxDomainValue = x;
            }
            if (y < minRangeValue)
            {
                minRangeValue = y;
            }
            if (y > maxRangeValue)
            {
                maxRangeValue = y;
            }
        }
        
        for (Iterator<Comparable> it = seriesList.iterator(); it.hasNext();)
        {
            Comparable serie = it.next();
            List<double[]> values = seriesMap.get(serie);
            int size = values.size();
            double[][] v = new double[2][size];
            for (int i=0; i<size; i++)
            {
                double[] xy = values.get(i);
                v[0][i] = xy[0];
                v[1][i] = xy[1];
            }
            addSeries(serie, v);
        }
        
        if (seriesMap.size() == 0)
        {
            minDomainValue = 0.0;
            maxDomainValue = 1.0;
            minRangeValue = 0.0;
            maxRangeValue = 1.0;
        }
        
        if (lowerRangeBound != null)
            minRangeValue = lowerRangeBound.doubleValue();
        
        if (upperRangeBound != null)
            maxRangeValue = upperRangeBound.doubleValue();
        
        log.trace("fillByRow: End");
    }
    
    private void fillByColumn(TableDataProvider tdp) throws Exception
    {
        log.trace("fillByColumn: Begin");
        
        Locale locale = L10nUtil.getLocale();
        // Get all columns
        FieldKey[] columns = tdp.getFieldKeys();
        if (columns.length < 2)
            throw new IllegalArgumentException("Invalid columns number");
        
        // Get all rows.
        RowKey allRows[] = tdp.getRowKeys(Integer.MAX_VALUE, null);
        
        maxDomainValue = Double.NEGATIVE_INFINITY;
        minDomainValue = Double.POSITIVE_INFINITY;
        maxRangeValue = Double.NEGATIVE_INFINITY;
        minRangeValue = Double.POSITIVE_INFINITY;
        
        List<Comparable> seriesList = new ArrayList<Comparable>();
        
        Map<Comparable,List<double[]>> seriesMap =
                new TreeMap<Comparable,List<double[]>>();
        
        for (RowKey rk : allRows)
        {
            Number xValue = NumberUtils.createNumber(locale, tdp.getValue(columns[0], rk));
            double x = xValue.doubleValue();
            
            if (x < minDomainValue)
            {
                minDomainValue = x;
            }
            if (x > maxDomainValue)
            {
                maxDomainValue = x;
            }
            
            for (int fieldIdx = 1; fieldIdx < columns.length; fieldIdx++)
            {
                Number yValue = NumberUtils.createNumber(locale,
                        tdp.getValue(columns[fieldIdx], rk));
                
                double y = yValue.doubleValue();
                
                String serie = columns[fieldIdx].getDisplayName();
                
                List<double[]> data = seriesMap.get(serie);
                
                if (data == null)
                {
                    data = new ArrayList<double[]>();
                    seriesMap.put(serie, data);
                    seriesList.add(serie);
                }
                
                data.add(new double[]{ x, y });
                
                if (y < minRangeValue)
                {
                    minRangeValue = y;
                }
                if (y > maxRangeValue)
                {
                    maxRangeValue = y;
                }
            }
            
            for (Iterator<Comparable> it = seriesList.iterator(); it.hasNext();)
            {
                Comparable serie = it.next();
                List<double[]> values = seriesMap.get(serie);
                int size = values.size();
                double[][] v = new double[2][size];
                for (int i=0; i<size; i++)
                {
                    double[] xy = values.get(i);
                    v[0][i] = xy[0];
                    v[1][i] = xy[1];
                }
                addSeries(serie, v);
            }
            
            if (seriesMap.size() == 0)
            {
                minDomainValue = 0.0;
                maxDomainValue = 1.0;
                minRangeValue = 0.0;
                maxRangeValue = 1.0;
            }
            
            if (lowerRangeBound != null)
                minRangeValue = lowerRangeBound.doubleValue();
            
            if (upperRangeBound != null)
                maxRangeValue = upperRangeBound.doubleValue();
        }
        
        log.trace("fillByColumn: End");
    }
    
    /**
     * Holds value of property lowerRangeBound.
     */
    private java.lang.Double lowerRangeBound = null;
    
    /**
     * Getter for property lowerRangeBound.
     * @return Value of property lowerRangeBound.
     */
    public java.lang.Double getLowerRangeBound()
    {
        return this.lowerRangeBound;
    }
    
    /**
     * Setter for property lowerRangeBound.
     * @param lowerRangeBound New value of property lowerRangeBound.
     */
    public void setLowerRangeBound(java.lang.Double lowerRangeBound)
    {
        this.lowerRangeBound = lowerRangeBound;
    }
    
    /**
     * Holds value of property upperRangeBound.
     */
    private java.lang.Double upperRangeBound = null;
    
    /**
     * Getter for property upperRangeBound.
     * @return Value of property upperRangeBound.
     */
    public java.lang.Double getUpperRangeBound()
    {
        return this.upperRangeBound;
    }
    
    /**
     * Setter for property upperRangeBound.
     * @param upperRangeBound New value of property upperRangeBound.
     */
    public void setUpperRangeBound(java.lang.Double upperRangeBound)
    {
        this.upperRangeBound = upperRangeBound;
    }
}
