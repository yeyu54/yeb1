package com.yeyu.converter;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author 13474
 * @Package com.yeyu.converter
 * @date 2022/3/217:02
 */
@Component
public class DateConverter implements Converter<String,LocalDate> {
    @Override
        public LocalDate convert(String source) {
        try {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
