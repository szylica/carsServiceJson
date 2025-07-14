package org.szylica.service.records;

import java.math.BigDecimal;

public record PriceStatistics(BigDecimal top, BigDecimal average, BigDecimal least) {
}
