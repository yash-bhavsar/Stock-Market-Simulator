package model;

import java.text.ParseException;
import java.util.List;

/**
 * The interface IStockMarketModel which has methods to buy stocks, calculate cost basis, view
 * composition, create and evaluate portfolio.
 *
 * @param <T> the type parameter
 */
public interface IStockMarketModel<T> {

  /**
   * Method to buy stock.
   *
   * @param ticker          the ticker
   * @param numberOfStocks  the number of stocks
   * @param date            the date
   * @param portfolioNumber the portfolio number
   * @param commission      the commission
   */
  void buyStock(String ticker, double numberOfStocks, String date, int portfolioNumber, double commission);

  /**
   * View composition of a portfolio.
   *
   * @param portfolioNumber the portfolio name
   * @param date            the date
   * @return the composition of the portfolio.
   */
  List<T> viewComposition(int portfolioNumber, String date);

  /**
   * Create portfolio.
   *
   * @param portfolioNumber the portfolio number
   */
  void createPortfolio(int portfolioNumber);

  /**
   * Evaluate the portfolio.
   *
   * @param portfolioNumber the portfolio number
   * @param date            the date
   * @return returns the evaluated value of the portfolio.
   */
  double evaluatePortfolio(int portfolioNumber, String date);

  /**
   * Calculate cost basis.
   *
   * @param portfolioNumber the portfolio number
   * @param date            the date
   * @return returns the cost basis.
   */
  double calculateCostBasis(int portfolioNumber, String date);

  /**
   * Invest in a particular portfolio.
   *
   * @param ticker           the ticker
   * @param investmentAmount the investment amount
   * @param date             the date
   * @param portfolioNumber  the portfolio number
   * @param commission       the commission
   * @param flag             the flag to see if invest was called by one time investment
   *                         or strategy. True if called by invest else false.
   */
  void invest(String ticker, double investmentAmount, String date, int portfolioNumber,
              double commission);

  /**
   * DCAS strategy.
   *
   * @param ticker           the ticker
   * @param investmentAmount the investment amount
   * @param startDate        the start date
   * @param endDate          the end date
   * @param portfolioNumber  the portfolio number
   * @param frequency        the frequency
   * @param model            the model
   * @throws ParseException the parse exception
   */
  void DCassStrategy(String ticker, double investmentAmount, String startDate,
                     String endDate, int portfolioNumber,
                     int frequency, IStockMarketModel model) throws ParseException;
}
