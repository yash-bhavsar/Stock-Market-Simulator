package view;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import javax.swing.*;

import controller.IStockMarketController;

/**
 * The type Welcome frame.
 *
 * @author ojaspatwardhan
 */
public class WelcomeFrame extends JFrame implements IStockMarketView {

  protected IStockMarketController stockMarketController;
  private CreatePortfolioPanel createPortfolioPanel;
  private BuyStockPanel buyStockPanel;
  private ViewCompositionPanel viewCompositionPanel;
  private CostBasisEvaluationPanel costBasisEvaluationPanel;
  private CreateStrategyPanel createStrategyPanel;
  private SavePortfolioPanel savePortfolioPanel;
  private ApplyStrategyPanel applyStrategyPanel;
  private GraphPanel graphPanel;
  private InvestPanel investPanel;
  private Object WelcomeFrame = this;

  /**
   * Creates new form WelcomeFrame
   */
  public WelcomeFrame(IStockMarketController stockMarketController) {
    initComponents();
    super.setSize(1200, 600);
    this.stockMarketController = stockMarketController;
    createPortfolioPanel = new CreatePortfolioPanel(stockMarketController);
    buyStockPanel = new BuyStockPanel(stockMarketController);
    viewCompositionPanel = new ViewCompositionPanel(stockMarketController);
    costBasisEvaluationPanel = new CostBasisEvaluationPanel(stockMarketController);
    createStrategyPanel = new CreateStrategyPanel(stockMarketController);
    investPanel = new InvestPanel(stockMarketController);
    savePortfolioPanel = new SavePortfolioPanel(stockMarketController);
    graphPanel = new GraphPanel(stockMarketController);
    applyStrategyPanel = new ApplyStrategyPanel(stockMarketController);
  }

  @Override
  public String enterCommand() throws IOException {
    return null;
  }

  @Override
  public void result(String result) throws IOException {

  }

  @Override
  public String continueTakingWeights(List<String> stockList) throws IOException {
    return null;
  }

  @Override
  public String getEqualWeightsAmount() throws IOException {
    return null;
  }

  @Override
  public void setFeatures() {
    createPortfolioButton.addActionListener(l -> jSplitPane1.setRightComponent(createPortfolioPanel));
    buyStockButton.addActionListener(l -> jSplitPane1.setRightComponent(buyStockPanel));
    viewCompositionButton.addActionListener(l -> jSplitPane1.setRightComponent(viewCompositionPanel));
    costbasisEvaluationButton.addActionListener(l -> jSplitPane1.setRightComponent(costBasisEvaluationPanel));
    investButton.addActionListener(l -> jSplitPane1.setRightComponent(investPanel));
    createStrategyButton.addActionListener(l -> jSplitPane1.setRightComponent(createStrategyPanel));
    savePortfolioButton.addActionListener(l -> jSplitPane1.setRightComponent(savePortfolioPanel));
    plotGraphButton.addActionListener(l -> jSplitPane1.setRightComponent(graphPanel));
    applyStrategyButton.addActionListener(l -> jSplitPane1.setRightComponent(applyStrategyPanel));
    exitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        JOptionPane.showConfirmDialog((Component) WelcomeFrame, "Quitting...");
        System.exit(0);
      }
    });
  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jSplitPane1 = new javax.swing.JSplitPane();
    jPanel1 = new javax.swing.JPanel();
    createPortfolioButton = new javax.swing.JButton();
    buyStockButton = new javax.swing.JButton();
    investButton = new javax.swing.JButton();
    viewCompositionButton = new javax.swing.JButton();
    plotGraphButton = new javax.swing.JButton();
    applyStrategyButton = new javax.swing.JButton();
    savePortfolioButton = new javax.swing.JButton();
    exitButton = new javax.swing.JButton();
    createStrategyButton = new javax.swing.JButton();
    costbasisEvaluationButton = new javax.swing.JButton();
    jPanel2 = new javax.swing.JPanel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    createPortfolioButton.setText("Create");

    buyStockButton.setText("Buy Stock");

    investButton.setText("Invest");

    viewCompositionButton.setText("View Composition");

    savePortfolioButton.setText("Save Portfolio");

    plotGraphButton.setText("Plot Graph");

    applyStrategyButton.setText("Apply Existing Strategy");

    exitButton.setText("Exit");

    createStrategyButton.setText("Create and Save Strategy");

    costbasisEvaluationButton.setText("Cost Basis & Evaluation");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(investButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(createPortfolioButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buyStockButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(createStrategyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addContainerGap())
                    .addComponent(costbasisEvaluationButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewCompositionButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(plotGraphButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(savePortfolioButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(applyStrategyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(createPortfolioButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(buyStockButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(investButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(createStrategyButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(viewCompositionButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(costbasisEvaluationButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(savePortfolioButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(applyStrategyButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(plotGraphButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(exitButton)
                            .addContainerGap(51, Short.MAX_VALUE))
    );

    jSplitPane1.setLeftComponent(jPanel1);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 196, Short.MAX_VALUE)
    );
    jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGap(0, 296, Short.MAX_VALUE)
    );

    jSplitPane1.setRightComponent(jPanel2);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSplitPane1)
    );
    layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSplitPane1)
    );

    pack();
    setVisible(true);
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buyStockButton;
  private javax.swing.JButton costbasisEvaluationButton;
  private javax.swing.JButton createPortfolioButton;
  private javax.swing.JButton createStrategyButton;
  private javax.swing.JButton exitButton;
  private javax.swing.JButton investButton;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JSplitPane jSplitPane1;
  private javax.swing.JButton viewCompositionButton;
  private javax.swing.JButton plotGraphButton;
  private javax.swing.JButton savePortfolioButton;
  private javax.swing.JButton applyStrategyButton;
  // End of variables declaration//GEN-END:variables
}
