/* 
****문제****
[문제 출처-프로그래머스 코딩테스트 고득점 Kit-조이스틱]


문제 설명
조이스틱으로 알파벳 이름을 완성하세요. 맨 처음엔 A로만 이루어져 있습니다.
ex) 완성해야 하는 이름이 세 글자면 AAA, 네 글자면 AAAA

조이스틱을 각 방향으로 움직이면 아래와 같습니다.

▲ - 다음 알파벳
▼ - 이전 알파벳 (A에서 아래쪽으로 이동하면 Z로)
◀ - 커서를 왼쪽으로 이동 (첫 번째 위치에서 왼쪽으로 이동하면 마지막 문자에 커서)
▶ - 커서를 오른쪽으로 이동 (마지막 위치에서 오른쪽으로 이동하면 첫 번째 문자에 커서)
예를 들어 아래의 방법으로 "JAZ"를 만들 수 있습니다.

- 첫 번째 위치에서 조이스틱을 위로 9번 조작하여 J를 완성합니다.
- 조이스틱을 왼쪽으로 1번 조작하여 커서를 마지막 문자 위치로 이동시킵니다.
- 마지막 위치에서 조이스틱을 아래로 1번 조작하여 Z를 완성합니다.
따라서 11번 이동시켜 "JAZ"를 만들 수 있고, 이때가 최소 이동입니다.
만들고자 하는 이름 name이 매개변수로 주어질 때, 이름에 대해 조이스틱 조작 횟수의 최솟값을 return 하도록 solution 함수를 만드세요.

제한 사항
name은 알파벳 대문자로만 이루어져 있습니다.
name의 길이는 1 이상 20 이하입니다.
입출력 예
name	return
"JEROEN"	56
"JAN"	23 */


class Solution {
    public int solution(String name) {
        int answer = 0; // 조이스틱 조작 횟수
        int name_length = name.length();
        int move = name_length - 1; // 최소 이동 횟수 초기화

        for (int i = 0; i < name_length; i++) {
            char ch = name.charAt(i);
            // 각 문자를 A로 바꾸는 데 필요한 최소 횟수
            answer += Math.min(ch - 'A', 'Z' - ch + 1);

            // 'A'가 연속되는 부분을 건너뛰기 위한 코드
            int idx = i + 1;
            while (idx < name_length && name.charAt(idx) == 'A') {
                idx++;
            }

            // 오른쪽으로 가는 경우
            move = Math.min(move, i * 2 + name_length - idx);
            // 왼쪽으로 가는 경우
            move = Math.min(move, (name_length - idx) * 2 + i);
        }

        // 최종적으로 문자 바꾸는 횟수와 이동 횟수를 더한 값 반환
        return answer + move;
    }
}
