package es.uniovi.miw.monitora.bean;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity class. Xml notation allow un/marshalling
 * @author diesire
 */
@XmlRootElement
public class Foo{
	private String bar;

	public String getBar() {
		return bar;
	}

	public void setBar(String bar) {
		this.bar = bar;
	}

	@Override
	public boolean equals(Object obj) {
		Foo foo = (Foo)obj;
		return bar.equals(foo.getBar());
	}
}
