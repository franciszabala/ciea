/*
 * Copyright 2014 Codigo Fantasma.
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
package com.hbv.ciea.rest;

import com.hbv.ciea.dto.ErrorRestDTO;
import com.hbv.ciea.dto.ErrorValidacionDTO;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Herman
 * @since 2014-12-16
 * @see
 * http://www.javacodegeeks.com/2013/05/spring-from-the-trenches-adding-validation-to-a-rest-api.html
 * @see http://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc
 */
@ControllerAdvice
public class RestErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorRestDTO errorRestGenerico(Exception ex) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String mensaje = messageSource.getMessage("error.generico", null, currentLocale);
        return new ErrorRestDTO(mensaje, ex);
    }

    @ExceptionHandler(ErrorRestDTO.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorRestDTO errorRestEspecifico(ErrorRestDTO ex) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String mensaje = messageSource.getMessage(ex.getMensaje(), ex.getArgumentos(), currentLocale);
        return new ErrorRestDTO(mensaje, ex.getMensajeError());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ErrorValidacionDTO errorValidacion(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return processFieldErrors(fieldErrors);
    }

    private ErrorValidacionDTO processFieldErrors(List<FieldError> erroresCampo) {
        ErrorValidacionDTO dto = new ErrorValidacionDTO();

        for (FieldError errorCampo : erroresCampo) {
            String localizedErrorMessage = resolverMensajeError(errorCampo);
            dto.addErrorCampo(errorCampo.getField(), localizedErrorMessage);
        }

        return dto;
    }

    private String resolverMensajeError(FieldError errorCampo) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(errorCampo, currentLocale);

        //If the message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
        if (localizedErrorMessage.equals(errorCampo.getDefaultMessage())) {
            String[] fieldErrorCodes = errorCampo.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }

        return localizedErrorMessage;
    }

}
