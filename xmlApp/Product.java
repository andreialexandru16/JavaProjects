package testJobXml;

public class Product {
	String description;
	String gtin;
	float price;
	String currency;
	String supplier;

	public Product() {
		super();
	}

	public Product(String description, String gtin, float price, String currency, String supplier) {
		super();
		this.description = description;
		this.gtin = gtin;
		this.price = price;
		this.currency = currency;
		this.supplier = supplier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	@Override
	public String toString() {
		return "Product [description=" + description + ", gtin=" + gtin + ", price=" + price + ", currency=" + currency
				+ ", supplier=" + supplier + "]";
	}

}
