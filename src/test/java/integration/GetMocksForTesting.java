package integration;

import java.util.ArrayList;
import java.util.List;
import com.everis.d4i.tutorial.json.CategoryRest;


public class GetMocksForTesting {
	
	public static List<CategoryRest> getMockListCategoryRest() {
		List<CategoryRest> categoryList = new ArrayList<>();
		categoryList.add(new CategoryRest(1L, "category 1"));
		categoryList.add(new CategoryRest(1L, "category 2"));
		categoryList.add(new CategoryRest(1L, "category 3"));
		categoryList.add(new CategoryRest(1L, "category 4"));
		return categoryList;
	}

}
