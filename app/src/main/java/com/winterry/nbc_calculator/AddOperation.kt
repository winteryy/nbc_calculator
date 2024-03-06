package com.winterry.nbc_calculator

import java.math.BigDecimal

class AddOperation: AbstractOperation() {
    override fun operate(lValue: BigDecimal, rValue: BigDecimal): BigDecimal? {
        return try { lValue + rValue } catch(e: Exception) { null }
    }
}