/*
Sempre 	que for usar Ajax importar o jquery no html
append('<tr><td>'+response[i].id+'</td><td>'+response[i].nome+'</td><td><button type="button" onclick="colocarEmEdicao('+response[i].id+')" class="btn btn-outline-dark">Selecionar</button></td></tr>');
*/
function pesquisarFornecedor() {
	var nome = $('#nameBuscar').val();
	if (nome != null && nome.trim() != '') {
		$.ajax({
			method: "GET",
			url: "produto/buscarPorNomeFornecedor/",
			data: "name=" + nome,
			success: function(response) {
				$('#tabelaresultados > tbody > tr').remove();
				for (var i = 0; i < response.length; i++) {
					$('#tabelaresultados > tbody').append('<tr><td>' + response[i].fornecedorId + '</td><td>' + response[i].nome + '</td><td><button type="button" onClick="carregarFornecedor(' + response[i].fornecedorId + ')" class="btn btn-primary">Selecionar</button></td></tr>');
				}
			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao buscar fornecedor: " + xhr.responseText);
		});
	}
	else {
		alert("Por favor insira um fornecedor!!!");
	}
}
function pesquisarCliente() {
	var nomeCliente = $('#nomeCliente').val();
	if (nomeCliente != null && nomeCliente.trim() != '') {
		$.ajax({
			method: "GET",
			url: "pedido/buscarPorNomeCliente/",
			data: "name=" + nomeCliente,
			success: function(response) {
				$('#tabResultadosCliente > tbody > tr').remove();
				for (var i = 0; i < response.length; i++) {
					$('#tabResultadosCliente > tbody').append('<tr><td>' + response[i].clienteId + '</td><td>' + response[i].nome + '</td><td><button type="button" onClick="carregarCliente(' + response[i].clienteId + ')" class="btn btn-primary">Selecionar</button></td></tr>');
				}
			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao buscar cliente: " + xhr.responseText);
		});
	}
	else {
		alert("Por favor insira o nome do cliente!!!");
	}
}
function pesquisarProduto() {
	var descricao = $('#descricaoProduto').val();
	if (descricao != null && descricao.trim() != '') {
		$.ajax({
			method: "GET",
			url: "pedido/buscarProduto/",
			data: "descricao=" + descricao,
			success: function(response) {
				$('#tabDescricaoProduto > tbody > tr').remove();
				for (var i = 0; i < response.length; i++) {
					$('#tabDescricaoProduto > tbody').append('<tr><td>' + response[i]
					.produtoId + '</td><td>' + response[i]
					.descricao + 
				    '</td><td><button type="button" onClick="carregarProduto(' + response[i].produtoId + ')" class="btn btn-primary">Selecionar</button></td></tr>'
					//'</td><td><button type="button" onClick="psq()" class="btn btn-primary">Selecionar</button></td></tr>'
					);
				}
			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao buscar o produto1!!!: " + xhr.responseText);
		});
	}
	else {
		alert("Por favor insira um o nome do produto!!!");
	}
}

function carregarFornecedor(fornecedorId) {
	$.ajax({
		method: "GET",
		url: "produto/buscarPorIdFornecedor/",
		data: "fornecedorId=" + fornecedorId,
		success: function(response) {
			$("#idFor").val(response.fornecedorId);
			$("#nomeFor").val(response.nome);

			$("#pesquisarModal").modal('hide');
			//$("#pesquisarModal").hide('close');
			$(".modal-backdrop").css("display", "none");

		}
	}).fail(function(xhr, status, errorThrow) {
		alert("Erro ao buscar fornecedor: " + xhr.responseText);
	});
}
function carregarCliente(clienteId) {
	$.ajax({
		method: "GET",
		url: "pedido/buscarPorIdCliente/",
		data: "clienteId=" + clienteId,
		success: function(response) {
			$("#idClie").val(response.clienteId);
			$("#nomeClie").val(response.nome);

			$("#pesquisarClienteModal").modal('hide');
			//$("#pesquisarModal").hide('close');
			$(".modal-backdrop").css("display", "none");

		}
	}).fail(function(xhr, status, errorThrow) {
		alert("Erro ao buscar fornecedor: " + xhr.responseText);
	});
}
function carregarProduto(produtoId) {
	
	//let tbody = document.getElementById('tbody');
	
	$.ajax({
		method: "GET",
		url: "pedido/buscarProdutoId/",
		data: "produtoId=" + produtoId,
		success: function(response) {
				/*
					let tr = tbody.insertRow();
					
					let td_produtoId = tr.insertCell();
					let td_descricao = tr.insertCell();
					let td_marca = tr.insertCell();
					let td_preco = tr.insertCell();
					let td_estoqueQtd = tr.insertCell();
					let td_remover = tr.insertCell();
					
					td_produtoId.innerText = response.produtoId;
					td_descricao.innerText = response.descricao;
					td_marca.innerText = response.marca;
					td_preco.innerText = response.valorSaida;
					td_estoqueQtd.innerText = response.estoqueQtd;
					td_remover.innerHtml = 
				*/
				
				$('#listaPedido > tbody > tr').remove();
				
				for (var i = 0; i < response.length; i++) {
					
					$('#listaPedido > tbody').append(
						'<tr><td>' + response[i].produtoId + '</td><td>' + response[i].descricao + '</td><td>' + response[i].marca + 
						'</td><td>' + response[i].preco +
						'</td><td><input type="number" id="qtd"/></td><td><input type="text" id="subTotal"/></td><td><button type="button" class="btn btn-primary">Remover</button></td></tr>'
					);
					
				}
				
			$("#pesquisarProdutoModal").modal('hide');
			//$("#pesquisarModal").hide('close');
			//$(".modal-backdrop").css("display","none");
		}
	}).fail(function(xhr, status, errorThrow) {alert("Erro ao buscar fornecedor: " + xhr.responseText);});
}
function limparCampos(){
	
}
function removerItens(){
	
}
 