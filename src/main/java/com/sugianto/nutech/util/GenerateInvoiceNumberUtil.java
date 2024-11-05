package com.sugianto.nutech.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateInvoiceNumberUtil {

    public static String generateInvoiceNumber(long count) {
        String prefix = "INV";
        String date = new SimpleDateFormat("ddMMyyyy").format(new Date());
        String sequenceNumber = String.format("%03d", count + 1);
        return prefix + date + "-" + sequenceNumber;
    }

}
