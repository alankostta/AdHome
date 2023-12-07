$(document).ready(function() {
	$('#example').DataTable({
		processing: true,
		serverSide: true,
		responsive: true,
		lengthMenu: [5, 10, 15, 20],
		ajax: {
			url: "/pedido/datatables",
			data: "data"
		},
		columns: [
			{ data: 'id' },
			{ data: 'dataAlteraPedido' },
			{ data: 'anoRef' },
			{ data: 'valorPedido' },
			{ data: 'descontoPedido' },
			{ data: 'observacaoPedido' },
			{ data: 'dataCadastro' },
			{ data: 'enumStatus' },
			{ data: 'enumPagamento' },
			{ data: 'enumCartao' },
			{
				data: 'itens',
				render: function(data, type, full, meta) {
					if (type === 'display') {
						let itemsText = "";
						if (data && Array.isArray(data)) {
							data.forEach(function(item) {
								itemsText += item.produto.nome + "<br>";
							});
						} else {
							itemsText = "<span class='warning'>Itens não carregados</span>";
						}
						return itemsText;
					}
					return data;
				}
			},
            {
				data: 'user',
				render: function(data) {
					return data.nome;
				}
			},
			{
				data: 'cliente',
				render: function(data) {
					return data.nome;
				}
			}
		]
	});
});
