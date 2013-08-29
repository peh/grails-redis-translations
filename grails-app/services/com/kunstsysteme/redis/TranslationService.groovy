package com.kunstsysteme.redis

import redis.clients.jedis.Jedis

class TranslationService {

    static transactional = false

    def redisService
    def grailsApplication
    public static final String READ_COUNT_FIELD = 'read_count'
    public static final String WRITE_COUNT_FIELD = 'write_count'

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
            } else {
                jedis.hincrBy(key, READ_COUNT_FIELD, 1)
            }
        }
        result
    }

    void set(String code, String value, Locale locale = defaultLocale) {
        def lang = locale.language
        def key = munch(code)
        redisService.withRedis { Jedis jedis ->
            jedis.hset(key, lang, value)
            jedis.hincrBy(key, WRITE_COUNT_FIELD, 1)
        }
    }

    void setAll(String code, def map) {
        String key = munch(code)
        redisService.withRedis { Jedis jedis ->
            map.each { lang, value ->
                if (lang in supportedLocales.language) {
                    jedis.hset(key, lang, value)
                    jedis.hincrBy(key, READ_COUNT_FIELD, 1)
                }
            }
            jedis.srem(missingKey, key)
        }
    }

    def getMissing() {
        getAll(redisService.smembers(missingKey))
    }

    private def getAll(def keys) {
        def result = [:]
        redisService.withRedis { Jedis jedis ->
            keys.each() { key ->
                result << ["$key": jedis.hgetAll(key)]
            }
        }
        result
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
