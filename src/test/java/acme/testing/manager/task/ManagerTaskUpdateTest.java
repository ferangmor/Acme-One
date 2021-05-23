package acme.testing.manager.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmeOneTest;

public class ManagerTaskUpdateTest extends AcmeOneTest{

	// Lifecycle management -----------------
	
	// Test Cases ---------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/updatePositive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updatePositive(final int recordIndex, final String title, final String startTime, final String endTime, final String workload,
									final String description, final String info) {
		this.navigateHome();
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "List all my tasks");
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startTime", startTime);
		super.fillInputBoxIn("endTime", endTime);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("isPublic", "true");
		
		super.clickOnSubmitButton("Update");
		
		super.clickOnMenu("Manager", "List all my tasks");
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, startTime);
		super.checkColumnHasValue(recordIndex, 2, endTime);
		super.checkColumnHasValue(recordIndex, 3, workload);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("startTime", startTime);
		super.checkInputBoxHasValue("endTime", endTime);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("info", info);
		
		super.signOut();
	}
	
	
	@ParameterizedTest
	@CsvFileSource(resources = "/manager/task/updateNegative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void updateNegative(final int recordIndex, final String title, final String startTime, final String endTime, final String workload,
									final String description, final String info) {
		
		this.navigateHome();
		super.signIn("manager1", "manager1");
		
		super.clickOnMenu("Manager", "List all my tasks");
		super.clickOnListingRecord(recordIndex);
		
		super.fillInputBoxIn("title", title);
		super.fillInputBoxIn("startTime", startTime);
		super.fillInputBoxIn("endTime", endTime);
		super.fillInputBoxIn("workload", workload);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("info", info);
		super.fillInputBoxIn("isPublic", "true");
		
		super.clickOnSubmitButton("Update");
		
		super.checkErrorsExist();
		super.signOut();
	}

}
