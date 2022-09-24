function openModal(){
	let modal = document.getElementById("modal");
	if(typeof modal == 'undefined' || modal === null)
		return;
		
		let modalNova = new bootstrap.Modal(modal);
		modalNova.show();
}
/*
Sempre 	ue for usar Ajax importar o jquery no html
append('<tr><td>'+response[i].id+'</td><td>'+response[i].nome+'</td><td><button type="button" onclick="colocarEmEdicao('+response[i].id+')" class="btn btn-outline-dark">Selecionar</button></td></tr>');
*/
function pesquisarUser(){
		
		let nome = document.getElementById('nameBuscar').value;
		if(nome != null && nome.trim()!=''){
		
			$.ajax({
				method : "GET",
				url : "produto/buscarPorNome/",
				data : "name=" + nome,
				sucess : function(response){
				$('#tabelaresultados > tbody > tr').remove();
				for(let i=0;i<response.length; i++){
					$('#tabelaresultados > tbody').append('<tr><td>'+response[i].id+'</td></tr>');
				}
			}
			}).fail(function(xhr,status,errorThrow){
				alert("Erro ao buscar fornecedor: " + xhr.responseText);
			});
		}
	}
	function colocarEmEdicao(id){
		$.ajax({
			method : "GET",
			url : "buscarPorId",
			data : "idUser" + id,
			success : function(response){
				$("#id").val(response.id);
				$("#nome").val(response.nome);
				
				$("#pesquisarModal").modal('hide');
			}
		}).fail(function(xhr,status,errorThrow){
			alert("Erro ao buscar fornecedor: " + xhr.responseText);
		});
	}