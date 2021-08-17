package hk.com.nexify.cmn.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.activation.DataHandler;

import org.apache.commons.beanutils.PropertyUtils;

import hk.com.nexify.cmn.constant.CmnConstants;

public final class CmnUtils {

	public static boolean isNotEmpty(String obj) {
		return !isEmpty(obj);
	}

	public static boolean isEmpty(String str) {
		return str == null || str.isEmpty() || str.trim().length() == 0;
	}

	public static boolean isEmpty(@SuppressWarnings("rawtypes") Collection list) {
		if (list == null || list.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(@SuppressWarnings("rawtypes") Map map) {

		if (map == null || map.isEmpty()) {
			return true;
		}
		return false;
	}

	public static byte[] hexString2Bytes(String str) {

		int len = str.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
		}
		return data;
	}

	public static String bytes2HexString(byte[] bytes) {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		// System.out.println("Hex format : " + sb.toString());
		return sb.toString();
	}

	public static File dataHandler2TmpFile(DataHandler handler) throws IOException {

		File tmpFile = File.createTempFile("dataHandler2TmpFile-", ".tmp");
		OutputStream os = new FileOutputStream(tmpFile);
		handler.writeTo(os);
		return tmpFile;

	}

	/**
	 * Find Arguments mapped name which is null
	 *
	 * @param names
	 * @param objects
	 * @return
	 */
	public static String getNullOrEmptyArgs(String[] names, Object... objects) {
		if (names.length == objects.length) {
			StringBuilder sb = new StringBuilder();
			int i = 0;
			for (Object o : objects) {
				if (isEmptyObj(o)) {
					if (sb.length() > 0) {
						sb.append(",");
					}
					sb.append(names[i]);
				}
				i++;
			}
			if (sb.length() > 0) {
				return sb.toString();
			}
		}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmptyObj(Object o) {
		if (o == null) {
			return true;
		} else if (o instanceof String) {
			String str = (String) o;
			return isEmpty(str);
		} else if (o instanceof List) {
			return isEmpty((List) o);
		} else if (o instanceof Map) {
			return isEmpty((Map) o);
		}
		return false;
	}

	public static Date getDateWithDayStartTime(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0); // anything 0 - 23
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date getDateWithDayEndTime(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23); // anything 0 - 23
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date trimTime(Date lastSyncDate) {
		Calendar c = new GregorianCalendar();
		c.setTime(lastSyncDate);
		c.set(Calendar.HOUR_OF_DAY, 0); // anything 0 - 23
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date getToday() {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.HOUR_OF_DAY, 0); // anything 0 - 23
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date getTodayOffset(int type, int add) {
		Calendar c = new GregorianCalendar();
		c.set(Calendar.HOUR_OF_DAY, 0); // anything 0 - 23
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(type, add);
		return c.getTime();
	}

	public static Date getDateOffset(Date d, int type, int add) {
		Calendar c = new GregorianCalendar();
		c.setTime(d);
		c.set(Calendar.HOUR_OF_DAY, 0); // anything 0 - 23
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.add(type, add);
		return c.getTime();
	}

	public static String getDateStr(Date d, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(d);
	}

	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}

	@SuppressWarnings("rawtypes")
	public static Object String2Obj(String strVal, Class propClazz, String datePattern) {

		Object val = null;
		if (propClazz.equals(String.class)) {
			val = strVal.trim();
		} else if (propClazz.equals(Long.class)) {
			val = Long.parseLong(strVal);
		} else if (propClazz.equals(Integer.class)) {
			val = Integer.parseInt(strVal);
		} else if (propClazz.equals(Date.class)) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
				val = sdf.parse(strVal);
			} catch (ParseException ex) {
			}
		} else if (propClazz.equals(Boolean.class)) {
			if ("1".equals(strVal) || "true".equals(strVal.toLowerCase())) {
				val = true;
			}
			val = false;
		}
		return val;
	}

	public static String getObjArgs(Object obj) {
		StringBuilder sb = new StringBuilder();

		if (obj == null) {
			sb.append("[NULL]");
			return sb.toString();
		} else if (obj.getClass().isPrimitive() || isAppendableClass(obj)) {
			sb.append(obj.toString());
			return sb.toString();
		} else if (isAppendableAware(obj)) {
			sb.append(((NafAppendableAware) obj).getAppendableString());
			return sb.toString();
		} else if (obj instanceof List) {
			List c = (List) obj;
			for (Object co : c) {
				sb.append(getObjArgs(co)).append(",");
			}
		}

		for (Field field : obj.getClass().getDeclaredFields()) {
			String name = field.getName();

			try {

				Object prop = PropertyUtils.getProperty(obj, name);
				// log.debug("getObjArgs name:" + name + " ,prop:" + prop + "," +
				// prop.getClass() + "," + prop.getClass().isPrimitive());
				sb.append(name).append(":");
				if (prop.getClass().isPrimitive()) {
					sb.append(prop.toString()).append(";");
				} else if (isAppendableClass(prop)) {
					sb.append(prop.toString()).append(";");
				} else if (isAppendableAware(obj)) {
					sb.append(((NafAppendableAware) obj).getAppendableString());
				} else {
					sb.append(obj.getClass().getSimpleName()).append(";");
					// may lead to exception
					/*
					 * sb.append(obj.getClass()).append(":") .append(getObjArgs(obj)).append(";");
					 */
				}
			} catch (Exception ex) {
			}
		}

		return sb.toString();
	}

	public static String convertFirstLetter2LowerCase(String str) {
		return Character.toLowerCase(str.charAt(0)) + str.substring(1);
	}

	public static <T extends Object> List<T> trimPaging(List<T> objs, int pageNo, int pageSize) {
		int startIndex = -1;
		int endIndex = -1;

		if (pageNo > 0) {
			startIndex = (pageNo - 1) * pageSize + 1;
			endIndex = pageNo * pageSize;
		}

		List<T> pagedObjects = new ArrayList<T>();

		int i = 1;
		for (T obj : objs) {
			if (startIndex <= 0 || (i >= startIndex && i <= endIndex)) {
				pagedObjects.add(obj);
			}

			i++;
		}

		return pagedObjects;
	}

	private static boolean isAppendableClass(Object prop) {
		if (prop instanceof String) {
			return true;
		} else if (prop instanceof Integer) {
			return true;
		} else if (prop instanceof Double) {
			return true;
		} else if (prop instanceof Float) {
			return true;
		} else if (prop instanceof StringBuilder) {
			return true;
		} else if (prop instanceof Long) {
			return true;
		} else if (prop instanceof Byte) {
			return true;
		} else if (prop instanceof Boolean) {
			return true;
		} else if (prop instanceof Date) {
			return true;
		} else if (prop instanceof NafCachableParam) {
			return true;
		}

		return false;
	}

	private static boolean isAppendableAware(Object obj) {
		if (obj instanceof NafAppendableAware) {
			return true;
		}

		return false;
	}

	public static boolean isEqual(String s1, String s2) {

		if (s1 == null && s2 == null) {
			return true;
		}
		if (s1 == null || s2 == null) {
			return false;
		}
		return s1.equals(s2);

	}

	public static boolean toBoolean(String str) {
		if (str == null) {
			return false;
		}

		str = str.toLowerCase();
		if ("y".equals(str) || "true".equals(str)) {
			return true;
		}

		return false;
	}

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static boolean isVaildEmail(String email) {
		if (isEmpty(EMAIL_PATTERN)) {
			return false;
		}

		return email.matches(EMAIL_PATTERN);

	}

	public static boolean isTrue(Boolean b) {
		if (b == null || b == false) {
			return false;
		}
		return true;
	}

	public static String replaceChars(String str, char[] char2Replace, char replaceVal) {
		String result = str;

		for (char c : char2Replace) {
			result = result.replace(c, replaceVal);
		}

		return result;
	}

	public static Date addDate(Date date, int dayToAdd, boolean dateOnly, boolean skipWeekendAndHoliday) {
		return addDate(date, dayToAdd, dateOnly, skipWeekendAndHoliday, null);
	}

	public static Date addDate(Date date, int dayToAdd, boolean dateOnly, boolean skipWeekendAndHoliday,
			List<Date> holidays) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int numDay = Math.abs(dayToAdd);
		boolean isMinus = false;
		if (dayToAdd < 0) {
			isMinus = true;
		}

		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		if (skipWeekendAndHoliday) {
			for (int i = 0; i < numDay;) {
				if (isMinus) {
					cal.add(Calendar.DAY_OF_MONTH, -1);
				} else {
					cal.add(Calendar.DAY_OF_MONTH, 1);
				}

				int weekDay = cal.get(Calendar.DAY_OF_WEEK);
				if (weekDay != Calendar.SUNDAY && weekDay != Calendar.SATURDAY) {
					if (holidays == null || !holidays.contains(cal.getTime())) {
						i++;
					}
				}
			}
		} else {
			cal.add(Calendar.DATE, dayToAdd);
		}
		return cal.getTime();
	}

	public static boolean isActive(String status) {
		return CmnConstants.CMN_STS_ACTIVE.equals(status);
	}

}
