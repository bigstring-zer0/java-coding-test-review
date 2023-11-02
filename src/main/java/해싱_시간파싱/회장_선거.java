package 해싱_시간파싱;

import java.util.*;

public class 회장_선거 {
    public String solution(String[] votes, int k) {
        String answer;
        // 추천인, 추천인이 추천한 사람들을 순서로 키와 값을 가지는 hashmap 초기화.
        HashMap<String, Set<String>> voteHash = new HashMap<>();
        // 어떤 사람이 몇번 회장 후보로 추천 받앗는지를 계산하기 위한 hashmap 초기화.
        HashMap<String, Integer> candidateHash = new HashMap<>();
        // 어떤 사람이 k 값에 의해서 최종 회장 후보로 선정되어서 해당 후보들을 추천한 사람들이 각각 몇개의 선물을 받게될지를 계산하기 위한 hashmap 초기화.
        HashMap<String, Integer> presentHash = new HashMap<>();
        for (String vote : votes) {
            // 추천인과 추천받은 사람을 입력받은 votes를 순회하며 분리.
            String recommender = vote.split(" ")[0];
            String referral = vote.split(" ")[1];
            // 만약 해당 키값이 voteHash에 저장되있지 않으면 해당 함수를 실행. 그러니까 처음 한번만 해당 키값으로 hashset을 생성한다.
            voteHash.putIfAbsent(recommender, new HashSet<>());
            // 해당 사람이 추천한 학생을 저장.
            voteHash.get(recommender).add(referral);
            // 회장 후보로 추천받은 사람들의 빈도수를 계산한다.
            candidateHash.put(referral, candidateHash.getOrDefault(referral, 0) + 1);
        }

        int maxVal = Integer.MIN_VALUE;
        for (String recommender : voteHash.keySet()) {
            // 해당 추천인이 몇개의 선물을 받게될지 카운트하기 위한 변수 초기화.
            int cnt = 0;
            for (String candidate : voteHash.get(recommender)) {
                // 만약에 해당 추천인이 추천한 사람의 추천 득표수가 k 이상이라면 해당 추천인은 선물 1개를 받는다.
                if (candidateHash.get(candidate) >= k) {
                    cnt++;
                }
            }
            // 해당 추천인이 받게될 선물의 수를 presentHash에 저장.
            presentHash.put(recommender, cnt);
            // 선물을 가장 많이 받은 사람을 구하기 위한 전체 사람들 중에서 각각 받은 선물의 최대값 계산하기.
            maxVal = Math.max(maxVal, cnt);
        }

        List<String> lst = new ArrayList<>();
        // 선물을 받은 사람들 중에서 받은 선물의 갯수가 선물의 최대 갯수와 같다면 해당 학생을 리스트에 저장하기.
        for (String recommender : presentHash.keySet()) {
            if (presentHash.get(recommender) == maxVal) {
                lst.add(recommender);
            }
        }
        // 리스트에 저장된 이름을 사전순으로 빠른 이름부터 위치하도록 람다식을 활용하여 정렬하기.
        lst.sort((a, b) -> a.compareTo(b));
        // 해당 문제에서 요구하는 답이 여러명일 경우 사전순으로 가장 빠른 이름 하나를 answer에 저장하기.
        answer = lst.get(0);

        return answer;

    }
    public static void main(String[] args) {
        회장_선거 T = new 회장_선거();
        System.out.println(T.solution(new String[]{"john tom", "daniel luis", "john luis", "luis tom", "daniel tom", "luis john"}, 2));
        System.out.println(T.solution(new String[]{"john tom", "park luis", "john luis", "luis tom", "park tom", "luis john", "luis park", "park john", "john park", "tom john", "tom park", "tom luis"}, 2));
        System.out.println(T.solution(new String[]{"cody tom", "john tom", "cody luis", "daniel luis", "john luis", "luis tom", "daniel tom", "luis john"}, 2));
        System.out.println(T.solution(new String[]{"bob tom", "bob park", "park bob", "luis park", "daniel luis", "luis bob", "park luis", "tom bob", "tom luis", "john park", "park john"}, 3));
    }
}
