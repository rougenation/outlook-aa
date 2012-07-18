// JavaScript Document
(function(jQuery) {
	jQuery.fn.clickoutside = function(callback) {
		var outside = 1, self = $(this);
		self.cb = callback;
		this.mouseup(function() { 
			outside = 0; 
		});
		$(document).mouseup(function() { 
			outside && self.cb();
			outside = 1;
		});
		return $(this);
	}
})(jQuery);

$(function(){
	$('#menu ul li.dropdown a').each(function(){
		$(this).click(function(){
			var divdropdown = $(this).next();
			divdropdown.slideToggle(0, function(){
				if($(this).is(':hidden'))
			   {
				$(this).prev('a').removeClass('selected'); 
			   }
			   else
			   {
				$(this).prev('a').addClass('selected'); 
			   }
			});
		});
	})
	$('#menu ul li.dropdown').each(function(){
		$(this).clickoutside(function(){
			var divdropdown = $(this).children('.div_dropdown');
			divdropdown.hide();
			$(this).children('a').removeClass('selected'); 
		})
	})
	$('#menu ul li.dropdown li a').click(function(){
		var divdropdown = $(this).parents('.div_dropdown');
		var aSelected = divdropdown.prev('a');
		divdropdown.hide();
		aSelected.removeClass('selected'); 
	})
	//Set min width
	setminwidth();
})
function setminwidth() {
	var minWidth = 200; 
	var dropdown = $('#menu .div_dropdown');
	if ( dropdown.width() < minWidth )
	{ 
		dropdown.width(minWidth);
	}
}