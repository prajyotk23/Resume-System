package com.resumesystem.Test;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LombokCheck {
    private String message;

    public static void main(String[] args) {
        LombokCheck test = new LombokCheck();
        test.setMessage("Lombok is working!");
        System.out.println(test.getMessage());
    }
}

