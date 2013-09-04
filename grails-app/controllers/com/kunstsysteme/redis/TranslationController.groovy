package com.kunstsysteme.redis

class TranslationController {

    def translationService

    def list() {
        [list: translationService.listKeys(), supported: translationService.supportedLocales.language]
    }

    def details(String key) {
        [key: key, obj: translationService.getComplete(key), supported: translationService.supportedLocales.language]
    }

    def save(String key) {
        translationService.setAll(key, params)
        flash.success = true
        flash.message = g.message(code: 'translator.save.success')
        redirect(action: 'list')
    }

    def clearMissing() {
        translationService.clearMissing()
        flash.success = true
        flash.message = g.message(code: 'translator.save.success')
        redirect(request.getHeader('referer'))
    }

    def find(String search) {
        def list
        if (search)
            list = translationService.find(search)
        [list: list, supported: translationService.supportedLocales.language, search: search ?: '']
    }

    def missing() {
        [list: translationService.getMissing(), supported: translationService.supportedLocales.language]
    }

    def stats() {
        [list: translationService.listKeys()]
    }
}
