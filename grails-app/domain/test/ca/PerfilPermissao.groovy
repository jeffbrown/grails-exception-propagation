package test.ca

import grails.gorm.DetachedCriteria
import groovy.transform.ToString
import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class PerfilPermissao implements Serializable {

	Perfil perfil
	Permissao permissao

    static constraints = {
        permissao validator: { Permissao r, PerfilPermissao rg ->
            if (rg.perfil?.id) {
                PerfilPermissao.withNewSession {
                    if (PerfilPermissao.exists(rg.perfil.id, r.id)) {
                        return ['roleGroup.exists']
                    }
                }
            }
        }
    }

    static mapping = {
        id composite: ['perfil', 'permissao']
        version false
        table schema: "ca"
    }

	static PerfilPermissao get(long perfilId, long permissaoId) {
		criteriaFor(perfilId, permissaoId).get()
	}

	static boolean exists(long perfilId, long permissaoId) {
		criteriaFor(perfilId, permissaoId).count()
	}

	private static DetachedCriteria criteriaFor(long perfilId, long permissaoId) {
		PerfilPermissao.where {
			perfil == Perfil.load(perfilId) &&
			permissao == Permissao.load(permissaoId)
		}
	}

	static PerfilPermissao create(Perfil perfil, Permissao permissao) {
		new PerfilPermissao(perfil: perfil, permissao: permissao).save()
	}

	static void create(Perfil perfil, String[] permissoesIds) {
		for(permissaoId in permissoesIds){
			def permissao = new Permissao()
			permissao.setId(permissaoId.toInteger())
			this.create(perfil, permissao)
		}
	}

    static void create(Perfil perfil, List<Permissao> permissoes) {
        for(permissao in permissoes){
            this.create(perfil, permissao)
        }
    }

	static boolean remove(Perfil rg, Permissao r) {
		if (rg != null && r != null) {
			PerfilPermissao.where { perfil == rg && permissao == r }.deleteAll()
		}
	}

	static int removeAll(Permissao r) {
		r == null ? 0 : PerfilPermissao.where { permissao == r }.deleteAll()
	}

	static int removeAll(Perfil rg) {
		rg == null ? 0 : PerfilPermissao.where { perfil == rg }.deleteAll()
	}

	@Override
	boolean equals(other) {
		if (other instanceof PerfilPermissao) {
			other.permissaoId == permissao?.id && other.perfilId == perfil?.id
		}
	}

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (perfil) builder.append(perfil.id)
		if (permissao) builder.append(permissao.id)
		builder.toHashCode()
	}
}
