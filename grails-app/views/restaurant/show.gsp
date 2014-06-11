
<%@ page import="br.com.beezu.foodiez.domain.Restaurant" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'restaurant.label', default: 'Restaurant')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-restaurant" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-restaurant" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list restaurant">
			
				<g:if test="${restaurantInstance?.venueId}">
				<li class="fieldcontain">
					<span id="venueId-label" class="property-label"><g:message code="restaurant.venueId.label" default="Venue Id" /></span>
					
					<span class="property-value" aria-labelledby="venueId-label"><g:fieldValue bean="${restaurantInstance}" field="venueId"/></span>
					
				</li>
				</g:if>

                <g:if test="${restaurantInstance?.name}">
                    <li class="fieldcontain">
                        <span id="venueId-label" class="property-label"><g:message code="restaurant.name.label" default="Name" /></span>

                        <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${restaurantInstance}" field="name"/></span>

                    </li>
                </g:if>

				<g:if test="${restaurantInstance?.menus}">
				<li class="fieldcontain">
					<span id="menus-label" class="property-label"><g:message code="restaurant.menus.label" default="Menus" /></span>
					
						<g:each in="${restaurantInstance.menus}" var="m">
						<span class="property-value" aria-labelledby="menus-label"><g:link controller="menu" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:restaurantInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${restaurantInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
