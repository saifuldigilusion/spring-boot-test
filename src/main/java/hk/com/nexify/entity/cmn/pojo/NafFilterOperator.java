package hk.com.nexify.entity.cmn.pojo;

public enum NafFilterOperator {

	EQUAL("eq"),
	LESS_THAN("lt"),
	GREATER_THAN("gt"),
	LESS_OR_EQUAL("le"),
	GREATER_OR_EQUAL("ge"),
	IN("in"),
	NOT_IN("not in"),
	LIKE("like"),
	I_LIKE("ilike"),  //ignore case
	NOT_EQUAL("not eq"),
	IS_NULL("is null"),
	IS_NOT_NULL("is not null"),
	IS_EMPTY("is empty"),
	IS_NOT_EMPTY("is not empty");

	private String code;

	public String getCode() {
		return code;
	}

	private NafFilterOperator(String code) {
		this.code = code;
	}

	public static NafFilterOperator getOper(String value) {
		NafFilterOperator[] opers = NafFilterOperator.values();
		for (NafFilterOperator op : opers) {
			if (op.getCode().equals(value)) {
				return op;
			}
		}

		return null;
	}
};
