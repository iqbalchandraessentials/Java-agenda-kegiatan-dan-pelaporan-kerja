/*
 * ExternalDataset.java
 *
 * Created on 7 Aug 2006, 11:16
 */

package net.sf.jschart.component;
import org.jfree.data.general.Dataset;

/**
 *
 * @author als
 */
public interface ExternalDataset
{
    public enum DatasetType
    {
        PIE,
        CATEGORY,
        XY
    }

    /**
     * Initialize external data
     */
    public void initData(DatasetType datasetType);
}
