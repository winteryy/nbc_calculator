package com.winterry.nbc_calculator

import java.math.BigDecimal
import java.math.RoundingMode

class DivideOperation: AbstractOperation() {
    override fun operate(lValue: BigDecimal, rValue: BigDecimal): BigDecimal? {
        return try { lValue.divide(rValue, 6, RoundingMode.HALF_EVEN) } catch(e: Exception) { null }
    }
}