package employee_tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.arsin.ArsinSeleniumAPI;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import wrkday_pom.Emphomepg;
import wrkday_pom.Empprofilepg;
import wrkday_pom.Login;
import wrkday_pom.TestData;
import wrkday_pom.Utility;

public class Workday_ChangeEmployeeContactInformation_Primaryaddress {

	ArsinSeleniumAPI oASelFW = null;
	@Parameters({ "prjName", "testEnvironment","instanceName","sauceUser","moduleName","testSetName"})
	
	@BeforeClass
	public void oneTimeSetUp(String prjName,String testEnvironment,String instanceName,String sauceUser,String moduleName,String testSetName) throws InterruptedException 
	{
		    String[] environment=new ArsinSeleniumAPI().getEnvironment(testEnvironment,this.getClass().getName());	
			String os=environment[0];
		    String browser=environment[1];String testCasename=this.getClass().getSimpleName();	
			oASelFW = new ArsinSeleniumAPI(prjName,testCasename,browser,os,instanceName,sauceUser,moduleName,testSetName);
			oASelFW.startSelenium(oASelFW.getURL("WEB_URL_ADMIN",oASelFW.instanceName));
	}
		
    @Test
    public void changePrimaryAddress() throws Exception
    {
    	TestData tData 	= new TestData();
    	Utility util	= new Utility();
    	
		try{
			
			String filePath     = System.getProperty("user.dir")+oASelFW.getConstValFrmPropertyFile("FilePath");
	        String sheet        = oASelFW.getConstValFrmPropertyFile("Sheet");
	        
			int totalRows		 = tData.getRowCount(filePath,sheet);
			System.out.println("totalRows"+totalRows);
			System.out.println("filePath=="+filePath);
			//for(int rownum = 2; rownum<=totalRows; rownum++){
				for(int rownum = 2; rownum<=2; rownum++){	
				System.out.println("data rowCount " + rownum);
				
				String emp_id		 = tData.getCellData(filePath, sheet, "Employee ID", rownum);
				String pswd			 = tData.getCellData(filePath, sheet, "Emp Password", rownum);
				String emp_FName	 = tData.getCellData(filePath, sheet, "Employee First Name", rownum);
				String emp_LName	 = tData.getCellData(filePath, sheet, "Employee Last Name", rownum);
				String newAddress	 = tData.getCellData(filePath, sheet, "new_Addr", rownum);
				String resetAddress	 = tData.getCellData(filePath, sheet, "Curr_Addr", rownum);
				String empName 		 = util.toCamelCase1(emp_FName)+" "+util.toCamelCase1(emp_LName);
				String formattedDate = util.getDaysBetweenDates(1);
				String date 		 = formattedDate.replace("/", "");
				
				String mgr_Name	 	 = tData.getCellData(filePath, sheet, "Salary Manager", rownum);
				String[] str 		 = util.splitString(mgr_Name," ");
				String[] str1 		 = util.splitString(str[0],",");
				String managerName 	 = str1[1]+" "+str1[0];
				System.out.println("Manger name--"+managerName);
				
				if(rownum == 10 || rownum == 11){
					System.out.println("Skipped " +rownum+"th row number record");
					oASelFW.effecta("sendReport", empName+" is Canada employee", "Date is already set some future date for Canada users", "Pass");
				}
				else{
					
					//Login to the application
			    	Login oUser = new Login (oASelFW);
			    	oUser.logIn(emp_id,pswd);
			    	oASelFW.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			    	
			      	//Verify Employee is logged in
			    	Emphomepg oemp = new Emphomepg(oASelFW);
			    	oemp.verifyEmployeeNameOnHomePage(empName);
			    	
			    	//Click profile picture and view profile
			    	oemp.viewEmployeeProfilePage();

			    	//Verify employee name
			    	Empprofilepg oempprf = new Empprofilepg(oASelFW);
			    	oempprf.verifyEmployeeNameOnProfilePage(empName);
			    	
			    	//Verify manager name
			    	oempprf.verifyManagerOfEmployee(managerName);
			    	
			    	//Click actions button
			    	oempprf.clickActionsButtonOnProfilePage();
			    	
			    	//Click Change Contact Information
			    	oempprf.click_Change_Contact_Information("Personal Data");
			    	
			    	//Click Edit Primary Address Button
			    	oempprf.clickEditPrimaryAddress();
			    	
			    	/*oempprf.editContactDetails("Effective Date",date);
			    	oempprf.editContactDetails("Address Line 1",newAddress);
			    	oempprf.editContactDetails("Address Line 2","pqrst");
			    	oempprf.editContactDetails("City","Denver");*/
			    	
			    	//Update the address and date
			    	oempprf.changeContactDetails(newAddress,date);
			    	
			    	//Click actions button
			    	oempprf.clickActionsButtonOnProfilePage();
			    	
			    	//Click Change Contact Information
			    	oempprf.click_Change_Contact_Information("Personal Data");
			    	
			    	//Click Edit Primary Address Button
			    	oempprf.clickEditPrimaryAddress();
			    	
			    	//Verify the address and date
			    	oempprf.verifyContactDetails(newAddress,formattedDate);
			    	
			    	/*oempprf.editContactDetails("Effective Date",date);
			    	oempprf.editContactDetails("Address Line 1",resetAddress);*/
			    	
			    	//Reset the details and verify
			    	oempprf.changeContactDetails(resetAddress,date);
			    	oempprf.clickActionsButtonOnProfilePage();
			    	oempprf.click_Change_Contact_Information("Personal Data");
			    	oempprf.clickEditPrimaryAddress();
			    	oempprf.verifyContactDetails(resetAddress,formattedDate);
			    	
			    	//oASelFW.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			    	oemp.clkSgnout();
				}
			}
	    }
		catch(Exception e){
			
		}
    }
    
    @AfterClass
	public void oneTearDown() throws Exception
	{
    	try{
    		oASelFW.stopSelenium();
    	}catch(Exception e){
			
		}
	}
}
