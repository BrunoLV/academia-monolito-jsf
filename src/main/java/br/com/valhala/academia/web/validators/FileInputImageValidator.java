package br.com.valhala.academia.web.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

@SuppressWarnings("rawtypes")
@FacesValidator("fileInputImageValidator")
public class FileInputImageValidator implements Validator {

	Pattern pattern;
	Matcher matcher;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) {

		Part file = (Part) value;

		if (file != null) {
			pattern = Pattern.compile("^(image)\\/{1}(gif|jpe?g|png)$");
			matcher = pattern.matcher(file.getContentType());

			if (!matcher.matches()) {
				FacesMessage message = new FacesMessage(
						"Formato de arquivo inválido. Deve ser um arquivo .gif, .jpeg ou .png.");
				message.setSeverity(FacesMessage.SEVERITY_WARN);
				throw new ValidatorException(message);
			}
		}

	}

}
