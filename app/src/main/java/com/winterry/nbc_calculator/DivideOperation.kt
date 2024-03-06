package com.winterry.nbc_calculator

import java.math.BigDecimal

class DivideOperation: AbstractOperation() {
    override fun operate(lValue: BigDecimal, rValue: BigDecimal): BigDecimal {
        return lValue / rValue
    }
}