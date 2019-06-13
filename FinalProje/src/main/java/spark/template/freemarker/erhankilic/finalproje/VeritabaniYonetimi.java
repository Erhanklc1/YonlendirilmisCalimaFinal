package spark.template.freemarker.erhankilic.finalproje;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import spark.template.freemarker.erhankilic.finalproje.birim.Order;
import spark.template.freemarker.erhankilic.finalproje.birim.Product;
import spark.template.freemarker.erhankilic.finalproje.birim.User;


public class VeritabaniYonetimi {

	private static Statement veritabaniStatement;
	
	public static Statement veritabaniBaglantisiKur() {
		try {
			
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			
			Connection veritabaniBaglantisi = DriverManager.getConnection("jdbc:derby:"+ ".\\.erhankilicdb\\"+ ";create=true;"
					);
			
			veritabaniStatement = veritabaniBaglantisi.createStatement();
			
			return veritabaniStatement;
			
		} catch(Exception e) {
			System.out.println("Veritabanina baglanirken hata olustu");
		}
		
		return null;
	}
	
	public static boolean tablolariOlustur() {
		
		try  {
			veritabaniStatement.execute("create table kullanicilar(" +
                    "kullanici_adi varchar(50)," +
                    "kullanici_tipi varchar(50)," +
                    "yas int," +
                    "cinsiyet varchar(50)," +
                    "sifre varchar(50))");
            System.out.println("Kullanici tablosu olusturuldu!");
 
            veritabaniStatement.execute("create table urunler(" +
                    "urun_id int," +
                    "urun_adi varchar(255)," +
                    "urun_kategori varchar(255)," +
                    "fiyat int)");
            System.out.println("Urunler tablosu olusturuldu!");


            veritabaniStatement.execute("create table siparisler(" +
                    "siparis_id int," +
                    "kullanici_adi varchar(255)," +
                    "urun_idler varchar(255)," +
                    "toplam_ucret int)");
            System.out.println("Siparisler tablosu olusturuldu!");
			
			
		} catch(Exception e) {
			
			System.out.println("Tablo zaten mevcut. Tekrar olusturulmayacak: " + e.getMessage());
		}
		
		return true;
	}
	
	
//SORGU ISLEMLERI	
	public static User kullaniciSorgula(String kullaniciAdi) {
		
		try  {
			ResultSet sonuclar = veritabaniStatement.executeQuery(
					"SELECT * FROM kullanicilar WHERE kullanici_adi = '" + kullaniciAdi + "'"
			);
			while(sonuclar.next()) {
				String kullaniciTipi = sonuclar.getString("kullanici_tipi");
				int yas = sonuclar.getInt("yas");
				String cinsiyet = sonuclar.getString("cinsiyet");
				String sifre = sonuclar.getString("sifre");
				return new User(kullaniciAdi, kullaniciTipi, yas, cinsiyet, sifre);
			}
		} catch(Exception e) {
			System.out.println("Kullanici sorgu hata" + e.getMessage());
		}
		return null;
	}
	

    public Product urunSorgula(String urunId){

        try {
            ResultSet urunSorgu = this.veritabaniStatement.executeQuery("SELECT * FROM urunler WHERE urun_id=" + Integer.parseInt(urunId));

            while (urunSorgu.next()) {
                Product urun = new Product(
                		urunSorgu.getInt("urun_id"),
                		urunSorgu.getString("urun_adi"),
                		urunSorgu.getString("urun_kategori"),
                		urunSorgu.getInt("fiyat")
                );
                
                return urun;
            }
        } catch (Exception e) {
            System.out.println("Urun bulunamadi : " + e.getMessage());
        }

        return null;

    }
    
    public Order siparisSorgula(String siparisId){

        try {
            ResultSet siparisSorgu = this.veritabaniStatement.executeQuery("SELECT * FROM siparisler WHERE siparis_id=" + Integer.parseInt(siparisId));

            while (siparisSorgu.next()) {
                Order siparis = new Order(
                		siparisSorgu.getInt("siparis_id"),
                		siparisSorgu.getString("kullanici_adi"),
                		siparisSorgu.getString("urun_idler"),
                		siparisSorgu.getInt("toplam_ucret")
                );

              
                return siparis;
            }
        } catch (Exception e) {
            System.out.println("Urun bulunamadi : " + e.getMessage());
        }

        return null;

    }
    
    
 //EKLEME ISLEMLERI   
    
    
    public void KullaniciEkle(User kullanici) {
        try {
            veritabaniStatement.execute("INSERT INTO kullanicilar(kullanici_adi,kullanici_tipi,yas,cinsiyet,sifre)" +
                    "VALUES('" + kullanici.getUserName() + "','" + kullanici.getUserType() + "', " +
                    kullanici.getAge() + ", '" + kullanici.getGender() + "', '" + kullanici.getPassword() + "')");

        } catch (Exception e) {
            System.out.println("Kullanici eklenemedi : " + e.getMessage());
        }
    }

    public void UrunEkle(Product urun) {
        try {
            veritabaniStatement.execute("INSERT INTO urunler(urun_id, urun_adi, urun_kategori, fiyat)" +
                    "VALUES("+ urun.getProductId() +",'" + urun.getProductName() + "', '" +
                    urun.getProductCategory() + "', " + urun.getPrice() + ")");

        } catch (Exception e) {
            System.out.println("Urun eklenemedi : " + e.getMessage());
        }
    }


    public void SiparisEkle(Order siparis){
        try {
            veritabaniStatement.execute("INSERT INTO siparisler(siparis_id, kullanici_adi, urun_idler, toplam_ucret)" +
                    "VALUES(" + siparis.getOrderId() + ",'" + siparis.getUserName() + "', '" +
                    siparis.getProductIDs() + "', " + siparis.getSumPrice() + ")");

        } catch (Exception e) {
            System.out.println("Siparis eklenemedi : " + e.getMessage());
        }
    }
    

//SILME ISLEMLERI
    
    
    public void KullaniciSil(String kullaniciAdi) {
        try {
            veritabaniStatement.execute("DELETE FROM kullanicilar WHERE kullanici_adi='" + kullaniciAdi +"'");

        } catch (Exception e) {
            System.out.println("Kullanici silinemedi : " + e.getMessage());
        }
    }
	
    public void UrunSil(String UrunId) {
        try {
        	veritabaniStatement.execute("DELETE FROM urunler WHERE urun_id='" + UrunId +"'");

        } catch (Exception e) {
            System.out.println("Urun silinemedi : " + e.getMessage());
        }
    }
    
    public void SiparisSil(String SiparisId) {
        try {
        	veritabaniStatement.execute("DELETE FROM siparisler WHERE siparis_id='" + SiparisId +"'");

        } catch (Exception e) {
            System.out.println("Siparis silinemedi : " + e.getMessage());
        }
    }
	
	
	
	
	
	
}