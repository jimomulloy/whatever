package com.wakaleo.gameoflife.webtests.steps;

import static org.fest.assertions.Assertions.assertThat;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.pages.Pages;

import com.wakaleo.gameoflife.webtests.pages.EnterGridPage;
import com.wakaleo.gameoflife.webtests.pages.GameOfLifePage;
import com.wakaleo.gameoflife.webtests.pages.HomePage;
import com.wakaleo.gameoflife.webtests.pages.ShowGridPage;

public class PlayerSteps {

    private Pages pages;

    @Step
    public void chooses_to_start_a_new_game() {
        onHomePage().clickOnNewGameLink();
    }

    @Step
    public void clicks_on_cell_at(int row, int column) {
        onEnterGridPage().clickOnCellAt(row, column);
    }

    @Step
    public void clicks_on_home() {
        currentPage().clickOnHome();
    }

    public void continues_simulation() {
        onShowGridPage().clickOnNextGenerationButton();
    }

    private GameOfLifePage currentPage() {
        return pages.get(GameOfLifePage.class);
    }

    private EnterGridPage onEnterGridPage() {
        return pages.currentPageAt(EnterGridPage.class);
    }

    private HomePage onHomePage() {
        return pages.currentPageAt(HomePage.class);
    }

    private ShowGridPage onShowGridPage() {
        return pages.currentPageAt(ShowGridPage.class);
    }

    @Step
    public void opens_home_page() {
        onHomePage().open();
    }

    @Step
    public void should_see_a_page_containing_text(String expectedText) {
        currentPage().shouldContainText(expectedText);
    }

    @Step
    public void should_see_grid(String[][] expectedGrid) {
        assertThat(onShowGridPage().getDisplayedGrid()).isEqualTo(expectedGrid);
    }

    @Step
    public void should_see_title_of(String expectedTitle) {
        assertThat(currentPage().getTitle()).contains(expectedTitle);
    }

    @Step
    public void starts_simulation() {
        onEnterGridPage().clickOnGoButton();
    }
}
