//package com.bizvane.utils.tenant;
//
//import com.bizvane.utils.tokens.SysAccountPO;
//import com.bizvane.utils.tokens.TokenUtils;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.reflect.FieldUtils;
//import org.apache.ibatis.executor.statement.StatementHandler;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.plugin.*;
//import org.apache.ibatis.reflection.DefaultReflectorFactory;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.ibatis.reflection.ReflectorFactory;
//import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
//import org.apache.ibatis.reflection.factory.ObjectFactory;
//import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
//import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//import org.yaml.snakeyaml.Yaml;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.annotation.Annotation;
//import java.lang.reflect.Method;
//import java.sql.Connection;
//import java.util.ArrayList;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Properties;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
///**
// * @author Micro
// * @Title: ${file_name}
// * @Package com.bizvane.utils.tenant
// * @Description: todo
// * @date 2018/9/3 9:59
// */
//@Component
//@Intercepts({@Signature(method = "prepare", type = StatementHandler.class, args = {Connection.class, Integer.class})})
//public class TenantInterceptor implements Interceptor {
//
//    private static Logger logger = LoggerFactory.getLogger(TenantInterceptor.class);
//    private static final String START_KEY = " start ";
//    private static final String TOKEN_KEY = " token ";
//    private static final String END_KEY = " end ";
//    private static final String LOG_FORMAT_KEY = "{}:{}";
//    private static final String WHERE_KEY = " WHERE ";
//    private static final String LIMIT_KEY = " LIMIT ";
//    private String TENANT_KEY = " sys_company_id =  ";
//    private String COMMAND_TYPE = "SELECT";
//    private String TENANT_KEY_TEMP = " sys_company_id =  ";
//    private static final String MAPPED_STATEMENT = "delegate.mappedStatement";
//    private static final String IS_ENABLED_TENANT_INTERCEPTOR = "tenant.isEnabledTenantInterceptor";
//    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
//    private static final String REGEX_PATTERN = "\\bfrom\\b(\\s)*\\b(\\s)*.+?(\\r|\\n)*\\s*.*?\\b(inner|where|left|limit)\\b";
//    private static final String REGEX_EXISTS_PATTERN = "\\b(\\.)sys_company_id\\s*=\\s*";
//    private static final Pattern PATTERN = Pattern.compile(REGEX_PATTERN, Pattern.CASE_INSENSITIVE);
//    private static final Pattern EXISTS_PATTERN = Pattern.compile(REGEX_EXISTS_PATTERN, Pattern.CASE_INSENSITIVE);
//    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
//    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();
//    private ServletRequestAttributes SERVLET_REQUEST_ATTRIBUTES = null;
//    private long SYS_COMPANY_ID = 0L;
//    private String BOUND_SQL = "";
//    private String MATCHER_SQL = "";
//    private Matcher MATCHER = null;
//    private SysAccountPO SYS_ACCOUNT_PO = null;
//    private Yaml yaml = null;
//    private static final ArrayList<String> FIXED_PREFIX = new ArrayList<String>() {{
//        add("goods_");
//        add("order_");
//        add("t_");
//        add("zzz_");
//    }};
//
//    public TenantInterceptor() {
//        yaml = new Yaml();
//    }
//
//    @Override
//    public Object intercept(Invocation invocation) throws Throwable {
//        try {
//            if (invocation.getTarget() instanceof StatementHandler) {
//                StatementHandler handler = (StatementHandler) invocation.getTarget();
//                MetaObject metaStatementHandler = MetaObject.forObject(handler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
//                while (metaStatementHandler.hasGetter("h")) {
//                    Object object = metaStatementHandler.getValue("h");
//                    metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
//                }
//                while (metaStatementHandler.hasGetter("target")) {
//                    Object object = metaStatementHandler.getValue("target");
//                    metaStatementHandler = MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
//                }
//                BOUND_SQL = handler.getBoundSql().getSql();
//                MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue(MAPPED_STATEMENT);
//                if (mappedStatement.getSqlCommandType().name().equals(COMMAND_TYPE) && !checkExistsTenant())
//                    if (Objects.equals(getAnnotation(mappedStatement.getId()), null) && checkEnabled()) {
//                        SERVLET_REQUEST_ATTRIBUTES = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//                        try {
//                            SYS_ACCOUNT_PO = TokenUtils.getStageUser((HttpServletRequest) SERVLET_REQUEST_ATTRIBUTES.getRequest());
//                        } catch (Exception ex) {
//                            SYS_ACCOUNT_PO = new SysAccountPO();
//                            ex.printStackTrace();
//                        }
//                        /*if(SYS_ACCOUNT_PO==null)
//                            SYS_ACCOUNT_PO = new SysAccountPO();
//                        SYS_ACCOUNT_PO.setSysCompanyId(1L);*/
//                        logger.info(LOG_FORMAT_KEY, TOKEN_KEY, (new ObjectMapper()).writeValueAsString(SYS_ACCOUNT_PO));
//                        if (null != SYS_ACCOUNT_PO && null != SYS_ACCOUNT_PO.getSysCompanyId() && 0 < SYS_ACCOUNT_PO.getSysCompanyId()) {
//                            SYS_COMPANY_ID = SYS_ACCOUNT_PO.getSysCompanyId();
//                            logger.info(LOG_FORMAT_KEY, START_KEY, BOUND_SQL);
//                            MATCHER = PATTERN.matcher(BOUND_SQL);
//                            if (MATCHER.find())
//                                MATCHER_SQL = MATCHER.group();
//                            BOUND_SQL = parseSql(parseMatcherSql(MATCHER_SQL));
//                            FieldUtils.writeField(handler.getBoundSql(), "sql", BOUND_SQL, true);
//                            logger.info(LOG_FORMAT_KEY, END_KEY, BOUND_SQL);
//                        }
//                    }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return invocation.proceed();
//    }
//
//    /**
//     * @param namespace
//     * @return
//     * @throws Throwable
//     */
//    private Annotation getAnnotation(String namespace) {
//        try {
//            Annotation annotation = null;
//            if (!StringUtils.isEmpty(namespace)) {
//                String className = namespace.substring(0, namespace.lastIndexOf('.'));
//                String methodName = namespace.substring(namespace.lastIndexOf('.') + 1, namespace.length());
//                Method[] methods = Class.forName(className).getMethods();
//                for (Method method : methods) {
//                    if (method.getName().equals(methodName)) {
//                        annotation = method.getAnnotation(TenantNotInterceptor.class);
//                        break;
//                    }
//                }
//            }
//            return annotation;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    private String parseMatcherSql(String matcherSql) {
//        String aliasName = "";
//        String[] strings;
//        if (StringUtils.isEmpty(matcherSql)) {
//            strings = BOUND_SQL.substring(StringUtils.indexOfIgnoreCase(BOUND_SQL, "FROM") + 4).trim().split("\\s+");
//            if (1 == strings.length)
//                return strings[0].trim();
//            else
//                return strings[strings.length - 1];
//        }
//        strings = matcherSql.split("\\s+");
//        if (1 == strings.length)
//            return strings[0];
//        if (2 == strings.length || 3 == strings.length)
//            aliasName = strings[1].trim();
//        else if (4 == strings.length && !FIXED_PREFIX.contains(strings[2].substring(0, strings[2].indexOf("_") + 1))) {
//            aliasName = strings[2].trim();
//        } else if (5 == strings.length && !FIXED_PREFIX.contains(strings[3].substring(0, strings[3].indexOf("_") + 1)))
//            aliasName = strings[3].trim();
//        return aliasName;
//    }
//
//    private boolean checkEnabled() {
//        if (System.getProperties().containsKey(IS_ENABLED_TENANT_INTERCEPTOR))
//            return Boolean.valueOf(System.getProperty(IS_ENABLED_TENANT_INTERCEPTOR));
//        Map map = (Map) yaml.load(TenantInterceptor.class.getClassLoader().getResourceAsStream("application.yml"));
//        if (map.containsKey("tenant")) {
//            return Boolean.valueOf(((Map) map.get("tenant")).get("isEnabledTenantInterceptor").toString());
//        }
//        return true;
//    }
//
//    /**
//     * 　　* @Description: ${todo}
//     * 　　* @param ${tags}
//     * 　　* @return ${return_type}
//     * 　　* @throws
//     * 　　* @author Micro
//     * 　　* @date 2018/9/7 14:54
//     */
//    private boolean checkExistsTenant() {
//        return EXISTS_PATTERN.matcher(BOUND_SQL).find();
//    }
//
//    private String parseSql(String matcherSql) {
//
//        if (BOUND_SQL.lastIndexOf(matcherSql.concat(".").concat(TENANT_KEY.trim()).concat(String.valueOf(SYS_COMPANY_ID))) > 0)
//            return BOUND_SQL;
//        if (StringUtils.isNotBlank(matcherSql) && BOUND_SQL.lastIndexOf(matcherSql.concat(".").concat(TENANT_KEY.trim()).concat(String.valueOf(SYS_COMPANY_ID)).concat(" AND ")) < 0 && StringUtils.indexOfIgnoreCase(BOUND_SQL, WHERE_KEY.trim()) > 0)
//            TENANT_KEY = matcherSql.concat(".").concat(TENANT_KEY.trim()).concat(String.valueOf(SYS_COMPANY_ID)).concat(" AND ");
//        else if (StringUtils.isNotBlank(matcherSql))
//            TENANT_KEY = matcherSql.concat(".").concat(TENANT_KEY.trim()).concat(String.valueOf(SYS_COMPANY_ID));
//        else if (StringUtils.indexOfIgnoreCase(BOUND_SQL, WHERE_KEY.trim()) > 0)
//            TENANT_KEY = TENANT_KEY.trim().concat(String.valueOf(SYS_COMPANY_ID)).concat(" AND ");
//        else
//            TENANT_KEY = TENANT_KEY.trim().concat(String.valueOf(SYS_COMPANY_ID));
//        if (StringUtils.indexOfIgnoreCase(BOUND_SQL, WHERE_KEY.trim()) > 0 && StringUtils.indexOfIgnoreCase(BOUND_SQL, TENANT_KEY.trim()) < 0) {
//            BOUND_SQL = StringUtils.replaceIgnoreCase(BOUND_SQL, WHERE_KEY.trim(), WHERE_KEY.concat(TENANT_KEY));
//        }
//        else if(StringUtils.indexOfIgnoreCase(BOUND_SQL, WHERE_KEY.trim()) < 0 && StringUtils.indexOfIgnoreCase(BOUND_SQL, LIMIT_KEY.trim()) > 0) {
//            BOUND_SQL = StringUtils.replaceIgnoreCase(BOUND_SQL, LIMIT_KEY, WHERE_KEY.concat(TENANT_KEY.concat(LIMIT_KEY)));
//        } else if (StringUtils.indexOfIgnoreCase(BOUND_SQL, TENANT_KEY.trim()) < 0) {
//            BOUND_SQL = BOUND_SQL.concat(WHERE_KEY.concat(TENANT_KEY));
//        }
//        TENANT_KEY = TENANT_KEY_TEMP;
//        return BOUND_SQL;
//    }
//
//    @Override
//    public Object plugin(Object target) {
//        return Plugin.wrap(target, this);
//    }
//
//    @Override
//    public void setProperties(Properties properties) {
//    }
//}
