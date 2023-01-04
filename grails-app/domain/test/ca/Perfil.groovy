package test.ca

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes='nome')
class Perfil implements Serializable {

	String nome
	String descricao

	static constraints = {
		nome blank: false, unique: true
	}

	static mapping = {
        table schema: "ca"
		version false
    	cache true
	}

	Set<Permissao> getAuthorities() {
		PerfilPermissao.findAllByPerfil(this)*.permissao
	}

	String toString(){
		nome
	}
}
