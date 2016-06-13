package com.cooper;

import com.cooper.creator.enums.CarryableType;
import com.cooper.creator.model.Carryable;

public class Mocks {

    public static Carryable getMockCarryable() {

        return new Carryable() {

            @Override public CarryableType getType() {
                return CarryableType.WEAPON;
            }

            @Override public String getIdentifier() {
                return "Mock Carryable";
            }
        };
    }
}
