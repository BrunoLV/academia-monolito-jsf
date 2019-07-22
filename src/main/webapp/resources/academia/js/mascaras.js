$(document).ready(function() {

	$('[data-toggle="tooltip"]').tooltip();

	$('.documento').inputmask('999.999.999-99');

	$('.cep').inputmask('99999-999');

	$('.telefone').inputmask('9999[9]-9999');

	$('.ddd').inputmask('99');

	// Date picker
	$('.calendario').datepicker({
		format : 'dd/mm/yyyy',
		language : 'pt-BR',
		autoclose : true
	});
	$('.calendario').inputmask('99/99/9999');

});