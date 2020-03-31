package clinica.app.Clase;

public class Calculator {
     
	 public int venituriTotaleLunar;
	 public int venitAngajatLunar;
	 
	public Calculator(int venituriTotaleLunar, int venitAngajatLunar) {
		super();
		this.venituriTotaleLunar = venituriTotaleLunar;
		this.venitAngajatLunar = venitAngajatLunar;
	}
	 
	
	 public boolean decideBonus(int numarAngajati) {
		 if(this.venitAngajatLunar>=this.venituriTotaleLunar/numarAngajati) {
			 return true;
		 }else {
			 return false;
		 }
		 
	 }
  
	 
	 
	 
	 
	 
	
	
	
}
