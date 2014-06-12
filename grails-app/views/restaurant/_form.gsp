<%@ page import="br.com.beezu.foodiez.domain.Restaurant" %>



<div class="fieldcontain ${hasErrors(bean: restaurantInstance, field: 'venueId', 'error')} ">
	<label for="venueId">
		<g:message code="restaurant.venueId.label" default="Venue Id" />
		
	</label>
	<g:textField name="venueId" value="${restaurantInstance?.venueId}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: restaurantInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="restaurant.name.label" default="Name" />		
	</label>
	<g:textField name="name" readonly="true" value="${restaurantInstance?.name}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: restaurantInstance, field: 'menus', 'error')} ">
	<label for="menus">
		<g:message code="restaurant.menus.label" default="Menus" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${restaurantInstance?.menus?}" var="m">
    <li><g:link controller="menu" action="show" id="${m.id}">${m?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="menu" action="create" params="['restaurant.id': restaurantInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'menu.label', default: 'Menu')])}</g:link>
</li>
</ul>


</div>

