package br.furb.problema02.enums;

public enum OrderTypeEnum {
	BUY {
		@Override
		public OrderTypeEnum opposite() {
			return SELL;
		}
	},
	SELL {
		@Override
		public OrderTypeEnum opposite() {
			return BUY;
		}
	};

	public abstract OrderTypeEnum opposite();
}
