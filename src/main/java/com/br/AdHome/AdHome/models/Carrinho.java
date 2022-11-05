package com.br.AdHome.AdHome.models;

import java.util.ArrayList;

public class Carrinho {
	private ArrayList<Pedido> itens;
	
	public Carrinho() {
		this.itens =  new ArrayList<>();
	}
	public void inserirItem(Pedido itens) {
		this.itens.add(itens);
	}
	public void removerItem(Pedido itens) {
		this.itens.add(itens);
	}
	public void listarPedido() {
		@SuppressWarnings("unused")
		float valorTotal = 0.0f;
		for(Pedido itensCompra : itens) {
			Produto p = (Produto)itensCompra.getProduto();
			int qtd = itensCompra.getQtdItens();
			System.out.print("Nome do Produto: "+p.getDescricao());
			System.out.print("Valor: "+p.getPreco());
			System.out.print("Quantidade: "+ qtd);
			System.out.print("Parcial: "+p.getPreco()*qtd);
			
			valorTotal += p.getPreco()*qtd;
			
		}
		System.out.print(false);
	}
}
