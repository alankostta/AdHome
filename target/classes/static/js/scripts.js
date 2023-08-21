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
					$('#tabelaresultados > tbody').append('<tr><td>' + response[i].id + '</td><td>' + response[i].nome + '</td><td><button type="button" onClick="carregarFornecedor(' + response[i].id + ')" class="btn btn-primary">Selecionar</button></td></tr>');
				}
			}
		}).fail(function(xhr, status, errorThrown) {
			console.log(status, errorThrown)
			alert("Erro ao buscar fornecedor: " + xhr.responseText, status, errorThrown);
		});
	}
	else {
		alert("Por favor insira um fornecedor!!!");
	}
}
function pesquisarCliente() {
	
	let nomeCliente = $('#nomeCliente').val();
	alert(nomeCliente)
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
			alert("Erro ao buscar cliente: " + xhr.responseText,status, errorThrown);
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
	}
	else {
		alert("Por favor insira um o nome do produto!!!");
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
		alert("Erro ao buscar fornecedor: " + xhr.responseText, status,  errorThrow);
	});
}
function carregarCliente(id) {
	$.ajax({
		method: "GET",
		url: "/pedido/buscarPorIdCliente",
		data: "id=" + id,
		success: function(response) {

			for (var i = 0; i < response.length; i++) {
				var id = response[i][0];
				var nome = response[i][1];
				var bairro = response[i][2];
				var uf = response[i][3];
				var localidade = response[i][4];
				var enderecoId = response[i][5];
				var complemento = response[i][6];
				var numero = response[i][7];
				var cep = response[i][8];
				var logradouro = response[i][9];
			}

			$("#idClie").val(id);
			$("#nomeClie").val(nome);
			$("#pedidoUf").val(uf);
			$("#pedidoCidade").val(localidade);
			$("#pedidoBairro").val(bairro);
			$("#pedidoLogradouro").val(logradouro);
			$("#pedidoNumero").val(numero);
			$("#pedidoCep").val(cep);
			$("#pedidoComplemento").val(complemento);
			$("#codigoEndereco").val(enderecoId);

			$("#pesquisarClienteModal").modal('hide');
			//$("#pesquisarModal").hide('close');
			$(".modal-backdrop").css("display", "none");

		}
	}).fail(function(xhr, status, errorThrow) {
		alert("Erro ao buscar fornecedor: " + xhr.responseText, status, errorThrow);
	});
}
/*
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
					'</td><td><input type="number" min="0" class="form-control" id="qtd"/></td><td><input type="number" id="subTotal" min="0" class="form-control" style="background-color: #DCDCDC" readonly="readonly"/></td><td><button class="btn btn-danger remove-btn">Remover</button></td></tr>'
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
*/
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
/*
document.addEventListener('DOMContentLoaded', function() {
	listarUser();
});
*/
/*
function listarUser() {
	$.ajax({
		method: "GET",
		url: "/usuarios",
		success: function(response) {
			// criar um elemento option para cada usuário
			
			var options = "";
			
			for (var i = 0; i < response.length; i++){
				options += "<option value='" + response[i].userId + "'>" + response[i].userId + "- " + response[i].nome + "</option>";
			}
			
			
		//	$.each(response, function(index, usuario) {
			//	options += "<option value='" + usuario.id + "'>" + usuario.nome + "</option>";
			//});
			
			// substituir o conteúdo da tag select pelas opções
			var select = document.getElementById("selectUsuarios");
			select.innerHTML = options;
		},
		error: function(error) {
			console.log(error);
		}
	});
}
*/

function carregarProduto(id) {
    $.ajax({
        method: "GET",
        url: "/pedido/buscarProdutoId",
        data: "id=" + id, // Envie os dados como um objeto, não uma string
        success: function(response) {
			for(let i = 0; i < response.length; i++){
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