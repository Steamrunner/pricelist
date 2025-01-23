package be.devoegt.koen.pricelist.beans;

public class Product {

	private String name;
	private double price;
	private double paying_member_price;
	private double event_price;
	private String currency;
	private String category;
	private PriceType priceType = PriceType.REGULAR;
	private Boolean visible;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public String getEuroPrice() {
		switch (priceType) {
		case REGULAR:
			return "€ " + getPrice();
		case MEMBER:
			return "€ " + getPaying_member_price();
		case EVENT:
			return "€ " + getEvent_price();
		default:
			return "€ " + getPrice();
		}
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPaying_member_price() {
		return paying_member_price;
	}

	public void setPaying_member_price(double paying_member_price) {
		this.paying_member_price = paying_member_price;
	}

	public double getEvent_price() {
		return event_price;
	}

	public void setEvent_price(double event_price) {
		this.event_price = event_price;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public PriceType getPriceType() {
		return priceType;
	}

	public void setPriceType(PriceType priceType) {
		this.priceType = priceType;
	}

	public Boolean isVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + ", paying_member_price=" + paying_member_price
				+ ", event_price=" + event_price + ", currency=" + currency + ", category=" + category + "]";
	}

}
