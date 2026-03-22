package br.furb.order;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.furb.problema02.enums.OrderTypeEnum;
import br.furb.problema02.factories.OrderTypeFactory;
import br.furb.problema02.order.IOrderType;

class OrderTypeBuyTest {

	@Test
    void shouldReturnCorrectType() {
		IOrderType order = OrderTypeFactory.CreateOrder(
                "Joaquim",
                new BigDecimal("24.00"),
                OrderTypeEnum.BUY
        );

        assertEquals(OrderTypeEnum.BUY, order.getOrderType());
    }

    @Test
    void shouldReturnCorrectData() {
    	IOrderType order = OrderTypeFactory.CreateOrder(
                "Joaquim",
                new BigDecimal("25.00"),
                OrderTypeEnum.BUY
        );

        assertEquals("Joaquim", order.getInvestorName());
        assertEquals(new BigDecimal("25.00"), order.getOrderValue());
    }

}
