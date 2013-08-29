grails-redis-translations
=========================

Grails Plugin to store i18n messages in a [redis server](http://redis.io/).


Provides
------------------------
A MessageSource which looks up message codes in a redis key-value store.
A Frontend to edit messages on the fly.

Usage
------------------------
- Install the plugin
	compile ":redis-translations:0.0.1"
- Configure you default locale and your supported locales in Config.groovy see Config
- Start redis-server (if not running)
- Run the migration script
	grails translator-migrate
- Start you webapp
	grails run-app
- If the migration ran smoothly, you should not see any difference by now


Config
------------------------
The Default locale to use if no locale is given

	grails.plugin.redis.translation.locale.default = ENGLISH

The supported locales. If something else is requested, the default locale will be used

	grails.plugin.redis.translation.locale.supported = [ENGLISH, GERMAN, FRENCH]

The Key prefix for all translator keys

	grails.plugin.redis.translation.prefix = '_translation.'

The name of the key which is holding all missing keys. This key is used to enable faster lookup of missing keys

	grails.plugin.redis.translation.missing = '_missing.'