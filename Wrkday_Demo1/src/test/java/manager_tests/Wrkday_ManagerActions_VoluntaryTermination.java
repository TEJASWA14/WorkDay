package manager_tests;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.arsin.ArsinSeleniumAPI;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import wrkday_pom.Emphomepg;
import wrkday_pom.Empprofilepg;
import wrkday_pom.Login;
import wrkday_pom.Mgrprofilepg;
import wrkday_pom.TestData;
import wrkday_pom.Utility;


public class Wrkday_ManagerActions_VoluntaryTermination {

	
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
	    public void VoluntaryTerminate() throws Exception
	    {
		 	TestData tData  = new TestData();
		 	Utility util	= new Utility();
		 	
		 	String filePath     = System.getProperty("user.dir")+oASelFW.getConstValFrmPropertyFile("FilePath");
	        String sheet        = oASelFW.getConstValFrmPropertyFile("Sheet");
	        
	    	int totalRows		= tData.getRowCount(filePath,sheet);
	    	System.out.println("totalRows"+totalRows);
			
			//for(int rownum = 2; rownum<=totalRows;rownum++){
	    	for(int rownum = 2; rownum<=2;rownum++){	
				System.out.println("data rowCount "+rownum);
				String mgr_id		 = tData.getCellData(filePath, sheet, "Salary Mgr Id", rownum);
				String pswd			 = tData.getCellData(filePath, sheet, "Mgr Password", rownum);
				//String mgr_Name		 = tData.getCellData(filePath,sheet, "Mgr_Name", rownum);
				String emp_FName	 = tData.getCellData(filePath, sheet, "Employee First Name", rownum);
				String emp_LName	 = tData.getCellData(filePath, sheet, "Employee Last Name", rownum);
				String empName 		 = util.toCamelCase1(emp_FName)+" "+util.toCamelCase1(emp_LName);
				System.out.println("Complete empName "+empName );
				
				String mgr_Name	 	 = tData.getCellData(filePath, sheet, "Salary Manager", rownum);
				String[] str 		 = util.splitString(mgr_Name," ");
				String[] str1 		 = util.splitString(str[0],",");
				String managerName 	 = str1[1]+" "+str1[0];
				System.out.println("Manger name--"+managerName);
				
				//Log in to the application
		    	Login oUser = new Login (oASelFW);
		    	oUser.logIn(mgr_id,pswd);
		    	oASelFW.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		      	
		    	//Verify manager is logged in
		    	Emphomepg oemp = new Emphomepg(oASelFW);
		    	oemp.verifyEmployeeNameOnHomePage(managerName);
		    	
		    	//Click profile picture and view profile
		    	oemp.viewEmployeeProfilePage();
		    	
		    	//Verify manager name
		    	Empprofilepg oempprf = new Empprofilepg(oASelFW);
		    	oempprf.verifyEmployeeNameOnProfilePage(managerName);
		    	
		    	//Click home icon at top left corner
		    	oempprf.clickHomePageIcon();
		    	
		    	//Click on "My Team Management" icon
		    	oemp.clkOptionsAtHomeScreen("My Team Management");
		    	
		    	//Terminate the employee
		    	Mgrprofilepg oMgr = new Mgrprofilepg(oASelFW);
		    	oMgr.voluntaryTerminate(empName,"Voluntary","Voluntary > Death");
		    	
		    	//Sign Out
		    	oemp.clkSgnout();
			}
	 	 }
	    
	    @AfterClass
		public void closebrowser()
		{
				oASelFW.stopSelenium();
		}
	}

	

