package com.winterry.nbc_calculator

import java.math.BigDecimal

abstract class AbstractOperation {
    abstract fun operate(lValue: BigDecimal, rValue: BigDecimal): BigDecimal?
}