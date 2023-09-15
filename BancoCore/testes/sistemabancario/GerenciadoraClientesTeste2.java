package sistemabancario;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste criada para garantir o funcionamento das principais operações
 * sobre clientes, realizadas pela classe {@link GerenciadoraCientes}
 * 
 * @author Rodrigo Barreto
 * @date 18/08/2023
 */

public class GerenciadoraClientesTeste2 {
	
	private GerenciadoraClientes gerClientes;
	private int idCliente01 = 1;
	private int idCliente02 = 2;
	
	
	@Before   //anotação que roda a montagem do cenário antes de chamar too método com @Test
	public void setUp() {
		/*====Montagem do cenário de teste====*/
		Cliente cliente01 = new Cliente(idCliente01, "João da Silva", 20, "joaodasilva@gmail.com", 1, true);
		Cliente cliente02 = new Cliente(idCliente02, "Maria da Silva", 18, "mariadasilva@gmail.com", 1, true);
		
		List<Cliente> clientesDoBanco = new ArrayList<Cliente>();
		clientesDoBanco.add(cliente01);
		clientesDoBanco.add(cliente02);
		
		gerClientes = new GerenciadoraClientes(clientesDoBanco);
	}
	
	@After
	public void tearDown() {
		//*****Desmontagem do cenário global*****//
		gerClientes.limpa();
	}
	
	
	
	
	/**
	 * Teste básico da pesquisa de um cliente a partir do seu ID
	 * 
	 * @author Rodrigo Barreto
	 * @date 18/08/2023
	 */
	@Test
	public void testPesquisaCliente(){
		/*=========Montagem do cenário é chamada automaticamente no @Before=========*/
		
		/*====Execução do Teste=====*/
		Cliente cliente = gerClientes.pesquisaCliente(idCliente01);
		
		/*====Verificação e Avaliação do Teste====*/
		assertThat(cliente.getId(), is(idCliente01));
	}
	
	
	
	/**
	 * Teste básico da remoção de um cliente  partit do ID
	 * 
	 * @author Rodrigo Barreto
	 * @date 25/08/2023
	 */
	@Test
	public void testRemoveCliente() {
		/*=========Montagem do cenário é chamada automaticamente no @Before=========*/
		
		/*=========Execução da regra de negócio a ser testada===========*/
		boolean resultadoRemocaoCliente = gerClientes.removeCliente(idCliente02);
		
		/*=========Execução dos testes pelo JUnit para Análises e Verificações=========*/
		assertThat(resultadoRemocaoCliente, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(idCliente01));
		assertNull(gerClientes.pesquisaCliente(idCliente02));	
	}
	
	
	@Test
	public void testRemoveClienteInexistente() {
		/*=========Montagem do cenário é chamada automaticamente no @Before=========*/
		
		/*=========Execução=========*/
		boolean resultadoRemocaoCliente = gerClientes.removeCliente(10);
		
		/*=========Execução dos testes pelo JUnit para Análises e Verificações=========*/
		assertThat(resultadoRemocaoCliente, is(false));
		assertFalse(resultadoRemocaoCliente);
		assertThat(gerClientes.getClientesDoBanco().size(), is(2));
	}
	
	
	@Test
	public void testPesquisaClienteInexistente() {
		/*=========Montagem do cenário é chamada automaticamente no @Before=========*/
		
		/*=========Execução do Teste=========*/
		Cliente cliente = gerClientes.pesquisaCliente(13);
		
		
		/*====Verificação e Avaliação do Teste====*/
		assertNull(cliente);
	}
	
	
}














