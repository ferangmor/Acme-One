package acme.testing.administrator.dashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;

import acme.testing.AcmeOneTest;

public class AdministratorDashboardShowTest extends AcmeOneTest{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/dashboard/show.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void show() {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator","Dashboard");
		
		super.checkExists(By.xpath("(//TD)[1]"));
		super.checkExists(By.xpath("(//TD)[2]")); 
		super.checkExists(By.xpath("(//TD)[3]")); 
		super.checkExists(By.xpath("(//TD)[4]")); 
		super.checkExists(By.xpath("(//TD)[5]")); 
		super.checkExists(By.xpath("(//TD)[6]")); 
		super.checkExists(By.xpath("(//TD)[7]")); 
		super.checkExists(By.xpath("(//TD)[8]")); 
		super.checkExists(By.xpath("(//TD)[9]")); 
		super.checkExists(By.xpath("(//TD)[10]")); 
		super.checkExists(By.xpath("(//TD)[11]")); 
		super.checkExists(By.xpath("(//TD)[12]")); 
		super.checkExists(By.xpath("(//TD)[13]")); 
		super.checkExists(By.xpath("(//TD)[14]")); 
		super.checkExists(By.xpath("(//TD)[15]"));  
		
		super.signOut();
		
	}

}
