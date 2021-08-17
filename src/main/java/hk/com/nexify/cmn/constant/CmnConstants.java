/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hk.com.nexify.cmn.constant;

/**
 *
 * @author Administrator
 */
public final class CmnConstants {

    public static final String CMN_STS_PENDING = "P";
    public static final String CMN_STS_ACTIVE = "A";
    public static final String CMN_STS_EXPIRED = "E";
    public static final String CMN_STS_DELETED = "D";

    public static final String CMN_STS_COMPLETE = "C";
    public static final String CMN_STS_IN_PROGRESS = "I";
    public static final String CMN_STS_FAILED = "F";

    public static final String CMN_ACTION_CREATE = "C";
    public static final String CMN_ACTION_UPDATE = "U";
    public static final String CMN_ACTION_DELETE = "D";

    public static final String SEC_CLASS_UNCLASS = "UC";
    public static final String SEC_CLASS_RESTRICTED = "R";
    public static final String SEC_CLASS_CONFIDENTIAL = "C";

    public static final String CMN_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String CMN_DATE_FORMAT = "yyyy-MM-dd";
    public static final String CMN_TIMEZONE = "GMT+8";

    public static final String CMN_TYPE_PUBLIC = "P";
    public static final String CMN_TYPE_USER = "U";

    public static final String CMN_ATCH_TYPE_DOC = "D";
    public static final String CMN_ATCH_TYPE_RECORD = "R";
    
    public static final String SMTP_SEP = ",";

    public static final char[] FILENAME_UNSUPPORT_CHAR = {'\\', '/', ':', '*', '?', '"', '<', '>', '|'};
}
