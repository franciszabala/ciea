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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Herman
 * @since 03/03/2015
 */
public class ActivoSpecifications {

    public static Specification<Activo> hasPlaca(final String placa) {
        return new Specification<Activo>() {

            @Override
            public Predicate toPredicate(Root<Activo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.<String>get("placa"), placa);
            }
        };
    }
    
    public static Specification<Activo> hasSitio(final String placa) {
        return new Specification<Activo>() {

            @Override
            public Predicate toPredicate(Root<Activo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.<String>get("placa"), placa);
            }
        };
    }
}
