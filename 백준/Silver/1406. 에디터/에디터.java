import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력을 빠르게 처리하기 위해 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 초기 문자열 입력 및 커서 초기화
        String initial = br.readLine();
        int M = Integer.parseInt(br.readLine());

        // 커서 왼쪽, 오른쪽을 관리할 두 개의 스택
        Stack<Character> leftStack = new Stack<>();
        Stack<Character> rightStack = new Stack<>();

        // 초기 문자열을 왼쪽 스택에 push
        for (char c : initial.toCharArray()) {
            leftStack.push(c);
        }

        // 명령어 입력 및 처리
        for (int i = 0; i < M; i++) {
            String command = br.readLine();
            char cmd = command.charAt(0);

            switch (cmd) {
                case 'L': // 커서를 왼쪽으로 이동
                    if (!leftStack.isEmpty()) {
                        rightStack.push(leftStack.pop());
                    }
                    break;
                case 'D': // 커서를 오른쪽으로 이동
                    if (!rightStack.isEmpty()) {
                        leftStack.push(rightStack.pop());
                    }
                    break;
                case 'B': // 커서 왼쪽 문자 삭제
                    if (!leftStack.isEmpty()) {
                        leftStack.pop();
                    }
                    break;
                case 'P': // 커서 왼쪽에 문자 추가
                    leftStack.push(command.charAt(2));
                    break;
            }
        }

        // 최종 결과 조합 (leftStack + rightStack의 반대 순서)
        while (!leftStack.isEmpty()) {
            sb.append(leftStack.pop());
        }
        sb.reverse(); // leftStack은 LIFO 구조이므로 reverse 필요

        while (!rightStack.isEmpty()) {
            sb.append(rightStack.pop());
        }

        // 출력
        System.out.println(sb.toString());
    }
}
