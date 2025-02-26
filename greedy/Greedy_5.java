/* 
****문제****
[문제 출처-프로그래머스 코딩테스트 고득점 Kit - Greedy - 섬 연결하기]


n개의 섬 사이에 다리를 건설하는 비용(costs)이 주어질 때, 최소의 비용으로 모든 섬이 서로 통행 가능하도록 만들 때 필요한 최소 비용을 return 하도록 solution을 완성하세요.

다리를 여러 번 건너더라도, 도달할 수만 있으면 통행 가능하다고 봅니다. 예를 들어 A 섬과 B 섬 사이에 다리가 있고, B 섬과 C 섬 사이에 다리가 있으면 A 섬과 C 섬은 서로 통행 가능합니다.

제한사항

섬의 개수 n은 1 이상 100 이하입니다.
costs의 길이는 ((n-1) * n) / 2이하입니다.
임의의 i에 대해, costs[i][0] 와 costs[i] [1]에는 다리가 연결되는 두 섬의 번호가 들어있고, costs[i] [2]에는 이 두 섬을 연결하는 다리를 건설할 때 드는 비용입니다.
같은 연결은 두 번 주어지지 않습니다. 또한 순서가 바뀌더라도 같은 연결로 봅니다. 즉 0과 1 사이를 연결하는 비용이 주어졌을 때, 1과 0의 비용이 주어지지 않습니다.
모든 섬 사이의 다리 건설 비용이 주어지지 않습니다. 이 경우, 두 섬 사이의 건설이 불가능한 것으로 봅니다.
연결할 수 없는 섬은 주어지지 않습니다.
입출력 예

n	costs	                                 return
4	[[0,1,1],[0,2,2],[1,2,5],[1,3,1],[2,3,8]]	4 */

import java.util.*;
class Solution{
    private int[] parent;

    //find 함수 : 해당 노드의 부모를 찾는 함수 (경로 압축)
    public int find(int a){
        if(parent[a]  == a) return a; // 부모가 자기 자신이면 자신을 반환
        else retrun parent[a] = find(parent[a]); // 경로 압축을 통해 부모를 바로 찾음
    }

    // union 함수 : 두 노드를 합치는 함수 (합치기)
    public void union(int a, int b){
        a = find(a); // a의 부모 찾기
        b = find(b); // b의 부모 찾기
        if(a != b){ // 두 노드가 다르면 합침
            parent[b] = a; // b의 부모를 a로 설정
        }
    }
}

public int solution(int n, int[][] costs){
    int answer = 0;
    parent = new int[n]; // 부모 배열 초기화

    // 초기에는 각 노드의 부모를 자기 자신으로 설정 
    for(int i = 0; i < n; i++){
        parent[i] = i;
    }

    // 간선들을 비용 기준으로 오름차순 정렬
    Arrays.sort(costs, (o1, o2) -> o1[2] - o2[2]);

    //크루스칼 알고리즘
    for(int i =0; i < costs.length; i++){
        // 간선의 두 노드가 같은 집합에 속하지 않으면 합침
        if(find(costs[i][0]) != find(costs[i][1])){
            union(costs[i][0], costs[i][1]); // 두 노드를 합침
            answer += costs[i][2]; // 비용을 더함
        }
    }
    return answer; // 최소 스패닝 트리의 총 비용 반환
}