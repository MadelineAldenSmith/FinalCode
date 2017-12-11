package pkgEmpty;

import static org.junit.Assert.*;

import java.text.DecimalFormat;

import org.apache.poi.ss.formula.functions.FinanceLib;
import org.junit.Test;

import pkgCore.Retirement;


public class SavingTest {

	@Test
	public void TestPV()
	{
		// PV vs. TotalAmountSaved

		double dMonthsToWork = 40 * 12;
		double rAnnualReturnWorking = 0.07 / 12;
		double dMonthsRetired = 20 * 12;
		double rAnnaulReturnRetired = 0.02 / 12;
		double dRequiredIncome = 10000 ;
		double dMonthlySSI = 2642;
		
		double pv = FinanceLib.pv(rAnnaulReturnRetired, dMonthsRetired, dRequiredIncome - dMonthlySSI, 0, false);
		DecimalFormat df = new DecimalFormat(".##");
		
		
		Retirement R = new Retirement((40), 0.07 , (20), 0.02, 10000, 2642) ;
		
		assertTrue(R.TotalAmountSaved() == Double.parseDouble(df.format(-pv)));
		assertTrue(1454485.55 == Double.parseDouble(df.format(-pv)));
		System.out.println(Double.parseDouble(df.format(-pv)));
		System.out.println(R.TotalAmountSaved());

	}
	
	
	@Test
	public void TestPMT() {
		
		// PMT vs. AmountToSaved

		double dMonthsToWork = 40 * 12;
		double rAnnualReturnWorking = 0.07 / 12;
		double dMonthsRetired = 20 * 12;
		double rAnnaulReturnRetired = 0.02 / 12;
		double dRequiredIncome = 10000 ;
		double dMonthlySSI = 2642;
		
		double pv = FinanceLib.pv(rAnnaulReturnRetired, dMonthsRetired, dRequiredIncome - dMonthlySSI, 0, false);
		double pmt = FinanceLib.pmt(rAnnualReturnWorking, dMonthsToWork, 0, pv, false);
		DecimalFormat df = new DecimalFormat(".##");

		Retirement R = new Retirement(40, 0.07 , 20, 0.02, 10000, 2642) ;

		assertTrue(554.13 == Double.parseDouble(df.format(pmt)));
		assertTrue(R.AmountToSaved() == Double.parseDouble(df.format(pmt)));
		System.out.println(Double.parseDouble(df.format(pmt)));
		System.out.println(R.AmountToSaved());

		
	}
}
	 