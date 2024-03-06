package com.winterry.nbc_calculator

import java.math.BigDecimal
import java.math.MathContext

class ModuloOperation: AbstractOperation() {
    override fun operate(lValue: BigDecimal, rValue: BigDecimal): BigDecimal? {
        return try { lValue.remainder(rValue, MathContext.DECIMAL128) } catch(e: Exception) { null }
    }
}