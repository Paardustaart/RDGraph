package nl.paardustaart.rdgraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.VCARD;

public class Main {
	
	
	public static void main(String[] args) {
		
		Model model = createCustomModel(); // load a custom RDF model
		
		ArrayList<Resource> rootResources = ModelMapper.getRootResources(model); // Return the root resources from the given RDF model
		
		//ModelMapper.printModel(rootResources); // print the RDF model in tree structure starting from the given root resources
		
		HashMap<String, List<String[]>> map = ModelMapper.getSimpleStructuredModel(rootResources);
		
		for(String key : map.keySet()) {
			System.out.println(key);
			
			for(String[] collection : map.get(key)) {
				for(String s : collection) {
					System.out.println(s);
				}
			}
			
		}
		
	}
	
	
	// Creates a custom RDF model
	private static Model createCustomModel() {
		String URI = "http://johndoe.nl/john";
		String otherURI = "http://www.brock.com/brock";

		String firstName = "John";
		String lastName = "Doe";
		String fullName = firstName + " " + lastName;

		Model model = ModelFactory.createDefaultModel();

		model.createResource(URI)
				.addProperty(VCARD.EMAIL, "john@email.com")
				.addProperty(VCARD.EMAIL, "johndoe@email.com")
				.addProperty(VCARD.FN, fullName)
				.addProperty(VCARD.N, model.createResource()
					.addProperty(VCARD.Given, firstName)
					.addProperty(VCARD.Family, lastName))
				.addProperty(VCARD.Family, model.createResource()
						.addProperty(VCARD.NAME, "Does"));
		
		model.createResource(otherURI)
			.addProperty(VCARD.NICKNAME, "Brocko's");
		
		return model;
	}
	
}

