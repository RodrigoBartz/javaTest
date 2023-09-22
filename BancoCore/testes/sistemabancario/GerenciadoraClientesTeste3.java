package sistemabancario;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
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

public class GerenciadoraClientesTeste3 {
	
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
	
	
	//validação quando o cliente está no intervalo de idade permitido
	@Test
	public void testClienteIdadePermitida1() throws IdadeNaoPermitidaException {
		//Montagem do cenário
		Cliente cliente = new Cliente(3, "Daniel", 30, "daniel@gmail.com", 3, true);
		
		//Execução
		boolean resultado = gerClientes.validaIdade(cliente.getIdade());
		
		//Verifição
		assertTrue(resultado);
		
	}
	
	//validação quando o cliente está no intervalo de idade permitido
	@Test
	public void testClienteIdadePermitida2() throws IdadeNaoPermitidaException {
		//Montagem do cenário
		Cliente cliente = new Cliente(4, "Pedro", 18, "pedro@gmail.com", 4, true);
		
		//Execução
		boolean resultado = gerClientes.validaIdade(cliente.getIdade());
		
		//Verifição
		assertTrue(resultado);
		
	}
	
	//validação quando o cliente está no intervalo de idade permitido
	@Test
	public void testClienteIdadePermitida3() throws IdadeNaoPermitidaException {
		//Montagem do cenário
		Cliente cliente = new Cliente(5, "Cleyton", 65, "cleyton@gmail.com", 5, true);
		
		//Execução
		boolean resultado = gerClientes.validaIdade(cliente.getIdade());
		
		//Verifição
		assertTrue(resultado);
		
	}
	
	
	//validação quando o cliente está fora do intervalo permitido na borda inferior
	@Test
	public void testClienteIdadePermitida4() throws IdadeNaoPermitidaException {
		//Montagem do cenário
		Cliente cliente = new Cliente(6, "Victor", 17, "victor@gmail.com", 6, true);
		
		//Execução
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}				
	}
	
	//validação quando o cliente está fora do intervalo permitido na borda inferior
	@Test
	public void testClienteIdadePermitida5() throws IdadeNaoPermitidaException {
		//Montagem do cenário
		Cliente cliente = new Cliente(7, "Victor", 17, "victor@gmail.com", 7, true);
		
		//Execução
		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}				
	}
	
	
	
	
}














