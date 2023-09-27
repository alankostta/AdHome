/*
Sempre 	que for usar Ajax importar o jquery no html
append('<tr><td>'+response[i].id+'</td><td>'+response[i].nome+'</td><td><button type="button" onclick="colocarEmEdicao('+response[i].id+')" class="btn btn-outline-dark">Selecionar</button></td></tr>');
*/

function pesquisarFornecedor() {
	let nome = $('#nameBuscar').val();
	if (nome != null && nome.trim() != '') {
		$.ajax({
			method: "GET",
			url: "/produto/buscarPorNomeFornecedor",
			data: "name=" + nome,
			success: function(response) {
				
				$('#tabelaresultados > tbody > tr').remove();
				for (let i = 0; i < response.length; i++) {
					$('#tabelaresultados > tbody').append('<tr><td>' + response[i].id + '</td><td>' + response[i].nome + '</td><td>' + response[i].nomeEmpresa + '</td> <td><button type="button" onClick="carregarFornecedor(' + response[i].id + ')" class="btn btn-primary">Selecionar</button></td></tr>');
					alert("nome empresa", response[i].nomeEmpresa)
				}
			}
		}).fail(function(xhr, status, errorThrown) {
			console.log(status, errorThrown)
			alert("Erro ao buscar fornecedor: " + xhr.responseText, status, errorThrown);
		});
	} else {
		$.ajax({
			method: "GET",
			url: "/pedido/listarFornecedores",
			data: "name=" + nome,
			success: function(response) {
				$('#tabelaresultados > tbody > tr').remove();
				for (let i = 0; i < response.length; i++) {
					$('#tabelaresultados > tbody').append('<tr><td>' + response[i].id + '</td><td>' + response[i].nome + '</td><td>' + response[i].nomeEmpresa + '</td> <td><button type="button" onClick="carregarFornecedor(' + response[i].id + ')" class="btn btn-primary">Selecionar</button></td></tr>');				}
			}
		}).fail(function(xhr, status, errorThrown) {
			console.log(status, errorThrown)
			alert("Erro ao buscar fornecedor: " + xhr.responseText, status, errorThrown);
		});
	}
}
function isNumeric(str) {
	return /^\d+$/.test(str);
}
function pesquisarCliente() {

	let nomeCliente = $('#nomeCliente').val();
	if (nomeCliente != null && nomeCliente.trim() != '') {
		$.ajax({
			method: "GET",
			url: "/pedido/buscarPorNomeCliente",
			data: "name=" + nomeCliente,
			success: function(response) {
				$('#tabResultadosCliente > tbody > tr').remove();
				for (var i = 0; i < response.length; i++) {
					$('#tabResultadosCliente > tbody')
						.append('<tr><td>' + response[i].id + '</td><td>' + response[i].nome
							+ '</td><td><button type="button" onClick="carregarCliente('
							+ response[i].id + ')" class="btn btn-primary">Selecionar</button></td></tr>');
				}
			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao buscar cliente: " + xhr.responseText, status, errorThrown);
		});
	} if (isNumeric(nomeCliente)) {
		id = parseInt(nomeCliente);
		alert(id)
		$.ajax({
			method: "GET",
			url: "/pedido/buscarPorIdCliente",
			data: "id=" + id,
			success: function(response) {
				$('#tabResultadosCliente > tbody > tr').remove();

				$('#tabResultadosCliente > tbody')
					.append('<tr><td>' + response.id + '</td><td>' + response.nome
						+ '</td><td><button type="button" onClick="carregarCliente('
						+ response.id + ')" class="btn btn-primary">Selecionar</button></td></tr>');

			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao buscar cliente: " + xhr.responseText, status, errorThrown);
		});
	}
	else {
		$.ajax({
			method: "GET",
			url: "/pedido/buscarTodosClientes",
			data: "name=" + nomeCliente,
			success: function(response) {
				$('#tabResultadosCliente > tbody > tr').remove();
				for (var i = 0; i < response.length; i++) {
					$('#tabResultadosCliente > tbody')
						.append('<tr><td>' + response[i].id + '</td><td>' + response[i].nome
							+ '</td><td><button type="button" onClick="carregarCliente('
							+ response[i].id + ')" class="btn btn-primary">Selecionar</button></td></tr>');
				}
			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao buscar cliente: " + xhr.responseText, status, errorThrown);
		});
	}
}

function pesquisarProduto() {
	
	var descricao = $('#descricaoProduto').val();
	if (descricao != null && descricao.trim() != '') {
		$.ajax({
			method: "GET",
			url: "/pedido/buscarProduto",
			data: "descricao=" + descricao,
			success: function(response) {
				$('#tabDescricaoProduto > tbody > tr').remove();
				for (var i = 0; i < response.length; i++) {
					$('#tabDescricaoProduto > tbody').append('<tr><td>' + response[i]
						.id + '</td><td>' + response[i]
							.descricao +
						'</td><td><button type="button" onClick="carregarProduto(' + response[i].id + ')" class="btn btn-primary">Selecionar</button></td></tr>'
						//'</td><td><button type="button" onClick="psq()" class="btn btn-primary">Selecionar</button></td></tr>'
					);
				}
			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao buscar o produto1!!!: " + xhr.responseText, status, errorThrown);
		});
	}else{
		$.ajax({
			method: "GET",
			url: "/pedido/listarProdutos",
			data: "descricao=" + descricao,
			success: function(response) {
				$('#tabDescricaoProduto > tbody > tr').remove();
				for (var i = 0; i < response.length; i++) {
					$('#tabDescricaoProduto > tbody').append('<tr><td>' + response[i]
						.id + '</td><td>' + response[i]
							.descricao +
						'</td><td><button type="button" onClick="carregarProduto(' + response[i].id + ')" class="btn btn-primary">Selecionar</button></td></tr>'
						//'</td><td><button type="button" onClick="psq()" class="btn btn-primary">Selecionar</button></td></tr>'
					);
				}
			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao buscar o produto1!!!: " + xhr.responseText, status, errorThrown);
		});
	}
}
function pesquiProduto() {
	
	var descricao = $('#descricaoProduto').val();
	if (descricao != null && descricao.trim() != '') {
		$.ajax({
			method: "GET",
			url: "/pedido/buscarProduto",
			data: "descricao=" + descricao,
			success: function(response) {
				$('#tabDescricaoProduto > tbody > tr').remove();
				for (var i = 0; i < response.length; i++) {
					var productId = response[i].id;
					var descricaoProduto = response[i].descricao;

					var buttonHtml = '<button type="button" class="btn btn-primary">Selecionar</button>';
					var $button = $(buttonHtml);
					alert("entrou", response[i].id)
					$button.click(function() {
						carregarProduto(productId);
						addListaItensProduto(productId);
					});

					var rowHtml = '<tr><td>' + productId + '</td><td>' + descricaoProduto + '</td><td></td></tr>';
					var $row = $(rowHtml);
					$row.find('td:last').append($button);

					$('#tabDescricaoProduto > tbody').append($row);
				}
			}
		}).fail(function(xhr, status, errorThrown) {
			alert("Erro ao buscar o produto1!!!: " + xhr.responseText, status, errorThrown);
		});
	}
}
function carregarFornecedor(id) {
	$.ajax({
		method: "GET",
		url: "/produto/buscarPorIdFornecedor",
		data: "id=" + id,
		success: function(response) {
			$("#idFor").val(response.id);
			$("#nomeFor").val(response.nome);

			$("#pesquisarModal").modal('hide');
			//$("#pesquisarModal").hide('close');
			$(".modal-backdrop").css("display", "none");

		}
	}).fail(function(xhr, status, errorThrow) {
		alert("Erro ao buscar fornecedor: " + xhr.responseText, status, errorThrow);
	});
}
function carregarCliente(id) {
    $.ajax({
        method: "GET",
        url: "/pedido/buscarPorIdCliente",
        data: "id=" + id,
        success: function(response) {
		    
		    console.log(response)

            $("#idClie").val(response.id);
            $("#nomeClie").val(response.nome);
            $("#sexo").val(response.sexo);
            $("#dataNasci").val(response.dataNasci);
            $("#anoRef").val(response.anoRef);
            $("#pedidoUf").val(response.endereco[0].uf);
            $("#pedidoCidade").val(response.endereco[0].localidade);
            $("#pedidoBairro").val(response.endereco[0].bairro);
            $("#pedidoLogradouro").val(response.endereco[0].logradouro);
            $("#pedidoNumero").val(response.endereco[0].numero);
            $("#pedidoCep").val(response.endereco[0].cep);
            $("#pedidoComplemento").val(response.endereco[0].complemento);
            $("#codigoEndereco").val(response.endereco[0].id);
            $("#pedidoEndEnum").val(response.endereco[0].enderecoEnum);
           	$("#idContato").val(response.contato.id);
            $("#emailContato").val(response.contato.email);
            $("#telefoneContato").val(response.contato.telefone);
         	$("#enumContato").val(response.contato.contatoEnum);
         

            // Fechar o modal
            $("#pesquisarClienteModal").modal('hide');
        },
        error: function(xhr, status, errorThrown) {
            alert("Erro ao buscar cliente: " + xhr.responseText, status, errorThrown);
        }
    });
}


function addListaItensProduto(id) {
	alert("entrou", id)
	$.ajax({
		method: "GET",
		url: "/pedido/addListaIten",
		data: "id=" + id,
		success: function(response) {
			console.log(response)
		}
	}).fail(function(xhr, status, errorThrow) { alert("Erro ao buscar fornecedor: " + xhr.responseText, status, errorThrow); });
}
function buscarCep() {
	var cep = $('#cep').val();
	if (cep != null && cep.trim() != '') {
		$.ajax({
			method: "GET",
			url: "cep",
			data: "cep=" + cep,
			success: function(response) {
				$("#uf").val(response.uf);
				$("#cep").val(response.cep);
				$("#cidade").val(response.localidade);
				$("#bairro").val(response.bairro);
				$("#logradouro").val(response.logradouro);
			}
		}).fail(function(xhr, status, errorThrow) {
			alert("Erro ao buscar cep informado!: " + xhr.responseText, status, errorThrow);
		});
	}
	else {
		alert("Por favor insira um cep!!!");
	}
}
function limparPedido() {
	$('#listaPedido tbody tr').remove();
	alert("PEDIDO CANCELADO!");
	verificarDisponibilidadeDaPagina();
}

function verificarDisponibilidadeDaPagina() {
	// Verifica se a página está disponível
	$.ajax({
		method: "GET",
		url: "/pedido",
		success: function(data) {
			// Se a página estiver disponível, você pode acessá-la aqui
			window.location.href = "/pedido";
		},
		error: function() {
			// Se a página não estiver disponível, você pode exibir uma mensagem de erro ou redirecionar para outra página
			alert("A página não está disponível. Redirecionando para a página inicial.");
			window.location.href = "/index.html";
		}
	});
}

function carregarProduto(id) {
	$.ajax({
		method: "GET",
		url: "/pedido/buscarProdutoId",
		data: "id=" + id, // Envie os dados como um objeto, não uma string
		success: function(response) {
			for (let i = 0; i < response.length; i++) {
				var codigo = response[i].id;
				var descricao = response[i].descricao;
				var marca = response[i].marca;
				var preco = response[i].preco;
			}

			var row = $("<tr></tr>");
			row.append($("<td>" + codigo + "</td>"));
			row.append($("<td>" + descricao + "</td>"));
			row.append($("<td>" + marca + "</td>"));
			row.append($("<td>" + preco + "</td>"));

			var qtdInput = $("<input>", {
				type: "number",
				min: "0",
				class: "form-control qtd",
			});
			row.append($("<td></td>").append(qtdInput));

			var subTotalInput = $("<input>", {
				type: "number",
				min: "0",
				class: "form-control",
				style: "background-color: #DCDCDC",
				readonly: "readonly",
			});
			row.append($("<td></td>").append(subTotalInput));

			var removeButton = $("<button>", {
				class: "btn btn-danger remove-btn",
				text: "Remover",
			});
			row.append($("<td></td>").append(removeButton));

			$("#listaPedido tbody").append(row);

			$(document).on("click", ".remove-btn", function() {
				$(this).closest("tr").remove();
				calcularTotal();
			});

			$(document).on("change", ".qtd", function() {
				var qtd = parseFloat($(this).val());
				var preco = parseFloat($(this)
					.closest("tr")
					.find("td:nth-child(4)")
					.text());
				var subTotal = qtd * preco || 0;
				$(this)
					.closest("tr")
					.find("td:nth-child(6) input")
					.val(subTotal.toFixed(2)); // Formate para exibir duas casas decimais
				calcularTotal();
			});

			$("#pesquisarProdutoModal").modal("hide");
		},
		error: function(xhr, status, error) {
			alert("Erro ao buscar produto: " + xhr.responseText, status, error);
		}
	});
}
function calcularDescontoPedido() {
	var desconto = parseFloat($('#porcentagem').val());
	var total = parseFloat($('#total').val());
	var novoTotal = total - (total * (desconto / 100));
	$("#total").val(novoTotal);

}
function calcularTotal() {
	var total = 0.0;
	var subTotal = 0.0;
	$('#listaPedido > tbody > tr').each(function() {
		subTotal = parseFloat($(this).find('#subTotal').val());
		total += subTotal;
	});

	$("#total").val(total.toFixed(2));
}
$(document).ready(function() {
	$('#valDebitoInput').inputmask('currency', {
		prefix: 'R$ ', // Prefixo para indicar que é uma moeda
		radixPoint: ',', // Ponto decimal
		groupSeparator: '.', // Separador de milhares
		allowNegative: true, // Permite valores negativos
		digits: 2, // Número de casas decimais
		rightAlign: false, // Alinha à esquerda
		autoUnmask: true // Remove a máscara quando o campo perde o foco
	});
});

