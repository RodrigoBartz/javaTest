package sistemabancario;

import java.util.List;

/**
 * Classe de negocio para realizar operacoes sobre os clientes do banco.
 */
public class GerenciadoraClientes {

	private List<Cliente> clientesDoBanco;

	public GerenciadoraClientes(List<Cliente> clientesDoBanco) {
		this.clientesDoBanco = clientesDoBanco;
	}
	
	/**
	 * Retorna uma lista com todos os clientes do banco.
	 * @return lista com todos os clientes do banco
	 */
	public List<Cliente> getClientesDoBanco() {
		return clientesDoBanco;
	}
	
	/**
	 * Pesquisa por um cliente a partir do seu ID.
	 * @param idCliente id do cliente a ser pesquisado
	 * @return o cliente pesquisado ou null, caso nao seja encontrado
	 */
	public Cliente pesquisaCliente (int idCliente) {

		for (Cliente cliente : clientesDoBanco) {
			if(cliente.getId() == idCliente)
				return cliente;
		}
		return null;
	}
	
	/**
	 * Adiciona um novo cliente a lista de clientes do banco.
	 * @param novoCliente novo cliente a ser adicionado
	 */
	public void adicionaCliente (Cliente novoCliente) {
		clientesDoBanco.add(novoCliente);
	}

	/**
	 * Remove cliente da lista de clientes do banco.
	 * @param idCliente ID do cliente a ser removido 
	 * @return true se o cliente foi removido. False, caso contrario.
	 */
	public boolean removeCliente (int idCliente) {
		boolean clienteRemovido = false;
		
		for (int i = 0; i < clientesDoBanco.size(); i++) {
			Cliente cliente = clientesDoBanco.get(i);
			if(cliente.getId() == idCliente){
				clientesDoBanco.remove(i);
				clienteRemovido = true;
				break;
			}
		}
		
		return clienteRemovido;
	}

	/**
	 * Informa se um determinado cliente esta ativo ou nao.
	 * @param idCliente ID do cliente cujo status sera verificado
	 * @return true se o cliente esta ativo. False, caso contrario. 
	 */
	public boolean clienteAtivo (int idCliente) {
		boolean clienteAtivo = false;
		
		for (int i = 0; i < clientesDoBanco.size(); i++) {
			Cliente cliente = clientesDoBanco.get(i);
			if(cliente.getId() == idCliente)
				if(cliente.isAtivo()){
					clienteAtivo = true;
					break;
				}
		}
		
		return clienteAtivo;
	}

	/**
	 * Limpa a lista de clientes, ou seja, remove todos eles. 
	 */
	public void limpa() {
		this.clientesDoBanco.clear();
	}
	
	/**
	 * Valida se a idade do cliente esta dentro do intervalo permitido (18 - 65).
	 * @param idade a idade do possivel novo cliente
	 */
	public boolean validaIdade(int idade) throws IdadeNaoPermitidaException {
	
		if(idade < 18 || idade > 65)
			throw new IdadeNaoPermitidaException(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA);
		
		return true;
	}
	
}
