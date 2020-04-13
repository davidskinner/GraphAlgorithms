import java.util.*;

public //Graph Class
class Graph<T>
{
	//string is two vertices (K,V) -> ex. (12,EDGE)
	public HashMap<String, Edge<T>> map = new HashMap<>();

	public double[][] adjacencyMatrix;

	private List<Edge<T>> allEdges;
	private Map<Long, Vertex<T>> allVertices;
	boolean isDirected = false;

	public Graph()
	{

	}

	public Graph(boolean isDirected)
	{
		allEdges = new ArrayList<Edge<T>>();
		allVertices = new HashMap<Long, Vertex<T>>();
		this.isDirected = isDirected;
	}

	public Graph(boolean isDirected, int vertices)
	{
		adjacencyMatrix = new double[vertices][vertices];
		allEdges = new ArrayList<Edge<T>>();
		allVertices = new HashMap<Long, Vertex<T>>();
		this.isDirected = isDirected;
	}

	//assumes undirected and connected
	public Graph(int width, int height)
	{
		int total = width * height;

		adjacencyMatrix = new double[total][total];
		for (int i = 0; i < total; i++)
		{
			for (int j = 0; j < total; j++)
			{
				adjacencyMatrix[i][j] = 9d;
			}
		}
		allEdges = new ArrayList<Edge<T>>();
		allVertices = new HashMap<Long, Vertex<T>>();
		this.isDirected = true;

		for (long i = 0; i < total; i++)
		{
			if (i % width < width-1 && i + 1 < total)
			{
				// this addEdge adds an edge in both directions
				addEdge(i, i + 1, 1);
				addEdge(i+1,i,1);
			}
			if (i + height < total)
			{
				addEdge(i, i + height, 1);
				addEdge(i + height,i,1);
			}
		}
	}


	public void addEdge(long id1, long id2)
	{
		addEdge(id1, id2, 0);
	}

	//This works only for directed graph because for undirected graph we can end up
	//adding edges two times to allEdges

	public void addVertex(Vertex<T> vertex)
	{
		if (allVertices.containsKey(vertex.getId()))
		{
			return;
		}
		allVertices.put(vertex.getId(), vertex);
		for (Edge<T> edge : vertex.getEdges())
		{
			allEdges.add(edge);
		}
	}

	public Vertex<T> addSingleVertex(long id)
	{
		if (allVertices.containsKey(id))
		{
			return allVertices.get(id);
		}
		Vertex<T> v = new Vertex<T>(id);
		allVertices.put(id, v);
		return v;
	}

	public Vertex<T> getVertex(long id)
	{
		return allVertices.get(id);
	}

	public void addEdge(long id1, long id2, float weight)
	{
		Vertex<T> vertex1 = null;
		if (allVertices.containsKey(id1))
		{
			vertex1 = allVertices.get(id1);
		} else
		{
			vertex1 = new Vertex<T>(id1);
			allVertices.put(id1, vertex1);
		}

		Vertex<T> vertex2 = null;
		if (allVertices.containsKey(id2))
		{
			vertex2 = allVertices.get(id2);
		} else
		{
			vertex2 = new Vertex<T>(id2);
			allVertices.put(id2, vertex2);
		}

		Edge<T> edge = new Edge<T>(vertex1, vertex2, isDirected, weight);
		allEdges.add(edge);
		// add edge for both directions
		adjacencyMatrix[((int) vertex1.getId())][((int) vertex2.getId())] = edge.weight;
//		adjacencyMatrix[((int) vertex2.getId())][((int) vertex1.getId())] = edge.weight;
		map.put(String.valueOf(vertex1) + String.valueOf(vertex2), edge);
//		map.put(String.valueOf(vertex2) + String.valueOf(vertex1), edge);
		vertex1.addAdjacentVertex(edge, vertex2);
		if (!isDirected)
		{
			vertex2.addAdjacentVertex(edge, vertex1);
		}
	}

	public void addEdge(long id1, long id2, float from, float to)
	{
		Vertex<T> vertex1 = null;
		if (allVertices.containsKey(id1))
		{
			vertex1 = allVertices.get(id1);
		} else
		{
			vertex1 = new Vertex<T>(id1);
			allVertices.put(id1, vertex1);
		}

		Vertex<T> vertex2 = null;
		if (allVertices.containsKey(id2))
		{
			vertex2 = allVertices.get(id2);
		} else
		{
			vertex2 = new Vertex<T>(id2);
			allVertices.put(id2, vertex2);
		}

		Edge<T> edge = new Edge<T>(vertex1, vertex2, isDirected, from, to);
		allEdges.add(edge);
		adjacencyMatrix[((int) vertex1.getId())][((int) vertex2.getId())] = edge.weight;
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

	public Collection<Vertex<T>> getAllVertices()
	{
		return allVertices.values();
	}

	public void setDataForVertex(long id, T data)
	{
		if (allVertices.containsKey(id))
		{
			Vertex<T> vertex = allVertices.get(id);
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

	public String printAdjacencyMatrix()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("   ");
		for (int i = 0; i <adjacencyMatrix.length; i++)
		{
			builder.append(i).append("  ");
		}
		builder.append("\n");
		for (int i = 0; i < adjacencyMatrix.length; i++)
		{
			builder.append(i).append("| ");
			for (int j = 0; j < adjacencyMatrix.length; j++)
			{
				builder.append((int) adjacencyMatrix[i][j]).append("  ");
			}
			builder.append("\n");
		}
		return builder.toString();
	}
}
