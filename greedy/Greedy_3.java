/* 
****문제****
[문제 출처-프로그래머스 코딩테스트 고득점 Kit - Greedy - 큰 수 만들기]

문제 설명
어떤 숫자에서 k개의 수를 제거했을 때 얻을 수 있는 가장 큰 숫자를 구하려 합니다.

예를 들어, 숫자 1924에서 수 두 개를 제거하면 [19, 12, 14, 92, 94, 24] 를 만들 수 있습니다. 이 중 가장 큰 숫자는 94 입니다.

문자열 형식으로 숫자 number와 제거할 수의 개수 k가 solution 함수의 매개변수로 주어집니다. 
number에서 k 개의 수를 제거했을 때 만들 수 있는 수 중 가장 큰 숫자를 문자열 형태로 return 하도록 solution 함수를 완성하세요.

제한 조건
number는 2자리 이상, 1,000,000자리 이하인 숫자입니다.
k는 1 이상 number의 자릿수 미만인 자연수입니다.
입출력 예
number	k	return
"1924"	2	"94"
"1231234"	3	"3234"
"4177252841"	4	"775841"
 */

import java.util.Stack;

class Solution {
    public String solution(String number, int k) {
        char[] result = new char[number.length() - k];  // 결과를 저장할 배열
        Stack<Character> stack = new Stack<>();  // 숫자들을 관리할 스택

        // 문자열의 각 문자에 대해 반복
        for (int i = 0; i < number.length(); i++) {
            char c = number.charAt(i);  // 현재 처리하는 숫자
            // 스택이 비어있지 않고, 스택의 top 숫자보다 현재 숫자가 크고,
            // 아직 제거할 숫자가 남아있다면 (k가 남아있다면)
            while (!stack.isEmpty() && stack.peek() < c && k-- > 0) {
                stack.pop();  // 스택에서 작은 숫자 제거 (가장 큰 숫자를 만들기 위한 그리디 선택)
            }
            stack.push(c);  // 현재 숫자를 스택에 추가
        }

        // 스택에 남은 숫자들을 result 배열에 저장
        for (int i = 0; i < result.length; i++) {
            result[i] = stack.get(i);  // 스택에서 하나씩 꺼내서 result에 넣음
        }

        return new String(result);  // 최종적으로 결과 문자열을 반환
    }
}
