package net.shrye.services.decide

import org.springframework.lang.NonNull
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping('/')
class DecideController {

    @GetMapping
    static String decide(
            @NonNull @RequestHeader(value = "User-Agent") String userAgent) {
            return Decider.decide(new DecideInput(identity:userAgent))
    }

    @PostMapping(consumes = 'application/json', produces = 'application/json')
    static DecideOutput decide(@NonNull @Validated @RequestBody DecideInput input) {
        return Decider.decide(input)
    }
}