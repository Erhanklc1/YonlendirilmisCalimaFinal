package spark.template.freemarker.erhankilic.finalproje;

import static spark.Spark.*;
import spark.*;
import spark.template.freemarker.*;
import spark.template.freemarker.erhankilic.finalproje.birim.User;

public class GirisSayfasi {

	public GirisSayfasi(String string, UygulamaMain uygulamaMain, String string2) {

	}
	

	public static void sayfalariTanimla() {

		girisSayfasiTanimla();
		girisFormPostTanimla();

	}

	private static void girisFormPostTanimla() {

		Route girisFormPostAction = new Route("/FinalProje/giris_sayfasi/login") {
			@Override
			public Object handle(Request istek, Response cevap) {

		
				String kullaniciadi = istek.queryParams("kullaniciadi"); 
				
				User kullanici = VeritabaniYonetimi.kullaniciSorgula(kullaniciadi);
				if(kullanici == null) {
					System.out.println("Verilen kullanici bulunamadi" + kullaniciadi);
					cevap.redirect("/erhankilic/giris_sayfasi/");
				} else {
					
					String password = istek.queryParams("sifre");
					
					if(kullanici.getPassword().equals(password)) {
						System.out.println("Sifre dogru. Giris basarili");
						UygulamaMain.girisYapmisKullanici = kullanici;
						cevap.redirect("/FinalProje/anasayfa");
					} else {
						System.out.println("Sifre hatali.");
						cevap.redirect("/FinalProje/giris_sayfasi/");
					}
				}
				
				return null;
			}
		};
		post(girisFormPostAction);

	}

	private static void girisSayfasiTanimla() {
		FreeMarkerRoute girisSayfasi = new FreeMarkerRoute("/FinalProje/giris_sayfasi/" ) {
			
			@Override
			public Object handle(Request istek, Response cevap) {
				return new ModelAndView(null, "/FinalProje/giris_sayfasi/" 
				);
			}
		};
		get(girisSayfasi);
	}
}




