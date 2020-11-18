package net.sf.jschart.taglib;

import java.io.IOException;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.webapp.UIComponentTag;

public class ChartTag extends UIComponentTag
{
    
    /**
     * <p>Return the requested component type.</p>
     */
    public String getComponentType()
    {
        return "net.sf.jschart.Chart";
    }
    
    /**
     * <p>Return the requested renderer type.</p>
     */
    public String getRendererType()
    {
        return "net.sf.jschart.Chart";
    }
    
    /**
     * <p>Release any allocated tag handler attributes.</p>
     */
    public void release()
    {
        super.release();
        alpha = null;
        antialias = null;
        background = null;
        border = null;
        categoryToPieByRow = null;
        chart3d = null;
        colors = null;
        datasource = null;
        dateFormat = null;
        dateTick = null;
        dateTickCount = null;
        depth = null;
        domainNumberTick = null;
        gradient = null;
        gradientType = null;
        height = null;
        includeZero = null;
        intervalMarker = null;
        label = null;
        labelAngle = null;
        labelFormat = null;
        legend = null;
        legendLabelFormat = null;
        legendPosition = null;
        limitPercent = null;
        limitPercentTitle = null;
        orientation = null;
        outline = null;
        periodAxisFormat = null;
        rangeNumberTick = null;
        startAngle = null;
        style = null;
        styleClass = null;
        subtitle = null;
        title = null;
        tooltipFormat = null;
        tooltipXDate = null;
        tooltipYDate = null;
        tooltips = null;
        type = null;
        useChartlet = null;
        verticalDateTicks = null;
        visibleShapes = null;
        width = null;
        xlabel = null;
        ylabel = null;
        converter = null;
        value = null;
    }
    
    /**
     * <p>Transfer tag attributes to component properties.</p>
     */
    protected void setProperties(UIComponent _component)
    {
        super.setProperties(_component);
        if (alpha != null)
        {
            if (isValueReference(alpha))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(alpha);
                _component.setValueBinding("alpha", _vb);
            }
            else
            {
                _component.getAttributes().put("alpha", Integer.valueOf(alpha));
            }
        }
        if (antialias != null)
        {
            if (isValueReference(antialias))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(antialias);
                _component.setValueBinding("antialias", _vb);
            }
            else
            {
                _component.getAttributes().put("antialias", Boolean.valueOf(antialias));
            }
        }
        if (background != null)
        {
            if (isValueReference(background))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(background);
                _component.setValueBinding("background", _vb);
            }
            else
            {
                _component.getAttributes().put("background", background);
            }
        }
        if (border != null)
        {
            if (isValueReference(border))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(border);
                _component.setValueBinding("border", _vb);
            }
            else
            {
                _component.getAttributes().put("border", Integer.valueOf(border));
            }
        }
        if (categoryToPieByRow != null)
        {
            if (isValueReference(categoryToPieByRow))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(categoryToPieByRow);
                _component.setValueBinding("categoryToPieByRow", _vb);
            }
            else
            {
                _component.getAttributes().put("categoryToPieByRow", Boolean.valueOf(categoryToPieByRow));
            }
        }
        if (chart3d != null)
        {
            if (isValueReference(chart3d))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(chart3d);
                _component.setValueBinding("chart3d", _vb);
            }
            else
            {
                _component.getAttributes().put("chart3d", Boolean.valueOf(chart3d));
            }
        }
        if (colors != null)
        {
            if (isValueReference(colors))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(colors);
                _component.setValueBinding("colors", _vb);
            }
            else
            {
                _component.getAttributes().put("colors", colors);
            }
        }
        if (datasource != null)
        {
            if (isValueReference(datasource))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(datasource);
                _component.setValueBinding("datasource", _vb);
            }
            else
            {
                _component.getAttributes().put("datasource", datasource);
            }
        }
        if (dateFormat != null)
        {
            if (isValueReference(dateFormat))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(dateFormat);
                _component.setValueBinding("dateFormat", _vb);
            }
            else
            {
                _component.getAttributes().put("dateFormat", dateFormat);
            }
        }
        if (dateTick != null)
        {
            if (isValueReference(dateTick))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(dateTick);
                _component.setValueBinding("dateTick", _vb);
            }
            else
            {
                _component.getAttributes().put("dateTick", dateTick);
            }
        }
        if (dateTickCount != null)
        {
            if (isValueReference(dateTickCount))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(dateTickCount);
                _component.setValueBinding("dateTickCount", _vb);
            }
            else
            {
                _component.getAttributes().put("dateTickCount", Integer.valueOf(dateTickCount));
            }
        }
        if (depth != null)
        {
            if (isValueReference(depth))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(depth);
                _component.setValueBinding("depth", _vb);
            }
            else
            {
                _component.getAttributes().put("depth", Integer.valueOf(depth));
            }
        }
        if (domainNumberTick != null)
        {
            if (isValueReference(domainNumberTick))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(domainNumberTick);
                _component.setValueBinding("domainNumberTick", _vb);
            }
            else
            {
                _component.getAttributes().put("domainNumberTick", Double.valueOf(domainNumberTick));
            }
        }
        if (gradient != null)
        {
            if (isValueReference(gradient))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(gradient);
                _component.setValueBinding("gradient", _vb);
            }
            else
            {
                _component.getAttributes().put("gradient", Integer.valueOf(gradient));
            }
        }
        if (gradientType != null)
        {
            if (isValueReference(gradientType))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(gradientType);
                _component.setValueBinding("gradientType", _vb);
            }
            else
            {
                _component.getAttributes().put("gradientType", gradientType);
            }
        }
        if (height != null)
        {
            if (isValueReference(height))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(height);
                _component.setValueBinding("height", _vb);
            }
            else
            {
                _component.getAttributes().put("height", Integer.valueOf(height));
            }
        }
        if (includeZero != null)
        {
            if (isValueReference(includeZero))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(includeZero);
                _component.setValueBinding("includeZero", _vb);
            }
            else
            {
                _component.getAttributes().put("includeZero", Boolean.valueOf(includeZero));
            }
        }
        if (intervalMarker != null)
        {
            if (isValueReference(intervalMarker))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(intervalMarker);
                _component.setValueBinding("intervalMarker", _vb);
            }
            else
            {
                _component.getAttributes().put("intervalMarker", intervalMarker);
            }
        }
        if (label != null)
        {
            if (isValueReference(label))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(label);
                _component.setValueBinding("label", _vb);
            }
            else
            {
                _component.getAttributes().put("label", Boolean.valueOf(label));
            }
        }
        if (labelAngle != null)
        {
            if (isValueReference(labelAngle))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(labelAngle);
                _component.setValueBinding("labelAngle", _vb);
            }
            else
            {
                _component.getAttributes().put("labelAngle", Integer.valueOf(labelAngle));
            }
        }
        if (labelFormat != null)
        {
            if (isValueReference(labelFormat))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(labelFormat);
                _component.setValueBinding("labelFormat", _vb);
            }
            else
            {
                _component.getAttributes().put("labelFormat", labelFormat);
            }
        }
        if (legend != null)
        {
            if (isValueReference(legend))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(legend);
                _component.setValueBinding("legend", _vb);
            }
            else
            {
                _component.getAttributes().put("legend", Boolean.valueOf(legend));
            }
        }
        if (legendLabelFormat != null)
        {
            if (isValueReference(legendLabelFormat))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(legendLabelFormat);
                _component.setValueBinding("legendLabelFormat", _vb);
            }
            else
            {
                _component.getAttributes().put("legendLabelFormat", legendLabelFormat);
            }
        }
        if (legendPosition != null)
        {
            if (isValueReference(legendPosition))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(legendPosition);
                _component.setValueBinding("legendPosition", _vb);
            }
            else
            {
                _component.getAttributes().put("legendPosition", legendPosition);
            }
        }
        if (limitPercent != null)
        {
            if (isValueReference(limitPercent))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(limitPercent);
                _component.setValueBinding("limitPercent", _vb);
            }
            else
            {
                _component.getAttributes().put("limitPercent", Integer.valueOf(limitPercent));
            }
        }
        if (limitPercentTitle != null)
        {
            if (isValueReference(limitPercentTitle))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(limitPercentTitle);
                _component.setValueBinding("limitPercentTitle", _vb);
            }
            else
            {
                _component.getAttributes().put("limitPercentTitle", limitPercentTitle);
            }
        }
        if (orientation != null)
        {
            if (isValueReference(orientation))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(orientation);
                _component.setValueBinding("orientation", _vb);
            }
            else
            {
                _component.getAttributes().put("orientation", orientation);
            }
        }
        if (outline != null)
        {
            if (isValueReference(outline))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(outline);
                _component.setValueBinding("outline", _vb);
            }
            else
            {
                _component.getAttributes().put("outline", Boolean.valueOf(outline));
            }
        }
        if (periodAxisFormat != null)
        {
            if (isValueReference(periodAxisFormat))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(periodAxisFormat);
                _component.setValueBinding("periodAxisFormat", _vb);
            }
            else
            {
                _component.getAttributes().put("periodAxisFormat", periodAxisFormat);
            }
        }
        if (rangeNumberTick != null)
        {
            if (isValueReference(rangeNumberTick))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(rangeNumberTick);
                _component.setValueBinding("rangeNumberTick", _vb);
            }
            else
            {
                _component.getAttributes().put("rangeNumberTick", Double.valueOf(rangeNumberTick));
            }
        }
        if (startAngle != null)
        {
            if (isValueReference(startAngle))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(startAngle);
                _component.setValueBinding("startAngle", _vb);
            }
            else
            {
                _component.getAttributes().put("startAngle", Integer.valueOf(startAngle));
            }
        }
        if (style != null)
        {
            if (isValueReference(style))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(style);
                _component.setValueBinding("style", _vb);
            }
            else
            {
                _component.getAttributes().put("style", style);
            }
        }
        if (styleClass != null)
        {
            if (isValueReference(styleClass))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(styleClass);
                _component.setValueBinding("styleClass", _vb);
            }
            else
            {
                _component.getAttributes().put("styleClass", styleClass);
            }
        }
        if (subtitle != null)
        {
            if (isValueReference(subtitle))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(subtitle);
                _component.setValueBinding("subtitle", _vb);
            }
            else
            {
                _component.getAttributes().put("subtitle", subtitle);
            }
        }
        if (title != null)
        {
            if (isValueReference(title))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(title);
                _component.setValueBinding("title", _vb);
            }
            else
            {
                _component.getAttributes().put("title", title);
            }
        }
        if (tooltipFormat != null)
        {
            if (isValueReference(tooltipFormat))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(tooltipFormat);
                _component.setValueBinding("tooltipFormat", _vb);
            }
            else
            {
                _component.getAttributes().put("tooltipFormat", tooltipFormat);
            }
        }
        if (tooltipXDate != null)
        {
            if (isValueReference(tooltipXDate))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(tooltipXDate);
                _component.setValueBinding("tooltipXDate", _vb);
            }
            else
            {
                _component.getAttributes().put("tooltipXDate", Boolean.valueOf(tooltipXDate));
            }
        }
        if (tooltipYDate != null)
        {
            if (isValueReference(tooltipYDate))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(tooltipYDate);
                _component.setValueBinding("tooltipYDate", _vb);
            }
            else
            {
                _component.getAttributes().put("tooltipYDate", Boolean.valueOf(tooltipYDate));
            }
        }
        if (tooltips != null)
        {
            if (isValueReference(tooltips))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(tooltips);
                _component.setValueBinding("tooltips", _vb);
            }
            else
            {
                _component.getAttributes().put("tooltips", Boolean.valueOf(tooltips));
            }
        }
        if (type != null)
        {
            if (isValueReference(type))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(type);
                _component.setValueBinding("type", _vb);
            }
            else
            {
                _component.getAttributes().put("type", type);
            }
        }
        if (useChartlet != null)
        {
            _component.getAttributes().put("useChartlet", Boolean.valueOf(useChartlet));
        }
        if (verticalDateTicks != null)
        {
            if (isValueReference(verticalDateTicks))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(verticalDateTicks);
                _component.setValueBinding("verticalDateTicks", _vb);
            }
            else
            {
                _component.getAttributes().put("verticalDateTicks", Boolean.valueOf(verticalDateTicks));
            }
        }
        if (visibleShapes != null)
        {
            if (isValueReference(visibleShapes))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(visibleShapes);
                _component.setValueBinding("visibleShapes", _vb);
            }
            else
            {
                _component.getAttributes().put("visibleShapes", Boolean.valueOf(visibleShapes));
            }
        }
        if (width != null)
        {
            if (isValueReference(width))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(width);
                _component.setValueBinding("width", _vb);
            }
            else
            {
                _component.getAttributes().put("width", Integer.valueOf(width));
            }
        }
        if (xlabel != null)
        {
            if (isValueReference(xlabel))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(xlabel);
                _component.setValueBinding("xlabel", _vb);
            }
            else
            {
                _component.getAttributes().put("xlabel", xlabel);
            }
        }
        if (ylabel != null)
        {
            if (isValueReference(ylabel))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(ylabel);
                _component.setValueBinding("ylabel", _vb);
            }
            else
            {
                _component.getAttributes().put("ylabel", ylabel);
            }
        }
        if (converter != null)
        {
            if (isValueReference(converter))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(converter);
                _component.setValueBinding("converter", _vb);
            }
            else
            {
                Converter _converter = FacesContext.getCurrentInstance().
                        getApplication().createConverter(converter);
                _component.getAttributes().put("converter", _converter);
            }
        }
        if (value != null)
        {
            if (isValueReference(value))
            {
                ValueBinding _vb = getFacesContext().getApplication().createValueBinding(value);
                _component.setValueBinding("value", _vb);
            }
            else
            {
                _component.getAttributes().put("value", value);
            }
        }
    }
    
    // alpha
    private String alpha = null;
    public void setAlpha(String alpha)
    {
        this.alpha = alpha;
    }
    
    // antialias
    private String antialias = null;
    public void setAntialias(String antialias)
    {
        this.antialias = antialias;
    }
    
    // background
    private String background = null;
    public void setBackground(String background)
    {
        this.background = background;
    }
    
    // border
    private String border = null;
    public void setBorder(String border)
    {
        this.border = border;
    }
    
    // categoryToPieByRow
    private String categoryToPieByRow = null;
    public void setCategoryToPieByRow(String categoryToPieByRow)
    {
        this.categoryToPieByRow = categoryToPieByRow;
    }
    
    // chart3d
    private String chart3d = null;
    public void setChart3d(String chart3d)
    {
        this.chart3d = chart3d;
    }
    
    // colors
    private String colors = null;
    public void setColors(String colors)
    {
        this.colors = colors;
    }
    
    // datasource
    private String datasource = null;
    public void setDatasource(String datasource)
    {
        this.datasource = datasource;
    }
    
    // dateFormat
    private String dateFormat = null;
    public void setDateFormat(String dateFormat)
    {
        this.dateFormat = dateFormat;
    }
    
    // dateTick
    private String dateTick = null;
    public void setDateTick(String dateTick)
    {
        this.dateTick = dateTick;
    }
    
    // dateTickCount
    private String dateTickCount = null;
    public void setDateTickCount(String dateTickCount)
    {
        this.dateTickCount = dateTickCount;
    }
    
    // depth
    private String depth = null;
    public void setDepth(String depth)
    {
        this.depth = depth;
    }
    
    // domainNumberTick
    private String domainNumberTick = null;
    public void setDomainNumberTick(String domainNumberTick)
    {
        this.domainNumberTick = domainNumberTick;
    }
    
    // gradient
    private String gradient = null;
    public void setGradient(String gradient)
    {
        this.gradient = gradient;
    }
    
    // gradientType
    private String gradientType = null;
    public void setGradientType(String gradientType)
    {
        this.gradientType = gradientType;
    }
    
    // height
    private String height = null;
    public void setHeight(String height)
    {
        this.height = height;
    }
    
    // includeZero
    private String includeZero = null;
    public void setIncludeZero(String includeZero)
    {
        this.includeZero = includeZero;
    }
    
    // intervalMarker
    private String intervalMarker = null;
    public void setIntervalMarker(String intervalMarker)
    {
        this.intervalMarker = intervalMarker;
    }
    
    // label
    private String label = null;
    public void setLabel(String label)
    {
        this.label = label;
    }
    
    // labelAngle
    private String labelAngle = null;
    public void setLabelAngle(String labelAngle)
    {
        this.labelAngle = labelAngle;
    }
    
    // labelFormat
    private String labelFormat = null;
    public void setLabelFormat(String labelFormat)
    {
        this.labelFormat = labelFormat;
    }
    
    // legend
    private String legend = null;
    public void setLegend(String legend)
    {
        this.legend = legend;
    }
    
    // legendLabelFormat
    private String legendLabelFormat = null;
    public void setLegendLabelFormat(String legendLabelFormat)
    {
        this.legendLabelFormat = legendLabelFormat;
    }
    
    // legendPosition
    private String legendPosition = null;
    public void setLegendPosition(String legendPosition)
    {
        this.legendPosition = legendPosition;
    }
    
    // limitPercent
    private String limitPercent = null;
    public void setLimitPercent(String limitPercent)
    {
        this.limitPercent = limitPercent;
    }
    
    // limitPercentTitle
    private String limitPercentTitle = null;
    public void setLimitPercentTitle(String limitPercentTitle)
    {
        this.limitPercentTitle = limitPercentTitle;
    }
    
    // orientation
    private String orientation = null;
    public void setOrientation(String orientation)
    {
        this.orientation = orientation;
    }
    
    // outline
    private String outline = null;
    public void setOutline(String outline)
    {
        this.outline = outline;
    }
    
    // periodAxisFormat
    private String periodAxisFormat = null;
    public void setPeriodAxisFormat(String periodAxisFormat)
    {
        this.periodAxisFormat = periodAxisFormat;
    }
    
    // rangeNumberTick
    private String rangeNumberTick = null;
    public void setRangeNumberTick(String rangeNumberTick)
    {
        this.rangeNumberTick = rangeNumberTick;
    }
    
    // startAngle
    private String startAngle = null;
    public void setStartAngle(String startAngle)
    {
        this.startAngle = startAngle;
    }
    
    // style
    private String style = null;
    public void setStyle(String style)
    {
        this.style = style;
    }
    
    // styleClass
    private String styleClass = null;
    public void setStyleClass(String styleClass)
    {
        this.styleClass = styleClass;
    }
    
    // subtitle
    private String subtitle = null;
    public void setSubtitle(String subtitle)
    {
        this.subtitle = subtitle;
    }
    
    // title
    private String title = null;
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    // tooltipFormat
    private String tooltipFormat = null;
    public void setTooltipFormat(String tooltipFormat)
    {
        this.tooltipFormat = tooltipFormat;
    }
    
    // tooltipXDate
    private String tooltipXDate = null;
    public void setTooltipXDate(String tooltipXDate)
    {
        this.tooltipXDate = tooltipXDate;
    }
    
    // tooltipYDate
    private String tooltipYDate = null;
    public void setTooltipYDate(String tooltipYDate)
    {
        this.tooltipYDate = tooltipYDate;
    }
    
    // tooltips
    private String tooltips = null;
    public void setTooltips(String tooltips)
    {
        this.tooltips = tooltips;
    }
    
    // type
    private String type = null;
    public void setType(String type)
    {
        this.type = type;
    }
    
    // useChartlet
    private String useChartlet = null;
    public void setUseChartlet(String useChartlet)
    {
        this.useChartlet = useChartlet;
    }
    
    // verticalDateTicks
    private String verticalDateTicks = null;
    public void setVerticalDateTicks(String verticalDateTicks)
    {
        this.verticalDateTicks = verticalDateTicks;
    }
    
    // visibleShapes
    private String visibleShapes = null;
    public void setVisibleShapes(String visibleShapes)
    {
        this.visibleShapes = visibleShapes;
    }
    
    // width
    private String width = null;
    public void setWidth(String width)
    {
        this.width = width;
    }
    
    // xlabel
    private String xlabel = null;
    public void setXlabel(String xlabel)
    {
        this.xlabel = xlabel;
    }
    
    // ylabel
    private String ylabel = null;
    public void setYlabel(String ylabel)
    {
        this.ylabel = ylabel;
    }
    
    // converter
    private String converter = null;
    public void setConverter(String converter)
    {
        this.converter = converter;
    }
    
    // value
    private String value = null;
    public void setValue(String value)
    {
        this.value = value;
    }
    
    private static Class actionArgs[] = new Class[0];
    private static Class actionListenerArgs[] = { ActionEvent.class };
    private static Class validatorArgs[] = { FacesContext.class, UIComponent.class, Object.class };
    private static Class valueChangeListenerArgs[] = { ValueChangeEvent.class };
    
}
