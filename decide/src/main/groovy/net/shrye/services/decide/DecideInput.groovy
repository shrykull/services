package net.shrye.services.decide

import org.springframework.lang.NonNull
import org.springframework.lang.Nullable

class DecideInput {

    @NonNull
    String identity

    @Nullable
    String question
}
