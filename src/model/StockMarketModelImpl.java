package model;

/**
 * The class StockMarketModelImpl which implements the interface IStockMarketModel. It has
 * overridden methods like buyStock, viewComposition, createPortfolio, evaluatePortfolio and
 * calculateCostBasis.
 */
public class StockMarketModelImpl implements IStockMarketModel {

  private User user;

  /**
   * Constructor which initializes a new user.
   */
  public StockMarketModelImpl() {
    this.user = new User();
  }

  /**
   * Method to buy stock.
   *
   * @param ticker          the ticker
   * @param numberOfStocks  the number of stocks
   * @param date            the date
   * @param portfolioNumber the portfolio number
   */
  @Override
  public void buyStock(String ticker, int numberOfStocks, String date, int portfolioNumber) {
    Services s = Services.getInstance();
    Stock stock;
    try {
      stock = s.getDataForCompany(ticker, numberOfStocks, date);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    user.buyStock(portfolioNumber, stock);
  }

  /**
   * View composition of a portfolio.
   *
   * @param portfolioNumber the portfolio name
   * @return the composition of the portfolio.
   */
  @Override
  public String viewComposition(int portfolioNumber) {
    return user.viewComposition(portfolioNumber);
  }

  /**
   * Create portfolio.
   *
   * @param portfolioNumber the portfolio number
   */
  @Override
  public void createPortfolio(int portfolioNumber) {
    user.createPortfolio(portfolioNumber);
  }

  /**
   * Evaluate the portfolio.
   *
   * @param portfolioNumber the portfolio number
   * @param date            the date
   * @return returns the evaluated value of the portfolio.
   */
  @Override
  public double evaluatePortfolio(int portfolioNumber, String date) {
    return user.evaluatePortfolio(portfolioNumber, date);
  }

  /**
   * Calculate cost basis.
   *
   * @param portfolioNumber the portfolio number
   * @param date            the date
   * @return returns the cost basis.
   */
  @Override
  public double calculateCostBasis(int portfolioNumber, String date) {
    return user.calculateCostBasis(portfolioNumber, date);
  }
}
