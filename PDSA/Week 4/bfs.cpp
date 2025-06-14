#include <iostream>
#include <vector>
#include <queue>
#include <unordered_map>
#include <unordered_set>

using namespace std;

class Graph {
public:
    unordered_map<int, vector<int>> adj_list;

    void add_edge(int u, int v) {
        adj_list[u].push_back(v);
        adj_list[v].push_back(u); // For an undirected graph
    }

    void bfs(int start) {
        unordered_set<int> visited;
        queue<int> q;
        
        q.push(start);
        visited.insert(start);
        
        while (!q.empty()) {
            int node = q.front();
            q.pop();
            cout << node << " ";
            
            for (int neighbor : adj_list[node]) {
                if (visited.find(neighbor) == visited.end()) {
                    visited.insert(neighbor);
                    q.push(neighbor);
                }
            }
        }
    }
};

int main() {
    Graph g;
    g.add_edge(0, 1);
    g.add_edge(0, 2);
    g.add_edge(1, 3);
    g.add_edge(1, 4);
    g.add_edge(2, 5);
    g.add_edge(2, 6);
    
    g.bfs(0); // Output: 0 1 2 3 4 5 6
    
    return 0;
}
