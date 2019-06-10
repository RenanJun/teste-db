package org.br.curso.tests;

import static com.br.inmetrics.frm.bdd.Gherkin.executeScenario_;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.br.inmetrics.frm.base.TestBase;
import com.br.inmetrics.frm.controllers.EmptyController;
import com.br.inmetrics.frm.testng.TestConfig;

@TestConfig(controllerType=EmptyController.class)
public class DBTests extends TestBase {
	
	@Test(priority=1)
	public void testInsercao()  {
		
		try { 
			executeScenario_("DBFeature", "Validar a inserção de um cliente na base");
		} catch(Exception e) {
			Assert.fail("Error on test.", e);
		}
		
	}
	
	@Test(priority=2)
	public void testAtualizacao() {
		
		try { 
			executeScenario_("DBFeature", "Validar a alteração de um cliente na base");
		} catch(Exception e) {
			Assert.fail("Error on test.", e);
		}
		
	}

	@Test(priority=3)
	public void testExclusao() {
		
		try { 
			executeScenario_("DBFeature", "Validar a exclusão de um cliente na base");
		} catch(Exception e) {
			Assert.fail("Error on test.", e);
		}
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public void setup(final Method method, final ITestContext context) {
		super.setup(method, context);
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown(final Method method, final ITestContext context) {
		super.teardown(method, context);
	}
	
}