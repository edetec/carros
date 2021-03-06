package com.carros.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.carros.model.Marca;

@FacesConverter(value = "marcaConverter")
public class MarcaConverter implements Converter {

	protected void addAttribute(UIComponent component, Marca bean) {
		String key = String.valueOf(bean.getId());
		getAttributesFrom(component).put(key, bean);
	}

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component,
			String value) {
		if (value != null) {
			return getAttributesFrom(component).get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component,
			Object value) {
		if (value != null && !"".equals(value)) {
			Marca bean = (Marca) value;
			addAttribute(component, bean);
			Integer codigo = bean.getId();
			if (codigo != null) {
				return String.valueOf(codigo);
			}
		}
		return null;
	}

	protected Map<String, Object> getAttributesFrom(UIComponent component) {
		return component.getAttributes();
	}

}
