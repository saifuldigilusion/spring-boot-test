package hk.com.nexify.dao.cmn.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.googlecode.genericdao.search.Filter;

import hk.com.nexify.cmn.util.CmnUtils;
import hk.com.nexify.entity.cmn.pojo.NafFilter;
import hk.com.nexify.entity.cmn.pojo.NafFilterOperator;

public final class FilterInfoUtils {

	private static final Logger log = LoggerFactory.getLogger(FilterInfoUtils.class);

	public static void main(String args[]) {

		// single filter
		NafFilter singleFilter = new NafFilter("testField", NafFilterOperator.EQUAL, "val1");

		// sub filter list
		NafFilter sub1 = new NafFilter("testField1", NafFilterOperator.EQUAL, "val1");
		NafFilter sub2 = new NafFilter("testField2", NafFilterOperator.EQUAL, "val2");
		NafFilter sub3 = new NafFilter("testField3", NafFilterOperator.EQUAL, "val3");
		List<NafFilter> subList = new ArrayList<NafFilter>();
		subList.add(sub3);
		subList.add(sub2);
		subList.add(sub1);
		NafFilter subAndFilter = new NafFilter(subList, false);

		// Nested And Filetr
		NafFilter nestedFilter = new NafFilter();
		nestedFilter.setOrSubFilter(true);
		nestedFilter.add(singleFilter, subAndFilter);

		// Filter f = FilterInfoUtils.convert2Filter(subAndFilter);
		toGenericDaoFilter(nestedFilter);

	}

	public static Filter toGenericDaoFilter(NafFilter fi) {
		Filter f = null;

		if (fi != null) {
			if (CmnUtils.isNotEmpty(fi.getFieldName())) {
				f = convert2Filter(fi);
			} else if (fi.getSubFilters() != null && f == null) {
				f = toGenericDaoFilter(fi.getSubFilters(), fi.isOrSubFilter());
			}
		}

		return f;
	}

	public static Filter toGenericDaoFilter(List<NafFilter> fiList) {
		return toGenericDaoFilter(fiList, false);
	}

	public static Filter toGenericDaoFilter(List<NafFilter> fiList, boolean isOr) {

		Filter[] filters = new Filter[fiList.size()];
		int count = 0;
		for (NafFilter fi : fiList) {
			filters[count] = toGenericDaoFilter(fi);
			count++;
		}
		if (isOr) {
			return Filter.or(filters);
		} else {
			return Filter.and(filters);
		}

		/*
		 * List<Filter> filterList = new ArrayList<Filter>(); for (NafFilter fi : fiList) { filterList.add(toGenericDaoFilter(fi)); } Filter[] filters = new
		 * Filter[filterList.size()]; filters = (Filter[]) filterList.toArray(filters); if (isOr) { return Filter.or(filters); } else { return Filter.and(filters); }
		 */
	}

	private static Filter convert2Filter(NafFilter fi) {

		Filter f = null;

		switch (fi.getOperator()) {
		case EQUAL:
			f = Filter.equal(fi.getFieldName(), fi.getValue());
                      
			break;
		case NOT_EQUAL:
			f = Filter.notEqual(fi.getFieldName(), fi.getValue());
			break;
		case LESS_THAN:
			f = Filter.lessThan(fi.getFieldName(), fi.getValue());
			break;
		case GREATER_THAN:
			f = Filter.greaterThan(fi.getFieldName(), fi.getValue());
			break;
		case LESS_OR_EQUAL:
			f = Filter.lessOrEqual(fi.getFieldName(), fi.getValue());
			break;
		case GREATER_OR_EQUAL:
			f = Filter.greaterOrEqual(fi.getFieldName(), fi.getValue());
			break;
		case LIKE:
			f = Filter.like(fi.getFieldName(), fi.getValue().toString());
			break;
		case I_LIKE:
			f = Filter.ilike(fi.getFieldName(), fi.getValue().toString());
			break;
		case IS_NULL:
			f = Filter.isNull(fi.getFieldName());
			break;
		case IS_NOT_NULL:
			f = Filter.isNotNull(fi.getFieldName());
			break;
		case IS_EMPTY:
			f = Filter.isEmpty(fi.getFieldName());
			break;
		case IS_NOT_EMPTY:
			f = Filter.isNotEmpty(fi.getFieldName());
			break;
		case IN:
			// if only 1 value in the List may need special logic handle data type
			if (fi.getValues() != null && fi.getValues().size() > 0) {
				f = Filter.in(fi.getFieldName(), fi.getValues());
			} else {
				f = Filter.in(fi.getFieldName(), Arrays.asList(fi.getValue().toString().split("\\s*,\\s*")));
			}
			break;
		case NOT_IN:
			f = Filter.notIn(fi.getFieldName(), fi.getValue().toString());
			break;
		default:
			break;
		}

		return f;
	}
}
