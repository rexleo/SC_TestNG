package MyTests;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Reporter.log("*******Test : '"+result.getName()+ "' has started*******");
        System.out.println("*******Test : '"+result.getName()+ "' has started*******");
    }

    public void onFinish(ITestResult result) {
        Reporter.log("*******Test : '"+result.getName()+ "' has started*******");
        System.out.println("*******Test : '"+result.getName()+ "' has started*******");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log(result.getTestContext().getSuite().getName());
        System.out.println(result.getName() + "PASSED");
    }


    public void onTestFailure(ITestResult result) {
        Reporter.log(result.getTestContext().getSuite().getName());
        System.out.println(result.getName() + "FAILED");
    }


}

/*
{

    @Override
    public void onTestFailure(ITestResult result) {
        if (result.getThrowable() != null) {
            result.getThrowable().printStackTrace();
        }
        ExtentReport.getTest().log(Status.FAIL ,result.getThrowable().getMessage());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReport.getTest().log(Status.PASS, "Test Passed");
        String paragraph = String.format("%s \n%s \n%s",result.getTestContext().getCurrentXmlTest().getSuite().getName(), result.getMethod().getMethodName(), result.getTestContext().getCurrentXmlTest().getLocalParameters().get("browser"));
        PdfReport.addParagraph(paragraph);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReport.getTest().log(Status.SKIP, "Test Skipped");

    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReport.getReporter().flush();
        PdfReport.closePdf();
    }
}*/
