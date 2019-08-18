package com.github.daggerok.immutables.valueobject;

import lombok.extern.log4j.Log4j2;
import org.immutables.value.Value;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.UUID;

/**
 *
 */
@Log4j2
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ValueImmutableTest {

    @Value.Immutable
    interface MyCommand1 {
        @Value.Parameter UUID aggregateId();
        @Value.Parameter String username();
    }

    @Test
    void builder_true_copy_true() {
        MyCommand1 myCommand11 = ImmutableMyCommand1.builder()
                                                   .aggregateId(UUID.randomUUID())
                                                   .username("bob")
                                                   .build();
        log.info("my cmd 1.1: {}", myCommand11);

        MyCommand1 myCommand12 = ImmutableMyCommand1.copyOf(myCommand11)
                                                   .withUsername("molly");
        log.info("my cmd 1.1: {}", myCommand12);
    }

    @Value.Immutable(builder = false, copy = false)
    interface MyCommand2 {
        @Value.Parameter UUID aggregateId();
        @Value.Parameter String username();
    }

    @Test
    void builder_false_copy_false() {
        MyCommand2 myCommand2 = ImmutableMyCommand2.of(UUID.randomUUID(), "bob");
        log.info("my cmd 2: {}", myCommand2);
    }
}
