package com.kunstsysteme.redis

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.springframework.context.support.AbstractMessageSource

import java.text.MessageFormat

class RedisMessageSource extends AbstractMessageSource {

    static GrailsApplication application

    static translationService

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        if (!translationService)
            translationService = application.mainContext.getBean('translationService')

        def format

        def result = translationService.get(code, locale)

        if(result)
            format = new MessageFormat(result, locale)
        else
            format = new MessageFormat(code, locale)

        return format;
    }
}