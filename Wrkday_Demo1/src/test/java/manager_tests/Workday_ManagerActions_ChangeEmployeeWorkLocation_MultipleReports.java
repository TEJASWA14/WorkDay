package manager_tests;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import com.arsin.ArsinSeleniumAPI;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import wrkday_pom.Emphomepg;
import wrkday_pom.Empprofilepg;
import wrkday_pom.Login;
import wrkday_pom.Mgrprofilepg;
import wrkday_pom.TestData;
import wrkday_pom.Utility;

public class Workday_ManagerActions_ChangeEmployeeWorkLocation_MultipleReports {
	
	ArsinSeleniumAPI oASelFW = null;
	@Parameters({ "prjName", "testEnvironment","instanceName","sauceUser","moduleName","testSetName"})
	
	
	@BeforeMethod
	public void oneTimeSetUp(String prjName,String testEnvironment,String instanceName,String sauceUser,String moduleName,String testSetName) throws InterruptedException 
	{
	    String[] environment=new ArsinSeleniumAPI().getEnvironment(testEnvironment,this.getClass().getName());	
		String os=environment[0];
	    String browser=environment[1];
	    String testCasename=this.getClass().getSimpleName();	
		oASelFW = new ArsinSeleniumAPI(prjName,testCasename,browser,os,instanceName,sauceUser,moduleName,testSetName);
		oASelFW.startSelenium(oASelFW.getURL("WEB_URL_ADMIN",oASelFW.instanceName));
	}
	@Test(dataProvider = "iteration",alwaysRun=true)
    public void ChangeEmployeeWorkLocation(int rownum) throws Exception
    {	
		TestData tData 	= new TestData();
		Utility util	= new Utility();
		
		String filePath     = System.getProperty("user.dir")+"\\Data\\WorkDayTestData.xls";
        String sheet        = "WorkDay_Datasheet";
       /* 
    	int totalRows		 = tData.getRowCount(filePath,sheet);
    	System.out.println("totalRows"+totalRows);*/
    	
		//for(int rownum = 2; rownum<=totalRows;rownum++){	
    	//int rownum = 2;	
			System.out.println("data rowCount "+rownum );
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
	    	//oemp.clkOptionsAtHomeScreen("My Team Management");
	    	
	    	//Click Transfer Promote or Change Job
	    //	Mgrprofilepg oMgr = new Mgrprofilepg(oASelFW);
	    //	oMgr.click_Transfer_Promote_ChangeJob();
	    	
	    	//Change the location
	    //	oMgr.changeLocation(empName, "Atlanta, GA, USA - #2 National Data Plz NE");

	    	//Sign Out
	    	oemp.clkSgnout();
	    	Thread.sleep(2000);
	    	oASelFW.stopSelenium();
		//}
    }
	
	/* @AfterMethod
		public void closebrowser()
		{
			oASelFW.stopSelenium();
		}*/
	 
	 @DataProvider(name="iteration")
	 public Object[][] iteration() throws IOException{
		 TestData tData 	= new TestData();
		 String filePath    = System.getProperty("user.dir")+"\\Data\\WorkDayTestData.xls";
	     String sheet       = "WorkDay_Datasheet";
		 int rowNum 		= tData.getRowCount(filePath,sheet);
		 Object [][] sData	=new Object[rowNum-1][1];
		 for(int i=0;i<rowNum-1;i++){
			 sData[i][0] = i+2;
			 System.out.println("sData[i][0]=="+sData[i][0]);
		 }
		 return sData;
	 }
	 
	 
	 

}
