grails-redis-translations
=========================

Grails Plugin to store i18n messages in a [redis server](http://redis.io/).


Provides
------------------------
A MessageSource which looks up message codes in a redis key-value store.
A Frontend to edit messages on the fly.

Usage
------------------------

1. Install the plugin

	compile ":redis-translations:0.0.1"

2. Optional: Configure you default locale and your supported locales in Config.groovy see Config

3. Start redis-server (if not running)

4. Run the migration script

	grails translator-migrate

5. Optional: Run the translator init script to set all messages used in admin frontend

	grails tranlator-init

6. Start you webapp

	grails run-app

7. If the migration ran smoothly, you should not see any difference by now

8. point you browser to http://localhost:8080/translation to reach the backoffice

9. ???

10. profit


Config
------------------------

The following values can be changed in Config.groovy

The Default locale to use if no locale is given

	grails.plugin.redis.translation.locale.default = ENGLISH

The supported locales. If something else is requested, the default locale will be used

	grails.plugin.redis.translation.locale.supported = [ENGLISH, GERMAN, FRENCH]

The Key prefix for all translator keys

	grails.plugin.redis.translation.prefix = '_translation.'

The name of the key which is holding all missing keys. This key is used to enable faster lookup of missing keys

	grails.plugin.redis.translation.missing = '_missing.'

You should import java.util.Locale.* at the beginning of your Config.groovy if you want to change default or supported locales

	import static java.util.Locale.*