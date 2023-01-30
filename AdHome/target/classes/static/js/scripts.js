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

	$.ajax({
		method: "GET",
		url: "pedido/buscarProdutoId/",
		data: "produtoId=" + produtoId,
		success: function(response) {

			for (var i = 0; i < response.length; i++) {

				$('#listaPedido > tbody').append(
					'<tr><td>' + response[i].produtoId + '</td><td>' + response[i].descricao + '</td><td>' + response[i].marca +
					'</td><td>' + response[i].preco +
					'</td><td><input type="number" id="qtd"/></td><td><input type="number" id="subTotal" readonly="readonly"/></td><td><button class="btn btn-danger remove-btn">Remover</button></td></tr>'
				);

				$(document).on('click', '.remove-btn', function() {
					$(this).closest('tr').remove();
					calcularTotal();
				});

			}

			$(document).on('change', '#qtd', function() {
				var qtd = $(this).val();
				var preco = $(this).closest('tr').find('td:nth-child(4)').text();
				var subTotal = qtd * preco;
				$(this).closest('tr').find('#subTotal').val(subTotal);
				calcularTotal();
			});

			$("#pesquisarProdutoModal").modal('hide');

		}
	}).fail(function(xhr, status, errorThrow) { alert("Erro ao buscar fornecedor: " + xhr.responseText); });
}
function calcularTotal() {
    var total = 0.0;
    $('#listaPedido tr').each(function() {
		var qtd = parseFloat($(this).find('#qtd').val());
	    var preco = $(this).closest('tr').find('td:nth-child(4)').text();
	    var subTotal = qtd * preco;
	    total += subTotal;
	    alert("Qtd "+qtd);
	    alert("preço "+preco);
	    alert("sub-total "+subTotal);
	    alert("total "+total);
    });
        
  $("#total").val(total);
}



