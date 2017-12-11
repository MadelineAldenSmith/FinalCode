package pkgCore;

import java.text.DecimalFormat;

import org.apache.poi.ss.formula.functions.FinanceLib;

public class Retirement {

	private int iYearsToWork;
	private double dAnnualReturnWorking;
	private int iYearsRetired;
	private double dAnnualReturnRetired;
	private double dRequiredIncome;
	private double dMonthlySSI;
	
	//TODO: Build the contructor, getters and setters for the attributes above. DONE!
	
	public Retirement(int iYearsToWork, double dAnnualReturnWorking, int iYearsRetired, double dAnnualReturnRetired,
			double dRequiredIncome, double dMonthlySSI) {
		super();
		this.iYearsToWork = iYearsToWork;
		this.dAnnualReturnWorking = dAnnualReturnWorking;
		this.iYearsRetired = iYearsRetired;
		this.dAnnualReturnRetired = dAnnualReturnRetired;
		this.dRequiredIncome = dRequiredIncome;
		this.dMonthlySSI = dMonthlySSI;
	}
	
	// getters and setters:
	public int getiYearsToWork() {
		return iYearsToWork;
	}

	public void setiYearsToWork(int iYearsToWork) {
		this.iYearsToWork = iYearsToWork;
	}

	public double getdAnnualReturnWorking() {
		return dAnnualReturnWorking;
	}

	public void setdAnnualReturnWorking(double dAnnualReturnWorking) {
		this.dAnnualReturnWorking = dAnnualReturnWorking;
	}

	public int getiYearsRetired() {
		return iYearsRetired;
	}

	public void setiYearsRetired(int iYearsRetired) {
		this.iYearsRetired = iYearsRetired;
	}

	public double getdAnnualReturnRetired() {
		return dAnnualReturnRetired;
	}

	public void setdAnnualReturnRetired(double dAnnualReturnRetired) {
		this.dAnnualReturnRetired = dAnnualReturnRetired;
	}

	public double getdRequiredIncome() {
		return dRequiredIncome;
	}

	public void setdRequiredIncome(double dRequiredIncome) {
		this.dRequiredIncome = dRequiredIncome;
	}

	public double getdMonthlySSI() {
		return dMonthlySSI;
	}

	public void setdMonthlySSI(double dMonthlySSI) {
		this.dMonthlySSI = dMonthlySSI;
	}

	
	
	public double AmountToSaved()
	{
		double r = this.dAnnualReturnRetired / 12 ;
		int n = this.iYearsRetired * 12;
		double y1 = this.dRequiredIncome  ;
		double y2 = this.dMonthlySSI ;
		double r1 = this.dAnnualReturnWorking  / 12;
		int n2 = this.iYearsToWork * 12;
		
		double PV2 =  FinanceLib.pv(r, n , y1 - y2 , 0, false) ;
		double PMT =  FinanceLib.pmt(r1, n2 , 0, PV2, false) ;
		
		DecimalFormat df = new DecimalFormat(".##");
		
		return Double.parseDouble(df.format(PMT)) ; 
	}
	
	
	public double TotalAmountSaved()
	{	double r = this.dAnnualReturnRetired / 12 ;
		int n = this.iYearsRetired * 12 ;
		double y1 = this.dRequiredIncome ;
		double y2 = this.dMonthlySSI ;
		
		double PV =  FinanceLib.pv(r, n, y1 - y2 , 0, false) ;
	
		
		DecimalFormat df = new DecimalFormat(".##");
		
		return Double.parseDouble(df.format(-PV)) ; 
	}
	}

