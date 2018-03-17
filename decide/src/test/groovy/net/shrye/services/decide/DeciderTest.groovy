package net.shrye.services.decide

import spock.lang.Specification
import spock.lang.Unroll

class DeciderTest extends Specification {
    def "decide works without question"() {
        when:
        DecideOutput out = Decider.decide(new DecideInput(identity: 'any'))

        then:
        noExceptionThrown()
        out.type != DecideType.SPECIFIC
        out.toString().startsWith('da')
    }

    def "decide dafür/dagegen bei single match"() {
        def identity1 = '@npx#5201'
        def identity2 = '@someone#124'

        def question = 'etwas'

        def firstDecision = Decider.decide(question, identity1)
        def secondDecision = Decider.decide(question, identity2)

        expect:
        firstDecision == firstDecision
        firstDecision != secondDecision
        secondDecision == secondDecision

        "gegen $question" == firstDecision || "für $question" == firstDecision
        "gegen $question" == secondDecision || "für $question" == secondDecision
    }

    def "roberts special case"() {
        expect:
        ['für a', 'für b'].contains(Decider.decide("a b", "robert3388"))
    }

    @Unroll
    def "!decide #question"(String question) {
        def identity1 = '@npx#5201'
        def identity2 = '@someone#123'

        def firstDecision = Decider.decide(question, identity1)
        def secondDecision = Decider.decide(question, identity2)

        expect:
        firstDecision == firstDecision
        firstDecision != secondDecision
        secondDecision == secondDecision

        and: 'assert no lists were accidentially toString()ed'
        !firstDecision.contains('[')
        !secondDecision.contains('[')

        and: 'multiple possible answers should not cause "against"'
        !firstDecision.contains('gegen')
        !secondDecision.contains('gegen')

        and: 'payload should be trimmed'
        firstDecision.trim() == firstDecision
        secondDecision.trim() == secondDecision

        where:
        question << [
                '"bla fasel" "blubb" "mehr bla"',
                '"bla fasel" blubb "mehr bla"',
                '"bla fasel" "blubb" mehr bla',
                'bla fasel "blubb" "mehr blubb"',
                '"bla fasel blubb mehr bla'
        ]
    }
}
