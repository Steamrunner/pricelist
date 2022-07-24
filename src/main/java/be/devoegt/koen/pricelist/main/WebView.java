package be.devoegt.koen.pricelist.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

import be.devoegt.koen.pricelist.beans.PriceType;
import be.devoegt.koen.pricelist.beans.Product;
import be.devoegt.koen.pricelist.beans.ProductList;

@Route("")
public class WebView extends VerticalLayout implements HasUrlParameter<String> {

	private static final long serialVersionUID = 5563482149683861197L;

	private ComboBox<PriceType> comboBox = new ComboBox<PriceType>();

	@SuppressWarnings("deprecation")
	public WebView(@Autowired Environment env) {

		ParseProductsYaml parser = new ParseProductsYaml(env.getProperty("products.path"));
		ArrayList<Grid<Product>> grids = new ArrayList<Grid<Product>>();
		ProductList products = parser.getProducts();

		// Heading
		H1 heading = new H1(env.getProperty("organisation.name") + " - Pricelist");
		add(heading);
		setAlignItems(Alignment.CENTER);

		add(comboBox);

		// Body
		for (String category : products.getCategories()) {
			String capitalizedCatagory = category.substring(0, 1).toUpperCase() + category.substring(1);
			Accordion accordion = new Accordion();

			Grid<Product> grid = new Grid<Product>(Product.class, false);
			grid.addColumn(Product::getName);
			grid.addColumn(Product::getEuroPrice).setTextAlign(ColumnTextAlign.END);
			grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
			grid.addThemeVariants(GridVariant.LUMO_ROW_STRIPES);
			grid.setHeightByRows(true);

			List<Product> productsByCategory = products.getProductsByCategory(category, comboBox.getValue());
			grid.setItems(productsByCategory);
			accordion.add(capitalizedCatagory, grid).addThemeVariants(DetailsVariant.REVERSE);

			grids.add(grid);

			accordion.setWidth("1000px");
			add(accordion);
		}

		comboBox.setItems(PriceType.values());
		comboBox.addValueChangeListener(event -> {
			products.setPriceType(event.getValue());
			for (Grid<?> grid : grids) {
				grid.getDataProvider().refreshAll();
			}
		});
	}

	@Override
	public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {

		PriceType priceType = PriceType.REGULAR;
//		try {
//			priceType = PriceType.valueOf(parameter.toUpperCase());
//		} catch (IllegalArgumentException e) {
//		} catch (NullPointerException e) {
//		}
		comboBox.setValue(priceType);

	}
}
