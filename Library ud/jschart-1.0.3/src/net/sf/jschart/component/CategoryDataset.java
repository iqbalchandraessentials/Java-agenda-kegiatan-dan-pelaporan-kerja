/*
 * CategoryDataset.java
 *
 * Created on 13 July 2006, 12:03
 */

package net.sf.jschart.component;
import com.sun.data.provider.DataProvider;
import com.sun.data.provider.FieldKey;
import com.sun.data.provider.RowKey;
import com.sun.data.provider.TableDataProvider;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.data.category.DefaultCategoryDataset;
import net.sf.jschart.util.L10nUtil;
import net.sf.jschart.util.NumberUtils;

/**
 *
 * @author als
 */
public class CategoryDataset extends DefaultCategoryDataset
        implements ExternalDataset
{
    private static Log log = LogFactory.getLog(CategoryDataset.class);
    
    /** Creates a new instance of CategoryDataset */
    public CategoryDataset()
    {
        log.trace("ctor");
    }
    
    // byColumn
    private boolean byColumn = false;
    
    /**
     * <p>Sets a flag that controls whether or not the
     *    categories gets by columns or by rows. If by columns then dataset
     *    should have as many columns as you want to have categories.
     *    If by rows then dataset should have three column: 1 category group,
     *    2 category, 3 value.</p>
     */
    public boolean isByColumn()
    {
        return this.byColumn;
    }
    
    /**
     * <p>Sets a flag that controls whether or not the
     *    categories gets by columns or by rows. If by columns then dataset
     *    should have as many columns as you want to have categories.
     *    If by rows then dataset should have three column: 1 category group,
     *    2 category, 3 value.</p>
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
    
    // transpose
    private boolean transpose = false;
    
    /**
     * <p>Sets a flag that controls whether or not the table
     *    values are transposed when added to the dataset.</p>
     */
    public boolean isTranspose()
    {
        return this.transpose;
    }
    
    /**
     * <p>Sets a flag that controls whether or not the table
     *    values are transposed when added to the dataset.</p>
     * @see #isTranspose()
     */
    public void setTranspose(boolean transpose)
    {
        this.transpose = transpose;
    }
    
    /**
     * Method called when dataprovider has changed
     */
    public void initData(DatasetType datasetType)
    {
        log.trace("CategoryDataset.initData: Begin");
        if (datasetType != DatasetType.CATEGORY)
            throw new IllegalArgumentException("Invalid dataset type");

        try
        {
            Locale locale = L10nUtil.getLocale();
            DataProvider dp = getDataProvider();
            if (dp != null)
            {
                super.clear();
                
                if (dp instanceof TableDataProvider)
                {
                    TableDataProvider tdp = (TableDataProvider)dp;
                    int rowCount = tdp.getRowCount();
                    FieldKey[] cols = tdp.getFieldKeys();
                    
                    if (log.isTraceEnabled())
                    {
                        StringBuffer sb = new StringBuffer();
                        sb.append("CategoryDataset.initData: ");
                        sb.append(tdp);
                        sb.append(" rows: ");
                        sb.append(rowCount);
                        sb.append(" cols: ");
                        sb.append(cols.length);
                        log.trace(sb.toString());
                    }
                    
                    if (isByColumn())
                    {
                        for (RowKey rk : tdp.getRowKeys(Integer.MAX_VALUE, null))
                        {
                            Comparable row_key = null;
                            boolean firstColumn = true;
                            for (FieldKey fk : cols)
                            {
                                if (firstColumn)
                                {
                                    row_key = String.valueOf(tdp.getValue(fk,rk));
                                    firstColumn = false;
                                }
                                else
                                {
                                    Object value = tdp.getValue(fk,rk);
                                    Number nValue = NumberUtils.createNumber(locale, value);
                                    if (isTranspose())
                                        super.addValue(nValue,
                                                fk.getDisplayName(), row_key);
                                    else
                                        super.addValue(nValue,
                                                row_key, fk.getDisplayName());
                                }
                            }
                        }
                    }
                    else
                    {
                        if (cols.length >= 3)
                        {
                            for (RowKey rk : tdp.getRowKeys(Integer.MAX_VALUE, null))
                            {
                                Comparable row_key = String.valueOf(tdp.getValue(cols[0], rk));
                                Comparable cat_key = String.valueOf(tdp.getValue(cols[1], rk));
                                Object value = tdp.getValue(cols[2],rk);
                                Number nValue = NumberUtils.createNumber(locale, value);
                                if (isTranspose())
                                    super.addValue(nValue, cat_key, row_key);
                                else
                                    super.addValue(nValue, row_key, cat_key);
                            }
                        }
                    }
                }
                else
                {
                    log.trace("CategoryDataset.initData: " + dp);
                    Comparable row_key = null;
                    boolean firstColumn = true;
                    for (FieldKey fk : dp.getFieldKeys())
                    {
                        if (firstColumn)
                        {
                            row_key = dp.getValue(fk).toString();
                            firstColumn = false;
                        }
                        else
                        {
                            Object value = dp.getValue(fk);
                            Number nValue = NumberUtils.createNumber(locale, value);
                            super.addValue(nValue, row_key, fk.getDisplayName());
                        }
                    }
                }
            }
            else
            {
                log.trace("CategoryDataset.initData: NULL Dataprovider");
            }
        }
        catch (Exception e)
        {
            log.error("initData: ", e);
            throw new RuntimeException("CategoryDataset unexpected", e);
        }
        
        log.trace("CategoryDataset.initData: End");
    }
    
}
