package by.smirnov.chequeprintproject.printer;

public interface ChequeConstants {
    String DECIMAL = "###,###.##";
    String CHEQUE_DIV = "==========================================================\n";
    String CHEQUE_DIV_2 = "----------------------------------------------------------\n";
    String TABLE_FRAME =
            "----------------------------------------------------------\n";
    String TABLE_BLANK =
                    "                                                            \n";
    String TABLE_FORMAT_1 = "%58s\n";
    String TABLE_FORMAT_2 = "CASHIER: %-34s DATE: %tD\n";
    String TABLE_FORMAT_3 = "                                            TIME: %tT\n";
    String TABLE_FORMAT_POSITION = "%2d  %-39s $%3.2f $%4.2f\n";
    String TABLE_FORMAT_POSITIONS_HEADERS = "%-3s %-40s %-7s %-8s\n";
    String TABLE_FORMAT_4 = "%-50s $%s\n";
    String TABLE_AD = "*** Place your ad here ***";
    String TITLE = "CASH RECEIPT";
    int LINE_WIDTH = 58;
}
