/**
 * Script for authenticating
 * Deprecated : Better aproach is to use the Spring AOP to 
 * check for logged in user before the required busyness logic 
 * is executed
 * 
 */
var authenticated = false;
$(document).ready(isAuthenticated());
function isAuthenticated() {
	$.get("isA")
}
