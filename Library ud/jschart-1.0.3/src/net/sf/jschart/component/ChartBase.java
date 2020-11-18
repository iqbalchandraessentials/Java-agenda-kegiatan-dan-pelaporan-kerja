package net.sf.jschart.component;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;

/**
 * Render a Chart.
 */

public abstract class ChartBase extends javax.faces.component.UIOutput
{
    
    /**
     * <p>Construct a new <code>ChartBase</code>.</p>
     */
    public ChartBase()
    {
        super();
        setRendererType("net.sf.jschart.Chart");
    }
    
    /**
     * <p>Return the family for this component.</p>
     */
    public String getFamily()
    {
        return "net.sf.jschart.Chart";
    }
    
    // alpha
    private int alpha = Integer.MIN_VALUE;
    private boolean alpha_set = false;
    
    /**
     * <p>Chart Alpha</p>
     */
    public int getAlpha()
    {
        if (this.alpha_set)
        {
            return this.alpha;
        }
        ValueBinding _vb = getValueBinding("alpha");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return Integer.MIN_VALUE;
            }
            else
            {
                return ((Integer) _result).intValue();
            }
        }
        return 100;
    }
    
    /**
     * <p>Chart Alpha</p>
     * @see #getAlpha()
     */
    public void setAlpha(int alpha)
    {
        this.alpha = alpha;
        this.alpha_set = true;
    }
    
    // antialias
    private boolean antialias = false;
    private boolean antialias_set = false;
    
    /**
     * <p>Antialias</p>
     */
    public boolean isAntialias()
    {
        if (this.antialias_set)
        {
            return this.antialias;
        }
        ValueBinding _vb = getValueBinding("antialias");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return false;
            }
            else
            {
                return ((Boolean) _result).booleanValue();
            }
        }
        return true;
    }
    
    /**
     * <p>Antialias</p>
     * @see #isAntialias()
     */
    public void setAntialias(boolean antialias)
    {
        this.antialias = antialias;
        this.antialias_set = true;
    }
    
    // background
    private String background = null;
    
    /**
     * <p>Chart background color</p>
     */
    public String getBackground()
    {
        if (this.background != null)
        {
            return this.background;
        }
        ValueBinding _vb = getValueBinding("background");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return "white";
    }
    
    /**
     * <p>Chart background color</p>
     * @see #getBackground()
     */
    public void setBackground(String background)
    {
        this.background = background;
    }
    
    // border
    private int border = Integer.MIN_VALUE;
    private boolean border_set = false;
    
    /**
     * <p>Chart Border width</p>
     */
    public int getBorder()
    {
        if (this.border_set)
        {
            return this.border;
        }
        ValueBinding _vb = getValueBinding("border");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return Integer.MIN_VALUE;
            }
            else
            {
                return ((Integer) _result).intValue();
            }
        }
        return 0;
    }
    
    /**
     * <p>Chart Border width</p>
     * @see #getBorder()
     */
    public void setBorder(int border)
    {
        this.border = border;
        this.border_set = true;
    }
    
    // categoryToPieByRow
    private boolean categoryToPieByRow = false;
    private boolean categoryToPieByRow_set = false;
    
    /**
     * <p>Multiple Pie conversion from Category to Pie by row</p>
     */
    public boolean isCategoryToPieByRow()
    {
        if (this.categoryToPieByRow_set)
        {
            return this.categoryToPieByRow;
        }
        ValueBinding _vb = getValueBinding("categoryToPieByRow");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return false;
            }
            else
            {
                return ((Boolean) _result).booleanValue();
            }
        }
        return false;
    }
    
    /**
     * <p>Multiple Pie conversion from Category to Pie by row</p>
     * @see #isCategoryToPieByRow()
     */
    public void setCategoryToPieByRow(boolean categoryToPieByRow)
    {
        this.categoryToPieByRow = categoryToPieByRow;
        this.categoryToPieByRow_set = true;
    }
    
    // chart3d
    private boolean chart3d = false;
    private boolean chart3d_set = false;
    
    /**
     * <p>Is 3D chart</p>
     */
    public boolean isChart3d()
    {
        if (this.chart3d_set)
        {
            return this.chart3d;
        }
        ValueBinding _vb = getValueBinding("chart3d");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return false;
            }
            else
            {
                return ((Boolean) _result).booleanValue();
            }
        }
        return true;
    }
    
    /**
     * <p>Is 3D chart</p>
     * @see #isChart3d()
     */
    public void setChart3d(boolean chart3d)
    {
        this.chart3d = chart3d;
        this.chart3d_set = true;
    }
    
    // colors
    private String colors = null;
    
    /**
     * <p>Chart colors</p>
     */
    public String getColors()
    {
        if (this.colors != null)
        {
            return this.colors;
        }
        ValueBinding _vb = getValueBinding("colors");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return null;
    }
    
    /**
     * <p>Chart colors</p>
     * @see #getColors()
     */
    public void setColors(String colors)
    {
        this.colors = colors;
    }
    
    // datasource
    private org.jfree.data.general.Dataset datasource = null;
    
    /**
     * <p>Datasource. Must be PieDataset, CategoryDataset
     *             or XYDataset derived objects</p>
     */
    public org.jfree.data.general.Dataset getDatasource()
    {
        if (this.datasource != null)
        {
            return this.datasource;
        }
        ValueBinding _vb = getValueBinding("datasource");
        if (_vb != null)
        {
            return (org.jfree.data.general.Dataset) _vb.getValue(getFacesContext());
        }
        return null;
    }
    
    /**
     * <p>Datasource. Must be PieDataset, CategoryDataset
     *             or XYDataset derived objects</p>
     * @see #getDatasource()
     */
    public void setDatasource(org.jfree.data.general.Dataset datasource)
    {
        this.datasource = datasource;
    }
    
    // dateFormat
    private String dateFormat = null;
    
    /**
     * <p>Chart output Date Format</p>
     */
    public String getDateFormat()
    {
        if (this.dateFormat != null)
        {
            return this.dateFormat;
        }
        ValueBinding _vb = getValueBinding("dateFormat");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return null;
    }
    
    /**
     * <p>Chart output Date Format</p>
     * @see #getDateFormat()
     */
    public void setDateFormat(String dateFormat)
    {
        this.dateFormat = dateFormat;
    }
    
    // dateTick
    private String dateTick = null;
    
    /**
     * <p>Date tick value</p>
     */
    public String getDateTick()
    {
        if (this.dateTick != null)
        {
            return this.dateTick;
        }
        ValueBinding _vb = getValueBinding("dateTick");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return "auto";
    }
    
    /**
     * <p>Date tick value</p>
     * @see #getDateTick()
     */
    public void setDateTick(String dateTick)
    {
        this.dateTick = dateTick;
    }
    
    // dateTickCount
    private int dateTickCount = Integer.MIN_VALUE;
    private boolean dateTickCount_set = false;
    
    /**
     * <p>Date tick count</p>
     */
    public int getDateTickCount()
    {
        if (this.dateTickCount_set)
        {
            return this.dateTickCount;
        }
        ValueBinding _vb = getValueBinding("dateTickCount");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return Integer.MIN_VALUE;
            }
            else
            {
                return ((Integer) _result).intValue();
            }
        }
        return 1;
    }
    
    /**
     * <p>Date tick count</p>
     * @see #getDateTickCount()
     */
    public void setDateTickCount(int dateTickCount)
    {
        this.dateTickCount = dateTickCount;
        this.dateTickCount_set = true;
    }
    
    // depth
    private int depth = Integer.MIN_VALUE;
    private boolean depth_set = false;
    
    /**
     * <p>Chart Depth</p>
     */
    public int getDepth()
    {
        if (this.depth_set)
        {
            return this.depth;
        }
        ValueBinding _vb = getValueBinding("depth");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return Integer.MIN_VALUE;
            }
            else
            {
                return ((Integer) _result).intValue();
            }
        }
        return 15;
    }
    
    /**
     * <p>Chart Depth</p>
     * @see #getDepth()
     */
    public void setDepth(int depth)
    {
        this.depth = depth;
        this.depth_set = true;
    }
    
    // domainNumberTick
    private double domainNumberTick = Double.MIN_VALUE;
    private boolean domainNumberTick_set = false;
    
    /**
     * <p>Domain Number tick value</p>
     */
    public double getDomainNumberTick()
    {
        if (this.domainNumberTick_set)
        {
            return this.domainNumberTick;
        }
        ValueBinding _vb = getValueBinding("domainNumberTick");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return Double.MIN_VALUE;
            }
            else
            {
                return ((Double) _result).doubleValue();
            }
        }
        return 0.0;
    }
    
    /**
     * <p>Domain Number tick value</p>
     * @see #getDomainNumberTick()
     */
    public void setDomainNumberTick(double domainNumberTick)
    {
        this.domainNumberTick = domainNumberTick;
        this.domainNumberTick_set = true;
    }
    
    // gradient
    private int gradient = Integer.MIN_VALUE;
    private boolean gradient_set = false;
    
    /**
     * <p>Gradient factor. If 100 no gradient</p>
     */
    public int getGradient()
    {
        if (this.gradient_set)
        {
            return this.gradient;
        }
        ValueBinding _vb = getValueBinding("gradient");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return Integer.MIN_VALUE;
            }
            else
            {
                return ((Integer) _result).intValue();
            }
        }
        return 100;
    }
    
    /**
     * <p>Gradient factor. If 100 no gradient</p>
     * @see #getGradient()
     */
    public void setGradient(int gradient)
    {
        this.gradient = gradient;
        this.gradient_set = true;
    }
    
    // gradientType
    private String gradientType = null;
    
    /**
     * <p>Gradient Type</p>
     */
    public String getGradientType()
    {
        if (this.gradientType != null)
        {
            return this.gradientType;
        }
        ValueBinding _vb = getValueBinding("gradientType");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return "vertical";
    }
    
    /**
     * <p>Gradient Type</p>
     * @see #getGradientType()
     */
    public void setGradientType(String gradientType)
    {
        this.gradientType = gradientType;
    }
    
    // height
    private int height = Integer.MIN_VALUE;
    private boolean height_set = false;
    
    /**
     * <p>Chart Height</p>
     */
    public int getHeight()
    {
        if (this.height_set)
        {
            return this.height;
        }
        ValueBinding _vb = getValueBinding("height");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return Integer.MIN_VALUE;
            }
            else
            {
                return ((Integer) _result).intValue();
            }
        }
        return 300;
    }
    
    /**
     * <p>Chart Height</p>
     * @see #getHeight()
     */
    public void setHeight(int height)
    {
        this.height = height;
        this.height_set = true;
    }
    
    // includeZero
    private boolean includeZero = false;
    private boolean includeZero_set = false;
    
    /**
     * <p>Chart Include Zero axis value</p>
     */
    public boolean isIncludeZero()
    {
        if (this.includeZero_set)
        {
            return this.includeZero;
        }
        ValueBinding _vb = getValueBinding("includeZero");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return false;
            }
            else
            {
                return ((Boolean) _result).booleanValue();
            }
        }
        return true;
    }
    
    /**
     * <p>Chart Include Zero axis value</p>
     * @see #isIncludeZero()
     */
    public void setIncludeZero(boolean includeZero)
    {
        this.includeZero = includeZero;
        this.includeZero_set = true;
    }
    
    // intervalMarker
    private String intervalMarker = null;
    
    /**
     * <p>Chart has Interval Marker</p>
     */
    public String getIntervalMarker()
    {
        if (this.intervalMarker != null)
        {
            return this.intervalMarker;
        }
        ValueBinding _vb = getValueBinding("intervalMarker");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return null;
    }
    
    /**
     * <p>Chart has Interval Marker</p>
     * @see #getIntervalMarker()
     */
    public void setIntervalMarker(String intervalMarker)
    {
        this.intervalMarker = intervalMarker;
    }
    
    // label
    private boolean label = false;
    private boolean label_set = false;
    
    /**
     * <p>Chart has labels</p>
     */
    public boolean isLabel()
    {
        if (this.label_set)
        {
            return this.label;
        }
        ValueBinding _vb = getValueBinding("label");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return false;
            }
            else
            {
                return ((Boolean) _result).booleanValue();
            }
        }
        return true;
    }
    
    /**
     * <p>Chart has labels</p>
     * @see #isLabel()
     */
    public void setLabel(boolean label)
    {
        this.label = label;
        this.label_set = true;
    }
    
    // labelAngle
    private int labelAngle = Integer.MIN_VALUE;
    private boolean labelAngle_set = false;
    
    /**
     * <p>Category label angle</p>
     */
    public int getLabelAngle()
    {
        if (this.labelAngle_set)
        {
            return this.labelAngle;
        }
        ValueBinding _vb = getValueBinding("labelAngle");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return Integer.MIN_VALUE;
            }
            else
            {
                return ((Integer) _result).intValue();
            }
        }
        return 0;
    }
    
    /**
     * <p>Category label angle</p>
     * @see #getLabelAngle()
     */
    public void setLabelAngle(int labelAngle)
    {
        this.labelAngle = labelAngle;
        this.labelAngle_set = true;
    }
    
    // labelFormat
    private String labelFormat = null;
    
    /**
     * <p>Label format</p>
     */
    public String getLabelFormat()
    {
        if (this.labelFormat != null)
        {
            return this.labelFormat;
        }
        ValueBinding _vb = getValueBinding("labelFormat");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return null;
    }
    
    /**
     * <p>Label format</p>
     * @see #getLabelFormat()
     */
    public void setLabelFormat(String labelFormat)
    {
        this.labelFormat = labelFormat;
    }
    
    // legend
    private boolean legend = false;
    private boolean legend_set = false;
    
    /**
     * <p>Chart has legend</p>
     */
    public boolean isLegend()
    {
        if (this.legend_set)
        {
            return this.legend;
        }
        ValueBinding _vb = getValueBinding("legend");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return false;
            }
            else
            {
                return ((Boolean) _result).booleanValue();
            }
        }
        return true;
    }
    
    /**
     * <p>Chart has legend</p>
     * @see #isLegend()
     */
    public void setLegend(boolean legend)
    {
        this.legend = legend;
        this.legend_set = true;
    }
    
    // legendLabelFormat
    private String legendLabelFormat = null;
    
    /**
     * <p>Legend label format</p>
     */
    public String getLegendLabelFormat()
    {
        if (this.legendLabelFormat != null)
        {
            return this.legendLabelFormat;
        }
        ValueBinding _vb = getValueBinding("legendLabelFormat");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return null;
    }
    
    /**
     * <p>Legend label format</p>
     * @see #getLegendLabelFormat()
     */
    public void setLegendLabelFormat(String legendLabelFormat)
    {
        this.legendLabelFormat = legendLabelFormat;
    }
    
    // legendPosition
    private String legendPosition = null;
    
    /**
     * <p>Legend position</p>
     */
    public String getLegendPosition()
    {
        if (this.legendPosition != null)
        {
            return this.legendPosition;
        }
        ValueBinding _vb = getValueBinding("legendPosition");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return "bottom";
    }
    
    /**
     * <p>Legend position</p>
     * @see #getLegendPosition()
     */
    public void setLegendPosition(String legendPosition)
    {
        this.legendPosition = legendPosition;
    }
    
    // limitPercent
    private int limitPercent = Integer.MIN_VALUE;
    private boolean limitPercent_set = false;
    
    /**
     * <p>Pie Chart limit percent</p>
     */
    public int getLimitPercent()
    {
        if (this.limitPercent_set)
        {
            return this.limitPercent;
        }
        ValueBinding _vb = getValueBinding("limitPercent");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return Integer.MIN_VALUE;
            }
            else
            {
                return ((Integer) _result).intValue();
            }
        }
        return 0;
    }
    
    /**
     * <p>Pie Chart limit percent</p>
     * @see #getLimitPercent()
     */
    public void setLimitPercent(int limitPercent)
    {
        this.limitPercent = limitPercent;
        this.limitPercent_set = true;
    }
    
    // limitPercentTitle
    private String limitPercentTitle = null;
    
    /**
     * <p>Pie Chart limit percent title</p>
     */
    public String getLimitPercentTitle()
    {
        if (this.limitPercentTitle != null)
        {
            return this.limitPercentTitle;
        }
        ValueBinding _vb = getValueBinding("limitPercentTitle");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return null;
    }
    
    /**
     * <p>Pie Chart limit percent title</p>
     * @see #getLimitPercentTitle()
     */
    public void setLimitPercentTitle(String limitPercentTitle)
    {
        this.limitPercentTitle = limitPercentTitle;
    }
    
    // orientation
    private String orientation = null;
    
    /**
     * <p>Chart orientation</p>
     */
    public String getOrientation()
    {
        if (this.orientation != null)
        {
            return this.orientation;
        }
        ValueBinding _vb = getValueBinding("orientation");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return "vertical";
    }
    
    /**
     * <p>Chart orientation</p>
     * @see #getOrientation()
     */
    public void setOrientation(String orientation)
    {
        this.orientation = orientation;
    }
    
    // outline
    private boolean outline = false;
    private boolean outline_set = false;
    
    /**
     * <p>Chart outline</p>
     */
    public boolean isOutline()
    {
        if (this.outline_set)
        {
            return this.outline;
        }
        ValueBinding _vb = getValueBinding("outline");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return false;
            }
            else
            {
                return ((Boolean) _result).booleanValue();
            }
        }
        return false;
    }
    
    /**
     * <p>Chart outline</p>
     * @see #isOutline()
     */
    public void setOutline(boolean outline)
    {
        this.outline = outline;
        this.outline_set = true;
    }
    
    // periodAxisFormat
    private String periodAxisFormat = null;
    
    /**
     * <p>Sets period axis format.
     *                 Use standard SimpleDateFormat letters separated by '|' for bands.</p>
     */
    public String getPeriodAxisFormat()
    {
        if (this.periodAxisFormat != null)
        {
            return this.periodAxisFormat;
        }
        ValueBinding _vb = getValueBinding("periodAxisFormat");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return null;
    }
    
    /**
     * <p>Sets period axis format.
     *                 Use standard SimpleDateFormat letters separated by '|' for bands.</p>
     * @see #getPeriodAxisFormat()
     */
    public void setPeriodAxisFormat(String periodAxisFormat)
    {
        this.periodAxisFormat = periodAxisFormat;
    }
    
    // rangeNumberTick
    private double rangeNumberTick = Double.MIN_VALUE;
    private boolean rangeNumberTick_set = false;
    
    /**
     * <p>Range Number tick value</p>
     */
    public double getRangeNumberTick()
    {
        if (this.rangeNumberTick_set)
        {
            return this.rangeNumberTick;
        }
        ValueBinding _vb = getValueBinding("rangeNumberTick");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return Double.MIN_VALUE;
            }
            else
            {
                return ((Double) _result).doubleValue();
            }
        }
        return 0.0;
    }
    
    /**
     * <p>Range Number tick value</p>
     * @see #getRangeNumberTick()
     */
    public void setRangeNumberTick(double rangeNumberTick)
    {
        this.rangeNumberTick = rangeNumberTick;
        this.rangeNumberTick_set = true;
    }
    
    // startAngle
    private int startAngle = Integer.MIN_VALUE;
    private boolean startAngle_set = false;
    
    /**
     * <p>Pie Chart starting angle</p>
     */
    public int getStartAngle()
    {
        if (this.startAngle_set)
        {
            return this.startAngle;
        }
        ValueBinding _vb = getValueBinding("startAngle");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return Integer.MIN_VALUE;
            }
            else
            {
                return ((Integer) _result).intValue();
            }
        }
        return 90;
    }
    
    /**
     * <p>Pie Chart starting angle</p>
     * @see #getStartAngle()
     */
    public void setStartAngle(int startAngle)
    {
        this.startAngle = startAngle;
        this.startAngle_set = true;
    }
    
    // style
    private String style = null;
    
    /**
     * <p>CSS style attribute.</p>
     */
    public String getStyle()
    {
        if (this.style != null)
        {
            return this.style;
        }
        ValueBinding _vb = getValueBinding("style");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return null;
    }
    
    /**
     * <p>CSS style attribute.</p>
     * @see #getStyle()
     */
    public void setStyle(String style)
    {
        this.style = style;
    }
    
    // styleClass
    private String styleClass = null;
    
    /**
     * <p>CSS "class" attribute.</p>
     */
    public String getStyleClass()
    {
        if (this.styleClass != null)
        {
            return this.styleClass;
        }
        ValueBinding _vb = getValueBinding("styleClass");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return null;
    }
    
    /**
     * <p>CSS "class" attribute.</p>
     * @see #getStyleClass()
     */
    public void setStyleClass(String styleClass)
    {
        this.styleClass = styleClass;
    }
    
    // subtitle
    private String subtitle = null;
    
    /**
     * <p>Chart subtitle</p>
     */
    public String getSubtitle()
    {
        if (this.subtitle != null)
        {
            return this.subtitle;
        }
        ValueBinding _vb = getValueBinding("subtitle");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return null;
    }
    
    /**
     * <p>Chart subtitle</p>
     * @see #getSubtitle()
     */
    public void setSubtitle(String subtitle)
    {
        this.subtitle = subtitle;
    }
    
    // title
    private String title = null;
    
    /**
     * <p>Chart title</p>
     */
    public String getTitle()
    {
        if (this.title != null)
        {
            return this.title;
        }
        ValueBinding _vb = getValueBinding("title");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return null;
    }
    
    /**
     * <p>Chart title</p>
     * @see #getTitle()
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    // tooltipFormat
    private String tooltipFormat = null;
    
    /**
     * <p>Tooltip format</p>
     */
    public String getTooltipFormat()
    {
        if (this.tooltipFormat != null)
        {
            return this.tooltipFormat;
        }
        ValueBinding _vb = getValueBinding("tooltipFormat");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return null;
    }
    
    /**
     * <p>Tooltip format</p>
     * @see #getTooltipFormat()
     */
    public void setTooltipFormat(String tooltipFormat)
    {
        this.tooltipFormat = tooltipFormat;
    }
    
    // tooltipXDate
    private boolean tooltipXDate = false;
    private boolean tooltipXDate_set = false;
    
    /**
     * <p>Is the X axis tooltip has date format?</p>
     */
    public boolean isTooltipXDate()
    {
        if (this.tooltipXDate_set)
        {
            return this.tooltipXDate;
        }
        ValueBinding _vb = getValueBinding("tooltipXDate");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return false;
            }
            else
            {
                return ((Boolean) _result).booleanValue();
            }
        }
        return false;
    }
    
    /**
     * <p>Is the X axis tooltip has date format?</p>
     * @see #isTooltipXDate()
     */
    public void setTooltipXDate(boolean tooltipXDate)
    {
        this.tooltipXDate = tooltipXDate;
        this.tooltipXDate_set = true;
    }
    
    // tooltipYDate
    private boolean tooltipYDate = false;
    private boolean tooltipYDate_set = false;
    
    /**
     * <p>Is the Y axis tooltip has date format?</p>
     */
    public boolean isTooltipYDate()
    {
        if (this.tooltipYDate_set)
        {
            return this.tooltipYDate;
        }
        ValueBinding _vb = getValueBinding("tooltipYDate");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return false;
            }
            else
            {
                return ((Boolean) _result).booleanValue();
            }
        }
        return false;
    }
    
    /**
     * <p>Is the Y axis tooltip has date format?</p>
     * @see #isTooltipYDate()
     */
    public void setTooltipYDate(boolean tooltipYDate)
    {
        this.tooltipYDate = tooltipYDate;
        this.tooltipYDate_set = true;
    }
    
    // tooltips
    private boolean tooltips = false;
    private boolean tooltips_set = false;
    
    /**
     * <p>Chart has tooltips</p>
     */
    public boolean isTooltips()
    {
        if (this.tooltips_set)
        {
            return this.tooltips;
        }
        ValueBinding _vb = getValueBinding("tooltips");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return false;
            }
            else
            {
                return ((Boolean) _result).booleanValue();
            }
        }
        return true;
    }
    
    /**
     * <p>Chart has tooltips</p>
     * @see #isTooltips()
     */
    public void setTooltips(boolean tooltips)
    {
        this.tooltips = tooltips;
        this.tooltips_set = true;
    }
    
    // type
    private String type = null;
    
    /**
     * <p>Chart type (bar,pie,line...)</p>
     */
    public String getType()
    {
        if (this.type != null)
        {
            return this.type;
        }
        ValueBinding _vb = getValueBinding("type");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return "bar";
    }
    
    /**
     * <p>Chart type (bar,pie,line...)</p>
     * @see #getType()
     */
    public void setType(String type)
    {
        this.type = type;
    }
    
    // useChartlet
    private boolean useChartlet = false;
    
    /**
     * <p>Use Chartlet servlet for image sending instead
     *                 of PhaseListener.</p>
     */
    public boolean isUseChartlet()
    {
        return this.useChartlet;
    }
    
    /**
     * <p>Use Chartlet servlet for image sending instead
     *                 of PhaseListener.</p>
     * @see #isUseChartlet()
     */
    public void setUseChartlet(boolean useChartlet)
    {
        this.useChartlet = useChartlet;
    }
    
    // verticalDateTicks
    private boolean verticalDateTicks = false;
    private boolean verticalDateTicks_set = false;
    
    /**
     * <p>Chart has Vertical Date Ticks</p>
     */
    public boolean isVerticalDateTicks()
    {
        if (this.verticalDateTicks_set)
        {
            return this.verticalDateTicks;
        }
        ValueBinding _vb = getValueBinding("verticalDateTicks");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return false;
            }
            else
            {
                return ((Boolean) _result).booleanValue();
            }
        }
        return false;
    }
    
    /**
     * <p>Chart has Vertical Date Ticks</p>
     * @see #isVerticalDateTicks()
     */
    public void setVerticalDateTicks(boolean verticalDateTicks)
    {
        this.verticalDateTicks = verticalDateTicks;
        this.verticalDateTicks_set = true;
    }
    
    // visibleShapes
    private boolean visibleShapes = false;
    private boolean visibleShapes_set = false;
    
    /**
     * <p>Chart has Visible Shapes</p>
     */
    public boolean isVisibleShapes()
    {
        if (this.visibleShapes_set)
        {
            return this.visibleShapes;
        }
        ValueBinding _vb = getValueBinding("visibleShapes");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return false;
            }
            else
            {
                return ((Boolean) _result).booleanValue();
            }
        }
        return true;
    }
    
    /**
     * <p>Chart has Visible Shapes</p>
     * @see #isVisibleShapes()
     */
    public void setVisibleShapes(boolean visibleShapes)
    {
        this.visibleShapes = visibleShapes;
        this.visibleShapes_set = true;
    }
    
    // width
    private int width = Integer.MIN_VALUE;
    private boolean width_set = false;
    
    /**
     * <p>Chart Width</p>
     */
    public int getWidth()
    {
        if (this.width_set)
        {
            return this.width;
        }
        ValueBinding _vb = getValueBinding("width");
        if (_vb != null)
        {
            Object _result = _vb.getValue(getFacesContext());
            if (_result == null)
            {
                return Integer.MIN_VALUE;
            }
            else
            {
                return ((Integer) _result).intValue();
            }
        }
        return 400;
    }
    
    /**
     * <p>Chart Width</p>
     * @see #getWidth()
     */
    public void setWidth(int width)
    {
        this.width = width;
        this.width_set = true;
    }
    
    // xlabel
    private String xlabel = null;
    
    /**
     * <p>X label</p>
     */
    public String getXlabel()
    {
        if (this.xlabel != null)
        {
            return this.xlabel;
        }
        ValueBinding _vb = getValueBinding("xlabel");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return null;
    }
    
    /**
     * <p>X label</p>
     * @see #getXlabel()
     */
    public void setXlabel(String xlabel)
    {
        this.xlabel = xlabel;
    }
    
    // ylabel
    private String ylabel = null;
    
    /**
     * <p>Y label</p>
     */
    public String getYlabel()
    {
        if (this.ylabel != null)
        {
            return this.ylabel;
        }
        ValueBinding _vb = getValueBinding("ylabel");
        if (_vb != null)
        {
            return (String) _vb.getValue(getFacesContext());
        }
        return null;
    }
    
    /**
     * <p>Y label</p>
     * @see #getYlabel()
     */
    public void setYlabel(String ylabel)
    {
        this.ylabel = ylabel;
    }
    
    /**
     * <p>Restore the state of this component.</p>
     */
    public void restoreState(FacesContext _context,Object _state)
    {
        Object _values[] = (Object[]) _state;
        super.restoreState(_context, _values[0]);
        this.alpha = ((Integer) _values[1]).intValue();
        this.alpha_set = ((Boolean) _values[2]).booleanValue();
        this.antialias = ((Boolean) _values[3]).booleanValue();
        this.antialias_set = ((Boolean) _values[4]).booleanValue();
        this.background = (String) _values[5];
        this.border = ((Integer) _values[6]).intValue();
        this.border_set = ((Boolean) _values[7]).booleanValue();
        this.categoryToPieByRow = ((Boolean) _values[8]).booleanValue();
        this.categoryToPieByRow_set = ((Boolean) _values[9]).booleanValue();
        this.chart3d = ((Boolean) _values[10]).booleanValue();
        this.chart3d_set = ((Boolean) _values[11]).booleanValue();
        this.colors = (String) _values[12];
        this.datasource = (org.jfree.data.general.Dataset) _values[13];
        this.dateFormat = (String) _values[14];
        this.dateTick = (String) _values[15];
        this.dateTickCount = ((Integer) _values[16]).intValue();
        this.dateTickCount_set = ((Boolean) _values[17]).booleanValue();
        this.depth = ((Integer) _values[18]).intValue();
        this.depth_set = ((Boolean) _values[19]).booleanValue();
        this.domainNumberTick = ((Double) _values[20]).doubleValue();
        this.domainNumberTick_set = ((Boolean) _values[21]).booleanValue();
        this.gradient = ((Integer) _values[22]).intValue();
        this.gradient_set = ((Boolean) _values[23]).booleanValue();
        this.gradientType = (String) _values[24];
        this.height = ((Integer) _values[25]).intValue();
        this.height_set = ((Boolean) _values[26]).booleanValue();
        this.includeZero = ((Boolean) _values[27]).booleanValue();
        this.includeZero_set = ((Boolean) _values[28]).booleanValue();
        this.intervalMarker = (String) _values[29];
        this.label = ((Boolean) _values[30]).booleanValue();
        this.label_set = ((Boolean) _values[31]).booleanValue();
        this.labelAngle = ((Integer) _values[32]).intValue();
        this.labelAngle_set = ((Boolean) _values[33]).booleanValue();
        this.labelFormat = (String) _values[34];
        this.legend = ((Boolean) _values[35]).booleanValue();
        this.legend_set = ((Boolean) _values[36]).booleanValue();
        this.legendLabelFormat = (String) _values[37];
        this.legendPosition = (String) _values[38];
        this.limitPercent = ((Integer) _values[39]).intValue();
        this.limitPercent_set = ((Boolean) _values[40]).booleanValue();
        this.limitPercentTitle = (String) _values[41];
        this.orientation = (String) _values[42];
        this.outline = ((Boolean) _values[43]).booleanValue();
        this.outline_set = ((Boolean) _values[44]).booleanValue();
        this.periodAxisFormat = (String) _values[45];
        this.rangeNumberTick = ((Double) _values[46]).doubleValue();
        this.rangeNumberTick_set = ((Boolean) _values[47]).booleanValue();
        this.startAngle = ((Integer) _values[48]).intValue();
        this.startAngle_set = ((Boolean) _values[49]).booleanValue();
        this.style = (String) _values[50];
        this.styleClass = (String) _values[51];
        this.subtitle = (String) _values[52];
        this.title = (String) _values[53];
        this.tooltipFormat = (String) _values[54];
        this.tooltipXDate = ((Boolean) _values[55]).booleanValue();
        this.tooltipXDate_set = ((Boolean) _values[56]).booleanValue();
        this.tooltipYDate = ((Boolean) _values[57]).booleanValue();
        this.tooltipYDate_set = ((Boolean) _values[58]).booleanValue();
        this.tooltips = ((Boolean) _values[59]).booleanValue();
        this.tooltips_set = ((Boolean) _values[60]).booleanValue();
        this.type = (String) _values[61];
        this.useChartlet = ((Boolean) _values[62]).booleanValue();
        this.verticalDateTicks = ((Boolean) _values[63]).booleanValue();
        this.verticalDateTicks_set = ((Boolean) _values[64]).booleanValue();
        this.visibleShapes = ((Boolean) _values[65]).booleanValue();
        this.visibleShapes_set = ((Boolean) _values[66]).booleanValue();
        this.width = ((Integer) _values[67]).intValue();
        this.width_set = ((Boolean) _values[68]).booleanValue();
        this.xlabel = (String) _values[69];
        this.ylabel = (String) _values[70];
    }
    
    /**
     * <p>Save the state of this component.</p>
     */
    public Object saveState(FacesContext _context)
    {
        Object _values[] = new Object[71];
        _values[0] = super.saveState(_context);
        _values[1] = new Integer(this.alpha);
        _values[2] = this.alpha_set ? Boolean.TRUE : Boolean.FALSE;
        _values[3] = this.antialias ? Boolean.TRUE : Boolean.FALSE;
        _values[4] = this.antialias_set ? Boolean.TRUE : Boolean.FALSE;
        _values[5] = this.background;
        _values[6] = new Integer(this.border);
        _values[7] = this.border_set ? Boolean.TRUE : Boolean.FALSE;
        _values[8] = this.categoryToPieByRow ? Boolean.TRUE : Boolean.FALSE;
        _values[9] = this.categoryToPieByRow_set ? Boolean.TRUE : Boolean.FALSE;
        _values[10] = this.chart3d ? Boolean.TRUE : Boolean.FALSE;
        _values[11] = this.chart3d_set ? Boolean.TRUE : Boolean.FALSE;
        _values[12] = this.colors;
        _values[13] = this.datasource;
        _values[14] = this.dateFormat;
        _values[15] = this.dateTick;
        _values[16] = new Integer(this.dateTickCount);
        _values[17] = this.dateTickCount_set ? Boolean.TRUE : Boolean.FALSE;
        _values[18] = new Integer(this.depth);
        _values[19] = this.depth_set ? Boolean.TRUE : Boolean.FALSE;
        _values[20] = new Double(this.domainNumberTick);
        _values[21] = this.domainNumberTick_set ? Boolean.TRUE : Boolean.FALSE;
        _values[22] = new Integer(this.gradient);
        _values[23] = this.gradient_set ? Boolean.TRUE : Boolean.FALSE;
        _values[24] = this.gradientType;
        _values[25] = new Integer(this.height);
        _values[26] = this.height_set ? Boolean.TRUE : Boolean.FALSE;
        _values[27] = this.includeZero ? Boolean.TRUE : Boolean.FALSE;
        _values[28] = this.includeZero_set ? Boolean.TRUE : Boolean.FALSE;
        _values[29] = this.intervalMarker;
        _values[30] = this.label ? Boolean.TRUE : Boolean.FALSE;
        _values[31] = this.label_set ? Boolean.TRUE : Boolean.FALSE;
        _values[32] = new Integer(this.labelAngle);
        _values[33] = this.labelAngle_set ? Boolean.TRUE : Boolean.FALSE;
        _values[34] = this.labelFormat;
        _values[35] = this.legend ? Boolean.TRUE : Boolean.FALSE;
        _values[36] = this.legend_set ? Boolean.TRUE : Boolean.FALSE;
        _values[37] = this.legendLabelFormat;
        _values[38] = this.legendPosition;
        _values[39] = new Integer(this.limitPercent);
        _values[40] = this.limitPercent_set ? Boolean.TRUE : Boolean.FALSE;
        _values[41] = this.limitPercentTitle;
        _values[42] = this.orientation;
        _values[43] = this.outline ? Boolean.TRUE : Boolean.FALSE;
        _values[44] = this.outline_set ? Boolean.TRUE : Boolean.FALSE;
        _values[45] = this.periodAxisFormat;
        _values[46] = new Double(this.rangeNumberTick);
        _values[47] = this.rangeNumberTick_set ? Boolean.TRUE : Boolean.FALSE;
        _values[48] = new Integer(this.startAngle);
        _values[49] = this.startAngle_set ? Boolean.TRUE : Boolean.FALSE;
        _values[50] = this.style;
        _values[51] = this.styleClass;
        _values[52] = this.subtitle;
        _values[53] = this.title;
        _values[54] = this.tooltipFormat;
        _values[55] = this.tooltipXDate ? Boolean.TRUE : Boolean.FALSE;
        _values[56] = this.tooltipXDate_set ? Boolean.TRUE : Boolean.FALSE;
        _values[57] = this.tooltipYDate ? Boolean.TRUE : Boolean.FALSE;
        _values[58] = this.tooltipYDate_set ? Boolean.TRUE : Boolean.FALSE;
        _values[59] = this.tooltips ? Boolean.TRUE : Boolean.FALSE;
        _values[60] = this.tooltips_set ? Boolean.TRUE : Boolean.FALSE;
        _values[61] = this.type;
        _values[62] = this.useChartlet ? Boolean.TRUE : Boolean.FALSE;
        _values[63] = this.verticalDateTicks ? Boolean.TRUE : Boolean.FALSE;
        _values[64] = this.verticalDateTicks_set ? Boolean.TRUE : Boolean.FALSE;
        _values[65] = this.visibleShapes ? Boolean.TRUE : Boolean.FALSE;
        _values[66] = this.visibleShapes_set ? Boolean.TRUE : Boolean.FALSE;
        _values[67] = new Integer(this.width);
        _values[68] = this.width_set ? Boolean.TRUE : Boolean.FALSE;
        _values[69] = this.xlabel;
        _values[70] = this.ylabel;
        return _values;
    }
    
}
