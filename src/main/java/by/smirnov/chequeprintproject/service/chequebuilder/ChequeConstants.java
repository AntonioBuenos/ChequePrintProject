package by.smirnov.chequeprintproject.service.chequebuilder;

import org.apache.commons.lang3.StringUtils;

public interface ChequeConstants {
    String DECIMAL = "###,###.##";
    String CHEQUE_FRAME = StringUtils.repeat("-",58) + "\n";
    String CHEQUE_DIV = StringUtils.repeat("=",58) + "\n";
    String BLANK_LINE = "\n";
    String FORMAT_LINE = "%58s\n";
    String FORMAT_DATE = "CASHIER: %-34s DATE: %tD\n";
    String TIME = "TIME:";
    String FORMAT_TIME = "%49s %tT\n";
    String FORMAT_POSITION = "%2d  %-39s $%3.2f $%4.2f\n";
    String FORMAT_POSITIONS_HEADERS = "%-3s %-39s %-6s %-6s\n";
    String FORMAT_FOOTER = "%-50s $%s\n";
    String AD = "*** Place your ad here ***";
    String TITLE = "CASH RECEIPT";
    String QTY = "QTY";
    String DESC = "DESCRIPTION";
    String PRICE = "PRICE";
    String TOTAL = "TOTAL";
    String SUM = "SUM";
    String PROMO_DISC = "PROMO DISCOUNT";
    String CARD_DISC = "CARD DISCOUNT";
    String TAXABLE = "TAXABLE TOT.";
    String VAT = "VAT20%";
    String TOTAL_AMT = "TOTAL";
    String PHONE = "tel. ";
    int LINE_WIDTH = 58;
}
