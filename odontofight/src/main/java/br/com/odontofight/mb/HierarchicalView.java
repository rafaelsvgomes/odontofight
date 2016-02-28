package br.com.odontofight.mb;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.diagram.Connection;
import org.primefaces.model.diagram.DefaultDiagramModel;
import org.primefaces.model.diagram.DiagramModel;
import org.primefaces.model.diagram.Element;
import org.primefaces.model.diagram.connector.StraightConnector;
import org.primefaces.model.diagram.endpoint.DotEndPoint;
import org.primefaces.model.diagram.endpoint.EndPoint;
import org.primefaces.model.diagram.endpoint.EndPointAnchor;


@ManagedBean(name = "diagramHierarchicalView")
@RequestScoped
public class HierarchicalView {

	private DefaultDiagramModel model;

	@PostConstruct
	public void init() {
		model = new DefaultDiagramModel();
		model.setMaxConnections(-1);

		Element ceo = new Element(
				new NetworkElement("CEO", "pessoa.png"), "25em", "6em");
		ceo.addEndPoint(createEndPoint(EndPointAnchor.BOTTOM));
		model.addElement(ceo);

		// CFO
		Element cfo = new Element(
				new NetworkElement("CFO", "pessoa.png"),  "20em", "16em");
		cfo.addEndPoint(createEndPoint(EndPointAnchor.TOP));
		cfo.addEndPoint(createEndPoint(EndPointAnchor.BOTTOM));

		Element fin = new Element(
				new NetworkElement("FIM", "pessoa.png"),  "15em", "30em");
		fin.addEndPoint(createEndPoint(EndPointAnchor.TOP));

		Element pur = new Element(
				new NetworkElement("PUR", "pessoa.png"),  "25em", "30em");
		pur.addEndPoint(createEndPoint(EndPointAnchor.TOP));

		model.addElement(cfo);
		model.addElement(fin);
		model.addElement(pur);

		// CTO
		Element cto = new Element(
				new NetworkElement("CTO", "pessoa.png"),  "30em", "16em");
		cto.addEndPoint(createEndPoint(EndPointAnchor.TOP));
		cto.addEndPoint(createEndPoint(EndPointAnchor.BOTTOM));

		Element dev = new Element(
				new NetworkElement("DEV", "pessoa.png"),  "35em", "30em");
		dev.addEndPoint(createEndPoint(EndPointAnchor.TOP));

		Element tst = new Element(
				new NetworkElement("TST", "pessoa.png"),  "50em", "30em");
		tst.addEndPoint(createEndPoint(EndPointAnchor.TOP));

		model.addElement(cto);
		model.addElement(dev);
		model.addElement(tst);

		StraightConnector connector = new StraightConnector();
		connector.setPaintStyle("{strokeStyle:'#404a4e', lineWidth:1}");
		connector.setHoverPaintStyle("{strokeStyle:'#20282b'}");

		// connections
		model.connect(new Connection(ceo.getEndPoints().get(0), cfo
				.getEndPoints().get(0), connector));
		model.connect(new Connection(ceo.getEndPoints().get(0), cto
				.getEndPoints().get(0), connector));
		model.connect(new Connection(cfo.getEndPoints().get(1), fin
				.getEndPoints().get(0), connector));
		model.connect(new Connection(cfo.getEndPoints().get(1), pur
				.getEndPoints().get(0), connector));
		model.connect(new Connection(cto.getEndPoints().get(1), dev
				.getEndPoints().get(0), connector));
		model.connect(new Connection(cto.getEndPoints().get(1), tst
				.getEndPoints().get(0), connector));
	}

	private EndPoint createEndPoint(EndPointAnchor anchor) {
		DotEndPoint endPoint = new DotEndPoint(anchor);
		endPoint.setStyle("{fillStyle:'#404a4e'}");
		endPoint.setHoverStyle("{fillStyle:'#20282b'}");

		return endPoint;
	}

	public DiagramModel getModel() {
		return model;
	}

	public class NetworkElement implements Serializable {

		/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		private String name;
		private String image;

		public NetworkElement() {
		}

		public NetworkElement(String name, String image) {
			this.name = name;
			this.image = image;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		@Override
		public String toString() {
			return name;
		}

	}

}
