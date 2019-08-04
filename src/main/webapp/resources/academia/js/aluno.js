var aluno = (function() {

	var controller = {
		resetaMascarasDadosEndereco : function() {
			$('.cep').inputmask('99999-999');
		},
		resetaMascarasDadosTelefone : function() {
			$('.telefone').inputmask('9999[9]-9999');
			$('.ddd').inputmask('99');
		}
	};

	return {
		controller : controller
	}

})();