package br.com.valhala.academia.web.validators;

import org.apache.commons.lang3.StringUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.servlet.http.Part;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@FacesValidator("fileInputImageValidator")
public class FileInputImageValidator implements Validator {

    Pattern pattern;
    Matcher matcher;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        Part file = (Part) value;

        if (file != null) {
            pattern = Pattern.compile("^(image)\\/{1}(gif|jpe?g|png)$");
            matcher = pattern.matcher(file.getContentType());

            if (!matcher.matches()) {
                FacesMessage message = new FacesMessage("Formato de arquivo inválido. Deve ser um arquivo .gif, .jpeg ou .png.");
                message.setSeverity(FacesMessage.SEVERITY_WARN);
                throw new ValidatorException(message);
            }
        }

    }

}
