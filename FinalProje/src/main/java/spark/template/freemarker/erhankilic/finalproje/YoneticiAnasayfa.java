package spark.template.freemarker.erhankilic.finalproje;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.freemarker.erhankilic.finalproje.birim.Order;
import spark.template.freemarker.erhankilic.finalproje.birim.Product;
import spark.template.freemarker.erhankilic.finalproje.birim.User;

public class YoneticiAnasayfa { 
			UygulamaMain server;
		    String process = null;
		    public VeritabaniYonetimi veritabani;

		    protected YoneticiAnasayfa(UygulamaMain server, String process) {
		        this.server = server;
		        this.process = process;
		    }

		    public Object handle(Request request, Response response) {
		    	
		    	veritabani = new VeritabaniYonetimi();

		        if (UygulamaMain.girisYapmisKullanici == null) {
		        	server.hata = "Giris Yapmalisiniz!";
		            response.redirect("/FinalProje/hata/");
		            return null;
		        }

		        if (!server.girisYapmisKullanici.getUserType().equals("ADMIN")) {
		        	server.hata = "Yetkili Degilsiniz!";
		            response.redirect("/FinalProje/hata/");
		            return null;
		        }

		        if (process.equals("anasayfa")) {
		        	
		          /// BURADA TUM URUNLERI CEKECEK KODLARA IHTIYACIMIZ VAR - COZEMEDIK :(

		            return null;
		        }
		        
		        
		        //EKLEME ISLEMLERI

		        
		        if (process.equals("kullanici_ekle")) {
		            String kullaniciAdi = request.queryParams("kullaniciadi");
		            String kullaniciTipi = request.queryParams("kullanicitipi");
		            String sifre = request.queryParams("sifre");
		            String cinsiyet = request.queryParams("cinsiyet");
		            String yas = request.queryParams("yas");

		            if (kullaniciAdi == null || kullaniciTipi == null || sifre == null || cinsiyet == null || yas == null) {
		                System.out.println("Eksik bilgi girildi");
		                response.redirect("/FinalProje/yonetici_anasayfa/");
		                return null;
		            }

		            User kullanici = new User(kullaniciAdi, kullaniciTipi, Integer.parseInt(yas), cinsiyet, sifre);

		            veritabani.KullaniciEkle(kullanici);

		            System.out.println("KullaniciEklendi");
		            response.redirect("/FinalProje/yonetici_anasayfa");
		            return null;

		        }

		        if (process.equals("urun_ekle")) {
		        	String urunID = request.queryParams("urunID");
		            String urunAdi = request.queryParams("urunadi");
		            String urunKategori = request.queryParams("urunkategori");
		            String fiyat = request.queryParams("fiyat");

		            if (urunID == null || urunAdi == null || urunKategori == null || fiyat == null) {
		                System.out.println("Eksik bilgi girildi");
		                response.redirect("/FinalProje/yonetici_anasayfa/");
		                return null;
		            }

		            Product urun = new Product(Integer.parseInt(urunID), urunAdi, urunKategori, Integer.parseInt(fiyat));

		            veritabani.UrunEkle(urun);

		            response.redirect("/FinalProje/yonetici_anasayfa/");
		            return null;

		        }
		        
		        if (process.equals("siparis_ekle")) {
		        	String orderId = request.queryParams("siparis_id");
		            String userName = request.queryParams("kullanici_adi");
		            String productIDs = request.queryParams("urun_idler");
		            String sumPrice = request.queryParams("toplam_ucret");

		            if (orderId == null || userName == null || productIDs == null || sumPrice == null) {
		                System.out.println("Eksik bilgi girildi");
		                response.redirect("/FinalProje/yonetici_anasayfa/");
		                return null;
		            }

		            Order siparis = new Order(Integer.parseInt(orderId), userName, productIDs, Integer.parseInt(sumPrice));

		            veritabani.SiparisEkle(siparis);

		            response.redirect("/FinalProje/yonetici_anasayfa/");
		            return null;

		        }		        
		        
		        // SILME ISLEMLERI     
		        
		        
		        if (process.equals("kullanici_sil")) {

		            String kullaniciID = request.queryParams("kullanici_ýd");

		            if (kullaniciID == null) {
		                response.redirect("/FinalProje/yonetici_anasayfa/");
		                return null;
		            }

		            veritabani.KullaniciSil(kullaniciID);

		            response.redirect("/FinalProje/yonetici_anasayfa/");
		            return null;
		        }
		        
		        if (process.equals("urun_sil")) {

		            String urunID = request.queryParams("urun_ýd");

		            if (urunID == null) {
		                response.redirect("/FinalProje/yonetici_anasayfa/");
		                return null;
		            }

		            veritabani.UrunSil(urunID);

		            response.redirect("/FinalProje/yonetici_anasayfa/");
		            return null;
		        }
		        
		        if (process.equals("siparis_sil")) {

		            String siparisID = request.queryParams("siparis_ýd");

		            if (siparisID == null) {
		                response.redirect("/FinalProje/yonetici_anasayfa/");
		                return null;
		            }

		            veritabani.SiparisSil(siparisID);

		            response.redirect("/FinalProje/yonetici_anasayfa/");
		            return null;
		        }
		        
		        
		        

		        return "Yonetici Anasayfa";
		    }
}