package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.Helper;
import utils.Product;

import java.util.List;

public class CompareListOfItemsStepDefinition {
    private List<Product> firstList;
    private List<Product> secondList;
    private List<String> differences;
    Helper helper = new Helper();

    @Given("I have the following items in the first list")
    public void firstList(DataTable table) {
        firstList = helper.convertTableToProductList(table);
    }

    @Given("I have the following items in the second list")
    public void secondList(DataTable table) {
        secondList = helper.convertTableToProductList(table);
    }

    @When("I compare both lists")
    public void compareLists() {
        differences = helper.compareTwoLists(firstList, secondList);
    }

    @Then("the lists should contain the same items with name, price, and category, regardless of order")
    public void differences() {
        helper.printOutDifferences(differences);
    }

}
