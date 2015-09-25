package com.sunwayland.core.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;


/**
 *  控制  密码重复输入;  错误5次 锁死ip 半个小时; 
 * @author grrey
 *
 */
public class RetryLimitMatcher  extends HashedCredentialsMatcher{
    
    private Cache passwordRetryCache;   // 缓存 ip 上重复次数; 

    public RetryLimitMatcher( org.apache.shiro.cache.CacheManager manager ) {
       
        passwordRetryCache = manager.getCache("passwordRetryCache");
    }
    
    public RetryLimitMatcher(   ) {
    	 super();
    }
    
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String)token.getPrincipal();
        
        //retry count + 1
      /*    Element element = passwordRetryCache.get(username);
        if(element == null) {
            element = new Element(username , new AtomicInteger(0));
            passwordRetryCache.put(element);
        }
        AtomicInteger retryCount = (AtomicInteger)element.getObjectValue();
        if(retryCount.incrementAndGet() > 5) {
            //if retry count > 5 throw
            throw new ExcessiveAttemptsException();
        }*/

        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            //clear retry count
            passwordRetryCache.remove(username);
        }
        return matches;
    }
    
}
