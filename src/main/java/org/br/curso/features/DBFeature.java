package org.br.curso.features;

import static com.br.inmetrics.frm.bdd.Gherkin.given_;

import java.util.concurrent.ExecutionException;

import com.br.inmetrics.frm.bdd.Feature;
import com.br.inmetrics.frm.bdd.Scenario;

@Feature("DBFeature")
public class DBFeature {

	@SuppressWarnings("static-access")
	@Scenario("Validar a inser��o de um cliente na base")
	public void validarInsercaoClienteBase() throws ExecutionException {
		
		given_("Dado que fa�a a inser��o de um novo cliente").
		when_("Quando fizer a busca").
		then_("Ent�o dever� retornar o cliente inserido").
		execute_();
		
	}
	
	@SuppressWarnings("static-access")
	@Scenario("Validar a altera��o de um cliente na base")
	public void validarAlteracaoClienteBase() throws ExecutionException {
		
		given_("Dado que fa�a a altera��o de um cliente existente").
		when_("Quando fizer a busca").
		then_("Ent�o dever� retornar o cliente alterado").
		execute_();
		
	}
	
	@SuppressWarnings("static-access")
	@Scenario("Validar a exclus�o de um cliente na base")
	public void validarExclusaoClienteBase() throws ExecutionException {
		
		given_("Dado que fa�a a exclus�o de um cliente existente").
		when_("Quando fizer a busca").
		then_("Ent�o dever� retornar 0 registros").
		execute_();
		
	}
	
}