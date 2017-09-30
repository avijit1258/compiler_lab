#include <bits/stdc++.h>


using namespace std;

struct Node
{
	int at ;
	int cost;

	Node(int _at,int _cost)
	{
		at = _at;
		cost = _cost;
	}
};

bool operator<(Node A, Node B)
{

	return A.cost > B.cost;
}

struct Edge
{
	int v;
	int w;
	Edge(int _v,int _w)
	{
		v = _v;
		w = _w;
	}
};

vector<Edge> adj[100];
priority_queue<Node> PQ;
int dist[100];
int n;

void dijkstra(int s)
{
	for(int i = 1; i <= n; i++)
		dist[i] = 1000000000;
	dist[s] = 0;

	PQ.push(Node(s,0));

	while(!PQ.empty())
	{
		Node u = PQ.top();
		//cout << u.at << endl;
		PQ.pop();

		if(u.cost != dist[u.at])
			continue;

		for(Edge e : adj[u.at])
		{

			if(dist[e.v] > u.cost + e.w)
			{
				dist[e.v] = u.cost + e.w;
				cout << dist[e.v] << "   " <<e.v << endl;
				PQ.push(Node(e.v, dist[e.v]));

			}
		}


	}


}

int main()
{
	int T, a, b, h, m, n;
	cin >> T;

	for(int i = 0; i < T ; i++)
	{
		cin >> n >> m;

		for(int j = 0; j < m ; j++)
		{
			cin >> a >> b >> h;
			adj[a].push_back(Edge(b,h));
		}

		dijkstra(1);

		cout << dist[n] << endl;
        adj.clear();
	}



	return 0;
}
