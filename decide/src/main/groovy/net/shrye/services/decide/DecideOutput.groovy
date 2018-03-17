package net.shrye.services.decide

class DecideOutput {
    DecideInput input
    DecideType type
    String payload

    String toString() {
        if (type == DecideType.SPECIFIC) return payload
        else return type
    }
}
