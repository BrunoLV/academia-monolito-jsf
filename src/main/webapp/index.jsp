<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:p="http://primefaces.org/ui">

<h:head>
</h:head>

<h:body>

	<h:form>
		<p:growl />
		<h:outputText value="#{testeController.mensagemHello}" />
		<br />

		<div class="ui-g">
			<div class="ui-g-1"><p:outputPanel value="Nome:" /></div>
			<div class="ui-g-3"><p:inputText value="#{alunoController.aluno.nome}" /></div>
		</div>
		
	</h:form>

</h:body>
</html>