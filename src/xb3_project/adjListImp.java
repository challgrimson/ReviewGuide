public class adjGraph<Product>{

	private int V;
	private int E;
	private Bag<Product>[] adj;

	public adjGraph(int V){
		this.V = V;
		adj = (Bag<Product>[] new Bag[V];
		for (int i = 0; i < V; i++)
			adj[i] = new Bag<Product>()
	}

	public void addEdge(int v, int w){
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}

	public int vertexCount(){
		return V;
	}

	public int edgeCount(){
		return E;
	}


}