# WEEK 5:

# Shortest Paths in Weighted Graphs

## Weighted Graphs

A weighted graph assigns a numerical value (weight) to each edge, representing distance, cost, or another relevant metric.

- **Graph Representation:**
  - **Adjacency Matrix:** A 2D array where `matrix[i][j]` stores the weight of the edge from vertex `i` to `j`.
  - **Adjacency List:** A list of lists, where `list[i]` stores pairs `(neighbor, weight)` for each connected vertex.

```cpp
// Example: Adjacency Matrix Representation
#include <iostream>
using namespace std;
const int INF = 1e9; // Representation of no edge

int main() {
    int graph[4][4] = {{0, 3, INF, 7},
                       {3, 0, 2, INF},
                       {INF, 2, 0, 1},
                       {7, INF, 1, 0}};
    
    // Display Graph
    for(int i=0; i<4; i++) {
        for(int j=0; j<4; j++) {
            cout << (graph[i][j] == INF ? "INF" : to_string(graph[i][j])) << " ";
        }
        cout << endl;
    }
    return 0;
}
```
![image](https://github.com/user-attachments/assets/8f10c713-24fa-4376-8701-0e2c980acfef)


## Shortest Paths in Weighted Graphs

- BFS computes shortest paths in **unweighted graphs** based on the number of edges.
- In **weighted graphs**, shortest paths are determined by the sum of edge weights.

### Single Source Shortest Paths

Finds the shortest path from a fixed vertex to all others. Applications:
- **Transportation networks** (e.g., routing packages from a hub to destinations).
- **Navigation systems** (e.g., finding the shortest driving route).

### All Pairs Shortest Paths

Finds the shortest paths between every pair of vertices. Applications:
- **Optimizing airline routes.**
- **Analyzing network latencies.**

## Negative Edge Weights

- **Example Use Case:**
  - Roads where traffic congestion increases cost (positive weight).
  - Routes with toll discounts (negative weight).
- **Problem with Negative Cycles:**
  - If a cycle has a negative total weight, the path cost can be reduced indefinitely.

## Dijkstra's Algorithm: Single Source Shortest Path

- **Works for:** Graphs with **non-negative** weights.
- **Does NOT work for:** Graphs with **negative** weight edges.
- **Uses:** Priority Queue to pick the next vertex with the smallest known distance.
- **Time Complexity:** O(n²) with a naive approach, O((n + m) log n) with a priority queue.

```cpp
// Dijkstra's Algorithm using Priority Queue
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

const int INF = 1e9;
vector<pair<int, int>> adj[1000];

void dijkstra(int src, int n) {
    vector<int> dist(n, INF);
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    dist[src] = 0;
    pq.push({0, src});

    while (!pq.empty()) {
        int d = pq.top().first;
        int u = pq.top().second;
        pq.pop();

        if (d > dist[u]) continue;

        for (auto [v, w] : adj[u]) {
            if (dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
                pq.push({dist[v], v});
            }
        }
    }
}
```

## Bellman-Ford Algorithm: Single Source Shortest Path with Negative Weights

- **Works for:** Graphs with **negative edges** (but no negative cycles).
- **Time Complexity:** O(mn), where `m` is the number of edges and `n` is the number of vertices.

```cpp
// Bellman-Ford Algorithm
#include <iostream>
#include <vector>
using namespace std;
const int INF = 1e9;

struct Edge {
    int u, v, w;
};

void bellmanFord(int n, int src, vector<Edge> &edges) {
    vector<int> dist(n, INF);
    dist[src] = 0;

    for (int i = 0; i < n - 1; i++) {
        for (Edge e : edges) {
            if (dist[e.u] != INF && dist[e.u] + e.w < dist[e.v]) {
                dist[e.v] = dist[e.u] + e.w;
            }
        }
    }
}
```

## Floyd-Warshall Algorithm: All Pairs Shortest Paths

- **Time Complexity:** O(n³).
- **Use Case:** Computing shortest paths between all pairs of nodes.

```cpp
// Floyd-Warshall Algorithm
#include <iostream>
using namespace std;
const int INF = 1e9;
int graph[100][100];

void floydWarshall(int n) {
    for (int k = 0; k < n; k++) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][k] != INF && graph[k][j] != INF)
                    graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j]);
            }
        }
    }
}
```

## Minimum Cost Spanning Tree (MCST)

### Prim’s Algorithm

- **Grows a tree from an arbitrary starting vertex.**
- **Similar to Dijkstra’s Algorithm,** but updates distances differently.
- **Time Complexity:** O(n²), or O(m log n) with priority queues.

```cpp
// Prim's Algorithm
#include <iostream>
#include <vector>
#include <queue>
using namespace std;
const int INF = 1e9;
vector<pair<int, int>> adj[1000];

void prim(int n) {
    vector<int> key(n, INF), parent(n, -1);
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push({0, 0});
    key[0] = 0;

    while (!pq.empty()) {
        int u = pq.top().second;
        pq.pop();

        for (auto [v, weight] : adj[u]) {
            if (weight < key[v]) {
                key[v] = weight;
                pq.push({key[v], v});
                parent[v] = u;
            }
        }
    }
}
```

### Kruskal’s Algorithm

- **Sort edges by weight and add edges without forming a cycle.**
- **Uses Union-Find to detect cycles.**
- **Time Complexity:** O(m log n).

```cpp
// Kruskal's Algorithm
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
struct Edge { int u, v, w; };
vector<Edge> edges;

bool cmp(Edge a, Edge b) { return a.w < b.w; }

void kruskal(int n) {
    sort(edges.begin(), edges.end(), cmp);
}
```
