package DieuKhien;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class QuyDoi {
	public static String QUSo(int so) {
		 Locale localeVN = new Locale("vi", "VN");
		 NumberFormat numberFormat = NumberFormat.getInstance(localeVN);
		 return numberFormat.format(so);
		    
	}

}
