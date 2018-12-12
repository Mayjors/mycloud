package com.example.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 前端时间传输到后台转换Date
 * @author yuanjie
 * @date 2018/7/30 18:08
 */
@Component
public class CustomDate implements Converter<String, Date> {
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomDate.class);

    private static final List<String> formarts = new ArrayList<>();
    static {
        formarts.add("yyyy-MM-dd");
        formarts.add("yyyy-MM-dd hh:mm");
        formarts.add("yyyy-MM-dd hh:mm:ss");
    }

    @Override
    public Date convert(String source) {
        String value = source.trim();
        if ("".equals(value)) {
            return null;
        }
        // 只对时间戳进行转换， 不对其他类型进行转换
        if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")){
            return parseDate(source, formarts.get(0));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")){
            return parseDate(source, formarts.get(1));
        }else if(source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")){
            //String f = formarts.get(2);
            return parseDate(source, formarts.get(2));
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Long time = Long.parseLong(source);
        source = simpleDateFormat.format(time);
        Date date = null;
        try {
            date = simpleDateFormat.parse(source);
        } catch (ParseException e) {
            //e.printStackTrace();
            LOG.info("exception:"+e);
        }
        return date;
    }

    /**
     * 格式化日期
     * @param dateStr String 字符型日期
     * @param format String 格式
     * @return Date 日期
     */
    public  Date parseDate(String dateStr, String format) {
        Date date=null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(dateStr);
        } catch (Exception e) {
            //e.printStackTrace();
            LOG.info("exception:"+e);
        }
        return date;
    }

}
