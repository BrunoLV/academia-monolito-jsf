$(document).ready(function() {

	$('[data-toggle="tooltip"]').tooltip();

	$('.documento').inputmask('999.999.999-99');

	$('.cep').inputmask('99999-999');
	
	// Date picker
	$('.calendario').datepicker({
		format : 'dd/mm/yyyy',
		language : 'pt-BR',
		autoclose : true
	});
	
	$('.calendario').inputmask({
		alias: 'date',
		format: 'dd/mm/yyyy'
	});
	
	$('.decimal').inputmask({
		alias: 'decimal',
		enforceDigitsOnBlur: true,
		positionCaretOnClick: 'none',
		digits: 2,
		digitsOptional: false,
		radixPoint: ',',
		allowMinus: false,
		placeholder: '0'
	});

});