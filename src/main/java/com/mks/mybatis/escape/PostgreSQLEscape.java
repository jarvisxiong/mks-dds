package com.mks.mybatis.escape;

/**
 * 基于PostgreSQL数据库的转义方言。
 * 
 * <pre>
 *  SELECT * FROM TABLE
 *  WHERE
 *  NALE LIKE '%/%小%' ESCAPE '/';
 * </pre>
 *  
 */
public class PostgreSQLEscape implements Escape {

    @Override
    public String escape(String value) {
        StringBuffer sb = new StringBuffer(value.length());
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            for (char es : Escape.ESCAPE_CHAR) {
                if (c == es) {
                    sb.append("/");
                    sb.append(c);
                }
                else {
                    sb.append(c);
                }
                break;
            }
        }
        return sb.toString();
    }

    @Override
    public String unescape(String value) {
        return null;
    }

}
