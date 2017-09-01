# jsontest

## Karma testing

Run:

    sbt jsontest-sharedJS/test:npmUpdate jsontest-sharedJS/test:buildKarma
    cd shared\js\target\scala-2.12\scalajs-bundler\test
    node .\node_modules\karma\bin\karma start karma.conf.js

Then start browser and go to http://<hostname>:9876
