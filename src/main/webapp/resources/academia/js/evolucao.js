var evolucao = (function() {

	var controller = {

		renderizaGraficoEvolucao : function(idAluno) {

			google.charts.load('current', {
				'packages' : [ 'corechart' ]
			});

			google.charts.setOnLoadCallback(drawChart);

			function drawChart() {

				var medidas = [];
				var dadosGrafico = [];

				$.get('http://localhost:8080/academia/services/aluno/'
						+ idAluno + '/medidas', function(data) {
					console.log(data);
					medidas = data;

					dadosGrafico.push([ 'Data Medição'
									  , 'Peso'
									  , 'Pescoço'
									  , 'Tórax Superior'
									  , 'Tórax Inferior'
									  , 'Busto'
									  , 'Cintura'
									  , 'Abdômen'
									  , 'Quadril'
									  , 'Coxa Direita'
									  , 'Coxa Esquerda'
									  , 'Panturrilha Direita'
									  , 'Panturrilha Esquerda'
									  , 'Braço Direito'
									  , 'Braço Esquerdo'
									  , 'Antebraço Direito'
									  , 'Antebraço Esquerdo' ]);

					var i;
					for (i = 0; i < medidas.length; i++) {
						dadosGrafico.push([ medidas[i].dataMedicao
							, medidas[i].peso
							, medidas[i].pescoco
							, medidas[i].toraxSuperior
							, medidas[i].toraxInferior
							, medidas[i].busto
							, medidas[i].cintura
							, medidas[i].abdomen
							, medidas[i].quadril
							, medidas[i].coxaDireita
							, medidas[i].coxaEsquerda
							, medidas[i].panturrilhaDireita
							, medidas[i].panturrilhaEsquerda
							, medidas[i].bracoDireito
							, medidas[i].bracoEsquerdo
							, medidas[i].antebracoDireito
							, medidas[i].antebracoEsquerdo ]);
					};

					var data = google.visualization
							.arrayToDataTable(dadosGrafico);

					var options = {
						title : 'Evolução Corporal',
						legend : {
							position : 'right'
						}
					};

					var chart = new google.visualization.LineChart(document
							.getElementById('curve_chart'));
					chart.draw(data, options);

				});
			}
			;
		}
	};

	return {
		controller : controller
	}

})();