/*
 * PieDataset.java
 *
 * Created on 9 Oct 2006, 14:20
 */

package net.sf.jschart.component;
import com.sun.data.provider.DataProvider;
import com.sun.data.provider.FieldKey;
import com.sun.data.provider.RowKey;
import com.sun.data.provider.TableDataProvider;
import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jfree.data.general.DefaultPieDataset;
import net.sf.jschart.component.ExternalDataset.DatasetType;
import net.sf.jschart.util.L10nUtil;
import net.sf.jschart.util.NumberUtils;

/**
 *
 * @author als
 */
public class PieDataset extends DefaultPieDataset
        implements ExternalDataset
{
    private static Log log = LogFactory.getLog(PieDataset.class);
    
    /** Creates a new instance of PieDataset */
    public PieDataset()
    {
    }
    
    // byColumn
    private boolean byColumn = false;
    
    /**
     * <p>Sets a flag that controls whether or not the
     *    data gets by columns or by rows. If by columns then dataset
     *    should have as many columns as you want to have parts.
     *    If by rows then dataset should have two column: 1 key,
     *    2 value.</p>
     */
    public boolean isByColumn()
    {
        return this.byColumn;
    }
    
    /**
     * <p>Sets a flag that controls whether or not the
     *    data gets by columns or by rows. If by columns then dataset
     *    should have as many columns as you want to have parts.
     *    If by rows then dataset should have two column: 1 key,
     *    2 value.</p>
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
     * Method called when dataprovider has changed
     */
    public void initData(DatasetType datasetType)
    {
        log.trace("PieDataset.initData: Begin");
        if (datasetType != DatasetType.PIE)
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
                        sb.append("PieDataset.initData: TableDataProvider ");
                        sb.append(rowCount);
                        sb.append(" rows ");
                        sb.append(cols.length);
                        sb.append(" cols");
                        log.trace(sb.toString());
                    }
                    
                    if (isByColumn())
                    {
                        for (RowKey rk : tdp.getRowKeys(1, null))
                        {
                            for (FieldKey fk : cols)
                            {
                                Comparable key = fk.getDisplayName();
                                Object value = tdp.getValue(fk,rk);
                                Number nValue = NumberUtils.createNumber(locale, value);
                                super.setValue(key, nValue);
                            }
                            break;
                        }
                    }
                    else
                    {
                        if (cols.length > 1)
                        {
                            for (RowKey rk : tdp.getRowKeys(Integer.MAX_VALUE, null))
                            {
                                Comparable key = String.valueOf(tdp.getValue(cols[0], rk));
                                Object value = tdp.getValue(cols[1],rk);
                                Number nValue = NumberUtils.createNumber(locale, value);
                                super.setValue(key, nValue);
                            }
                        }
                    }
                }
                else
                {
                    log.trace("PieDataset.initData: DataProvider");
                    for (FieldKey fk : dp.getFieldKeys())
                    {
                        Comparable key = fk.getDisplayName();
                        Object value = dp.getValue(fk);
                        Number nValue = NumberUtils.createNumber(locale, value);
                        super.setValue(key, nValue);
                    }
                }
            }
            else
            {
                log.trace("PieDataset.initData: NULL Dataprovider");
            }
        }
        catch (Exception e)
        {
            log.error("initData: ", e);
            throw new RuntimeException("PieDataset unexpected", e);
        }
        
        log.trace("PieDataset.initData: End");
    }
}
