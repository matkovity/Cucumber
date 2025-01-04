package utils;

import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.List;

public class Helper {
    private final List<String> differences = new ArrayList<>();

    // Helper method to convert a DataTable into a list of Product objects
    public List<Product> convertTableToProductList(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);
        List<Product> productList = new ArrayList<>();

        // Skip the header row and convert each row to a Product object
        for (int i = 1; i < rows.size(); i++) {
            List<String> row = rows.get(i);
            String name = row.get(0);
            double price = Double.parseDouble(row.get(1));
            String category = row.get(2);
            productList.add(new Product(name, price, category));
        }
        return productList;
    }

    public List<String> compareTwoLists(List<Product> firstList, List<Product> secondList) {
        for (int i = 0; i < firstList.size(); i++) {
            Product productFromFirstList = firstList.get(i);
            boolean productMatchFound = false;
            for (int j = 0; j < secondList.size(); j++) {
                Product productFromSecondList = secondList.get(j);
                if (productFromFirstList.getName().equals(productFromSecondList.getName())) {
                    productMatchFound = true;
                    if (!(productFromFirstList.getPrice() == productFromSecondList.getPrice())) {
                        differences.add("Difference detected on Name: " + productFromFirstList.getName() + ", for Price: on first List:" + productFromFirstList.getPrice() + ", on second List:" + productFromSecondList.getPrice());
                    }
                    if (!productFromFirstList.getCategory().equals(productFromSecondList.getCategory())) {
                        differences.add("Difference detected on Name: " + productFromFirstList.getName() + ", for Category: on first List:" + productFromFirstList.getCategory() + ", on second List:" + productFromSecondList.getCategory());
                    }
                    break;
                }
            }
            if (!productMatchFound) {
                differences.add("Difference detected on Item which is Missing from second list: Name:" + productFromFirstList.getName() + ", Price" + productFromFirstList.getPrice() + ", Category:" + productFromFirstList.getCategory());
            }
        }
        return differences;
    }


    public void printOutDifferences(List<String> differences) {
        if (differences.isEmpty()) {
            System.out.println("There were no differences!");
        } else {
            System.out.println(differences);
        }
    }
}
