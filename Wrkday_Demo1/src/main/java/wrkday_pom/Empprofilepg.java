package wrkday_pom;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.arsin.ArsinSeleniumAPI;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;
public class Empprofilepg {
	
	ArsinSeleniumAPI oASelFW;
	public Empprofilepg(ArsinSeleniumAPI oASelFW){
		this.oASelFW=oASelFW;
	}
	
	public void verifyEmployeeNameOnProfilePage(String emp) throws Exception{
		
		oASelFW.effecta("waitForElementPresent","xpath=.//*[@id='wd-WorkerProfile-6$25778']/div[1]/span","100");
		
		//fetch the employee name from the employee profile page
		String empName =  oASelFW.driver.findElement(By.xpath(".//*[@id='wd-WorkerProfile-6$25778']/div[1]/span")).getText();
		String seName  = emp;
		    
		if ((empName.equals(seName.trim()))) 
		{
			oASelFW.reportStepDetails("Verify Employee Name on profile page", "Actual employee name : "+empName+", Expected employee name : "+seName, "Pass");
			System.out.println("Pass------"+empName);
		}
		else
		{
			oASelFW.reportStepDetails("Verify Employee Name on profile page", "Actual employee name : "+empName+", Expected employee name : "+seName, "Fail");
			System.out.println("Fail------"+empName);
		}
	}
			
	//Verifying Manager Name
	public void verifyManagerOfEmployee(String Mgr) throws Exception{
		
		oASelFW.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		oASelFW.effecta("waitForElementPresent","xpath=//div[contains(text(),'Manager')]/following-sibling::div/div/span","100");
		String mgrName = oASelFW.driver.findElement(By.xpath("//div[contains(text(),'Manager')]/following-sibling::div/div/span")).getText();
		String smName  = Mgr;
		
		if ((mgrName.equals(smName.trim()))) 
		{
			oASelFW.reportStepDetails("Verify Manager Name on employee profile page", "Actual manager name : "+mgrName+", Expected manager name : "+smName, "Pass");
			System.out.println("Pass------"+mgrName);
		}
		else
		{
			oASelFW.reportStepDetails("Verify Manager Name on employee profile page", "Actual manager name : "+mgrName+", Expected manager name : "+smName, "Fail");
			System.out.println("Fail------"+mgrName);
		}
	}
	
	//Clicking on Home Page
	public void clickHomePageIcon() throws Exception{
		
		System.out.println("click home Page");
		oASelFW.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Click home icon at top left corner
		oASelFW.effecta("waitForElementPresent","xpath=//button[@data-automation-id='wd_header_home_icon']","100");
		oASelFW.effecta("clickAndWait","xpath=//button[@data-automation-id='wd_header_home_icon']","Home Button");
	}
  
	// Clicking on Actions button
	public void clickActionsButtonOnProfilePage() throws Exception{
		
		oASelFW.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Click the Actions button on employee profile page
		oASelFW.effecta("waitForElementPresent","xpath=//div[@data-automation-id='workerProfile']/div[1]/div[4]/button","100");
		oASelFW.effecta("clickAndWait","xpath=//div[@data-automation-id='workerProfile']/div[1]/div[4]/button","Actions");
		System.out.println("Clicked actions");
		//Thread.sleep(1000);
	}
	
	//Clicking on Contact Details
	public void click_Change_Contact_Information(String menu) throws Exception{
		
		oASelFW.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//Click Change contact information under Personal Data menu
		oASelFW.effecta("waitForElementPresent","xpath=//div[contains(@data-automation-label,'"+menu+"')]","100");
		oASelFW.effecta("clickAndWait","xpath=//div[contains(@data-automation-label,'"+menu+"')]","Change contact information");
	}
	
	
	public void clickEditPrimaryAddress() throws InterruptedException {
		
		oASelFW.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		oASelFW.effecta("waitForElementPresent","xpath=.//*[@id='NO_METADATA_ID']","100");
 		String empAddr = oASelFW.driver.findElement(By.xpath(".//*[@id='NO_METADATA_ID']")).getText();
 		oASelFW.reportStepDetails("Employee Address", empAddr  , "Pass");
 		
 		System.out.println("SingleLineEmpAddr--"+empAddr);
 		oASelFW.effecta("waitForElementPresent","xpath=//div[@title='Edit Primary Address']","100");
 		oASelFW.effecta("clickAndWait","xpath=//div[@title='Edit Primary Address']","Edit Address");
 		//oASelFW.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
     
   //Change Contact Details
 	public void changeContactDetails(String address, String Date) throws Exception{
 		
 		oASelFW.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 		
 		oASelFW.driver.findElement(By.xpath("//div/span[@data-automation-id='dateSectionMonth']")).click();
 		System.out.println("after debugg date value is  "+Date);
 		
 		Thread.sleep(2000);
 		//Enter the effective date
 		oASelFW.driver.findElement(By.xpath("//div[text()='Effective Date']/../following::div/input")).sendKeys(Date);
 		oASelFW.reportStepDetails("Entering Effective Date ", "Entered Date: "+Date  , "Pass");
 		
 		Thread.sleep(3000);
 		//Enter the address
 		oASelFW.effecta("waitForElementPresent","//div[text()='Address Line 1']/../following::div/input","100");
 		//oASelFW.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
 		oASelFW.effecta("typeSpecifiedText","//div[text()='Address Line 1']/../following::div/input",address,"Addr1");
 		Thread.sleep(5000);
 		oASelFW.reportStepDetails("Entering Address", "Entered Address: "+address  , "Pass");
 		System.out.println("value is "+address);
 		
 		//Click Submit button
 		oASelFW.effecta("waitForElementPresent","//span[text()='Submit']","100");
 		oASelFW.effecta("clickAndWait","//span[text()='Submit']","Submit");
 		
 		//Verify the message "Process Successfully Completed" on the screen
 		oASelFW.effecta("waitForElementPresent","//div[text()='Process Successfully Completed']","100");
 		String succ = oASelFW.driver.findElement(By.xpath("//div[text()='Process Successfully Completed']")).getText();
 		
 		if(succ.contains("Process Successfully Completed"))
 		{	
 			System.out.println("address saved successfully");
 			oASelFW.reportStepDetails("Verify Success Message", "Actual message : "+succ+", Expected : Process Successfully Completed", "Pass");
 		}
 		else
 		{	
 			System.out.println("address not saved");
 			oASelFW.reportStepDetails("Verify Success Message", "Actual message : "+succ+", Expected : Process Successfully Completed", "Fail");
 		}	
 		
 		//Click done button 
 		oASelFW.effecta("waitForElementPresent","//span[text()='Done']","100");
 		oASelFW.effecta("clickAndWait","//span[text()='Done']","Done");
 		//Thread.sleep(1000);
 	}
 	
 	
 	public void editContactDetails(String label, String value) throws InterruptedException{
 		
 		oASelFW.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 		//Enter the effective date
 		oASelFW.driver.findElement(By.xpath("//div[text()='"+label+"']/../following::div/input")).clear();
 		Thread.sleep(2000);
 		oASelFW.driver.findElement(By.xpath("//div[text()='"+label+"']/../following::div/input")).sendKeys(value);
 		oASelFW.reportStepDetails("Entering Effective Date ", "Entered Date: "+value  , "Pass");
 		
 		System.out.println("value is "+value);
 	}
 	
 	
 /*	
 	
 		//Resetting Data
 	public void resetContctDtls() throws Exception{
 		oASelFW.effecta("waitForElementPresent","css=#NO_METADATA_ID","100");
 		String empAddr = oASelFW.driver.findElement(By.cssSelector("#NO_METADATA_ID")).getText();
 		oASelFW.reportStepDetails("Employee Address", empAddr  , "Pass");
 		oASelFW.effecta("waitForElementPresent","css=.WPQ.WEQ.WHR.WER","100");
 		oASelFW.effecta("clickAndWait","css=.WPQ.WEQ.WHR.WER","Edit Address");
 		
 		Thread.sleep(500);
 		oASelFW.effecta("waitForElementPresent","css=div[data-automation-id='textInput']>input[dir='ltr']:nth-of-type(1)","100");
 		Thread.sleep(500);
 		oASelFW.effecta("typeSpecifiedText","css=div[data-automation-id='textInput']>input[dir='ltr']:nth-of-type(1)","611 Jones Street","Addr1");
 		Thread.sleep(500);
 		oASelFW.effecta("typeSpecifiedText","css=div[data-automation-id='textInput']>input[dir='ltr']:nth-of-type(1)","611 Jones Street","Addr1");
 		 		
 		oASelFW.effecta("waitForElementPresent","css=.WK1G.WGPH.WPCH.WL2G.WO1G[title='Submit']","100");
 		oASelFW.effecta("clickAndWait","css=.WK1G.WGPH.WPCH.WL2G.WO1G[title='Submit']","Submit");
 		
 		oASelFW.effecta("waitForElementPresent","xpath=.//*[@id='56$84336']","100");
 		String succ = oASelFW.driver.findElement(By.xpath(".//*[@id='56$84336']")).getText();
 		
 		if(succ.contains("Process Successfully Completed"))
 		{
 		oASelFW.reportStepDetails("Verifying Message", succ  , "Pass");
 		}
 		else
 		{
 			oASelFW.reportStepDetails("Verifying Message", succ  , "Fail");
 			}	
 		
 		oASelFW.effecta("waitForElementPresent","css=.WK1G.WO1G.WGPH.WPCH.WM2G","100");
 		oASelFW.effecta("clickAndWait","css=.WK1G.WO1G.WGPH.WPCH.WM2G","Done");
 	}*/

    //Verify Contact Details
 	public void verifyContactDetails(String addr1,String fDate) throws Exception{
		
 		oASelFW.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 		
 		//Verify Effective Date
 		String date = oASelFW.driver.findElement(By.xpath("//div[text()='Effective Date']/../following::div/input")).getAttribute("value");
		
		System.out.println("Verify Date------"+date);
		if (date.equals(fDate))
		{
			System.out.println("Verify Employee EffDate Pass------"+date);
			oASelFW.reportStepDetails("Verify Employee EffDate", "Actual Effective Date : "+date+", Expected : "+fDate, "Pass");
		}
		else
		{
			System.out.println("Verify Employee EffDate Fail------"+date);
			oASelFW.reportStepDetails("Verify Employee EffDate", "Actual Effective Date : "+date+", Expected : "+fDate, "Fail");
		}
		
		//Verify the address
		//oASelFW.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String empAddr = oASelFW.driver.findElement(By.xpath("//div[text()='Address Line 1']/../following::div/input")).getAttribute("value");
		System.out.println("empAddr------"+empAddr);
		
		if (empAddr.equals(addr1))
		{
			System.out.println("Verify Employee Address Pass------"+empAddr);
			oASelFW.reportStepDetails("Verify Employee Address", "Actual Address : "+empAddr+", Expected Address : "+addr1, "Pass");
		}
		else
		{	
			System.out.println("Verify Employee Address Fail------"+empAddr);
			oASelFW.reportStepDetails("Verify Employee Address", "Actual Address : "+empAddr+", Expected Address : "+addr1, "Fail");
		}
}
 
 	
 	public int performRequiredAction(String menu, String subMenu) throws Exception{
 		
 		oASelFW.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	Robot robot = new Robot();
        robot.mouseMove(0,0);
        
        //Performing the mouse hover operation on the menus under Actions
        Actions action = new Actions(oASelFW.driver); 
        WebElement mainMenu = oASelFW.driver.findElement(By.xpath("//div[contains(@data-automation-label,'"+menu+"')]")); 
        action.moveToElement(mainMenu).build().perform();
        oASelFW.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        
        //Checking if required subMenu is present under the menu
        int Size=oASelFW.driver.findElements(By.xpath("//div[@data-automation-label='"+subMenu+"']")).size();
        System.out.println("No of listes are :" +Size);
       
        if(Size==0)
        {
        	oASelFW.effecta("sendReport", "'"+subMenu+"' is not displayed under "+menu, "Skipping test script", "Pass");
        }
        else
        {	
        	//Clicking the subMenu
        	WebElement wev  = oASelFW.driver.findElement(By.xpath("//div[@data-automation-label='"+subMenu+"']"));
        	//WebElement wev  = oASelFW.driver.findElement(By.xpath("//div[text()='"+subMenu+"']"));
    		((JavascriptExecutor) oASelFW.driver).executeScript("arguments[0].click();", wev);
       	}
	        oASelFW.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	        oASelFW.reportStepDetails("Select "+menu+">"+subMenu, "Selected "+menu+">"+subMenu, "Pass");
	        return Size;
 	}
 	
 	//Change Military Status
    @SuppressWarnings("deprecation")
	public void changeVeteranStatus(String vStatus) throws Exception{
    	
    	oASelFW.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	int Size=oASelFW.driver.findElements(By.xpath("//label[contains(text(),'"+vStatus+"')]")).size();
        System.out.println("No of listes are :" +Size);
       
        if(Size==0)
        {
        	oASelFW.effecta("sendReport", vStatus+" is not displayed on screen", "Skipping test script", "Pass");
        }
        else
        {	
        	//Clicking the vetaran status radio button
	    	oASelFW.effecta("waitForElementPresent","xpath=//label[contains(text(),'"+vStatus+"')]","100");
	    	oASelFW.effecta("clickAndWait","xpath=//label[contains(text(),'"+vStatus+"')]","Select "+vStatus);
	        oASelFW.reportStepDetails("Action Peformed", "Select "+vStatus  , "Pass");
	
	        //Clicking the Submit button
			oASelFW.effecta("waitForElementPresent","xpath=//button[@title='Submit']","100");
			oASelFW.effecta("clickAndWait","xpath=//button[@title='Submit']","Submit");
			
			//Verifying "Process Successfully Completed" message after submitting 
			oASelFW.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			oASelFW.effecta("waitForElementPresent","xpath=//*[contains(text(),'Process Successfully Completed')]","100");
		 	String succ = oASelFW.driver.findElement(By.xpath("//*[contains(text(),'Process Successfully Completed')]")).getText();
		 		
	 		if(succ.contains("Process Successfully Completed"))
	 		{
	 			oASelFW.reportStepDetails("Verifying Message", "Actual message: "+succ+", Expected message: Process Successfully Completed", "Pass");
	 			System.out.println("vetaran pass");
	 		}
	 		else
	 		{
	 			oASelFW.reportStepDetails("Verifying Message", "Actual message: "+succ+", Expected message: Process Successfully Completed", "Fail");
	 			System.out.println("vetaran fail");
	 		}	
	 		
	 		//Clicking the Done button
	 		oASelFW.effecta("waitForElementPresent","xpath=//button[@title='Done']","100");
	 		oASelFW.effecta("clickAndWait","xpath=//button[@title='Done']","Click Done");
	 		oASelFW.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	 	}
	}
   }


