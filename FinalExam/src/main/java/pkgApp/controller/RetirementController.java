package pkgApp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import pkgApp.RetirementApp;
import pkgCore.Retirement;

public class RetirementController implements Initializable {

	private RetirementApp mainApp = null;

	@FXML
	private TextField txtYearsToWork;
	@FXML
	private TextField txtAnnualReturnWorking;
	@FXML
	private TextField txtYearsRetired;
	@FXML
	private TextField txtAnnualReturnRetired;
	@FXML
	private TextField txtRequiredIncome;
	@FXML
	private TextField txtMonthlySSI;
	@FXML
	private TextField txtAmountSaved;
	@FXML
	private TextField txtAmountToSave;

	public RetirementApp getMainApp() {
		return mainApp;
	}

	public void setMainApp(RetirementApp mainApp) {
		this.mainApp = mainApp;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtAmountSaved.setEditable(false);
		txtAmountToSave.setEditable(false);
	}

	@FXML
	public void btnClear(ActionEvent event) {
		System.out.println("Clear pressed");
		txtYearsToWork.setText("");
		txtAnnualReturnWorking.setText("");
		txtYearsRetired.setText("");
		txtAnnualReturnRetired.setText("");
		txtRequiredIncome.setText("");
		txtMonthlySSI.setText("");
		txtAmountSaved.setText("");
		txtAmountToSave.setText("");

	}

	@FXML
	public void btnCalculate(ActionEvent event) {
		if (isInputValid()) {
			int iYearsToWork = Integer.parseInt(txtYearsToWork.getText());
			double dAnnualReturnWorking = Double.parseDouble(txtAnnualReturnWorking.getText());
			int iYearsRetired = Integer.parseInt(txtYearsRetired.getText());
			double dAnnualReturnRetired = Double.parseDouble(txtAnnualReturnRetired.getText());
			double dRequiredIncome = Double.parseDouble(txtRequiredIncome.getText());
			double dMonthlySSI = Double.parseDouble(txtMonthlySSI.getText());

			Retirement R = new Retirement(iYearsToWork, dAnnualReturnWorking, iYearsRetired, dAnnualReturnRetired,
					dRequiredIncome, dMonthlySSI);

			double AmountToSaved = R.AmountToSaved();
			double TotalAmountSaved = R.TotalAmountSaved();

			txtAmountSaved.setText(Double.toString(TotalAmountSaved));
			txtAmountToSave.setText(Double.toString(AmountToSaved));
		}
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (txtYearsToWork.getText() == null || txtYearsToWork.getText().length() == 0) {
			errorMessage += "Not valid years to work!\n";
		} else {
			// checks if integer
			try {
				Integer.parseInt(txtYearsToWork.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Not valid years to work! (must be an integer)!\n";
			}
		}
		
		if (txtYearsRetired.getText() == null || txtYearsRetired.getText().length() == 0) {
			errorMessage += "Not valid years retired!\n";
		} else {
			try {
				Integer.parseInt(txtYearsRetired.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Not valid years retired! (must be an integer)!\n";
			}
		}
		
		// investment mode: the bounds are 0 to 20%
		if (txtAnnualReturnWorking.getText() == null || txtAnnualReturnWorking.getText().length() == 0) {
			errorMessage += "Not valid annual return rate!\n";
		} else {
			try {
				Double.parseDouble(txtAnnualReturnWorking.getText());

				if ((Double.parseDouble(txtAnnualReturnWorking.getText()) > 0.20
						|| Double.parseDouble(txtAnnualReturnWorking.getText()) < 0)) {
					errorMessage += "Not valid annual return rate! (must be between 0 and 20%)!\n";
				}
			} catch (NumberFormatException e) {
				errorMessage += "Not valid annual return rate! (must be numeric)!\n";
			}
		}

		// payback mode: the bounds are 0 to 3%
		if (txtAnnualReturnRetired.getText() == null || txtAnnualReturnRetired.getText().length() == 0) {
			errorMessage += "Not valid annual retired return rate!\n";
		} else {
			try {
				Double.parseDouble(txtAnnualReturnRetired.getText());

				if ((Double.parseDouble(txtAnnualReturnRetired.getText()) > 0.03
						|| Double.parseDouble(txtAnnualReturnRetired.getText()) < 0)) {
					errorMessage += "Not valid annual retired return rate! (must be between 0 and 3%)!\n";
				}
			} catch (NumberFormatException e) {
				errorMessage += "Not valid annual retired return rate! (must be numeric)!\n";
			}
		}

		// check numeric
		if (txtRequiredIncome.getText() == null || txtRequiredIncome.getText().length() == 0) {
			errorMessage += "Not valid required income!\n";
		} else {
			try {
				Double.parseDouble(txtRequiredIncome.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Not valid  required income! (must be numeric)!\n";
			}
		}
		// check numeric
		if (txtMonthlySSI.getText() == null || txtMonthlySSI.getText().length() == 0) {
			errorMessage += "Not valid Monthly SSI!\n";
		} else {
			try {
				Double.parseDouble(txtMonthlySSI.getText());
			} catch (NumberFormatException e) {
				errorMessage += "Not valid monthly SSI! (must be numeric)!\n";
			}
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();
			return false;
		}
	}

}
