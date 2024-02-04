package lista;

public class ListaEncadeada<T> {

	private No<T> inicio;
	private No<T> ultimo;
	private int tamanho = 0;

	private final int NAO_ENCONTRADO = -1;
	
	private final String NAO_EXISTE = "Posiçao nao existe";
	
	private final String LISTA_VAZIA = "Lista esta vazia";

	public void adiciona(T elemento) {
		No<T> celula = new No<T>(elemento);
		if (this.tamanho == 0) {
			this.inicio = celula;
		}else {
			this.ultimo.setProximo(celula);
		}
		this.ultimo = celula;
		this.tamanho++;
	}

	public int getTamanho() {
		return this.tamanho;
	}

	public void limpa() {
		for (No<T> atual = this.inicio; atual != null; ) {

			No<T> proximo = atual.getProximo();
			atual.setElemento(null);
			atual.setProximo(null);
			atual= proximo;
		}
		this.inicio = null;
		this.ultimo = null;
		this.tamanho = 0;
	}

	public void adicionaInicio(T elemento) {


		if (this.tamanho ==0) {
			No<T>novoNo = new No<>(elemento);
			this.inicio = novoNo;
			this.ultimo = novoNo;
		}else {
			No<T>novoNo = new No<>(elemento,this.inicio);
			this.inicio = novoNo;
		}
		this.tamanho++;
	}


	public  void adiciona(int posicao, T elemento) {

		if (posicao < 0 || posicao>this.tamanho) {
			throw new IllegalArgumentException();
		}


		if (posicao ==00) {//esta vazia
			this.adicionaInicio(elemento);
		}else if(posicao == this.tamanho){//adiciona
			this.adiciona(elemento);

		}else {
			No<T>noAnterior = this.buscaNo(posicao);
			No<T>proximoNo =noAnterior.getProximo();
			No<T>novoNo = new No<>(elemento,proximoNo);
			noAnterior.setProximo(novoNo);
			this.tamanho++;
		}
	}


	public T removeInicio() {
		if (this.tamanho == 0) {
			throw new  RuntimeException(LISTA_VAZIA);
		}
		T removido = this.inicio.getElemento();
		this.inicio = this.inicio.getProximo();
		this.tamanho--;

		if (this.tamanho == 0) {
			this.ultimo = null;
		}

		return removido;
	}

	
	private boolean posicaoNaoExiste(int posicao) {
		return !(posicao >= 0 && posicao <= this.tamanho);
	}
	
	
	public T remove(int posicao) {
		
		if (this.posicaoNaoExiste(posicao)) {
			
			throw new IllegalArgumentException(NAO_EXISTE);
		}
		 else if (posicao == 0) {
			return this.removeInicio();
		}else if (posicao == this.tamanho -1) {
			return  this.removeFinal();
		}
		No<T> noAnterior = this.buscaNo(posicao -1);
		No<T> atual = noAnterior.getProximo();
		No<T> proximo = atual.getProximo();
		
		noAnterior.setProximo(proximo);
		atual.setProximo(null);
		this.tamanho--;
		
		
		return atual.getElemento();
	}
	
	
	public T removeFinal() {
		if (this.tamanho == 0) {
			throw new RuntimeException(LISTA_VAZIA);
		}
		if (this.tamanho == 1) {
			return this.removeInicio();
		}
		No<T>penultimoNo = this.buscaNo(this.tamanho - 2);
		T removido = penultimoNo.getProximo().getElemento();
		
		penultimoNo.setProximo(null);
		this.ultimo = penultimoNo;
		this.tamanho--;
		return removido;
	}
	

	private No<T>buscaNo(int posicao){

		if (this.posicaoNaoExiste(posicao)) {
			throw new IllegalArgumentException(NAO_EXISTE);
		}

		No<T> noAtual = this.inicio;

		for (int i = 0; i < posicao; i++) {
			noAtual = noAtual.getProximo();
		}

		return noAtual;
	}


	public T buscaPorPosicao(int posicao) {

		return this.buscaNo(posicao).getElemento();	
	}


	public int busca(T elemento) {

		No<T> noAtual = this.inicio;
		int pos = 0;
		if (noAtual.getElemento().equals(elemento)) {

			return pos;
		}
		pos++;

		while (noAtual != null) {
			noAtual = noAtual.getProximo();
		}

		return  NAO_ENCONTRADO;
	}


	@Override
	public String toString() {
		//[1,2,3,4]

		if (this.tamanho == 0) {
			return "[]";
		}

		StringBuilder builder = new StringBuilder("[");

		No<T> atual = this.inicio;

		for (int i = 0; i <this.tamanho -1; i++) {
			builder.append(atual.getElemento()).append(",");
			atual = atual.getProximo();
		}

		builder.append(atual.getElemento()).append("]");

		//mesmo exemplo com while 
		/*builder.append(atual.getElemento()).append(",");
		while (atual.getProximo() != null) {
			atual = atual.getProximo();
			builder.append(atual.getElemento()).append(",");
		}*/

		return builder.toString();
	}


}
