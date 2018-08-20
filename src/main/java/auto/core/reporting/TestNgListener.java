package auto.core.reporting;

import auto.core.utils.LocationConstant;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import java.io.File;
import java.io.IOException;

import static auto.core.driver.DriverFactory.getThreadDriver;
import static auto.core.reporting.Logger.*;


public class TestNgListener extends TestListenerAdapter implements IInvokedMethodListener, ISuiteListener {

    private static final String PREFIX_METHOD_STARTED = "METHOD STARTED";
    private static final String PREFIX_METHOD_SUCCESS = "METHOD SUCCESS";
    private static final String PREFIX_METHOD_FAILED = "METHOD FAILED";
    private static final String PREFIX_METHOD_SKIPPED = "METHOD SKIPPED";
    private static final String PREFIX_CONFIGURATION_STARTED = "CONFIGURATION STARTED";
    private static final String PREFIX_CONFIGURATION_SUCCESS = "CONFIGURATION SUCCESS";
    private static final String PREFIX_CONFIGURATION_FAILED = "CONFIGURATION FAILED";
    private static final String PREFIX_CONFIGURATION_SKIPPED = "CONFIGURATION SKIPPED";
    private static final String PREFIX_TEST_STARTED = "TEST STARTED";
    private static final String PREFIX_TEST_FINISHED = "TEST FINISHED";
    private static final String PREFIX_TEST_SUITE_STARTED = "TEST SUITE STARTED";
    private static final String PREFIX_TEST_SUITE_FINISHED = "TEST SUITE FINISHED";

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        trace("Before invocation does not require any actions");
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        int result = testResult.getStatus();

        if (result == ITestResult.FAILURE) {
            processFailure(testResult);
        }
    }

    @Override
    public void onStart(ISuite suite) {
        info(buildMessage(PREFIX_TEST_SUITE_STARTED, suite.getName()));
        trace("On Start event does not require any actions");
    }

    @Override
    public void onFinish(ISuite suite) {
        info(buildMessage(PREFIX_TEST_SUITE_FINISHED, suite.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult testResult) {
        info(buildMessage(PREFIX_METHOD_SUCCESS, getMethodName(testResult)));
        processTestResult(testResult);
    }

    @Override
    public void onTestFailure(ITestResult testResult) {
        error(buildMessage(PREFIX_METHOD_FAILED, getMethodName(testResult)));
    }

    @Override
    public void onTestSkipped(ITestResult testResult) {
        warn(buildMessage(PREFIX_METHOD_SKIPPED, getMethodName(testResult)));
    }

    @Override
    public void onStart(ITestContext testContext) {
        info(buildMessage(PREFIX_TEST_STARTED, testContext.getName()));
    }

    @Override
    public void onFinish(ITestContext testContext) {
        info(buildMessage(PREFIX_TEST_FINISHED, testContext.getName()));
    }

    @Override
    public void onTestStart(ITestResult result) {
        info(buildMessage(PREFIX_METHOD_STARTED, getMethodName(result)));
        logStartMessage(result);
    }

    @Override
    public void onConfigurationFailure(ITestResult testResult) {
        error(buildMessage(PREFIX_CONFIGURATION_FAILED, getMethodName(testResult)));
    }

    @Override
    public void beforeConfiguration(ITestResult testResult) {
        info(buildMessage(PREFIX_CONFIGURATION_STARTED, getMethodName(testResult)));
    }

    @Override
    public void onConfigurationSkip(ITestResult testResult) {
        info(buildMessage(PREFIX_CONFIGURATION_SKIPPED, getMethodName(testResult)));
    }

    @Override
    public void onConfigurationSuccess(ITestResult testResult) {
        info(buildMessage(PREFIX_CONFIGURATION_SUCCESS, getMethodName(testResult)));
    }

    private void logStartMessage(ITestResult result) {
        if (result.getMethod().getDescription() != null) {
            StringBuilder message = new StringBuilder(result.getMethod().getDescription());
            for (Object parameter : result.getParameters()) {
                if (parameter instanceof String) {
                    message.append(" ").append(parameter.toString());
                } else {
                    message.append(" [").append(parameter.toString()).append("]");
                }
            }
            info(message.toString());
        } else {
            warn("Test [{}] have no description", result.getName());
        }
    }

    private static String buildMessage(String prefix, String msg) {
        return prefix + " - " + msg;
    }

    private void processFailure(ITestResult result) {
        processTestResult(result);
        debug("Trying to save screenshot ...");
        File screenshot = new File(createScreenshot());
        info("New screenshot saved: {}", screenshot.getName());
        Reporter.log("<img src='.." + LocationConstant.SUBFOLDER_SCREENSHOTS.getPath() + "/" + screenshot.getName() + "' />");
        info("Error logged");
    }

    private void processTestResult(ITestResult result) {
        if (result.getThrowable() != null) {
            error("Following error thrown while test execution: ", result.getThrowable());
        }
    }

    private String getMethodName(ITestResult result) {
        StringBuilder builder = new StringBuilder();
        builder.append(result.getTestClass().getRealClass().getSimpleName());
        builder.append(".");
        builder.append(result.getMethod().getMethodName());
        String description = result.getMethod().getDescription();

        if (null != description && !description.equals("")) {
            builder.append(" - ");
            builder.append(description);
        }

        return builder.toString();
    }

    private String createScreenshot() {
        String screenshotsStorage = LocationConstant.SUBFOLDER_SCREENSHOTS.getPath();
        File screenshot = ((TakesScreenshot) getThreadDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFileToDirectory(screenshot, new File(screenshotsStorage));
        } catch (IOException e) {
            error(e.getMessage());
        }
        return screenshot.getAbsolutePath();
    }


}