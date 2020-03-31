package phonebook.app;

import java.util.Date;

public class Contact {
	int id;
   String gen;
   String Surname;
   String FirstName;
   Date date;
   String adresa;
   String telefon;
   int tipNumar;
   int favorite;
   String notes;
   
   
   
   
   
   
public Contact(int id, String gen, String surname, String firstName, Date date, String adresa, String telefon,
		int tipNumar, int favorite, String notes) {
	super();
	this.id = id;
	this.gen = gen;
	Surname = surname;
	FirstName = firstName;
	this.date = date;
	this.adresa = adresa;
	this.telefon = telefon;
	this.tipNumar = tipNumar;
	this.favorite = favorite;
	this.notes = notes;
}






public int getId() {
	return id;
}






public void setId(int id) {
	this.id = id;
}






public String getGen() {
	return gen;
}






public void setGen(String gen) {
	this.gen = gen;
}






public String getSurname() {
	return Surname;
}






public void setSurname(String surname) {
	Surname = surname;
}






public String getFirstName() {
	return FirstName;
}






public void setFirstName(String firstName) {
	FirstName = firstName;
}






public Date getDate() {
	return date;
}






public void setDate(Date date) {
	this.date = date;
}






public String getAdresa() {
	return adresa;
}






public void setAdresa(String adresa) {
	this.adresa = adresa;
}






public String getTelefon() {
	return telefon;
}






public void setTelefon(String telefon) {
	this.telefon = telefon;
}






public int getTipNumar() {
	return tipNumar;
}






public void setTipNumar(int tipNumar) {
	this.tipNumar = tipNumar;
}






public int getFavorite() {
	return favorite;
}






public void setFavorite(int favorite) {
	this.favorite = favorite;
}






public String getNotes() {
	return notes;
}






public void setNotes(String notes) {
	this.notes = notes;
}






@Override
public String toString() {
	return "Contact [id=" + id + ", gen=" + gen + ", Surname=" + Surname + ", FirstName=" + FirstName + ", date=" + date
			+ ", adresa=" + adresa + ", telefon=" + telefon + ", tipNumar=" + tipNumar + ", favorite=" + favorite
			+ ", notes=" + notes + "]";
}
   

   
   
   
   
   
   
}
