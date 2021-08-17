package hk.com.nexify.entity.cmn.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class NafFilter {

    //1. if fieldName is set the filter will be a single filter (subfilters will be ignored)
    //2. if fieldName = null and subfilters set the filter is the container of the subfilters
    private String fieldName;
    private NafFilterOperator operator = NafFilterOperator.EQUAL;
    private Object value;

    //for multiple value when operator = IN
    private List<Object> values;

    private List<NafFilter> subFilters;
    //default and
    private boolean orSubFilter;

    public NafFilter() {
    }

    public NafFilter(String fieldName, NafFilterOperator oper) {
        this.fieldName = fieldName;
        this.operator = oper;
    }

    public NafFilter(String fieldName, NafFilterOperator oper, Object value) {
        this.fieldName = fieldName;
        this.operator = oper;
        this.value = value;
    }

    public NafFilter(String fieldName, NafFilterOperator oper, List<Object> values) {
        this.fieldName = fieldName;
        this.operator = oper;
        this.values = values;
    }

    // public NafFilter(String fieldName, String operCod, Object value) {
    // this.fieldName = fieldName;
    // this.operCod = operCod;
    // this.value = value;
    // }
    public NafFilter(List<NafFilter> subFilters, boolean isOr) {
        this.subFilters = subFilters;
        orSubFilter = isOr;
    }

    public NafFilter(NafFilter... filters) {

        subFilters = new ArrayList<NafFilter>();
        subFilters.addAll(Arrays.asList(filters));

    }

    public NafFilter(boolean isOr, NafFilter... filters) {
        orSubFilter = isOr;
        subFilters = new ArrayList<NafFilter>();
        subFilters.addAll(Arrays.asList(filters));

    }

    public void add(NafFilter filter) {
        add(filter, false);
    }

    public void add(NafFilter filter, boolean mergeFilter) {

        if (subFilters == null) {
            subFilters = new ArrayList<NafFilter>();
        }

        if(!mergeFilter){
             subFilters.add(filter);
        }
        else if (filter.getSubFilters() != null) {
            if (filter.isOrSubFilter()) {
                subFilters.add(filter);
            } else {
                for (NafFilter f : filter.getSubFilters()) {
                    add(f);
                }
            }
        } else if (filter.getFieldName() != null && filter.getOperator() != null) {
            subFilters.add(filter);
        }
    }

    public void add(List<NafFilter> filters) {
        if (subFilters == null) {
            subFilters = new ArrayList<NafFilter>();
        }
        for (NafFilter f : filters) {
            add(f);
        }
    }

    public void add(NafFilter... filters) {
        if (subFilters == null) {
            subFilters = new ArrayList<NafFilter>();
        }
        for (NafFilter f : filters) {
            add(f);
        }
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public boolean isOrSubFilter() {
        return orSubFilter;
    }

    public void setOrSubFilter(boolean orSubFilter) {
        this.orSubFilter = orSubFilter;
    }

    public List<NafFilter> getSubFilters() {
        return subFilters;
    }

    public void setSubFilters(List<NafFilter> subFilters) {
        this.subFilters = subFilters;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public NafFilterOperator getOperator() {
        return operator;
    }

    public void setOperator(NafFilterOperator operator) {
        this.operator = operator;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    @Override
    public String toString() {

        if (subFilters != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("Sub Filter - Or Filter:").append(isOrSubFilter()).append(System.getProperty("line.separator"));
            for (NafFilter f : subFilters) {
                sb.append(f.toString()).append(System.getProperty("line.separator"));
            }
            return sb.toString();
        }
        return fieldName + " " + (operator == null ? "" : operator.getCode()) + " " + value;
    }
}
