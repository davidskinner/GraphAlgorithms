public class Main
{
	public static void main(String[] args)
	{
		Graph<Integer> graph = new Graph<Integer>(2,2);
		System.out.println("edges ".concat(Integer.toString(graph.getAllEdges().size())));
		System.out.println(graph.printAdjacencyMatrix());
		GraphDrawer graphDrawer = new GraphDrawer(graph);
	}
}
