package wrkday_pom;

import java.util.concurrent.TimeUnit;

import com.arsin.*;

public class Login {
	ArsinSeleniumAPI oASelFW;
	public Login(ArsinSeleniumAPI oASelFW){
		this.oASelFW=oASelFW;
	}
	
	//login method
	public void logIn(String emp_id, String pswd) throws Exception{
		
		System.out.println("emp_id "+emp_id);
		System.out.println("Pass "+pswd);
		oASelFW.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		//Enter Employee ID
	 	oASelFW.effecta("waitForElementPresent","xpath=//input[@aria-label='user name']","100");
	 	oASelFW.effecta("typeSpecifiedText","xpath=//input[@aria-label='user name']",emp_id,"username");
		
	 	//Enter Password
		oASelFW.effecta("waitForElementPresent","xpath=//input[@aria-label='password']","100");
		oASelFW.effecta("typeSpecifiedText","xpath=//input[@aria-label='password']",pswd,"password");
		
		//Click Login button
		oASelFW.effecta("waitForElementPresent","xpath=//button[contains(text(),'Sign In')]","100");
		oASelFW.effecta("clickAndWait","xpath=//button[contains(text(),'Sign In')]","Login");
		oASelFW.effecta("sendReport", "Logged in with employee id: "+emp_id+"", "test is started", "Pass");
				
		Thread.sleep(10000);
	}	
	
	/*//login method
		public void loginSubmit(String emp) throws Exception{
			
			TestData tData = new TestData("C:\\Users\\tgupta\\Desktop\\Workday\\Wrkday_Demo1\\Data\\WorkDayTestData.xlsx");
			String emp_id=tData.getCellData("WorkDay_Datasheet", "Emp_ID", rownum);
			String pswd=tData.getCellData("WorkDay_Datasheet", "Emp_Pass", rownum);
			
			Thread.sleep (100);
		 	oASelFW.effecta("waitForElementPresent","xpath=.//*[@id='container']/div[1]/div[1]/div/div[2]/div/div[1]/div/div[1]/div[3]/div[1]/div/input","100");
		 	oASelFW.effecta("typeSpecifiedText","xpath=.//*[@id='container']/div[1]/div[1]/div/div[2]/div/div[1]/div/div[1]/div[3]/div[1]/div/input",emp,"username");
				
			oASelFW.effecta("waitForElementPresent","xpath=html/body/div[1]/div[2]/div[1]/div[1]/div/div[2]/div/div[1]/div/div[1]/div[3]/div[2]/div/input","100");
			oASelFW.effecta("typeSpecifiedText","xpath=html/body/div[1]/div[2]/div[1]/div[1]/div/div[2]/div/div[1]/div/div[1]/div[3]/div[2]/div/input","pW57F7-40","password");
					
			oASelFW.effecta("waitForElementPresent","xpath=html/body/div[1]/div[2]/div[1]/div[1]/div/div[2]/div/div[1]/div/div[1]/button","100");
			oASelFW.effecta("clickAndWait","xpath=html/body/div[1]/div[2]/div[1]/div[1]/div/div[2]/div/div[1]/div/div[1]/button","Login");
		}	
*/}
