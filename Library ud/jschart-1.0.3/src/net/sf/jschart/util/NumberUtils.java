package net.sf.jschart.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * <p>Provides extra functionality for Java Number classes.</p>
 */
public class NumberUtils
{
    public static Number createNumber(Locale locale, Object obj) throws ParseException
    {
        if (obj instanceof Date)
        {
            Date date = (Date) obj;
            Number value = new Long(date.getTime());
            return value;
        }
        else if (obj instanceof Number)
        {
            return (Number)obj;
        }
        else if (obj instanceof Boolean)
        {
            return ((Boolean)obj).booleanValue() ? 1 : 0;
        }
        else if (obj instanceof Character ||
                obj instanceof String)
        {
            String s = (String)obj;
            return NumberFormat.getNumberInstance(locale).parse(s);
        }
        
        return new Integer(0);
    }
}
