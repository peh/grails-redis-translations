package com.kunstsysteme.redis

import redis.clients.jedis.Jedis

class TranslationService {

    static transactional = false

    def redisService
    def grailsApplication

    def getComplete(String code) {
        redisService.hgetAll(munch(code))
    }

    def get(String code, Locale locale = defaultLocale) {
        def lang = locale.language
        def key = munch(code)
        def result = code
        redisService.withRedis { Jedis jedis ->
            result = jedis.hget(key, lang)
            if (!result) {
                jedis.sadd(missingKey, key)
            }
        }
        result
    }

    void set(String code, String value, Locale locale = defaultLocale) {
        def lang = locale.language
        redisService.withRedis { Jedis jedis ->
            jedis.hset(munch(code), lang, value)
        }
    }

    void setAll(String code, def map) {
        String key = munch(code)
        redisService.withRedis { Jedis jedis ->
            map.each { lang, value ->
                if (lang in supportedLocales.language)
                    jedis.hset(key, lang, value)
            }
            jedis.srem(missingKey, key)
        }
    }

    def getMissing() {
        redisService.smembers(missingKey)
    }

    def listKeys() {
        def result = [:]
        redisService.withRedis { Jedis jedis ->
            jedis.smembers(missingKey).each { key2 ->
                result.put(demunch(key2), [:])
            }
            jedis.keys("${prefix}*").each { key ->
                if (key != missingKey) {
                    def all = jedis.hgetAll(key)
                    result.put(demunch(key), all)
                }

            }
        }
        result
    }

    void clearMissing() {
        redisService.del(missingKey)
    }

    private String munch(String code) {
        "${prefix}${code}"
    }

    private String demunch(String key) {
        key.replaceFirst(prefix, '')
    }

    private String getPrefix() {
        return grailsApplication.config.grails.plugin.redis.translation.prefix
    }

    private String getMissingKey() {
        return "${prefix}${grailsApplication.config.grails.plugin.redis.translation.missing}"
    }

    List<Locale> getSupportedLocales() {
        return grailsApplication.config.grails.plugin.redis.translation.locale.supported
    }

    Locale getDefaultLocale() {
        return grailsApplication.config.grails.plugin.redis.locale.default
    }
}
