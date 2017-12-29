package wrkday_pom;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;



import org.openqa.selenium.interactions.Actions;

import com.arsin.ArsinSeleniumAPI;
public class Mgrprofilepg {

	
	ArsinSeleniumAPI oASelFW;
	public Mgrprofilepg(ArsinSeleniumAPI oASelFW){
		this.oASelFW=oASelFW;
	}
	//Clicking on change Location
	
	public void click_Transfer_Promote_ChangeJob() throws Exception{
		
		//Click "Transfer, Promote or Change Job"
		oASelFW.effecta("waitForElementPresent","xpath=//div[@title='Transfer, Promote or Change Job']/div","100");
		oASelFW.effecta("clickAndWait","xpath=//div[@title='Transfer, Promote or Change Job']/div","Clicking Transfer, Promote or Change Job");
		}
	
	public void changeLocation(String empNameToLocate,String location) throws Exception{
		
		oASelFW.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Click the worker field
		oASelFW.effecta("waitForElementPresent","xpath=//span[@data-automation-id='promptIcon']/div","100");
		oASelFW.effecta("clickAndWait","xpath=//span[@data-automation-id='promptIcon']/div","Click Worker Field");
		
		//Click the "My Team"
		oASelFW.effecta("waitForElementPresent","xpath=//div[@data-automation-label='My Team']","100");
		oASelFW.effecta("clickAndWait","xpath=//div[@data-automation-label='My Team']","Selecting My Team");
		
		//Check either employee name is present or not after clicking My Team
		int Size=oASelFW.driver.findElements(By.xpath("//div[@data-automation-label='"+empNameToLocate+"']")).size();
        
        System.out.println("No of listes are :" +Size);
       
        if(Size==0)
        {	System.out.println(empNameToLocate+" Employee is not listed in dropdown===");
        	oASelFW.effecta("sendReport", "Employee is not listed in dropdown", "Skipping test script", "Pass");
        }
        else
        {	
        	//Select the employee name
	        oASelFW.effecta("waitForElementPresent","xpath=//div[@data-automation-label='"+empNameToLocate+"']","100");
			oASelFW.effecta("clickAndWait","xpath=//div[@data-automation-label='"+empNameToLocate+"']","Select Employee"+empNameToLocate);
			
			//Click Ok to confirm the employee
			oASelFW.effecta("waitForElementPresent","xpath=//button[@title='OK']","100");
			oASelFW.effecta("clickAndWait","xpath=//button[@title='OK']","Click OK to Confirm Employee");
			
			//Click the Edit button
			oASelFW.effecta("waitForElementPresent","xpath=//div[@title='Edit Start Details']","100");
			oASelFW.effecta("clickAndWait","xpath=//div[@title='Edit Start Details']","Click Edit Start Details");
			
			Actions actions = new Actions(oASelFW.driver);
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
			
			//Click "Where this perosn will be located after this change" field
			oASelFW.effecta("waitForElementPresent","xpath=(//span[@data-automation-id='promptIcon'])[4]","200");
			oASelFW.effecta("clickAndWait","xpath=(//span[@data-automation-id='promptIcon'])[4]","Click Where to locate");
			
			//Click "All Locations"
			oASelFW.effecta("waitForElementPresent","xpath=//div[@data-automation-label='All Locations']","100");
			oASelFW.effecta("clickAndWait","xpath=//div[@data-automation-label='All Locations']","Click All Locations");
			
			//Select the location
			oASelFW.effecta("waitForElementPresent","xpath=//div[@data-automation-label='"+location+"']","100");
			oASelFW.effecta("clickAndWait","xpath=//div[@data-automation-label='"+location+"']","Select Location "+location);
			
			//Click "Why are you making this change" field
			oASelFW.effecta("waitForElementPresent","xpath=(//span[@data-automation-id='promptIcon'])[1]","100");
			oASelFW.effecta("clickAndWait","xpath=(//span[@data-automation-id='promptIcon'])[1]","Click Why are you making this change?");
	        
			//Click Job Details
			oASelFW.effecta("waitForElementPresent","xpath=//div[@data-automation-label='Job Details']","100");
			oASelFW.effecta("clickAndWait","xpath=//div[@data-automation-label='Job Details']","Click Job Details");
			
			//Click "Change Location"
			oASelFW.effecta("waitForElementPresent","xpath=//div[@data-automation-label='Change Location']","100");
			oASelFW.effecta("clickAndWait","xpath=//div[@data-automation-label='Change Location']","Change Location");
		
			//Click "Save Start Details" button
			oASelFW.effecta("waitForElementPresent","xpath=//div[@title='Save Start Details']","100");
			oASelFW.effecta("clickAndWait","xpath=//div[@title='Save Start Details']","Save Start Details");
        }
		
	}
	
	// Terminating employee
	public void voluntaryTerminate(String empToTerminate, String reason, String subReason) throws Exception{
		
		oASelFW.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		oASelFW.effecta("waitForElementPresent","xpath=(//span[@data-automation-id='moreLinkLabel'])[1]","100");
		oASelFW.effecta("clickAndWait","xpath=(//span[@data-automation-id='moreLinkLabel'])[1]","Clicking More");
        
        //Click Terminate on My Team Management Screen
        oASelFW.effecta("waitForElementPresent","xpath=//div[@title='Terminate']/div","100");
		oASelFW.effecta("clickAndWait","xpath=//div[@title='Terminate']/div","Clicking Terminate");
		
		//Click Employee field
		oASelFW.effecta("waitForElementPresent","xpath=//span[@data-automation-id='promptIcon']","100");
		oASelFW.effecta("clickAndWait","xpath=//span[@data-automation-id='promptIcon']","Clicking Edit button");
		
		//Select 'My Team' in Employee field
		oASelFW.effecta("waitForElementPresent","xpath=//div[@data-automation-label='My Team']","100");
		oASelFW.effecta("clickAndWait","xpath=//div[@data-automation-label='My Team']","Selecting My Team");
		
		//Check whether employee is present or not in the list
		System.out.println("emp name is  "+empToTerminate);
		int Size=oASelFW.driver.findElements(By.xpath("//div[text()='"+empToTerminate+"']")).size();
		System.out.println("xpath is   div[text()='"+empToTerminate+"']");
        System.out.println("Size value is:" +Size);
       
        if(Size==0)
        {	System.out.println(empToTerminate+" Employee is not listed in dropdown===");
        	oASelFW.effecta("sendReport", "Employee is not listed in dropdown", "Skipping Execution", "Pass");
        }
        else
        {
        	//Select employee to terminate
			String empname=oASelFW.effecta("waitForElementPresent","xpath=//div[@data-automation-label='"+empToTerminate+"']","100");
			oASelFW.effecta("clickAndWait","xpath=//div[@data-automation-label='"+empToTerminate+"']","Selecting "+empToTerminate);
	
			//Click Ok Button 
			oASelFW.effecta("waitForElementPresent","xpath=//button[@data-automation-id='wd-CommandButton_uic_okButton']","100");
			oASelFW.effecta("clickAndWait","xpath=//button[@data-automation-id='wd-CommandButton_uic_okButton']","Confirming Termination Click Ok");
			
			//Click Edit button for Reason
			oASelFW.effecta("waitForElementPresent","xpath=(//div[@title='Edit'])[1]","100");
			oASelFW.effecta("clickAndWait","xpath=(//div[@title='Edit'])[1]","Edit button for Reason");
			
			//Click Primary Reason field
			oASelFW.effecta("waitForElementPresent","xpath=//div[@data-automation-id='promptSearchButton']","100");
			oASelFW.effecta("clickAndWait","xpath=//div[@data-automation-id='promptSearchButton']","Search Primary Reason");
			
			//Select the reason type Voluntary or Involuntary
	        oASelFW.effecta("waitForElementPresent","xpath=//div[@data-automation-label='"+reason+"']","100");
			oASelFW.effecta("clickAndWait","xpath=//div[@data-automation-label='"+reason+"']","Selecting "+reason);
			
			//Select th ereason
			oASelFW.effecta("waitForElementPresent","xpath=//div[@data-automation-label='"+subReason+"']","100");
			oASelFW.effecta("clickAndWait","xpath=//div[@data-automation-label='"+subReason+"']","Selecting Voluntary > Death");
        }
	}
	

	
}
