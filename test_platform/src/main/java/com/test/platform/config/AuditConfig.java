package com.test.platform.config;

import com.test.shiro.dto.ShiroUser;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class AuditConfig {

    public class UserIdAware implements AuditorAware<String> {
        @Override
        public Optional<String> getCurrentAuditor() {
            Subject subject = ThreadContext.getSubject();
            if(subject != null){
                ShiroUser user = (ShiroUser) subject.getPrincipals().getPrimaryPrincipal();
                return Optional.of(user.getLoginName());
            }

            return Optional.of("-1000");

        }
    }
}
