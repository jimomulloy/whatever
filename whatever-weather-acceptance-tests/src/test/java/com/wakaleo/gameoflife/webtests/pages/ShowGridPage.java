package com.wakaleo.gameoflife.webtests.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShowGridPage extends GameOfLifePage {

    @FindBy(id = "submit")
    WebElement nextButton;

    public ShowGridPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnNextGenerationButton() {
        clickOn(nextButton);
    }

    private String[] getCellsIn(WebElement row) {
        List<WebElement> cells = row.findElements(By.tagName("td"));
        List<String> rowContents = new ArrayList<String>();
        for (WebElement cell : cells) {
            String cellValue = cell.getText();
            rowContents.add(cellValue.trim());
        }
        return rowContents.toArray(new String[rowContents.size()]);
    }

    public String[][] getDisplayedGrid() {
        return getTableWithId("grid");
    }

    public String[][] getTableWithId(String tableId) {

        WebElement gridTable = getDriver().findElement(By.id(tableId));
        List<WebElement> rows = gridTable.findElements(By.tagName("tr"));
        List<String[]> tableContents = new ArrayList<String[]>();
        int numberOfColumns = 0;
        for (WebElement row : rows) {
            String[] rowContents = getCellsIn(row);
            numberOfColumns = rowContents.length;
            tableContents.add(rowContents);
        }
        return tableContentsAsAnArray(tableContents, numberOfColumns);
    }

    private String[][] tableContentsAsAnArray(List<String[]> tableContents, int numberOfColumns) {
        int numberOfRows = tableContents.size();
        return tableContents.toArray(new String[numberOfRows][numberOfColumns]);
    }

}
