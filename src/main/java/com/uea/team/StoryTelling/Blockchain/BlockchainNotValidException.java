package com.uea.team.StoryTelling.Blockchain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class BlockchainNotValidException extends Exception {

    public BlockchainNotValidException(String errorMessage) {
        super(errorMessage);
    }
}
