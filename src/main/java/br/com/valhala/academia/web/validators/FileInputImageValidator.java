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

@FacesValidator("fileInputImageValidator")
public class FileInputImageValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Part file = (Part) value;
        if (!StringUtils.equalsAny(file.getContentType(), "image/gif", "image/jpeg", "image/png")) {
            FacesMessage message = new FacesMessage("Formato de arquivo inválido. Deve ser um arquivo .gif, .jpeg ou .png.");
            message.setSeverity(FacesMessage.SEVERITY_WARN);
            throw new ValidatorException(message);
        }
    }

}
