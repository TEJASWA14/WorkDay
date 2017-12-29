package reporting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class HtmlGenerator {

public void generateReport(List<ISuite> suites) {

		File file = new File("TestExecutionSuite.html");
		
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			for(ISuite suite:suites){
				String suiteName=suite.getName();
				Map<String,ISuiteResult> suiteResults=suite.getResults();
				for(ISuiteResult suiteResult:suiteResults.values() ){
					ITestContext itextContext= suiteResult.getTestContext();
					int pass=itextContext.getPassedTests().getAllResults().size();
					int fail=itextContext.getFailedTests().getAllResults().size();
					int skip=itextContext.getSkippedTests().getAllResults().size();
					int totTcs=pass+fail+skip;
					String passed=String.valueOf(pass);
					String failed=String.valueOf(fail);
					String skipped=String.valueOf(skip);
				  	Date start = itextContext.getStartDate();
				    Date end = itextContext.getEndDate();
				    long ms = end.getTime() - start.getTime();
				    long totalTime=(ms / 1000);
				    String htmlHead="<!DOCTYPE html><html><head><style>body{background-color:#F7F9F9}table { width:50%;}table, th, td {  border: 1px solid black; border-collapse: collapse;}"
							+ " th, td { padding: 5px;text-align: left; }table#t01 tr:nth-child(even) {  background-color: #D4E6F1; } table#t01 tr:nth-child(odd) "
							+ "{  background-color:#E6E6FA; } table#t01 th { background-color: #87CEFA; color: Black; }</style></head><body>	<div id='backgroundpaper'>"
							+ "<center> <h1>  Automation Test Suite Execution Report </h1><table><tr bgcolor=#87CEFA><th align=left><b> Suite Name </b></th><td colspan=6, bgcolor=#E6E6FA><b>"+suiteName+"<b></td></tr> <tr bgcolor=#87CEFA><th align=left><b>Total Test Cases</b></th><th><center><b>Passed</b></center></th>   <th><center><b>Failed</b></center></th> <th><center><b>Skipped</b></center></th>"
							+ " <th><center><b>Start time</b></center></th><th><center><b>End Time</b></center></th><th><center><b>Total Time(Secs)</b></center></th></h1></tr> <tr bgcolor=#E6E6FA> <td><center><b>"+totTcs+"</b></center></td><td><center><b>"+passed+"</b></center></td> <td><center><b>"+failed+"</b></center></td><td><center><b>"+skipped+"</b></center>"
							+ "</th><td><center><b>"+start.getHours()+":"+start.getMinutes()+":"+start.getSeconds()+"</b></center></td><td><center><b>"+end.getHours()+":"+end.getMinutes()+":"+end.getSeconds()+"</b></center></td><td><center><b>"
							+totalTime+"</b></center></td> </tr></table><br><br><br><table id='t01'><tr> <th><center><b>Test Case Name</b></center></th> <th><center><b>Status</b></center></th><th><center><b>Time(Secs)</b></center></th> </tr>";
					bufferedWriter.write(htmlHead);
					for (ISuite theSuite : suites) {
				        Map<String, ISuiteResult> testsWithResult = theSuite.getResults();
						  for (ISuiteResult result : testsWithResult.values()) {
					          ITestContext testContext = result.getTestContext();
				            Set<ITestResult> failedResult=testContext.getFailedTests().getAllResults();
				            String status=null;
				            for(ITestResult it:failedResult){
				            	if(it.getStatus()==2){
				            		status="Failed";
				            	}
				            	long tottime=((it.getEndMillis()-it.getStartMillis())/1000);
				            	  bufferedWriter.append("<tr> <td>"+  it.getName()+"</td> <td><center><font color=red><b>"+status+"</b></font></center></td><td><center>"+tottime+"</center></td> </tr>");
				            	
				            }
				            Set<ITestResult> passedResult=testContext.getPassedTests().getAllResults();
				            
				            for(ITestResult it:passedResult){
				              	if(it.getStatus()==1){
				            		status="Passed";
				            	}
				              	long tottime=((it.getEndMillis()-it.getStartMillis())/1000);
				            	  bufferedWriter.append("<tr> <td>"+  it.getName()+"</td> <td><center><font color=green><b>"+status+"</b></font></center></td><td><center>"+tottime+"</center></td> </tr>");
				            	
				            }
				           
				            Set<ITestResult> skippedResult=testContext.getSkippedTests().getAllResults();
				            
				            for(ITestResult it:skippedResult){
				            	if(it.getStatus()==3){
				            		status="Skipped";
				            	}
				            	 long tottime=((it.getEndMillis()-it.getStartMillis())/1000);
				            	
				            	  bufferedWriter.append("<tr> <td>"+  it.getName()+"</td> <td><center><font color=black><b>"+status+"</b></font></center></td><td><center>"+tottime+"</center></td> </tr>");
				            }

						}
					}

				}
			}
			
			bufferedWriter.flush();
			fileWriter.flush();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {

				bufferedWriter.close();
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}