package bukmacher.bukmacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


//@EnableOAuth2Sso
//@RestController
@SpringBootApplication
public class BukmacherApplication{
// extends WebSecurityConfigurerAdapter

	public static void main(String[] args) {
		SpringApplication.run(BukmacherApplication.class, args);
	}

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .antMatcher("/**")
//                .authorizeRequests()
//                .antMatchers("/", "/login**", "/webjars/**", "/error**")
//                .permitAll()
//                .anyRequest()
//                .authenticated();
//    }
//
//    @RequestMapping("/user")
//    public Principal user(Principal principal) {
//        return principal;
//    }
////
}
