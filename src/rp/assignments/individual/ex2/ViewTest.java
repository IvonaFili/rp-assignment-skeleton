package rp.assignments.individual.ex2;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import rp.robotics.testing.RobotTest;
import rp.robotics.testing.TestViewer;

public class ViewTest {

	/**
	 * Example of how to visualise a test running
	 * 
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unused")
	private static void visualiseTest() throws ClassNotFoundException {
		Ex2Tests tests = new Ex2Tests();
		RobotTest<?> test = tests.createSlowTest();
		TestViewer demo = new TestViewer(test, test.getSimulation());
		demo.run();
	}

	public static void main(String[] args) throws ClassNotFoundException {
		// Visualise an IA2 test. For further examples, reuse code from
		// ex1.ViewTest
		//visualiseTest();
		Result result = JUnitCore
                .runClasses(rp.assignments.individual.ex2.Ex2Tests.class);

        System.out.println(String.format("%d/%d tests successful",
                result.getRunCount() - result.getFailureCount(),
                result.getRunCount()));
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
	}

}
