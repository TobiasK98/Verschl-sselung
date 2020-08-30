package pis.hue1;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import pis.CodecTest;


public class MainTest
{

	public static void main(String[] args) 
	{
		Codectest();
		// TODO Auto-generated method stub
	}
		private static void Codectest() 
		{
			Result resultFertig = JUnitCore.runClasses(CodecTest.class);
			for(Failure fehler:resultFertig.getFailures())
			{
				System.out.println(fehler);
			}
		}
	}


