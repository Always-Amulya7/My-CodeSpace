class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        int MOD = 105;                    
        int[] result = new int[numberOfUsers];
        int[] timeline = new int[200];          
        int timelineSize = 0;
        int allMentions = 0;                 

        for (List<String> event : events) {
            final int timestamp = parseInt(event.get(1));
            String payload = event.get(2);

            if (event.get(0).equals("MESSAGE")) {
                if (payload.equals("ALL")) {
                    allMentions++;
                } else if (payload.equals("HERE")) {
                    timeline[timelineSize++] = timestamp * MOD + (MOD - 1);  
                } else {
                    int i = 0;
                    while (true) {
                        int spacePos = payload.indexOf(' ', i);
                        int end = (spacePos == -1) ? payload.length() : spacePos;
                        int userId = parseId(payload, i + 2, end);
                        result[userId]++;
                        
                        if (spacePos == -1) break;
                        i = spacePos + 1;
                    }
                }
            } 
            else { 
                int userId = parseInt(payload);
                timeline[timelineSize++] = timestamp * MOD + userId + 1;        
                timeline[timelineSize++] = (timestamp + 60) * MOD;              
            }
        }
        Arrays.sort(timeline, 0, timelineSize);

        int[] offlineUsers = new int[200];
        int offlineStart = 0;
        int offlineEnd = 0;

        for (int i = 0; i < timelineSize; i++) {
            int code = timeline[i] % MOD;
            int time = timeline[i] / MOD;

            if (code == 0) {
                offlineStart++;
            } 
            else if (code == MOD - 1) {
                allMentions++;
                for (int j = offlineStart; j < offlineEnd; j++) {
                    result[offlineUsers[j]]--;
                }
            } 
            else {
                offlineUsers[offlineEnd++] = code - 1;
            }
        }

        if (allMentions > 0) {
            for (int i = 0; i < numberOfUsers; i++) {
                result[i] += allMentions;
            }
        }
        return result;
    }

    private static final int parseInt(String s) {
        return parseInt(s, 0, s.length());
    }

    private static final int parseInt(String ids, int start, int end) {
        int r = ids.charAt(start) - '0';
        for (int i = start + 1; i < end; i++) {
            r = r * 10 + ids.charAt(i) - '0';
        }
        return r;
    }

    private int parseId(String s, int start, int end) {
        int num = 0;
        for (int i = start; i < end; i++) {
            num = num * 10 + (s.charAt(i) - '0');
        }
        return num;
    }
}