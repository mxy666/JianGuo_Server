/*jQuery(document).ready(function($){
	//open popup
	$('.cd-popup-trigger').on('click', function(event){
		event.preventDefault();
		$('.cd-popup').addClass('is-visible');
	});
	
	//close popup
	$('.cd-popup').on('click', function(event){
		if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup') ) {
			event.preventDefault();
			$(this).removeClass('is-visible');
		}
	});
	//close popup when clicking the esc keyboard button
	$(document).keyup(function(event){
    	if(event.which=='27'){
    		$('.cd-popup').removeClass('is-visible');
	    }
    });
});*/
    function testAuto(thisId,needLeng){
        var ididid = document.getElementById(thisId);
        var nowLeng = ididid.innerHTML.length;
        if(nowLeng > needLeng){
        var nowWord = ididid.innerHTML.substr(0,needLeng)+'...';
        ididid.innerHTML = nowWord;
        }
    }   
testAuto('test',9)
