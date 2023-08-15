package com.zkh.config;





import com.zkh.filter.JwtAuthenticationTokenFiler;
import com.zkh.handler.AccessDeniedHandlerImpl;
import com.zkh.handler.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationTokenFiler jwtAuthenticationTokenFiler;
    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;
    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //关闭csrf
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //取消session
        .and()
        .authorizeRequests()
        //todo 目前全部放行，完善登录请修改放行，添加数据库
        .antMatchers("/img/**").permitAll()
        .antMatchers("/login").anonymous()//匿名状态未登录可以访问
        .anyRequest().authenticated();
       //添加过滤器处理token
        http.addFilterBefore(jwtAuthenticationTokenFiler, UsernamePasswordAuthenticationFilter.class);
        //异常处理json返回
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler);
        //允许跨域
        http.cors();

    }
}
