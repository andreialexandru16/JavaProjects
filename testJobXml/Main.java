package testJobXml;

import java.io.File;
import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

import javax.swing.text.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class Main {
	public static List<Orders> listaOrders = new ArrayList<Orders>();
	public static Set<String> setSupp = new HashSet<String>();

	public static void main(String[] args) {
		
		try {
			System.out.println("Introduceti numele fisierului citit: ");
		Scanner sc=new Scanner(System.in);
		String fisierNume=sc.nextLine();
		sc.close();
        String[] numeSplit = fisierNume.split(".x");
        String numarComanda= numeSplit[0].substring(numeSplit[0].length()-2, numeSplit[0].length());
			if(Character.isDigit(numeSplit[0].charAt(numeSplit[0].length()-1)) && Character.isDigit(numeSplit[0].charAt(numeSplit[0].length()-2)) &&
					numeSplit[0].contains("orders") && numeSplit[0].length()==8 && numeSplit[1].contains("ml")){
				String filePath = "C:\\Users\\Asus\\Desktop\\Orders\\";
				filePath += fisierNume;
				File file = new File(filePath);
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = builderFactory.newDocumentBuilder();
				org.w3c.dom.Document doc = builder.parse(file);
				citesteOrders(doc);
				creareXmlComandaProduse(listaOrders,numarComanda);
			} else{
				throw new ExceptieFisierInvalid();
			}
		} catch (ExceptieFisierInvalid  e) {
			System.out.println("Numele fisierului este incorect!");
		}catch(Exception ex) {
			ex.printStackTrace();
		}

		
	}

	public static boolean uniqueSupplier(List<Orders> listaOrders) {
		boolean ok = true;
		String numeSupplier = listaOrders.get(0).getLista().get(0).supplier;
		for (int i = 0; i < listaOrders.size(); i++) {
			if (!(listaOrders.get(i).getLista().get(i).supplier.equalsIgnoreCase(numeSupplier))) {
				ok = false;
			}
		}
		return ok;

	}

	public static void creareXmlComandaProduse(List<Orders> listaOrders,String numarComanda) throws ParserConfigurationException,
			TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
		boolean ok = uniqueSupplier(listaOrders);
		if (ok == true) {
			DocumentBuilderFactory builderFactory2 = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = builderFactory2.newDocumentBuilder();
			org.w3c.dom.Document document = documentBuilder.newDocument();

			Element products = document.createElement("products");
			document.appendChild(products);

			for (int i = 0; i < listaOrders.size(); i++) {
				for (int j = 0; j < listaOrders.get(i).getLista().size(); j++) {
					Element product = document.createElement("product");
					products.appendChild(product);
					Element description = document.createElement("description");
					description.appendChild(document.createTextNode(listaOrders.get(i).getLista().get(j).description));
					product.appendChild(description);

					Element gtin = document.createElement("gtin");
					gtin.appendChild(document.createTextNode(listaOrders.get(i).getLista().get(j).gtin));
					product.appendChild(gtin);

					Element price = document.createElement("price");
					price.appendChild(
							document.createTextNode(Float.toString(listaOrders.get(i).getLista().get(j).price)));
					price.setAttribute("currency", listaOrders.get(i).getLista().get(j).currency);
					product.appendChild(price);

					Element orderId = document.createElement("orderid");
					orderId.appendChild(document.createTextNode(Integer.toString(listaOrders.get(i).getId())));
					product.appendChild(orderId);

					TransformerFactory factory = TransformerFactory.newInstance();
					Transformer transformer = factory.newTransformer();
					DOMSource domSource = new DOMSource(document);

					StreamResult result = new StreamResult("C:\\Users\\Asus\\Desktop\\SupplierXml\\"
							+ listaOrders.get(0).getLista().get(0).supplier + numarComanda + ".xml");

					transformer.transform(domSource, result);
				}
			}
		} else if (ok == false) {
			setSupp = crearelistaSupplierOrder(listaOrders);
			for (String s : setSupp) {
				DocumentBuilderFactory builderFactory2 = DocumentBuilderFactory.newInstance();
				DocumentBuilder documentBuilder = builderFactory2.newDocumentBuilder();
				org.w3c.dom.Document document = documentBuilder.newDocument();

				Element products = document.createElement("products");
				document.appendChild(products);
				for (int i = 0; i < listaOrders.size(); i++) {
					for (int j = 0; j < listaOrders.get(i).getLista().size(); j++) {
						if (s.equalsIgnoreCase(listaOrders.get(i).getLista().get(j).supplier)) {
							Element product = document.createElement("product");
							products.appendChild(product);
							Element description = document.createElement("description");
							description.appendChild(
									document.createTextNode(listaOrders.get(i).getLista().get(j).description));
							product.appendChild(description);

							Element gtin = document.createElement("gtin");
							gtin.appendChild(document.createTextNode(listaOrders.get(i).getLista().get(j).gtin));
							product.appendChild(gtin);

							Element price = document.createElement("price");
							price.appendChild(document
									.createTextNode(Float.toString(listaOrders.get(i).getLista().get(j).price)));
							price.setAttribute("currency", listaOrders.get(i).getLista().get(j).currency);
							product.appendChild(price);

							Element orderId = document.createElement("orderid");
							orderId.appendChild(document.createTextNode(Integer.toString(listaOrders.get(i).getId())));
							product.appendChild(orderId);

							TransformerFactory factory = TransformerFactory.newInstance();
							Transformer transformer = factory.newTransformer();
							DOMSource domSource = new DOMSource(document);

							StreamResult result = new StreamResult("C:\\Users\\Asus\\Desktop\\SupplierXml\\"
									+ listaOrders.get(i).getLista().get(j).supplier + numarComanda+ ".xml");

							transformer.transform(domSource, result);
						}
					}
				}
			}

		}
	}

	public static Set<String> crearelistaSupplierOrder(List<Orders> listaorders) {
		Set<String> setSupplier = new HashSet<String>();
		for (Orders o : listaorders) {
			for (int i = 0; i < o.getLista().size(); i++) {
				setSupplier.add(o.getLista().get(i).supplier);
			}

		}
		return setSupplier;
	}

	public static void citesteOrders(org.w3c.dom.Document doc) {
		NodeList order = doc.getElementsByTagName("order");
		for (int i = 0; i < order.getLength(); i++) {
			Node node = order.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Orders orders = new Orders();
				Element element = (Element) node;
				orders.data = element.getAttribute("created");
				orders.id = Integer.parseInt(element.getAttribute("ID"));
				orders.lista = citesteNodurile(doc, i);
				listaOrders.add(orders);
			}
		}
	}

	public static List<Product> citesteNodurile(org.w3c.dom.Document doc, int itemNr) {
		List<Product> lista = new ArrayList<Product>();
		Node orderNode = doc.getElementsByTagName("order").item(itemNr);
		NodeList orderChildNodes = orderNode.getChildNodes();
		Product p = new Product();
		for (int i = 0; i < orderChildNodes.getLength(); i++) {
			Node node = orderChildNodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE && Objects.equals("product", node.getNodeName())) {
				Element element = (Element) node;
				p.description = element.getElementsByTagName("description").item(0).getTextContent();
				p.gtin = element.getElementsByTagName("gtin").item(0).getTextContent();
				p.currency = element.getElementsByTagName("price").item(0).getAttributes().getNamedItem("currency").getTextContent();
				p.price = Float.parseFloat(element.getElementsByTagName("price").item(0).getTextContent());
				p.supplier = element.getElementsByTagName("supplier").item(0).getTextContent();
				lista.add(p);
				p = new Product();
			}
		}
		return lista;
	}
}
