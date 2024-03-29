package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import model.IStockMarketModel;
import model.Stock;
import view.IStockMarketView;

/**
 * The class StockMarketController which has the overridden method startStockMarketSimulator to
 * start the simulation.
 */
public class StockMarketControllerImpl implements IStockMarketController {

  private IStockMarketView iv;
  private IStockMarketModel im;


  /**
   * Constructor which initializes stock market model object.
   *
   * @param im IStockMarketModel object.
   */
  public StockMarketControllerImpl(IStockMarketModel im) {
    if (im == null) {
      throw new IllegalArgumentException("Model or View is null");
    }
    this.im = im;
  }

  /**
   * Constructor which initiates stock market model and view objects.
   *
   * @param im IStockMarketModel object.
   * @param iv IStockMarketView object.
   */
  public StockMarketControllerImpl(IStockMarketModel im, IStockMarketView iv) {
    if (im == null || iv == null) {
      throw new IllegalArgumentException("Model or View is null");
    }
    this.im = im;
    this.iv = iv;
  }

  @Override
  public void setView(IStockMarketView view) {
    iv = view;
    iv.setFeatures();
  }

  /**
   * Method to start stock market simulator.
   */
  @Override
  public void startStockMarketSimulator() {
    try {
      while (true) {
        String input = this.iv.enterCommand();
        if (input.equals(String.valueOf(Integer.MAX_VALUE))) {
          break;
        }
        String[] inputs = input.trim().split("\\s+");
        String result = "";
        switch (inputs[0]) {
          case "1":
            try {
              this.im.createPortfolio(Integer.parseInt(inputs[1].trim()));
            } catch (IllegalArgumentException e) {
              result = e.getMessage();
            }
            break;
          case "2":
            try {
              this.im.buyStock(inputs[1], Integer.parseInt(inputs[2]), inputs[3],
                      Integer.parseInt(inputs[4]), Double.parseDouble(inputs[5]));
              result += "\nStock bought successfully\n";
            } catch (IllegalArgumentException e) {
              result = e.getMessage();
            }
            break;
          case "3":
            String date = inputs[2];
            int portfolioNumber = Integer.parseInt(inputs[1]);
            if (inputs[3].equals("2")) {
              try {
                result = investForCustomWeights(result, date, portfolioNumber);
              } catch (IllegalArgumentException e) {
                result = e.getMessage();
              }
            } else if (inputs[3].equals("1")) {
              try {
                result = investForEqualWeights(result, date, portfolioNumber);
              } catch (IllegalArgumentException e) {
                result = e.getMessage();
              }
            }
            break;
          case "4":
            String sdate = inputs[2];
            String edate = inputs[3];
            String saveStrategy = inputs[6];
            int portfolioNumber1 = Integer.parseInt(inputs[1]);
            int amount1 = Integer.parseInt(inputs[4]);
            int frequency = Integer.parseInt(inputs[5]);
            if (inputs[7].equals("2")) {
              try {
                result = investInStrategyCustomWeights(saveStrategy, result, sdate,
                        portfolioNumber1,
                        edate, frequency, amount1);
              } catch (IllegalArgumentException e) {
                result = e.getMessage();
              }
            } else if (inputs[7].equals("1")) {
              try {
                result = investInStrategyEqualWeights(saveStrategy, result, sdate,
                        portfolioNumber1,
                        edate, frequency, amount1);
              } catch (IllegalArgumentException e) {
                result = e.getMessage();
              }
            }
            break;
          case "5":
            try {
              List<Stock> stocks = this.im.viewComposition(Integer.parseInt(inputs[1]), inputs[2]);
              for (Stock stock : stocks) {
                result += "\nTicker name: " + stock.getTicker() + "\nDate of purchase: "
                        + stock.getDateTime() + "\nPurchase price: $" + stock.getPurchasePrice()
                        + "\nNumber of shares: " + stock.getShares() + "\n";
              }
            } catch (IllegalArgumentException e) {
              result = e.getMessage();
            }
            break;
          case "6":
            try {
              result += "Total basis: $" + this.im.calculateCostBasis(Integer.parseInt(inputs[1])
                      , inputs[2]) + "\nTotal Evaluation: $" + this.im.evaluatePortfolio(
                      Integer.parseInt(inputs[1]), inputs[2]);
            } catch (Exception e) {
              result = e.getMessage();
            }
            break;
          case "7":
            try {
              this.im.save(Integer.parseInt(inputs[1]));
            } catch (IllegalArgumentException e) {
              result = e.getMessage();
            }
            break;
          case "8":
            String strategyNumber = inputs[1];
            portfolioNumber1 = Integer.parseInt(inputs[2]);
            String[] details = new String[4];
            try {
              details = this.im.strategyDetails(strategyNumber);
            } catch (IllegalArgumentException e) {
              result = e.getMessage();
              break;
            }
            int amount = (int) Double.parseDouble(details[0]);
            String startdate = details[1];
            String endDate = details[2];
            int frequency1 = Integer.parseInt(details[3]);

            List<Stock> stocks = this.im.viewComposition(portfolioNumber1, startdate);
            if (inputs[3].equals("2")) {
              try {
                result = investInStrategyCustomWeights("no", result, startdate,
                        portfolioNumber1,
                        endDate, frequency1, amount);
              } catch (IllegalArgumentException e) {
                result = e.getMessage();
              }
            } else if (inputs[3].equals("1")) {
              try {
                result = investInStrategyEqualWeights("no", result, startdate,
                        portfolioNumber1,
                        endDate, frequency1, amount);
              } catch (IllegalArgumentException e) {
                result = e.getMessage();
              }
            }
            break;
          case "9":
            System.exit(0);
            break;
          default:
            break;
        }
        this.iv.result(result);
      }
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String createPortfolio(String portfolioNumber) {
    try {
      this.im.createPortfolio(Integer.parseInt(portfolioNumber));
      return "pass";
    } catch (IllegalArgumentException e) {
      return e.getMessage();
    }
  }

  @Override
  public String buyStock(String ticker, double numberOfStocks, String date, int portfolioNumber,
                         int commission) {
    try {
      this.im.buyStock(ticker, numberOfStocks, date, portfolioNumber, commission);
      return "pass";
    } catch (IllegalArgumentException e) {
      return e.getMessage();
    }
  }

  @Override
  public String buyStockByAmount(String ticker, double amount, String date, int portfolioNumber,
                                 double commission) {
    try {
      this.im.invest(ticker, amount, date, portfolioNumber, commission);
      return "pass";
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override
  public List<Stock> viewComposition(int portfolioNumber, String date) {
    try {
      List<Stock> stocks = this.im.viewComposition(portfolioNumber, date);
      return stocks;
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override
  public String costBasisAndEvaluation(int portfolioNumber, String date) {
    try {
      Double costBasis = this.im.calculateCostBasis(portfolioNumber, date);
      Double valuation = this.im.evaluatePortfolio(portfolioNumber, date);
      return "Cost Basis: $" + costBasis.toString() + "\nEvaluation: $" + valuation.toString();
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  @Override
  public String executeStrategy(String ticker, double investmentAmount, String startDate,
                                String endDate, int portfolioNumber,
                                int frequency) throws ParseException {
    try {
      this.im.dCassStrategy(ticker, investmentAmount, startDate, endDate, portfolioNumber,
              frequency, this.im);
      return "pass";
    } catch (IllegalArgumentException ie) {
      throw new IllegalArgumentException(ie.getMessage());
    }
  }

  @Override
  public String savePortfolio(int portfolioNumber) {
    try {
      this.im.save(portfolioNumber);
      return "pass";
    } catch (IllegalArgumentException ie) {
      throw new IllegalArgumentException(ie.getMessage());
    }
  }

  @Override
  public String saveStrategy(String strategyNumber, double amount, String sdate, String edate,
                             int frequency) {
    try {
      this.im.saveDcassStrategy(strategyNumber, amount, sdate, edate, frequency);
      return "pass";
    } catch (IllegalArgumentException ie) {
      throw new IllegalArgumentException(ie.getMessage());
    }
  }

  @Override
  public String[] getStrategyDetails(String strategyNumber) {
    try {
      return this.im.strategyDetails(strategyNumber);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }

  /**
   * Helper method to check if stocks exist in the portfolio.
   *
   * @param stockList is the list of stocks.
   * @return the result string.
   */
  private boolean checkForStocks(List<Stock> stockList) {
    return stockList.size() == 0;
  }

  /**
   * Helper method to invest using strategy and equal weights.
   *
   * @param result          is the result string.
   * @param sdate           is the start date for the strategy.
   * @param portfolioNumber is the portfolio number.
   * @param edate           is the end date for the strategy, which is optional. If not mentioned,
   *                        then the end date is assumed to be todays date.
   * @param frequency       is how frequently the user wants to invest in the strategy.
   * @return the result string.
   * @throws IOException    if the input is invalid.
   * @throws ParseException if parsing is invalid.
   */
  private String investInStrategyEqualWeights(String saveStrategy, String result, String sdate,
                                              int portfolioNumber,
                                              String edate, int frequency, int amount)
          throws IOException,
          ParseException {
    List<Stock> stockList = this.im.viewComposition(portfolioNumber, sdate);
    List<String> stockNames = stockList.stream().map(Stock::getTicker).collect(Collectors.toList());
    stockNames = stockNames.stream().distinct().collect(Collectors.toList());
    if (checkForStocks(stockList)) {
      result = "\n\nBuy stock first\n";
      return result;
    }
    if (amount <= 0) {
      result = "\n\nAmount cannot be less than or equal to 0\n";
      return result;
    }
    double investmentAmount = (double) amount / stockList.size();
    try {
      for (int i = 0; i < stockNames.size(); i++) {
        this.im.dCassStrategy(stockNames.get(i), investmentAmount, sdate, edate,
                portfolioNumber, frequency, this.im);
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    if (!saveStrategy.equals("no")) {
      try {
        this.im.saveDcassStrategy(saveStrategy, (double) amount, sdate, edate, frequency);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    }
    return "";
  }

  /**
   * Helper method to invest using strategy and custom weights.
   *
   * @param result          is the result string.
   * @param sdate           is the start date for the strategy.
   * @param portfolioNumber is the portfolio number.
   * @param edate           is the end date for the strategy, which is optional. If not mentioned,
   *                        then the end date is assumed to be todays date.
   * @param frequency       is how frequently the user wants to invest in the strategy.
   * @return the result string.
   * @throws IOException    if the input is invalid.
   */
  private String investInStrategyCustomWeights(String saveStrategy, String result, String sdate,
                                               int portfolioNumber, String edate, int frequency,
                                               int amount) throws IOException {
    List<Stock> stockList = this.im.viewComposition(portfolioNumber, sdate);
    List<String> stockNames = stockList.stream().map(Stock::getTicker).collect(Collectors.toList());
    stockNames = stockNames.stream().distinct().collect(Collectors.toList());
    if (checkForStocks(stockList)) {
      result = "\n\nBuy stock first\n";
      return result;
    }
    String weights = this.iv.continueTakingWeights(stockNames);
    int[] numbers = Arrays.stream(weights.trim().split("\\s+"))
            .mapToInt(Integer::parseInt).toArray();
    if (amount <= 0) {
      result = "\n\nAmount cannot be less than or equal to 0\n";
      return result;
    }
    int[] weightsNumbers = Arrays.copyOf(numbers, numbers.length - 1);
    if (Arrays.stream(weightsNumbers).sum() != 100) {
      result = "\nSum of weights should be 100\n";
      return result;
    }
    double[] numbers1 = Arrays.stream(weightsNumbers)
            .mapToDouble(number -> number * 0.01 * (double) amount).toArray();
    try {
      for (int i = 0; i < stockNames.size(); i++) {
        this.im.dCassStrategy(stockNames.get(i), numbers1[i], sdate, edate,
                portfolioNumber, frequency, this.im);
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
    if (!saveStrategy.equals("no")) {
      try {
        this.im.saveDcassStrategy(saveStrategy, amount, sdate, edate, frequency);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException(e.getMessage());
      }
    }
    return "";
  }



  /**
   * Private helper method to calculate the number of shares the user can purchase using equal
   * weights.
   *
   * @param result          is the result string.
   * @param date            is the date at which the user wants to invest.
   * @param portfolioNumber is the portfolio number in which the user wants to invest.
   * @return the result string.
   * @throws IOException if input is invalid.
   */
  private String investForEqualWeights(String result, String date, int portfolioNumber)
          throws IOException {
    List<Stock> stockList = this.im.viewComposition(portfolioNumber, date);
    if (checkForStocks(stockList)) {
      result = "\n\nBuy stock first\n";
      return result;
    }
    String amount = this.iv.getEqualWeightsAmount();
    if (Integer.parseInt(amount.trim()) <= 0) {
      result = "\n\nAmount cannot be less than or equal to 0\n";
      return result;
    }
    double investmentAmount = Double.parseDouble(amount) / stockList.size();
    for (int i = 0; i < stockList.size(); i++) {
      this.im.invest(stockList.get(i).getTicker(), investmentAmount, date, portfolioNumber,
              0);
    }
    return "";
  }

  /**
   * Private helper method to calculate the number of shares the user can purchase using custom
   * weights.
   *
   * @param result          is the result string.
   * @param date            is the date at which the user wants to invest.
   * @param portfolioNumber is the portfolio number in which the user wants to invest.
   * @return the result string.
   * @throws IOException if input is invalid.
   */
  private String investForCustomWeights(String result, String date, int portfolioNumber)
          throws IOException {
    List<Stock> stockList = this.im.viewComposition(portfolioNumber, date);
    List<String> stockNames = stockList.stream().map(Stock::getTicker).collect(Collectors.toList());
    stockNames = stockNames.stream().distinct().collect(Collectors.toList());
    if (checkForStocks(stockList)) {
      result = "\n\nBuy stock first\n";
      return result;
    }
    String weights = this.iv.continueTakingWeights(stockNames);
    int[] numbers = Arrays.stream(weights.trim().split("\\s+"))
            .mapToInt(Integer::parseInt).toArray();
    int amount = numbers[numbers.length - 1];
    if (amount <= 0) {
      result = "\n\nAmount cannot be less than or equal to 0\n";
      return result;
    }
    int[] weightsNumbers = Arrays.copyOf(numbers, numbers.length - 1);
    if (Arrays.stream(weightsNumbers).sum() != 100) {
      result = "\nSum of weights should be 100\n";
      return result;
    }
    double[] numbers1 = Arrays.stream(weightsNumbers)
            .mapToDouble(number -> number * 0.01 * amount).toArray();
    for (int i = 0; i < stockList.size(); i++) {
      this.im.invest(stockList.get(i).getTicker(), numbers1[i], date, portfolioNumber,
              0);
    }
    return "";
  }
}
