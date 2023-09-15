package sistemabancario;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Classe de teste criada para garantir o funcionamento das principais operações
 * sobre contas, realizadas pela classe {@link GerenciadoraCientes}
 * 
 * @author Rodrigo Barreto
 * @date 25/08/2023
 */



public class GerenciadoraContasTeste2 {
	
	private GerenciadoraContas gerContas;
	
	private int idConta01 = 1;
	private int idConta02 = 2;
	
	private ContaCorrente conta01;
	private ContaCorrente conta02;
	
	
	@Before
	public void setUp() {
		/*=====Montagem do cenário de teste=====*/
		conta01 = new ContaCorrente(1, 0, true);
		conta02 = new ContaCorrente(2, 0, true);
		
		List<ContaCorrente> contasDoBanco = new ArrayList<ContaCorrente>();
		contasDoBanco.add(conta01);
		contasDoBanco.add(conta02);
		
		gerContas = new GerenciadoraContas(contasDoBanco);
	}
	
	@After
	public void tearDown() {
		/** desmontagem do cnário global **/
		gerContas.limpa();
	}
	
	
	/**
	 * Função para fazer o teste básico de transferência bancaria de um valor e uma conta
	 * de origem para uma conta de destino
	 * 
	 * @author Rodrigo Barreto
	 * @date 25/08/2023
	 */
	@Test
	public void testeTransfereValor() {
		
		/*====Execução da regra de negócio a ser testada====*/
		conta01.setSaldo(200.0);
		conta02.setSaldo(0);
		boolean  resultadoTransferancia = gerContas.transfereValor(1, 50, 2);
		
		/*====Execução dos testes pelo JUnit para Análises e Verificações====*/
		assertTrue(resultadoTransferancia);
		assertThat(conta01.getSaldo(), is(150.0));
		assertThat(conta02.getSaldo(), is(50.0));
	}
	
	
	@Test
	public void testeTransfereValorInsuficiente1() {

		/*====Execução da regra de negócio a ser testada====*/
		conta01.setSaldo(100.0);
		conta02.setSaldo(0);
		boolean  resultadoTransferancia = gerContas.transfereValor(1, 200, 2);
		
		/*====Execução dos testes pelo JUnit para Análises e Verificações====*/
		assertTrue(resultadoTransferancia);
		assertThat(conta01.getSaldo(), is(-100.0));
		assertThat(conta02.getSaldo(), is(200.0));
		
		
	}

	@Test
	public void testeTransfereValorInsuficiente2() {
		/*====Execução da regra de negócio a ser testada====*/
		conta01.setSaldo(-100.0);
		conta02.setSaldo(0);
		boolean  resultadoTransferancia = gerContas.transfereValor(1, 200, 2);
		
		/*====Execução dos testes pelo JUnit para Análises e Verificações====*/
		assertTrue(resultadoTransferancia);
		assertThat(conta01.getSaldo(), is(-300.0));
		assertThat(conta02.getSaldo(), is(200.0));
		
		
	}
	
	@Test
	public void testeTransfereValorInsuficiente3() {
		/*====Execução da regra de negócio a ser testada====*/
		conta01.setSaldo(-100.0);
		conta02.setSaldo(-100);
		boolean  resultadoTransferancia = gerContas.transfereValor(1, 200, 2);
		
		/*====Execução dos testes pelo JUnit para Análises e Verificações====*/
		assertTrue(resultadoTransferancia);
		assertThat(conta01.getSaldo(), is(-300.0));
		assertThat(conta02.getSaldo(), is(100.0));
		
		
	}
	
	
	
}
