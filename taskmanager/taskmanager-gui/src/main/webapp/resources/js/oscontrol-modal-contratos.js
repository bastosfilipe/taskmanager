var Oscontrol = Oscontrol || {};

Oscontrol.Modal = (function() {
	
	function Modal() {
		this.modal = $('.js-modal-contratos');
		this.inputSearch = $('.js-input-search');
	}
	
	Modal.prototype.carregar = function() {
		this.modal.on('shown.bs.modal', onModalShow.bind(this))
	}
	
	function onModalShow() {
		this.inputSearch.focus();
		
		var height = $(window).height() - 200;
		$(this).find(".modal-body").css("max-height", height);
	}
		
	return Modal;
	
}());

$(function() {
	
	var modal = new Oscontrol.Modal();
//	modal.carregar();
	
});
