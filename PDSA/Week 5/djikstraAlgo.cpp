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
        int v = pq.top().first;
        int u = pq.top().second;
        pq.pop();

        if (v > dist[u]) continue;

        for (auto [v, w] : adj[u]) {
            if (dist[u] + w < dist[v]) {
                dist[v] = dist[u] + w;
                pq.push({dist[v], v});
            }
        }
    }
}