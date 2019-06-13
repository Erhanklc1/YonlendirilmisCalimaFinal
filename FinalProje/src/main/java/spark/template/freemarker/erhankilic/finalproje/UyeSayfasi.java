package spark.template.freemarker.erhankilic.finalproje;



import static spark.Spark.*;
import spark.*;
import spark.template.freemarker.*;

public class UyeSayfasi {

	public static void sayfalariTanimla() {
		
		FreeMarkerRoute girisSayfasi = new FreeMarkerRoute(
				"/FinalProje/kayit_sayfasi/" 
		) {
			@Override
			public Object handle(Request istek, Response cevap) {
				return new ModelAndView(
						null, 
						"/FinalProje/uye_sayfasi.html"
				);
			}
		};
		get(girisSayfasi);
	}
}