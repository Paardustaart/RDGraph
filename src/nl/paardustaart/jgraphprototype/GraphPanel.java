package nl.paardustaart.jgraphprototype;

import javax.swing.JPanel;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxGraph;

public class GraphPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public GraphPanel(int w, int h) {
		
		mxGraph graph = new mxGraph();
		
		graph.setMinimumGraphSize(new mxRectangle(0, 0, w, h));
		
		Object parent = graph.getDefaultParent();
		
		graph.getModel().beginUpdate();
		try
		{
			Object node1 = graph.insertVertex(parent, null, "Node I", w / 2, h / 2 - 100, 80, 30, "defaultVertex;fontColor=red");
			Object node2 = graph.insertVertex(parent, null, "Node II!", w / 2 - 125, h / 2 + 75, 80, 30);
			Object node3 = graph.insertVertex(parent, null, "Node III", w / 2, h / 2 + 75, 80, 30);
			Object node4 = graph.insertVertex(parent, null, "Node IV", w / 2 + 125, h / 2 + 75, 80, 30);
			
			graph.insertEdge(parent, null, "Edge I", node1, node2);
			graph.insertEdge(parent, null, "Edge II", node1, node3);
			graph.insertEdge(parent, null, "Edge III", node1, node4);
			
			System.out.println(graph.getCellStyle(node1));
			
		}
		finally
		{
			graph.getModel().endUpdate();
		}
		
		this.add(new mxGraphComponent(graph));
		
	}
	
	
}
