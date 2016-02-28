package br.com.odontofight.mb;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class SobreMB {

	private String[] values;
	
	public SobreMB() {
		values = new String[]{"sobre.titulo", "sobre.desenv", 
				"sobre.site", "sobre.versao"};
	}
	
	public String[] getValues() {
		return values;
	}
}
