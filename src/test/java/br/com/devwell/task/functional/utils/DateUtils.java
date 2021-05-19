package br.com.devwell.task.functional.utils;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static String oneFutureDateString(){
        return formatDatePtString(LocalDate.now().plusDays(1));
    }

    public static String formatDatePtString(LocalDate date){
        return date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
