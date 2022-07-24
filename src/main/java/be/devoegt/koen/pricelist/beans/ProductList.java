package be.devoegt.koen.pricelist.beans;

import java.util.ArrayList;
import java.util.LinkedHashSet;

public class ProductList {

	private ArrayList<Product> products;

	public ProductList(ArrayList<Product> products) {
		this.products = products;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	
	public void setPriceType(PriceType priceType) {
		for (Product product  : products) {
			product.setPriceType(priceType);
		}
	}

	public ArrayList<String> getCategories() {
		ArrayList<String> categories = new ArrayList<String>();
		LinkedHashSet<String> hashSet = new LinkedHashSet<String>();

		for (Product product : products) {
			String category = product.getCategory();
			if (hashSet.add(category))
				categories.add(category);
		}
		return categories;
	}

	public ArrayList<Product> getProductsByCategory(String category, PriceType priceType) {
		ArrayList<Product> productsByCategory = new ArrayList<Product>();
		
		for (Product product : products) {
			if (product.getCategory().contentEquals(category))
				productsByCategory.add(product);
		}
		return productsByCategory;
	}

}
