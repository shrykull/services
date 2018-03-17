package net.shrye.services.decide

import spock.lang.Specification
import spock.lang.Unroll

class DecideOutputTest extends Specification {
    @Unroll
    def "returns #out if #type.toString() and question #question"(String out, DecideType type, String question) {
        setup:
        DecideInput anyInput = new DecideInput(question: question, identity: 'somebody')

        expect:
        out == new DecideOutput(type: type, input: anyInput, payload: question).toString()

        where:
        out      || type               || question
        'any'     | DecideType.SPECIFIC | 'any'
        'some'    | DecideType.SPECIFIC | 'some'
        'dafür'   | DecideType.PRO      | 'any'
        'dagegen' | DecideType.CONTRA   | 'any'
    }
}
