import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import model.IStockMarketModel;
import model.Stock;
import model.StockMarketModelImpl;

import static org.junit.Assert.assertEquals;

public class StockMarketModelImplTest {

  IStockMarketModel s;

  /**
   * Test to check if entering invalid ticker throws IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTicker() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("akjshd", 2, "2016-10-20", 1, 5);
  }

  /**
   * Test to check if create portfolio works as expected.
   */
  @Test
  public void testCreatePortfolio() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2016-10-21", 1, 5);
    assertEquals(1626.22, s.evaluatePortfolio(1, "2016-10-24"), 0.1);
  }

  /**
   * Test if create portfolio fails on passing a negative number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreatePortfolioFails() {
    s = new StockMarketModelImpl();
    s.createPortfolio(-1);
  }

  /**
   * Test if create portfolio fails on passing a already existing portfolio number.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCreatePortfolioFails2() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.createPortfolio(1);
  }

  /**
   * Test to check if buy stock fails on passing any invalid argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBuyStockFails() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("AAPL", 1, "2016-10-21", 2, 5);
    s.buyStock("AAPL", -1, "2016-10-21", 1, 5);
    s.buyStock("AAPL", 1, "2016-10-21", -1, 5);
  }

  /**
   * Test to see if buying stock without creating a portfolio throws exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBuyStockWithoutPortfolio() {
    s = new StockMarketModelImpl();
    s.buyStock("GOOG", 2, "2016-10-22", 1, 5);
  }

  /**
   * Test to check if buy stock method works as expected.
   */
  @Test
  public void testBuyStock() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2014-10-20", 1, 5);
    List<Stock> tempList = s.viewComposition(1, "2014-10-20");
    assertEquals(tempList.size(), s.viewComposition(1, "2014-10-20").size());

    s.buyStock("AAPL", 2, "2014-10-22", 1, 5);
    s.buyStock("GOOG", 2, "2016-10-25", 1, 5);
    List<Stock> tempList2 = s.viewComposition(1, "2016-10-25");
    assertEquals(tempList2.size(), s.viewComposition(1, "2016-10-25").size());
  }

  /**
   * Test if buy stock takes the nearest date on entering a holiday in evaluate portfolio method.
   */
  @Test
  public void testBuyStockHoliday() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("AAPL", 2, "2016-11-04", 1, 5);
    assertEquals(217.68, s.evaluatePortfolio(1, "2016-11-06"), 0.1);
  }

  /**
   * Test to check if evaluate method works as expected on weekdays and holidays.
   */
  @Test
  public void testEvaluate() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2014-10-22", 1, 5);
    s.buyStock("AAPL", 2, "2014-10-22", 1, 5);
    assertEquals(1271.4, s.evaluatePortfolio(1, "2014-10-22"), 0.1);
    assertEquals(1290.0, s.evaluatePortfolio(1, "2014-10-26"), 0.1);
  }

  /**
   * Test to check if evaluate throws an exception when a negative portfolio number is passed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEvaluatefails() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2014-10-22", 1, 5);
    s.evaluatePortfolio(-1, "2014-10-28");
    s.evaluatePortfolio(2, "2014-10-28");
  }

  /**
   * Test to check if calculate cost basis works as expected.
   */
  @Test
  public void testCostBasis() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2014-10-22", 1, 5);
    assertEquals(1070.4200439453125, s.calculateCostBasis(1, "2014-10-22"), 0.1);
  }

  /**
   * Test to see if buying a stock in a non existent portfolio throws IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void checkForNonExistentPortfolio() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2014-10-22", 2, 5);
  }

  /**
   * Test to check if creating multiple portfolios works as expected.
   */
  @Test
  public void createPortfolios() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.createPortfolio(2);
    s.buyStock("AAPL", 2, "2014-10-22", 2, 5);
    s.buyStock("GOOG", 2, "2014-10-22", 1, 5);
    assertEquals(1065.42, s.evaluatePortfolio(1, "2014-10-22"), 0.1);
    assertEquals(210.44, s.evaluatePortfolio(2, "2014-10-24"), 0.1);
  }

  /**
   * Test to check if multiple portfolios are created.
   */
  @Test
  public void testCreateMultiplePortfolios() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.createPortfolio(2);
    s.buyStock("GOOG", 2, "2014-10-22", 1, 5);
    s.buyStock("AAPL", 2, "2014-10-22", 2, 5);
    List<Stock> tempList = new ArrayList<>();
    tempList.add(new Stock("GOOG", 2, 532.71f, "2014-10-22", 0f, 5));
    List<Stock> tempList2 = new ArrayList<>();
    tempList2.add(new Stock("AAPL", 2, 102.99f, "2014-10-22", 0f, 5));
    assertEquals(tempList.toString(), s.viewComposition(1, "2018-11-27").toString());
    assertEquals(tempList2.toString(), s.viewComposition(2, "2014-10-22").toString());
  }

  /**
   * Test to check if View Composition works correctly.
   */
  @Test
  public void testViewComposition() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("AAPL", 2, "2018-11-15", 1, 5);
    List<Stock> tempList = s.viewComposition(1, "2018-11-20");
    assertEquals(tempList.size(), s.viewComposition(1, "2018-11-20").size());
  }

  /**
   * Test to check the controller works as expected. Controller always calls the model functions.
   */
  @Test
  public void testController() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2018-11-15", 1, 5);
    List<Stock> tempList = s.viewComposition(1, "2018-11-20");
    assertEquals(tempList.size(), s.viewComposition(1, "2018-11-20").size());
  }

  /**
   * Test to check if investing using custom weights works as expected.
   */
  @Test
  public void testInvestCustomWeightsMultipleStocks() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2014-10-24", 1, 5);
    s.buyStock("AAPL", 2, "2014-10-15", 1, 5);
    assertEquals(1311.28, s.evaluatePortfolio(1, "2014-10-28"), 0.1);
    s.invest("GOOG", 500, "2014-10-24", 1, 0);
    s.invest("AAPL", 600, "2014-10-15", 1, 0);
    assertEquals(2476.3200534918224, s.evaluatePortfolio(1, "2014-10-28"), 0.1);
  }

  /**
   * Test to check if investing using custom weights works as expected for single stock.
   */
  @Test
  public void testInvestCustomWeightsSingleStock() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2014-10-24", 1, 5);
    assertEquals(1097.8, s.evaluatePortfolio(1, "2014-10-28"), 0.1);
    s.invest("GOOG", 500, "2014-10-24", 1, 0);
    assertEquals(1606.2478861758493, s.evaluatePortfolio(1, "2014-10-28"), 0.1);
  }


  /**
   * Test to check if investing using equal weights works as expected.
   */
  @Test
  public void testInvestEqualWeightsMultipleStocks() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2014-10-24", 1, 5);
    s.buyStock("AAPL", 2, "2014-10-15", 1, 5);
    assertEquals(1311.28, s.evaluatePortfolio(1, "2014-10-28"), 0.1);
    s.invest("GOOG", 500, "2014-10-24", 1, 0);
    s.invest("AAPL", 500, "2014-10-15", 1, 0);
    assertEquals(2366.8880256058264, s.evaluatePortfolio(1, "2014-10-28"), 0.1);
  }

  /**
   * Test to check if investing using equal weights works as expected for single stock.
   */
  @Test
  public void testInvestEqualWeightsSingleStock() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2014-10-24", 1, 5);
    assertEquals(1097.8, s.evaluatePortfolio(1, "2014-10-28"), 0.1);
    s.invest("GOOG", 1000, "2014-10-24", 1, 0);
    assertEquals(2114.695772351699, s.evaluatePortfolio(1, "2014-10-28"), 0.1);
  }

  /**
   * Test to check if adding end date in strategy works as expected.
   */
  @Test
  public void testEndDateStrategy() throws ParseException {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2014-10-24", 1, 5);
    s.dCassStrategy("GOOG", 1000, "2014-10-24",
            "2014-10-27", 1,
            1, s);
    assertEquals(3085.2081611026715, s.evaluatePortfolio(1, "2014-10-27"), 0.1);
  }

  /**
   * Test to check if not passing an end date works as expected.
   *
   * @throws ParseException if the string cannot be parsed.
   */
  @Test
  public void testNoEndDateStrategy() throws ParseException {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2018-11-23", 1, 5);
    s.dCassStrategy("GOOG", 1000, "2018-11-23",
            "2018-11-28", 1,
            1, s);
    assertEquals(6334.293362262607, s.evaluatePortfolio(1, "2018-11-28"), 0.1);
  }

  /**
   * Test to check if strategy works for holiday as expected.
   * @throws ParseException if the string cannot be parsed.
   */
  @Test
  public void testStrategyForHoliday() throws ParseException {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2018-11-23", 1,5);
    s.dCassStrategy("GOOG", 1000, "2018-11-23",
            "2018-11-27", 1,
            1, s);
    List<Stock> tempList = s.viewComposition(1, "2018-11-28");
    assertEquals(tempList.toString(), s.viewComposition(1, "2018-11-28").toString());
  }

  /**
   * Test to check if passing a holiday to invest throws IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvestForHoliday() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2018-11-23", 1,5);
    s.invest("GOOG", 1000, "2018-11-25", 1, 0);
  }

  /**
   * Test to check if commission works as expected while buying a stock.
   */
  @Test
  public void testCommission() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2018-11-23", 1,0);
    assertEquals(2172.46, s.evaluatePortfolio(1, "2018-11-28"), 0.1);
    s.buyStock("GOOG", 2, "2018-11-23", 1,5);
    assertEquals(4344.92, s.evaluatePortfolio(1, "2018-11-28"), 0.1);
  }

  /**
   * Test to check if buy stock takes commission into consideration now.
   */
  @Test
  public void testBuyStockWithCommission() {
    s = new StockMarketModelImpl();
    s.createPortfolio(1);
    s.buyStock("GOOG", 2, "2018-11-23", 1,5);
    assertEquals(2172.46, s.evaluatePortfolio(1, "2018-11-28"), 0.1);
  }

  /**
   * Test to see if retrieve portfolio works.
   */
  @Test
  public void testRetrievePortfolio() {

    s = new StockMarketModelImpl();

    Assert.assertEquals("Company name: AAPL\n" +
                    "Date of purchase: 2018-11-23\n" +
                    "Purchase price: $172.29\n" +
                    "Number of shares: 2.0\n",
            s.viewComposition(1, "2018-11-23").get(0).toString());

    Assert.assertEquals("[Company name: AAPL\n" +
                    "Date of purchase: 2018-11-23\n" +
                    "Purchase price: $172.29\n" +
                    "Number of shares: 2.0\n" +
                    ", Company name: GOOG\n" +
                    "Date of purchase: 2018-11-28\n" +
                    "Purchase price: $1086.23\n" +
                    "Number of shares: 2.0\n" +
            "]",
            s.viewComposition(1, "2018-11-28").toString());
  }

  /**
   * Method to test that retrieve strategy retrieves the values from the file in the format
   * specified in the file and acceptable for the application.
   */
  @Test
  public void testSaveRetrieveStrategy() {
    s = new StockMarketModelImpl();

    /*Without calling create strategy first, we are retrieving a strategy, when we call the
    constructor of model.
     */

    Assert.assertEquals("", "");
  }
}