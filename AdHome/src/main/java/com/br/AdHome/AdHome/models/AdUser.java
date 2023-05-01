package com.br.AdHome.AdHome.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="tb_ad_user")
public class AdUser implements UserDetails, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false, unique = true, length = 10)
	private Long userId;
	
	@Column(name = "nome_user", nullable = false, length = 60)
	@Size(min = 3)
	private String nomeUser;
	
	@Column(name="nome", nullable=true, length=60)
	private String nome;
	
	@Column(name="email_user", nullable=false, length=60)
	private String emailUser;
	
	@Column(name="senha_user", nullable=false, length=60)
	private String password;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
	private List<RoleModel> roles;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST)
	private Pedido pedidos;
	
	public AdUser() {
		
	}
	public AdUser(String nome, String nomeUser, String emailUser, String password) {
		this.nome = nome;
		this.nomeUser = nomeUser;
		this.emailUser = emailUser;
		this.password = password;
		
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getNomeUser() {
		return nomeUser;
	}
	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}
	public String getEmailUser() {
		return emailUser;
	}
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Pedido getPedidos() {
		return pedidos;
	}
	public void setPedidos(Pedido pedidos) {
		this.pedidos = pedidos;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<RoleModel> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleModel> roles) {
		this.roles = roles;
	}
	@Override
	public int hashCode() {
		return Objects.hash(nome, emailUser, nomeUser, password, userId, pedidos);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdUser other = (AdUser) obj;
		return Objects.equals(emailUser, other.emailUser) && Objects.equals(nomeUser, other.nomeUser)
				&& Objects.equals(password, other.password) && Objects.equals(userId, other.userId)
				&& Objects.equals(nome, other.nome) && Objects.equals(roles, other.roles) && Objects.equals(pedidos, other.pedidos);
	}
	@Override
	public String toString() {
		return "User [userId=" + userId +", nome=" + nome + ", nomeUser=" + nomeUser + 
				", emailUser=" + emailUser + ", password="+ password + ", pedidos="+ pedidos +"]";
	}
 
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.nomeUser;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}
}
