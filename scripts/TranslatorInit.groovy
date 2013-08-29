includeTargets << grailsScript('_GrailsBootstrap')

target(main: "The description of the script goes here!") {
    depends(configureProxy, packageApp, classpath, loadApp, configureApp)
    def service = appCtx.getBean('translationService')
    service.set('brand.name', 'Redis-Translations', Locale.ENGLISH)

}

setDefaultTarget(main)
