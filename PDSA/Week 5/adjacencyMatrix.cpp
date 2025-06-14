// Example: Adjacency Matrix Representation
#include <bits/stdc++.h>
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