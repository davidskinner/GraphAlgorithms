import java.util.*;

public //Graph Class
class Graph<T>
{
	//string is two vertices (K,V) -> ex. (12,EDGE)
	public HashMap<String, Edge<T>> map = new HashMap<>();

	public double[][] adjacencyMatrix;

	private List<Edge<T>> allEdges;
	private Map<Long, Vertex<T>> allVertex;
	boolean isDirected = false;

	public Graph()
	{

	}

	public Graph(boolean isDirected)
	{
		allEdges = new ArrayList<Edge<T>>();
		allVertex = new HashMap<Long, Vertex<T>>();
		this.isDirected = isDirected;
	}

	public Graph(boolean isDirected, int vertices)
	{
		adjacencyMatrix = new double[vertices][vertices];
		allEdges = new ArrayList<Edge<T>>();
		allVertex = new HashMap<Long, Vertex<T>>();
		this.isDirected = isDirected;
	}

	public void addEdge(long id1, long id2)
	{
		addEdge(id1, id2, 0);
	}

	//This works only for directed graph because for undirected graph we can end up
	//adding edges two times to allEdges

	public void addVertex(Vertex<T> vertex)
	{
		if (allVertex.containsKey(vertex.getId()))
		{
			return;
		}
		allVertex.put(vertex.getId(), vertex);
		for (Edge<T> edge : vertex.getEdges())
		{
			allEdges.add(edge);
		}
	}

	public Vertex<T> addSingleVertex(long id)
	{
		if (allVertex.containsKey(id))
		{
			return allVertex.get(id);
		}
		Vertex<T> v = new Vertex<T>(id);
		allVertex.put(id, v);
		return v;
	}

	public Vertex<T> getVertex(long id)
	{
		return allVertex.get(id);
	}

	public void addEdge(long id1, long id2, float weight)
	{
		Vertex<T> vertex1 = null;
		if (allVertex.containsKey(id1))
		{
			vertex1 = allVertex.get(id1);
		} else
		{
			vertex1 = new Vertex<T>(id1);
			allVertex.put(id1, vertex1);
		}

		Vertex<T> vertex2 = null;
		if (allVertex.containsKey(id2))
		{
			vertex2 = allVertex.get(id2);
		} else
		{
			vertex2 = new Vertex<T>(id2);
			allVertex.put(id2, vertex2);
		}

		Edge<T> edge = new Edge<T>(vertex1, vertex2, isDirected, weight);
		allEdges.add(edge);
		map.put(String.valueOf(vertex1) + String.valueOf(vertex2), edge);
		vertex1.addAdjacentVertex(edge, vertex2);
		if (!isDirected)
		{
			vertex2.addAdjacentVertex(edge, vertex1);
		}
	}

	public void addEdge(long id1, long id2, float from, float to)
	{
		Vertex<T> vertex1 = null;
		if (allVertex.containsKey(id1))
		{
			vertex1 = allVertex.get(id1);
		} else
		{
			vertex1 = new Vertex<T>(id1);
			allVertex.put(id1, vertex1);
		}

		Vertex<T> vertex2 = null;
		if (allVertex.containsKey(id2))
		{
			vertex2 = allVertex.get(id2);
		} else
		{
			vertex2 = new Vertex<T>(id2);
			allVertex.put(id2, vertex2);
		}

		Edge<T> edge = new Edge<T>(vertex1, vertex2, isDirected, from, to);
		allEdges.add(edge);
		adjacencyMatrix[((int) vertex1.getId()) - 1][((int) vertex2.getId()) - 1] = edge.weight;
		map.put(String.valueOf(vertex1) + String.valueOf(vertex2), edge);
		vertex1.addAdjacentVertex(edge, vertex2);
		if (!isDirected)
		{
			vertex2.addAdjacentVertex(edge, vertex1);
		}

	}

	public List<Edge<T>> getAllEdges()
	{
		return allEdges;
	}

	public Collection<Vertex<T>> getAllVertex()
	{
		return allVertex.values();
	}

	public void setDataForVertex(long id, T data)
	{
		if (allVertex.containsKey(id))
		{
			Vertex<T> vertex = allVertex.get(id);
			vertex.setData(data);
		}
	}

	@Override
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		for (Edge<T> edge : getAllEdges())
		{
			buffer.append(edge.getVertex1() + " " + edge.getVertex2() + " " + edge.getWeight());
			buffer.append("\n");
		}
		return buffer.toString();
	}
}
