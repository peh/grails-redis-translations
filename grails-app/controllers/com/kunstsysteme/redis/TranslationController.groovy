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

    def clearMissing(){
        translationService.clearMissing()
        flash.success = true
        flash.message = g.message(code: 'translator.save.success')
        redirect(action: 'list')
    }
}
