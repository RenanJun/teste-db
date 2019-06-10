package org.br.curso.steps;

import org.br.curso.actions.DBActions;
import org.testng.Assert;

import com.br.inmetrics.frm.bdd.DesignSteps;
import com.br.inmetrics.frm.bdd.Step;

@DesignSteps
public class DBSteps {
	
	DBActions dbActions = DBActions.createOrInstance();
	private static DBActions.CustomerTO tempCustomer = null;
	private static int rowsModified = 0;

	@Step("Dado que fa�a a inser��o de um novo cliente")
	public void dado_que_faca_a_insercao_de_um_novo_cliente() {
		
		try {
			if(!dbActions.isConnected()) dbActions.initializeConnecion();
			dbActions.insertCustomer("Avon", "John", "Brazil Av.", "S�o Paulo", "SP");
		} catch (Exception e) {
			Assert.fail("Erro na inser��o do cliente", e);
		}
		
	}
	
	@Step("Quando fizer a busca")
	public void quando_fizer_a_busca() {
		
		try {
			if(dbActions.lastIdCreated() >= 0)
				tempCustomer = dbActions.getCustomer(dbActions.lastIdCreated());
		} catch (Exception e) {
			Assert.fail("Erro na busca do cliente", e);
		}
		
	}
	
	@Step("Ent�o dever� retornar o cliente inserido")
	public void entao_devera_retornar_o_cliente_inserido() {
	
		try {
			Assert.assertTrue(dbActions.lastIdCreated() > 0);
			Assert.assertNotNull(tempCustomer);
			Assert.assertEquals(tempCustomer.companyName, "Avon");
		} catch (Exception e) {
			Assert.fail("Erro na valida��o do retorno de um cliente inserido", e);
		}
	}
	
	@Step("Dado que fa�a a altera��o de um cliente existente")
	public void dado_que_faca_a_alteracao_de_um_cliente_existente() {
		
		try {
			if(!dbActions.isConnected()) dbActions.initializeConnecion();
			rowsModified = dbActions.updateCustomer(dbActions.lastIdCreated(), "Natura", "John", "Brazil Av.", "S�o Paulo", "SP");
		} catch(Exception e) {
			Assert.fail("Erro na altera��o de um cliente existente", e);
		}
		
	}
	
	@Step("Ent�o dever� retornar o cliente alterado")
	public void entao_devera_retornar_o_cliente_alterado() {
		
		try {
			Assert.assertEquals(rowsModified, 1);
			Assert.assertNotNull(tempCustomer);
			Assert.assertEquals(tempCustomer.companyName, "Natura");
		} catch(Exception e) {
			Assert.fail("Erro na valida��o do retorno de um cliente alterado", e);
		}
		
	}
	
	@Step("Dado que fa�a a exclus�o de um cliente existente")
	public void dado_que_faca_a_exclusao_de_um_cliente_existente() {
		
		try {
			if(!dbActions.isConnected()) dbActions.initializeConnecion();
			rowsModified = dbActions.deleteCustomer(dbActions.lastIdCreated());
		} catch(Exception e) {
			Assert.fail("Erro na exclus�o de um cliente", e);
		}

	}
	
	@Step("Ent�o dever� retornar 0 registros")
	public void entao_devera_retornar_nenhum_registro() {
	
		try {
			Assert.assertEquals(rowsModified, 1);
			Assert.assertNull(tempCustomer);
		} catch(Exception e) {
			Assert.fail("Erro na valida��o do retorno de um cliente exclu�do", e);
		}
		
	}
}