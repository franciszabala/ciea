/*
 * Copyright 2015 Codigo Fantasma.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hbv.ciea.repository.specification;

import com.hbv.ciea.model.Activo;
import com.hbv.ciea.model.Activo_;
import com.hbv.ciea.model.Sitio_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Herman
 * @since 03/03/2015
 * @see
 * http://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/
 * @see
 * https://blog.42.nl/articles/spring-data-jpa-with-querydsl-repositories-made-easy/
 * @see http://www.altuure.com/2010/09/23/jpa-criteria-api-by-samples-part-i/
 * @see
 * http://stackoverflow.com/questions/7790794/some-basic-questions-on-criteria-from-jpa-2-0
 * @see http://docs.oracle.com/javaee/6/tutorial/doc/gjivm.html
 * @see http://en.wikibooks.org/wiki/Java_Persistence/Criteria
 *
 */
public class ActivoSpecifications {

    public static Specification<Activo> isHabilitado() {
        return new Specification<Activo>() {

            @Override
            public Predicate toPredicate(Root<Activo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.isTrue(root.get(Activo_.habilitado));
            }
        };
    }
    
    public static Specification<Activo> hasPlaca(final String placa) {
        return new Specification<Activo>() {

            @Override
            public Predicate toPredicate(Root<Activo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(cb.lower(root.get(Activo_.placa)), placa.toLowerCase());
            }
        };
    }

    public static Specification<Activo> inSitio(final Long sitio) {
        return new Specification<Activo>() {

            @Override
            public Predicate toPredicate(Root<Activo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.join(Activo_.sitio).get(Sitio_.id), sitio);
            }
        };
    }
}
