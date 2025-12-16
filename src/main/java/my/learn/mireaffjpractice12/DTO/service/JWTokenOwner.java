package my.learn.mireaffjpractice12.DTO.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JWTokenOwner {

    private final Long userId;
    private final String username;

}
