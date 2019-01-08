/**
 * Script for authenticating
 * Deprecated : Better approach is to use the Spring AOP to 
 * check for logged in user before the required business logic 
 * is executed
 *	TODO delete
 */
var authenticated = false;
$(document).ready(isAuthenticated());
function isAuthenticated() {
	$.get("isA")
}
