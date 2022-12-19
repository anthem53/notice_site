package com.test.springboot.web.Dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloRespoenseDtoTest {

    @Test
    public void lombok_function_test(){
        String name = "test";
        int amount = 1000;

        HelloResponseDto dto = new HelloResponseDto(name,amount);

        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);

    }

}
