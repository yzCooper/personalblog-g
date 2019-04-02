package com.yzblog.datacenter.web.modules.sysmonitor.aspect;

import com.yzblog.datacenter.web.core.util.StringDateUtils;
import com.yzblog.datacenter.web.modules.sysmonitor.dao.DsSOperationLogDao;
import com.yzblog.datacenter.web.modules.sysmonitor.util.RequestStatus;
import com.yzblog.datacenter.web.modules.sys.util.Constans;
import com.yzblog.datacenter.web.modules.sysmonitor.entity.DsSOperationLog;
import com.yzblog.datacenter.web.modules.sysmonitor.util.LogType;
import net.sf.json.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller层日志aop切入点
 *
 * @author yuzhou
 * @create 2017-10-19 17:33:00
 */
@Component
@Aspect
public class ControllerLogAspect {
    private static final Logger LOG = LoggerFactory.getLogger(ControllerLogAspect.class);

    private final DsSOperationLogDao dsSOperationLogDao;

    @Autowired
    public ControllerLogAspect(DsSOperationLogDao dsSOperationLogDao) {
        this.dsSOperationLogDao = dsSOperationLogDao;
    }


    /**
     * Spring Controller层切点
     */
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMappingAspect() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMappingAspect() {
    }

    @Around("postMappingAspect() || getMappingAspect()")
    public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        Throwable throwable = null;
        String exceptionMessage = null;
        RequestStatus requestStatus;

        try {
            result = pjp.proceed();
            // 处理状态代码
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            int statusCode = response.getStatus();
            if (!HttpStatus.valueOf(statusCode).is2xxSuccessful()) {
                requestStatus = RequestStatus.FAIL;
                exceptionMessage = "request error, statusCode: " + statusCode;
            } else {
                requestStatus = RequestStatus.SUCCESS;
            }

        } catch (Throwable ex) {
            throwable = ex;
            requestStatus = RequestStatus.FAIL;
            exceptionMessage = ex.getMessage();
        }

        try {
            insertControllerLog(pjp, requestStatus, exceptionMessage);
        } catch (Exception e) {
            LOG.error("controller access log save error.", e.getMessage());
        }

        if (throwable != null) {
            throw throwable;
        }

        return result;

    }

    /**
     * insert控制器日志
     *
     * @param pjp              aop对象
     * @param requestStatus    请求状态
     * @param exceptionMessage 异常信息
     */
    private void insertControllerLog(ProceedingJoinPoint pjp, RequestStatus requestStatus, String exceptionMessage) {
        // 获取用户id
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Long userID = getUserID(request);
        Enumeration enu=request.getParameterNames();
        Map<String,Object> map = new HashMap<>();
        while(enu.hasMoreElements()){
            String paraName=(String)enu.nextElement();
            map.put(paraName,request.getParameter(paraName));
        }
        JSONObject jsonObject = JSONObject.fromObject(map);
        DsSOperationLog dsSOperationLog = new DsSOperationLog();
        dsSOperationLog.setLogType(String.valueOf(LogType.CONTROLLER.getValue()));
        dsSOperationLog.setClassName(pjp.getTarget().getClass().getName());
        dsSOperationLog.setMethodName(pjp.getSignature().getName());
        dsSOperationLog.setReqTime(new Date());
        dsSOperationLog.setReqUrl(request.getRequestURI());
        dsSOperationLog.setReqParam(jsonObject.toString());
        dsSOperationLog.setReqIp(request.getRemoteAddr());
        dsSOperationLog.setUserId(StringDateUtils.nullToString(userID));
        dsSOperationLog.setReqStatus(String.valueOf(requestStatus.getValue()));
        dsSOperationLog.setExcpMessage(exceptionMessage);
        // 写入数据库
        dsSOperationLogDao.insertSelective(dsSOperationLog);
    }

    /**
     * 获取用户ID
     *
     * @param request HttpServletRequest
     * @return 用户ID
     */
    private Long  getUserID(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Long) session.getAttribute(Constans.USER_ID);
    }

    /**
     * 获取用户ID
     *
     * @param request HttpServletRequest
     * @return 用户ID
     */
    private String  getUserName(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute(Constans.USER_NAME);
    }

}
