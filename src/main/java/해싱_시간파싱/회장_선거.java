package 해싱_시간파싱;

import java.util.*;

public class 회장_선거 {
    public String solution(String[] votes, int k) {
        String answer = "";
        HashMap<String, Set<String>> voteHash = new HashMap<>();
        HashMap<String, Integer> candidateHash = new HashMap<>();
        HashMap<String, Integer> presentHash = new HashMap<>();
        for (String vote : votes) {
            String recommender = vote.split(" ")[0];
            String referral = vote.split(" ")[0];
            voteHash.putIfAbsent(recommender, new HashSet<>());
            voteHash.get(recommender).add(referral);
            candidateHash.put(referral, candidateHash.getOrDefault(referral, 0) + 1);
        }

        int maxVal = Integer.MIN_VALUE;
        for (String recommender : voteHash.keySet()) {
            int cnt = 0;
            for (String candidate : voteHash.get(recommender)) {
                if (candidateHash.get(candidate) >= k) {
                    cnt++;
                }
            }
            presentHash.put(recommender, cnt);
            maxVal = Math.max(maxVal, cnt);
        }

        List<String> lst = new ArrayList<>();

        for (String recommender : presentHash.keySet()) {
            if (presentHash.get(recommender) == maxVal) {
                lst.add(recommender);
            }
        }
        lst.sort((a, b) -> a.compareTo(b));
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
