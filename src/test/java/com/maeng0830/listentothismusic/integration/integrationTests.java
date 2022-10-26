package com.maeng0830.listentothismusic.integration;

import com.maeng0830.listentothismusic.ListentothismusicApplicationTests;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("local test!!")
public class integrationTests extends ListentothismusicApplicationTests {

    @Test
    void sum() {
        // given
        int a = 1;
        int b = 2;

        // when
        int sum = a + b;

        // then
        Assertions.assertThat(a + b).isEqualTo(sum);
    }

}
