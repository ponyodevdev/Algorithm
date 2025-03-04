import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력 받기
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] answer = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // 입력 데이터 저장
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            answer[i] = -1;  // 기본값 -1로 초기화
        }
        
        // 스택 선언 (인덱스를 저장)
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < N; i++) {
            // 스택이 비어있지 않고 현재 숫자가 스택 top 인덱스의 값보다 크면
            while (!stack.isEmpty() && A[stack.peek()] < A[i]) {
                int idx = stack.pop();  // 인덱스 꺼내기
                answer[idx] = A[i];     // 오큰수 저장
            }
            
            // 현재 인덱스 push
            stack.push(i);
        }
        
        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb.toString());
    }
}
