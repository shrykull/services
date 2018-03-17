package net.shrye.services.decide

enum DecideType {
    SPECIFIC(''),
    PRO('dafür'),
    CONTRA('dagegen')

    private final String name

    DecideType(String s) {
        name = s
    }

    String toString() {
        return this.name
    }
}
