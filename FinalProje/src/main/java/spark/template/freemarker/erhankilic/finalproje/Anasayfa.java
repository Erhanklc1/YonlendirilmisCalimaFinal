package spark.template.freemarker.erhankilic.finalproje;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.FreeMarkerRoute;
import spark.template.freemarker.erhankilic.finalproje.birim.User;

import java.util.*;

public class Anasayfa {

	public static void sayfalariTanimla() {
		anaSayfaGET();
	}
	
	private static void anaSayfaGET() {
		
		FreeMarkerRoute anaSayfa = new FreeMarkerRoute("/FinalProje/anasayfa/") {
			@Override
			public Object handle(Request istek, Response cevap) {
				User user = UygulamaMain.girisYapmisKullanici;
				
				if (user == null) {
					System.out.println("Once giris yapilmali");
					cevap.redirect("/FinalProje/giris_sayfasi/");
				}
				
				Map<String, Object> sayfaVerisi = new HashMap<String, Object>();
				sayfaVerisi.put("kullaniciadi", user.getUserName());
				return new ModelAndView(sayfaVerisi, "/FinalProje/anasayfa.html"
				);
			}
		};
		get(anaSayfa);
	}
}