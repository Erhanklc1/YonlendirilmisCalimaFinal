package spark.template.freemarker.erhankilic.finalproje;

import java.util.ArrayList;
import java.util.List;

import spark.Spark;
import spark.template.freemarker.erhankilic.finalproje.birim.Product;
import spark.template.freemarker.erhankilic.finalproje.birim.User;

public class UygulamaMain {
    public static User girisYapmisKullanici = null;
	public String hata = null; // hata durumunda kullanýlacak degisken tanimlamasi
	
	
		public static void main(String[] args) {
		
			VeritabaniYonetimi.veritabaniBaglantisiKur();
			VeritabaniYonetimi.tablolariOlustur();
			GirisSayfasi.sayfalariTanimla();
			UyeSayfasi.sayfalariTanimla();
			Anasayfa.sayfalariTanimla();
	}
		
}
