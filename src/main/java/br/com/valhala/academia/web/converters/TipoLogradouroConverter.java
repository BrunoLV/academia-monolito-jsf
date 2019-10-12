package br.com.valhala.academia.web.converters;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.valhala.academia.modelo.TipoLogradouro;

@FacesConverter("tipoLogradouroConverter")
public class TipoLogradouroConverter implements Converter<TipoLogradouro> {

	private static final String KEY_MAPA_TIPO_LOGRADOURO_CONVERTER = "mapaTipoLogradouroConverter";

	@Override
	public TipoLogradouro getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
		@SuppressWarnings("unchecked")
		Map<String, TipoLogradouro> mapa = (Map<String, TipoLogradouro>) viewMap
				.get(KEY_MAPA_TIPO_LOGRADOURO_CONVERTER);
		if (mapa == null) {
			return null;
		}
		return mapa.get(String.valueOf(value.hashCode()));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, TipoLogradouro tipoLogradouro) {
		if (tipoLogradouro == null) {
			return null;
		}
		Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
		@SuppressWarnings("unchecked")
		Map<String, TipoLogradouro> mapa = (Map<String, TipoLogradouro>) viewMap
				.get(KEY_MAPA_TIPO_LOGRADOURO_CONVERTER);
		if (mapa == null) {
			mapa = new HashMap<>();
			mapa.put(String.valueOf(tipoLogradouro.getDescricao().hashCode()), tipoLogradouro);
			viewMap.put(KEY_MAPA_TIPO_LOGRADOURO_CONVERTER, mapa);
		} else {
			if (!mapa.containsKey(String.valueOf(tipoLogradouro.getDescricao().hashCode()))) {
				mapa.put(String.valueOf(tipoLogradouro.getDescricao().hashCode()), tipoLogradouro);
			}
		}
		return tipoLogradouro.getDescricao();
	}

}
