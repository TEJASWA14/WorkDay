package reporting;

import java.util.List;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.xml.XmlSuite;

public class CustomReporting implements IReporter {

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outptDir) {
		
		HtmlGenerator htmlGenrator=new HtmlGenerator();
		htmlGenrator.generateReport(suites);
	}
}
