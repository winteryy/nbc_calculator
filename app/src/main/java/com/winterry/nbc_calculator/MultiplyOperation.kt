package com.winterry.nbc_calculator

import java.math.BigDecimal

class MultiplyOperation: AbstractOperation() {
    override fun operate(lValue: BigDecimal, rValue: BigDecimal): BigDecimal {
        return lValue * rValue
    }
}