package nl.paardustaart.rdgraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.NodeIterator;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

public class ModelMapper {
	
	private static int indentation;
	
	static {
		indentation = 0;
	}
	
	/**
	 * This method takes an RDF model as parameter and returns the root resource(s) of that model
	 * @param model
	 * @return
	 */
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
	public static void printModel(ArrayList<Resource> rootList) {
		
		for(Resource res : rootList) { // Loop through root resources
			printResource(res);
			System.out.println("");
		}
		
	}
	
	
	// This method prints resources and their objects, if an object is also a resource with other objects, print those as well
	private static void printResource(Resource res) {
		
		if(!res.isAnon()) {
			System.out.println(res); // Print resource name (URI)
		} else {
			System.out.println(); // Prints placeholder for a blank/anon node
		}
		
		indentation++; // Adds a tab for indentation purposes
		
		StmtIterator it = res.listProperties(); // Get the resource's properties and objects
		while(it.hasNext()) {
			Statement currentStatement = it.nextStatement();
			Property currentProperty = currentStatement.getPredicate();
			RDFNode currentNode = currentStatement.getObject();
			if(currentNode.isLiteral()) { // If the object is a literal, print it
				System.out.println(tab(indentation) + trim(currentProperty.toString()) + " : " + currentNode.toString());
			} else {
				System.out.print(tab(indentation) + trim(currentProperty.toString())); // print the property name for the BLANK reosurce
				printResource((Resource)currentNode); // If the object is a resource, go deeper
			}
		}
		indentation--; // Removes a tab
	}
	
	/**
	 * Returns a simple representation of the most top level RDF structure
	 * @param rootList
	 * @return
	 */
	public static HashMap<String, List<String[]>> getSimpleStructuredModel(ArrayList<Resource> rootList) {
		HashMap<String, List<String[]>> map = new HashMap<String, List<String[]>>();
		
		for(Resource res : rootList) {			
			ArrayList<String[]> currentList = new ArrayList<String[]>();
			
			StmtIterator it = res.listProperties();
			while(it.hasNext()) {
				String[] currentArray = new String[2];
				Statement currentStatement = it.nextStatement();
				Property currentProperty = currentStatement.getPredicate();
				RDFNode currentNode = currentStatement.getObject();
				
				currentArray[0] = trim(currentProperty.toString());			
				if(currentNode.isLiteral()) {
					currentArray[1] = currentNode.toString();
				} else {
					currentArray[1] = "BLANK";
				}
				currentList.add(currentArray);
			}
			map.put(res.toString(), currentList);
		}
		return map;
	}
	
	
	// This method is an easy way to add tabs to a sysout
	private static String tab(int indents) {
		String indentationString = "";
		for(int i = 0; i < indents; i++) {
			indentationString += "\t";
		}
		return indentationString;
	}
	
	
	// return the last part of a property URI
	private static String trim(String propertyURI) {
		String[] parts = propertyURI.split("#");
		return parts[parts.length - 1];
	}
}
