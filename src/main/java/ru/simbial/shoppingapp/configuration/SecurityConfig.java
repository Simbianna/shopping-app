package ru.simbial.shoppingapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.simbial.shoppingapp.service.UserService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /* private DataSource dataSource;*/
    private UserService userService;

   /* @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().permitAll();
    /*    http.authorizeRequests()
                //Доступ только для не зарегистрированных пользователей
                .antMatchers("/registration").not().fullyAuthenticated()
                //Доступ только для пользователей с ролью Администратор
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/products/edit/**").hasRole("ADMIN")
                .antMatchers("/news").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/");*/

/*
        http.authorizeRequests()
                //  .anyRequest().permitAll()
                .antMatchers("/products/edit/**").hasAnyRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/authenticateTheUser")
                .permitAll();*/
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // (1)
//        auth.jdbcAuthentication().dataSource(dataSource);
//    }
/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // (2)
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        auth.inMemoryAuthentication()
                .withUser(users.username("user1").password("pass1").roles("USER", "ADMIN"))
                .withUser(users.username("user2").password("pass2").roles("USER"));
    }*/

}
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest()
//                .permitAll()
//                .and().csrf().disable();
//    }
