package clinica.app.Clase;

public class EmployeeFired {
	String nume;
	String prenume;
	String cnp;
	
	
	
	
	public EmployeeFired(String nume, String prenume, String cnp) {
		super();
		this.nume = nume;
		this.prenume = prenume;
		this.cnp = cnp;
	}
	
	
	
	@Override
	public String toString() {
		return "EmployeeFired [nume=" + nume + ", prenume=" + prenume + ", cnp=" + cnp + "]";
	}



	public String getNume() {
		return nume;
	}
	public void setNume(String nume) {
		this.nume = nume;
	}
	public String getPrenume() {
		return prenume;
	}
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	public String getCnp() {
		return cnp;
	}
	public void setCnp(String cnp) {
		this.cnp = cnp;
	}
	
	
	

}
