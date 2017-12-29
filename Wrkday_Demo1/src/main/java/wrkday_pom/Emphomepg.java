package wrkday_pom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.arsin.*;


public class Emphomepg {
	
	ArsinSeleniumAPI oASelFW;
	public Emphomepg(ArsinSeleniumAPI oASelFW){
		this.oASelFW=oASelFW;
	}
	
	//Verify Employee name
	public void verifyEmployeeNameOnHomePage(String emp) throws Exception{
		
		oASelFW.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//fetch employee name from application
		oASelFW.effecta("waitForElementPresent","xpath=.//*[@id='promptOption-gwt-uid-1']","100");
		String empName =  oASelFW.driver.findElement(By.xpath(".//*[@id='promptOption-gwt-uid-1']")).getText();
		
		String seName = emp;
		System.out.println("Value"+empName+"==="+seName);
		if ((empName.equals(seName.trim()))) 
		{
			System.out.println("Pass------"+empName);
			oASelFW.reportStepDetails("Verify Employee Name on home page", "Actual employee name : "+empName+", Expected employee name : "+seName  , "Pass");
		}
		else
		{	
			System.out.println("Fail------"+empName);
			oASelFW.reportStepDetails("Verify Employee Name on home page", "Actual employee name : "+empName+", Expected employee name : "+seName, "Fail");	
		}
	}
	
	//Click on Employee Picture and select view profile	
	public void viewEmployeeProfilePage() throws Exception{
		
		oASelFW.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Click on Employee picture at right top corner
		oASelFW.effecta("waitForElementPresent","xpath=.//*[@id='wd-Worker-NO_METADATA_ID']/span[2]/img","100");
		oASelFW.effecta("clickAndWait","xpath=.//*[@id='wd-Worker-NO_METADATA_ID']/span[2]/img","EmployeePicture");
		
		//Click on View Profile
		oASelFW.effecta("waitForElementPresent","//span[text()='View Profile']/..","100");
		oASelFW.effecta("clickAndWait","//span[text()='View Profile']/..","ViewProfile");
		oASelFW.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void clkOptionsAtHomeScreen(String option) throws Exception{
		
		oASelFW.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Click My Team Management icon on manager's home screen
		oASelFW.effecta("waitForElementPresent","xpath=//span[@title='"+option+"']","100");
		oASelFW.effecta("clickAndWait","xpath=//span[@title='"+option+"']","Clicking My Team Management");
		}
			
	
	public void clkSgnout() throws Exception {
		
		oASelFW.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		oASelFW.effecta("waitForElementPresent","xpath=.//*[@id='wd-Worker-NO_METADATA_ID']/span[2]/img","100");
		oASelFW.effecta("clickAndWait","xpath=.//*[@id='wd-Worker-NO_METADATA_ID']/span[2]/img","EmployeePicture");
		
		oASelFW.effecta("waitForElementPresent","xpath=//span[@data-automation-id='Sign_Out']","100");
		oASelFW.effecta("clickAndWait","xpath=//span[@data-automation-id='Sign_Out']","Sign Out");
		System.out.println("Sign Out");
		//oASelFW.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
	}
	
	
	
	
	

}
