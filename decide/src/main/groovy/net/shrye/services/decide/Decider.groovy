package net.shrye.services.decide

import java.util.regex.Matcher

import static java.time.LocalDateTime.now
import static java.time.format.DateTimeFormatter.ofPattern

class Decider {
    static DecideOutput decide(DecideInput input) {
        if (!input.question) {
            input.question = ''
        }
        Matcher r = (input.question =~ /(".+?"|(?<!").+?(?!"))(?:\s+|$)/)
        long c = asciicount(input.question) + asciicount(now().format(ofPattern('yyyy/MM/dd')))
        if (r.size() >= 2) {
            return new DecideOutput(
                    input: input,
                    type: DecideType.SPECIFIC,
                    payload: r[(c + safeMod(asciicount(input.identity), 100)) % r.size() as int][1]
            )
        }
        return new DecideOutput(input: input,
                type: DecideType.values()[safeMod(c, safeMod(asciicount(input.identity), 100)) % 2 + 1 as int],
                payload: ''
        )
    }

    static String decide(String input, String identity) {
        DecideOutput out = decide(new DecideInput(question: input, identity: identity))
        switch (out.type) {
            case DecideType.PRO:
                return "für $input"
            case DecideType.CONTRA:
                return "gegen $input"
            default:
                return "für ${out.payload}"
        }
    }

    static private int asciicount(String t) {
        return Arrays.stream(t.toCharArray()).mapToInt({ i -> (int) i }).sum()
    }

    static private long safeMod(long a, long b) {
        if (b == 0) return 0
        return a % b
    }
}