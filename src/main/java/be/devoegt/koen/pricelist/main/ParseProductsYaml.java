package be.devoegt.koen.pricelist.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import be.devoegt.koen.pricelist.beans.Product;
import be.devoegt.koen.pricelist.beans.ProductList;

public class ParseProductsYaml {

	private File productsFile;

	public ParseProductsYaml(String fileName) {
		productsFile = new File(fileName);
	}

	public File getFileName() {
		return productsFile;
	}

	public void setFileName(String fileName) {
		productsFile = new File(fileName);
	}

	public ProductList getProducts() {

		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Product.class);

		ArrayList<Product> productArrayList = null;
		try {
			productArrayList = mapper.readValue(productsFile, listType);
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ProductList(productArrayList);
	}

}
