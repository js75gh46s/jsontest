module.exports = function(config) {
  config.set({
    plugins: ['karma-chrome-launcher', 'karma-scalajs-scalatest'],
    reporters: ['progress'],
    frameworks: ['scalajs-scalatest'],
    files: [
      'jsontest-shared-test-fastopt-bundle.js'
    ],

    browsers: process.env.TRAVIS ? ['Firefox'] : ['Chrome'],

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
