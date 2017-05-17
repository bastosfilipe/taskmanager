
function validation() {
	
	//Testar se a descrição está vazia
	var desc = document.getElementsByClassName("desc-js")[0].value;

	if (desc.trim().length === 0) {

		var msg = document.getElementsByClassName("desc-msg-error-js")[0];
		msg.textContent = "Por favor informe a descrição."
						
	} else {
		var btn = document.getElementsByClassName("btn-create-js")[0];
		btn.click();				
	}
}

function openModal() {
	document.getElementsByClassName("desc-js")[0].value = "";
	document.getElementsByClassName("desc-msg-error-js")[0].textContent = "";
	document.getElementsByClassName("btn-modal-new-js")[0].click();
}
