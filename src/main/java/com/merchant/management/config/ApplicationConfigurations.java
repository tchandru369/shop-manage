/*
 * package com.merchant.management.config;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.authentication.AuthenticationProvider; import
 * org.springframework.security.authentication.dao.DaoAuthenticationProvider;
 * import
 * org.springframework.security.config.annotation.authentication.configuration.
 * AuthenticationConfiguration; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * 
 * import com.merchant.management.repository.MerchantRepository;
 * 
 * public class ApplicationConfigurations {
 * 
 * private final MerchantRepository merchantRepository;
 * 
 * public ApplicationConfigurations(MerchantRepository merchantRepository) {
 * this.merchantRepository = merchantRepository; }
 * 
 * // @Bean // UserDetailsService userDetailsService() { // return username ->
 * merchantRepository.findByEmail(username) // .orElseThrow(() -> new
 * UsernameNotFoundException("User not found")); // }
 * 
 * @Bean BCryptPasswordEncoder passwordEncoder() { return new
 * BCryptPasswordEncoder(); }
 * 
 * @Bean public AuthenticationManager
 * authenticationManager(AuthenticationConfiguration config) throws Exception {
 * return config.getAuthenticationManager(); }
 * 
 * @Bean AuthenticationProvider authenticationProvider() {
 * DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
 * 
 * authProvider.setUserDetailsService(userDetailsService());
 * authProvider.setPasswordEncoder(passwordEncoder());
 * 
 * return authProvider; } }
 */