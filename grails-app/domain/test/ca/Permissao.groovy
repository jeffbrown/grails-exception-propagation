package test.ca

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='authority')
@ToString(includes='authority', includeNames=true, includePackage=false)
class Permissao implements Serializable {

	String nome
	String descricao
	String authority

	static constraints = {
        nome blank: false, unique: true
        descricao blank: false, unique: true
		authority blank: false, unique: true
		secao nullable: false
	}

	static mapping = {
		cache true
		version false
		table schema: "ca"
	}
}
