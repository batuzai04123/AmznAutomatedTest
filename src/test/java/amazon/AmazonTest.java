package amazon;

import org.testng.annotations.Test;

public class AmazonTest extends BaseTest {
    @Override
    public void init() {}

    @Test(description = "Search Products in Amazon")
    public void searchProductsAmazon() {
        amazonHomePage.open()
                .searchContent("iPhone")
                .clickSearch()
                .selectCategory("Cell Phones")
                .setPriceRange("400", "500")
                .getTopFiveResultsAndPrintOutput();
    }
}