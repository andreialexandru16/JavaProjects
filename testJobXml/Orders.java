package testJobXml;

import java.util.List;

public class Orders {
	int id;
	String data;
	List<Product> lista;
	
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public List<Product> getLista() {
		return lista;
	}
	public void setLista(List<Product> lista) {
		this.lista = lista;
	}
	@Override
	public String toString() {
		return "Orders [id=" + id + ", data=" + data + ", lista=" + lista + "]";
	}
	

}
