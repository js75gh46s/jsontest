module.exports = function(config) {
  config.set({
    plugins: [
    	'karma-chrome-launcher',
    	'karma-custom'
    ],
    reporters: ['progress'],
    frameworks: [
    	'custom'
    ],
    files: [
      'jsontest-shared-test-fastopt-bundle.js',
      'testmain.js'
    ],

    browsers: process.env.TRAVIS ? ['ChromeNoSandbox'] : ['Chrome'],

    customLaunchers: {
      ChromeNoSandbox: {
        base: 'Chrome',
        flags: ['--no-sandbox']
      }
    },

    autoWatch: true,

    client: {
      tests: [
        "com.example.jsontest.rest.test.TestProtocol"
      ]
    },
    
    // Concurrency level
    // how many browser should be started simultanous
    concurrency: 1
    
  });
};
