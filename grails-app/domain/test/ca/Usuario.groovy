package test.ca


import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=false, includePackage=false)
class Usuario implements Serializable {

    String username
	String password

    Perfil perfil

    transient boolean enabled = true
    transient boolean accountExpired
    transient boolean accountLocked
    transient boolean passwordExpired

    transient springSecurityService

    static constraints = {
        password blank: false, password: true
        username blank: false, unique: true, maxSize: 15
        nome nullable: false, blank: false, maxSize: 128
        cpf nullable: false
        registroGeral nullable: false
        telefone nullable: false
        email nullable: false, email: true
        funcao nullable: false
        registroPrefeitura nullable: true, maxSize: 40
        conselho nullable: true, maxSize: 40
        registroProfissional nullable: true, maxSize: 40
    }

    static mapping = {
        password column: '`password`'
        version false
        table schema: "ca"
    }

    static transients = ['springSecurityService']

    Set<Perfil> getAuthorities() {
        [perfil]
    }

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}
}
