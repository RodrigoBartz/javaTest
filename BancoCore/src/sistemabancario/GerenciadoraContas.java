package sistemabancario;

import java.util.List;

/**
 * Classe de negocio para realizar operacoes sobre as contas do banco.
 */
public class GerenciadoraContas {

	private List<ContaCorrente> contasDoBanco;

	public GerenciadoraContas(List<ContaCorrente> contasDoBanco) {
		this.contasDoBanco = contasDoBanco;
	}

	/**
	 * Retorna uma lista com todas as contas do banco.
	 * @return lista com todas as contas do banco
	 */
	public List<ContaCorrente> getContasDoBanco() {
		return contasDoBanco;
	}

	/**
	 * Pesquisa por uma conta a partir do seu ID.
	 * @param idConta id da conta a ser pesquisada
	 * @return a conta pesquisada ou null, caso nao seja encontrada
	 */
	public ContaCorrente pesquisaConta (int idConta) {

		for (ContaCorrente contaCorrente : contasDoBanco) {
			if(contaCorrente.getId() == idConta)
				return contaCorrente;
		}
		return null;
	}
	
	/**
	 * Adiciona uma nova conta a lista de contas do banco.
	 * @param novaConta nova conta a ser adicionada
	 */
	public void adicionaConta (ContaCorrente novaConta) {
		this.contasDoBanco.add(novaConta);
	}

	/**
	 * Remove conta da lista de contas do banco.
	 * @param idConta ID da conta a ser removida 
	 * @return true se a conta foi removida. False, caso contrario.
	 */
	public boolean removeConta (int idConta) {
		
		boolean contaRemovida = false;
		
		for (int i = 0; i < contasDoBanco.size(); i++) {
			ContaCorrente conta = contasDoBanco.get(i);
			if(conta.getId() == idConta){
				contasDoBanco.remove(i);
				break;
			}
		}
		
		return contaRemovida;
	}

	/**
	 * Informa se uma determinada conta esta ativa ou nao.
	 * @param idConta ID da conta cujo status sera verificado
	 * @return true se a conta esta ativa. False, caso contrario. 
	 */
	public boolean contaAtiva (int idConta) {
		
		boolean contaAtiva = false;
		
		for (int i = 0; i < contasDoBanco.size(); i++) {
			ContaCorrente conta = contasDoBanco.get(i);
			if(conta.getId() == idConta)
				if(conta.isAtiva()){
					contaAtiva = true;
					break;
				}
		}
		
		return contaAtiva;
	}
	
	/**
	 * Transfere um determinado valor de uma conta Origem para uma conta Destino.
	 * Caso nao haja saldo suficiente, o valor nao sera transferido.
	 * 
	 * @param idContaOrigem conta que tera o valor deduzido
	 * @param valor valor a ser transferido
	 * @param idContaDestino conta que tera o valor acrescido
	 * @return true, se a transferencia foi realizada com sucesso.
	 */
	public boolean transfereValor (int idContaOrigem, double valor, int idContaDestino) {
		
		boolean sucesso = false;
		
		ContaCorrente contaOrigem = pesquisaConta(idContaOrigem);
		ContaCorrente contaDestino = pesquisaConta(idContaDestino);
		
//		if(contaOrigem.getSaldo() >= valor){
			contaDestino.setSaldo(contaDestino.getSaldo() + valor);
			contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
			sucesso = true;
//		}
	
		return sucesso;
	}
	
	public void limpa() {
		this.contasDoBanco.clear();
	}
	
}
