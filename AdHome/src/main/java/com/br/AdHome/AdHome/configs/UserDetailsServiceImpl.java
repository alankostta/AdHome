package com.br.AdHome.AdHome.configs;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.AdHome.AdHome.models.AdUser;
import com.br.AdHome.AdHome.repositories.AdUserRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	AdUserRepository aduserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdUser adUser = aduserRepository
				.findByNomeUser(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usúario não encontrado na base de dados!" + username));
		
		return new User(adUser.getNomeUser(),adUser.getPassword(),true,true,true,true,adUser.getAuthorities());
	}
	public AdUser saveUser(AdUser user) {
		return aduserRepository.save(user);
	}
	public List<AdUser> findAllUser() {
		return aduserRepository.findAll();
	}
	public Optional<AdUser> findByUserId(Long id) {
		return aduserRepository.findById(id);
	}
	public void deleteUser(AdUser user) {
		aduserRepository.delete(user);
	}
	public Optional<AdUser> findByUserName(String username){
		return aduserRepository.findByNomeUser(username);
	}
}
