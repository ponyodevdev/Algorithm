import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력을 빠르게 받기 위한 BufferedReader 객체 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 첫 번째 줄에서 수열의 길이 n을 읽어옴
        int n = Integer.parseInt(br.readLine());
        
        // 두 번째 줄부터 n개의 수를 읽어 배열에 저장
        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = Integer.parseInt(br.readLine());
        }
        
        // ArrayDeque를 스택처럼 사용하기 위해 초기화
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        
        // 연산 결과를 저장할 StringBuilder 객체 초기화
        StringBuilder result = new StringBuilder();
        
        // 현재 push할 숫자, 1부터 시작
        int current = 1;
        
        // 주어진 수열의 각 숫자에 대해 처리
        for (int num : sequence) {
            // 현재 숫자가 목표 숫자(num)보다 작거나 같다면 push 연산을 계속
            while (current <= num) {
                stack.offerLast(current); // 스택에 current 숫자를 넣음 (push)
                result.append("+\n"); // "+" 연산 결과 기록
                current++; // 다음 숫자로 넘어감
            }
            
            // 스택의 top과 num이 일치하면 pop 연산을 해야함
            if (!stack.isEmpty() && stack.peekLast() == num) {
                stack.pollLast(); // 스택에서 숫자 pop
                result.append("-\n"); // "-" 연산 결과 기록
            } else {
                // 만약 현재 스택의 top이 num이 아니라면 불가능한 경우
                System.out.println("NO");
                return; // 프로그램 종료
            }
        }
        
        // 모든 연산을 마친 후 결과 출력
        System.out.print(result);
    }
}