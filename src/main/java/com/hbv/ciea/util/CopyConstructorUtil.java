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
package com.hbv.ciea.util;

import com.hbv.ciea.dto.ErrorRestDTO;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Utilitario para copiar objetos usando CopyConstructor.
 *
 * @author Herman Barrantes
 * @since 15-ene-2015
 */
@Component
public class CopyConstructorUtil {

    /**
     * Crea una copia de X en Y mediante CopyConstructor.
     *
     * @param <X> Clase a ser copiada
     * @param <Y> Clase copiada
     * @param objeto Objeto a copiar
     * @param clase Clase de retorno (debe implementar CopyConstructor)
     * @return Objeto copiado
     */
    public <X, Y> Y copiarObjeto(X objeto, Class<Y> clase) {
        try {
            Constructor<Y> constructor = clase.getConstructor(objeto.getClass());
            Y instancia = constructor.newInstance(objeto);
            return instancia;
        } catch (Exception ex) {
            throw new ErrorRestDTO("error.copy_constructor", ex);
        }
    }

    /**
     * Crea una copia de X en Y mediante CopyConstructor para una lista de
     * elementos.
     *
     * @param <X> Clase a ser copiada
     * @param <Y> Clase copiada
     * @param lista Lista de objetos a copiar
     * @param clase Clase de retorno (debe implementar CopyConstructor)
     * @return Lista de objetos copiados
     */
    public <X, Y> List<Y> copiarLista(Iterable<X> lista, Class<Y> clase) {
        List<Y> instancias = new ArrayList<Y>();
        for (X item : lista) {
            instancias.add(copiarObjeto(item, clase));
        }
        return instancias;
    }
}
