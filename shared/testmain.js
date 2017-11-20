//in your main-test-file.js

window.karmaCustomEnv = {};
window.karmaCustomEnv.execute = function(karma, window) {
	//use the node assert module (via browserify)
//    var assert = require('assert');

    //You have access to the karma framework API
    console.log("Test suite started");
    
    try{
        //do your custom test here
//        assert(true);

    	var p = TestRunner.run()

    	karma.info({ total: p.length })
    	
    	var arrayLength = p.length;
		for (var i = 0; i < arrayLength; i++) {
			karma.result( p[i])
		}

    }catch(err){
        karma.result({
            success: false,
            suite: [],
            log: [err.message]
        });
    }

    karma.complete({});
};