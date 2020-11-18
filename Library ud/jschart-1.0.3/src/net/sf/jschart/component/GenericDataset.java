/*
 * GenericDataset.java
 *
 * Created on 15 Feb 2007, 15:46
 */

package net.sf.jschart.component;

import com.sun.data.provider.FieldKey;
import com.sun.data.provider.RowKey;
import com.sun.data.provider.TableDataProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.DomainInfo;
import org.jfree.data.Range;
import org.jfree.data.RangeInfo;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.AbstractXYDataset;
import net.sf.jschart.component.ExternalDataset.DatasetType;
import net.sf.jschart.util.L10nUtil;
import net.sf.jschart.util.NumberUtils;

/**
 *
 * @author als
 */
public class GenericDataset extends AbstractXYDataset
        implements
        org.jfree.data.category.CategoryDataset,
        org.jfree.data.general.PieDataset,
        org.jfree.data.xy.XYDataset,
        DomainInfo, RangeInfo,
        ExternalDataset
{
    private static Log log = LogFactory.getLog(GenericDataset.class);
    
    /** Columns. */
    private FieldKey[] columns;
    
    private FieldKey categoryColumn;
    private FieldKey seriesColumn;
    private FieldKey keyColumn;
    private FieldKey rowColumn;
    private FieldKey domainColumn;
    private FieldKey valueColumn;
    
    /** Series. */
    private List<Comparable> seriesList = null;
    
    /** Storage for Pie dataset */
    private DefaultKeyedValues pieValues = null;
    
    /** Storage for Category dataset */
    private DefaultKeyedValues2D categoryValues = null;
    
    /** Storage for XY dataset*/
    private Map<Comparable, List<Number>> xValues = null;
    private Map<Comparable, List<Number>> yValues = null;
    
    /** The maximum x value of the returned result set */
    private double maxDomainValue = 0.0;
    
    /** The minimum x value of the returned result set */
    private double minDomainValue = 0.0;
    
    /** The maximum y value of the returned result set */
    private double maxRangeValue = 0.0;
    
    /** The minimum y value of the returned result set */
    private double minRangeValue = 0.0;
    
    private DatasetType datasetType;
    
    /** Creates a new instance of GenericDataset */
    public GenericDataset()
    {
    }
    
    public void initData(DatasetType datasetType)
    {
        log.trace("GenericDataset.initData: Begin " + datasetType);
        this.datasetType = datasetType;
        try
        {
            TableDataProvider tdp = getDataProvider();
            if (tdp != null)
            {
                int rowCount = tdp.getRowCount();
                
                if (log.isTraceEnabled())
                {
                    FieldKey[] cols = tdp.getFieldKeys();
                    StringBuffer sb = new StringBuffer();
                    sb.append("GenericDataset.initData: TableDataProvider ");
                    sb.append(rowCount);
                    sb.append(" rows ");
                    sb.append(cols.length);
                    sb.append(" cols");
                    log.trace(sb.toString());
                }
                
                Locale locale = L10nUtil.getLocale();
                
                // Get all columns
                columns = dataProvider.getFieldKeys();
                
                valueColumn = valueField == null ? columns[2]
                        : new FieldKey(valueField);
                
                switch(datasetType)
                {
                    case PIE:
                        fillPie(tdp, locale);
                        break;
                        
                    case CATEGORY:
                        fillCategory(tdp, locale);
                        break;
                        
                    case XY:
                        fillXY(tdp, locale);
                        break;
                }
            }
            else
            {
                log.trace("GenericDataset.initData: NULL Dataprovider");
            }
        }
        catch (Exception e)
        {
            log.error("initData: ", e);
            throw new RuntimeException("GenericDataset unexpected: " + e.getMessage());
        }
        
        log.trace("GenericDataset.initData: End");
    }
    
    private void fillPie(TableDataProvider tdp, Locale locale) throws Exception
    {
        pieValues = new DefaultKeyedValues();
        
        keyColumn = keyField == null ? columns[0]
                : new FieldKey(keyField);
        
        for (RowKey rk : tdp.getRowKeys(Integer.MAX_VALUE, null))
        {
            Comparable key = (Comparable)tdp.getValue(keyColumn, rk);
            Number nValue = NumberUtils.createNumber(locale, tdp.getValue(valueColumn,rk));
            int idx = pieValues.getIndex(key);
            if (idx != -1)
            {
                Number oldValue = pieValues.getValue(idx);
                nValue = new Double(nValue.doubleValue() + oldValue.doubleValue());
            }
            pieValues.setValue(key,nValue);
        }
    }
    
    private void fillCategory(TableDataProvider tdp, Locale locale) throws Exception
    {
        categoryValues = new DefaultKeyedValues2D();
        
        rowColumn = rowField == null ? columns[0]
                : new FieldKey(rowField);
        categoryColumn = categoryField == null ? columns[1]
                : new FieldKey(categoryField);
        
        maxRangeValue = Double.NEGATIVE_INFINITY;
        minRangeValue = Double.POSITIVE_INFINITY;

        for (RowKey rk : tdp.getRowKeys(Integer.MAX_VALUE, null))
        {
            Comparable row_key = (Comparable)tdp.getValue(rowColumn, rk);
            Comparable cat_key = (Comparable)tdp.getValue(categoryColumn, rk);
            Number nValue = NumberUtils.createNumber(locale, tdp.getValue(valueColumn,rk));
            categoryValues.addValue(nValue, row_key, cat_key);

            double val = nValue.doubleValue();
            if (val < minRangeValue)
            {
                minRangeValue = val;
            }
            if (val > maxRangeValue)
            {
                maxRangeValue = val;
            }
        }

        if (categoryValues.getRowCount() == 0)
        {
            minRangeValue = 0.0;
            maxRangeValue = 1.0;
        }
    }
    
    private void fillXY(TableDataProvider tdp, Locale locale) throws Exception
    {
        maxDomainValue = Double.NEGATIVE_INFINITY;
        minDomainValue = Double.POSITIVE_INFINITY;
        maxRangeValue = Double.NEGATIVE_INFINITY;
        minRangeValue = Double.POSITIVE_INFINITY;
        
        seriesList = new ArrayList<Comparable>();
        xValues = new TreeMap<Comparable,List<Number>>();
        yValues = new TreeMap<Comparable,List<Number>>();
        
        seriesColumn = seriesField == null ? columns[0]
                : new FieldKey(seriesField);
        domainColumn = domainField == null ? columns[1]
                : new FieldKey(domainField);
        
        for (RowKey rk : tdp.getRowKeys(Integer.MAX_VALUE, null))
        {
            Comparable series = (Comparable)tdp.getValue(seriesColumn, rk);
            Number xValue = NumberUtils.createNumber(locale, tdp.getValue(domainColumn, rk));
            Number yValue = NumberUtils.createNumber(locale, tdp.getValue(valueColumn, rk));
            
            double x = xValue.doubleValue();
            double y = yValue.doubleValue();
            
            List<Number> xData = xValues.get(series);
            
            if (xData == null)
            {
                xData = new ArrayList<Number>();
                xValues.put(series, xData);
                seriesList.add(series);
            }
            
            xData.add(xValue);
            
            List<Number> yData = yValues.get(series);
            
            if (yData == null)
            {
                yData = new ArrayList<Number>();
                yValues.put(series, yData);
            }
            
            yData.add(yValue);
            
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
        
        if (seriesList.size() == 0)
        {
            minDomainValue = 0.0;
            maxDomainValue = 1.0;
            minRangeValue = 0.0;
            maxRangeValue = 1.0;
        }
    }
    
// ========== ChartImpl ======
    /**
     * Returns the x-value for the specified series and item.  The
     * implementation is responsible for ensuring that the x-values are
     * presented in ascending order.
     *
     * @param  seriesIndex  the series (zero-based index).
     * @param  itemIndex  the item (zero-based index).
     *
     * @return The x-value
     *
     * @see XYDataset
     */
    public Number getX(int seriesIndex, int itemIndex)
    {
        if (seriesList == null || xValues == null)
            return null;

        Comparable series = seriesList.get(seriesIndex);

        if (series == null)
            return null;

        List<Number> data = xValues.get(series);

        if (data == null)
            return null;

        return data.get(itemIndex);
    }
    
    /**
     * Returns the y-value for the specified series and item.
     *
     * @param  seriesIndex  the series (zero-based index).
     * @param  itemIndex  the item (zero-based index).
     *
     * @return The yValue value
     *
     * @see XYDataset
     */
    public Number getY(int seriesIndex, int itemIndex)
    {
        if (seriesList == null || yValues == null)
            return null;
        
        Comparable series = seriesList.get(seriesIndex);

        if (series == null)
            return null;

        List<Number> data = yValues.get(series);

        if (data == null)
            return null;

        return data.get(itemIndex);
    }
    
    /**
     * Returns the number of items in the specified series.
     *
     * @param  seriesIndex  the series (zero-based index).
     *
     * @return The itemCount value
     *
     * @see XYDataset
     */
    public int getItemCount(int seriesIndex)
    {
        if (seriesList == null || xValues == null)
            return 0;

        Comparable series = seriesList.get(seriesIndex);

        if (series == null)
            return 0;

        List<Number> data = xValues.get(series);

        if (data == null)
            return 0;

        return data.size();
    }
    
    /**
     * Returns the number of series in the dataset.
     *
     * @return The seriesCount value
     *
     * @see XYDataset
     * @see Dataset
     */
    public int getSeriesCount()
    {
        if (seriesList == null)
            return 0;
        
        return seriesList.size();
    }
    
    /**
     * Returns the key for the specified series.
     *
     * @param seriesIndex  the series (zero-based index).
     *
     * @return The seriesName value
     *
     * @see XYDataset
     * @see Dataset
     */
    public Comparable getSeriesKey(int seriesIndex)
    {
        if (seriesList == null)
            return null;
        
        return seriesList.get(seriesIndex);
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
    
// =================== Category ================
    /**
     * Returns a list of the categories in the dataset.
     * <P>
     * Supports the CategoryDataset interface.
     *
     * @return A list of the categories in the dataset.
     */
    public List getColumnKeys()
    {
        if (categoryValues == null)
            return null;
        
        return categoryValues.getColumnKeys();
    }
    
    /**
     * Returns the data value for one category in a series.
     * <P>
     * This method is part of the CategoryDataset interface.  Not particularly
     * meaningful for this class...returns the end value.
     * @param series    The required series (zero based index).
     * @param category  The required category.
     * @return The data value for one category in a series (null possible).
     */
    public Number getValue(Comparable series, Comparable category)
    {
        if (categoryValues == null)
            return null;
        
        return categoryValues.getValue(series, category);
    }
    
    /**
     * Returns the data value for one category in a series.
     * <P>
     * This method is part of the CategoryDataset interface.  Not particularly
     * meaningful for this class...returns the end value.
     *
     * @param series  the required series (zero based index).
     * @param category  the required category.
     *
     * @return The data value for one category in a series (null possible).
     */
    public Number getValue(int series, int category)
    {
        if (categoryValues == null)
            return null;
        
        return categoryValues.getValue(series, category);
    }
    
    /**
     * Returns a column key.
     *
     * @param column  the column index.
     *
     * @return The column key.
     */
    public Comparable getColumnKey(int column)
    {
        if (categoryValues == null)
            return null;
        
        return categoryValues.getColumnKey(column);
    }
    
    /**
     * Returns a column index.
     *
     * @param columnKey  the column key.
     *
     * @return The column index.
     */
    public int getColumnIndex(Comparable columnKey)
    {
        if (categoryValues == null)
            return -1;
        
        return categoryValues.getColumnIndex(columnKey);
    }
    
    /**
     * Returns a row index.
     *
     * @param rowKey  the row key.
     *
     * @return The row index.
     */
    public int getRowIndex(Comparable rowKey)
    {
        if (categoryValues == null)
            return -1;
        
        return categoryValues.getRowIndex(rowKey);
    }
    
    /**
     * Returns a list of the series in the dataset.
     * <P>
     * Supports the CategoryDataset interface.
     *
     * @return A list of the series in the dataset.
     */
    public List getRowKeys()
    {
        if (categoryValues == null)
            return null;
        
        return categoryValues.getRowKeys();
    }
    
    /**
     * Returns the name of the specified series.
     *
     * @param row  the index of the required row/series (zero-based).
     *
     * @return The name of the specified series.
     */
    public Comparable getRowKey(int row)
    {
        if (categoryValues == null)
            return null;
        
        return categoryValues.getRowKey(row);
    }
    
    /**
     * Returns the number of categories in the dataset.  This method is part of
     * the {@link CategoryDataset} interface.
     *
     * @return The number of categories in the dataset.
     */
    public int getColumnCount()
    {
        if (categoryValues == null)
            return 0;
        
        return categoryValues.getColumnCount();
    }
    
    /**
     * Returns the number of series in the dataset (possibly zero).
     *
     * @return The number of series in the dataset.
     */
    public int getRowCount()
    {
        if (categoryValues == null)
            return 0;
        
        return categoryValues.getRowCount();
    }
    
// ============== Pie =============
    /**
     * Returns the number of items in the dataset.
     *
     * @return The item count.
     */
    public int getItemCount()
    {
        if (pieValues == null)
            return 0;
        
        return pieValues.getItemCount();
    }
    
    /**
     * Returns the categories in the dataset.  The returned list is
     * unmodifiable.
     *
     * @return The categories in the dataset.
     */
    public List getKeys()
    {
        if (pieValues == null)
            return null;
        
        return pieValues.getKeys();
    }
    
    /**
     * Returns the key for the specified item, or <code>null</code>.
     *
     * @param item  the item index (in the range <code>0</code> to
     *     <code>getItemCount() - 1</code>).
     *
     * @return The key, or <code>null</code>.
     *
     * @throws IndexOutOfBoundsException if <code>item</code> is not in the
     *     specified range.
     */
    public Comparable getKey(int item)
    {
        if (pieValues == null)
            return null;
        
        return pieValues.getKey(item);
    }
    
    /**
     * Returns the index for a key, or -1 if the key is not recognised.
     *
     * @param key  the key (<code>null</code> not permitted).
     *
     * @return The index, or <code>-1</code> if the key is unrecognised.
     *
     * @throws IllegalArgumentException if <code>key</code> is
     *     <code>null</code>.
     */
    public int getIndex(Comparable key)
    {
        if (pieValues == null)
            return -1;
        
        return pieValues.getIndex(key);
    }
    
    /**
     * Returns a value.
     *
     * @param item  the value index.
     *
     * @return The value (possibly <code>null</code>).
     */
    public Number getValue(int item)
    {
        if (pieValues == null)
            return null;
        
        return pieValues.getValue(item);
    }
    
    /**
     * Returns the data value associated with a key.
     *
     * @param key  the key (<code>null</code> not permitted).
     *
     * @return The value (possibly <code>null</code>).
     *
     * @throws UnknownKeyException if the key is not recognised.
     */
    public Number getValue(Comparable key)
    {
        if (pieValues == null)
            return null;
        
        return pieValues.getValue(key);
    }
    
    public boolean equals(Object obj)
    {
        
        if (obj == this)
            return true;
        
        if (!(obj instanceof GenericDataset))
            return false;
        
        GenericDataset that = (GenericDataset) obj;
        if (!datasetType.equals(that.datasetType))
            return false;

        if (!dataProvider.equals(that.dataProvider))
            return false;

        return true;
    }

// ========== Component properties ==========
    
    // dataProvider
    private com.sun.data.provider.TableDataProvider dataProvider = null;
    
    /**
     * <p>data provider.</p>
     * @return Data provider
     */
    public com.sun.data.provider.TableDataProvider getDataProvider()
    {
        return this.dataProvider;
    }
    
    /**
     * <p>data provider.</p>
     * @param dataProvider Data provider
     * @see #getDataProvider()
     */
    public void setDataProvider(com.sun.data.provider.TableDataProvider dataProvider)
    {
        this.dataProvider = dataProvider;
    }
    
    /**
     * Holds value of property categoryField.
     */
    private String categoryField = null;
    
    /**
     * Getter for property categoryField.
     * @return Value of property categoryField.
     */
    public String getCategoryField()
    {
        return this.categoryField;
    }
    
    /**
     * Setter for property categoryField.
     * @param categoryField New value of property categoryField.
     */
    public void setCategoryField(String categoryField)
    {
        this.categoryField = categoryField;
    }
    
    /**
     * Holds value of property seriesField.
     */
    private String seriesField = null;
    
    /**
     * Getter for property seriesField.
     * @return Value of property seriesField.
     */
    public String getSeriesField()
    {
        return this.seriesField;
    }
    
    /**
     * Setter for property seriesField.
     * @param seriesField New value of property seriesField.
     */
    public void setSeriesField(String seriesField)
    {
        this.seriesField = seriesField;
    }
    
    /**
     * Holds value of property keyField.
     */
    private String keyField = null;
    
    /**
     * Getter for property keyField.
     * @return Value of property keyField.
     */
    public String getKeyField()
    {
        return this.keyField;
    }
    
    /**
     * Setter for property keyField.
     * @param keyField New value of property keyField.
     */
    public void setKeyField(String keyField)
    {
        this.keyField = keyField;
    }
    
    /**
     * Holds value of property domainField.
     */
    private String domainField = null;
    
    /**
     * Getter for property domainField.
     * @return Value of property domainField.
     */
    public String getDomainField()
    {
        return this.domainField;
    }
    
    /**
     * Setter for property domainField.
     * @param domainField New value of property domainField.
     */
    public void setDomainField(String domainField)
    {
        this.domainField = domainField;
    }
    /**
     * Holds value of property valueField.
     */
    private String valueField = null;
    
    /**
     * Getter for property valueField.
     * @return Value of property valueField.
     */
    public String getValueField()
    {
        return this.valueField;
    }
    
    /**
     * Setter for property valueField.
     * @param valueField New value of property valueField.
     */
    public void setValueField(String valueField)
    {
        this.valueField = valueField;
    }
    
    /**
     * Holds value of property rowField.
     */
    private String rowField = null;
    
    /**
     * Getter for property rowField.
     * @return Value of property rowField.
     */
    public String getRowField()
    {
        return this.rowField;
    }
    
    /**
     * Setter for property rowField.
     * @param rowField New value of property rowField.
     */
    public void setRowField(String rowField)
    {
        this.rowField = rowField;
    }
}
