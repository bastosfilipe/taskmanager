var Oscontrol = Oscontrol || {};

Oscontrol.Paginator = (function() {
	
	function Paginator() {
		this.dataTable = $('.js-datatable');
	}
	
	Paginator.prototype.carregar = function() {
		this.dataTable.DataTable({
			responsive : true,					
			"language": {
			    "sEmptyTable": "Nenhum registro encontrado",
			    "sInfo": "Exibindo de _START_ até _END_ de _TOTAL_ registros",
			    "sInfoEmpty": "Exibindo 0 até 0 de 0 registros",
			    "sInfoFiltered": "(Filtrados de _MAX_ registros)",
			    "sInfoPostFix": "",
			    "sInfoThousands": ".",
			    "sLengthMenu": "_MENU_ registros por página",
			    "sLoadingRecords": "Carregando...",
			    "sProcessing": "Processando...",
			    "sZeroRecords": "Nenhum registro encontrado",
			    "sSearch": "Pesquisar ",
			    "oPaginate": {
			        "sNext": "Próximo",
			        "sPrevious": "Anterior",
			        "sFirst": "Primeiro",
			        "sLast": "Último"
			    },
			    "oAria": {
			        "sSortAscending": ": Ordenar colunas de forma ascendente",
			        "sSortDescending": ": Ordenar colunas de forma descendente"
			    }
			}				
		})
	}
		
	return Paginator;
	
}());

$(function() {
	
	var paginator = new Oscontrol.Paginator();
	paginator.carregar();
	
});
