package org.br.curso.features;

import static com.br.inmetrics.frm.bdd.Gherkin.given_;

import java.util.concurrent.ExecutionException;

import com.br.inmetrics.frm.bdd.Feature;
import com.br.inmetrics.frm.bdd.Scenario;

@Feature("DBFeature")
public class DBFeature {

	@SuppressWarnings("static-access")
	@Scenario("Validar a inserção de um cliente na base")
	public void validarInsercaoClienteBase() throws ExecutionException {
		
		given_("Dado que faça a inserção de um novo cliente").
		when_("Quando fizer a busca").
		then_("Então deverá retornar o cliente inserido").
		execute_();
		
	}
	
	@SuppressWarnings("static-access")
	@Scenario("Validar a alteração de um cliente na base")
	public void validarAlteracaoClienteBase() throws ExecutionException {
		
		given_("Dado que faça a alteração de um cliente existente").
		when_("Quando fizer a busca").
		then_("Então deverá retornar o cliente alterado").
		execute_();
		
	}
	
	@SuppressWarnings("static-access")
	@Scenario("Validar a exclusão de um cliente na base")
	public void validarExclusaoClienteBase() throws ExecutionException {
		
		given_("Dado que faça a exclusão de um cliente existente").
		when_("Quando fizer a busca").
		then_("Então deverá retornar 0 registros").
		execute_();
		
	}
	
}