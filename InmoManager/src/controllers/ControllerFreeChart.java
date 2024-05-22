package controllers;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import util.ManageDatabase;
import views.GUIFreeChart;
import views.GUIMainAdmin;

public class ControllerFreeChart {
	private GUIFreeChart freeChart;
	private int currentIdx = 0;
	private List<JFreeChart> graphs;

	public ControllerFreeChart(GUIFreeChart freeChart) {
		this.freeChart = freeChart;
		graphs = new ArrayList<>();
		initializeGraphs();
		initializeList();
		freeChart.addActListeners(new ButtonListeners());
		freeChart.addListListeners(new ListListener());
	}

	private class ListListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			@SuppressWarnings("unchecked")
			JList<String> source = (JList<String>) e.getSource();
			currentIdx = source.getSelectedIndex();
			refreshIdx();
			loadGraph();
		}

	}

	private class ButtonListeners implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			if (source == freeChart.getBtnReturn()) {
				freeChart.dispose();
				new GUIMainAdmin(null);
			} else if (source == freeChart.getBtnPrevious()) {
				currentIdx--;
				refreshIdx();
				loadGraph();
			} else if (source == freeChart.getBtnNext()) {
				currentIdx++;
				refreshIdx();
				loadGraph();
			}
		}

	}

	public void initializeList() {
		DefaultListModel<String> dlm = new DefaultListModel<>();
		for (JFreeChart chart : graphs) {
			dlm.addElement(chart.getTitle().getText());
		}
		freeChart.getList().setModel(dlm);
		freeChart.getList().setSelectedIndex(0);
		freeChart.getPanelIdx().revalidate();
		freeChart.getPanelIdx().repaint();
	}

	public void refreshIdx() {
		if (currentIdx == graphs.size() - 1 && currentIdx == 0) {
			freeChart.getBtnNext().setEnabled(false);
			freeChart.getBtnPrevious().setEnabled(false);
		} else if (currentIdx == graphs.size() - 1) {
			freeChart.getBtnPrevious().setEnabled(true);
			freeChart.getBtnNext().setEnabled(false);
		} else if (currentIdx == 0) {
			freeChart.getBtnPrevious().setEnabled(false);
			freeChart.getBtnNext().setEnabled(true);
		} else {
			freeChart.getBtnPrevious().setEnabled(true);
			freeChart.getBtnNext().setEnabled(true);
		}
		freeChart.getLblIndex().setText((currentIdx + 1) + "/" + graphs.size());
		freeChart.getList().setSelectedIndex(currentIdx);
	}

	public void loadGraph() {
		freeChart.getPanelChart().removeAll();
		setGraphPanel(graphs.get(currentIdx));
	}

	public void setGraphPanel(JFreeChart graph) {
		ChartPanel panel = new ChartPanel(graph);
		panel.setMouseWheelEnabled(true);
		freeChart.getPanelChart().add(panel);
		panel.setPreferredSize(new Dimension(600, 235));

		freeChart.getPanelChart().revalidate();
		freeChart.getPanelChart().repaint();
	}

	public void initializeGraphs() {
		graphs.add(generateSPYGraph());
		graphs.add(generateSalesRentsGraph());
		graphs.add(generateSPMGraph());
		graphs.add(generateCityCountMap());
		graphs.add(generateManagerPerformanceGraph());
		graphs.add(generateFeatureCountGraph());
		graphs.add(generatePropertyTypeCountGraph());
		graphs.add(generateSalesPerCityGraph());
		graphs.add(generateRentsPerCityGraph());
		graphs.add(generateRentableTypeProfitGraph());
		graphs.add(generatePurchasableTypeProfitGraph());
		refreshIdx();
		loadGraph();
	}

	public JFreeChart generateSPYGraph() {
		Map<Integer, Integer> salesPerYear = ManageDatabase.getSalesPerYear();
		DefaultCategoryDataset dts = new DefaultCategoryDataset();
		for (Map.Entry<Integer, Integer> entry : salesPerYear.entrySet()) {
			dts.setValue(entry.getValue(), "Sales", entry.getKey().toString());
		}

		return ChartFactory.createLineChart(
				"Sales per year",
				"Year", "Sales", dts,
				PlotOrientation.VERTICAL,
				true, true, false);

	}

	public JFreeChart generateSalesRentsGraph() {
		int[] rentsSales = ManageDatabase.countRentsSales();
		DefaultPieDataset<String> dpd = new DefaultPieDataset<>();
		dpd.setValue("Rents", rentsSales[0]);
		dpd.setValue("Purchases", rentsSales[1]);

		return ChartFactory.createPieChart("Rents vs Purchases", dpd, true, true, false);
	}

	public JFreeChart generateSPMGraph() {
		Map<String, Integer> salesPerMonth = ManageDatabase.getSalesPerMonth();
		DefaultCategoryDataset dts = new DefaultCategoryDataset();
		for (Map.Entry<String, Integer> entry : salesPerMonth.entrySet()) {
			dts.setValue(entry.getValue(), "Sales", entry.getKey());
		}
		return ChartFactory.createBarChart("Sales per month", "Month", "Sales", dts, PlotOrientation.VERTICAL, true,
				true, false);
	}

	public JFreeChart generateCityCountMap() {
		Map<String, Integer> cityCount = ManageDatabase.getCityCountMap(true, true);
		DefaultPieDataset<String> dpd = new DefaultPieDataset<>();
		for (Map.Entry<String, Integer> entry : cityCount.entrySet()) {
			dpd.setValue(entry.getKey(), entry.getValue());
		}
		return ChartFactory.createPieChart("City Count", dpd, true, true, false);
	}

	public JFreeChart generateManagerPerformanceGraph() {
		Map<String, Integer> managerSales = ManageDatabase.managerSales();
		Map<String, Integer> managerRents = ManageDatabase.managerRents();
		DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		for (Map.Entry<String, Integer> entry : managerSales.entrySet()) {
			dcd.setValue(entry.getValue(), "Purchases", entry.getKey());
		}
		for (Map.Entry<String, Integer> entre : managerRents.entrySet()) {
			dcd.setValue(entre.getValue(), "Rents", entre.getKey());
		}

		return ChartFactory.createBarChart("Manager Performance", "Manager", "Sales", dcd, PlotOrientation.VERTICAL,
				true, true, false);
	}

	public JFreeChart generateFeatureCountGraph() {
		Map<String, Integer> featureCount = ManageDatabase.featureCountMap();
		DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		for (Map.Entry<String, Integer> entry : featureCount.entrySet()) {
			dcd.setValue(entry.getValue(), "Count", entry.getKey());
		}
		return ChartFactory.createBarChart("Feature Count", "Feature", "Count", dcd, PlotOrientation.VERTICAL,
				true, true, false);
	}

	public JFreeChart generatePropertyTypeCountGraph() {
		Map<String, Integer> propertyTypeCount = ManageDatabase.propertyTypeCountMap();
		DefaultPieDataset<String> dpd = new DefaultPieDataset<>();
		for (Map.Entry<String, Integer> entry : propertyTypeCount.entrySet()) {
			dpd.setValue(entry.getKey(), entry.getValue());
		}
		return ChartFactory.createPieChart("Property Type Count", dpd, true, true, false);
	}

	public JFreeChart generateSalesPerCityGraph() {
		Map<String, Integer> salesPerCity = ManageDatabase.salesCityCostMap();
		DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		for (Map.Entry<String, Integer> entry : salesPerCity.entrySet()) {
			dcd.setValue(entry.getValue(), "Total", entry.getKey());
		}
		return ChartFactory.createBarChart("Purchases Per City Revenue", "City", "Revenue", dcd,
				PlotOrientation.VERTICAL, true,
				true,
				false);
	}

	public JFreeChart generateRentsPerCityGraph() {
		Map<String, Integer> rentsPerCityAvg = ManageDatabase.rentsCityCostMap();
		DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		for (Map.Entry<String, Integer> entry : rentsPerCityAvg.entrySet()) {
			dcd.setValue(entry.getValue(), "Total", entry.getKey());
		}
		return ChartFactory.createBarChart("Rents Per City Revenue", "City", "Revenue", dcd, PlotOrientation.VERTICAL,
				true,
				true, false);
	}

	public JFreeChart generateRentableTypeProfitGraph() {
		Map<String, Integer> typeCount = ManageDatabase.getRentableTypeAvgMap();
		DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		for (Map.Entry<String, Integer> entry : typeCount.entrySet()) {
			dcd.setValue(entry.getValue(), "Total", entry.getKey());
		}
		return ChartFactory.createBarChart("Rentable Type Profit", "Type", "AVG Profit", dcd, PlotOrientation.VERTICAL,
				true,
				true, false);
	}

	public JFreeChart generatePurchasableTypeProfitGraph() {
		Map<String, Integer> typeCount = ManageDatabase.getPurchasableTypeAvgMap();
		DefaultCategoryDataset dcd = new DefaultCategoryDataset();
		for (Map.Entry<String, Integer> entry : typeCount.entrySet()) {
			dcd.setValue(entry.getValue(), "Total", entry.getKey());
		}
		return ChartFactory.createBarChart("Purchasable Type Profit", "Type", "AVG Profit", dcd, PlotOrientation.VERTICAL,
				true,
				true, false);
	}
}
