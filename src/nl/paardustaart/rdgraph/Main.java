package nl.paardustaart.rdgraph;

import java.util.ArrayList;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.vocabulary.VCARD;

public class Main {
	
	static int indentation = 0;
	
	public static void main(String[] args) {
		Logger.getRootLogger().setLevel(Level.OFF);

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
		
		printModel(model, getRootResources(model));
		
	}
	
	// This method takes an RDF model as parameter and returns the root resource(s) of that model
	public static ArrayList<Resource> getRootResources(Model model) {
		
		ArrayList<Resource> nonLiteralObjectlist = new ArrayList<Resource>(); // Create ArrayList to cache non-literal objects
		NodeIterator tempNodeIt = model.listObjects(); // Get iterator of the model's objects
		
		ArrayList<Resource> rootResources = new ArrayList<Resource>(); // create a list for the model's root resources
		ResIterator resIt = model.listSubjects(); // Get iterator of the model's subjects
		
		// Loop through every object node
		while (tempNodeIt.hasNext()) {
			RDFNode currentNode = tempNodeIt.nextNode();
			if(!currentNode.isLiteral()) { // Check if current Node is something different than a literal
				nonLiteralObjectlist.add((Resource)currentNode); // if so, add it
			}
		}
		
		// Loop through the model's resources
		while(resIt.hasNext()) {
			Resource currentResource = resIt.nextResource();
				if(!nonLiteralObjectlist.contains(currentResource) && !rootResources.contains(currentResource)) { // check if any resource is also an object and if it is also added to the rootList
					rootResources.add(currentResource);
			}
		}
		
		return rootResources;
	}
	
	// This method prints the structure of a given RDF model starting with the given root resource
	public static void printModel(Model model, ArrayList<Resource> rootList) {
		
		for(Resource res : rootList) { // Loop through rootresources
			printResource(res);
			System.out.println("");
		}
	}
		
	// This method prints resources and their objects, if an object is also a resource with other objects, print those as well
	public static void printResource(Resource res) {
		if(!res.isAnon()) {
			System.out.println(res); // Print resource name (URI)
		} else {
			System.out.println(tab(indentation) + "BLANK NODE"); // Prints placeholder for a blank/anon node
		}
		indentation++; // Adds a tab for indentation purposes
		StmtIterator it = res.listProperties(); // Get the resource's properties and objects
		while(it.hasNext()) { 
			RDFNode currentNode = it.nextStatement().getObject();
			if(currentNode.isLiteral()) { // If the object is a literal, print it
				System.out.println(tab(indentation) + currentNode.toString());
			} else {
				printResource((Resource)currentNode); // If the object is a resource, go deeper
			}
		}
		indentation--; // Removes a tab
	}
	
	// This method is an easy way to add tabs to a sysout
	public static String tab(int indents) {
		String indentation = "";
		for(int i = 0; i < indents; i++) {
			indentation += "\t";
		}
		return indentation;
	}
	
}

