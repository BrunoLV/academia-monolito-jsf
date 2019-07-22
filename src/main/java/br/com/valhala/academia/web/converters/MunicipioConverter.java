package br.com.valhala.academia.web.converters;

import java.util.HashMap;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.valhala.academia.db.modelo.Municipio;

@FacesConverter("municipioConverter")
public class MunicipioConverter implements Converter<Municipio> {

	private static final String KEY_MAPA_MUNICIPIO_CONVERTER = "mapaMunicipioConverter";

	@Override
	public Municipio getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.trim().isEmpty()) {
			return null;
		}
		Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
		@SuppressWarnings("unchecked")
		Map<String, Municipio> mapa = (Map<String, Municipio>) viewMap.get(KEY_MAPA_MUNICIPIO_CONVERTER);
		if (mapa == null) {
			return null;
		}
		return mapa.get(String.valueOf(value.hashCode()));
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Municipio municio) {
		if (municio == null) {
			return null;
		}
		Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
		@SuppressWarnings("unchecked")
		Map<String, Municipio> mapa = (Map<String, Municipio>) viewMap.get(KEY_MAPA_MUNICIPIO_CONVERTER);
		if (mapa == null) {
			mapa = new HashMap<>();
			mapa.put(String.valueOf(municio.getNome().hashCode()), municio);
			viewMap.put(KEY_MAPA_MUNICIPIO_CONVERTER, mapa);
		} else {
			if (!mapa.containsKey(String.valueOf(municio.getNome().hashCode()))) {
				mapa.put(String.valueOf(municio.getNome().hashCode()), municio);
			}
		}
		return municio.getNome();
	}

}
