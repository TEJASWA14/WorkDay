package employee_tests;
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


public class Workday_ChangeEmployeeVeteranStatus {

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
    public void changeVeteranStatus() throws Exception
    {
    	TestData tData 	= new TestData();
    	Utility util	= new Utility();
    	
    	String filePath     = System.getProperty("user.dir")+oASelFW.getConstValFrmPropertyFile("FilePath");
        String sheet        = oASelFW.getConstValFrmPropertyFile("Sheet");

        int totalRows		 = tData.getRowCount(filePath,sheet);
    	System.out.println("totalRows"+totalRows);
    	System.out.println("filePath=="+filePath);
		//for(int rownum = 2; rownum<=totalRows; rownum++){
			for(int rownum = 2; rownum<=2; rownum++){	
			System.out.println("data rowCount "+rownum );
			String emp_id		 = tData.getCellData(filePath,sheet, "Employee ID", rownum);
			String pswd			 = tData.getCellData(filePath,sheet, "Emp Password", rownum);
			String emp_FName	 = tData.getCellData(filePath,sheet, "Employee First Name", rownum);
			String emp_LName	 = tData.getCellData(filePath,sheet, "Employee Last Name", rownum);
			String empName 		 = util.toCamelCase1(emp_FName)+" "+util.toCamelCase1(emp_LName);
			System.out.println("Complete empName "+empName );
			
			String mgr_Name	 	 = tData.getCellData(filePath,sheet, "Salary Manager", rownum);
			String[] str 		 = util.splitString(mgr_Name," ");
			String[] str1 		 = util.splitString(str[0],",");
			String managerName 	 = str1[1]+" "+str1[0];
			System.out.println("Manger name--"+managerName);
			
			String country		 = tData.getCellData(filePath,sheet, "Country", rownum);
			
			if(country.equalsIgnoreCase("CAN")){
				System.out.println("Vetaran status is not applicable for Canada users");
				oASelFW.effecta("sendReport", empName+" is Canada employee", "Vetaran status is not applicable for Canada users", "Pass");
			}
			else{
				//Log in to the application
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
		    	
		    	//Click Personal Data > Change My Veteran Status Identification
		    	int size=oempprf.performRequiredAction("Personal Data","Change My Veteran Status Identification");
		    	if(size==1){
			    	//Click radio button "IDENTIFY AS A VETERAN, JUST NOT A PROTECTED VETERAN"
			    	oempprf.changeVeteranStatus("IDENTIFY AS A VETERAN, JUST NOT A PROTECTED VETERAN");
			    	oASelFW.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    	}
		    	oemp.clkSgnout();
			}
		}
    }
    
    @AfterClass
	public void closebrowser() throws Exception
	{
    	try{
    		oASelFW.stopSelenium();
    	}catch(Exception e){
			
		}
	}
}










